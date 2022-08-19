package ru.otus.annotations.usage;

import ru.otus.annotations.Email;
import ru.otus.annotations.Immutable;
import ru.otus.annotations.NonEpmpty;
import ru.otus.annotations.Unfinished;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"ResultOfMethodCallIgnored", "UnusedReturnValue"})
@Deprecated(since = "2018-04")
@Unfinished(priority = Unfinished.Priority.LOW, value = "pew pew pew", lastChanged = "2018-01", lastChangedBy = "Yokku", changedBy = {"John", "NotJohn"})
public class Main<T extends @Email String> {
    private <T> void print(T t) {
    }

    @SuppressWarnings({"unused", "FieldCanBeLocal"})
    private final int size;

    @Deprecated
    public Main(int size) {
        this.size = size;
    }

    @Deprecated
    public static void main(@Immutable String... args) {
        @Immutable ArrayList<String> list = new @NonEpmpty ArrayList<>();

        Main.<@Email ArrayList<String>>cast2(list);
        Main.<@Email ArrayList<String>>cast(list);
    }

    private static <TayBay extends Object> void cast2(TayBay e) {

    }

    private static <@Immutable E> E cast(Object o) {
        return (E) o;
    }

    private <G> void print(T t) {
        G g = (G) t;

    }
}
