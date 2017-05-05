package com.example.dllo.blevel.tool;

import android.content.Context;
import android.os.Environment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by dllo on 17/4/19.
 */

public class Tools {

        private final  static String ALBUM_PATH  = Environment.getExternalStorageState()+"/yueqiu/";

        public  int dip2px(Context context , float dpValue){

            final float scale = context.getResources().getDisplayMetrics().density;

            return (int) (dpValue*scale+0.5f);
        }

        public int px2dip(Context context,float pxValue){
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (pxValue/scale+0.5f);

        }
        public static ArrayList<Double> getintFormMap(HashMap<Double,Double> map){
            ArrayList<Double> dlk = new ArrayList<Double>();
            Set set = map.entrySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()){
                Map.Entry mapentry = (Map.Entry) iterator.next();
                dlk.add((Double) mapentry.getKey());
                Collections.sort(dlk);
            }
            return dlk;
        }
    }

