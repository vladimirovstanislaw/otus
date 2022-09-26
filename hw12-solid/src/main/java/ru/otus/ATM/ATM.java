package ru.otus.ATM;

import ru.otus.cash.Banknote;

import java.util.List;

public interface ATM {
    List<Banknote> giveMoney(int amount);

    void putMoney(List<Banknote> wadOfMoney);

    int showAmountOfAvailableMoney();

}
