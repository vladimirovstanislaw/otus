package ru.otus.cash;

public class BanknoteDenominationImplementation implements BanknoteDenomination{
    private final int denomination;

    public BanknoteDenominationImplementation(int denomination) {
        this.denomination = denomination;
    }

    @Override
    public int getDenomination() {
        return denomination;
    }
}
