package com.example.kotlintestdemo.util;

import android.graphics.Bitmap;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BitmapUtil {
    public  int resultBgcolor(Bitmap bitmap) {
        ArrayList<Integer> integers = comp(bitmap);
        HashMap<Integer, Integer> colors = new HashMap<>();
        for (Integer integer : integers) {
            if (colors.containsKey(integer)) {
                Integer integer1 = colors.get(integer);
                ++integer1;
                colors.remove(integer);
                colors.put(integer,integer1);
            } else {
            colors.put(integer,1);
            }
        }
        Iterator iterator=colors.entrySet().iterator();
        int count=0;
        int color=0;
        while (iterator.hasNext()) {
            Map.Entry entry= (Map.Entry) iterator.next();
            int value = (int) entry.getValue();
            if (count<value) {
                count=value;
                color= (int) entry.getKey();
            }
        }
        return  color;
    }

    private ArrayList<Integer> comp(Bitmap bitmap) {
        int wight = bitmap.getWidth();
        int height = bitmap.getHeight();

        int[] piels = new int[wight * height];
        bitmap.getPixels(piels, 0, wight, 0, 0, wight, height/4);
        ArrayList<Integer> rgb = new ArrayList<>();
        for (int i = 0; i < piels.length; i++) {
            int clr = piels[i];
            int red = (clr & 0x00ff0000) >> 16;
            int greem = (clr & 0x0000ff00) >> 8;
            int blue = (clr & 0x000000ff);

            int color = Color.rgb(red, greem, blue);
            if (color != Color.WHITE && color != Color.BLACK) {
                rgb.add(color);
            }
        }
        return rgb;
    }
}
