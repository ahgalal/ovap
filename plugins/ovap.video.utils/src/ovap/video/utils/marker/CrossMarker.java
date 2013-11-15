/***************************************************************************
 * Copyright 2010,2011 by Ahmed Galal, Ahmed Mohammed Aly, Sarah Hamid and
 * Mohammed Ahmed Ramadan contact: ceng.ahmedgalal@gmail.com This file is part
 * of Behavioral Monitoring Tool. Behavioral Monitoring Tool is free software:
 * you can redistribute it and/or modify it under the terms of the GNU General
 * Public License as published by the Free Software Foundation, version 3 of the
 * License. Behavioral Monitoring Tool is distributed in the hope that it will
 * be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details. You should have received a copy of the GNU
 * General Public License along with Behavioral Monitoring Tool. If not, see
 * <http://www.gnu.org/licenses/>.
 **************************************************************************/

package ovap.video.utils.marker;

import java.awt.Color;

/**
 * Cross marker for the Rat Finder filter.
 * 
 * @author Creative
 */
public class CrossMarker extends Marker {

	private int	actualWidth, actualHeight;
	private final int	width, height, thikness;
	private int			x1, x2, y1, y2;

	/**
	 * Initializes the marker.
	 * 
	 * @param width
	 *            marker's width
	 * @param height
	 *            marker's height
	 * @param thickness
	 *            line thickness
	 * @param color
	 *            color
	 * @param imgWidth
	 *            image's width
	 * @param imgHeight
	 *            image's height
	 */
	public CrossMarker(final int width, final int height, final int thickness,
			final Color color, final int imgWidth, final int imgHeight) {
		super(imgWidth, imgHeight, color);
		this.width = width;
		this.height = height;
		this.thikness = thickness;
	}

	@Override
	public void draw(final int[] img, final int x, final int y) {
		actualWidth = (x < width / 2) ? x + width / 2 : width;
		actualHeight = (y < height / 2) ? y + height / 2 : height;

		x1 = (x < width / 2) ? 0 : x - width / 2;
		x2 = (x < thikness / 2) ? 0 : x - thikness / 2;

		y1 = (y < thikness / 2) ? 0 : y - thikness / 2;
		y2 = (y < height / 2) ? 0 : y - height / 2;

		// Horizontal line
		fillRect(img, x1, y1, actualWidth, thikness);

		// Vertical line
		fillRect(img, x2, y2, thikness, actualHeight);

	}

}
