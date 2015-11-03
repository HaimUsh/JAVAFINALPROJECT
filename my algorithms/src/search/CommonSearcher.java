package search;

import java.util.Comparator;
import java.util.PriorityQueue;

import solution.Solution;
import stateComparator.StateComparator;
import stateComparator.StateCostComparator;
import states.State;
// TODO: Auto-generated Javadoc

/**
 * The Class CommonSearcher.
 */
public abstract class CommonSearcher implements Searcher {

	/** The open list. */
	protected PriorityQueue<State> openList;
	
	/** The evaluated nodes. */
	private int evaluatedNodes;

	/* (non-Javadoc)
	 * @see search.Searcher#search(search.Searchable)
	 */
	@Override
	public abstract Solution search(Searchable s);

	/* (non-Javadoc)
	 * @see search.Searcher#getNumberOfNodesEvaluated()
	 */
	@Override
	public int getNumberOfNodesEvaluated()
	{
		return evaluatedNodes;
	}
	
	/**
	 * Instantiates a new common searcher.
	 */
	public CommonSearcher()
	{
		StateComparator sc = new StateCostComparator();
		Comparator<State> comparator = (Comparator<State>)sc;
		openList=new PriorityQueue<State>(1, comparator);
		setEvaluatedNodes(0);
	}
	
	/**
	 * Instantiates a new common searcher.
	 *
	 * @param sc the sc
	 */
	public CommonSearcher(StateComparator sc)
	{
		Comparator<State> comparator = (Comparator<State>)sc;
		openList=new PriorityQueue<State>(1, comparator);
		setEvaluatedNodes(0);
	}
	
	/**
	 * Pop open list.
	 *
	 * @return the state
	 */
	protected State popOpenList()
	{
		evaluatedNodes++;
		return openList.poll();
	}
	
	/**
	 * Gets the evaluated nodes.
	 *
	 * @return the evaluated nodes
	 */
	public int getEvaluatedNodes() 
	{
		return evaluatedNodes;
	}
	
	/**
	 * Sets the evaluated nodes.
	 *
	 * @param evaluatedNodes the new evaluated nodes
	 */
	public void setEvaluatedNodes(int evaluatedNodes)
	{
		this.evaluatedNodes = evaluatedNodes;
	}
	
	/**
	 * Adds the to open list.
	 *
	 * @param s1 the s1
	 */
	public void addToOpenList(State s1)
	{
		State s2 = new State(s1);
		s2.toString();
		openList.add(s2);
	}
	
	/**
	 * Back trace.
	 *
	 * @param goalState the goal state
	 * @param startState the start state
	 * @return the solution
	 */
	protected Solution backTrace(State goalState, State startState) 
	{
		State s1 = new State(startState);
		State s2 = new State(goalState);

		Solution solution1 = new Solution();
		while (!s2.equals(s1)) 
		{
			solution1.getSolutionList().add(s2.getstate());
			State sTemp = new State (s2);
			s2 = new State(sTemp.getcameFrom());
		}
		solution1.getSolutionList().add(startState.getstate());
		solution1.sortback();
		solution1.print();
		return solution1;
	}
}
