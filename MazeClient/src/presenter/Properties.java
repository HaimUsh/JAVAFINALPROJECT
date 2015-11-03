package presenter;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Properties.
 */
public class Properties implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The num of threads. */
	private int numOfThreads;

	/** The generate algo. */
	private String generateAlgo;

	/** The solve algo. */
	private String solveAlgo;

	/** The game interface. */
	private String gameInterface;
	
	/** The port. */
	private int port;
	
	/** The ip. */
	private String ip;

	/**
	 * Instantiates a new properties.
	 */
	public Properties() {
		
		this.numOfThreads=10;
		this.generateAlgo="my";
		this.solveAlgo="bfs";
		this.gameInterface="gui";
		this.ip="localhost";
		this.port=5400;
	}
	
	/**
	 * Gets the generate algo.
	 *
	 * @return the generate algo
	 */
	public String getGenerateAlgo() {
		return generateAlgo;
	}
	
	/**
	 * Sets the generate algo.
	 *
	 * @param generateAlgo the new generate algo
	 */
	public void setGenerateAlgo(String generateAlgo) {
		this.generateAlgo = generateAlgo;
	}
	
	/**
	 * Gets the solve algo.
	 *
	 * @return the solve algo
	 */
	public String getSolveAlgo() {
		return solveAlgo;
	}
	
	/**
	 * Sets the solve algo.
	 *
	 * @param solveAlgo the new solve algo
	 */
	public void setSolveAlgo(String solveAlgo) {
		this.solveAlgo = solveAlgo;
	}
	
	/**
	 * Gets the game interface.
	 *
	 * @return the game interface
	 */
	public String getGameInterface() {
		return gameInterface;
	}
	
	/**
	 * Sets the game interface.
	 *
	 * @param gameInterface the new game interface
	 */
	public void setGameInterface(String gameInterface) {
		this.gameInterface = gameInterface;
	}
	
	/**
	 * Gets the num of threads.
	 *
	 * @return the num of threads
	 */
	public int getNumOfThreads() {
		return numOfThreads;
	}
	
	/**
	 * Sets the num of threads.
	 *
	 * @param numOfThreads the new num of threads
	 */
	public void setNumOfThreads(int numOfThreads) {
		this.numOfThreads = numOfThreads;
	}
	
	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public int getPort() {
		return port;
	}
	
	/**
	 * Sets the port.
	 *
	 * @param port the new port
	 */
	public void setPort(int port) {
		this.port = port;
	}
	
	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * Sets the ip.
	 *
	 * @param ip the new ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	/**
	 * Load preferences.
	 */
	public void loadPreferences(){
		try {
			XMLDecoder xmlDe = new XMLDecoder(new FileInputStream("preferences.xml"));
			Properties p  = (Properties) xmlDe.readObject();
			xmlDe.close();

			setNumOfThreads(p.getNumOfThreads());
			setGameInterface(p.getGameInterface());
			setGenerateAlgo(p.getGenerateAlgo());
			setSolveAlgo(p.solveAlgo);
			setIp(p.ip);
			setPort(p.port);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Save preferences.
	 */
	public void savePreferences(){
		try {
			XMLEncoder xmlEn = new XMLEncoder(new FileOutputStream("preferences.xml"));
			xmlEn.writeObject(this);
			xmlEn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
