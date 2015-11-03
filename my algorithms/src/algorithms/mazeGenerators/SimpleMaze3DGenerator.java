package algorithms.mazeGenerators;


// TODO: Auto-generated Javadoc
/**
 * The Class SimpleMaze3DGenerator.
 */
public class SimpleMaze3DGenerator extends CommonMaze3dGenerator {

		/* (non-Javadoc)
		 * @see algorithms.mazeGenerators.CommonMaze3dGenerator#generate(int, int, int)
		 */
		@Override
		public Maze3d generate(int x, int y, int z)
		{
			
			int min = 1;
			Maze3d maze = new Maze3d(x,y,z);
		
			for (int i = 0; i < x; i++) 
			{
				for (int j = 0; j < y; j++) 
				{
					for (int k = 0; k < z; k++) 
					{
							maze.setValue(i, j, k,1);
					}
				}
			}
			maze.setStartPosition(randomNumberMaker(maze.getRows()-2, min), randomNumberMaker((maze.getCols()-2), min), randomNumberMaker((maze.getLevels()-2), min));
			maze.setGoalPosition(randomNumberMaker((maze.getRows()-2), min), randomNumberMaker((maze.getCols()-2), min), randomNumberMaker((maze.getLevels()-2), min));
			maze.getCurrentPosition().copyPosition(maze.getStartPosition());
			
			for (int i = maze.getCurrentPosition().x; i <= maze.getGoalPosition().x ; i++) {
				maze.setValue(i, maze.getCurrentPosition().z,maze.getCurrentPosition().y, 0);
			}
			
			for (int j = maze.getCurrentPosition().z; j <= maze.getGoalPosition().z ; j++) {
				maze.setValue(maze.getCurrentPosition().x, j,maze.getCurrentPosition().y, 0);
			}

			for (int k = maze.getCurrentPosition().y; k <= maze.getGoalPosition().y ; k++) {
				maze.setValue(maze.getCurrentPosition().x, maze.getCurrentPosition().z,k, 0);
			}
			maze.setCurrentPosition(maze.getStartPosition().getX(),maze.getStartPosition().getZ(), maze.getStartPosition().getY() );

			return maze;
		}
}
		