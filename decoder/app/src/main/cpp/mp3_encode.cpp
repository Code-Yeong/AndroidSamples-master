//
// Created by yeongguo(郭青云) on 2019-12-11.
//
#include "mp3_encode.h"

extern "C"

Mp3Encoder::Mp3Encoder() {

}

Mp3Encoder::~Mp3Encoder() {

}

int Mp3Encoder::lint(const char *pcmFilePath,
                     const char *mp3FilePath,
                     int sampleRate,
                     int channels,
                     int bitRate) {
    int ret = 1;
    const int quality = 5;
    pcmFIle = fopen(pcmFilePath, "rb");
    if (pcmFIle) {
        mp3File = fopen(mp3FilePath, "wb");
        if (mp3File) {
            //初始化lame相关参数，输入/输出采样率、音频声道数、码率
            lameClient = lame_init();
            lame_set_in_samplerate(lameClient, sampleRate);
            lame_set_out_samplerate(lameClient, sampleRate);
            lame_set_num_channels(lameClient, channels);
            lame_set_brate(lameClient, bitRate);
            lame_set_quality(lameClient, quality);

            lame_init_params(lameClient);
            ret = 0;
        }
    }
    return ret;
}

void Mp3Encoder::Encode() {
    int bufferSize = 1024 * 256;
    short *buffer = new short[bufferSize / 2];
    short *leftBuffer = new short[bufferSize / 2];
    short *rightBuffer = new short[bufferSize / 2];
    unsigned char* mp3_buffer = new unsigned char[bufferSize];
    size_t readBufferSize = 0;
    while ((readBufferSize = fread(buffer, 2, bufferSize / 2, pcmFIle)) > 0) {
        for (int i = 0; i < readBufferSize; i++) {
                            leftBuffer[i] = buffer[i];
//            if (i % 2 == 0) {
//                leftBuffer[i / 2] = buffer[i];
//            } else {
//                rightBuffer[i / 2] = buffer[i];
//            }
        }
        size_t wroteSize = lame_encode_buffer(lameClient, (short int *) leftBuffer, (short int *) leftBuffer, (int)(readBufferSize / 2), mp3_buffer, bufferSize);
        fwrite(mp3_buffer, 1, wroteSize, mp3File);
    }

    delete [] buffer;
    delete [] leftBuffer;
    delete [] rightBuffer;
    delete [] mp3_buffer;
}

void Mp3Encoder::Destory() {
    if (pcmFIle) {
        fclose(pcmFIle);
    }
    if (mp3File) {
        fclose(mp3File);
        lame_close(lameClient);
    }
}
