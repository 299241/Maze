package pl.edu.pw.ee.maze;

import java.util.LinkedList;
import java.util.List;

public class Node {

    private int n;
    private int i;
    private int j;
    private List<Node> neighbours;
    private Node deletedWhenAdded;
    private boolean visited;

    public Node(int n, int i, int j) {
        this.n = n;
        this.i = i;
        this.j = j;
        this.neighbours = new LinkedList<>();
        deletedWhenAdded = null;
        visited = false;
    }

    public int getN() {
        return n;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public List<Node> getNeighbours() {
        return neighbours;
    }

    public Node getDeletedWhenAdded() {
        return deletedWhenAdded;
    }

    public void setDeletedWhenAdded(Node deletedWhenAdded) {
        this.deletedWhenAdded = deletedWhenAdded;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[n = ").append(n).append(", neighbours = ");
        neighbours.forEach((x) -> str.append(x.getN()).append(", "));
        str.append("]");
        return str.toString();
    }
}
