package skadistats.clarity.decoder.unpacker.factory.s2;

import skadistats.clarity.decoder.s2.field.DecoderProperties;
import skadistats.clarity.decoder.unpacker.FloatCoordDecoder;
import skadistats.clarity.decoder.unpacker.FloatNoScaleDecoder;
import skadistats.clarity.decoder.unpacker.FloatQuantizedDecoder;
import skadistats.clarity.decoder.unpacker.FloatSimulationTimeDecoder;
import skadistats.clarity.decoder.unpacker.Decoder;

public class FloatDecoderFactory implements DecoderFactory<Float> {

    @Override
    public Decoder<Float> createDecoder(DecoderProperties f) {
        return createDecoderStatic(f);
    }

    public static Decoder<Float> createDecoderStatic(DecoderProperties f) {
        if ("coord".equals(f.getEncoderType())) {
            return new FloatCoordDecoder();
        }
        if ("simulationtime".equals(f.getEncoderType())) {
            return new FloatSimulationTimeDecoder();
        }
        int bc = f.getBitCountOrDefault(0);
        if (bc <= 0 || bc >= 32) {
            return new FloatNoScaleDecoder();
        }
        // TODO: get real name
        return new FloatQuantizedDecoder("N/A", bc, f.getEncodeFlagsOrDefault(0) & 0xF, f.getLowValueOrDefault(0.0f), f.getHighValueOrDefault(1.0f));
    }

}
