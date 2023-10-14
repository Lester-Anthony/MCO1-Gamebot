public class Cell {
    int col;
    int row;
    char val;
    boolean visited = false;

    public Cell(int col, int row, char val, boolean visited) {
        this.col = col;
        this.row = row;

        // 0 = SPACE | 1 = WALL | 2 = START | 3 = GOAL
        this.val = val; 

        // true | false
        this.visited = visited;
    }

    public char getVal() {
        return this.val;
    }

    public boolean getVisited(){
        return this.visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}