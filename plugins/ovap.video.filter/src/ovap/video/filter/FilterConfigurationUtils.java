/**
 * 
 */
package ovap.video.filter;

import java.util.Map;

/**
 * @author Creative
 */
public class FilterConfigurationUtils {
	public static int getConfigurationValue(final Map<String, String> map,
			final String paramName, final int defaultValue) {
		final String valueStr = map.get(paramName);
		if ((valueStr != null) && !valueStr.isEmpty())
			return Integer.parseInt(valueStr);
		return defaultValue;
	}

	public static String getConfigurationValue(final Map<String, String> map,
			final String paramName, final String defaultValue) {
		final String valueStr = map.get(paramName);
		if ((valueStr != null) && !valueStr.isEmpty())
			return valueStr;
		return defaultValue;
	}

	public static boolean getConfigurationValue(
			Map<String, String> map, String paramName,
			boolean defaultValue) {
		final String valueStr = map.get(paramName);
		if ((valueStr != null) && !valueStr.isEmpty())
			return Boolean.parseBoolean(valueStr);
		return defaultValue;
	}
}
