package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class MyMaze3DGenerator.
 */
public class MyMaze3DGenerator extends CommonMaze3dGenerator {

	/* (non-Javadoc)
	 * @see algorithms.mazeGenerators.CommonMaze3dGenerator#generate(int, int, int)
	 */
	@Override
	public Maze3d generate(int rows, int cols, int levels) {
		Maze3d maze= new Maze3d(rows, cols, levels);
		maze.setStartPosition(randomNumberMaker((maze.getRows()-2), 1), randomNumberMaker((maze.getCols()-2), 1), randomNumberMaker((maze.getLevels()-2), 1));
		maze.setValueByPosition(maze.getStartPosition(), 0);
		initOpening(maze.getStartPosition().x, maze.getStartPosition().z, maze.getStartPosition().y, maze);
		maze.setGoalPosition(randomNumberMaker((maze.getRows()-2), 1), randomNumberMaker((maze.getCols()-2), 1), randomNumberMaker((maze.getLevels()-2), 1));
		while(maze.getValueByPosition(maze.getGoalPosition())==1)
			maze.setGoalPosition(randomNumberMaker((maze.getRows()-2), 1), randomNumberMaker((maze.getCols()-2), 1), randomNumberMaker((maze.getLevels()-2), 1));
		
		return maze;
	}
	
	/**
	 * Inits the opening.
	 *
	 * @param currentX the current x
	 * @param currentZ the current z
	 * @param currentY the current y
	 * @param maze the maze
	 */
	public void initOpening(int currentX, int currentZ, int currentY,
			Maze3d maze) {

		maze.setVisited(currentX, currentZ, currentY);
		ArrayList<Moves> dirList = new ArrayList<Moves>(6);
		if (!(currentY == 0))
			dirList.add(Moves.UP);
		if (!(currentY == maze.getLevels() - 1))
			dirList.add(Moves.DOWN);
		if (!(currentX == 0))
			dirList.add(Moves.LEFT);
		if (!(currentX == maze.getRows() - 1))
			dirList.add(Moves.RIGHT);
		if (!(currentZ == 0))
			dirList.add(Moves.BACK);
		if (!(currentZ == maze.getCols() - 1))
			dirList.add(Moves.FORWARD);
		Collections.shuffle(dirList);
		int nextX = 0, nextZ = 0, nextY = 0;
		for (Moves direction : dirList) {
			switch (direction) {
			case DOWN:
				nextX = currentX;
				nextZ = currentZ;
				nextY = currentY + 1;
				break;

			case UP:
				nextX = currentX;
				nextZ = currentZ;
				nextY = currentY - 1;
				break;

			case LEFT:
				nextX = currentX - 1;
				nextZ = currentZ;
				nextY = currentY;
				break;

			case RIGHT:
				nextX = currentX + 1;
				nextZ = currentZ;
				nextY = currentY;
				break;

			case BACK:
				nextX = currentX;
				nextZ = currentZ - 1;
				nextY = currentY;
				break;
			case FORWARD:
				nextX= currentX;
				nextZ= currentZ+1;
				nextY= currentY;
				break;
			}

			if (maze.isOutOfBound(nextX, nextZ, nextY))
				continue;
			if (maze.isVisited(nextX, nextZ, nextY))
				continue;
			Random rand = new Random();
			maze.setValue(currentX, currentZ, currentY, rand.nextInt(2));

			initOpening(nextX, nextZ, nextY, maze);
			
		}
		maze.setCurrentPosition(maze.getStartPosition().getX(),maze.getStartPosition().getZ(), maze.getStartPosition().getY() );
	}
}
