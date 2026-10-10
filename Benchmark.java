
import java.util.Random;

public class Benchmark {
    private static final int[] SIZES = {100, 1000, 10000};
    private static final int RUNS = 5;

    public static void main(String[] args) {
        System.out.printf(
                "%-12s | %8s | %14s | %14s | %14s%n",
                "Structure", "Elements",
                "Insert (us)", "Search (us)", "Delete (us)"
        );

        System.out.println(
                "----------------------------------------------------------------------------"
        );

        for (int size : SIZES) {
            int[] data = createData(size, 12345L + size);

            benchmarkBST(data);
            benchmarkHashTable(data);

            System.out.println(
                    "----------------------------------------------------------------------------"
            );
        }
    }

    private static int[] createData(int size, long seed) {
        int[] data = new int[size];

        for (int i = 0; i < size; i++) {
            data[i] = i * 2 + 1;
        }

        Random random = new Random(seed);

        // Shuffle to avoid inserting sorted data into the BST.
        for (int i = data.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            int temp = data[i];
            data[i] = data[j];
            data[j] = temp;
        }

        return data;
    }

    private static void benchmarkBST(int[] data) {
        long insertTotal = 0;
        long searchTotal = 0;
        long deleteTotal = 0;

        for (int run = 0; run < RUNS; run++) {
            BST bst = new BST();

            long start = System.nanoTime();
            for (int value : data) {
                bst.insert(value);
            }
            insertTotal += System.nanoTime() - start;

            start = System.nanoTime();
            for (int value : data) {
                bst.search(value);
            }
            searchTotal += System.nanoTime() - start;

            start = System.nanoTime();
            for (int value : data) {
                bst.delete(value);
            }
            deleteTotal += System.nanoTime() - start;
        }

        printResult("BST", data.length,
                insertTotal, searchTotal, deleteTotal);
    }

    private static void benchmarkHashTable(int[] data) {
        long insertTotal = 0;
        long searchTotal = 0;
        long deleteTotal = 0;

        for (int run = 0; run < RUNS; run++) {
            HashTable table =
                    new HashTable(Math.max(10, data.length * 2));

            long start = System.nanoTime();
            for (int value : data) {
                table.insert(value);
            }
            insertTotal += System.nanoTime() - start;

            start = System.nanoTime();
            for (int value : data) {
                table.search(value);
            }
            searchTotal += System.nanoTime() - start;

            start = System.nanoTime();
            for (int value : data) {
                table.delete(value);
            }
            deleteTotal += System.nanoTime() - start;
        }

        printResult("Hash Table", data.length,
                insertTotal, searchTotal, deleteTotal);
    }

    private static void printResult(
            String name,
            int size,
            long insertNs,
            long searchNs,
            long deleteNs
    ) {
        double insertUs =
                insertNs / (double) RUNS / 1000.0;
        double searchUs =
                searchNs / (double) RUNS / 1000.0;
        double deleteUs =
                deleteNs / (double) RUNS / 1000.0;

        System.out.printf(
                "%-12s | %8d | %14.2f | %14.2f | %14.2f%n",
                name, size, insertUs, searchUs, deleteUs
        );
    }
}
