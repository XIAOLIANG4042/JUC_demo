package com.liang;

import java.util.concurrent.*;

public class executeAndSubmitDemo {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                4,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

//Exception in thread "pool-1-thread-1" java.lang.ArithmeticException: / by zero
        //execute 直接抛出异常
//        threadPoolExecutor.execute(new TestExecute());


        //不会主动抛出异常
        Future<Integer> result = threadPoolExecutor.submit(new TestSubmit());
//
        Thread.sleep(1000);
        System.out.println(result.isDone());
//        //获取返回结果才会抛出异常
        System.out.println(result.get());


    }





}


 class TestExecute implements Runnable {

    @Override
    public void run() {
        //抛出异常
        int i = 10/0;
    }
}


 class TestSubmit implements Callable<Integer> {

    @Override
    public Integer call() {

        int i = 10/0;

        return  i;
    }
}