package pl.edu.pw.ee.maze;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BreadthFirstSearchAlgorithmTest {

    private static MazeAsGraph maze5x5;
    private static MazeAsGraph maze10x15;
    private static MazeAsGraph maze25x25;

    @BeforeClass
    public static void setUpClass() {
        maze5x5 = new MazeAsGraph(new Maze("resources/maze5x5"));
        maze10x15 = new MazeAsGraph(new Maze("resources/maze10x15"));
        maze25x25 = new MazeAsGraph(new Maze("resources/maze25x25"));
    }

    @Test
    public void maze5x5() {
        boolean expResult = false;
        boolean result = true;
        String exitPath = "[4, 9, 14, 19, 24, 23]";
        if (BreadthFirstSearchAlgorithm.findTheExitPath(maze5x5).toString().equals(exitPath)) {
            result = false;
        }

        assertEquals(expResult, result);
    }

    @Test
    public void maze10x15() {
        boolean expResult = false;
        boolean result = true;
        String exitPath = "[12, 11, 26, 41, 56, 57, 58, 59, 74, 73, 72, 87, 102, 101, 116, 115, 114, 129, 130, 145]";
        if (BreadthFirstSearchAlgorithm.findTheExitPath(maze10x15).toString().equals(exitPath)) {
            result = false;
        }

        assertEquals(expResult, result);
    }

    @Test
    public void maze25x25() {
        boolean expResult = false;
        boolean result = true;
        String exitPath = "[17, 16, 15, 14, 13, 38, 63, 88, 113, 138, 139, 140, 165, 190, 215, 216, 217, 242, 267, 268, 243, 218, 193, 168, 169, 170, 195, 220, 245, 244, 269, 270, 295, 320, 319, 344, 369, 394, 395, 396, 421, 446, 471, 496, 521, 522, 547, 572, 571, 596, 621, 622]";
        if (BreadthFirstSearchAlgorithm.findTheExitPath(maze25x25).toString().equals(exitPath)) {
            result = false;
        }

        assertEquals(expResult, result);
    }
}