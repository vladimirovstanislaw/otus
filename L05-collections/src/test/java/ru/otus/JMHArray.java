package ru.otus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Thread)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class JMHArray {
    private static final int ARRAY_SIZE_MAX = 100000000;
    private static final int ARRAY_SIZE_INIT_CAPACITY = 10;
    private MyArrayInt myArr;
    private List<Integer> arrayList;

    public static void main(String[] args) throws Exception {
        new Runner((Options) new OptionsBuilder().include(JMHArray.class.getSimpleName()).forks(2).build()).run();
    }

    @Setup
    public void setup() throws Exception {
        myArr = new MyArrayInt(ARRAY_SIZE_INIT_CAPACITY);
        arrayList = new ArrayList<>(ARRAY_SIZE_INIT_CAPACITY);
    }

    @Benchmark
    public long myArrayInitTest() {
        for (int idx = 0; idx < ARRAY_SIZE_MAX; idx++) {
            myArr.setValue(idx, idx);
        }

        long summ = 0;
        for (int idx = 0; idx < ARRAY_SIZE_MAX; idx++) {
            summ += myArr.getValue(idx);
        }
        return summ;
    }

    @Benchmark
    public long ArrayListTest() {
        for (int idx = 0; idx < ARRAY_SIZE_MAX; idx++) {
            arrayList.add(idx, idx);
        }

        long summ = 0;
        for (int idx = 0; idx < ARRAY_SIZE_MAX; idx++) {
            summ += arrayList.get(idx);
        }
        return summ;
    }
}
