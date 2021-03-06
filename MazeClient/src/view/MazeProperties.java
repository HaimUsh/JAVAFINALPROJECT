package view;

import java.text.DecimalFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


// TODO: Auto-generated Javadoc
/**
 * The Class MazeProperties.
 */
public class MazeProperties extends Dialog {
	

	/** The value. */
	private Double value;
	
	/** The string value. */
	private String stringValue;
	
	/** The temp value. */
	private String tempValue;
	
	/** The number of thread stringi. */
	private String numberOfThreadStringi;
	
	/** The my interface. */
	private String myInterface;
	
	/** The generation algorithm. */
	private String generationAlgorithm;
	
	/** The solve algorithm. */
	private String solveAlgorithm;
	
	/** The verify data1. */
	private boolean verifyData1 = false;
	
	/** The verify data2. */
	private boolean verifyData2 = false;
	
	/** The verify data3. */
	private boolean verifyData3 = false;
	
	/** The verify data4. */
	private boolean verifyData4 = false;

	/**
	 * Instantiates a new maze properties.
	 *
	 * @param parent the parent
	 */
	public MazeProperties(Shell parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Instantiates a new maze properties.
	 *
	 * @param parent the parent
	 * @param style the style
	 */
	public MazeProperties(Shell parent, int style) 
	{
		super(parent, style);
	}
	
	/**
	 * Open.
	 *
	 * @return the string
	 */
	public String open() 
	{
		Shell parent = getParent();
		final Shell shell = new Shell(parent, SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL);
		shell.setText("Properties");

		shell.setLayout(new GridLayout(2, true));

		Label label = new Label(shell, SWT.NULL);
		label.setText("Enter number of threads:");
		final Text text = new Text(shell, SWT.SINGLE | SWT.BORDER);

		Label label2 = new Label(shell, SWT.NULL);
		label2.setText("Enter Maze Generation Algorithm:\n <simple/recursive>");
		final Text text2 = new Text(shell, SWT.SINGLE | SWT.BORDER);

		Label label3 = new Label(shell, SWT.NULL);
		label3.setText("Enter Maze Solve Algorithm:\n <bfs/astarman/astarair>");
		final Text text3 = new Text(shell, SWT.SINGLE | SWT.BORDER);

		Label label4 = new Label(shell, SWT.NULL);
		label4.setText("Enter interface:\n <gui/cli>");
		final Text text4 = new Text(shell, SWT.SINGLE | SWT.BORDER);

		final Button buttonOK = new Button(shell, SWT.PUSH);
		buttonOK.setText("Ok");
		buttonOK.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		Button buttonCancel = new Button(shell, SWT.PUSH);
		buttonCancel.setText("Cancel");

		text.addListener(SWT.Modify, new Listener()
		{
			public void handleEvent(Event event)
			{
				try 
				{
					DecimalFormat df = new DecimalFormat("#.##");
					value = new Double(text.getText());
					if ((value>0) && (value<80000))
					{
						numberOfThreadStringi = new String(df.format(value));
						verifyData1 = true;
						stringValue = numberOfThreadStringi+" "+myInterface+" "+generationAlgorithm+" "+solveAlgorithm;

						if (verifyData1 && verifyData2 && verifyData3 && verifyData4)
						{
							buttonOK.setEnabled(true);
						}
						else{}
					}
					else
					{
						verifyData1 = false;
						buttonOK.setEnabled(false);
					}
				} 
				catch (Exception e) 
				{
					buttonOK.setEnabled(false);
				}
			}
		});

		text2.addListener(SWT.Modify, new Listener()
		{
			public void handleEvent(Event event)
			{
				try 
				{
					myInterface = new String(text2.getText());
					if (myInterface.equalsIgnoreCase("cli") || myInterface.equalsIgnoreCase("gui"))
					{
						verifyData2 = true;
						stringValue = numberOfThreadStringi+" "+myInterface+" "+generationAlgorithm+" "+solveAlgorithm;

						if (verifyData1 && verifyData2 && verifyData3 && verifyData4)
						{
							buttonOK.setEnabled(true);
						}
						else{}
					}
					else
					{
						verifyData2 = false;
						buttonOK.setEnabled(false);
					}
				}
				catch (Exception e) 
				{
					buttonOK.setEnabled(false);
				}
			}
		});

		text3.addListener(SWT.Modify, new Listener()
		{
			public void handleEvent(Event event)
			{
				try 
				{
					generationAlgorithm = new String(text3.getText());
					if (generationAlgorithm.equalsIgnoreCase("simple") || generationAlgorithm.equalsIgnoreCase("recursive"))
					{
						verifyData3 = true;
						stringValue = numberOfThreadStringi+" "+myInterface+" "+generationAlgorithm+" "+solveAlgorithm;

						if (verifyData1 && verifyData2 && verifyData3 && verifyData4)
						{
							buttonOK.setEnabled(true);
						}
						else{}
					}
					else
					{
						verifyData3 = false;
						buttonOK.setEnabled(false);
					}

				}
				catch (Exception e) 
				{
					buttonOK.setEnabled(false);
				}
			}
		});

		text4.addListener(SWT.Modify, new Listener()
		{
			public void handleEvent(Event event)
			{
				try 
				{
					solveAlgorithm = new String(text4.getText());
					if (solveAlgorithm.equalsIgnoreCase("bfs") || solveAlgorithm.equalsIgnoreCase("astarman")|| solveAlgorithm.equalsIgnoreCase("astarair"))
					{
						verifyData4 = true;
						stringValue = numberOfThreadStringi+" "+myInterface+" "+generationAlgorithm+" "+solveAlgorithm;

						if (verifyData1 && verifyData2 && verifyData3 && verifyData4)
						{
							buttonOK.setEnabled(true);
						}
						else{}
					}
					else
					{
						verifyData4 = false;
						buttonOK.setEnabled(false);
					}
				}
				catch (Exception e) 
				{
					buttonOK.setEnabled(false);
				}
			}
		});

		buttonOK.addListener(SWT.Selection, new Listener() 
		{
			public void handleEvent(Event event)
			{
				shell.dispose();
			}
		});

		buttonCancel.addListener(SWT.Selection, new Listener() 
		{
			public void handleEvent(Event event) 
			{
				DecimalFormat df = new DecimalFormat("#.##");
				value = new Double(5.0);
				stringValue = new String("unNamed Maze");
				tempValue = new String(df.format(value));
				stringValue = stringValue+" "+tempValue;
				shell.dispose();
			}
		});

		shell.addListener(SWT.Traverse, new Listener()
		{
			public void handleEvent(Event event)
			{
				if(event.detail == SWT.TRAVERSE_ESCAPE)
					event.doit = false;
			}
		});

		text.setText("");
		shell.pack();
		shell.open();

		Display display = parent.getDisplay();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
				display.sleep();
		}
		return stringValue;
	}
}
	
	

