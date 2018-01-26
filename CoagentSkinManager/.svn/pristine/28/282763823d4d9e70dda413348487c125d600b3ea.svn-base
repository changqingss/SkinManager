LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)
    
LOCAL_MODULE_TAGS := optional
LOCAL_JAVA_LIBRARIES := CoagentProxy
LOCAL_SRC_FILES := $(call all-java-files-under, src)

LOCAL_MODULE := CoagentSkinManager
include $(BUILD_JAVA_LIBRARY)

#MAKE_XML
PRODUCT_COPY_FILES += \
    $(LOCAL_PATH)/CoagentSkinManagerPermission.xml:system/etc/permissions/CoagentSkinManagerPermission.xml
