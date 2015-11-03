package algorithms.mazeGenerators;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonMaze3dGenerator.
 */
public abstract class CommonMaze3dGenerator implements Maze3dGenerator {

	/* (non-Javadoc)
	 * @see algorithms.mazeGenerators.Maze3dGenerator#generate(int, int, int)
	 */
	@Override
	public Maze3d generate(int rows, int cols, int levels) {
		return null;
	}

	/* (non-Javadoc)
	 * @see algorithms.mazeGenerators.Maze3dGenerator#measureAlgorithmTime(int, int, int)
	 */
	@Override
	public String measureAlgorithmTime(int rows,int cols,int levels) {
		long start= System.currentTimeMillis();
		generate(rows,cols,levels);
		long end=System.currentTimeMillis();
		long totalTime=end-start;
		return Long.toString(totalTime);
		
	}
	
	/**
	 * Random number maker.
	 *
	 * @param max the max
	 * @param min the min
	 * @return the int
	 */
	public int randomNumberMaker(int max, int min)
	{
		int num = 0;
		Random rand = new Random();
		num = rand.nextInt((max - min) + 1) + min;
		return num; 
	

	}
}
