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
 * The Class MazeSettings.
 */
public class MazeSettings extends Dialog
{
	
	/** The value. */
	private Double value;
	
	/** The string value. */
	private String stringValue;
	
	/** The temp value. */
	private String tempValue;
	
	/** The total value. */
	private String totalValue;
	
	/** The verify data1. */
	private boolean verifyData1 = false;
	
	/** The verify data2. */
	private boolean verifyData2 = false;

	/**
	 * Instantiates a new maze settings.
	 *
	 * @param parent the parent
	 */
	public MazeSettings(Shell parent)
	{
		super(parent);
	}

	/**
	 * Instantiates a new maze settings.
	 *
	 * @param parent the parent
	 * @param style the style
	 */
	public MazeSettings(Shell parent, int style)
	{
		super(parent, style);
	}

	/**
	 * Makes the dialog visible.
	 *
	 * @return the string
	 */
	public String open()
	{
		Shell parent = getParent();
		final Shell shell =
				new Shell(parent, SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL);
		shell.setText("Choose Maze Size");

		shell.setLayout(new GridLayout(2, true));

		Label label = new Label(shell, SWT.NULL);
		label.setText("Please enter the maze size\n (4-15):");

		final Text text = new Text(shell, SWT.SINGLE | SWT.BORDER);

		Label label2 = new Label(shell, SWT.NULL);
		label2.setText("Please enter maze name:");

		final Text text2 = new Text(shell, SWT.SINGLE | SWT.BORDER);

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
					value = new Double(text.getText());;
					stringValue = new String(df.format(value));
					verifyData1 = true;
					if ((value>200) || (value<3))
					{
						verifyData1 = false;
						buttonOK.setEnabled(false);
					}

					if (verifyData1 && verifyData2)
						buttonOK.setEnabled(true);
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
					totalValue = new String(text2.getText());
					totalValue = totalValue+" "+stringValue;
					verifyData2 = true;
					if ((text2.getText() == null) || (text2.getText().length() > 20) || (text2.getText().length() < 2))
					{
						verifyData2 = false;
						buttonOK.setEnabled(false);
					}

					if (verifyData1 && verifyData2)
						buttonOK.setEnabled(true);
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
				if (stringValue == null) 
				{
					DecimalFormat df = new DecimalFormat("#.##");
					value = new Double(5.0);
					stringValue = new String("unNamed Maze");
					tempValue = new String(df.format(value));

					stringValue = stringValue+" "+tempValue;
					shell.dispose();
				}
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

				totalValue = stringValue+" "+tempValue;
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

		return totalValue;
	}
}
