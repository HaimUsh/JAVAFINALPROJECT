package stateComparator;

import java.util.Comparator;

import states.State;


// TODO: Auto-generated Javadoc
/**
 * The Interface StateComparator.
 */
public interface StateComparator extends Comparator<State> 
{
	
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(State s1, State s2);
}
