package sudoku_solver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class SudokuGrid extends JPanel {
	
	private final int GRID_SIZE = 9;
	
	private JButton[][] sudokuButtons = new JButton[GRID_SIZE][GRID_SIZE];
	
	public SudokuGrid() {
		setLayout (new GridLayout (GRID_SIZE, GRID_SIZE, 5, 5));
		setPreferredSize (new Dimension(400, 400));
		setBorder (new EmptyBorder(10, 10, 10, 10));
		
		SudokuButtonsListener sbl = new SudokuButtonsListener();
		
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {
				sudokuButtons[row][col] = new JButton();
				sudokuButtons[row][col].addActionListener(sbl);
				add (sudokuButtons[row][col]);
			}
		}
	}
	
	public int[][] getGridData () {
		int[][] grid = new int[GRID_SIZE][GRID_SIZE];
		
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {
				if (sudokuButtons[row][col].getText() == "")
					grid[row][col] = 0;
				else
					grid[row][col] = Integer.parseInt (sudokuButtons[row][col].getText ());
			}
		}
		return grid;
	}
	
	public void setGridData (int[][] grid) {
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {
				sudokuButtons[row][col].setText("" + grid[row][col]);
			}
		}
	}
	
	public void reset() {
		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				sudokuButtons[i][j].setText("");
			}
		}
	}
	
	private class SudokuButtonsListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			JButton button = (JButton)e.getSource();
			
			if (button.getText() == "") {
				button.setText("1");
			}
			else {
				button.setText("" + (int)(Integer.parseInt(button.getText()) + 1) );
			}
			
			if (Integer.parseInt (button.getText ()) == 10) {
				button.setText ("");
			}
			
		}
	}
}
