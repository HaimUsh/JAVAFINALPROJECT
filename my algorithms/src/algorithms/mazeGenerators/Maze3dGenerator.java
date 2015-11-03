package algorithms.mazeGenerators;

// TODO: Auto-generated Javadoc
/**
 * The Interface Maze3dGenerator.
 */
public interface Maze3dGenerator {
	
	/**
	 * Generate.
	 *
	 * @param rows the rows
	 * @param cols the cols
	 * @param levels the levels
	 * @return the maze3d
	 */
	public Maze3d generate(int rows,int cols,int levels);
	
	/**
	 * Measure algorithm time.
	 *
	 * @param rows the rows
	 * @param cols the cols
	 * @param levels the levels
	 * @return the string
	 */
	public String measureAlgorithmTime(int rows,int cols,int levels);

}
