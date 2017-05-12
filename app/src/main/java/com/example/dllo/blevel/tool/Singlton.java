package com.example.dllo.blevel.tool;

/**
 * Created by dllo on 17/5/12.
 */

public class Singlton {
    private static class SingltonHolder{
        private static final Singlton INSTANCE = new Singlton();

    }
    public static final Singlton getInstance(){
        return SingltonHolder.INSTANCE;
    }
    private Singlton(){

    }
}
