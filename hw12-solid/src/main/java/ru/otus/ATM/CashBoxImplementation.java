package ru.otus.ATM;

import ru.otus.cash.Banknote;
import ru.otus.cash.BanknoteImplementation;

import java.util.ArrayList;
import java.util.List;

public class CashBoxImplementation implements CashBox {
    private List<Banknote> cash;
    private final Banknote banknoteType;

    public CashBoxImplementation(Banknote banknoteType) {
        this.banknoteType = banknoteType;
        cash = new ArrayList<>();
    }

    @Override
    public int getDenomination() {
        return banknoteType.getDenomination().getDenomination();
    }
}
