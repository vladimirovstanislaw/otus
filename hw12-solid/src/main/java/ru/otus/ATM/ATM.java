package ru.otus.ATM;

import ru.otus.cash.Banknote;

import java.util.List;

public interface ATM {
    List<Banknote> giveMoney(int amount);

    List<Banknote> putMoney(List<Banknote> wadOfMoney);

    int showAmountOfAvailableMoney();

}
