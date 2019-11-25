package pl.edu.pw.ee.maze;

import java.util.ArrayList;
import java.util.List;

public class MazeAsGraph {

    Maze maze;
    private List<Node> nodes;
    private Node entrance;
    private Node exit;

    public MazeAsGraph(Maze maze) {
        this.maze = maze;
        this.nodes = new ArrayList<>();
        readMazeAsNodes(maze);
        readNeighbours(maze);
    }

    private void readMazeAsNodes(Maze maze) {
        for (int i = 1; i < maze.getGridHeight(); i += 2) {
            for (int j = 1; j < maze.getGridWidth(); j += 2) {
                int pos = (j + 1) / 2 + ((maze.getGridWidth() - 1) / 2 * (i - 1) / 2) - 1;
                nodes.add(pos, new Node(pos, i, j));

                if (maze.get(i - 1, j) == Maze.ENTRANCE) {
                    entrance = nodes.get(pos);
                } else if (maze.get(i + 1, j) == Maze.EXIT) {
                    exit = nodes.get(pos);
                }
            }
        }
    }

    private void readNeighbours(Maze maze) {
        for (int i = 0; i < (((maze.getGridHeight() - 1) / 2) * ((maze.getGridWidth() - 1) / 2)); i++) {
            Node node = nodes.get(i);
            if (maze.get(node.getI() - 1, node.getJ()) == Maze.PASSAGE) {
                node.getNeighbours().add(nodes.get(node.getN() - (maze.getGridWidth() - 1) / 2));
            }
            if (maze.get(node.getI() + 1, node.getJ()) == Maze.PASSAGE) {
                node.getNeighbours().add(nodes.get(node.getN() + (maze.getGridWidth() - 1) / 2));
            }
            if (maze.get(node.getI(), node.getJ() - 1) == Maze.PASSAGE) {
                node.getNeighbours().add(nodes.get(node.getN() - 1));
            }
            if (maze.get(node.getI(), node.getJ() + 1) == Maze.PASSAGE) {
                node.getNeighbours().add(nodes.get(node.getN() + 1));
            }
        }
    }

    public Node getEntrance() {
        return entrance;
    }

    public Node getExit() {
        return exit;
    }

}
