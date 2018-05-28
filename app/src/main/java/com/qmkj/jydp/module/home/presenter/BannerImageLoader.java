package com.qmkj.jydp.module.home.presenter;

import android.content.Context;
import android.widget.ImageView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.GlideApp;
import com.qmkj.jydp.bean.response.HomeDataRes;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Yun on 2018/1/6.
 */

public class BannerImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object obj, ImageView imageView) {
        if (obj instanceof HomeDataRes.SystemAdsHomepagesListBean) {
            GlideApp.with(context)
                    .load(((HomeDataRes.SystemAdsHomepagesListBean) obj).getAdsImageUrlFormat())
                    .placeholder(R.mipmap.ic_banner_loading)
                    .error(R.mipmap.ic_banner_fail)
                    .into(imageView);
        }
    }

    @Override
    public ImageView createImageView(Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }
}
