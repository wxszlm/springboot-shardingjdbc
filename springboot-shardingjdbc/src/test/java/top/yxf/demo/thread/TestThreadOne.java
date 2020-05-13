package top.yxf.demo.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class TestThreadOne {

    static Thread t1 = null,t2 = null;


    public static void main(String[] args) {

        char[] num = "12345678".toCharArray();
        char[] ch = "ABCDEFGH".toCharArray();

//        for (int i = 0; i <100 ; i++) {
            useLockCondition(num, ch);
//        }

    }

    private static void useLockCondition(char[] num,char[] ch) {

        Lock lock = new ReentrantLock();
        /**
         *  condition是个队列，一个condition是一个队列，可以用来解决生产者消费者问题，
         *  将生产者和消费者分别放入两个condition也就是两个队列里，这样就可以分别叫醒生产者和消费者，不用同时把他们叫醒了。
         *  而平常的synchronized只有一个队列，生产者和消费者全都放在一个队列里了
         */

        Condition conditionT1 = lock.newCondition();
        Condition conditionT2 = lock.newCondition();

        new Thread(() ->{
            try {
                lock.lock();
                for (char c : ch) {
                    System.out.print(c);
                    // 叫醒t2
                    conditionT2.signal();
                    // t1等待
                    conditionT1.await();
                }
                conditionT2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() ->{
            try {
                lock.lock();
                for (char c : num) {
                    System.out.print(c);
                    // 叫醒t1
                    conditionT1.signal();
                    // t2等待
                    conditionT2.await();
                }
                conditionT1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }



    private static void useWaitNotify(char[] num,char[] ch) {

        // 创建一个锁对象
        final Object o = new Object();

        new Thread(() ->{
            synchronized (o) {
                for (char c : ch) {
                    System.out.print(c);
                    try {
                        o.notify();
                        // 自己让出锁
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 必须加，否则程序无法停止
                o.notify();
            }

        }, "t1").start();


        new Thread(() ->{
            synchronized (o) {
                for (char c : num) {
                    System.out.print(c);
                    try {
                        o.notify();
                        // 自己让出锁
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 必须加，否则程序无法停止
                o.notify();
            }

        }, "t2").start();
    }

    private static void useLockSupport(char[] num,char[] ch) {

        t1 = new Thread(()->{
            for (char c : num) {
                System.out.print(c);
                // 叫醒t2
                LockSupport.unpark(t2);
                // t1阻塞
                LockSupport.park();
            }
        } ,"t1");


        t2 = new Thread(()->{
            for (char c : ch) {
                // t2阻塞
                LockSupport.park();
                System.out.print(c);
                // 叫醒t1
                LockSupport.unpark(t1);
            }
        } ,"t2");

        t1.start();
        t2.start();
    }

}
