package com.playMidi.SoundFont.soundFontInputStream;

import android.util.Log;

import com.playMidi.SoundFont.soundFontInputStream.metaData.InstrumentGenModIndcies;
import com.playMidi.SoundFont.soundFontInputStream.metaData.InstrumentGenerator;
import com.playMidi.SoundFont.soundFontInputStream.metaData.InstrumentModulator;
import com.playMidi.SoundFont.soundFontInputStream.metaData.PresetHeader;
import com.playMidi.SoundFont.soundFontInputStream.metaData.PresetZoneGenerator;
import com.playMidi.SoundFont.soundFontInputStream.metaData.PresetZoneIndex;
import com.playMidi.SoundFont.soundFontInputStream.metaData.PresetZoneModulator;
import com.playMidi.SoundFont.soundFontInputStream.metaData.SampleHeaders;

import java.util.ArrayList;

/**
 * Created by ra on 5/5/2017.
 */


public class SampleMetas {

    public ArrayList<PresetHeader> presets;
    public ArrayList<PresetZoneIndex> presetZones;
    public ArrayList<PresetZoneModulator> zoneModulators;
    public ArrayList<PresetZoneGenerator> zoneGenerators;
    /**
     * a list of names for the instruments
     */
    public ArrayList<String> instrumentNames;
    /**
     * the zone ATTRIBUTE_index indicies for each {@link #instrumentNames}
     * the indexes coorespond to the {@link #zoneGenerators} and {@link #zoneModulators} and {@link #presetZones}
     *<br>
     * The WORD wInstBagNdx is an ATTRIBUTE_index to the instrument's zone list in the IBAG sub-chunk(
     * {@link #allInstrumentZones}
     * ). Because the instrument zone list is in the same order as the instrument list, the instrument bag indices will be monotonically increasing with increasing instruments.
     * The size of the IBAG sub-chunk in bytes will be four greater than four times the terminal (EOI) instrument's wInstBagNdx.
     * If the instrument bag indices are non-monotonic or if the terminal instrument's wInstBagNdx does not match the IBAG sub-chunksize, the file is structurally defective and should be rejected at load time.
     * All instruments except the terminal instrument must have at least one zone; any preset with no zones should be ignored.

     The terminal sfInst record should never be accessed, and exists only to provide a terminal wInstBagNdx with which to determine the number of zones in the last instrument. All other values are conventionally zero, with the exception of achInstName, which should be "EOI" indicating end of instruments.
     *
     */
    public ArrayList<Integer> instrumentZonIndices;
    public ArrayList<InstrumentGenModIndcies> allInstrumentZones;
    public ArrayList<InstrumentModulator> allInstrumentZoneModulators;
    public ArrayList<InstrumentGenerator> zoneGeneratorsPerZone;
    /**
     * as specified in:RIFF_SFBK_ptda_shdr
     */
    public ArrayList<SampleHeaders> sampleHeadersList;


    //---------------------------------------------------------------------



    public int getNumberOfInstruments(){
        return instrumentNames.size()-1;
    }
    private ArrayList<PresetZoneModulator> getInstrumentZoneModulator(int instrumentIndex){
        if(instrumentIndex>= getNumberOfInstruments()){ throw new IndexOutOfBoundsException(); }
        ArrayList<PresetZoneModulator> retu = new ArrayList<>();
        int startIndex = presetZones.get(instrumentIndex).modulatorIndex;
        int endIndex = presetZones.get(instrumentIndex+1).modulatorIndex;
        for(int i = startIndex; i<endIndex; i++){
            retu.add(zoneModulators.get(i));
        }
        return retu;
    }
    private ArrayList<PresetZoneGenerator> getInstrumentZoneGenerators(int instrumentIndex){
        if(instrumentIndex>= getNumberOfInstruments()){ throw new IndexOutOfBoundsException(); }
        ArrayList<PresetZoneGenerator> retu = new ArrayList<>();
        int startIndex = presetZones.get(instrumentIndex).generatorIndex;
        int endIndex = presetZones.get(instrumentIndex+1).generatorIndex;
        for(int i = startIndex; i<endIndex; i++){
            retu.add(zoneGenerators.get(i));
        }
        return retu;
    }
    private ArrayList<InstrumentGenModIndcies> getInstrumentGeneratroModulatorIndicies(int instrumentIndex){
        if(instrumentIndex>= getNumberOfInstruments()){ throw new IndexOutOfBoundsException(); }
        ArrayList<InstrumentGenModIndcies> retu = new ArrayList<>();
        int startIndex = instrumentZonIndices.get(instrumentIndex);
        int endIndex = instrumentZonIndices.get(instrumentIndex+1);
        for(int i = startIndex; i<endIndex; i++){
            retu.add(allInstrumentZones.get(i));
        }
        return retu;
    }
    private int getNuberOfSamplesForInstrument(int instrumentIndex){
        if(instrumentIndex>=getNumberOfInstruments()){
            throw new IndexOutOfBoundsException(instrumentIndex+">"+getNumberOfInstruments());
        }
        return instrumentZonIndices.get(instrumentIndex+1)- instrumentZonIndices.get(instrumentIndex);
    }
    public String toString(){
        String retu = "";
        retu += "PresetHeaders(PHDR):["+presets.size()+"]:"+presets.toString();
        retu+="\npresetZones:["+presetZones.size()+"]"+presetZones.toString();
        retu+="\nzoneModulators["+zoneModulators.size()+"]:"+zoneModulators.toString();
        retu+="\nzone generators["+zoneGenerators.size()+"]:"+zoneGenerators.toString();
        retu+="\ninstrument names["+instrumentNames.size()+"]:"+instrumentNames.toString();
        retu+="\ninstrument zone indicies["+instrumentZonIndices.size()+"]:"+instrumentZonIndices.toString();
        retu+="\nall instrument zones["+allInstrumentZones.size()+"]:"+allInstrumentZones.toString();
        retu+="\nzoneGeneratorsPerZone["+zoneGeneratorsPerZone.size()+"]:"+zoneGeneratorsPerZone.toString();
        retu+="\nsampleHeaders["+sampleHeadersList.size()+"]:"+sampleHeadersList.toString();
        return retu;
    }
    public void LogValues(){
       Log.i("Presets(PHDR)["+presets.size()+"]",presets.toString());
        Log.i("presetZones(PBAG):["+presetZones.size()+"]",presetZones.toString());
        Log.i("zoneModulators(PMOD)["+zoneModulators.size()+"]",zoneModulators.toString());
        Log.i("zone generators(PGEN)["+zoneGenerators.size()+"]",zoneGenerators.toString());
        Log.i("instrument names(INST.name)["+instrumentNames.size()+"]",instrumentNames.toString());
        Log.i("instrument zone indicies(INST.index):["+instrumentZonIndices.size()+"]",instrumentZonIndices.toString());
            Log.i("all instrument zones(IBAG)["+allInstrumentZones.size()+"]","   "+allInstrumentZones.toString());
        Log.i("allInstrumentMod(IMOD):["+allInstrumentZoneModulators.size()+"]",allInstrumentZoneModulators.toString());
        Log.i("zoneGeneratorsPerZone(IGEN):["+zoneGeneratorsPerZone.size()+"]",zoneGeneratorsPerZone.toString());
        Log.i("sampleHeadersList["+sampleHeadersList.size()+"]",sampleHeadersList.toString());

    }
}
