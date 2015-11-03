package controller;

import model.Model;
import view.View;


// TODO: Auto-generated Javadoc
/**
 * The Class Generate3DMazeCommand.
 */
public class Generate3DMazeCommand implements Command {

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String, view.View, model.Model)
	 */
	@Override
	public void doCommand(String args,View v,Model m) {
		String[] nameandsize = args.split(" ");
		m.generate3DMaze(nameandsize[0], Integer.parseInt(nameandsize[1]));
		v.display("maze"+" "+ nameandsize[0]+" " +"is ready !");
	}
}
