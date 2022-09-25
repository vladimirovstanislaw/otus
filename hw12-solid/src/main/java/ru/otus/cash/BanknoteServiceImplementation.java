package ru.otus.cash;

import java.util.ArrayList;
import java.util.List;

public class BanknoteServiceImplementation implements BanknoteService {
    private final BanknoteDenomination FiveK = new BanknoteDenominationImplementation(5_000);
    private final BanknoteDenomination TwoK = new BanknoteDenominationImplementation(2_000);
    private final BanknoteDenomination OneK = new BanknoteDenominationImplementation(1_000);
    private final BanknoteDenomination FiveH = new BanknoteDenominationImplementation(500);
    private final BanknoteDenomination TwoH = new BanknoteDenominationImplementation(200);
    private final BanknoteDenomination OneH = new BanknoteDenominationImplementation(100);
    private final BanknoteDenomination HalfH = new BanknoteDenominationImplementation(50);
    private final BanknoteDenomination Ten = new BanknoteDenominationImplementation(10);
    private final List<Banknote> banknoteTypes;
    private final List<Integer> banknoteTypesValues;

    public BanknoteServiceImplementation() {
        banknoteTypes = new ArrayList<>();
        banknoteTypesValues = new ArrayList<>();
        banknoteTypes.add(new BanknoteImplementation(FiveK));
        banknoteTypesValues.add(FiveK.denomination());
        banknoteTypes.add(new BanknoteImplementation(TwoK));
        banknoteTypesValues.add(TwoK.denomination());
        banknoteTypes.add(new BanknoteImplementation(OneK));
        banknoteTypesValues.add(OneK.denomination());
        banknoteTypes.add(new BanknoteImplementation(FiveH));
        banknoteTypesValues.add(FiveH.denomination());
        banknoteTypes.add(new BanknoteImplementation(TwoH));
        banknoteTypesValues.add(TwoH.denomination());
        banknoteTypes.add(new BanknoteImplementation(OneH));
        banknoteTypesValues.add(OneH.denomination());
        banknoteTypes.add(new BanknoteImplementation(HalfH));
        banknoteTypesValues.add(HalfH.denomination());
        banknoteTypes.add(new BanknoteImplementation(Ten));
        banknoteTypesValues.add(Ten.denomination());

    }

    @Override
    public List<Banknote> getAllBanknoteTypes() {
        return List.copyOf(banknoteTypes);
    }

    @Override
    public List<Integer> getAllBanknoteTypesValues() {
        return List.copyOf(banknoteTypesValues);
    }
}
