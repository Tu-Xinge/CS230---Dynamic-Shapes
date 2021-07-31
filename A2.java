package cs230.A2Code;/*
 *  ============================================================================================
 *  A2.java : Extends JFrame and contains a panel where shapes move around on the screen.
 *  YOUR UPI: xtu424
 *  ============================================================================================
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class A2  extends JFrame {
	private AnimationViewer panel;  // panel for bouncing area
	JComboBox<ShapeType> shapesComboBox;
	JComboBox<PathType> pathComboBox;
	JTextField heightText, widthText;
	JButton borderButton, fillButton;

	/** main method for A2 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new A2();
			}
		});
	}
	/** constructor to initialise components */
	public A2() {
		super("Bouncing Application");
		panel = new AnimationViewer(true);
		add(panel, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(Shape.DEFAULT_MARGIN_WIDTH, Shape.DEFAULT_MARGIN_HEIGHT));
		add(setUpToolsPanel(), BorderLayout.NORTH);
		addComponentListener(
			new ComponentAdapter() { // resize the frame and reset all margins for all shapes
				public void componentResized(ComponentEvent componentEvent) {
					panel.resetMarginSize();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		setLocation((d.width - frameSize.width) / 2, (d.height - frameSize.height) / 2);
		pack();
		setVisible(true);
	}
	public JPanel setUpToolsPanel() {
		JPanel toolsPanel = new JPanel();
		toolsPanel.setLayout(new BoxLayout(toolsPanel, BoxLayout.X_AXIS));

		shapesComboBox = new JComboBox<ShapeType>();
		shapesComboBox.setModel(new DefaultComboBoxModel<ShapeType>(ShapeType.values()));
		shapesComboBox.setToolTipText("Set shape");
		shapesComboBox.addActionListener(new ShapeActionListener());

		pathComboBox = new JComboBox<PathType>();
		pathComboBox.setModel(new DefaultComboBoxModel<PathType>(PathType.values()));
		pathComboBox.setToolTipText("Set path");
		pathComboBox.addActionListener(new PathActionListener());
		

		heightText = new JTextField(""+ Shape.DEFAULT_HEIGHT);
		heightText.setToolTipText("Set Height (" + Shape.DEFAULT_MARGIN_HEIGHT/2 + ")");
		heightText.addActionListener(new HeightActionListener());
		

		widthText = new JTextField(""+ Shape.DEFAULT_WIDTH);
		widthText.setToolTipText("Set Width (" + Shape.DEFAULT_MARGIN_WIDTH/2 + ")");
		widthText.addActionListener(new WidthActionListener());

		fillButton = new JButton("Fill");
		fillButton.setToolTipText("Set Fill Color");
		fillButton.setForeground(panel.getCurrentFillColor());
		fillButton.addActionListener(new FillActionListener());

		borderButton = new JButton("Border");
		borderButton.setToolTipText("Set Border Color");
		borderButton.setForeground(panel.getCurrentBorderColor());
		borderButton.addActionListener(new BorderActionListener());
		
		toolsPanel.add(new JLabel(" Shape: ", JLabel.RIGHT));
		toolsPanel.add(shapesComboBox);
		toolsPanel.add(new JLabel(" Path: ", JLabel.RIGHT));
		toolsPanel.add(pathComboBox);
		toolsPanel.add(new JLabel(" Width: ", JLabel.RIGHT));
		toolsPanel.add(widthText);
		toolsPanel.add( new JLabel(" Height: ", JLabel.RIGHT));
		toolsPanel.add(heightText);
		toolsPanel.add(borderButton);
		toolsPanel.add(fillButton);
		return toolsPanel;
	
	}

	//Complete this: set up actionListener -- T
	class ShapeActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			panel.setCurrentShapeType(shapesComboBox.getSelectedIndex());
			// getActionCommand()
		}
	}

	class BorderActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Color newColor = JColorChooser.showDialog(null, "Fill Color", panel.getCurrentBorderColor()); 
			System.out.printf("The selected color is : %s\n",newColor);
			if (newColor!=null){ 
				borderButton.setForeground(newColor);
				panel.setCurrentBorderColor(newColor);
			}else{
				borderButton.setForeground(panel.getCurrentBorderColor());
				panel.setCurrentBorderColor(panel.getCurrentBorderColor());
			}
			
		}
			
	}

	class  FillActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Color newColor = JColorChooser.showDialog(null, "Fill Color", panel.getCurrentFillColor()); 
			System.out.printf("The selected color is : %s\n",newColor);
			if (newColor!=null){ 
				fillButton.setForeground(newColor);
				panel.setCurrentFillColor(newColor);
			}else{
				fillButton.setForeground(panel.getCurrentFillColor());
				panel.setCurrentFillColor(panel.getCurrentFillColor());
			}
			
		}
			
	}

	class HeightActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try {Integer.parseInt(heightText.getText());} 
			catch (NumberFormatException f) 
			{heightText.setText(panel.getCurrentHeight()+"");}
			
			int a = Integer.parseInt(heightText.getText());
			if(1<=a && a <= Shape.DEFAULT_MARGIN_HEIGHT / 2){
			   panel.setCurrentHeight(a);
			} else heightText.setText(panel.getCurrentHeight()+"");
			
		}
			
	}

	class PathActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			panel.getCurrentPathType();
			panel.setCurrentPathType(pathComboBox.getSelectedIndex());
		}
	}

	class WidthActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try {Integer.parseInt(widthText.getText());} 
			catch (NumberFormatException f) 
			{widthText.setText(panel.getCurrentWidth()+"");}
			
			int a = Integer.parseInt(widthText.getText());
			if(1<=a && a <= Shape.DEFAULT_MARGIN_WIDTH / 2){
			   panel.setCurrentWidth(a);
			} else widthText.setText(panel.getCurrentWidth()+"");
			
		}
			
	}

}

