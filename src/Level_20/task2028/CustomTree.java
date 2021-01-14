package Level_20.task2028;

import java.io.Serializable;
import java.util.*;

/*
Построй дерево(1)
*/

public class CustomTree extends AbstractList<String> implements Serializable, Cloneable {

    Entry<String> root;

    int count = 0;

    public CustomTree() {
        root = new Entry<String>("0");
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean add(String elementName) {
        Entry<String> element = new Entry<>(elementName);
        ArrayDeque<Entry<String>> queue = new ArrayDeque<>();
        queue.addFirst(root);

        while (!queue.isEmpty()) {
            Entry<String> next = queue.pollFirst();
            if (next.isAvailableToAddChildren()) {
                if (next.availableToAddLeftChildren) {
                    next.leftChild = element;
                    next.availableToAddLeftChildren = false;
                } else {
                    next.rightChild = element;
                    next.availableToAddRightChildren = false;
                }
                element.parent = next;
                count++;
                return true;
            } else {
                queue.addLast(next.leftChild);
                queue.addLast(next.rightChild);
            }
        }
        return true;
    }

    public String getParent(String elementName) {

        ArrayDeque<Entry<String>> queue = new ArrayDeque<>();
        queue.addFirst(root);
        while (!queue.isEmpty()) {
            Entry<String> next = queue.pollFirst();
            if(next.elementName.equals(elementName)){
                return next.parent.elementName;
            }
            if(!next.availableToAddLeftChildren) queue.addLast(next.leftChild);
            if(!next.availableToAddRightChildren) queue.addLast(next.rightChild);
        }

        return null;
    }

    static class Entry<T> implements Serializable {

        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }
}
