package utils;

public class DoublyLinkedList<T> {

    static class Node<T> {
        T val;
        Node<T> prev;
        Node<T> next;

        public Node(T val) {
            this.val = val;
        }
    }

    private Node<T> head;
    private Node<T> tail;

    public Node<T> insertAtHead(T data) {
        var newNode = new Node<>(data);

        if (head == null) {
            this.head = this.tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }

        return newNode;
    }

    public Node<T> insertAtTail(T data) {
        var newNode = new Node<>(data);

        if (tail == null) {
            this.head = this.tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }

        return newNode;
    }

    public void deleteNode(T val) {
        Node<T> current = head;

        while (current != null) {
            if (current.val.equals(val)) {
                if (current == head) {
                    head = current.next;

                    if (head != null) {
                        head.prev = null;
                    }
                } else if (current == tail) {
                    tail = current.prev;

                    if (tail != null) {
                        tail.next = null;
                    }
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
            } else {
                current = current.next;
            }
        }
    }
}
