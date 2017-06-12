import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GraphPanel extends JPanel{
	private BufferedImage image;
	private Graphics2D buffer;
	
	public static ArrayList<Vertex> vertices;
	public static ArrayList<Edge> edges;
	public static int actionNum;
	
	final static int WIDTH = 1000, HEIGHT = 700;

	public GraphPanel(){
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		actionNum = 0;
		
		this.setSize(WIDTH, HEIGHT);
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		System.out.println(getWidth() + " " + getHeight());
		buffer = (Graphics2D)image.getGraphics();
		buffer.setBackground(Color.WHITE);
		
		setLayout(null);
		addMouseListener(new Mouse());
	}
	
	@Override
	public void paintComponent(Graphics g){
		buffer.clearRect(0, 0, WIDTH, HEIGHT);
		buffer.setColor(Color.RED);
		buffer.fillOval(200, 300, 10, 10);
		for(Edge e : edges){
			e.draw(buffer);
		}
		System.out.println(edges.size());
		//System.out.println(getWidth() + " draw " + getHeight());
		g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
	}
	
	private class Mouse implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			if(actionNum == 1){
				Point p = e.getLocationOnScreen();
				Vertex v = new Vertex(p.x, p.y);
				System.out.println(p.x + " " + p.y);
				vertices.add(v);
				v.setToolTipText(Integer.toString(vertices.indexOf(v)));
				add(v);
				Container parent = v.getParent();
				parent.repaint();
			}
			
			if(actionNum == 2){
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
