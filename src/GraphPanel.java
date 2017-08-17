import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JPanel;

public class GraphPanel extends JPanel{
	private BufferedImage image;
	private Graphics2D buffer;
	
	public static ArrayList<Vertex> vertices;
	public static ArrayList<Integer> savedDivisor;
	public static HashSet<Edge> edges;
	public static int actionNum;
	public static Vertex selected;
	
	final static int WIDTH = 1000, HEIGHT = 700;

	public GraphPanel(){
		vertices = new ArrayList<Vertex>();
		edges = new HashSet<Edge>();
		actionNum = 0;
		
		this.setSize(WIDTH, HEIGHT);
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		//System.out.println(getWidth() + " " + getHeight());
		buffer = (Graphics2D)image.getGraphics();
		buffer.setBackground(Color.WHITE);
		
		setLayout(null);
		addMouseListener(new Mouse());
	}
	
	public static void setSelected(Vertex v){
		if(selected != null){
			selected.setBackground(Color.WHITE);
		}
		selected = v;
		selected.setBackground(Color.BLUE);
  }

	public void dhar(Vertex v){
		for(Vertex w : vertices){
			if(w != v && w.chips < 0){
				System.out.println("HELP!: Cannot do this step yet.");
				return;
			}
		}

		HashSet<Integer> toFire = burn(v);
		while(toFire.size() > 0){
			for(Integer i : toFire){
				vertices.get(i).fire();
			}
			toFire = burn(v);
		}
		System.out.println("Success!");
	}

	public HashSet<Integer> burn(Vertex v){
		//returns list of not burnt vertices to fire
		HashSet<Integer> burnt = new HashSet<Integer>();
		HashSet<Integer> unburnt = new HashSet<Integer>();
		for(int i = 0; i < vertices.size(); i++){
			unburnt.add(i);
		}

		int n = vertices.indexOf(v);
		unburnt.remove(n);
		burnt.add(n);
		HashSet<Integer> temp = new HashSet<Integer>();
		temp.add(n);
		while(temp.size() > 0){
			temp = new HashSet<Integer>();
			for(int i : unburnt){
				if(getsBurnt(vertices.get(i), burnt)){
					temp.add(i);
					burnt.add(i);
				}
			}
			for(int i : temp){
				unburnt.remove(i);
			}
		}
		return unburnt;
	}

	public boolean getsBurnt(Vertex v, HashSet<Integer> burnt){
		int fires = 0;
		for(int i : v.adjacencies){
			if(burnt.contains(i)) fires++; 
		}
		if(fires > v.chips) return true;
		return false;
	}

	@Override
	public void paintComponent(Graphics g){
		buffer.clearRect(0, 0, WIDTH, HEIGHT);
		buffer.setColor(Color.RED);
		buffer.fillOval(200, 300, 10, 10);
		for(Edge e : edges){
			e.draw(buffer);
		}
		//System.out.println(edges.size());
		//System.out.println(getWidth() + " draw " + getHeight());
		g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
	}
	
	private class Mouse implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			if(actionNum == 1){
				Point p = e.getLocationOnScreen();
				Vertex v = new Vertex(p.x, p.y);
				//System.out.println(p.x + " " + p.y);
				vertices.add(v);
				v.setToolTipText(Integer.toString(vertices.indexOf(v)));
				v.setOpaque(true);
				add(v);
				setSelected(v);
				Container parent = v.getParent();
				parent.repaint();
			}
			
			else if(actionNum == 2){
			}
}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}
