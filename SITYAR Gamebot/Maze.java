import java.util.ArrayList;
import javax.swing.JFrame;

public class Maze {
    int nSize;
    ArrayList<ArrayList<Cell>> grid = new ArrayList<>();

    // current cell
    int current;

    public Maze(int nSize) {

        this.nSize = nSize; // Size of array (n)
        int c, r; // Indicating columns and rows

        for (c = 0; c < nSize + 2; c++) {

            ArrayList<Cell> row = new ArrayList<>();

            // Maze itself
            for (r = 0; r < nSize + 2; r++) {
                char cellVal;
                boolean cellVisited = false;

                // Outer border
                if(c == 0 || c == nSize + 1 || r == 0 || r == nSize + 1) {
                    // Wall
                    cellVal = '#';
                }

                // Inner maze

                else if(c == 1 && r == 1) {
                    // Start node
                    cellVal = 'S';
                    cellVisited = true;
                }

                else if(c == nSize && r == nSize) {
                    // End node
                    cellVal = 'E';
                }

                else {
                    // Space
                    cellVal = '0';
                }


                Cell cell = new Cell(c, r, cellVal, cellVisited);

                row.add(cell);
            }

            grid.add(row);
        }
    }

    public void drawMaze() {
        for (int r = 0; r < nSize + 2; r++) {
            for (int c = 0; c < nSize + 2; c++) {

                // Print cell value
                System.out.print(grid.get(c).get(r).getVal() + " ");
            }

            // Move to the next row
            System.out.println();
        }
    }
}