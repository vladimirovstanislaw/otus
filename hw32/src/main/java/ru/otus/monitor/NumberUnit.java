package ru.otus.monitor;

public class NumberUnit {
    private int number;
    private boolean isShowedByThreadOne;
    private boolean isShowedByThreadTwo;

    public NumberUnit() {
    }

    public NumberUnit(int number, boolean isShowedByThreadOne, boolean isShowedByThreadTwo) {
        this.number = number;
        this.isShowedByThreadOne = isShowedByThreadOne;
        this.isShowedByThreadTwo = isShowedByThreadTwo;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isShowedByThreadOne() {
        return isShowedByThreadOne;
    }

    public void setShowedByThreadOne(boolean showedByThreadOne) {
        isShowedByThreadOne = showedByThreadOne;
    }

    public boolean isShowedByThreadTwo() {
        return isShowedByThreadTwo;
    }

    public void setShowedByThreadTwo(boolean showedByThreadTwo) {
        isShowedByThreadTwo = showedByThreadTwo;
    }
}
