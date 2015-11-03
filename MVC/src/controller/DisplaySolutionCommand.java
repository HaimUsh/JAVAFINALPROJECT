package controller;

import model.Model;
import solution.Solution;
import view.View;


// TODO: Auto-generated Javadoc
/**
 * The Class DisplaySolutionCommand.
 */
public class DisplaySolutionCommand implements Command {

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String, view.View, model.Model)
	 */
	@Override
	public void doCommand(String name,View v,Model m) {
	
		if(m.getSolutionList().containsKey(name)){
			Solution sol=m.getSolutionList().get(name);
			v.displaySolution(sol);
		}
		v.display("no solution!!");

	}

}
