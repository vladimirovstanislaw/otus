package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class NumberSequence2 {
    private static final Logger logger = LoggerFactory.getLogger(NumberSequence.class);

    private static String thread = "Thread ";
    private static String threadOne = "Thread 1";

    private static int countOfThread = 2;
    private AtomicInteger modificator = new AtomicInteger(1);

    private AtomicInteger currentValue = new AtomicInteger(1);
    private boolean wasCurrentValueShowedByFirstThread = false;
    private static final int firstNumber = 1;
    private static final int lastNumber = 10;
    private static final List<NumberUnit> numberUnitList = new ArrayList<>();
    private boolean isFirstThreadStarted = false;

    private synchronized void action(String threadName) {
        while (!Thread.currentThread().isInterrupted()) {
            try {

                logger.info("{}: {}", Thread.currentThread().getName(), currentValue);
                while (!Thread.currentThread().getName().equals(threadOne) && (currentValue.get() == 1)) {
                    Thread.currentThread().wait();
                    //this.wait();
                }
                logger.info("{}: {}", Thread.currentThread().getName(), currentValue);
                currentValue.incrementAndGet();

                sleep();
                notifyAll();

            } catch (Exception ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        NumberSequence2 numberSequence2 = new NumberSequence2();
        List<Thread> threadList = new ArrayList<>();

        for (int i = 1; i <= countOfThread; i++) {
            String threadName = thread + i;
            Thread t = new Thread(() -> numberSequence2.action(threadName));
            t.setName(threadName);
            threadList.add(t);
        }
        for (int i = firstNumber; i <= lastNumber; i++) {
            NumberUnit nu = new NumberUnit(i, countOfThread);
            numberUnitList.add(nu);
        }
        for (Thread t : threadList) {
            t.start();
        }

    }

    private static void sleep() {
        try {
            Thread.currentThread().sleep(3_000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
