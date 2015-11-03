package view;

import solution.Solution;
import controller.Controller;
import algorithms.mazeGenerators.Maze3d;


// TODO: Auto-generated Javadoc
/**
 * The Interface View.
 */
public interface View {


		/**
		 * Display.
		 *
		 * @param s the s
		 */
		public void display(String s);
	
		/**
		 * Start.
		 */
		public void start();

		/**
		 * Display maze.
		 *
		 * @param maze the maze
		 */
		public void displayMaze(Maze3d maze);
		
		/**
		 * Display cross section.
		 *
		 * @param cs the cs
		 */
		public void displayCrossSection(int[][] cs);
		
		/**
		 * Display solution.
		 *
		 * @param s the s
		 */
		public void displaySolution(Solution s);
	
		/**
		 * Gets the controller.
		 *
		 * @return the controller
		 */
		public Controller getController();
		
		/**
		 * Display maze size.
		 *
		 * @param size the size
		 */
		public void displayMazeSize(double size);
		
		/**
		 * Display file size.
		 *
		 * @param name the name
		 * @param fileSize the file size
		 */
		public void displayFileSize(String name, double fileSize);
		
		
}

