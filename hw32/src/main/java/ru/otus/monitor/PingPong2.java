package ru.otus.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PingPong2 {
    private static final Logger logger = LoggerFactory.getLogger(PingPong2.class);

    private String threadName1 = "Thread 1";
    private String threadName2 = "Thread 2";

    private int currentValue = 1;
    private int firstNumber = 1;
    private int lastNumber = 10;
    private boolean isFirstThreadStarted = false;

    private synchronized void action() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                //spurious wakeup https://en.wikipedia.org/wiki/Spurious_wakeup
                //поэтому не if
                while (Thread.currentThread().getName().equals(threadName2) && isFirstThreadStarted == false) {
                    this.wait();
                }
                while (Thread.currentThread().getName().equals(threadName1) && isFirstThreadStarted == false) {
                    isFirstThreadStarted = true;
                }

                logger.info("{}: {}", Thread.currentThread().getName(), currentValue);
                currentValue++;
                sleep();
                notifyAll();
                logger.info("after notify");
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        logger.info("{}", Thread.currentThread().getName());
        PingPong2 pingPong = new PingPong2();
        var first = new Thread(() -> pingPong.action());
        first.setName("Thread 1");
        var second = new Thread(() -> pingPong.action());
        second.setName("Thread 2");

        first.start();
        second.start();
    }

    private static void sleep() {
        try {
            Thread.sleep(1_0);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
