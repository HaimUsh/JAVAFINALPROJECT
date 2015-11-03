package boot;

import model.MyModel;
import presenter.Presenter;
import presenter.Properties;
import view.MazeWindow;
import view.MyView;
import view.View;

// TODO: Auto-generated Javadoc
/**
 * The Class Run.
 */
public class Run {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {


		boolean flag = false;
		Properties prop= new Properties();
		prop.loadPreferences();
		MyModel model= new MyModel(prop.getNumOfThreads(),prop.getGenerateAlgo(),prop.getSolveAlgo(),prop.getGameInterface());
		View ui = new MazeWindow("the maze", 300,300);
		Presenter presenter = new Presenter(model, ui);
		if (flag) 
		{
			((MyView) ui).addObserver(presenter);
		}
		else
		{
			((MazeWindow) ui).addObserver(presenter);
		}
		((MazeWindow)ui).addObserver(presenter);
		model.addObserver(presenter);
		ui.start();
		
		



	}

}
