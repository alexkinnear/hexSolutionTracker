import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// Union by Size with Path Compression
public class UnionFind {
    int size;
    public int[] id;
    public int[] treeSize;

    UnionFind(int size) {
        this.size = size;
        id = new int[size];
        treeSize = new int[size];

        // Initialize the elements to be their own roots (trees of size 1)
        for (int i = 0; i < size; i++) {
            id[i] = i;
            treeSize[i] = 1;
        }

    }

    // Union by size
    public void Union(int a, int b) {
        int num1 = find(a);
        int num2 = find(b);

        if (num1 == num2) return;   // same root, nothing happens

        if (treeSize[num1] > treeSize[num2]) {
            treeSize[num1] += treeSize[num2];
            id[num2] = id[num1];
        }
        else {
            treeSize[num2] += treeSize[num1];
            id[num1] = id[num2];
        }


    }

    // Find element and return root with path compression along the way
    public int find(int i) {
        int root = i;
        while (root != id[root]) {
            root = id[root];
        }

        while (i != root) {
            int num = id[i];
            id[i] = num;
            i = num;
        }
        return root;
    }

    public static void main(String[] args) throws FileNotFoundException {


    }
}
