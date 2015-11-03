package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

// TODO: Auto-generated Javadoc
/**
 * The Class FinishGesture.
 */
public class FinishGesture implements BasicCharacter {

	/**
	 * Instantiates a new finish gesture.
	 */
	public FinishGesture() {}
	
	/* (non-Javadoc)
	 * @see view.BasicCharacter#moveChar(org.eclipse.swt.graphics.GC, int, int, int, int)
	 */
	@Override
	public void moveChar(GC gc, int pointX, int pointY, int width, int height) 
	{
		try{
		Image i = new Image(null,new FileInputStream("./resources/win.png"));
		gc.drawImage(i, pointX, pointY);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}