LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := hdt

LOCAL_SRC_FILES := libhdt-jni.so

include $(PREBUILT_SHARED_LIBRARY)
