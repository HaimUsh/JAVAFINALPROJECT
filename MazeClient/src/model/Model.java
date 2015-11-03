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
	 * Gets the files in directory.
	 *
	 * @param path the path
	 * @return the files in directory
	 */
	public void getFilesInDirectory(String path);

	/**
	 * Generate3 d maze.
	 *
	 * @param name the name
	 * @param size the size
	 */
	public void generate3DMaze(String name, int size);

	/**
	 * Gets the cross section.
	 *
	 * @param xyz the xyz
	 * @param index the index
	 * @param name the name
	 * @return the cross section
	 */
	public void getCrossSection (char xyz, int index, String name);

	/**
	 * Save maze.
	 *
	 * @param mazeName the maze name
	 * @param fileName the file name
	 */
	public void saveMaze (String mazeName, String fileName);

	/**
	 * Load maze.
	 *
	 * @param mazeName the maze name
	 * @param fileName the file name
	 */
	public void loadMaze (String mazeName, String fileName);

	/**
	 * Maze size.
	 *
	 * @param name the name
	 */
	public void MazeSize(String name);

	/**
	 * File size.
	 *
	 * @param name the name
	 */
	public void FileSize(String name);

	/**
	 * Solve maze.
	 *
	 * @param name the name
	 */
	public void solveMaze(String name);

	/**
	 * Gets the maze list.
	 *
	 * @return the maze list
	 */
	public HashMap<String,Maze3d> getMazeList();

	/**
	 * Gets the solution list.
	 *
	 * @return the solution list
	 */
	public HashMap<Maze3d,Solution> getSolutionList();

	/**
	 * Gets the command data.
	 *
	 * @return the command data
	 */
	public HashMap<String, Object> getCommandData();

	/**
	 * Official exit.
	 */
	public void officialExit();
	
	/**
	 * Save zip.
	 */
	public void saveZip();
	
	/**
	 * Load zip.
	 */
	public void loadZip();
	
	/**
	 * Change prop.
	 *
	 * @param newProp the new prop
	 */
	public void changeProp(String[] newProp);
	
	

}

