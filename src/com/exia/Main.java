package com.exia;

public class Main {

    public static void main(String[] args) {

        // anonymous class
        // ---------------
/*
        Runnable runnable = new Runnable() { // CTRL + i to implement methods

            @Override
            public void run() {
                System.out.println("I'm running in : " + Thread.currentThread().getName());
            }
        };

        Thread t = new Thread(runnable);
        t.setName("My thread");
        t.start();
*/

        // anonymous class into the Thread's constructor
        // ---------------------------------------------
/*        Thread t = new Thread(new Runnable() { // CTRL + i to implement methods

            @Override
            public void run() {
                System.out.println("I'm running in : " + Thread.currentThread().getName());
            }
        });
        t.setName("My thread");
        t.start();*/

        // lambda expression
        // -----------------
        Runnable runnable = () -> {
            System.out.println("I'm running in : " + Thread.currentThread().getName());
        };

        Thread t1 = new Thread(runnable);
        t1.setName("My thread 1");
        Thread t2 = new Thread(runnable);
        t2.setName("My thread 2");
        t1.start();
        t2.start();

    }
}