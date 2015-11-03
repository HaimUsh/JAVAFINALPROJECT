package model;

import heuristics.MazeEuclideanDistance;
import heuristics.MazeManhattanDistance;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import search.AStar;
import search.BFS;
import search.CommonSearcher;
import search.SearchableMaze;
import solution.Solution;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3DGenerator;
import controller.Controller;

// TODO: Auto-generated Javadoc
/**
 * The Class MyModel.
 */
public class MyModel implements Model {


	/** The c. */
	Controller c;
	
	/** The maze list. */
	private HashMap<String , Maze3d> mazeList;
	
	/** The solution list. */
	private HashMap<String , Solution> solutionList;

	/**
	 * Sets the controller.
	 */
	public MyModel() {
		this.mazeList=new HashMap<String, Maze3d>();
		this.solutionList= new HashMap<String, Solution>();
	}
	
	/**
	 * Sets the controller.
	 *
	 * @param c the new controller
	 */
	public void setController(Controller c){
		this.c=c;
	}

	/**
	 * Gets the maze list.
	 *
	 * @return the maze list
	 */
	public HashMap<String , Maze3d> getMazeList(){
		return this.mazeList;
	}
	
	/**
	 * Gets the solution list.
	 *
	 * @return the solution list
	 */
	public HashMap<String , Solution> getSolutionList(){
		return this.solutionList;
	}

	/* (non-Javadoc)
	 * @see model.Model#generate3DMaze(java.lang.String, int)
	 */
	@Override
	public void generate3DMaze(String name, int size) {
		new Thread(new Runnable()
		{
			@Override
			public void run() 
			{
				Maze3dGenerator mg = new MyMaze3DGenerator();
				Maze3d myMaze = mg.generate(size, size, size);
				mazeList.put(name, myMaze);

			}
		}).start();

	}

	/* (non-Javadoc)
	 * @see model.Model#solveMaze(java.lang.String, java.lang.String)
	 */
	@Override
	public void solveMaze(String name, String algorithm) {
		{
			new Thread(new Runnable() 
			{

				@Override
				public void run() 
				{
					if (getMazeList().containsKey(name))
					{
						SearchableMaze sMaze = new SearchableMaze(new Maze3d(getMazeList().get(name)));
						CommonSearcher searcher;
						Solution sol = new Solution();

						if (algorithm.equalsIgnoreCase("astarman"))
						{
							searcher = new AStar(new MazeManhattanDistance());
							sol = searcher.search(sMaze);
						}
						if (algorithm.equalsIgnoreCase("astarair"))
						{
							searcher = new AStar(new MazeEuclideanDistance());
							sol = searcher.search(sMaze);
						}
						if (algorithm.equalsIgnoreCase("bfs"))
						{
							searcher = new BFS();
							sol = searcher.search(sMaze);
						}
						getSolutionList().put(name, sol);
						c.display("Done solving maze: "+name);

					} else 
					{
						c.display("Bad Maze Name (m.solve)");
					}
				}
			}).start();
		}

	}

	/* (non-Javadoc)
	 * @see model.Model#saveMaze(java.lang.String, java.lang.String)
	 */
	@Override
	public void saveMaze(String name, String fileName) {
		Maze3d myMaze = new Maze3d(getMazeList().get(name));
		try 
		{
			OutputStream out=new MyCompressorOutputStream( new FileOutputStream(fileName));
			out.write(myMaze.toByteArray());
			out.flush();
			out.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see model.Model#loadMaze(java.lang.String, java.lang.String)
	 */
	@Override
	public Maze3d loadMaze(String fileName, String name) {
		try
		{
			InputStream in=new MyDecompressorInputStream( new FileInputStream(fileName));
			byte[] b = new byte [((MyDecompressorInputStream) in).getLength()];
			in.read(b);
			in.close();
			return new Maze3d(b);
		}

		catch (FileNotFoundException e) 
		{

			e.printStackTrace();
		} catch (IOException e)
		{

			e.printStackTrace();
		}
		return null;
	}


	/* (non-Javadoc)
	 * @see model.Model#mazeSize(java.lang.String)
	 */
	@Override
	public double mazeSize(String name) {
		double size =-5;
		if(getMazeList().containsKey(name))
		{
			Maze3d myMaze = new Maze3d(getMazeList().get(name));
			size = myMaze.toByteArray().length;
			return size;
		}
		else
		{
			c.display("Bad Maze Name (m.calcmazesize)");
			return size;
		}
	}

	/* (non-Javadoc)
	 * @see model.Model#fileSize(java.lang.String)
	 */
	@Override
	public double fileSize(String name) {
		File f = new File(name);
		if (f.length() == 0L)
		{
			this.saveMaze(name, "tempFileName");
			f = new File("tempFileName");
		}
		return f.length();

	}

	/* (non-Javadoc)
	 * @see model.Model#getCrossSection(char, int, java.lang.String)
	 */
	@Override
	public int[][] getCrossSection(char xzy, int index, String name) {
		{
			if(getMazeList().containsKey(name))
			{
				Maze3d myMaze = new Maze3d(getMazeList().get(name));
				if (xzy == 'x' || xzy == 'X') 
				{
					return myMaze.getCrossSectionByX(index);
				}
				if (xzy == 'z' || xzy == 'Z') 
				{
					return myMaze.getCrossSectionByZ(index);
				}
				if (xzy == 'y' || xzy == 'Y') 
				{
					return myMaze.getCrossSectionByY(index);
				}
				else 
				{
					c.display("bad x/z/y selection!");
					return null;
				}
			}
			else
			{
				c.display("Bad Maze Name (m.getcross)" );
				return null;
			}
		}


	}
}
