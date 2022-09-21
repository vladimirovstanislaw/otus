package ru.otus.cash;

public class BanknoteImplementation implements Banknote {

    private final BanknoteDenominationImplementation denomination;


    public BanknoteImplementation(BanknoteDenominationImplementation denomination) {
        this.denomination = denomination;
    }

    @Override
    public BanknoteDenomination getDenomination() {
        return denomination;
    }
}
