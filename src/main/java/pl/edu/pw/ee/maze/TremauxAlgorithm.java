package pl.edu.pw.ee.maze;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TremauxAlgorithm {

    public static List<Node> findTheExitPath(MazeAsGraph maze) {
        LinkedList<Node> exitPath = new LinkedList<>();

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
            exitPath.addFirst(node);
            node = node.getDeletedWhenAdded();
        }
        exitPath.addFirst(node);

        return exitPath;
    }

    private static List<Node> countUnmarkedPaths(List<Node> neighbours) {
        List<Node> unmarkedPaths = new LinkedList<>();
        for (Node x : neighbours) {
            if (x.getMarked() == 0) {
                unmarkedPaths.add(x);
            }
        }

        return unmarkedPaths;
    }

}
