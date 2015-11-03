package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import controller.Command;
import controller.CommandList;
import controller.DirCommand;
import controller.DisplayCrossSectionCommand;
import controller.DisplayMazeCommand;
import controller.DisplaySolutionCommand;
import controller.FileSizeCommand;
import controller.Generate3DMazeCommand;
import controller.LoadMazeCommand;
import controller.MazeSizeCommand;
import controller.SaveMazeCommand;
import model.Model;
import model.MyModel;
import solution.Solution;
import view.View;

// TODO: Auto-generated Javadoc
/**
 * The Class Presenter.
 */
public class Presenter implements Observer {

	/** The model. */
	Model model;
	
	/** The ui. */
	View ui;
	
	/** The command list. */
	HashMap<String, Command> commandList;


	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {

		if (o instanceof MyModel)
		{
			String command = ((String) arg);
			switch (command)
			{
			case "dir":
			{
				ui.displayDirectory((String[]) model.getCommandData().get("dir"));
			} 	break;
			case "generated":
			{
				ui.displayMazeGUI(model.getMazeList().get(model.getCommandData().get("generated")));
			}	break;
			case "crossed":
			{
				ui.displayCrossSection((int[][]) model.getCommandData().get("crossed"));
			}	break;
			case "calcedMazeSize":
			{
				ui.displaySize((String) model.getCommandData().get("maze"), (double) model.getCommandData().get("calcedMazeSize"));
			}	break;
			case "loaded":
			{
			}	break;
			case "saved":
			{
			}	break;
			case "solved":
			{
				ui.setSolution((Solution) model.getSolutionList().get(model.getMazeList().get(model.getCommandData().get("solved"))));
				ui.display("Solution for maze: "+model.getCommandData().get("solved")+" is ready");
				ui.setShowSol(true);
			}	break;
			case "notify":
			{
				ui.display((String) model.getCommandData().get("notify"));
			}	break;
			case "quit":
			{
				ui.display((String) model.getCommandData().get("quit"));
			}	break;
			case "saveZip":
			{
				ui.display((String) model.getCommandData().get("saveZip"));
			}	break;
			case "loadZip":
			{
				ui.display((String) model.getCommandData().get("loadZip"));
			}	break;
				

			default:
				ui.display("ERROR!!!");
				break;
			}
		}
		else
		{
			if (o instanceof View)
			{

				invokeCommand((String)arg);
			}
			else
			{
				System.out.println("ERROR");
				return;
			}
		}
	}

	/**
	 * Instantiates a new presenter.
	 *
	 * @param m the m
	 * @param v the v
	 */
	public Presenter(Model m, View v) {
		this.ui=v;
		this.model=m;
		this.commandList= new HashMap<String, Command>();
		commandList.put("dir", new DirCommand());
		commandList.put("generate",new Generate3DMazeCommand());
		commandList.put("displayMaze",new DisplayMazeCommand());
		commandList.put("displayCrossSection",new DisplayCrossSectionCommand());
		commandList.put("save",new SaveMazeCommand());
		commandList.put("load",new LoadMazeCommand());
		commandList.put("mazeSize",new MazeSizeCommand());
		commandList.put("fileSize",new FileSizeCommand());
		commandList.put("solve",new Command() {
			
			@Override
			public void doCommand(String name, View v, Model m) {
				m.solveMaze(name);
				
			}
		});
		commandList.put("setProp", new Command() {
			
			@Override
			public void doCommand(String args, View v, Model m) {
				String[] properties= args.split(" ");
				model.changeProp(properties);
			}
		});
		commandList.put("displaySolution",new DisplaySolutionCommand());
		commandList.put("menu",new CommandList());
		commandList.put("exit", new Command(){
			@Override
			public void doCommand(String command, View v, Model m) {
				model.officialExit();
			}
		});
	}
	
	
	
	/**
	 * Display.
	 *
	 * @param s the s
	 */
	public void display(String s){
		ui.display(s);
	}
	
	/**
	 * Gets the command list.
	 *
	 * @return the command list
	 */
	public HashMap<String, Command> getCommandList(){
		return this.commandList;
	}
	
	/**
	 * Invoke command.
	 *
	 * @param command the command
	 */
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
			cmd.doCommand(args,ui, model);
		
		else
			ui.display("Command not found");
			
	}
	

	/**
	 * Select command.
	 *
	 * @param commandName the command name
	 * @return the command
	 */
	public Command selectCommand(String commandName){
			
		return commandList.get(commandName);
	}
}