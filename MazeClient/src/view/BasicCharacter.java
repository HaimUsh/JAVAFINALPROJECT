package view;

import org.eclipse.swt.graphics.GC;

// TODO: Auto-generated Javadoc
/**
 * The Interface BasicCharacter.
 */
public interface BasicCharacter {
	
	/**
	 * Move char.
	 *
	 * @param gc the gc
	 * @param pointX the point x
	 * @param pointZ the point z
	 * @param width the width
	 * @param height the height
	 */
	public void moveChar(GC gc, int pointX, int pointZ,int width, int height);

}
