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

        do {
            if (node.getNeighbours().size() > 1) {
                List<Node> unmarkedPaths = unmarkedPaths(node.getNeighbours());
                if (unmarkedPaths.size() > 0) {
                    Node next = node.getNeighbours().get(rand.nextInt(unmarkedPaths.size()));
                    next.setDeletedWhenAdded(node);
                    node = next;
                    node.setMarked(1);
                } else {
                    while (node.getNeighbours().size() > 1) {
                        node.setMarked(2);
                        node = node.getDeletedWhenAdded();
                    }
                }
            } else {
                Node next = node.getNeighbours().get(0);
                next.setDeletedWhenAdded(node);
                node = next;
                node.setMarked(1);
            }
        } while (node != maze.getExit());

        while (node != maze.getEntrance()) {
            exitPath.append(node.getN());
            exitPath.append(" -> ");
            node = node.getDeletedWhenAdded();
        }
        return exitPath.toString();
    }

    private static List<Node> unmarkedPaths(List<Node> neighbours) {
        List<Node> unmarkedPaths = new ArrayList<>();
        for (Node x : neighbours) {
            if (x.getMarked() == 0) {
                unmarkedPaths.add(x);
            }
        }

        return unmarkedPaths;
    }

}
