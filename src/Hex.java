import java.awt.*;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hex {

    public static final String RED = "\033[0;31m";     // RED
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String BLACK = "\033[0;30m";   // BLACK

    private static void printBoard(UnionFind board, ArrayList p1, ArrayList p2) {
        int count = 1;
        StringBuilder indent = new StringBuilder();
        for (int j = 1; j <= 11; j++) {
            System.out.print(indent);
            for (int i = 1; i <= 11; i++) {
                if (p1.contains(count)) {
                    System.out.print(BLUE + "B" + BLACK);
                }
                else if (p2.contains(count)) {
                    System.out.print(RED + "R"+ BLACK);
                }
                else System.out.print(0);
                System.out.print(" ");
                count++;
            }
            indent.append(" ");
            System.out.println();
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        String filename = "moves.txt";
        Scanner fileScanner = new Scanner(new File("data/"+filename));
        UnionFind  board = new UnionFind(121);
        ArrayList<Integer> p1 = new ArrayList<>();
        ArrayList<Integer> p2 = new ArrayList<>();
        int turn = 1;

        // read file and separate into p1 and 2
        while (fileScanner.hasNextInt()) {
            if (turn == 1) {
                p1.add(fileScanner.nextInt());
                turn++;
            }
            else {
                p2.add(fileScanner.nextInt());
                turn--;
            }
        }
        for (int i = 0; i < p1.size(); i++) {
            if (i+1 < p1.size()) {
                board.Union(p1.get(i), p1.get(i+1));
            }
        }
        for (int i = 0; i < p2.size(); i++) {
            if (i+1 < p2.size()) {
                board.Union(p2.get(i), p2.get(i+1));
            }
        }
        printBoard(board, p1, p2);
    }
}
