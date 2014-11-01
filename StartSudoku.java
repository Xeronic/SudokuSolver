package sudoku_solver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartSudoku {
	
	JButton btnResetGrid = new JButton ("Reset");
	JButton btnSolveSudoku = new JButton ("Solve sudoku");
	JButton btnQuit = new JButton ("Quit");
	
	SudokuGrid sudokuGrid = new SudokuGrid ();
	SudokuSolver sudokuSolver = new SudokuSolver (sudokuGrid);
	
	public StartSudoku() {
		JFrame frame = new JFrame ("SudokuSolver");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		JPanel buttonPanel = new JPanel (new GridLayout (3, 1));
		JPanel panel = new JPanel (new BorderLayout ());
		
		buttonPanel.add (btnSolveSudoku);
		buttonPanel.add (btnResetGrid);
		buttonPanel.add (btnQuit);
		
		ButtonListener bl = new ButtonListener ();
		btnSolveSudoku.addActionListener (bl);
		btnResetGrid.addActionListener (bl);
		btnQuit.addActionListener (bl);
		
		panel.add (sudokuGrid, BorderLayout.CENTER);
		panel.add (buttonPanel, BorderLayout.SOUTH);

		frame.add (panel);
		
		frame.pack ();
		frame.setVisible (true);
	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			if (e.getSource() == btnSolveSudoku) {	
				if (sudokuSolver.solve (sudokuGrid.getGridData())) {
					sudokuGrid.setGridData (sudokuSolver.getSolvedGrid());
				}
				else {
					JOptionPane.showMessageDialog(null,  "Invalid sudokugrid");
				}
			}
			else if (e.getSource() == btnResetGrid) {
				sudokuGrid.reset();
			}
			else if (e.getSource() == btnQuit) {
				System.exit(0);
			}
		}
	}
	
	public static void main(String[] args) {
		StartSudoku app = new StartSudoku();
	}
}
