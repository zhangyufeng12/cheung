package com.example.didi.CountDownLatch_Test;

import com.example.didi.tools.http.HttpUtil;

import java.util.concurrent.CountDownLatch;

public class CountDownLatch_test {



    public static void main(String[]args){

        final CountDownLatch cld = new CountDownLatch( 1 );

        for (int i=0;i<3;i++){

            Thread thread = new Thread( new Runnable() {
                @Override
                public void run() {
                    String res;
                   try {
                       cld.await();
                       Long start =System.currentTimeMillis();
                       res = new HttpUtil().doGet( "http://localhost:8018/trade/Ins?orderid=12313&userid=32123" );
                       System.out.println("线程开始时间："+start+"...."+res);

                   }catch (Exception e){

                   }
                }
            } );
            thread.start();
        }
        //递减锁存器的计数，如果计数到达零，则释放所有等待的线程
        cld.countDown();
    }
}


