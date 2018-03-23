package com.practice;

/**
 * Copyright (C), XXX有限公司
 * FileName: MyThreadPool
 * Author:   chenlu
 * Date:     2018/3/23 19:34
 * Email:  1768245095@qq.com
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



public class MyThreadPool {

    private static ExecutorService es = null;

    static {
        int minThreadNum = 5;
        int maxThreadNum = Runtime.getRuntime().availableProcessors();
        if(maxThreadNum < minThreadNum) minThreadNum = maxThreadNum;
        es = new ThreadPoolExecutor(minThreadNum, maxThreadNum, 60L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    }

    public static void submit(QueryTask task){
        es.submit(task);
    }


    public static interface QueryHandler{
        void doQuery();
    }

    public static class QueryTask implements Runnable {
        private QueryHandler queryHandler;
        private CountDownLatch cdl;

        public QueryTask(CountDownLatch cdl, QueryHandler queryHandler){

            this.cdl = cdl;
            this.queryHandler = queryHandler;
        }
        public void run() {
            try{
                this.queryHandler.doQuery();
            }catch(Exception ex){

            }
            this.cdl.countDown();
        }
    }
}

