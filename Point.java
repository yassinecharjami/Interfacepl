
public class Point {
	
	private double x; /* abscissa */ 
	private double y; /* ordinate */
	
	/* Create the point */
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/* Get the coordinates of the point */
	
	public double[] getCoordinates(){
		double coordinates[] = {this.x, this.y};
		return coordinates;
	}
	
	public Point multiply(double t) {
		return new Point(this.x * t, this.y * t);
	}
	
	public Point add(Point p) {
		double coordinates[] = p.getCoordinates(); 
		return new Point(this.x + coordinates[0], this.y + coordinates[1]);
	}
}