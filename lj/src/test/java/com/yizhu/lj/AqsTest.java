package com.yizhu.lj;

import org.junit.Test;

import java.util.concurrent.locks.Lock;

/**
 * 功能描述
 *
 * @author Lj
 * @date 2019/9/3
 */
public class AqsTest {
    final Lock lock = new AqsLock();


    @Test
    public void test() {
        class Worker extends Thread {
            public void run() {
               while (true){
                   lock.lock();

                   try {
                       Thread.sleep(1000L);
                       System.out.println(Thread.currentThread().getName());
                       Thread.sleep(1000L);

                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   } finally{
                       lock.unlock();
                   }
               }

            }


            }
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.start();

        }
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(200L);
                        System.out.println();
                    } catch (Exception ex) {
                    }
                }
            }
        }.start();
        try {
            Thread.sleep(20000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}
