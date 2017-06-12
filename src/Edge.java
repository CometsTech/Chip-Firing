import java.awt.Graphics2D;

public class Edge {
	Vertex a, b;
	
	public Edge(Vertex a, Vertex b){
		this.a = a;
		this.b = b;
	}
	
	public void draw(Graphics2D buffer){
		buffer.drawLine(a.x, a.y, b.x, b.y);
	}
}
