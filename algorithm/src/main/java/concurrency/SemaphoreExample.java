package main.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    static class Foo {
        private int i = 0;
        private Semaphore run1 = new Semaphore(0);
        private Semaphore run2 = new Semaphore(0);

        public void first() {
            System.out.println("first");
            System.out.println("i=" + ++i);
            run1.release();
        }

        public void second() throws InterruptedException {
            run1.acquire();
            for (int i = 0; i < 1_000_000_000; i++) {

            }
            System.out.println("second");
            System.out.println("i=" + ++i);
            run2.release();
        }

        public void third() throws InterruptedException {
            run2.acquire();
            System.out.println("third");
            System.out.println("i=" + ++i);
        }
    }

    public static void main(String... strings) {
        System.out.println(Thread.currentThread().getId());
        Foo foo = new Foo();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(() -> {
            System.out.println(Thread.currentThread().getId());
            try {
                foo.third();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(() -> {
            System.out.println(Thread.currentThread().getId());
            foo.first();
        });
        executorService.submit(() -> {
            System.out.println(Thread.currentThread().getId());
            try {
                foo.second();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
    }
}
