import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Display extends JPanel{
	JLabel footnote;
	String footnoteMessage = "feafg";
	GraphPanel graphPanel;
	//ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	public Display(){
		setLayout(new BorderLayout());
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 5, 20, 0));
		
		JButton selectButton = new JButton("Select");
		selectButton.addActionListener(new SelectListener());
		JButton addVertexButton = new JButton("Add Vertex");
		addVertexButton.addActionListener(new AddVertexListener());
		JButton addEdgeButton = new JButton("Add Edge");
		addEdgeButton.addActionListener(new AddEdgeListener());
		JButton fireButton = new JButton("Fire Vertex");
		fireButton.addActionListener(new FireListener());
		buttonPanel.add(selectButton);
		buttonPanel.add(addVertexButton);
		buttonPanel.add(addEdgeButton);
		buttonPanel.add(fireButton);
		add(buttonPanel, BorderLayout.NORTH);
		
		graphPanel = new GraphPanel();
		add(graphPanel, BorderLayout.CENTER);
		
		JPanel rightSidebar = new JPanel();
		rightSidebar.add(new JLabel("stuff goes here, print Laplacian matrix maybe?"));
		add(rightSidebar, BorderLayout.EAST);
		
		footnote = new JLabel("Choose an action");
		footnote.setHorizontalAlignment(SwingConstants.CENTER);
		add(footnote, BorderLayout.SOUTH);
	}
	
	public class SelectListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			footnote.setText("Select mode");
			graphPanel.actionNum = 0;
		}
	}
	private class AddVertexListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			footnote.setText("Click to add vertex");
			graphPanel.actionNum = 1;
		}
	}
	
	private class AddEdgeListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			footnote.setText("Click 2 vertices to add edge");
			graphPanel.actionNum = 2;
		}
	}
	
	private class FireListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			footnote.setText("Choose vertex to fire");
			graphPanel.actionNum = 4;
		}
	}
}
