package com.yx.wx.platform.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by weiwang207 on 2017/3/17.
 */
public class FutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> future = new FutureTask<>(() -> {
            System.out.println("xxxxx start");
            Thread.sleep(5000);
            System.out.println("xxxxx end");
            return "ok";
        });
        new Thread(future).start();

//        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println(i);
            }
//        }).start();
        String result = future.get();

        System.out.println("game over!");

    }
}
