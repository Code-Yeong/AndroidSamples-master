package com.chivan.decoder;

/**
 * @author Chivan
 * @description:
 * @date : 2019-12-11 14:46
 */
public class Mp3Encoder {
    public native int init(String pcmPath,
                           int audioChannels,
                           int bitRate,
                           int sampleRate,
                           String mp3Path);

    public native void encode();

    public native void destroy();
}
