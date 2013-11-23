/**
 * 
 */
package utils;

/**
 * @author Creative
 */
public class StringUtils {
	public static String join(final String delim, final String... data) {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			sb.append(data[i]);
			if (i >= (data.length - 1)) {
				break;
			}
			sb.append(delim);
		}
		return sb.toString();
	}
}
