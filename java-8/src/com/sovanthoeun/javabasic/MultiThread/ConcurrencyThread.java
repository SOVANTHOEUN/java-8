package com.sovanthoeun.javabasic.MultiThread;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConcurrencyThread implements Callable<Integer> {

    public static void main(String[] args){
        System.out.println("main() called: "+System.currentTimeMillis());

        int maxThreadCnt = 10;
        ExecutorService es = Executors.newFixedThreadPool(maxThreadCnt);

//        Callable<Integer> callable = null;
//        callable = new ConcurrencyThread();
//        es.submit(callable);

        ArrayList<Callable<Integer>> callableObj = new ArrayList<Callable<Integer>>();
        callableObj.add(ConcurrencyThread::deleteTXTfile);
        callableObj.add(ConcurrencyThread::deleteCSVfile);
        for(Callable<Integer> callable : callableObj){
            es.submit(callable);
        }

    }
    public static Integer deleteTXTfile(){
        System.out.println("call delete TXT: "+System.currentTimeMillis());
        System.out.println("thead id 1: "+Thread.currentThread().getName());
        return null;
    }

    public static Integer deleteCSVfile(){
        System.out.println("call delete CSV: "+System.currentTimeMillis());
        System.out.println("thead id 2: "+Thread.currentThread().getName());
        return null;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("call() called: "+System.currentTimeMillis());
        System.out.println("thead id 3: "+Thread.currentThread().getName());

        ConcurrencyThread.deleteTXTfile();
        ConcurrencyThread.deleteCSVfile();
        return null;
    }
}
