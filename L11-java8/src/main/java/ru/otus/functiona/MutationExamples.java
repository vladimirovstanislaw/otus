package ru.otus.functiona;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MutationExamples {
    private List<TestObjectMutable> listMute = Arrays.asList(new TestObjectMutable(1), new TestObjectMutable(2), new TestObjectMutable(3));
    private List<TestObjectUnMutable> listUnMute = Arrays.asList(
            new TestObjectUnMutable(1),
            new TestObjectUnMutable(2),
            new TestObjectUnMutable(3));

    public static void main(String[] args) {
        new MutationExamples().mutableUnMutable();
    }

    private void mutableUnMutable() {

        //собираем новую коллекцию из старой
        //ОЧЕНЬ Плохая идея - менять элементы исходной коллекции.
        List<TestObjectMutable> newList = listMute.stream().map(el -> el.updateValue(-1))
                .collect(Collectors.toList());
        System.out.println(listMute);
        System.out.println(newList);
        //Лучше создавать новый экземпляр, если надо поменять существующий.
        List<TestObjectUnMutable> newList2 = listUnMute.stream().map(el -> el.updateValue(-1))
                .collect(Collectors.toList());
        System.out.println(listUnMute);
        System.out.println(newList2);

    }

}
