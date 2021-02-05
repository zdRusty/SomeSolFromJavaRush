package Level_20.task2028;

import java.io.Serializable;
import java.util.*;

/*
Построй дерево(5)
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

        if (!isAvailableToAdd()) {
            recoverAbleToAdd();
        }

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
                if (next.leftChild != null) queue.addLast(next.leftChild);
                if (next.rightChild != null) queue.addLast(next.rightChild);
            }
        }
        return false;
    }

    public String getParent(String elementName) {
        ArrayDeque<Entry<String>> queue = new ArrayDeque<>();
        queue.addFirst(root);
        while (!queue.isEmpty()) {
            Entry<String> next = queue.pollFirst();
            if (next.elementName.equals(elementName)) {
                return next.parent.elementName;
            }
            if (!next.availableToAddLeftChildren && next.leftChild != null) queue.addLast(next.leftChild);
            if (!next.availableToAddRightChildren && next.rightChild != null) queue.addLast(next.rightChild);
        }
        return null;
    }

    @Override
    public boolean remove(Object o) {
        try {
            String elementName = (String) o;
            ArrayDeque<Entry<String>> queue = new ArrayDeque<>();
            queue.addFirst(root);

            while (!queue.isEmpty()) {
                Entry<String> next = queue.pollFirst();
                if (next.elementName.equals(elementName)) {

                    if (next.parent.leftChild != null && next.parent.leftChild.elementName.equals(elementName)) {
                        next.parent.leftChild = null;

                    }
                    if (next.parent.rightChild != null && next.parent.rightChild.elementName.equals(elementName)) {
                        next.parent.rightChild = null;
                    }
                    count = count - childrenCount(next);
                    return true;
                }
                if (!next.availableToAddLeftChildren && next.leftChild != null) queue.addLast(next.leftChild);
                if (!next.availableToAddRightChildren && next.rightChild != null) queue.addLast(next.rightChild);
            }

        } catch (ClassCastException e) {
            throw new UnsupportedOperationException();
        }
        return false;
    }

    private int childrenCount(Entry<String> element) {

        int numOfChildren = 0;
        ArrayDeque<Entry<String>> queue = new ArrayDeque<>();
        queue.addFirst(element);
        while (!queue.isEmpty()) {
            Entry<String> next = queue.pollFirst();
            numOfChildren++;
            if (!next.availableToAddLeftChildren && next.leftChild != null) queue.addLast(next.leftChild);
            if (!next.availableToAddRightChildren && next.rightChild != null) queue.addLast(next.rightChild);
        }
        return numOfChildren;
    }

    private boolean isAvailableToAdd() {
        ArrayDeque<Entry<String>> queue = new ArrayDeque<>();
        queue.addFirst(root);
        while (!queue.isEmpty()) {
            Entry<String> next = queue.pollFirst();
            if (next.isAvailableToAddChildren()) {
                return true;
            }
            if (!next.availableToAddLeftChildren && next.leftChild != null) queue.addLast(next.leftChild);
            if (!next.availableToAddRightChildren && next.rightChild != null) queue.addLast(next.rightChild);
        }
        return false;
    }

    private void recoverAbleToAdd() {
        ArrayDeque<Entry<String>> queue = new ArrayDeque<>();
        queue.addFirst(root);
        while (!queue.isEmpty()) {
            Entry<String> next = queue.pollFirst();
            if (next.leftChild == null) {
                next.availableToAddLeftChildren = true;
            }
            if (next.rightChild == null) {
                next.availableToAddRightChildren = true;
            }
            if (!next.availableToAddLeftChildren && next.leftChild != null) queue.addLast(next.leftChild);
            if (!next.availableToAddRightChildren && next.rightChild != null) queue.addLast(next.rightChild);
        }
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