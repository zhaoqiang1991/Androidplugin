package com.hm.iou.lifecycle.plugin;

/**
 * 自定义plugin进行参数传递
 */
class ReleaseInfoExtension {
    String versionName;
    String versionCode;
    String versionInfo;
    String fileName;

    ReleaseInfoExtension() {

    }

    public ReleaseInfoExtension(String versionName, String versionCode, String versionInfo, String fileName) {
        println "------ReleaseInfoExtension -------"
        this.versionName = versionName;
        this.versionCode = versionCode;
        this.versionInfo = versionInfo;
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "ReleaseInfoExtension{" +
                "versionName='" + versionName + '\'' +
                ", versionCode='" + versionCode + '\'' +
                ", versionInfo='" + versionInfo + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}

