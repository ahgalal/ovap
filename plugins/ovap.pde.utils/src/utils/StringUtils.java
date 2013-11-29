/**
 * 
 */
package utils;

import java.util.HashMap;
import java.util.Map;

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
	/**
	 * Converts a flat map to an instance specific map.<br>
	 * <b>Example:</b><br>
	 * flatMap data: moduleA__setting1=5<br>
	 * instance configurations data: setting1=5
	 * @param name
	 * @param attributes
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map convertToInstanceConfigMap(
			final String name, final Map attributes) {
		return convertToInstanceConfigMap(name, attributes, false);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map convertToInstanceConfigMap(
			final String name, final Map attributes,boolean includeAllItems) {
		final Map map = new HashMap();

		for (final Object keyObj : attributes.keySet()) {
			String key = (String) keyObj;
			if (key.startsWith(name + "__")) {
				final String cfgKey = key
						.substring(key.indexOf("__") + 2);
				final Object cfgValue = attributes.get(keyObj);

				map.put(cfgKey, cfgValue);
			}else if(includeAllItems)
				map.put(key, attributes.get(key));
		}
		return map;
	}

	/**Converts an instance specific map to a flat map.<br>
	 * <b>Example:</b><br>
	 * flatMap data: moduleA__setting1=5<br>
	 * instance configurations data: setting1=5
	 * @param name
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map convertToFlatConfigMap(
			final String name, final Map map) {
		final Map flatConfigMap = new HashMap();

		for (final Object keyObj : map.keySet()) {
			String key = (String) keyObj;
			flatConfigMap.put(name + "__" + key,
					map.get(key));
		}
		return flatConfigMap;
	}
}
