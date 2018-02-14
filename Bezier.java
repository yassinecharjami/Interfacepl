
public class Bezier {
	
	private Trajectory data; /* control point of the curve */
	private Trajectory output; /* curve obtained by the algorithm */
	
	public Bezier(int size, Trajectory data) {
		this.data = data;
		this.output = data.outputBezier(size);
	}

	
	public Trajectory getOutput() {
		return(this.output);
	}
	
	public Trajectory getData() {
		return(this.data);
	}
	
}
