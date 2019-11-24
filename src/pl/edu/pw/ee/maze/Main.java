package pl.edu.pw.ee.maze;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    private static Pane root = new Pane();

    public static void main(String[] args) {
        Maze maze = new Maze(10, 5);
        FindingTheExitPathAlgorithm bfs = new BreadthFirstSearchAlgorithm(maze);
        System.out.println(bfs.findTheExitPath());

        System.out.println(maze);

        showMaze(maze);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Maze");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void showMaze(Maze maze) {
        double prevX = 0;
        double prevY = 0;
        double prevRowY = 0;
        double longerDim = Math.max(maze.getGridWidth(), maze.getGridHeight());
        double size = 800 / (longerDim / 2 * 3 + (longerDim + 1) / 2);

        for (int i = 0; i < maze.getGridHeight(); i++) {
            for (int j = 0; j < maze.getGridWidth(); j++) {
                StackPane s = new StackPane();

                Integer pos = (j + 1) / 2 + ((maze.getGridWidth() - 1) / 2 * (i - 1) / 2) - 1;
                Text t = new Text(pos.toString());
                t.setFont(Font.font(size));

                Rectangle r = new Rectangle();
                s.getChildren().add(r);

                if (maze.get(i, j) == '+') {
                    r.setFill(Color.BLACK);
                } else if (maze.get(i, j) == '0') {
                    r.setFill(Color.WHITE);
                } else if (maze.get(i, j) == '#') {
                    r.setFill(Color.BLUE);
                } else if (maze.get(i, j) == '*') {
                    r.setFill(Color.GREEN);
                }

                if (i % 2 == 0 && j % 2 == 0) {
                    r.setHeight(size);
                    r.setWidth(size);
                } else if (i % 2 == 0 && j % 2 != 0) {
                    r.setHeight(size);
                    r.setWidth(size * 3);
                } else if (i % 2 != 0 && j % 2 == 0) {
                    r.setHeight(size * 3);
                    r.setWidth(size);
                } else if (i % 2 != 0 && j % 2 != 0) {
                    r.setHeight(size * 3);
                    r.setWidth(size * 3);
                    s.getChildren().add(t);
                }
                r.setStroke(Color.GRAY);
                r.setStrokeWidth(0.1);
                s.setLayoutX(prevX);
                s.setLayoutY(prevY);
                prevX += r.getWidth();
                prevRowY = r.getHeight();
                root.getChildren().add(s);
            }
            prevX = 0;
            prevY += prevRowY;
        }
    }
}
