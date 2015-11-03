package model;

import java.util.HashMap;

import solution.Solution;
import algorithms.mazeGenerators.Maze3d;


// TODO: Auto-generated Javadoc
/**
 * The Interface Model.
 */
public interface Model {
	
	
	/**
	 * Generate3 d maze.
	 *
	 * @param name the name
	 * @param size the size
	 */
	public void generate3DMaze(String name, int size);
	
	/**
	 * Solve maze.
	 *
	 * @param name the name
	 * @param algorithm the algorithm
	 */
	public void solveMaze(String name, String algorithm);
	
	/**
	 * Save maze.
	 *
	 * @param name the name
	 * @param fileName the file name
	 */
	public void saveMaze(String name, String fileName);
	
	/**
	 * Load maze.
	 *
	 * @param fileName the file name
	 * @param name the name
	 * @return the maze3d
	 */
	public Maze3d loadMaze(String fileName, String name);
	
	/**
	 * Maze size.
	 *
	 * @param name the name
	 * @return the double
	 */
	public double mazeSize(String name);
	
	/**
	 * File size.
	 *
	 * @param name the name
	 * @return the double
	 */
	public double fileSize(String name);
	
	/**
	 * Gets the cross section.
	 *
	 * @param xyz the xyz
	 * @param index the index
	 * @param name the name
	 * @return the cross section
	 */
	public int[][] getCrossSection(char xyz, int index, String name);
	

	/**
	 * Gets the maze list.
	 *
	 * @return the maze list
	 */
	public HashMap<String , Maze3d> getMazeList();
	
	/**
	 * Gets the solution list.
	 *
	 * @return the solution list
	 */
	public HashMap<String , Solution> getSolutionList();
}	
	
