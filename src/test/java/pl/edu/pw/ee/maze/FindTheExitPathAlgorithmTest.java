package pl.edu.pw.ee.maze;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class FindTheExitPathAlgorithmTest {

    @Test
    public void findTheExitPaths() throws FileNotFoundException {
        long start, end;
        PrintWriter testResult = new PrintWriter("resources/testResult.csv");
        List<Node> tmp;

        testResult.println("dim;timeBFS;sizeBFS;timeT;sizeT;");

        for (int i = 5; i <= 70; i += 5) {
            MazeAsGraph maze = new MazeAsGraph(new Maze(i, i));

            start = System.nanoTime();
            tmp = BreadthFirstSearchAlgorithm.findTheExitPath(maze);
            end = System.nanoTime();
            testResult.print(i + ";" + (end - start) + ";" + tmp.size() + ";");

            start = System.nanoTime();
            tmp = TremauxAlgorithm.findTheExitPath(maze);
            end = System.nanoTime();
            testResult.println((end - start) + ";" + tmp.size() + ";");
        }
        testResult.close();
    }

}
