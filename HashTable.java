import java.util.LinkedList;

public class HashTable {
    private int capacity;
    private LinkedList<Integer>[] buckets;

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        this.capacity = capacity;
        buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private int hash(int key) {
        return Math.abs(Integer.hashCode(key)) % capacity;
    }

    public void insert(int key) {
        int index = hash(key);
        if (!buckets[index].contains(key)) {
            buckets[index].add(key);
        }
    }

    public boolean search(int key) {
        int index = hash(key);
        return buckets[index].contains(key);
    }
}