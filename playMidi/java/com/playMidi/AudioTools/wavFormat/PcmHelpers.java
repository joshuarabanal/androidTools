package com.playMidi.AudioTools.wavFormat;

import android.media.AudioTrack;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PcmHelpers {

	public static class Short{
		public static void write( short[] buffer, OutputStream out) throws IOException {
			writeShortBuffer(buffer, out);
		}
		public static int read(InputStream in, short[] buffer){
			return read(in, buffer);
		}
	}

	public static int frameValueLittleEndian(int firstByte, int secondByte){
		int one = 1;
		if(secondByte>127){//this is a negative frame value
			secondByte-=256;
			one = -1;
		}
		return (firstByte+(secondByte*(256)));
	}
	public static void writeFrameToAudioTrack(AudioTrack track, int frame){
		byte[] b = {
		 (byte) (frame & 0xFF),
		 (byte) (((frame >> 8) & 0xff) )
		};
		track.write(b, 0, 2);
	}
	private static byte[] byteBuffer;
	public static void writeShortBuffer(OutputStream out, short[] buffer, int start, int length) throws IOException {
		if(byteBuffer == null){ byteBuffer = new byte[1028];}
		int byteBufferIndex = 0;
		for(int i = 0; i<length; i++){
			if(byteBufferIndex+1>=byteBuffer.length){
				out.write(byteBuffer,0, byteBufferIndex);
				byteBufferIndex = 0;
			}
			byteBuffer[byteBufferIndex++] = (byte)(buffer[i+start]&0xFF);
			byteBuffer[byteBufferIndex++] =(byte)((buffer[i+start]>>8) & 0xff);

		}
		out.write(byteBuffer,0,byteBufferIndex);
	}
	public static void writeShortBuffer(short[] buffer, OutputStream fos)throws IOException {
		writeShortBuffer(fos, buffer,0,buffer.length);
	}
    public static int readunsignedLittleEndian(InputStream in, int length) throws IOException {
        int retu = 0;
		int temp;
        for (int i = 0; i <length; i++) {
            if((temp = in.read())<0){ throw new IOException("unexpected end of file:"+temp); }
            retu += temp << (8*i);
        }

        return retu;
    }

    public static int readsignedLittleEndian(InputStream in, int length) throws IOException {
		int val = readunsignedLittleEndian(in,length);

		int tempmaxPositive = (0x01<<((8*(length-1))+7)) - 1;
		if(val>tempmaxPositive) {
			tempmaxPositive = (tempmaxPositive<<1) +1;
			return val - tempmaxPositive;
		}
		return val;
	}

	public static void writeUnsignedLittleEndian(OutputStream out, int value, int length) throws IOException {
		for(int i = 0; i<length; i++){
			out.write( 0xff&((value)>>(i*8)) );
		}
	}
	public static float linearInterpolation(float index, short[] data){
		int bottom = data[(int)index];
		int top = data[((int)index)+1];
		float position = index- ((int)index);

		return bottom + ((top-bottom)*position);

	}


}
