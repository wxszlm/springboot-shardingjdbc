package top.yxf.demo;

public class ThreadTest {

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {


        t1 = new Thread(() -> {

            synchronized (t1) {
                for (char c : "12345".toCharArray()) {
                    System.out.print(c);
                    try {
                        t1.wait(3*1000);
//                        t2.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, "t1");


//        t2 = new Thread(() -> {
//            synchronized (t2) {
//                for (char c : "abcde".toCharArray()) {
//                    System.out.print(c);
//                    t1.notify();
//                    try {
//                        t2.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }

//        }, "t2");

        t1.start();
//        t2.start();


    }

}
