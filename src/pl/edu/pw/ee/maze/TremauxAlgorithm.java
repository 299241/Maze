package pl.edu.pw.ee.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TremauxAlgorithm {
    public static String findTheExitPath(MazeAsGraph maze) {
        StringBuilder exitPath = new StringBuilder();

        Random rand = new Random();
        Node node = maze.getEntrance();
        node.setMarked(1);

        while (node != maze.getExit()) {
            List<Node> unmarkedPaths = countUnmarkedPaths(node.getNeighbours());
            if (unmarkedPaths.size() > 0) {
                node.setMarked(1);
                Node next = unmarkedPaths.get(rand.nextInt(unmarkedPaths.size()));
                next.setDeletedWhenAdded(node);
                node = next;
            } else {
                node.setMarked(2);
                node = node.getDeletedWhenAdded();
            }
        }

        while (node != maze.getEntrance()) {
            exitPath.append(node.getN());
            exitPath.append(" -> ");
            node = node.getDeletedWhenAdded();
        }
        exitPath.append(node.getN());

        return exitPath.toString();
    }

    private static List<Node> countUnmarkedPaths(List<Node> neighbours) {
        List<Node> unmarkedPaths = new ArrayList<>();
        for (Node x : neighbours) {
            if (x.getMarked() == 0) {
                unmarkedPaths.add(x);
            }
        }

        return unmarkedPaths;
    }

}
