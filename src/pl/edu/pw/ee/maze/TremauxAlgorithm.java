package pl.edu.pw.ee.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TremauxAlgorithm {
    public static String findTheExitPath(MazeAsGraph maze) {
        StringBuilder exitPath = new StringBuilder();

        Node node = maze.getEntrance();
        node.setMarked(1);
        Random rand = new Random();

        exitPath.append(unmarkedPaths(node.getNeighbours()));

        return exitPath.toString();
    }

    private static List<Node> unmarkedPaths(List<Node> neighbours) {
        List<Node> unmarkedPaths = new ArrayList<>();
        for (Node x : neighbours) {
            if(x.getMarked() == 0){
                unmarkedPaths.add(x);
            }
        }

        return unmarkedPaths;
    }

}
