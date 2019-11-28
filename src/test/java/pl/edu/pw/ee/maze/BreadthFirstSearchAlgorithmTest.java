package pl.edu.pw.ee.maze;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

class BreadthFirstSearchAlgorithmTest {
    private static MazeAsGraph maze5x5;
    private static MazeAsGraph maze10x10;
    private static MazeAsGraph maze10x15;
    private static MazeAsGraph maze15x15;
    private static MazeAsGraph maze25x25;

    @BeforeClass
    public static void setUpClass() {
        maze5x5 = new MazeAsGraph(new Maze("resources/maze5x5"));
        maze10x10 = new MazeAsGraph(new Maze("resources/maze10x10"));
        maze10x15 = new MazeAsGraph(new Maze("resources/maze10x15"));
        maze15x15 = new MazeAsGraph(new Maze("resources/maze15x15"));
        maze25x25 = new MazeAsGraph(new Maze("resources/maze25x25"));
    }

    @Test
    public void testBFS5x5() {
        boolean expResult = false;
        boolean result = true;
        String exitPath = "";
        if (BreadthFirstSearchAlgorithm.findTheExitPath(maze5x5).toString().equals(exitPath)) {
            result = false;
        }

        assertEquals(expResult, result);
    }

}