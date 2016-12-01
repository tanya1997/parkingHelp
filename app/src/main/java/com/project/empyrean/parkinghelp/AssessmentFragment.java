package com.project.empyrean.parkinghelp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;

public class AssessmentFragment extends DialogFragment implements RatingBar.OnRatingBarChangeListener, View.OnClickListener {
    RatingBar ratingBar;
    Button estimateBtn;
    Button notNowBtn;

    float evaluation;

    feedback openFeedback;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            openFeedback = (feedback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.assessment_fragment, null);
        Initialisation(v);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return v;
    }

    private void  Initialisation(View v){

        ratingBar = (RatingBar)v.findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(this);
        estimateBtn = (Button)v.findViewById(R.id.estimateBtn);
        notNowBtn = (Button)v.findViewById(R.id.notNowBtn);
        estimateBtn.setOnClickListener(this);
        notNowBtn.setOnClickListener(this);
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
        evaluation = v;
    }
    private void  rateAppStore(){
        final String appPackageName = getContext().getPackageName(); //
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }
    private void startWebViewActivity(){
        openFeedback.OpenFeedback();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.estimateBtn)
            assessmentAnalysis();
        else
            dismiss();
    }

    private void assessmentAnalysis(){
        if (evaluation>=3.5)
            rateAppStore();
        else
            startWebViewActivity();
        dismiss();
    }
}
