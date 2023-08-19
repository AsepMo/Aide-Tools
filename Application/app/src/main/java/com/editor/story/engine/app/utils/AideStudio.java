package com.editor.story.engine.app.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageStats;
import android.content.pm.ResolveInfo;

import java.util.List;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import com.editor.story.AppController;

public class AideStudio {

    private static final String TAG = AideStudio.class.getSimpleName();
    private Context context;
    
    
    private AideStudio(Context context) {
        this.context = context;
        
    }

    public static AideStudio with(Context context) {
        return new AideStudio(context);
    }
    
    public boolean Aide_Is_Intalled() {
        return AppIsIntalled(AideGroup.AIDE_STUDIO);
    }

    public boolean AppIsIntalled(String packageName) {
        boolean exist = false;
        PackageManager pm = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> resolveInfoList = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resolveInfoList) {
            if (resolveInfo.activityInfo.packageName.equals(packageName)) {
                exist = true;
            }
        }
        return exist;
    }

    public Drawable AideIcon() {
        return AppIcon(AideGroup.AIDE_STUDIO);
    }
    
    public Drawable AppIcon(String packageName) {
        Drawable aide = null;
        PackageManager pm = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> resolveInfoList = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resolveInfoList) {
            if (resolveInfo.activityInfo.packageName.equals(packageName)) {
                aide = packageInfo.applicationInfo.loadIcon(pm);
            }
        }
        return aide;
    }
    
    public String AideName(){
        return AppName(AideGroup.AIDE_STUDIO);
    }
    
    public String AppName(String packageName) {
        String aide = "";
        PackageManager pm = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);

        } catch (NameNotFoundException e) {
            e.printStackTrace();
		}
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> resolveInfoList = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resolveInfoList) {
            if (resolveInfo.activityInfo.packageName.equals(packageName)) {
                aide = packageInfo.applicationInfo.loadLabel(pm).toString();
            }
        }
        return aide;
    }
    
    public String AidePackageName(){
        return PackageName(AideGroup.AIDE_STUDIO);
    }

    public String PackageName(String packageName) {
        String aide = "";
        PackageManager pm = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> resolveInfoList = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resolveInfoList) {
            if (resolveInfo.activityInfo.packageName.equals(packageName)) {
                aide = packageInfo.applicationInfo.packageName;
            }
        }
        return aide;
    }
    
    public String AideVersionName(){
        return getVersionName(AideGroup.AIDE_STUDIO);
    }

    public String getVersionName(String packageName) {
        String aide = "";
        PackageManager pm = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> resolveInfoList = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resolveInfoList) {
            if (resolveInfo.activityInfo.packageName.equals(packageName)) {
                aide = packageInfo.versionName;
            }
        }
        return aide;
    }
    
    public String AideVersionCode(){
        return getVersionCode(AideGroup.AIDE_STUDIO);
    }
    
    public String getVersionCode(String packageName) {
        String aide = "";
        PackageManager pm = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> resolveInfoList = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resolveInfoList) {
            if (resolveInfo.activityInfo.packageName.equals(packageName)) {
                aide = Integer.toString(packageInfo.versionCode);
            }
        }
        return aide;
    }
    
    public String AideLocation(){
        return getAppLocation(AideGroup.AIDE_STUDIO);
    }

    public String getAppLocation(String packageName) {
        String aide = "";
        PackageManager pm = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> resolveInfoList = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resolveInfoList) {
            if (resolveInfo.activityInfo.packageName.equals(packageName)) {
                aide = packageInfo.applicationInfo.sourceDir;
            }
        }
        return aide;
    }
    
    
    public String AideFirstInstall(){
        return Long.toString(getFirstInstaller(AideGroup.AIDE_STUDIO));
    }
    
    public long getFirstInstaller(String packageName) {
        long aide = 0;
        PackageManager pm = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> resolveInfoList = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resolveInfoList) {
            if (resolveInfo.activityInfo.packageName.equals(packageName)) {
                aide = packageInfo.firstInstallTime;
            }
        }
        return aide;
    }
}
