package com.liang.demo5;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CreateThread3 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("使用 实现Callable接口 创建一个线程");
        TimeUnit.SECONDS.sleep(5);
        return 0;
    }

    public static void main(String[] args) {
        CreateThread3 createThread3 = new CreateThread3();
        try {
            Integer result = createThread3.call();
            System.out.println("线程返回的结果是："+result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
