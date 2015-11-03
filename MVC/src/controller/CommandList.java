package controller;

import model.Model;
import view.View;



// TODO: Auto-generated Javadoc
/**
 * The Class CommandList.
 */
public class CommandList implements Command {

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String, view.View, model.Model)
	 */
	@Override
	public void doCommand(String command,View v,Model m) {
		v.display("\nList of commands:");
		v.display("  dir <path>");
		v.display("  generate3DMaze <name> <size>");
		v.display("  displayMaze <name>");
		v.display("  displayCrossSection <x/y/z> <index> for <name>");
		v.display("  saveMaze <name> <file name>");
		v.display("  loadMaze <file name> <file>");
		v.display("  mazeSize <name>");
		v.display("  fileSize <name>");
		v.display("  solve <name> <algorithm (BFS / ASTARAIR / ASTARMAN>");
		v.display("  displaySolution <name>");
		v.display("  menu");
		v.display("  exit");

	}
}
