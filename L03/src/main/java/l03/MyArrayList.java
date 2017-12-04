package l03;

import java.util.*;

public class MyArrayList<T> extends AbstractList<T> {

    private final int DEFAULT_INITIAL_CAPACITY = 10;
    private int size = 0;
    private Object[] storage;

    MyArrayList() {
        storage = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }


    private void increaseCapacityIfNeeded(int requiredSize) {
        if (requiredSize > storage.length) {
            Object[] newStorage = new Object[storage.length * 2];
            System.arraycopy(storage, 0, newStorage, 0, storage.length);
            storage = newStorage;
        }
    }


    @Override
    @SuppressWarnings({"unchecked"})
    public T get(int index) {
        return (T) this.storage[index];
    }

    @Override
    public T set(int index, T element) {
        this.storage[index] = element;
        return element;
    }

    @Override
    public void add(int index, T element) {
        increaseCapacityIfNeeded(index + 1);
        storage[index] = element;
        if (index > size - 1) {
            size++;
        }
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public T remove(int index) {
        Object[] newStorage = new Object[storage.length];
        if (index > 0) {
            System.arraycopy(storage, 0, newStorage, 0, index);
        }
        if (index < storage.length - 1) {
            System.arraycopy(storage, index + 1, newStorage, index, storage.length - index - 1);
        }
        T removedObject = (T) storage[index];
        storage = newStorage;
        size--;
        return removedObject;
    }

}
