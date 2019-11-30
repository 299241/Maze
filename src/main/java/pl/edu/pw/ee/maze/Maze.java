package pl.edu.pw.ee.maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Maze {

    public final static char WALL = '+';
    public final static char PASSAGE = '0';
    public final static char ENTRANCE = '#';
    public final static char EXIT = '*';

    private char[][] maze;
    private int gridWidth;
    private int gridHeight;

    public Maze(int height, int width) {
        if (height < 1 || width < 1) {
            throw new IllegalArgumentException("Niedozwolone wymiary labiryntu: " + height + "x" + width + "." +
                    "Każdy z wymiarów musi być większy lub równy 1.");
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
        boolean isEntrance = false;
        boolean isExit = false;

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
                    if (j % 2 != 0 && tmp[i][j] == ENTRANCE) {
                        isEntrance = true;
                    } else if (j % 2 != 0 && tmp[i][j] == EXIT) {
                        isExit = true;
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println(filePath + " (Błąd wczytywania z pliku. Niezgodna liczba znaków w liniach!)");
            System.exit(2);
        }

        if (!isEntrance || !isExit) {
            if (!isEntrance) {
                System.err.println(filePath + " (Błąd wczytywania z pliku. Brak wejścia do labiryntu!)");
            }
            if (!isExit) {
                System.err.println(filePath + " (Błąd wczytywania z pliku. Brak wyjścia z labiryntu!)");
            }
            System.exit(3);
        }

        for (int i = 1; i < tmp.length - 1; i++) {
            int countPassage = 0;
            for (int j = 1; j < tmp[0].length - 1; j++) {
                if (tmp[i][j] == PASSAGE) {
                    countPassage++;
                }
            }
            if (countPassage == 0) {
                System.err.println(filePath + " (Błąd wczytywania z pliku. Nie istnieje scieżka wyjścia!)");
                System.exit(4);
            }
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
