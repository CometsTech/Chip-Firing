import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

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
		rightSidebar.setLayout(new GridLayout(9, 2, 20, 20));
		JButton laplacianButton = new JButton("Print Laplacian");
		laplacianButton.addActionListener(new LaplacianListener());
		JButton increaseChipsButton = new JButton("Increase Chips");
		JButton decreaseChipsButton = new JButton("Decrease Chips");
		increaseChipsButton.addActionListener(new IncreaseChipsListener());
		decreaseChipsButton.addActionListener(new DecreaseChipsListener());
		JButton reducedDivisorButton = new JButton("Reduce Divisor");
		reducedDivisorButton.addActionListener(new ReducedDivisorListener());
		rightSidebar.add(laplacianButton);
		rightSidebar.add(increaseChipsButton);
		rightSidebar.add(decreaseChipsButton);
		rightSidebar.add(reducedDivisorButton);
		add(rightSidebar, BorderLayout.EAST);

		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1, 3, 20, 0));
		JButton saveButton = new JButton("Save Divisor");
		JButton loadButton = new JButton("Load Divisor");
		saveButton.addActionListener(new SaveDivisorListener());
		loadButton.addActionListener(new LoadDivisorListener());
		footnote = new JLabel("Choose an action");
		footnote.setHorizontalAlignment(SwingConstants.CENTER);
		bottomPanel.add(saveButton);
		bottomPanel.add(footnote);
		bottomPanel.add(loadButton);
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
	public class SelectListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			footnote.setText("Select node");
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

	private class LaplacianListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//System.out.println("Laplacian gets printed");
			ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>(graphPanel.vertices.size());
			for(Vertex v : graphPanel.vertices){
				ArrayList<Integer> row = new ArrayList<Integer>(graphPanel.vertices.size());
				for(int i = 0; i < graphPanel.vertices.size(); i++){
					int index = Integer.parseInt(v.getToolTipText());
					/*if(v.adjacencies.contains(i)){
						row.add(1);
					}*/
					if(i != index){
						row.add(Collections.frequency(v.adjacencies, i));
					}
					else if(i == index){
						row.add(-v.adjacencies.size());
					}
					//else row.add(0);
				}
				matrix.add(row);
			}
			//System.out.println(matrix.toString());
			System.out.println(matrix.toString().replaceAll("\\[", "{").replaceAll("\\]", "}"));
		}
	}

	private class IncreaseChipsListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			GraphPanel.selected.setChips(graphPanel.selected.chips + 1);
			//graphPanel.selected.getParent().repaint();
		}
	}

	private class DecreaseChipsListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
      GraphPanel.selected.setChips(graphPanel.selected.chips - 1);
			//graphPanel.selected.getParent().repaint();
		}
	}

	private class ReducedDivisorListener implements ActionListener{
		 public void actionPerformed(ActionEvent e){
			graphPanel.dhar(graphPanel.selected); //XXX rethink statics
		}
	}

	private class SaveDivisorListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			graphPanel.savedDivisor = new ArrayList<Integer>();
			for(Vertex v : graphPanel.vertices){
				graphPanel.savedDivisor.add(v.chips);
			}
			footnote.setText("Divisor saved");
		}
	}

	private class LoadDivisorListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			for(int i = 0; i < graphPanel.vertices.size(); i++){
				graphPanel.vertices.get(i).setChips(graphPanel.savedDivisor.get(i));
			}
			footnote.setText("Divisor loaded");
		}
	}
}
