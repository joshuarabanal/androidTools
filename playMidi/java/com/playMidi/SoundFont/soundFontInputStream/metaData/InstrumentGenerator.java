package com.playMidi.SoundFont.soundFontInputStream.metaData;

import com.playMidi.AudioTools.wavFormat.PcmHelpers;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by ra on 5/5/2017.
 */

public class InstrumentGenerator {
    //public int sfGenOper_type;
    //public boolean sfGenOper_polarity;
    //public boolean sfGenOper_direction;
    //public int sfGenOper_midiContinuousController;
    //public int sfGenOper_index;
    public int genAmount_byLO;
    public int genAmount_byHI;
    public String sfGenOper;

    @Override
    public String toString() {
        return "\n{"
                +",sfGenOper:"+sfGenOper
                +",genAmount_byLO:"+genAmount_byLO
                +",genAmount_byHI:"+genAmount_byHI
                +"}";
    }
    public int generatorOperatorToInt() throws UnsupportedEncodingException {
        switch(sfGenOper){
            case "startAddrsOffset":return 0;       case "endAddrsOffset":return 1;
            case "startloopAddrsOffset":return 2;   case "endloopAddrsOffset":return 3;
            case "startAddrsCoarseOffset":return 4; case "modLfoToPitch":return 5;
            case "vibLfoToPitch":return 6;          case "modEnvToPitch":return 7;
            case "initialFilterFc":return 8;        case "initialFilterQ":return 9;
            case "modLfoToFilterFc":return 10;      case "modEnvToFilterFc":return 11;
            case "endAddrsCoarseOffset":return 12;  case "modLfoToVolume":return 13;
            case "unused1":return 14;               case "chorusEffectsSend":return 15;
            case "reverbEffectsSend":return 16;     case "pan":return 17;
            case "unused2":return 18;               case "unused3":return 19;
            case "unused4":return 20;               case "delayModLFO":return 21;
            case "freqModLFO":return 22;            case "delayVibLFO":return 23;
            case "freqVibLFO":return 24;            case "delayModEnv":return 25;
            case "attackModEnv":return 26;          case "holdModEnv":return 27;
            case "decayModEnv":return 28;           case "sustainModEnv":return 29;
            case "releaseModEnv":return 30;         case "keynumToModEnvHold":return 31;
            case "keynumToModEnvDecay":return 32;   case "delayVolEnv":return 33;
            case "attackVolEnv":return 34;          case "holdVolEnv":return 35;
            case "decayVolEnv":return 36;           case "sustainVolEnv":return 37;
            case "releaseVolEnv":return 38;         case "keynumToVolEnvHold":return 39;
            case "keynumToVolEnvDecay":return 40;   case "instrument":return 41;
            case "reserved1":return 42;             case "keyRange":return 43;
            case "velRange":return 44;              case "startloopAddrsCoarseOffset":return 45;
            case "keynum":return 46;                case "velocity":return 47;
            case "initialAttenuation":return 48;    case "reserved2":return 49;
            case "endloopAddrsCoarseOffset":return 50;case "coarseTune":return 51;
            case "fineTune":return 52;              case "sampleID":return 53;
            case "sampleModes":return 54;           case "reserved3":return 55;
            case "scaleTuning":return 56;           case "exclusiveClass":return 57;
            case "overridingRootKey":return 58;     case "unused5":return 59;
            case "endOper":return 60;               default : throw new UnsupportedEncodingException("cannot parse:"+sfGenOper);
        }
    }
    private static String generatorOperatorToString(int oper) throws IOException {
        switch(oper){
            case 0: return "startAddrsOffset";       case 1: return "endAddrsOffset" ;
            case 2: return "startloopAddrsOffset" ;   case 3: return "endloopAddrsOffset" ;
            case 4: return "startAddrsCoarseOffset" ; case 5: return "modLfoToPitch" ;
            case 6: return "vibLfoToPitch" ;          case 7: return "modEnvToPitch" ;
            case 8: return "initialFilterFc" ;        case 9: return "initialFilterQ";
            case 10: return "modLfoToFilterFc";      case 11: return "modEnvToFilterFc";
            case 12: return "endAddrsCoarseOffset";  case 13: return "modLfoToVolume";
            case 14: return "unused1";               case 15: return "chorusEffectsSend";
            case 16: return "reverbEffectsSend";     case 17: return "pan";
            case 18: return "unused2";               case 19: return "unused3";
            case 20: return "unused4";               case 21: return "delayModLFO";
            case 22: return "freqModLFO";            case 23: return "delayVibLFO";
            case 24: return "freqVibLFO";            case 25: return "delayModEnv";
            case 26: return "attackModEnv";          case 27: return "holdModEnv";
            case 28: return "decayModEnv";           case 29: return "sustainModEnv";
            case 30: return "releaseModEnv";         case 31: return "keynumToModEnvHold";
            case 32: return "keynumToModEnvDecay";   case 33: return "delayVolEnv";
            case 34: return "attackVolEnv";          case 35: return "holdVolEnv";
            case 36: return "decayVolEnv";           case 37: return "sustainVolEnv";
            case 38: return "releaseVolEnv";         case 39: return "keynumToVolEnvHold";
            case 40: return "keynumToVolEnvDecay";   case 41: return "instrument";
            case 42: return "reserved1";             case 43: return "keyRange";
            case 44: return "velRange";              case 45: return "startloopAddrsCoarseOffset";
            case 46: return "keynum";                case 47: return "velocity";
            case 48: return "initialAttenuation";    case 49: return "reserved2";
            case 50: return "endloopAddrsCoarseOffset";case 51: return "coarseTune";
            case 52: return "fineTune";              case 53: return "sampleID";
            case 54: return "sampleModes";           case 55: return "reserved3";
            case 56: return "scaleTuning";           case 57: return "exclusiveClass";
            case 58: return "overridingRootKey";     case 59: return "unused5";
            case 60: return "endOper";               default: return "unknown:"+oper;
        }
    }
}
