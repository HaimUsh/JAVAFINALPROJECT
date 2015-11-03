package view;


import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


// TODO: Auto-generated Javadoc
/**
 * The Class BasicWindow.
 */
public abstract class BasicWindow extends Observable implements Runnable , View{
	
	/** The display. */
	Display display;
	
	/** The shell. */
	Shell shell;
	
 	/**
	  * Instantiates a new basic window.
	  *
	  * @param title the title
	  * @param width the width
	  * @param height the height
	  */
	 public BasicWindow(String title, int width,int height) {
 		display=new Display();
 		shell  = new Shell(display);
 		shell.setSize(width,height);
 		
 		shell.setText(title);
	}
 	
 	/**
	  * Inits the widgets.
	  */
	 abstract void initWidgets();

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		initWidgets();
		shell.open();
		
		 while(!shell.isDisposed()){ 

		    if(!display.readAndDispatch()){ 
		       display.sleep(); 			
		    }

		 } 

		 display.dispose(); 
	}
	

}
