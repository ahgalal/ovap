package ovap.video.utils;

public class RGB {

	private int	r, g, b;

	public RGB(final int r, final int g, final int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public int getB() {
		return b;
	}

	public int getG() {
		return g;
	}

	public int getR() {
		return r;
	}

	public void setB(final int b) {
		this.b = b;
	}

	public void setG(final int g) {
		this.g = g;
	}

	public void setR(final int r) {
		this.r = r;
	}

	public void setRGB(final int r, final int g, final int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	@Override
	public String toString() {
		return "R: " + getR() + ",G: " + getG() + ",B: " + getB();
	}
}