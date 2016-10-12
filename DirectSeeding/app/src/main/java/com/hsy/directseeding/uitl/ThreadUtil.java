package com.hsy.directseeding.uitl;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhaojiaxing_name on 16/3/22.
 */
public class ThreadUtil {
    public static ExecutorService threadPool = Executors.newFixedThreadPool(5);
}
