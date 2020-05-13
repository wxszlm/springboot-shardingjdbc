package top.yxf.demo.thread;

public class TestYield implements  Runnable{

    private String name;

    public TestYield(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(name  + ":" + i);
            if ("t1".equals(name) && i == 5) {
                System.out.println(name  + ":" + i +"......yield.............");
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new TestYield("t1"));
        Thread t2 = new Thread(new TestYield("t2"));
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t1.start();
        t2.start();
    }
}
