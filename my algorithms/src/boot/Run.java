package boot;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3DGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMaze3DGenerator;

// TODO: Auto-generated Javadoc
/**
 * The Class Run.
 */
public class Run {

	/**
	 * Test maze generator.
	 *
	 * @param mg the mg
	 */
	private static void testMazeGenerator(Maze3dGenerator mg){
		// prints the time it takes the algorithm to run
		System.out.println("algo time:");
		System.out.println(mg.measureAlgorithmTime(10,10,10));
		// generate another 3d maze
		Maze3d maze=mg.generate(10,10,10);
		// get the maze entrance
		Position p=maze.getStartPosition();
		// print the position
		System.out.println("start position:");
		System.out.println(p); // format "{x,y,z}"
		// get all the possible moves from a position
		System.out.println("possible moves:");
		String[] moves=maze.getPossibleMoves(p);
		// print the moves
		for(String move : moves)
		System.out.println(move);
		// prints the maze exit position
		System.out.println("goal position:");
		System.out.println(maze.getGoalPosition());
		try{
		// get 2d cross sections of the 3d maze
		int[][] maze2dx=maze.getCrossSectionByX(2);
		maze.print2dMaze(maze2dx);
		int[][] maze2dy=maze.getCrossSectionByY(5);
		maze.print2dMaze(maze2dy);
		int[][] maze2dz=maze.getCrossSectionByZ(0);
		maze.print2dMaze(maze2dz);
		// this should throw an exception!
		maze.getCrossSectionByX(-1);
		} catch (IndexOutOfBoundsException e){
		System.out.println("good!");
		System.out.println();
		}
		}
		
		/**
		 * The main method.
		 *
		 * @param args the arguments
		 */
		public static void main(String[] args) {
		
		System.out.println("simple generator:");
		testMazeGenerator(new SimpleMaze3DGenerator());
		System.out.println("recursive backtracker generator:");
		testMazeGenerator(new MyMaze3DGenerator());
		}
	}
