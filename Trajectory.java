
public class Trajectory {
	
	private int size; /* number of positions in the trajectory */
	private Point[] positions; /* every position of the trajectory */
	
	/* create the trajectory */
	
	public Trajectory(int size, Point[] positions) {
		this.size = size;
		this.positions = positions;
	}
	
	/* get the size of the trajectory */
	
	public int getSize() {
		return this.size;
	}
	
	
	/* get the i-th position of the trajectory */
	
	public Point getPosition(int i) {
		return this.positions[i];
	}
	
	/* set the i-th position of the trajectory */
	
	public void setPosition(int i, Point p) {
		this.positions[i] = p;
	}

	public Point casteljau(double t) {
		int n = this.size;
		Point[][] T = new Point[n+1][n+1];
		for (int j = 0; j <= n; j++) {
			T[0][j] = this.positions[j]; 
		}
		for (int j = 1; j <= n; j++) {
			for (int i = 0; i <= n-j; i++) {
				T[i][j] = T[i+1][j-1].multiply(t).add(T[i][j-1].multiply(1-t)); 
			}
		}
		return(T[0][n]);
	}
	

	/* Apply Casteljau's algorithm to each point */
	
	public Trajectory outputBezier(int size) {
		Point[] output = new Point[size];
		for (int i=0; i < size; i++) {
			double t = i/size;
			output[i] = this.casteljau(t);
		}
		return(new Trajectory(size, output));
	}
	
	public Point[] pointsDeControle(int degre) {
		int nbPtControle = 0;
		for (int i=1; i < this.size-1; i++) {
			double x0 = this.positions[i-1].getCoordinates()[0];
			double y0 = this.positions[i-1].getCoordinates()[1];
			double x1 = this.positions[i].getCoordinates()[0];
			double y1 = this.positions[i].getCoordinates()[1];
			double x2 = this.positions[i+1].getCoordinates()[0];
			double y2 = this.positions[i+1].getCoordinates()[1];
			if (((x1-x0)*(x2-x1) < 0)||((y1-y0)*(y2-y1) < 0)) {
				nbPtControle++;
			}
		}
		Point[] pointsDeControle = new Point[nbPtControle];
		int pos = 0;
		for (int i=1; i < this.size-1; i++) {
			double x0 = this.positions[i-1].getCoordinates()[0];
			double y0 = this.positions[i-1].getCoordinates()[1];
			double x1 = this.positions[i].getCoordinates()[0];
			double y1 = this.positions[i].getCoordinates()[1];
			double x2 = this.positions[i+1].getCoordinates()[0];
			double y2 = this.positions[i+1].getCoordinates()[1];
			if (((x1-x0)*(x2-x1) < 0)||((y1-y0)*(y2-y1) < 0)) {
				pointsDeControle[pos]=this.positions[i];
				pos++;
			}
		}
		return(pointsDeControle);
	}
	
	public Trajectory spline(int degre, int nbPoints, Point[] pointsDeControle) {
		Point[] coordonnees = new Point[nbPoints];
		for (int t = 0; t < nbPoints; t++) {
			if (degre==0) {
				
			}
		}
	}
	
}
