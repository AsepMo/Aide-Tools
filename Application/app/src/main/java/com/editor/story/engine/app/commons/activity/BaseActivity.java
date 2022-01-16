package com.editor.story.engine.app.commons.activity;

import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageStats;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.ArrayList;

import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import com.editor.story.R;
import com.editor.story.AppController;
import com.editor.story.engine.app.adapters.DrawerAdapter;
import com.editor.story.engine.app.models.DrawerItem;
import com.editor.story.engine.app.models.SimpleItem;
import com.editor.story.engine.app.models.SpaceItem;
import com.editor.story.engine.app.listeners.OnRequestHandlerListener;

public abstract class BaseActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {

	private ActionBar mActionBar;
	private Toolbar mToolbar;
    
    private static final int POS_DASHBOARD = 0;
    private static final int POS_ACCOUNT = 1;
    private static final int POS_MESSAGES = 2;
    private static final int POS_CART = 3;
    private static final int POS_LOGOUT = 5;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;
    
	public int SPLASH_TIME_OUT = 5000;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		
    }

    public void initActionBar(String title) {
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
			mActionBar.setTitle(title);
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

	public void initToolbar() {
		mToolbar = (Toolbar)findViewById(R.id.toolbar);
		if (mToolbar == null) {
			ActionBar mActionBar = getSupportActionBar();
			mActionBar.setTitle(null);
			setSupportActionBar(mToolbar);
		}
		final TextView mAppName = (TextView) findViewById(R.id.app_title);
		mAppName.setText(getString(R.string.app_name));
	}
	
	public void initToolbar(String title) {
		Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbar);
		if (mToolbar == null) {
		    mActionBar = getSupportActionBar();
			mActionBar.setTitle(null);
			setSupportActionBar(mToolbar);
		}
		final TextView mAppName = (TextView) findViewById(R.id.app_title);
		mAppName.setText(title);
	}

	public ActionBar getActionBarMe() {
		return mActionBar;
	}

	public Toolbar getToolbar() {
		return mToolbar;
	}
	
    public AppController getAppController() {
        return AppController.getAppController();
    }

	public void initToolbarMenu(Bundle savedInstanceState, String title){
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        if (mToolbar == null) {
            ActionBar mActionBar = getSupportActionBar();
            mActionBar.setTitle(null);
            setSupportActionBar(mToolbar);
        }
        final TextView mAppName = (TextView) findViewById(R.id.app_title);
		mAppName.setText(title);
        
        slidingRootNav = new SlidingRootNavBuilder(this)
            .withToolbarMenuToggle(mToolbar)
            .withMenuOpened(false)
            .withContentClickableWhenMenuOpened(false)
            .withSavedState(savedInstanceState)
            .withMenuLayout(R.layout.layout_menu_toolbar)
            .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                                                      createItemFor(POS_DASHBOARD).setChecked(true),
                                                      createItemFor(POS_ACCOUNT),
                                                      createItemFor(POS_MESSAGES),
                                                      createItemFor(POS_CART),
                                                      new SpaceItem(48),
                                                      createItemFor(POS_LOGOUT)));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(POS_DASHBOARD);
    }
    
	public void switchActivity(final AppCompatActivity activity, final String message, final Class<?> mClass) {
		new CountDownTimer(2000, 2000){
			@Override
			public void onTick(long l) {

			}

			@Override
			public void onFinish() {
				Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();       

				Intent mIntent = new Intent(activity, mClass);
				mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
				activity.startActivity(mIntent);
				activity.finish();
			}  
		}.start();
    }
    
    public void switchActivity(final AppCompatActivity activity, final TextView mMessage, final String message, final Class<?> mClass) {
        mMessage.setText(message);
        new CountDownTimer(2000, 2000){
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                //Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();       

                Intent mIntent = new Intent(activity, mClass);
                mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                activity.startActivity(mIntent);
                activity.finish();
            }  
        }.start();
    }

	public void switchFragment(Fragment fragment) {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.content_frame, fragment)
            .commit();
    }
    
    public void showToast(String message){
        Toast.makeText(AppController.getContext(), message, Toast.LENGTH_SHORT).show();       
    }
    
    
    public void setRequestHandler(final OnRequestHandlerListener mOnRequestHandlerListener){
        new CountDownTimer(2000, 2000){
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
               if(mOnRequestHandlerListener != null){
                   mOnRequestHandlerListener.onHandler();
               }
            }  
        }.start();
    }
    

    @Override
    public void onItemSelected(int position) {
        if (position == POS_LOGOUT) {
            finish();
        }  
    }

    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
            .withIconTint(color(R.color.textColorSecondary))
            .withTextTint(color(R.color.textColorPrimary))
            .withSelectedIconTint(color(R.color.colorAccent))
            .withSelectedTextTint(color(R.color.colorAccent));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }
    
    public void onHomeItemClick(MenuItem item) {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onHomeItemClick(item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
