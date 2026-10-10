
import java.util.LinkedList;

public class HashTable {
    private final LinkedList<Integer>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "Capacity must be positive"
            );
        }

        buckets = (LinkedList<Integer>[])
                new LinkedList<?>[capacity];

        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private int hash(int key) {
        return Math.floorMod(
                Integer.hashCode(key),
                buckets.length
        );
    }

    public void insert(int key) {
        LinkedList<Integer> bucket = buckets[hash(key)];

        if (!bucket.contains(key)) {
            bucket.add(key);
            size++;
        }
    }

    public boolean search(int key) {
        return buckets[hash(key)].contains(key);
    }

    public boolean delete(int key) {
        boolean removed =
                buckets[hash(key)].remove(Integer.valueOf(key));

        if (removed) {
            size--;
        }

        return removed;
    }

    public int size() {
        return size;
    }
}
