/**
 * 
 */
package controller;

import model.Model;
import view.View;
import algorithms.mazeGenerators.Maze3d;


// TODO: Auto-generated Javadoc
/**
 * The Class LoadMazeCommand.
 *
 * @author User
 */
public class LoadMazeCommand implements Command {

	/* (non-Javadoc)
	 * @see controller.commands.Command#doCommand(java.lang.String)
	 */
	@Override
	public void doCommand(String args,View v,Model m) {
		String[] fileandname=args.split(" ");
		Maze3d maze= m.loadMaze(fileandname[0], fileandname[1]);
		v.displayMaze(maze);

	}

}
