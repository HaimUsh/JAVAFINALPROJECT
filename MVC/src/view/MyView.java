package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Observer;

import solution.Solution;
import algorithms.mazeGenerators.Maze3d;
import controller.Controller;


// TODO: Auto-generated Javadoc
/**
 * The Class MyView.
 */
public class MyView implements View {

	/** The c. */
	Controller c;
	
	/** The cli. */
	CLI cli;

	/** The Observables. */
	ArrayList<Observer> Observables;
	/**
	 * Sets the controller.
	 *
	 * @param c the new controller
	 */
	public void setController(Controller c){
		this.c=c;
	}

	/* (non-Javadoc)
	 * @see view.View#display(java.lang.String)
	 */
	@Override
	public void display(String s) {
		System.out.println(s);
		
	}

	/* (non-Javadoc)
	 * @see view.View#start()
	 */
	@Override
	public void start() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		CLI cli = new CLI(br, pw,this.getController().getMap());
		cli.start(c);
		
	}

	/* (non-Javadoc)
	 * @see view.View#displayMaze(algorithms.mazeGenerators.Maze3d)
	 */
	@Override
	public void displayMaze(Maze3d maze) {
		
		maze.displayMaze(maze);
		
	}

	/* (non-Javadoc)
	 * @see view.View#displayCrossSection(int[][])
	 */
	@Override
	public void displayCrossSection(int[][] cs) {
		{
			System.out.println("Your CrossSection:");
			System.out.println();
			for (int i = 0; i < cs.length; i++)
			{
				System.out.print("    ");
				for (int j = 0; j < cs.length; j++)
				{

					System.out.print(cs[j][cs.length-i-1]);
					if (j+1 < cs.length)
					{
						System.out.print(",");
					}
				}
				System.out.println();
			}
			System.out.println();
		}
		
	}

	/* (non-Javadoc)
	 * @see view.View#displaySolution(solution.Solution)
	 */
	@Override
	public void displaySolution(Solution s) {
		s.print();
		
	}


	/* (non-Javadoc)
	 * @see view.View#getController()
	 */
	@Override
	public Controller getController() {
		return this.c;
		
	}

	/* (non-Javadoc)
	 * @see view.View#displayMazeSize(double)
	 */
	@Override
	public void displayMazeSize(double size) {
		System.out.println(size+"bytes");
		
	}

	/* (non-Javadoc)
	 * @see view.View#displayFileSize(java.lang.String, double)
	 */
	@Override
	public void displayFileSize(String name, double fileSize) {
		
		DecimalFormat df = new DecimalFormat("#.##");
		String newValue = new String(df.format(fileSize));
		System.out.print("For Maze: ");
		System.out.print("'"+name+"'");
		System.out.print(" - the FILE size is: ");
		System.out.println(newValue+" Bytes.");
		
	}
	
	
	
}
