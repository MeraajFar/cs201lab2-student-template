import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    public SinglyLinkedList() {

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.getElement();
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e) {
        head = new Node<>(e, head);

        if (isEmpty()) {
            tail = head;
        }
        size++;
    }

    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) {
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()) {
            tail = null;
        }
        return answer;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap() {
        if (size <= 1) {
            return;
        }

        // Store references to the existing nodes.
        ArrayList<Node<E>> nodes = new ArrayList<>(size);
        Node<E> current = head;
        while (current != null) {
            nodes.add(current);
            current = current.getNext();
        }

        // Sort node references by their elements. We do NOT modify elements.
        ArrayList<Node<E>> sorted = new ArrayList<>(nodes);
        sorted.sort((a, b) -> a.getElement().compareTo(b.getElement()));

        // Map each node to the node containing its opposite-ranked value:
        // smallest <-> largest, second-smallest <-> second-largest, etc.
        IdentityHashMap<Node<E>, Node<E>> replacement = new IdentityHashMap<>();
        int n = sorted.size();
        for (int i = 0; i < n; i++) {
            replacement.put(sorted.get(i), sorted.get(n - 1 - i));
        }

        // Rebuild the linked-list order using the replacement nodes.
        head = replacement.get(nodes.get(0));
        current = head;

        for (int i = 1; i < n; i++) {
            Node<E> nextNode = replacement.get(nodes.get(i));
            current.setNext(nextNode);
            current = nextNode;
        }

        tail = current;
        tail.setNext(null);
    }

}
