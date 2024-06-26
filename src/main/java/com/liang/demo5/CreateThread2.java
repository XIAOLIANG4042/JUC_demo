package com.liang.demo5;

public class CreateThread2 implements Runnable {

    @Override
    public void run() {
        System.out.println("使用 实现Runnable 方法创建一个线程");
    }

    public static void main(String[] args) {
        new Thread(new CreateThread2()).start();
    }

}
