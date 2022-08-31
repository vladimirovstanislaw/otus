package ru.otus.var;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VarIntro {
    //var field;
    public static void main(String[] args) throws IOException {
        new VarIntro().test(5);
    }

    //private String test(var u) throws IOException{
    private String test(int u) throws IOException {
        //var error=null; //Тип не определен
        var example = "ValueStr";
        example = new String("ValueStr2");
        System.out.println("exmple: " + example);
        try (InputStream is = new FileInputStream("L11-java8/src/main/resources/lines.txt")) {

        }

        //тут is будет FileInputStream, а не интерфейсом InputStream
        try (var is = new FileInputStream("L11-java8/src/main/resources/lines.txt")) {

        }
        List<CharSequence> list = Arrays.asList("1", "2", "3", "4", new StringBuffer("5"));
        for (var str : list) {
            System.out.println(str);
        }

        var alist = new ArrayList<>();//?
        alist.add("123");
        alist.add(5);
        for (var k : alist) {
            System.out.println(k);
        }
        var StringList = new ArrayList<String>();//всё ясно

        var testString = "StringVal";
        if (testString instanceof String) {
            System.out.println("testString is String");
        }
        return "";
    }
}
