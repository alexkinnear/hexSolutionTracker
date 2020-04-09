import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hex {

    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    public static final String RESET = "\u001B[0m";

    private static void printBoard(ArrayList p1, ArrayList p2) {
        int count = 1;
        StringBuilder indent = new StringBuilder();
        for (int j = 1; j <= 11; j++) {
            System.out.print(indent);
            for (int i = 1; i <= 11; i++) {
                if (p1.contains(count)) {
                    System.out.print(BLUE + "B" + RESET);
                }
                else if (p2.contains(count)) {
                    System.out.print(RED + "R"+ RESET);
                }
                else System.out.print(0);
                System.out.print(" ");
                count++;
            }
            indent.append(" ");
            System.out.println();
        }
    }

    private static void initializeEdges(UnionFind board) {
        // 122 = TOP
        // 123 = BOTTOM
        // 124 = LEFT
        // 125 = RIGHT
        for (int i = 1; i < board.size; i++) {
            if (i <= 11) board.Union(122, i);
            if (i >= 111 && i <= 121) board.Union(123, i);
            if ((i-1) % 11 == 0 && i > 1 && i < 121) board.Union(124, i);
            if (i % 11 == 0 && i > 11 && i < 121) board.Union(125, i);
        }
    }



    public static void main(String[] args) throws FileNotFoundException {
        String filename = "moves2.txt";
        Scanner fileScanner = new Scanner(new File("data/"+filename));
        UnionFind board = new UnionFind(126);
        initializeEdges(board);

        ArrayList<Integer> p1 = new ArrayList<>();
        ArrayList<Integer> p2 = new ArrayList<>();
        int turn = 1;

        // read file and separate into p1 and p2
        while (fileScanner.hasNextInt()) {
            if (turn == 1) {                        // player 1 (Blue)
                p1.add(fileScanner.nextInt());
                turn++;
            }
            else {                                  // player 2 (Red)
                p2.add(fileScanner.nextInt());
                turn--;
            }
        }

        for (int i = 0; i < p1.size(); i++) {
            for (int j = 0; j < p1.size(); j++) {
                if (board.getNeighbors(p1.get(i)).contains(p1.get(j))) {
                    board.Union(p1.get(i), p1.get(j));
                }
            }
        }
        for (int i = 0; i < p2.size(); i++) {
            for (int j = 0; j < p2.size(); j++) {
                if (board.getNeighbors(p2.get(i)).contains(p2.get(j))) {
                    board.Union(p2.get(i), p2.get(j));
                }
            }
        }

        int moves = p1.size() + p2.size();

        if (board.find(122) == board.find(123)) System.out.println("- - - - > Red Wins after " + moves + " moves! Here is the final board.");
        if (board.find(124) == board.find(125)) System.out.print("- - - - - > Blue Wins after " + moves + " moves! Here is the final board.");


        printBoard(p1, p2);
    }
}
