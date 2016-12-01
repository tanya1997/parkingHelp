package com.project.empyrean.parkinghelp;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.widget.FrameLayout;


public class AnimationFragment extends DialogFragment implements View.OnClickListener {
    WebView animationView;
    FrameLayout animateDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_animation, null);
        animateDialog = (FrameLayout)v.findViewById(R.id.animateDialog);
        animationView = (WebView)v.findViewById(R.id.animationView);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setAnimation(getArguments().getString("url"));
        return v;
    }

    private void setAnimation(String url){
        parameterSettingAnimationView();
        animationView.loadUrl(url);

    }
    private void parameterSettingAnimationView(){

        Display display =getDialog().getWindow().getWindowManager().getDefaultDisplay();
        animateDialog.setMinimumWidth(display.getWidth());
        animateDialog.setMinimumHeight((display.getWidth()));

        animationView.getLayoutParams().width = FrameLayout.LayoutParams.MATCH_PARENT;
        animationView.getLayoutParams().height = display.getWidth();

        animationView.getSettings().setJavaScriptEnabled(true);
        animationView.getSettings().setLoadWithOverviewMode(true);

        animationView.setVerticalScrollBarEnabled(false);
        animationView.setHorizontalScrollBarEnabled(false);

        animationView.getSettings().setUseWideViewPort(false);
        animationView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        animationView.getSettings().setSupportMultipleWindows(true);
    }

    @Override
    public void onClick(View view) {
         dismiss();
    }


}
