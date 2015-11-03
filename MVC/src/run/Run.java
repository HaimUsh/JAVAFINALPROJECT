package run;

import model.MyModel;
import view.MyView;
import controller.MyController;

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
		MyView v = new MyView();
		MyModel m = new MyModel();
		MyController c = new MyController(v,m);
		v.setController(c);
		m.setController(c);
		
		v.start();
	}

}
