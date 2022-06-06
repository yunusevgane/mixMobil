package com.financemoney.yoga.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;

public class WaveDrawableRequest extends Drawable {
    protected int alpha;
    private Interpolator alphaInterpolator;
    private long animationTime;
    private Animator animator;
    private AnimatorSet animatorSet;
    private int color;
    private int inerRadius;
     int number;
    private int radius;
    private Interpolator waveInterpolator;
    private Paint wavePaint;
    private float waveScale;

    public WaveDrawableRequest(int i, int i2, long j) {
        this(i, i2);
        this.animationTime = j;
    }

    public void stopAnimation() {
        if (this.animator.isRunning()) {
            this.animator.end();
        }
    }

    public WaveDrawableRequest(int i, int i2) {
        this.number = 0;
        this.inerRadius = 0;
        this.animationTime = 2000;
        this.color = i;
        this.radius = i2;
        this.inerRadius = i2 - 200;
        this.waveScale = 0.0f;
        this.alpha = 255;
        this.wavePaint = new Paint(1);
        this.animatorSet = new AnimatorSet();
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        this.wavePaint.setStyle(Paint.Style.FILL);
        this.wavePaint.setColor(this.color);
        this.wavePaint.setAlpha(this.alpha);
        canvas.drawCircle((float) bounds.centerX(), (float) bounds.centerY(), ((float) this.radius) * this.waveScale, this.wavePaint);
        canvas.drawCircle((float) bounds.centerX(), (float) bounds.centerY(), ((float) this.inerRadius) * this.waveScale, this.wavePaint);
    }

    public void setWaveInterpolator(Interpolator interpolator) {
        this.waveInterpolator = interpolator;
    }

    public void setAlphaInterpolator(Interpolator interpolator) {
        this.alphaInterpolator = interpolator;
    }

    public void startAnimation() {
        this.animator = generateAnimation();
        this.animator.start();
    }

    public void setAlpha(int i) {
        this.alpha = i;
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.wavePaint.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        return this.wavePaint.getAlpha();
    }


    public void setWaveScale(float f) {
        this.waveScale = f;
        invalidateSelf();
    }

    private Animator generateAnimation() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "waveScale", new float[]{0.0f, 1.0f});
        ofFloat.setDuration(this.animationTime);
        Interpolator interpolator = this.waveInterpolator;
        if (interpolator != null) {
            ofFloat.setInterpolator(interpolator);
        }
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(-1);
        ObjectAnimator ofInt = ObjectAnimator.ofInt(this, "alpha", new int[]{255, 0});
        ofInt.setDuration(this.animationTime);
        Interpolator interpolator2 = this.alphaInterpolator;
        if (interpolator2 != null) {
            ofInt.setInterpolator(interpolator2);
        }
        ofInt.setRepeatCount(-1);
        ofInt.setRepeatMode(-1);
        this.animatorSet.playTogether(new Animator[]{ofFloat, ofInt});
        return this.animatorSet;
    }
}
