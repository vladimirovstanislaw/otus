package ru.otus.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class PingPong2 {
    private static final Logger logger = LoggerFactory.getLogger(PingPong2.class);

    private static String threadName1 = "Thread 1";
    private static String threadName2 = "Thread 2";
    private AtomicInteger modificator = new AtomicInteger(1);

    private AtomicInteger currentValue = new AtomicInteger(1);
    private boolean wasCurrentValueShowedByFirstThread = false;
    private int firstNumber = 1;
    private int lastNumber = 10;
    private boolean isFirstThreadStarted = false;

    private synchronized void action(String threadName) {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                //spurious wakeup https://en.wikipedia.org/wiki/Spurious_wakeup
                //поэтому не if
                while (threadName.equals(threadName2) && isFirstThreadStarted == false) {
                    this.wait();
                }
                while (threadName.equals(threadName1) && isFirstThreadStarted == false) {
                    isFirstThreadStarted = true;
                }
                while (threadName.equals(threadName2) && wasCurrentValueShowedByFirstThread == false) {
                    this.wait();
                }
                while (threadName.equals(threadName1) && wasCurrentValueShowedByFirstThread == true) {
                    this.wait();
                }

                while (threadName.equals(threadName1) && wasCurrentValueShowedByFirstThread == true) {
                    this.wait();
                }


                while (threadName.equals(threadName1)) {
                    logger.info("{}: {}", threadName, currentValue);
                    break;
                }
                while (threadName.equals(threadName2) && currentValue.get() < 10) {
                    logger.info("{}: {}", threadName, currentValue);
                    currentValue.addAndGet(modificator.get());
                    break;
                }
                while (threadName.equals(threadName2) && currentValue.get() == 10) {
                    logger.info("{}: {}", threadName, currentValue);
                    modificator.getAndAdd(-2);
                    currentValue.addAndGet(modificator.get());
                    break;
                }
                while (threadName.equals(threadName1)) {
                    wasCurrentValueShowedByFirstThread = true;
                    break;
                }
                while (threadName.equals(threadName2)) {
                    wasCurrentValueShowedByFirstThread = false;
                    break;
                }
                sleep();
                notifyAll();
                logger.info("after notify");
            } catch (Exception ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        PingPong2 pingPong = new PingPong2();
        new Thread(() -> pingPong.action(threadName1)).start();
        new Thread(() -> pingPong.action(threadName2)).start();


    }

    private static void sleep() {
        try {
            Thread.currentThread().sleep(3_00);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
