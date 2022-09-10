package ru.otus.functiona;

import java.util.Locale;
import java.util.Optional;

public class MonadExample {
    public static void main(String[] args) {
        var monadExample = new MonadExample();
        String result = monadExample.fucntion("str");
        System.out.println(result);

        result = monadExample.fucntion(null);
        System.out.println(result);

        result = monadExample.functionWrong(null);
        System.out.println(result);
    }
    private String fucntion(String str) {
        Optional<String> optional = Optional.ofNullable(str);
        optional.stream().map(val -> "!" + val.toUpperCase(Locale.ROOT)).forEach(System.out::println);
        return optional.map(param -> param + "addStr").orElse("param is NULL");
    }

    private String functionWrong(String str) {
        Optional<String> optional = Optional.ofNullable(str);
        if (optional.isPresent()) {
            return optional.get() + "+addStr";

        }
        return "param is NULL";
    }
}
