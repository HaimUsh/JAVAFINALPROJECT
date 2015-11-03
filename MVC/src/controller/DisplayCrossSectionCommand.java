package controller;

import model.Model;
import view.View;


// TODO: Auto-generated Javadoc
/**
 * The Class DisplayCrossSectionCommand.
 */
public class DisplayCrossSectionCommand implements Command {

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String, view.View, model.Model)
	 */
	@Override
	public void doCommand(String args,View v,Model m) {

		String[] s=args.split(" ");
		char crossBy=s[0].charAt(0);
		int index=Integer.parseInt(s[1]);
		String name=s[2];
		int [][]cs = m.getCrossSection(crossBy, index, name);
		v.displayCrossSection(cs);



	}

}
