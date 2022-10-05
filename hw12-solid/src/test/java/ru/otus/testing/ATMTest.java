package ru.otus.testing;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.ATM.ATM;
import ru.otus.ATM.ATMImplementation;
import ru.otus.cash.Banknote;
import ru.otus.cash.BanknoteDenomination;
import ru.otus.cash.BanknoteDenominationImplementation;
import ru.otus.cash.BanknoteImplementation;

import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.List;

public class ATMTest {
    private final static BanknoteDenomination FiveK = new BanknoteDenominationImplementation(5_000);
    private final static BanknoteDenomination TwoK = new BanknoteDenominationImplementation(2_000);
    private final static BanknoteDenomination OneK = new BanknoteDenominationImplementation(1_000);
    private final static BanknoteDenomination FiveH = new BanknoteDenominationImplementation(500);
    private final static BanknoteDenomination TwoH = new BanknoteDenominationImplementation(200);
    private final static BanknoteDenomination OneH = new BanknoteDenominationImplementation(100);
    private final static BanknoteDenomination HalfH = new BanknoteDenominationImplementation(50);
    private final static BanknoteDenomination Ten = new BanknoteDenominationImplementation(10);
    private final static BanknoteDenomination wrongCash = new BanknoteDenominationImplementation(2);
    private ATM atm = new ATMImplementation();
    private List<Banknote> banknotes = new ArrayList<>();

    @BeforeEach
    void amountOfMoney() {//testing putting some money
        int countOf5k = 1, countOf2k = 2, countOf1k = 3, countOf500 = 4, countOf200 = 5, countOf100 = 6, countOf50 = 7, countOf10 = 8;//summ = 16030, count = 36
        //int countOf5k = 1, countOf2k = 0, countOf1k = 0, countOf500 = 0, countOf200 = 0, countOf100 = 0, countOf50 = 0, countOf10 = 3;//summ = 5030, count = 4
        for (int i = 0; i < countOf5k; i++) {
            banknotes.add(new BanknoteImplementation(FiveK));
        }
        for (int i = 0; i < countOf2k; i++) {
            banknotes.add(new BanknoteImplementation(TwoK));
        }
        for (int i = 0; i < countOf1k; i++) {
            banknotes.add(new BanknoteImplementation(OneK));
        }
        for (int i = 0; i < countOf500; i++) {
            banknotes.add(new BanknoteImplementation(FiveH));
        }
        for (int i = 0; i < countOf200; i++) {
            banknotes.add(new BanknoteImplementation(TwoH));
        }
        for (int i = 0; i < countOf100; i++) {
            banknotes.add(new BanknoteImplementation(OneH));
        }
        for (int i = 0; i < countOf50; i++) {
            banknotes.add(new BanknoteImplementation(HalfH));
        }
        for (int i = 0; i < countOf10; i++) {
            banknotes.add(new BanknoteImplementation(Ten));
        }
        //how work
        atm.putMoney(banknotes);
    }

    @Test
    public void checkPutAndAmount() {
        Assertions.assertEquals(16030, atm.showAmountOfAvailableMoney());
    }


    @Test
    public void checkListOfWithWrongMoney() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> atm.putMoney(List.of(new BanknoteImplementation(wrongCash))));
    }

    @Test
    public void givingMoney() {
        List<Banknote> issuedMoney = atm.giveMoney(16020);
        Assertions.assertEquals(35, issuedMoney.size());
    }


}
