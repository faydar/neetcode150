package utils;

// dequeue implementation using doubly linked list
public class Dequeue<T> {

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data) {
            this.data = data;
        }
    }

    Node<T> head;
    Node<T> tail;
    int size;

    public void pushFront(T elem) {
        var n = new Node<>(elem);
        if (isEmpty()) {
            this.head = this.tail = n;
        } else {
            n.next = this.head;
            this.head.prev = n;
            this.head = n;
        }

        size++;
    }

    public void pushBack(T elem) {
        var n = new Node<>(elem);
        if (isEmpty()) {
            this.head = this.tail = n;
        } else {
            this.tail.next = n;
            n.prev = this.tail;
            this.tail = n;
        }

        size++;
    }

    public T popFront() {
        if (isEmpty()) {
            throw new RuntimeException("Dequeue is empty");
        }

        var res = this.head.data;
        this.head = this.head.next;

        if (this.head == null) {
            this.tail = null; // 1 node case
        } else {
            this.head.prev = null;
        }

        size--;
        return res;
    }

    public T popBack() {
        if (isEmpty()) {
            throw new RuntimeException("Dequeue is empty");
        }

        var res = this.tail.data;
        this.tail = this.tail.prev;

        if (this.tail == null) {
            this.head = null; // 1 node case
        } else {
            this.tail.next = null;
        }

        size--;
        return res;
    }

    public T peekFront() {
        if (isEmpty()) {
            throw new RuntimeException("Deque is empty");
        }
        return this.head.data;
    }

    public T peekBack() {
        if (isEmpty()) {
            throw new RuntimeException("Deque is empty");
        }
        return this.tail.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        Dequeue<Integer> deque = new Dequeue<>();
        deque.pushBack(10);
        deque.pushFront(5);
        deque.pushBack(15);
        deque.pushFront(2);

        System.out.println("Front: " + deque.peekFront()); // 2
        System.out.println("Back: " + deque.peekBack()); // 15

        System.out.println("Removed from front: " + deque.popFront()); // 2
        System.out.println("Removed from back: " + deque.popBack()); // 15

        System.out.println("Front after removals: " + deque.peekFront()); // 5
        System.out.println("Back after removals: " + deque.peekBack()); // 10
    }
}
