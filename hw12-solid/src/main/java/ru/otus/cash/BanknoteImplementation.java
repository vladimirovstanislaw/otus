package ru.otus.cash;

public class BanknoteImplementation implements Banknote {

    private final BanknoteDenomination denomination;


    public BanknoteImplementation(BanknoteDenomination denomination) {
        this.denomination = denomination;
    }

    @Override
    public int getDenomination() {
        return denomination.denomination();
    }
}
