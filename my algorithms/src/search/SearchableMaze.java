package search;

import java.util.ArrayList;

import states.MazeState;
import states.State;
import algorithms.mazeGenerators.Maze3d;
// TODO: Auto-generated Javadoc

/**
 * The Class SearchableMaze.
 */
public class SearchableMaze implements Searchable 
{
	
	/** The maze. */
	Maze3d maze;
	
	/** The start state. */
	State startState;
	
	/** The goal state. */
	State goalState;
	
	/** The move price. */
	final int movePrice = 10;
	
	/**
	 * Instantiates a new searchable maze.
	 */
	public SearchableMaze() //default ctor
	{

	}
	
	/**
	 * Instantiates a new searchable maze.
	 *
	 * @param _maze the _maze
	 */
	public SearchableMaze(Maze3d _maze) // ctor
	{
		this.maze = new Maze3d(_maze);
		this.startState = new MazeState(_maze.getStartPosition());
		this.goalState = new MazeState(_maze.getGoalPosition());

	}

	/**
	 * Gets the maze.
	 *
	 * @return the maze
	 */
	public Maze3d getMaze()
	{
		return maze;
	}
	
	/**
	 * Sets the start state.
	 *
	 * @param startState the new start state
	 */
	public void setStartState(State startState)
	{
		this.startState = new State(startState);
	}

	/**
	 * Sets the goal state.
	 *
	 * @param goalState the new goal state
	 */
	public void setGoalState(State goalState)
	{
		this.goalState = new State(goalState);
	}

	/* (non-Javadoc)
	 * @see search.Searchable#getStartState()
	 */
	@Override
	public State getStartState()
	{
		return this.startState;
	}

	/* (non-Javadoc)
	 * @see search.Searchable#getGoalState()
	 */
	@Override
	public State getGoalState() 
	{
		return this.goalState;
	}
	
	/* (non-Javadoc)
	 * @see search.Searchable#getAllPossibleStates(states.State)
	 */
	@Override
	public ArrayList<State> getAllPossibleStates(State s1) 
	{
		ArrayList<String> stringList = maze.getPossibleMovesInArrayList(s1);
		ArrayList<State> statesList = s1.stringArraytoStateArray(stringList);

		return statesList;
	}

	/* (non-Javadoc)
	 * @see search.Searchable#getPrice(states.State, states.State)
	 */
	@Override
	public double getPrice(State s1, State s2) 
	{
		if (s1.equals(s2))
		{
			return 0;
		}
		return movePrice;
	}
}
