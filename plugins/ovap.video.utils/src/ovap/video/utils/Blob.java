package ovap.video.utils;

import java.awt.Point;

public class Blob {
	private Point	centroid	= new Point();
	private int		maxX		= -1;
	private int		maxY		= -1;
	private int		minX		= 10000;
	private int		minY		= 10000;

	public void addPoint(final int x, final int y) {
		if (x < minX)
			minX = x;
		if (x > maxX)
			maxX = x;
		if (y < minY)
			minY = y;
		if (y > maxY)
			maxY = y;
		centroid.x = (maxX + minX) / 2;
		centroid.y = (maxY + minY) / 2;
		if ((centroid.x > 640) || (centroid.x < 0) || (centroid.y > 480)
				|| (centroid.y < 0))
			System.out.println("ssssssssss");

	}

	public int getArea() {
		return (maxX - minX) * (maxY - minY);
	}

	public Point getCentroid() {
		return centroid;
	}

	public int getHeight() {
		return maxY - minY;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public int getMinX() {
		return minX;
	}

	public int getMinY() {
		return minY;
	}

	public int getWidth() {
		return maxX - minX;
	}

	public void setCentroid(final Point centroid) {
		this.centroid = centroid;
	}

	@Override
	public String toString() {
		return "center: " + getCentroid().toString() + " rect area: "
				+ getArea() + " minX:" + minX + " maxX:" + maxX + " minY:"
				+ minY + " maxY:" + maxY;
	}
}