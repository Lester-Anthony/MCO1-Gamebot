import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends JFrame {
    private int[][] maze;
    private List<Integer> shortestPath;
    private int pathIndex;

    public Main(int mazeSize) {
        setTitle("Dijkstra's Algorithm Visualization");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeMaze(mazeSize);

        DijkstraSolver dijkstraSolver = new DijkstraSolver(maze);
        dijkstraSolver.solve();
        shortestPath = dijkstraSolver.getShortestPath();
        pathIndex = shortestPath.size() - 2;

        // game loop each 0.5 seconds
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                update();
                repaint();
            }
        }, 100, 500);
    }

    private void initializeMaze(int size) {
        maze = new int[size + 1][size + 1]; // Grid size

        Scanner scanner = new Scanner(System.in);

        // Initialize border walls
//        for (int i = 0; i < size + 2; i++) {
//            maze[i][0] = 1; // Left border
//            maze[i][size + 1] = 1; // Right border
//            maze[0][i] = 1; // Top border
//            maze[size + 1][i] = 1; // Bottom border
//        }

        for (int i = 1; i <= size; i++) {
            System.out.print("Enter values for maze row " + i + " (0 for path, 1 for wall, comma separated): ");
            String[] inputValues = scanner.nextLine().split(" ");

            for (int j = 1; j <= size; j++) {
                maze[i][j] = Integer.parseInt(inputValues[j - 1]);
            }
        }

        maze[1][1] = 0;       // Start
        maze[size][size] = 9; // End
    }

    private void update() {
        pathIndex -= 2;
        if (pathIndex < 0) {
            pathIndex = 0;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw the maze
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                Color color;
                switch (maze[row][col]) {
                    case 1:
                        color = Color.BLACK;
                        break;
                    case 9:
                        color = Color.BLUE;
                        break;
                    default:
                        color = Color.WHITE;
                }
                g.setColor(color);
                g.fillRect(30 * col, 30 * row, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(30 * col, 30 * row, 30, 30);
            }
        }

        // Draw the path list
        for (int p = 0; p < shortestPath.size(); p += 2) {
            int pathX = shortestPath.get(p);
            int pathY = shortestPath.get(p + 1);
            g.setColor(Color.GREEN);
            g.fillRect(30 * pathX, 30 * pathY, 30, 30);
        }

        // Draw the ball on path
        int pathX = shortestPath.get(pathIndex);
        int pathY = shortestPath.get(pathIndex + 1);
        g.setColor(Color.RED);
        g.fillOval(30 * pathX, 30 * pathY, 30, 30);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int mazeSize = getUserInput();
            Main view = new Main(mazeSize);
            view.setVisible(true);
        });
    }

    private static int getUserInput() {
        Scanner scanner = new Scanner(System.in);

        int size;
        do {
            System.out.print("Enter the size of the maze (between 8 and 64): ");
            size = scanner.nextInt();
        } while (size < 8 || size > 64);
        return size;
    }
}
