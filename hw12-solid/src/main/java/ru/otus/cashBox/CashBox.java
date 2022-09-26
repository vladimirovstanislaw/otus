package ru.otus.cashBox;

import ru.otus.cash.Banknote;

import java.util.List;

public interface CashBox {
    int getDenomination();

    void addBanknote(Banknote banknote);

    int amount();

    int banknoteCount();

    List<Banknote> giveBanknotes(int count);

}
