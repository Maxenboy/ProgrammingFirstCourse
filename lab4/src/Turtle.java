import se.lth.cs.ptdc.window.SimpleWindow;

public class Turtle {
	protected double x;
	protected double y;
	protected double grad;
	protected SimpleWindow w;

	// ... lägg till attribut

	/**
	 * Skapar en sköldpadda som ritar i ritfönstret w. Från början befinner sig
	 * sköldpaddan i punkten x,y med pennan lyft och huvudet pekande rakt uppåt
	 * i fönstret (i negativ y-riktning)
	 */
	public Turtle(SimpleWindow w, int x, int y) {
		this.w = w;
		this.x = x;
		this.y = y;
		this.grad = 90;

		// ... implementera
	}

	/** Sänker pennan */
	public void penDown() {

		w.setLineColor(java.awt.Color.BLACK);
		// ... implementera
	}

	/** Lyfter pennan */
	public void penUp() {

		w.setLineColor(java.awt.Color.WHITE);
		// ... implementera
	}

	/** Går rakt framåt n pixlar i den riktning som huvudet pekar */
	public void forward(int n) {

		w.moveTo((int) x, (int) y);
		double newX =  (x + n * Math.round(Math.cos(grad)));
		double newY =  (y - n * Math.round(Math.sin(grad)));
		w.lineTo((int)newX, (int)newY);
		x = newX;
		y = newY;

		// ... implementera
	}

	/** Vrider beta grader åt vänster runt pennan */
	public void left(double beta) {

		grad += beta;

		// ... implementera
	}

	/**
	 * Går till punkten newX,newY utan att rita. Pennans läge (sänkt eller lyft)
	 * och huvudets riktning påverkas inte
	 */
	public void jumpTo(int newX, int newY) {
		x = newX;
		y = newY;
		w.moveTo((int) x, (int) y);
		// ... implementera
	}

	/** Återställer huvudriktningen till den ursprungliga */
	public void turnNorth() {
		grad = 90;
		// ... implementera
	}

	/** Tar reda på x-koordinaten för sköldpaddans aktuella position */
	public int getX() {
		// ... implementera
		return (int) x;
	}

	/** Tar reda på y-koordinaten för sköldpaddans aktuella position */
	public int getY() {
		// ... implementera
		return (int) y;
	}

	/** Tar reda på sköldpaddans riktning, i grader från positiv x-led */
	public int getDirection() {
		// ... implementera
		return (int) grad;
	}
}
