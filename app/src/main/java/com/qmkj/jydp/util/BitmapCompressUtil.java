package com.qmkj.jydp.util;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Yun on 2018/1/9.
 * 压缩图片工具类
 */
public class BitmapCompressUtil {

    /**
     * 压缩图片
     *
     * @return 压缩至500k以内的图片byte[]
     */
    /*public static byte[] compress(Bitmap bitmap) {
        double maxSize = 500 * 1024 * 1024;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, (int) Math.min(maxSize / bitmap.getByteCount() / 8 * 100, 100), stream);
        return stream.toByteArray();
    }*/

    /**
     * 压缩图片
     *
     * @return 压缩至500k以内的图片byte[]
     */
    public static byte[] compress(Bitmap bitmap) {
        double maxSize = 500;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        // scale
        int options = 100;
        // Store the bitmap into output stream(no compress)
        bitmap.compress(Bitmap.CompressFormat.JPEG, options, os);
        // Compress by loop
        while (os.toByteArray().length / 1024 > maxSize) {
            // Clean up os
            os.reset();
            // interval 10
            options -= 10;
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, os);
        }
        byte[] compressBytes = os.toByteArray();
        try {
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return compressBytes;
    }

}
