package com.editor.story.engine.app.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import com.editor.story.R;
import com.editor.story.engine.app.models.AideTools;
import com.editor.story.engine.app.utils.PackageHunter;

public class AideToolAdapter extends ArrayAdapter<AideTools> {
    
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<AideTools> mAideTools;
    // Declare Variables
    /**
     * Internal Views
     */
    private View mLineView;
    private TextView mName, mPackageName, mVersionName, mVersionCode, mFirstTime;
    private LinearLayout mPointFrame;
    private LinearLayout mRightContainer;
    private LinearLayout mLinearLayout;
    private ImageView mDoneIconView;
	private View mMarginBottomView;
    private final PackageHunter packageHunter;
    
    public AideToolAdapter(Context context, ArrayList<AideTools> mAideTools)
    {
        super(context, R.layout.item_aide_tools, mAideTools);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mAideTools = mAideTools;    
        packageHunter = new PackageHunter(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        
        if (convertView == null)
        {
            convertView = this.mInflater.inflate(R.layout.item_aide_tools, parent, false);
        }
		 mLineView = convertView.findViewById(R.id.stepper_line);
         mName = (TextView)convertView.findViewById(R.id.stepper_title);
         mPackageName = (TextView)convertView.findViewById(R.id.stepper_summary);
         mVersionName = (TextView)convertView.findViewById(R.id.stepper_summary_1);
         mVersionCode = (TextView)convertView.findViewById(R.id.stepper_summary_2);
         mFirstTime = (TextView)convertView.findViewById(R.id.stepper_summary_3);
        
         mPointFrame = (LinearLayout)convertView.findViewById(R.id.stepper_point_frame);
         mRightContainer = (LinearLayout)convertView.findViewById(R.id.stepper_right_layout);
         mLinearLayout = (LinearLayout)convertView.findViewById(R.id.stepper_custom_view);
        
         mDoneIconView = (ImageView)convertView.findViewById(R.id.stepper_done_icon);
         mMarginBottomView = convertView.findViewById(R.id.stepper_margin_bottom);

        // Set title top margin
        mName.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    int singleLineHeight = mName.getMeasuredHeight();
                    int topMargin = (mPointFrame.getMeasuredHeight() - singleLineHeight) / 2;
                    // Only update top margin when it is positive, preventing titles being truncated.
                    if (topMargin > 0) {
                        ViewGroup.MarginLayoutParams mlp = (MarginLayoutParams) mName.getLayoutParams();
                        mlp.topMargin = topMargin;
                    }
                }
            });
        Drawable icon = packageHunter.getIconForPkg(mAideTools.get(position).getPackageName());
        // Capture position and set to the ImageView
        mDoneIconView.setImageDrawable(icon);
        mName.setText(mAideTools.get(position).getAppName());
        mPackageName.setText(mAideTools.get(position).getPackageName());
		//mVersionName.setText(mAideTools.get(position).getVersionName());
		//mVersionCode.setText(mAideTools.get(position).getVersionCode());
		//mFirstTime.setText(Long.toString(mAideTools.get(position).getFirstInstallTime()));
		
        return convertView;
    }
}
