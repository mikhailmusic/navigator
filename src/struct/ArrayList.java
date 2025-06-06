package struct;


import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;

    public ArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public void add(T element) {
        ensureCapacity();
        array[size++] = element;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return (T) array[index];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size == array.length) {
            int newCapacity = array.length * 2;
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return get(0);
    }

    public T getLast() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return get(size - 1);
    }

    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    public void sort(Comparator<T> comparator) {
        mergeSort(0, size - 1, comparator);

    }

    private void mergeSort(int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int mid = low + (high - low) / 2;

            mergeSort(low, mid, comparator);
            mergeSort(mid + 1, high, comparator);

            merge(low, mid, high, comparator);
        }
    }

    private void merge(int low, int mid, int high, Comparator<? super T> comparator) {
        int n1 = mid - low + 1;
        int n2 = high - mid;

        Object[] left = new Object[n1];
        Object[] right = new Object[n2];

        System.arraycopy(array, low, left, 0, n1);
        System.arraycopy(array, mid + 1, right, 0, n2);

        int i = 0, j = 0, k = low;

        while (i < n1 && j < n2) {
            if (comparator.compare((T) left[i], (T) right[j]) <= 0) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        while (i < n1) {
            array[k++] = left[i++];
        }

        while (j < n2) {
            array[k++] = right[j++];
        }
    }

    public void addAll(Iterable<T> elements) {
        for (T element : elements) {
            add(element);
        }
    }

    public static <T> ArrayList<T> asList(T... a) {
        ArrayList<T> list = new ArrayList<>();
        for (T element : a) {
            list.add(element);
        }
        return list;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) array[currentIndex++];
        }
    }
}