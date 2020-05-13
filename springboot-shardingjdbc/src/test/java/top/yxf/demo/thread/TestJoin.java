package top.yxf.demo.thread;

public class TestJoin implements Runnable {

    private String name;

    public TestJoin(String name) {
        this.name = name;
    }


    public static void main(String[] args) {
        Thread t1 = new Thread(new TestYield("t1"));
        Thread t2 = new Thread(new TestYield("t2"));


//        try {
            System.out.println("当前线程为：" + Thread.currentThread().getName());
        t2.interrupt();


//            System.out.println(Thread.currentThread().isInterrupted());
//            Thread.currentThread().join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        t1.start();
//        try {
//            t1.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        t2.start();
    }

    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {
            System.out.println(name  + ":" + i);
            if ("t1".equals(name) && i == 5) {
                System.out.println(name  + ":" + i +"......join.............");
            }
        }

    }
}
