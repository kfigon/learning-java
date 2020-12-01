package com.example.learningjava.threading;

import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;

class ThreadTest {
//    proces ma wlasny heap
//    thread wspoldzieli heapa z innymi, ma swoj stack

    private static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println(ConsoleColor.CYAN.getCode() + "hello from the thread");
        }
    }

    @Test
    void test1(){
        System.out.println(ConsoleColor.BLACK.getCode() + "hello from main");
        MyThread thread = new MyThread();
        thread.start();
        System.out.println(ConsoleColor.BLACK.getCode() + "goodbye to main");
    }

    @Test
    void test2() {
        System.out.println("hello from main");
        Runnable runner = () -> System.out.println(ConsoleColor.CYAN.getCode() + "hello from the thread");
        new Thread(runner).start();
        System.out.println("goodbye to main");
    }

    @Test
    void interrupt() {
        // then thread sleeps, we can interrupt
        System.out.println("hello from main");
        Thread longThread = new Thread(() -> {
            try {
                System.out.println(ConsoleColor.CYAN.getCode() + "Starting long thread");
                sleep(1000);
                System.out.println(ConsoleColor.CYAN.getCode() + "Ending long thread");
            } catch (InterruptedException e) {
                System.out.println(ConsoleColor.BLUE.getCode() + "Long thread interrupted");
            }
        });

        Thread thread2 = new Thread(() -> System.out.println(ConsoleColor.RED.getCode() + "hello from short thread!"));
        longThread.start();
        thread2.start();
        longThread.interrupt();
        System.out.println("goodbye to main");
    }
}
