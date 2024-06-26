package com.liang.demo3;

public class ThreadDemo3 {

    public static void main(String[] args) {

        ShareResource shareResource = new ShareResource();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 11; i++) {
                    shareResource.print1(i);
                }
            }
        },"AA").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 11; i++) {
                    shareResource.print2(i);
                }
            }
        },"BB").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 11; i++) {
                    shareResource.print3(i);
                }
            }
        },"CC").start();

    }

}
