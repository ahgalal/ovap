/**
 * 
 */
package ovap.video;

/**
 * @author Creative
 *
 */
public interface IFilterManager {
	boolean initialize(FiltersConfiguration configs);
	boolean startStream();
	boolean stopStream();
}
