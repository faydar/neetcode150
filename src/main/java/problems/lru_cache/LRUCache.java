package problems.lru_cache;

import java.util.HashMap;
import java.util.Map;

/*
 * Similar to LinkedHashMap
 */
class LRUCache {

    int capacity;
    DoublyLinkedList<Integer> ll;
    Map<Integer, DoublyLinkedList.Node<Integer>> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.ll = new DoublyLinkedList<>();
        this.cache = new HashMap<>();
    }

    public int get(int key) {
        var node = cache.get(key);

        if (node != null) {
            ll.deleteNode(node);
            ll.insertAtHead(node);
            return node.val;
        }

        return -1;
    }

    public void put(int key, int value) {
        DoublyLinkedList.Node<Integer> node = cache.get(key);

        if (node != null) {
            ll.deleteNode(node);
        }

        var newNode = new DoublyLinkedList.Node<>(key, value);
        cache.put(key, newNode);
        ll.insertAtHead(newNode);

        if (cache.size() == capacity + 1) {
            var t = ll.deleteTail();
            cache.remove(t.key);
        }
    }

    static class DoublyLinkedList<T> {

        static class Node<T> {
            T key;
            T val;
            Node<T> prev;
            Node<T> next;

            public Node(T key, T val) {
                this.key = key;
                this.val = val;
            }
        }

        private Node<T> head;
        private Node<T> tail;

        public Node<T> insertAtHead(Node<T> newNode) {
            if (head == null) {
                this.head = this.tail = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }

            return newNode;
        }

        public Node<T> insertAtTail(Node<T> newNode) {
            if (tail == null) {
                this.head = this.tail = newNode;
            } else {
                newNode.prev = tail;
                tail.next = newNode;
                tail = newNode;
            }

            return newNode;
        }

        public void deleteNode(Node<T> node) {
            if (node.key == head.key) {
                head = node.next;

                if (head != null) {
                    head.prev = null;
                }
            } else if (node.key == tail.key) {
                tail = node.prev;

                if (tail != null) {
                    tail.next = null;
                }
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
        }

        public Node<T> deleteTail() {
            var t = this.tail;
            this.deleteNode(this.tail);
            return t;
        }
    }

    public static void main(String[] args) {
        in1();
        return;
    }

    public static void in1() {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lRUCache.get(1)); // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2)); // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1)); // return -1 (not found)
        System.out.println(lRUCache.get(3)); // return 3
        System.out.println(lRUCache.get(4)); // return 4
    }

    public static void in2() {
        LRUCache lRUCache = new LRUCache(2);
        System.out.println(lRUCache.get(2));
        lRUCache.put(2, 6);
        System.out.println(lRUCache.get(1));
        lRUCache.put(1, 5);
        lRUCache.put(1, 2);
        System.out.println(lRUCache.get(1));
        System.out.println(lRUCache.get(2));
    }
}