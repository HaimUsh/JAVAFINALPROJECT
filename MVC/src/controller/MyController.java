package controller;

import java.util.HashMap;

import model.Model;
import view.View;


// TODO: Auto-generated Javadoc
/**
 * The Class MyController.
 */
public class MyController implements Controller {


	/** The v. */
	View v;
	
	/** The m. */
	Model m;
	
	/** The commands. */
	HashMap<String, Command> commands;

	/**
	 * Instantiates a new my controller.
	 *
	 * @param v the v
	 * @param m the m
	 */
	public MyController(View v,Model m) {
		this.v=v;
		this.m=m;
		commands= new HashMap<String, Command>();
		commands.put("dir",new DirCommand());
		commands.put("generate3DMaze",new Generate3DMazeCommand());
		commands.put("displayMaze",new DisplayMazeCommand());
		commands.put("displayCrossSection",new DisplayCrossSectionCommand());
		commands.put("saveMaze",new SaveMazeCommand());
		commands.put("loadMaze",new LoadMazeCommand());
		commands.put("mazeSize",new MazeSizeCommand());
		commands.put("fileSize",new FileSizeCommand());
		commands.put("solveMaze",new SolveMazeCommand());
		commands.put("displaySolution",new DisplaySolutionCommand());
		commands.put("menu",new CommandList());
	}

	/* (non-Javadoc)
	 * @see controller.Controller#display(java.lang.String)
	 */
	@Override
	public void display(String s) {
		v.display(s);

	}

	/* (non-Javadoc)
	 * @see controller.Controller#getMap()
	 */
	@Override
	public HashMap<String, Command> getMap() {
		return this.commands;
	}

	/* (non-Javadoc)
	 * @see controller.Controller#invokeCommand(java.lang.String)
	 */
	@Override
	public void invokeCommand(String command) {
		String[] sp = command.split(" ");

		String commandName = sp[0];
		
		String args = null;
		if (sp.length > 1){
			args = new String();
			for (int i = 1; i < sp.length; i++)
				if(i==1)
					args = sp[i];
				else
					args += " " + sp[i];
		}
	
		Command cmd = selectCommand(commandName);
		if (cmd != null)
			cmd.doCommand(args,v, m);
		else
			v.display("Command not found");
			
	}
	
	/**
	 * Select command.
	 *
	 * @param commandName the command name
	 * @return the command
	 */
	public Command selectCommand(String commandName){
			
		return commands.get(commandName);
	}
	
	

}


