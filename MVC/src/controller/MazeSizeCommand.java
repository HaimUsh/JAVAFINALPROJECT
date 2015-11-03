package controller;

import model.Model;
import view.View;


// TODO: Auto-generated Javadoc
/**
 * The Class MazeSizeCommand.
 */
public class MazeSizeCommand implements Command {

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String, view.View, model.Model)
	 */
	@Override
	public void doCommand(String name,View v,Model m) {
		double size= m.mazeSize(name);
		v.displayMazeSize(size);

	}

}
