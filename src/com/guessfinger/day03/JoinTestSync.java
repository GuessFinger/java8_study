package com.guessfinger.day03;

/**
 * create by GuessFinger on 2019/4/10
 **/
public class JoinTestSync {

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        ThreadJoinTest1 t1 = new ThreadJoinTest1("今天");
        ThreadJoinTest1 t2 = new ThreadJoinTest1("明天");
        ThreadJoinTest1 t3 = new ThreadJoinTest1("后天");
        /*
         * 通过join方法来确保t1、t2、t3的执行顺序
         * */
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();
    }

}

class ThreadJoinTest1 extends Thread {
    public ThreadJoinTest1(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(this.getName() + ":" + i);
        }
    }

}