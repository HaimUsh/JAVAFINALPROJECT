package controller;

import model.Model;
import view.View;
import algorithms.mazeGenerators.Maze3d;


// TODO: Auto-generated Javadoc
/**
 * The Class DisplayCommand.
 */
public class DisplayMazeCommand implements Command {

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String, view.View, model.Model)
	 */
	@Override
	public void doCommand(String name,View v,Model m) {
		if(m.getMazeList().containsKey(name));
		{
			Maze3d myMaze=m.getMazeList().get(name);
			v.displayMaze(myMaze);
		} 
	}

}
