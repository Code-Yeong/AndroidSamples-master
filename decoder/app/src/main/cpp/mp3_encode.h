//
// Created by yeongguo(郭青云) on 2019-12-11.
//

#ifndef DECODER_MP3_ENCODE_H
#define DECODER_MP3_ENCODE_H

#endif //DECODER_MP3_ENCODE_H
#include <stdio.h>
#include "lame/lame.h"

#ifndef MYAPPLICATION_MP3_ENCODER_H
#define MYAPPLICATION_MP3_ENCODER_H
#ifdef __cplusplus
extern "C" {
#endif

class Mp3Encoder {
private:
    FILE *pcmFIle;
    FILE *mp3File;
    lame_t lameClient;

public:
    Mp3Encoder();

    ~Mp3Encoder();

    int lint(const char *pcmFilePath,
             const char *mp3FilePath,
             int sampleRate,
             int channels,
             int bitRate);

    void Encode();

    void Destory();
};

#ifdef __cplusplus
}
#endif
#endif