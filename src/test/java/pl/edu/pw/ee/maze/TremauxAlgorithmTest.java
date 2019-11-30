package pl.edu.pw.ee.maze;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TremauxAlgorithmTest {

    private static MazeAsGraph maze5x5;
    private static MazeAsGraph maze20x10;

    @BeforeClass
    public static void setUpClass() {
        maze5x5 = new MazeAsGraph(new Maze("resources/testingData1"));
        maze20x10 = new MazeAsGraph(new Maze("resources/maze20x10"));
    }

    @Test
    public void maze5x5() {
        boolean expResult = false;
        boolean result = true;
        String exitPath = "[0, 1, 2, 7, 12, 11, 16, 15, 20, 21, 22, 17, 18, 23, 24]";
        if (BreadthFirstSearchAlgorithm.findTheExitPath(maze5x5).toString().equals(exitPath)) {
            result = false;
        }

        assertEquals(expResult, result);
    }

    @Test
    public void maze20x10() {
        boolean expResult = false;
        boolean result = true;
        String exitPath = "[8, 7, 17, 16, 15, 14, 13, 3, 2, 1, 11, 21, 22, 32, 42, 52, 51, 61, 71, 70, 80, 90, 91, " +
                "101, 100, 110, 111, 112, 113, 114, 124, 125, 126, 116, 106, 107, 117, 127, 137, 147, 157, 167, 177, " +
                "178, 188, 189, 199]";
        if (BreadthFirstSearchAlgorithm.findTheExitPath(maze20x10).toString().equals(exitPath)) {
            result = false;
        }

        assertEquals(expResult, result);
    }

}