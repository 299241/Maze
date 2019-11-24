package pl.edu.pw.ee.maze;

public class Node {
    private int x;
    private int y;
    private Node parent;
    private int directions;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = null;
        this.directions = 4;
    }
}
