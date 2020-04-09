import java.io.FileNotFoundException;
import java.util.ArrayList;


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
//        return id[i];
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



    public ArrayList<Integer> getNeighbors(int num) {
        ArrayList<Integer> neighbors = new ArrayList<>();
//        x-1, x+1,x-11, x-10, x+10, x+11
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

    public static void main(String[] args) throws FileNotFoundException {
        int NumElements = 128;
        int NumInSameSet = 16;

        UnionFind ds = new UnionFind( NumElements );
        int set1, set2;

        for( int k = 1; k < NumInSameSet; k *= 2 )
        {
            for( int j = 0; j + k < NumElements; j += 2 * k )
            {
                set1 = ds.find( j );
                set2 = ds.find( j + k );
                ds.Union( set1, set2 );
            }
        }

        for( int i = 0; i < NumElements; i++ )
        {
            System.out.print( ds.find( i )+ "*" );
            if( i % NumInSameSet == NumInSameSet - 1 )
                System.out.println( );
        }
        System.out.println( );

    }
}
