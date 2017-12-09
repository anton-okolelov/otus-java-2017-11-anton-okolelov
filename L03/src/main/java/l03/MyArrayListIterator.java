package l03;

import java.util.ListIterator;

public class MyArrayListIterator<T> implements ListIterator<T> {

    private final Object[] storage;
    private final int size;
    private int cursor = 0;
    private int lastReturnedIndex;

    public MyArrayListIterator(Object[] storage, int size) {
        this.size = size;
        this.storage = storage;
    }

    @Override
    public boolean hasNext() {
        return cursor < size;
    }

    @Override
    public T next() {
        lastReturnedIndex = cursor;
        return (T) storage[cursor++];
    }

    @Override
    public boolean hasPrevious() {
        return cursor > 0;
    }

    @Override
    public T previous() {
        lastReturnedIndex = cursor;
        return (T) storage[cursor--];
    }

    @Override
    public int nextIndex() {
        return cursor;
    }

    @Override
    public int previousIndex() {
        return cursor - 1;
    }

    @Override
    public void remove() {

    }

    @Override
    public void set(T t) {
        storage[lastReturnedIndex] = t;
    }

    @Override
    public void add(T t) {

    }
}
