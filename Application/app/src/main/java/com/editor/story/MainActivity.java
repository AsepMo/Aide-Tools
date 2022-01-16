package com.editor.story;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.widget.TextView;

import com.editor.story.engine.app.commons.activity.BaseActivity;
import com.editor.story.engine.app.fragments.AideToolFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

	    initToolbarMenu(savedInstanceState, getString(R.string.app_name));
        switchFragment(AideToolFragment.newInstance());
    }

}
/*don't forget to subscribe my YouTube channel for more Tutorial and mod*/
/*
https://youtube.com/channel/UC_lCMHEhEOFYgJL6fg1ZzQA */
