/*
*       So basically the code of this is a mess, and it's so much stuff it tend to just break before showing
*       the maze, i cant tell if it's just my laptop or like the stuff im using, but have a copy in case
*/

import java.util.*;

public class DijkstraSolver {
    private int[][] maze;          // The maze to be solved
    private int[][] distance;      // Array to store distances from the start point
    private PriorityQueue<Node> priorityQueue; // Priority queue to determine the next node to explore
    private List<Integer> shortestPath;        // List to store the shortest path

    // Constructor: Initializes the DijkstraSolver with the provided maze
    public DijkstraSolver(int[][] maze) {
        this.maze = maze;
        initialize();
    }

    // Initializes the distance array and priority queue with the start point
    private void initialize() {
        int size = maze.length;
        distance = new int[size][size];

        // Fill the distance array with large values initially
        for (int i = 0; i < size; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        // Set the distance to the start point as 0
        distance[0][0] = 0;

        // Priority queue to keep track of nodes based on their distances
        priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> distance[node.y][node.x]));
        priorityQueue.add(new Node(0, 0));

        // List to store the shortest path
        shortestPath = new ArrayList<>();
    }

    // Solves the maze using Dijkstra's algorithm
    public void solve() {
        while (!priorityQueue.isEmpty()) {
            // Get the current node with the smallest distance
            Node current = priorityQueue.poll();

            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, -1, 1};

            // Explore neighbors
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                // Check if the neighbor is valid and not a wall
                if (isValid(nx, ny) && maze[ny][nx] == 0) {
                    int newDistance = distance[current.y][current.x] + 1;

                    // If the new distance is smaller, update the distance and add to the priority queue
                    if (newDistance < distance[ny][nx]) {
                        distance[ny][nx] = newDistance;
                        priorityQueue.add(new Node(nx, ny));
                    }
                }
            }
        }

        // Reconstruct the shortest path
        int x = maze.length - 1;
        int y = maze.length - 1;

        while (x != 0 || y != 0) {
            shortestPath.add(x);
            shortestPath.add(y);

            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, -1, 1};

            // Move to the neighbor with the smallest distance
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (isValid(nx, ny) && distance[ny][nx] == distance[y][x] - 1) {
                    x = nx;
                    y = ny;
                    break;
                }
            }
        }
    }

    // Returns the list representing the shortest path
    public List<Integer> getShortestPath() {
        return shortestPath;
    }

    // Checks if the given coordinates are valid within the maze
    private boolean isValid(int x, int y) {
        int size = maze.length;
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    // Represents a node in the maze with x and y coordinates
    private static class Node {
        int x, y;

        // Node constructor
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

