package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberSequence3 {
    private static final Logger logger = LoggerFactory.getLogger(NumberSequence3.class);
    private int last = 0;
    private int firstNumber = 1;
    private int lastNumber = 10;
    private int modificator = 1;
    private boolean wasNumberShownByFirstThread = false;


    private synchronized void action(String threadName) {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                //spurious wakeup https://en.wikipedia.org/wiki/Spurious_wakeup
                //часть, отвечающая за то, чтобы всегда начинал 1-ый поток
                while (last == 0 && !Thread.currentThread().getName().equals("Thread-0")) {
                    this.wait();
                }
                if (last == 0) {
                    last++;
                }

                //пишем в консоль наше значение
                logger.info("{} : {}", Thread.currentThread().getName(), last);

                //изменяем состояние объекта и наше значение
                if (Thread.currentThread().getName().equals("Thread-0")) {
                    wasNumberShownByFirstThread = true;
                    sleep();
                    notifyAll();

                } else {
                    last += modificator;
                    wasNumberShownByFirstThread = false;
                    sleep();
                    notifyAll();

                }
                //меняем модификатор нашего значение в случе если это не первый поток, т.е. мы уже показали значение со стороны каждого потока
                if (last == lastNumber && !Thread.currentThread().getName().equals("Thread-0")) {
                    modificator -= 2;
                }
                if (last == firstNumber && !Thread.currentThread().getName().equals("Thread-0")) {
                    modificator += 2;
                }

                //если это первый поток и при этом он уже показывал значение, то просим его отключиться
                while (Thread.currentThread().getName().equals("Thread-0") && wasNumberShownByFirstThread == true) {
                    this.wait();
                }
                //если это не первый поток и при этом он уже показывал значение, то просим его отключиться
                while (!Thread.currentThread().getName().equals("Thread-0") && wasNumberShownByFirstThread == false) {
                    this.wait();
                }

            } catch (Exception ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        NumberSequence3 pingPong = new NumberSequence3();
        new Thread(() -> pingPong.action("Thread-0")).start();
        new Thread(() -> pingPong.action("Thread-1")).start();
    }

    private static void sleep() {
        try {
            Thread.sleep(1_00);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
