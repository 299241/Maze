package pl.edu.pw.ee.maze;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private int n;
    private int i;
    private int j;
    private List<Node> neighbours;
    private Node deletedWhenAdded;
    private int marked;

    public Node(int n, int i, int j) {
        this.n = n;
        this.i = i;
        this.j = j;
        this.neighbours = new ArrayList<>();
        deletedWhenAdded = null;
        marked = 0;
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

    public int getMarked() {
        return marked;
    }

    public void setMarked(int marked) {
        if (marked >= 0 && marked <= 2) {
            this.marked = marked;
        } else {
            throw new IllegalArgumentException("Niedozwolony parametr visited: " + marked +
                    ". Parametr visited może przyjmować liczby od 0 do 2.");
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(n);
        return str.toString();
    }

}
