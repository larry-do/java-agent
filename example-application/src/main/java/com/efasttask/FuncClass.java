package com.efasttask;

public class FuncClass {
    public static void printGoodBye() {
        System.out.println("Good bye");
    }

    public static void printHello(int i) {
        System.out.println("Hello, friend " + i);
    }

    public static void printSomething() throws InterruptedException {
        int i = 180;
        while (i-- >= 0) {
            printHello(i);
            Thread.sleep(1000);
        }
    }
}
