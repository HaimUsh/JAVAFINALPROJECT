package view;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonMazeDisplay.
 */
public abstract class CommonMazeDisplay extends Canvas 
{

	/** The my maze. */
	private Maze3d myMaze;

	/**
	 * Gets the my maze.
	 *
	 * @return the my maze
	 */
	public Maze3d getMyMaze() 
	{
		return myMaze;
	}

	/**
	 * Sets the my maze.
	 *
	 * @param myMaze the new my maze
	 */
	public void setMyMaze(Maze3d myMaze) 
	{
		if (myMaze != null)
			this.myMaze = myMaze;
	}

	/**
	 * Instantiates a new common maze display.
	 *
	 * @param parent the parent
	 * @param style the style
	 */
	public CommonMazeDisplay(Composite parent, int style)
	{
		super(parent, style);

	}

	/**
	 * Move up.
	 */
	public abstract void moveUp();
	
	/**
	 * Move down.
	 */
	public abstract void moveDown(); 
	
	/**
	 * Move left.
	 */
	public abstract void moveLeft(); 
	
	/**
	 * Move right.
	 */
	public abstract void moveRight();
	
	/**
	 * Move back.
	 */
	public abstract void moveBack(); 
	
	/**
	 * Move forward.
	 */
	public abstract void moveForward();
	
	/**
	 * Update current position.
	 *
	 * @param _p the _p
	 */
	public abstract void updateCurrentPosition(Position _p);
}
