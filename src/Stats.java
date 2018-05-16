//code based on following tutorial
//https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDialogRunnerProject/src/components/ListDialog.java

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class Stats extends JDialog implements ActionListener {
    private static Stats dialog;
    private static String value = "";
    private JList list;
 
    public static String showDialog(Component frameComp, String labelText, String title, String[] 
    		possibleValues, String initialValue) {
        Frame frame = JOptionPane.getFrameForComponent(frameComp);
        int max = 0, index = 0;
        String longValue = "";
        for(int i = 0; i < possibleValues.length; i++) {
        	if((possibleValues[i]).length() > max)  { 
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
	private Stats(Frame frame, Component locationComp, String labelText, String title, Object[] data, String initialValue,
			String longValue) {
        super(frame, title, true);
 
        //main part of the dialog
        list = new JList(data) {
            public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
                int row;
                if (orientation == SwingConstants.VERTICAL &&
                      direction < 0 && (row = getFirstVisibleIndex()) != -1) {
                    Rectangle r = getCellBounds(row, row);
                    if ((r.y == visibleRect.y) && (row != 0))  {
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
 
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (longValue != null) {
            list.setPrototypeCellValue(longValue); //get extra space
        }
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));
        listScroller.setAlignmentX(LEFT_ALIGNMENT);
 
        //Create and initialize the buttons.
        JButton closeButton = new JButton("Close");
        closeButton.setActionCommand("CloseWindow");
        closeButton.addActionListener(this);
               
        //Lay out the label and scroll pane from top to bottom.
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel(labelText);
        label.setLabelFor(list);
        listPane.add(label);
        listPane.add(Box.createRigidArea(new Dimension(0,5)));
        listPane.add(listScroller);
        listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
 
        //Lay out the buttons from left to right.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(closeButton);
 
        //Put everything together, using the content pane's BorderLayout.
        Container contentPane = getContentPane();
        contentPane.add(listPane, BorderLayout.CENTER);
        contentPane.add(buttonPane, BorderLayout.PAGE_END);
 
        //Initialize values.
        setValue(initialValue);
        pack();
        setLocation(locationComp.getWidth() + 5, 0);
    }
 
    //Handle clicks on the Set and Close buttons.
    public void actionPerformed(ActionEvent e) {
    	switch(e.getActionCommand()) {
            case "CloseWindow": setVisible(false); break;
    	}
        setVisible(false);
    }
}