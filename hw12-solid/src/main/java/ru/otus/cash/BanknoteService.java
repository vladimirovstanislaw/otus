package ru.otus.cash;

import java.util.List;

public interface BanknoteService {
    List<Banknote> getAllBanknoteTypes();

    List<Integer> getAllBanknoteTypesValues();
}
