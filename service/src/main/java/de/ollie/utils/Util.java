package de.ollie.utils;

import static de.ollie.utils.Check.ensure;

/**
 * A common utility class.
 *
 * @author ollie (02.12.2019)
 */
public class Util {

	Util() {
		throw new UnsupportedOperationException("Should not be called!");
	}

	/**
	 * Appends the passed string if the passed string builder is not empty.
	 * 
	 * @param sb The string builder to extend.
	 * @param s  A string which is to append, if the string builder is not empty.
	 * @return The string builder.
	 * @throws IllegalArgumentException If a null value is passed.
	 */
	public static StringBuilder appendIfNotEmpty(StringBuilder sb, String s) {
		ensure(sb != null, "string builder cannot be null.");
		ensure(s != null, "string cannot be null.");
		if (sb.length() > 0) {
			return sb.append(s);
		}
		return sb;
	}

	/**
	 * Appends the passed string if the passed string builder is not empty and returns this string or the "orElse"
	 * parameter if the passed string builder is empty.
	 * 
	 * @param sb     The string builder to extend.
	 * @param s      A string which is to append, if the string builder is not empty.
	 * @param orElse The string which is returned if an empty string builder is passed.
	 * @return A string with the content of the string builder (if it is not empty) plus the passed string or the
	 *         "orElse" string.
	 * @throws IllegalArgumentException If a null value is passed as string or string builder (null values are accepted
	 *                                  for the "orElse" parameter).
	 */
	public static String appendIfNotEmptyOrElseReturn(StringBuilder sb, String s, String orElse) {
		ensure(sb != null, "string builder cannot be null.");
		ensure(s != null, "string cannot be null.");
		if (sb.length() > 0) {
			return sb.append(s).toString();
		}
		return orElse;
	}

	/**
	 * Checks if the passed string is null or empty.
	 * 
	 * @param s The string to check.
	 * @return "true" if the passed string is "null" or empty.
	 */
	public static boolean isNullOrEmpty(String s) {
		return (s == null) || (s.length() == 0);
	}

}