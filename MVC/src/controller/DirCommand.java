package controller;

import java.io.File;

import model.Model;
import view.View;

// TODO: Auto-generated Javadoc
/**
 * The Class DirCommand.
 */
public class DirCommand implements Command {

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String, view.View, model.Model)
	 */
	@Override
	public void doCommand(String path,View v,Model m) {
		File file = new File(path);

		if (file.isFile()) 
			System.out.println(file);
		else 
			for (File f : file.listFiles()) 
				System.out.println(f);
	}

}


