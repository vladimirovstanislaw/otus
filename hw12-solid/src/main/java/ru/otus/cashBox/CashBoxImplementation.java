package ru.otus.cashBox;

import ru.otus.cash.*;

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
        return banknoteType.getDenomination();
    }

    @Override
    public void addBanknote(Banknote banknote) {
        cash.add(banknote);
    }

    @Override
    public int amount() {
        return banknoteType.getDenomination() * cash.size();
    }

    @Override
    public int banknoteCount() {
        return cash.size();
    }

    @Override
    public List<Banknote> giveBanknotes(int count) {
        if (count <= cash.size()) {
            List<Banknote> returnableList = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                returnableList.add(cash.get(i));
            }
            cash.removeAll(returnableList);
            return returnableList;
        }
        return List.of();
    }
}
