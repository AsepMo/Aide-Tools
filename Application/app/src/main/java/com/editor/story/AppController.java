package com.editor.story;

import android.support.v7.app.AppCompatDelegate;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class AppController extends Application {
    private static AppController sAppController;
    private static volatile Context mContext;
    
	private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
    }
    
	@Override
	public void onCreate() {
		super.onCreate();
        sAppController = this;
        mContext = this;
        
        this.uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread thread, Throwable ex) {
                    Intent intent = new Intent(getApplicationContext(), DebugActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("error", getStackTrace(ex));
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 11111, intent, PendingIntent.FLAG_ONE_SHOT);
                    AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                    am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 1000, pendingIntent);
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(2);
                    uncaughtExceptionHandler.uncaughtException(thread, ex);
                }
            });
	}
    
    public static AppController getAppController() {
        return sAppController;
    }
    
    public static synchronized Context getContext(){
        return mContext;
    }
    
	private String getStackTrace(Throwable th){
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		Throwable cause = th;
		while(cause != null){
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		final String stacktraceAsString = result.toString();
		printWriter.close();
		return stacktraceAsString;
	}
}
