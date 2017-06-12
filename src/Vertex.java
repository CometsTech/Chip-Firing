import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class Vertex extends JButton{
	private int radius = 25;
	int x, y, chips;
	GraphPanel panel;
	
	public ArrayList<Vertex> adjacencies = new ArrayList<Vertex>();
	public static int index1;
	
	Font font = new Font("Serif", Font.BOLD, 12);
	Insets insets = getInsets();
	
	public Vertex(int x, int y){
		this.x = x;
		this.y = y;
		setBounds(x-0*insets.left, y-0*insets.top, 2*radius, 2*radius);
		//System.out.println(insets.left + " affefaer " + insets.top);
		chips = 42;
		setFont(font);
		setText(Integer.toString(chips));
		addActionListener(new VertexListener());
	}
	
	public void fire(){
		
	}
	
	private class VertexListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(GraphPanel.actionNum == 2){
				index1 = GraphPanel.vertices.indexOf(e.getSource());
				GraphPanel.actionNum = 3;
				System.out.println(index1);
			}
			else if(GraphPanel.actionNum == 3){
				Edge edge = new Edge(GraphPanel.vertices.get(index1), (Vertex) e.getSource());
				GraphPanel.edges.add(edge);
				GraphPanel.actionNum = 2;
			}
		}
	}
}
