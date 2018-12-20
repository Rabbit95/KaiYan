package com.rabbit.kaiyan.component;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


public class ImageLoader {
    public static void load(Context context, String url, ImageView imageView){
        Glide.with(context).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }

    public static void loadCircle(final Context context,String url,final ImageView imageView){
        Glide.with(context).load(url).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).centerCrop().into(new BitmapImageViewTarget(imageView){
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(),resource);
                roundedBitmapDrawable.setCircular(true);
                imageView.setImageDrawable(roundedBitmapDrawable);
            }
        });
    }

    public static void loadRound(Context context,String url,final ImageView imageView){
        Glide.with(context).load(url).bitmapTransform(new CenterCrop(context),new RoundedCornersTransformation(context,15,0))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }

    public static void loadSquare(Context context,String url,ImageView imageView){
        Glide.with(context).load(url).bitmapTransform(new CenterCrop(context),new RoundedCornersTransformation(context,15,0))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }
}
