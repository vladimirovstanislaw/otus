package ru.otus.functiona;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;
import java.util.function.Supplier;


public class Lambda {
    private String value;


    public static void main(String[] args) {
        Lambda lambda = new Lambda();
        String result = lambda.func(str -> str + "+mod", "testStr");
        System.out.println(result);

        Integer result2 = lambda.func(val -> val * 10, 5);
        System.out.println(result2);

        //Билдер экземпляров Lambda с инициализацией поля value константой
        Lambda l = lambda.func(lb -> {
            lb.value = "testValue";
            return lb;
        }, new Lambda());

        System.out.println(l.value);
        //int[] initValue - не поле инстанса или класса, но сохраняет своё значение между вызовами функций
        Supplier<Integer> closure = lambda.generator();//final только ссылка closure на lambda.generator(). Внутри у generator есть состояние, что противоречит фукнциональному програмиированию.
        System.out.println("1:" + closure.get());
        System.out.println("2:" + closure.get());
        System.out.println("3:" + closure.get());
    }

    private <T, R> R func(@NotNull Function<T, R> func, T param) {
        return func.apply(param);
    }

    @NotNull
    @Contract(pure = true)
    private Supplier<Integer> generator() {
        int[] initValue = {0};
        return () -> ++initValue[0];
    }
}
