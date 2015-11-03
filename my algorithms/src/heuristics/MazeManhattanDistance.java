package heuristics;

import states.State;
import algorithms.mazeGenerators.Position;
// TODO: Auto-generated Javadoc

/**
 * The Class MazeManhattanDistance.
 */
public class MazeManhattanDistance implements Heuristic
{
	
	/* (non-Javadoc)
	 * @see heuristics.Heuristic#h(states.State, states.State)
	 */
	@Override
	public double h(State s1, State s2)
	{
		int goalX,goalZ,goalY;
		int startX, startZ, startY;
		Position p1 = s1.stateToPosition(s1.getstate());
		Position p2 = s2.stateToPosition(s2.getstate());

		goalX = p1.getX();
		goalZ = p1.getZ();
		goalY = p1.getY();

		startX = p2.getX();
		startZ = p2.getZ();
		startY = p2.getY();

		return ((Math.abs((startX-goalX)+(startZ-goalZ)+(startY-goalY)))*10);
	}

}
