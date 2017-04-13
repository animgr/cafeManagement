package utils;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

/**
 * java.util.Properties helps to store and retrieve the key-value pair, where
 * non-null values in key and value. This implementation made on top of
 * java.util.HashTable. We do get all the features and APIs of HashTable
 * additionally, getProperty(...), load() APIs. In getProperty method, key has
 * to be passed as argument to retrive the value. We can even specify the
 * default value also in it, if no key found in list. Then, What makes different
 * in this blog posting ?. Here, the key is case-sensitive one. We have to send
 * exact word to get the value. Most of the time, we may need to retrieve value
 * for case-insensitive key. This is where, why don't we extend the
 * functionality to give support to retrieve value with case-insensitive key.
 * 
 * Here, Properties extended to create PropertyIgnoreCase class, and added two
 * APIs - getPropertyIgnoreCase(String key), getPropertyIgnoreCase(String key,
 * String defaultV) Code copied from:
 * http://www.javafundu.com/2010/06/propertyignorecase.html
 * 
 * @author a_mgr
 *
 */
public class PropertyIgnoreCase extends Properties {

	private static final long serialVersionUID = 7511088737858527084L;

	/**
	 * get value from {Properties} This method is COSTY don't use it if you are
	 * sure about the case
	 * 
	 * @param props
	 * @param key
	 * @return
	 */
	public String getPropertyIgnoreCase(String key) {
		return getPropertyIgnoreCase(key, null);
	}

	/**
	 * get value from {Properties}, if no key exist then return default value.
	 * This method is COSTY don't use it if you are sure about the case
	 * 
	 * @param props
	 * @param key
	 * @param defaultV
	 * @return
	 */
	public String getPropertyIgnoreCase(String key, String defaultV) {
		String value = getProperty(key);
		if (null != value)
			return value;

		// Not matching with the actual key then
		Set<Entry<Object, Object>> s = entrySet();
		Iterator<Entry<Object, Object>> it = s.iterator();
		while (it.hasNext()) {
			Entry<Object, Object> entry = it.next();
			if (key.equalsIgnoreCase((String) entry.getKey())) {
				return (String) entry.getValue();
			}
		}
		return defaultV;
	}

}
