package com.editor.story.engine.app.models;

import android.os.Parcel;
import android.os.Parcelable;

public class AideTools implements Parcelable {

    public static final Creator<AideTools> CREATOR = new Creator<AideTools>() {
        @Override
        public AideTools createFromParcel(Parcel in) {
            return new AideTools(in);
        }

        @Override
        public AideTools[] newArray(int size) {
            return new AideTools[size];
        }
    };

    private String appName;

    private long firstInstallTime;

    private long lastUpdateTime;

    private String packageName;

    private int versionCode = 0;

    private String versionName;

    public AideTools() {
        versionName = "0.0";
        versionCode = 0;
        firstInstallTime = 0;
        lastUpdateTime = 0;
    }

    protected AideTools(Parcel in) {
        appName = in.readString();
        packageName = in.readString();
        versionName = in.readString();
        versionCode = in.readInt();
        firstInstallTime = in.readLong();
        lastUpdateTime = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(appName);
        parcel.writeString(packageName);
        parcel.writeString(versionName);
        parcel.writeInt(versionCode);
        parcel.writeLong(firstInstallTime);
        parcel.writeLong(lastUpdateTime);
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public long getFirstInstallTime() {
        return firstInstallTime;
    }

    public void setFirstInstallTime(long firstInstallTime) {
        this.firstInstallTime = firstInstallTime;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    @Override
    public String toString() {
        super.toString();
        return "AppName : "
                + appName
                + " | PackageName :"
                + packageName
                + "\nVersion :"
                + versionName
                + " | VersionCode :"
                + versionCode;
    }
}

