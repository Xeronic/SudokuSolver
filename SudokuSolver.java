package sudoku_solver;

public class SudokuSolver {
	
	private SudokuGrid sudokuGrid;
	private int[][] solvedGrid;
	
	public SudokuSolver(SudokuGrid sudokuGrid) {
		this.sudokuGrid = sudokuGrid;
	}
	
	public boolean has_solution(int[][] grid) {
		boolean solved = false;
		
		sudokuGrid.setGridData(grid);
		
		if (isFull(grid)) {
			this.solvedGrid = grid;
			return true;
		}
		else {
			Cell cell = getFreeCell(grid);
			int trialValue = 1;
			
			while (!solved && trialValue <= 9) {
				if (isValidMove (trialValue, cell, grid)) {
					grid[cell.x][cell.y] = trialValue;
					if (has_solution (grid.clone()))
						solved = true;
					else
						grid[cell.x][cell.y] = 0;
				}
				
				trialValue += 1;
			}
		}
		
		return solved;
	}
	
	public boolean solve(int[][] grid) {
		if (isValidSudokuGrid(grid)) {
			has_solution (grid);
			return true;
		}
		else
			return false;
	}
	
	public int[][] getSolvedGrid () {
		return this.solvedGrid;
	}
	
	private boolean isValidSudokuGrid (int[][] grid) {
		boolean valid = true;
		int trialValue;
		Cell pos;
		int[][] tempGrid;
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (grid[i][j] > 0) {
					trialValue = grid[i][j];
					pos = new Cell(i, j);
					tempGrid = grid.clone();
					tempGrid[pos.x][pos.y] = 0;
					if ( !isValidMove (trialValue, pos, tempGrid)) {
						System.out.println("Cell: " + pos + "\ntrialValue: " + trialValue);
						valid = false;
					}
				}
			}
		}
		return valid;
	}
	
	private boolean isValidMove (int trialValue, Cell cell, int[][] grid) {
		if (uniqueInRow (trialValue, cell, grid) && uniqueInCol (trialValue, cell, grid) && 
				uniqueInSquare (trialValue, cell, grid))
			return true;
		return false;
	}
	
	private boolean uniqueInRow (int trialValue, Cell cell, int[][] grid) {
		for (int col = 0; col < grid.length; col++) {
			if (grid[cell.x][col] == trialValue)
				return false;
		}
		return true;
	}
	
	private boolean uniqueInCol (int trialValue, Cell cell, int[][] grid) {
		for (int row = 0; row < grid.length; row++) {
			if (grid[row][cell.y] == trialValue)
				return false;
		}
		return true;
	}
	
	private boolean uniqueInSquare (int trialValue, Cell cell, int[][] grid) {
		Cell square = findSquare (cell);
		square.x = square.x * 3;
		square.y = square.y * 3;
		
		for (int i = square.x; i < square.x + 3; i++) {
			for (int j = square.y; j < square.y + 3; j++) {
				if (grid[i][j] == trialValue) {
					return false;
				}
			}
			System.out.println();
		}
		
		return true;
	}
	
	private Cell findSquare (Cell cell) {
		int x = cell.x / 3;
		int y = cell.y / 3;
		return new Cell(x, y);
	}
	
	private Cell getFreeCell (int[][] grid) {
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid.length; col++) {
				if (grid[row][col] == 0) 
					return new Cell (row, col);
			}
		}
		return new Cell (0, 0);
	}
	
	private boolean isFull (int[][] grid) {
		for (int[] row : grid) {
			for (int col : row) {
				if (col == 0) 
					return false;
			}
		}
		return true;
	}
	
	private void printGrid (int[][] grid) {
		for (int[] row : grid) {
			for (int col : row) {
				System.out.print(col);
			}
			System.out.println();
		}
	}
	
	private class Cell {
		public int x, y;
		
		public Cell (int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public String toString() {
			return String.format ("%d, %d", x, y);
		}
	}
}


