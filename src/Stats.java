
//Shows player statistics in dialog box
//Code based on following tutorial
//https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDialogRunnerProject/src/components/ListDialog.java

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Stats extends JDialog implements ActionListener {
	private static Stats dialog;
	private static String value = "";
	private JList list;

	// initialize values to be displayed
	public static String showDialog(Component frameComp, String labelText, String title, String[] possibleValues,
			String initialValue) {
		Frame frame = JOptionPane.getFrameForComponent(frameComp);
		int max = 0, index = 0;
		String longValue = "";
		for (int i = 0; i < possibleValues.length; i++) {
			if ((possibleValues[i]).length() > max) {
				max = (possibleValues[i]).length();
				index = i;
			}
		}
		longValue = (possibleValues[index]);
		dialog = new Stats(frame, frame, labelText, title, possibleValues, initialValue, longValue);
		dialog.setVisible(true);
		return value;
	}

	private void setValue(String newValue) {
		value = newValue;
		list.setSelectedValue(value, true);
	}

	@SuppressWarnings("unchecked")
	private Stats(Frame frame, Component locationComp, String labelText, String title, Object[] data,
			String initialValue, String longValue) {
		super(frame, title, true);

		// create list
		list = new JList(data) {
			public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
				int row;
				if (orientation == SwingConstants.VERTICAL && direction < 0 && (row = getFirstVisibleIndex()) != -1) {
					Rectangle r = getCellBounds(row, row);
					if ((r.y == visibleRect.y) && (row != 0)) {
						Point loc = r.getLocation();
						loc.y--;
						int prevIndex = locationToIndex(loc);
						Rectangle prevR = getCellBounds(prevIndex, prevIndex);

						if (prevR == null || prevR.y >= r.y) {
							return 0;
						}
						return prevR.height;
					}
				}
				return super.getScrollableUnitIncrement(visibleRect, orientation, direction);
			}
		};

		// display settings
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		if (longValue != null) {
			list.setPrototypeCellValue(longValue);
		}
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 80));
		listScroller.setAlignmentX(LEFT_ALIGNMENT);

		// buttons
		JButton closeButton = new JButton("Close");
		closeButton.setActionCommand("CloseWindow");
		closeButton.addActionListener(this);

		// layout and display
		JPanel listPane = new JPanel();
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
		JLabel label = new JLabel(labelText);
		label.setLabelFor(list);
		listPane.add(label);
		listPane.add(Box.createRigidArea(new Dimension(0, 5)));
		listPane.add(listScroller);
		listPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(closeButton);

		Container contentPane = getContentPane();
		contentPane.add(listPane, BorderLayout.CENTER);
		contentPane.add(buttonPane, BorderLayout.PAGE_END);

		setValue(initialValue);
		pack();
		setLocation(locationComp.getWidth() + 5, 0);
	}

	// close window with button
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "CloseWindow":
			setVisible(false);
			break;
		}
		setVisible(false);
	}
}