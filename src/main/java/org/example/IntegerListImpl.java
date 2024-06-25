package org.example;

import org.example.exceptions.BadIndexException;
import org.example.exceptions.BadItemException;

public class IntegerListImpl implements IntegerList {
    private Integer[] integerList;
    private int size;

    public IntegerListImpl() {
        this.integerList = new Integer[0];
        this.size = 0;
    }

    public IntegerListImpl(Integer[] integerList) {
        this.integerList = integerList;
        this.size = integerList.length;
    }

    @Override
    public Integer add(Integer item) {
        if (item != null) {
            Integer[] temporaryList = integerList;
            integerList = new Integer[size + 1];
            size++;
            System.arraycopy(temporaryList, 0, integerList, 0, size - 1);
            integerList[size - 1] = item;
            return item;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public Integer add(int index, Integer item) {
        if (item != null) {
            if (index < size && index >= 0) {
                Integer[] temporaryList = integerList;
                integerList = new Integer[size + 1];
                size++;
                System.arraycopy(temporaryList, 0, integerList, 0, index);
                System.arraycopy(temporaryList, index, integerList, index + 1, size - index - 1);
                integerList[index] = item;
                return item;
            } else {
                throw new BadIndexException();
            }
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public Integer set(int index, Integer item) {
        if (item != null) {
            if (index < size && index >= 0) {
                integerList[index] = item;
                return item;
            } else {
                throw new BadIndexException();
            }
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public Integer remove(Integer item) {
        if (item != null) {
            for (int i = 0; i < size; i++) {
                if (integerList[i].equals(item)) {
                    Integer[] temporaryList = integerList;
                    integerList = new Integer[size - 1];
                    size--;
                    System.arraycopy(temporaryList, 0, integerList, 0, i);
                    System.arraycopy(temporaryList, i + 1, integerList, i, size - i);
                    return item;
                }
            }
            throw new BadItemException();
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public Integer remove(int index) {
        if (index < size && index >= 0) {
            Integer result = integerList[index];
            Integer[] temporaryList = integerList;
            integerList = new Integer[size - 1];
            size--;
            System.arraycopy(temporaryList, 0, integerList, 0, index);
            System.arraycopy(temporaryList, index + 1, integerList, index, size - index);
            return result;
        } else {
            throw new BadIndexException();
        }
    }

    @Override
    public boolean contains(Integer item) {
        if (item != null) {
            IntegerListImpl arr = new IntegerListImpl(this.integerList);
            arr.sort();
            return arr.binarySearch(item) != -1;

        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public int indexOf(Integer item) {
        if (item != null) {
            for (int i = 0; i < size; i++) {
                if (integerList[i].equals(item)) {
                    return i;
                }
            }
            return -1;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public int lastIndexOf(Integer item) {
        if (item != null) {
            for (int i = size - 1; i >= 0; i--) {
                if (integerList[i].equals(item)) {
                    return i;
                }
            }
            return -1;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public Integer get(int index) {
        if (index < size && index >= 0) {
            return integerList[index];
        } else {
            throw new BadIndexException();
        }
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList != null) {
            if (size != otherList.size()) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (!(integerList[i].equals(otherList.toArray()[i]))) {
                    return false;
                }
            }
            return true;
        } else {
            throw new NullPointerException();
        }
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
    public void clear() {
        integerList = new Integer[0];
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return integerList;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (int i = 0; i < size; i++) {
            result += integerList[i].hashCode();
        }
        return result + size;
    }

    private void sort() {
        if (this.size != 0) {
            for (int i = 0; i < this.size; i++) {
                Integer temp = this.integerList[i];
                int j = i;
                while (j > 0 && this.integerList[j - 1] >= temp) {
                    this.integerList[j] = this.integerList[j - 1];
                    j--;
                }
                this.integerList[j] = temp;
            }
        } else {
            throw new NullPointerException();
        }
    }

    private int binarySearch(Integer item) {
        int min = 0;
        int max = this.size - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item.equals(this.integerList[mid])) {
                return mid;
            }

            if (item < this.integerList[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return -1;
    }
}
