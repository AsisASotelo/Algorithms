/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF wquUF;
    private boolean[][] nGrid;
    private final int gridSize;
    private int openSites;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int a) {
        // Validate Entry
        if (a <= 0) {
            throw new IllegalArgumentException("int a is less than 1");
        }
        openSites = 0;
        gridSize = a;
        nGrid = new boolean[a + 1][a + 1];
        wquUF = new WeightedQuickUnionUF(a * a + 2);
        for (int i = 1; i < nGrid.length; i++) {
            for (int j = 1; j < nGrid[i].length; j++) {
                nGrid[i][j] = false;
            }
        }

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);

        if (isOpen(row, col)) {
            return;
        }

        if (gridSize == 1) {
            nGrid[row][col] = true;
            openSites++;
            wquUF.union(0, col);
            return;

        }

        // Open Top Row is always full and connected to top.
        if (row == 1) {
            nGrid[row][col] = true;
            openSites++;
            wquUF.union(0, col);
            if (isOpen(row + 1, col)) {
                wquUF.union(col, col + gridSize);
            }


        }

        // Any [row][col] index not in the boundary. [CASE 1]
        if (row != 1 && row != gridSize && col != 1 && col != gridSize) {
            nGrid[row][col] = true;
            openSites++;

            // Site Above
            if (isOpen(row - 1, col)) {
                wquUF.union((row - 2) * gridSize + col, (row - 1) * gridSize + col);

            }
            // Site to the Right
            if (isOpen(row, col + 1)) {
                wquUF.union((row - 1) * gridSize + col, (row - 1) * gridSize + col + 1);
            }
            // Site to the left
            if (isOpen(row, col - 1)) {
                wquUF.union((row - 1) * gridSize + col, (row - 1) * gridSize + col - 1);
            }

            // Site Below
            if (isOpen(row + 1, col)) {
                wquUF.union((row) * gridSize + col, (row - 1) * gridSize + col);
            }

        }


        // Site on the left edge, not on top, not on bottom. [CASE 2]

        if (col == 1 && row != 1 && row != gridSize) {

            nGrid[row][col] = true;
            openSites++;

            // Site Above
            if (isOpen(row - 1, col)) {
                wquUF.union((row - 2) * gridSize + col, (row - 1) * gridSize + col);

            }

            // Site to the Right
            if (isOpen(row, col + 1)) {
                wquUF.union((row - 1) * gridSize + col, (row - 1) * gridSize + col + 1);
            }

            // Site Below
            if (isOpen(row + 1, col)) {
                wquUF.union((row) * gridSize + col, (row - 1) * gridSize + col);
            }


        }

        // Site on the right edge, not on top or bottom. [CASE 3]
        if (col == gridSize && row != 1 && row != gridSize) {

            nGrid[row][col] = true;
            openSites++;

            // Site Above
            if (isOpen(row - 1, col)) {
                wquUF.union((row - 2) * gridSize + col, (row - 1) * gridSize + col);

            }

            // Site to the left
            if (isOpen(row, col - 1)) {
                wquUF.union((row - 1) * gridSize + col, (row - 1) * gridSize + col - 1);
            }

            // Site Below
            if (isOpen(row + 1, col)) {
                wquUF.union((row) * gridSize + col, (row - 1) * gridSize + col);
            }

        }

        // Site on the bottom row, not corners. [CASE 4]
        if (row == gridSize && col != 1 && col != gridSize) {

            nGrid[row][col] = true;
            openSites++;
            

            // Site Above
            if (isOpen(row - 1, col)) {
                wquUF.union((row - 2) * gridSize + col, (row - 1) * gridSize + col);

            }

            // Site to the left
            if (isOpen(row, col - 1)) {
                wquUF.union((row - 1) * gridSize + col, (row - 1) * gridSize + col - 1);
            }

            // Site to the Right
            if (isOpen(row, col + 1)) {
                wquUF.union((row - 1) * gridSize + col, (row - 1) * gridSize + col + 1);
            }


        }

        // Left Corner [CASE 5]
        if (row == gridSize && col == 1) {
            nGrid[row][col] = true;
            openSites++;


            // Site Above
            if (isOpen(row - 1, col)) {
                wquUF.union((row - 2) * gridSize + col, (row - 1) * gridSize + col);

            }

            // Site to the Right
            if (isOpen(row, col + 1)) {
                wquUF.union((row - 1) * gridSize + col, (row - 1) * gridSize + col + 1);
            }

        }

        // Right Corner [CASE 5]
        if (row == gridSize && col == gridSize) {
            nGrid[row][col] = true;
            openSites++;


            // Site Above
            if (isOpen(row - 1, col)) {
                wquUF.union((row - 2) * gridSize + col, (row - 1) * gridSize + col);

            }

            // Site to the left
            if (isOpen(row, col - 1)) {
                wquUF.union((row - 1) * gridSize + col, (row - 1) * gridSize + col - 1);
            }

        }


    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return nGrid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);

        return wquUF.find((row - 1) * gridSize + col) == wquUF.find(0);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return wquUF.find(0) == wquUF.find(gridSize * gridSize + 1);

    }

    // Validate

    private void validate(int row, int col) {
        if (row < 1 || row > gridSize) {
            throw new IllegalArgumentException("row index i out of bounds");
        }
        if (col < 1 || col > gridSize) {
            throw new IllegalArgumentException("row index col out of bounds");
        }
    }

    // Test Client
    public static void main(String[] args) {

        Percolation perc = new Percolation(6);

        System.out.println();

        perc.open(6, 6); // 36
        perc.open(1, 4); // 4
        perc.open(2, 4); // 10
        perc.open(4, 4); // 22
        perc.open(5, 4); // 28
        perc.open(5, 5); // 29
        perc.open(3, 4); // 16
        perc.open(6, 5); // 35
        perc.open(2, 1); // 7
        perc.isFull(4, 4);
        perc.isFull(2, 1);

    }
}




