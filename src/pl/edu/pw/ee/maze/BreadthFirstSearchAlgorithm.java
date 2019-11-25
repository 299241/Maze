package pl.edu.pw.ee.maze;

import java.util.LinkedList;

public class BreadthFirstSearchAlgorithm {

    public static String findTheExitPath(MazeAsGraph maze) {
        StringBuilder exitPath = new StringBuilder();
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(maze.getEntrance());

        while (!queue.isEmpty()) {
            Node first = queue.getFirst();

            if (first == maze.getExit()) {
                exitPath.append(first.getN());
                exitPath.append(" -> ");
                while (first != maze.getEntrance()) {
                    first = first.getDeletedWhenAdded();
                    exitPath.append(first.getN());
                    exitPath.append(" -> ");
                }
                exitPath.delete(exitPath.length() - 4, exitPath.length() - 1);
                break;
            }
            first.getNeighbours().forEach((x) -> x.setDeletedWhenAdded(queue.getFirst()));
            first.getNeighbours().forEach((x) -> x.getNeighbours().remove(queue.getFirst()));
            queue.addAll(first.getNeighbours());
            queue.removeFirst();
        }

        return exitPath.toString();
    }

}
