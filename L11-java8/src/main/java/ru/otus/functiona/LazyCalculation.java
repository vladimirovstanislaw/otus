package ru.otus.functiona;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class LazyCalculation {
    public static void main(String[] args) {
        var item = new LazyCalculation();
        item.doSmth(item::veryHeavyDataRequest);//1.передается ссылка на вызов метода veryHeavyDataRequest
    }

    public void doSmth(Supplier<List<String>> supplier) {//поставщик
        boolean variant = false;
        if (variant) {
            var data = supplier.get();//2. Но вызывается только когда нам нужен List<String>
            //data нам нужна
        } else {
            //data не нужна
        }
    }

    public List<String> veryHeavyDataRequest() {
        //really heavy
        return new ArrayList<String>();
    }
}
