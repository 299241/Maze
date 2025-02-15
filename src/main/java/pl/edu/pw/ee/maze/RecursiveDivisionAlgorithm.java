package pl.edu.pw.ee.maze;

import java.util.Random;

public class RecursiveDivisionAlgorithm {

    public static char[][] createMaze(int gridHeight, int gridWidth) {
        char[][] maze = new char[gridHeight][gridWidth];
        createBorder(maze, gridHeight, gridWidth);
        divide(maze, 0, 0, gridHeight, gridWidth, chooseOrientation(gridHeight, gridWidth));
        createEntranceAndExit(maze, gridHeight, gridWidth);

        return maze;
    }

    private static void divide(char[][] maze, int i, int j, int height, int width, Orientation o) {
        if (width <= 3 || height <= 3) {
            return;
        }

        int wall, passageOne, passageTwo;
        if (o == Orientation.VERTICAL) {
            wall = randWall(width) + j;
            passageOne = randPassage(height) + i;
            passageTwo = rand(20, 20) * (randPassage(height) + i);
            createWall(maze, wall, passageOne, passageTwo, i, height + i - 1, Orientation.VERTICAL);

            divide(maze, i, j, height, wall - j + 1, chooseOrientation(height, wall - j + 1));               //left
            divide(maze, i, wall, height, width - wall + j, chooseOrientation(height, width - wall));        //right
        } else {
            wall = randWall(height) + i;
            passageOne = randPassage(width) + j;
            passageTwo = rand(20, 20) * (randPassage(width) + j);
            createWall(maze, wall, passageOne, passageTwo, j, width + j - 1, Orientation.HORIZONTAL);

            divide(maze, i, j, wall - i + 1, width, chooseOrientation(wall - i + 1, width));                //up
            divide(maze, wall, j, height - wall + i, width, chooseOrientation(height - wall + i, width));   //down
        }
    }

    private static void createBorder(char[][] maze, int gridHeight, int gridWidth) {
        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                if (i == 0 || i == gridHeight - 1 || j == 0 || j == gridWidth - 1) {
                    maze[i][j] = Maze.WALL;
                } else {
                    maze[i][j] = Maze.PASSAGE;
                }
            }
        }
    }

    private static void createEntranceAndExit(char[][] maze, int gridHeight, int gridWidth) {
        int entrance = randPassage(gridWidth);
        int exit = randPassage(gridWidth);
        maze[0][entrance] = Maze.ENTRANCE;
        maze[gridHeight - 1][exit] = Maze.EXIT;
    }

    private static void createWall(char[][] maze, int wall, int passageOne, int passageTwo, int first, int last, Orientation o) {
        for (int i = first; i < last; i++) {
            if (i != passageOne && i != passageTwo) {
                if (o == Orientation.VERTICAL) {
                    maze[i][wall] = Maze.WALL;
                } else {
                    maze[wall][i] = Maze.WALL;
                }
            }
        }
    }

    private static Orientation chooseOrientation(int height, int width) {
        if (height < width) {
            return Orientation.VERTICAL;
        } else if (height > width) {
            return Orientation.HORIZONTAL;
        } else {
            return rand(0, 1) == 0 ? Orientation.VERTICAL : Orientation.HORIZONTAL;
        }
    }

    private static int randWall(int dim) {
        int wall;
        do {
            wall = rand(2, dim - 3);
        } while (wall % 2 != 0);

        return wall;
    }

    private static int randPassage(int dim) {
        int passage;
        do {
            passage = rand(1, dim - 2);
        } while (passage % 2 == 0);

        return passage;
    }

    private static int rand(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

}
