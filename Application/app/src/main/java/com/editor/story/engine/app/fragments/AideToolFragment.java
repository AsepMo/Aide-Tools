package com.editor.story.engine.app.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageStats;
import android.os.Bundle;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.editor.story.R;
import com.editor.story.engine.app.commons.fragments.BaseFragment;
import com.editor.story.engine.app.adapters.AideToolAdapter;
import com.editor.story.engine.app.models.AideTools;
import com.editor.story.engine.app.utils.PackageHunter;
import com.editor.story.engine.app.utils.HtmlBuilder;
import com.editor.story.engine.app.utils.AideStudio;
import com.editor.story.engine.app.utils.AideGroup;

public class AideToolFragment extends BaseFragment {

    private Activity mActivity;
    private Context mContext;
    private ImageView mIcon;
    private TextView mName;
    private TextView mPackageName;
    private TextView mVersionName;
    private TextView mVersionCode;
    private TextView mFirstTime;
    private ListView mListView;

    private AideToolAdapter mAideToolAdapter;
    private ArrayList<AideTools> mAideTools;
    private PackageHunter mPackage;
    private AideStudio aideStudio;

    public static AideToolFragment newInstance() {
        AideToolFragment fragment = new AideToolFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_aide_tools, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity = (Activity)getActivity();
        mContext = getActivity();
        mPackage = new PackageHunter(mContext);
        
        mIcon = (ImageView)view.findViewById(R.id.imgvw_icn);          
        mName = (TextView)view.findViewById(R.id.txtvw_pkgname);
        mPackageName = (TextView)view.findViewById(R.id.txtvw_vname);
        mVersionName = (TextView)view.findViewById(R.id.txtvw_firsttime);
        mVersionCode = (TextView)view.findViewById(R.id.txtvw_vc);
        mFirstTime = (TextView)view.findViewById(R.id.txtvw_lastupdated);

        mListView = (ListView)view.findViewById(R.id.list);

        mAideTools = new ArrayList<AideTools>();
        mAideToolAdapter = new AideToolAdapter(mContext, mAideTools);
        mListView.setAdapter(mAideToolAdapter);
        aideStudio = AideStudio.with(mContext);
        if(aideStudio.Aide_Is_Intalled()){  
            mIcon.setImageDrawable(aideStudio.AideIcon());
            mName.setText("Name : " + aideStudio.AideName());
            mPackageName.setText("PackageName : "+ aideStudio.AidePackageName());
            mVersionName.setText("VersionName : " + aideStudio.AideVersionName());
            mVersionCode.setText("VersionCode : "+ aideStudio.AideVersionCode());
            File appFile = new File(aideStudio.AideLocation());
            mFirstTime.setText("FirstInstall Time : " + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(appFile.lastModified())).toString());
            AideTools aide = new AideTools();
            mAideTools.add(aide);
        }
    }

    
    private Spanned aideName() {
        HtmlBuilder html = new HtmlBuilder();
        html.h3().font(0xFFCAE682, "Name")
            .append(' ')
            .font(0xFFCAE682, ":")
            .append(' ')
            .font(0xFF33B5E5, aideStudio.AideName())
            .append(' ');
            return html.build();
    }
    
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
