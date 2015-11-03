package view;

import solution.Solution;
import algorithms.mazeGenerators.Maze3d;


// TODO: Auto-generated Javadoc
/**
 * The Interface View.
 */
public interface View {


	/**
	 * Start.
	 */
	public void start();

	/**
	 * Display.
	 *
	 * @param s the s
	 */
	public void display(String s);

	/**
	 * Display cross section.
	 *
	 * @param crossed the crossed
	 */
	public void displayCrossSection (int[][] crossed);

	/**
	 * Display size.
	 *
	 * @param name the name
	 * @param Size the size
	 */
	public void displaySize(String name, double Size);

	/**
	 * Display maze.
	 *
	 * @param maze the maze
	 */
	public void displayMaze(Maze3d maze);

	/**
	 * Display solution.
	 *
	 * @param s the s
	 */
	public void displaySolution (Solution s);
	
	/**
	 * Display directory.
	 *
	 * @param path the path
	 */
	public void displayDirectory(String[] path);

	/**
	 * Display menu.
	 */
	public void displayMenu();

	/**
	 * Sets the maze name.
	 *
	 * @param name the new maze name
	 */
	public void setMazeName(String name);
	
	/**
	 * Display maze gui.
	 *
	 * @param maze3d the maze3d
	 */
	public void displayMazeGUI(Maze3d maze3d);

	/**
	 * Sets the solution.
	 *
	 * @param solution the new solution
	 */
	public void setSolution(Solution solution);
	
	/**
	 * Sets the show sol.
	 *
	 * @param solved the new show sol
	 */
	public void setShowSol(boolean solved);
}
