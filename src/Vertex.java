import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JButton;

public class Vertex extends JButton{
	int x, y, chips;
	GraphPanel panel;
	
	public HashSet<Integer> adjacencies;
	public static int index1;
	public static final int RADIUS = 25;
	
	Font font = new Font("Serif", Font.BOLD, 12);
	Insets insets = getInsets();
	
	public Vertex(int x, int y){
		this.x = x;
		this.y = y-Vertex.RADIUS;
		adjacencies = new HashSet<Integer>();
		setBounds(x-Vertex.RADIUS, this.y-Vertex.RADIUS, 2*RADIUS, 2*RADIUS);
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
				Vertex v1 = GraphPanel.vertices.get(index1), v2 = (Vertex) e.getSource();
				Edge edge = new Edge(v1, v2);
				v1.adjacencies.add(GraphPanel.vertices.indexOf(v2));
				v2.adjacencies.add(index1);
				GraphPanel.edges.add(edge);
				((Vertex)(e.getSource())).getParent().repaint();
				GraphPanel.actionNum = 2;
			}
		}
	}
}
