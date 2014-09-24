import se.lth.cs.ptdc.window.SimpleWindow;
import java.awt.Color;

public class Turtle {
	protected SimpleWindow w;
	protected int x;
	protected int y;
	protected boolean penup;
	protected double direction;

	// ... lägg till attribut

	/**
	 * Skapar en sköldpadda som ritar i ritfönstret w. Från början befinner sig
	 * sköldpaddan i punkten x,y med pennan lyft och huvudet pekande rakt uppåt
	 * i fönstret (i negativ y-riktning)
	 */
	public Turtle(SimpleWindow w, int x, int y) {
		this.x = x;
		this.y = y;
		this.w = w;
		direction = 90;
		penup = true;
		// ... implementera
	}

	/** Sänker pennan */
	public void penDown() {
		penup = false;
		w.setLineColor(Color.black);
		// ... implementera
	}

	/** Lyfter pennan */
	public void penUp() {
		penup = true;
		// ... implementera
	}

	/** Går rakt framåt n pixlar i den riktning som huvudet pekar */
	public void forward(int n) {
		w.moveTo((int) Math.round(x), (int) Math.round(y));
		x = (int) (x + Math.round(n * Math.cos((direction * Math.PI) / 180)));
		y = (int) (y - (Math.round(n * Math.sin((direction * Math.PI) / 180))));
		if (penup == false) {
			w.lineTo((int) Math.round(x), (int) Math.round(y));

		}

		// ... implementera
	}

	/** Vrider beta grader åt vänster runt pennan */
	public void left(int beta) {
		direction += beta;
		if (direction > 360) {
			direction -= 360;
		}
		// ... implementera
	}

	/**
	 * Går till punkten newX,newY utan att rita. Pennans läge (sänkt eller lyft)
	 * och huvudets riktning påverkas inte
	 */
	public void jumpTo(int newX, int newY) {
		x = newX;
		y = newY;
		// ... implementera
	}

	/** Återställer huvudriktningen till den ursprungliga */
	public void turnNorth() {
		direction = 90;
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
		return (int) direction;
	}
}
