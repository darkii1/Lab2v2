
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class MyArrayList<T> implements Iterable<T> {
    private int currMaxSize;
    private int size;
    private int FinalMaxSize = Integer.MAX_VALUE;
    private Object[] inArray;

    //Конструктор по умолчанию
    public MyArrayList() {
        inArray = new Object[10];
        currMaxSize = 10;
        size = 0;
    }

    //Конструктор с параметром размера листа

    public MyArrayList(int size) {
        if (size > 0) {
            inArray = new Object[size];
            this.currMaxSize = size;
            this.size = 0;
        } else {
            throw new IllegalArgumentException("Illegal size");
        }
    }

    //Конструктор копирования

    public MyArrayList(MyArrayList<T> array) {
        this.inArray = new Object[array.getSize()];
        System.arraycopy(array, 0, this.inArray, 0, array.getSize());
        this.size = array.getSize();
        this.currMaxSize = array.currMaxSize;

    }

    //Текущий размер массива
    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    //Проверка на принадлежность элемента массиву

    public boolean contains(T element) {
        return (indexOf(element) >= 0);
    }

    //Первое вхождение элемента в массив
    public int indexOf(T element) {
        for (int i = 0; i < this.size; i++) {
            if (element.equals(this.inArray[i])) return i;
        }
        return -1;
    }

    //Получение элемента с определенной позиции

    public T get(int position) {
        this.isValidIndex(position);
        return (T) this.inArray[position];
    }



    //Добавление элемента в конец массива

    public boolean add(T element) {
        this.growArrayIfNeed();
        this.inArray[size] = element;
        size++;
        return true;
    }

    //Добавление элемента по индексу

    public boolean add(int index, T element) {
        this.isValidIndexForAdd(index);
        this.growArrayIfNeed();
        this.inArray[index] = element;
        if (index == size) size++;
        return true;
    }

    //Удаление всех вхождений элемента из массива

    public boolean remove(T element) {
        while (this.contains(element)) {
            this.remove(this.indexOf(element));
        }
        return true;
    }

    //Удаление элемента по индексу

    public boolean remove(int index) {
        this.isValidIndex(index);
        for (int i = index; i < size - 1; i++) {
            this.inArray[i] = this.inArray[i + 1];
        }
        this.inArray[size - 1] = null;
        size--;
        return true;
    }

    //Проверка индекса
    private boolean isValidIndex(int index) {
        if (index >= this.size || index < 0)
            throw new IndexOutOfBoundsException("Wrong index");
        return true;
    }

    //Проверка индекса для добавления
    private boolean isValidIndexForAdd(int index) {
        if (index > this.size || index < 0)
            throw new IndexOutOfBoundsException("Wrong index");
        return true;
    }

    // Изменение размера массива
    private void growArrayIfNeed() {
        if (this.size == this.currMaxSize && this.currMaxSize * 2 <= this.FinalMaxSize) {
            T[] oldArray = (T[]) new Object[this.size];
            System.arraycopy(this.inArray, 0, oldArray, 0, this.size);
            int newMaxSize = this.currMaxSize * 2;
            this.inArray = new Object[newMaxSize];
            System.arraycopy(oldArray, 0, this.inArray, 0, this.size);
            this.currMaxSize = newMaxSize;
        }
    }



    //Возвращает итератор

    @Override
    public  Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

    //Класс-итератор для массива
    private class MyArrayListIterator implements Iterator<T> {
        private int pointer = -1;

        //Существует ли следующий элемент
        @Override
        public boolean hasNext() {
            if (this.pointer >= size - 1)
                return false;
            else
                return true;
        }

        //Возращает следующий элемент
        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("No next element");
            } else
                return (T) inArray[++this.pointer];
        }

        //Удаление выбранного элемента

        @Override
        public void remove() {
            if (this.pointer >= 0) {
                MyArrayList.this.remove(this.pointer);
                this.pointer--;
            } else {
                throw new ConcurrentModificationException();
            }
        }
    }

}
