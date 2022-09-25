package ru.otus.ATM;

import ru.otus.cash.*;
import ru.otus.cashBox.CashBox;
import ru.otus.cashBox.CashBoxImplementation;

import java.util.*;

public class ATMImplementation implements ATM {
    private final NavigableSet<CashBox> cashBoxes = new TreeSet<>(Comparator.comparingInt(CashBox::getDenomination).reversed());
    private BanknoteService banknoteService = new BanknoteServiceImplementation();

    public ATMImplementation() {
        List<Banknote> banknoteTypes = banknoteService.getAllBanknoteTypes();
        for (Banknote banknote : banknoteTypes) {
            cashBoxes.add(new CashBoxImplementation(banknote));
        }
    }

    @Override
    public List<Banknote> giveMoney(int amount) {
        List<Banknote> money = new ArrayList<>();
        if (amount < 10 || amount > showAmountOfAvailableMoney() || amount % 10 != 0) {
            return List.of();
        }
        int reminder = amount;
        for (CashBox cashBox : cashBoxes) {
            if (cashBox.banknoteCount() == 0) {
                continue;
            }
            if (reminder == 0) {
                break;
            }
            int devide = reminder / cashBox.getDenomination();
            if (devide == 0) {
                continue;
            } else {
                if (cashBox.banknoteCount() >= devide) {
                    money.addAll(cashBox.giveBanknotes(devide));
                    reminder -= devide * cashBox.getDenomination();

                } else {
                    int cashBoxBanknoteCount = cashBox.banknoteCount();
                    money.addAll(cashBox.giveBanknotes(cashBoxBanknoteCount));
                    reminder -= cashBoxBanknoteCount * cashBox.getDenomination();
                }

            }


        }
        if (reminder == 0) {
            return money;
        } else {
            return List.of();
        }

    }

    public static void main(String[] args) {
        System.out.println((int) 4.5 / 2);
    }


    @Override
    public List<Banknote> putMoney(List<Banknote> wadOfMoney) {
        List<Banknote> wrongMoney = new ArrayList<>();
        for (Banknote banknote : wadOfMoney) {

            if (banknoteService.getAllBanknoteTypesValues().contains(banknote.getDenomination())) {
                for (CashBox cashBox : cashBoxes) {
                    if (cashBox.getDenomination() == banknote.getDenomination()) {
                        cashBox.addBanknote(banknote);
                    }
                }
            } else {
                wrongMoney.add(banknote);
            }
        }
        return wrongMoney;
    }

    @Override
    public int showAmountOfAvailableMoney() {
        int availableMoney = 0;
        for (CashBox cashBox : cashBoxes) {
            availableMoney += cashBox.amount();
        }
        return availableMoney;
    }
}
