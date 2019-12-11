#include <jni.h>
#include <string>
#include "lame/lame.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_chivan_decoder_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
//    return env->NewStringUTF(hello.c_str());
    return env->NewStringUTF(get_lame_version());
}
