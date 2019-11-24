package pl.edu.pw.ee.maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Maze {

    private char[][] maze;
    private int gridWidth;
    private int gridHeight;

    public Maze(int height, int width) {
        if (height < 1 || width < 1) {
            throw new IllegalArgumentException("Niedozwolone wymiary labiryntu: " + height + "x" + width + ". Każdy z wymiarów musi być większy lub równy 1.");
        } else {
            this.maze = RecursiveDivisionAlgorithm.createMaze(2 * height + 1, 2 * width + 1);
            this.gridWidth = maze[0].length;
            this.gridHeight = maze.length;
        }
    }

    public Maze(String filePath) {
        this.maze = readFromFile(filePath);
        this.gridWidth = maze[0].length;
        this.gridHeight = maze.length;
    }

    private char[][] readFromFile(String filePath) {
        ArrayList<String> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        char[][] tmp = new char[list.size()][list.get(0).length()];
        try {
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.get(i).length(); j++) {
                    tmp[i][j] = list.get(i).charAt(j);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println(filePath + " (Błąd wczytywania z pliku. Niezgodna liczba znaków w liniach!)");
            System.exit(2);
        }

        return tmp;
    }

    public void saveToFile(String filePath) {
        try {
            PrintWriter pw = new PrintWriter(filePath);
            pw.print(toString());
            pw.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public char get(int i, int j) {
        return maze[i][j];
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                str.append(maze[i][j]);
            }
            str.append("\n");
        }
        return str.toString();
    }
}
