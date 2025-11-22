package main.java.map;

public class HashMap<K, V> {
    class Node<K, V> {
        K key;
        V value;

        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private int size;
    private int capacity;
    private Node<K, V>[] data;

    public HashMap() {
        size = 0;
        capacity = 10;
        data = new Node[capacity];
    }

    public void put(K key, V value) {
        int hash = getHashKey(key);
        Node<K, V> head = data[hash];
        while (head != null) {
            if (head.key.equals(key)) {//Replace the value if key are the same
                head.value = value;
                return;
            }
            head = head.next;
        }

        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = data[hash];
        data[hash] = newNode;
        size++;

        if(((double) size / capacity) > 0.75) {
            resize();
        }
    }

    public V get(K key) {
        int hash = getHashKey(key);
        Node<K, V> head = data[hash];
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }

        return null;
    }

    public void delete(K key) {
        int hash = getHashKey(key);
        Node<K, V> prev = null;
        Node<K, V> head = data[hash];
        while (head != null) {
            if (head.key.equals(key)) {
                if (prev != null) {
                    prev.next = head.next;
                } else {
                    data[hash] = head.next;
                }
                head.next = null;
                size--;
                return;
            }
            prev = head;
            head = head.next;
        }
    }

    private int getHashKey(K key) {
        return key.hashCode() % capacity;
    }

    private void resize() {
        capacity *= 2;
        Node<K, V>[] oldData = data;
        data = new Node[capacity];
        size = 0;
        for(Node<K, V> node : oldData) {
            while (node != null) {
                put(node.key, node.value);
                node = node.next;
            }
        }
    }
}
