package com.liang.demo7;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//资源类
public class MyCache {

    //创建一个MAp集合
  HashMap<String,Object> cache = new HashMap<String, Object>();


  //创建一个读写锁  默认非公平锁
    ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public void put(String key,Object value){
        //获取写锁
        rwl.writeLock().lock();
        //写入数据

        try {
            System.out.println(Thread.currentThread().getName()+"正在进入写操作");


            //写入数据
            cache.put(key, value);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+"写入完成"+key+" value:"+value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            rwl.writeLock().unlock();
        }

    }


    public Object get(String key){
        rwl.readLock().lock();
        Object result = null;
        try {
//            System.out.println(Thread.currentThread().getName()+"正在进行读操作");
            result = cache.getOrDefault(key, null);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+"行读操作完成"+key+" value:"+result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            rwl.readLock().unlock();
        }

        return result;

    }



}
