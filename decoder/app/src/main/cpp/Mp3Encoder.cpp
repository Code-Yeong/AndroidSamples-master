//
// Created by yeongguo(郭青云) on 2019-12-11.
//

#include "com_chivan_decoder_Mp3Encoder.h"
#include "mp3_encode.h"

Mp3Encoder *encoder = NULL;

JNIEXPORT jint JNICALL Java_com_chivan_decoder_Mp3Encoder_init
        (JNIEnv *env,
         jobject jobj,
         jstring pcmPathParam,
         jint audioChannelsParam,
         jint bitRateParam,
         jint sampleRateParam,
         jstring mp3PahtParam){
    const char* pcmPath = env->GetStringUTFChars(pcmPathParam,NULL);
    const char* mp3Path = env->GetStringUTFChars(mp3PahtParam,NULL);
    encoder = new Mp3Encoder();
    int ret = encoder->lint(pcmPath,
                            mp3Path,
                            sampleRateParam,
                            audioChannelsParam,
                            bitRateParam);
    env->ReleaseStringUTFChars(mp3PahtParam, mp3Path);
    env->ReleaseStringUTFChars(pcmPathParam, pcmPath);
    return ret;
}

JNIEXPORT void JNICALL Java_com_chivan_decoder_Mp3Encoder_encode
(JNIEnv *, jobject){
encoder->Encode();
}

JNIEXPORT void JNICALL Java_com_chivan_decoder_Mp3Encoder_destroy
(JNIEnv *, jobject){
encoder->Destory();
}