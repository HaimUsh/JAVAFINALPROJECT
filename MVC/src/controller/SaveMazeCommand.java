package controller;

import model.Model;
import view.View;


// TODO: Auto-generated Javadoc
/**
 * The Class SaveMazeCommand.
 */
public class SaveMazeCommand implements Command {

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String, view.View, model.Model)
	 */
	@Override
	public void doCommand(String args,View v,Model m) {
		
		String[] nameandfile=args.split(" ");
		m.saveMaze(nameandfile[0], nameandfile[1]);
		v.display("maze saved!");
		
	}

}
