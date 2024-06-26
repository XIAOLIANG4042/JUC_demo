package com.liang.demo5;

public class CreateThread1 extends Thread{

    @Override
    public void run() {
        System.out.println("使用继承Thread方式创建一个线程");
    }

    public static void main(String[] args) {
        new CreateThread1().start();
    }

}
