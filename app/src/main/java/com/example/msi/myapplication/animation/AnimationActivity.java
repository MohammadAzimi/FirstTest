package com.example.msi.myapplication.animation;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.msi.myapplication.R;

public class AnimationActivity extends AppCompatActivity {
    private static final String TAG = "AnimationActivity";
    public static final String EXTRA_KEY_ANIMATION_TYPE = "animation_type";
    private int animationType = 0;
    public static final int TYPE_ALPHA = 0;
    public static final int TYPE_TRANSLATE = 1;
    public static final int TYPE_SCALE = 2;
    public static final int TYPE_ROTATE = 3;
    public static final int TYPE_VALUE_ANIMATOR = 4;
    public static final int TYPE_ANIMATION_SET = 5;

    private ImageView kouroshImage;
    private SwitchCompat loadFromXmlSwitch;
    private boolean mustLoadFromXml = false;

    private Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        animationType = getIntent().getIntExtra(EXTRA_KEY_ANIMATION_TYPE, TYPE_ALPHA);
        typeface = Typeface.createFromAsset(getAssets(), "fonts/koodak.TTF");
        Log.i(TAG, "Animation Type Selected: " + animationType);
        Button startButton = (Button) findViewById(R.id.button_start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAnimation();
            }
        });

        kouroshImage = (ImageView) findViewById(R.id.image_kourosh);
        loadFromXmlSwitch = (SwitchCompat) findViewById(R.id.switch_load_from_xml);
        loadFromXmlSwitch.setTypeface(typeface);
        loadFromXmlSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mustLoadFromXml = b;
            }
        });
    }

    private void showAnimation() {
        switch (animationType) {
            case TYPE_ALPHA:
                showAlphaAnimation();
                break;
            case TYPE_TRANSLATE:
                showTranslateAnimation();
                break;
            case TYPE_SCALE:
                showScaleAnimation();
                break;
            case TYPE_ROTATE:
                showRotateAnimation();
                break;
            case TYPE_VALUE_ANIMATOR:
                showValueAnimation();
                break;
            case TYPE_ANIMATION_SET:
                showAnimationSet();
                break;
        }
    }

    private void showAlphaAnimation() {
        if (mustLoadFromXml) {
            AlphaAnimation alphaAnimation = (AlphaAnimation) AnimationUtils.loadAnimation(this, R.anim.sample_alpha);
            alphaAnimation.setDuration(500);
            alphaAnimation.setRepeatCount(Animation.INFINITE);
            alphaAnimation.setRepeatMode(Animation.REVERSE);
            kouroshImage.startAnimation(alphaAnimation);
        } else {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(1000);
            alphaAnimation.setFillAfter(false);
            kouroshImage.startAnimation(alphaAnimation);
        }
    }

    private void showTranslateAnimation() {
        if (mustLoadFromXml) {
            TranslateAnimation translateAnimation = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.sample_translation);
            translateAnimation.setDuration(500);
            translateAnimation.setRepeatCount(Animation.INFINITE);
            translateAnimation.setRepeatMode(Animation.REVERSE);
            translateAnimation.setInterpolator(new BounceInterpolator());
            kouroshImage.startAnimation(translateAnimation);
        } else {
            TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, 500);
            translateAnimation.setDuration(1000);
            translateAnimation.setFillAfter(true);
            translateAnimation.setInterpolator(new DecelerateInterpolator());
            kouroshImage.startAnimation(translateAnimation);
        }
    }

    private void showScaleAnimation() {
        if (mustLoadFromXml) {
            ScaleAnimation scaleAnimation = (ScaleAnimation) AnimationUtils.loadAnimation(this, R.anim.sample_scale);
            scaleAnimation.setDuration(1000);
            scaleAnimation.setRepeatCount(Animation.INFINITE);
            scaleAnimation.setRepeatMode(Animation.REVERSE);
            kouroshImage.startAnimation(scaleAnimation);
        } else {
            ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnimation.setFillAfter(true);
            scaleAnimation.setDuration(1000);
            scaleAnimation.setInterpolator(new AccelerateInterpolator());
            kouroshImage.startAnimation(scaleAnimation);
        }
    }

    private void showRotateAnimation() {
        if (mustLoadFromXml) {
            RotateAnimation rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(this, R.anim.sample_rotate);
            rotateAnimation.setDuration(2000);
            rotateAnimation.setRepeatCount(1);
            rotateAnimation.setRepeatMode(Animation.REVERSE);
            rotateAnimation.setInterpolator(new AccelerateInterpolator());
            kouroshImage.startAnimation(rotateAnimation);
        } else {
            RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setDuration(1000);
            kouroshImage.startAnimation(rotateAnimation);
        }
    }

    private void showValueAnimation() {
        final FrameLayout frameLayout = findViewById(R.id.frame_root);
        final ValueAnimator valueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(),
                ContextCompat.getColor(this, R.color.colorPrimary), ContextCompat.getColor(this, R.color.colorPrimaryDark));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                frameLayout.setBackgroundColor((int) valueAnimator.getAnimatedValue());
            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();
    }

    private void showAnimationSet() {
        if (mustLoadFromXml) {
            AnimationSet animationSet = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.sample_animation_set);
            animationSet.setFillAfter(true);
            animationSet.setDuration(1000);
            animationSet.setRepeatCount(Animation.INFINITE);
            animationSet.setRepeatMode(Animation.REVERSE);
            kouroshImage.startAnimation(animationSet);
        } else {

            AnimationSet animationSet = new AnimationSet(true);
            ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

            TranslateAnimation translateAnimation = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0,
                    Animation.ABSOLUTE, 0, Animation.RELATIVE_TO_SELF, 1.0f);

            animationSet.addAnimation(scaleAnimation);
            animationSet.addAnimation(translateAnimation);
            animationSet.setDuration(1000);
            animationSet.setRepeatMode(Animation.REVERSE);
            animationSet.setRepeatCount(Animation.INFINITE);
            animationSet.setFillAfter(true);
            kouroshImage.startAnimation(animationSet);
        }
    }
}

