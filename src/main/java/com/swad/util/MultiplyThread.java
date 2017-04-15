package com.swad.util;

/**
 * Created by gonghaiyu on 28/03/2017.
 */
public class MultiplyThread extends Thread {

    @Override
    public void run(){
        for(int count = 1,row = 1;row < 10;row++,count++){
            for(int i = 0;i<count;i++){
                System.out.print('*');
            }
            System.out.println();
        }
    }

    public static void main(String [] args){
        MultiplyThread multiplyThread = new MultiplyThread();
        multiplyThread.start();
    }
}
