package com.qmkj.jydp.base;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.stream.HttpGlideUrlLoader;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.target.ViewTarget;
import com.qmkj.jydp.R;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * Created by Rongkui.xiao on 2017/5/19.
 *
 * @description
 */
@GlideModule
public final class BaseCachingGlideModule extends AppGlideModule {
    private static final String IMAGE_CACHE_NAME = "image";

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        ViewTarget.setTagId(R.id.glide_tag_id);
        //设置图片的显示格式ARGB_8888(指图片大小为32bit)
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        // 设置磁盘缓存为100M，缓存在内部缓存目录
        int cacheSize = 100 * 1024 * 1024;
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, IMAGE_CACHE_NAME, cacheSize));

        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, IMAGE_CACHE_NAME, cacheSize));

        // 20%大的内存缓存作为 Glide 的默认值
        MemorySizeCalculator.Builder builder1 = new MemorySizeCalculator.Builder(context);
        MemorySizeCalculator calculator = builder1.build();
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();

        int customMemoryCacheSize = (int) (1.2 * defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (1.2 * defaultBitmapPoolSize);

        builder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));
    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        registry.append(GlideUrl.class, InputStream.class, new HttpGlideUrlLoader.Factory());
    }
}
