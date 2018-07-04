package com.example.steeve.myapplication;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

/**
 * Created by Steeve on 5/6/2018.
 */

public class ViewPagerAdapter extends PagerAdapter{
    Context mContext;
    private int[] sliderImageId = new int[]{
            R.drawable.img1, R.drawable.slide5, R.drawable.slide7,
    };


    ViewPagerAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==  object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(sliderImageId[1]);
        ((ViewPager) container).addView(imageView, 0);
        return imageView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }




    @Override
    public int getCount() {
        return sliderImageId.length;
    }


}