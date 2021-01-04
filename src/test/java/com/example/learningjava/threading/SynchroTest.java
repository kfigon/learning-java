package com.example.learningjava.threading;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class SynchroTest {
    AtomicInteger atomicInteger = new AtomicInteger(10);
    int regularInteger = 10;

    //        volatile makes ONLY ONE atomic read OR write, where atomics can do few atomic operations, e.g getAndAdd
    volatile int volatileInt = 0; // `synchronised` for variables. Ensures that the variable won't be cached (read the latest value from main memory), and JVM won't optimize it by rearranging

    @Test
    void test1() throws InterruptedException {

        Runnable r1 = atomicInteger::decrementAndGet;
        Runnable r2 = atomicInteger::decrementAndGet;

        Thread thread = new Thread(r1);
        Thread thread1 = new Thread(r2);
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();

        assertThat(atomicInteger.get()).isEqualTo(8);
    }

    private void decrement() {
        regularInteger--;
        System.out.println("decrementing: "+regularInteger);
    }

    @Test
    void test2() throws InterruptedException {

        Runnable r1 = this::decrement;
        Runnable r2 = this::decrement;

        Thread thread = new Thread(r1);
        Thread thread1 = new Thread(r2);
        thread.start();
        thread1.start();
//
        thread.join();
        thread1.join();

        System.out.println("final res: " +regularInteger);
    }

    @Test
    void test3() throws InterruptedException {

        Runnable r1 = this::decrementSafe;
        Runnable r2 = this::decrementSafe;

        Thread thread = new Thread(r1);
        Thread thread1 = new Thread(r2);
        thread.start();
        thread1.start();

        thread.join();
        thread1.join();

        System.out.println("final res: " +regularInteger);
    }

    @Test
    void volatileTest() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(100);

//        volatile makes ONLY ONE atomic read OR write, where atomics can do few atomic operations, e.g getAndAdd
        IntStream.range(0, 10000)
                .forEach(count -> service.submit(() -> volatileInt++));
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);

        assertThat(volatileInt).isEqualTo(10000);
    }


//    synchronised method
//    sync block - DONT use local variables, use this or some member
    public synchronized void decrementSafe() {
        regularInteger--;
        System.out.println("decrementing safe: "+regularInteger);
    }

//    deadlock - ktos trzyma zasob i wszyscy stoja
//    wait i notify - metody na komunikacje miedzy watkami

//    wait dajemy w okresie czekania (w petli)
//    notify gdy inny worker skonczy - wtedy wait pierwszego sie skonczy
}
