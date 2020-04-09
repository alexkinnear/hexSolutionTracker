import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;


// Union by Size with Path Compression
public class UnionFind {
    int size;
    public int[] id; // root of the element
    public int[] treeSize; // size of the set (compare this for Union by size)

    UnionFind(int size) {
        this.size = size;
        id = new int[size+1];
        treeSize = new int[size+1];

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

        // path compression
        while (i != root) {
            int num = id[i];
            id[i] = num;
            i = num;
        }
        return root;
    }



    public ArrayList<Integer> getNeighbors(int num) {
        ArrayList<Integer> neighbors = new ArrayList<>();
//      Neighboring positions: x-1, x+1,x-11, x-10, x+10, x+11
        if (num - 1 >= 1) {
            neighbors.add(num-11);
        }
        if (num + 1 <= 121) {
            neighbors.add(num+1);
        }
        if (num - 11 >= 1) {
            neighbors.add(num-11);
        }
        if (num - 10 >= 1) {
            neighbors.add(num-10);
        }
        if (num + 10 <= 121) {
            neighbors.add(num+10);
        }
        if (num + 11 <= 121) {
            neighbors.add(num+11);
        }
        return neighbors;
    }

    public static void main(String[] args) {


        // Testing for Union and Find methods
        UnionFind UF = new UnionFind(100);
        int set1 = 2;           // All numbers divisible by 2
        int set2 = 3;           // All numbers divisible by 3 but not by 2
        int set3 = 5;           // All numbers divisible by 5 but not by 2 or 3
        int set4 = 7;           // All other numbers

        for (int i = 1; i <= UF.size; i++) {
            if (i % 2 == 0) {
                UF.Union(set1, i);
            }
            else if (i % 3 == 0) {
                UF.Union(set2, i);
            }
            else if (i % 5 == 0) {
                UF.Union(set3, i);
            }
            else {
                UF.Union(set4, i);
            }
        }

        ArrayList<Integer> nums1 = new ArrayList<>();
        ArrayList<Integer> nums2 = new ArrayList<>();
        ArrayList<Integer> nums3 = new ArrayList<>();
        ArrayList<Integer> nums4 = new ArrayList<>();
        for (int i = 1; i < UF.size; i++) {
            if (UF.find(i) == UF.find(set1)) {
                nums1.add(UF.find(set1));
            }
            else if (UF.find(i) == UF.find(set2)) {
                nums2.add(UF.find(set2));
            }
            else if (UF.find(i) == UF.find(set3)) {
                nums3.add(UF.find(set3));
            }
            else if (UF.find(i) == UF.find(set4)) {
                nums4.add(UF.find(set4));
            }
        }
        System.out.println(nums1);
        System.out.println(nums2);
        System.out.println(nums3);
        System.out.println(nums4);

        System.out.println();
        
        // This demonstrates path compression because every number points directly to its root
        // The root for set 1 is 4, for set 2 is 9, for set 3 is 25, and set 4 is 1
        System.out.println(Arrays.toString(UF.id));
    }
}
