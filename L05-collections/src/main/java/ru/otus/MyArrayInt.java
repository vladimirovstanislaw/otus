package ru.otus;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MyArrayInt implements AutoCloseable {
    private final Unsafe unsafe;
    private final long elemntSizeBytes = Integer.SIZE / 8;
    private long size;
    private long arrayBeginIdx;

    public MyArrayInt(long size) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        this.size = size;
        Constructor<Unsafe> unsafeConstructor = Unsafe.class.getDeclaredConstructor();
        unsafeConstructor.setAccessible(true);
        unsafe = unsafeConstructor.newInstance();
        arrayBeginIdx = unsafe.allocateMemory(this.size * elemntSizeBytes);
    }

    public void setValue(long index, int value) {
        if (index >= size) {
            this.size = Math.max(this.size * 2 + 1, index + 1);
            arrayBeginIdx = unsafe.reallocateMemory(arrayBeginIdx, this.size * elemntSizeBytes);
        }
        unsafe.putInt(calcIndex(index), value);
    }

    private long calcIndex(long index) {
        return arrayBeginIdx + index * this.elemntSizeBytes;
    }

    public int getValue(long index) {
        return unsafe.getInt(calcIndex(index));
    }

    public long getSize() {
        return size;
    }


    @Override
    public void close() throws Exception {
        unsafe.freeMemory(arrayBeginIdx);
    }
}
