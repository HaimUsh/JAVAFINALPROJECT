package controller;

import model.Model;
import view.View;


// TODO: Auto-generated Javadoc
/**
 * The Class SolveMazeCommand.
 */
public class SolveMazeCommand implements Command {

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String, view.View, model.Model)
	 */
	@Override
	public void doCommand(String args,View v,Model m) {
		
		String[] nameandalgo= args.split(" ");
		m.solveMaze(nameandalgo[0], nameandalgo[1]);
		
		

	}

}
