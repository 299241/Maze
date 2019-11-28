package pl.edu.pw.ee.maze;

import java.util.LinkedList;
import java.util.List;

public class BreadthFirstSearchAlgorithm {

    public static List<Node> findTheExitPath(MazeAsGraph maze) {
        LinkedList<Node> exitPath = new LinkedList<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(maze.getEntrance());

        while (!queue.isEmpty()) {
            Node first = queue.getFirst();

            if (first == maze.getExit()) {
                exitPath.addFirst(first);
                while (first != maze.getEntrance()) {
                    first = first.getDeletedWhenAdded();
                    exitPath.addFirst(first);
                }
                break;
            }
            first.getNeighbours().forEach((x) -> x.setDeletedWhenAdded(queue.getFirst()));
            first.getNeighbours().forEach((x) -> x.getNeighbours().remove(queue.getFirst()));
            queue.addAll(first.getNeighbours());
            queue.removeFirst();
        }

        return exitPath;
    }

}
