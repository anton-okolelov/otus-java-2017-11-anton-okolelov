package l03;

import java.util.*;

public class MyArrayList<T> implements List<T> {

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

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return Arrays.stream(storage).limit(size).anyMatch(item -> item.equals(o));
    }

    @Override
    public Iterator<T> iterator() {
        return listIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        add(size, t);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }


    private void increaseCapacityIfNeeded(int requiredSize) {
        if (requiredSize > storage.length) {
            storage = Arrays.copyOf(storage, storage.length * 2);
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
        increaseCapacityIfNeeded(size + 1);
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = element;
        size++;
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

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new MyArrayListIterator<>(storage, size);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    private class MyArrayListIterator<E> implements ListIterator<E> {

        private final Object[] storage;
        private final int size;
        private int cursor = 0;
        private int lastReturnedIndex;

        MyArrayListIterator(Object[] storage, int size) {
            this.size = size;
            this.storage = storage;
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public E next() {
            lastReturnedIndex = cursor;
            return (E) storage[cursor++];
        }

        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }

        @Override
        public E previous() {
            lastReturnedIndex = cursor;
            return (E) storage[cursor--];
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
        public void set(E t) {
            storage[lastReturnedIndex] = t;
        }

        @Override
        public void add(E t) {

        }
    }

}
