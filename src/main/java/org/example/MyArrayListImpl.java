package org.example;

import org.example.exception.ListIndexOutOfBoundsException;

import java.util.Arrays;

public class MyArrayListImpl<T> implements MyArrayList<T> {
    /**
     * массив
     */
    private T[] data;

    /**
     * количество заполненных элем. массива, также хранит индекс следующей свободной ячейки
     */
    private int currentSize;

    /**
     * Конструктор списка заданного размера состоящий из null
     *
     * @param size
     */
    public MyArrayListImpl(int size) {
        data = (T[]) new Object[size];
        currentSize = 0;
    }

    /**
     * Метод добавления элемента
     * Если для нового элем.нет места, увеличиваем массив,
     * добавляем элемент в конец списка, количество элементов увеличивается на 1,
     * возвращаем добавленный элемент
     *
     * @param element
     * @return добавленный элемент
     */
    @Override
    public T add(T element) {
        if (currentSize == data.length) {
            lengthen();
        }
        data[currentSize] = element;
        currentSize++;
        return element;
    }

    /**
     * Метод добавления элемента по указанному индексу
     * Проверяем, что индекс в пределах массива,
     * если нет пустых ячеек, расширяем массив
     * если нужный индекс свободен, записываем туда значение и увеличиваем currentSize на 1
     * если, нужный индекс занят значением, начиная с конца массива перезаписываем все значения
     * со сдвигом на одну ячейку вправо до достижения желаемого индекса, увеличиваем currentSize на 1
     * и присваиваем значение по освободившемуся индексу
     *
     * @param index
     * @param element
     * @return
     */
    @Override
    public T add(int index, T element) {
        checkBounds(index);
        if (currentSize >= data.length) {
            lengthen();
        }
        if (currentSize < data.length) {
            if (index < currentSize) {
                System.arraycopy(data, index, data, index + 1, currentSize - index);
            }
            currentSize++;
            return data[index] = element;
        } else {
            throw new ListIndexOutOfBoundsException("Список полон!");
        }

    }

    /**
     * Метод удлиннения массива в 1,5 раза
     * создаем массив нового размера - в 1,5 раза больше исходного по длине
     * и перезаписываем все элементы исходного массива в новый длинный массив
     */
    private void lengthen() {
        T[] data = (T[]) new Object[(int) (this.data.length * 1.5)];
        System.arraycopy(this.data, 0, data, 0, this.data.length);
        this.data = data;
    }

    /**
     * Метод получения объекта по индексу
     * Передаем в параметры индекс желаемого объекта,
     * проверяем, что индекс в пределах массива
     * получаем значение по индексу
     *
     * @param index
     * @return значение по индексу
     */
    @Override
    public T get(int index) {
        checkBounds(index);
        return data[index];
    }

    /**
     * Метод удаления элемента по индексу
     * Это метод обратный методу добавления по индексу
     *
     * @param index
     * @return
     */
    @Override
    public T remove(int index) {
        checkBounds(index);
        T result = data[index];
        for (int i = index + 1; i < currentSize; i++) {
            data[i - 1] = data[i];
        }
        currentSize--;
        return result;
    }

    /**
     * Метод очистки всей коллекции
     * заменяет все элементы на null, количество заполненных элем. - 0
     */
    @Override
    public void clear() {
        Arrays.fill(data, 0, currentSize, null);
        currentSize = 0;
    }

    /**
     *
     * @return
     */
    @Override
    public T sort() {
        return null;
    }

    /**
     * Метод проверки индекса на принадлежность массиву
     * Если индекс за пределами массива или отрицательный, сообщает об ошибке
     *
     * @param index
     */
    private void checkBounds(int index) {
        if (index < 0 || index >= currentSize) {
            throw new ListIndexOutOfBoundsException();
        }
    }

    /**
     * Метод поиска элемента
     *
     * @param element
     * @return индекс элемента или (-1) если не найден
     */
    public int indexOf(T element) {
        for (int i = 0; i < currentSize; i++) {
            if (data[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return currentSize;
    }
}
