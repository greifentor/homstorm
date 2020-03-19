package de.ollie.utils;

import static de.ollie.utils.Util.appendIfNotEmpty;
import static de.ollie.utils.Util.appendIfNotEmptyOrElseReturn;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for class "Util".
 *
 * @author ollie (02.12.2019)
 */
public class UtilTest {

	@DisplayName("Tests of the constructor.")
	@Nested
	class TestOfTheConstructor {

		@DisplayName("Throws an exception if called.")
		@Test
		void called_ThrowsAnException() {
			assertThrows(UnsupportedOperationException.class, //
					() -> new Util());
		}

	}

	@DisplayName("Tests of method 'appendIfNotEmpty(StringBuilder, String)'.")
	@Nested
	class TestOfTheMethod_appendIfNotEmpty_StringBuilder_String {

		@DisplayName("Throws an exception if a null value as string builder is passed.")
		@Test
		void nullValueAsStringBuilderPassed_ThrowsAnException() {
			assertThrows(IllegalArgumentException.class, () -> appendIfNotEmpty(null, ","));
		}

		@DisplayName("Throws an exception if a null as string value is passed.")
		@Test
		void nullValueAsStringPassed_ThrowsAnException() {
			assertThrows(IllegalArgumentException.class, () -> appendIfNotEmpty(new StringBuilder(), null));
		}

		@DisplayName("Returns the string builder plus the string if the string builder is not empty.")
		@Test
		void passedANotEmptyStringBuilder_ReturnsTheStringBuilderPlusTheString() {
			// Prepare
			String s = "TheString";
			StringBuilder sb = new StringBuilder("StringBuilder");
			StringBuilder expected = new StringBuilder(sb).append(s);
			// Run
			StringBuilder returned = Util.appendIfNotEmpty(sb, s);
			// Check
			assertEquals(expected.toString(), returned.toString());
		}

		@DisplayName("Returns an empty string builder if the passed string builder is empty.")
		@Test
		void passedAnEmptyStringBuilder_ReturnsAEmptyStringBuilder() {
			// Prepare
			String s = "TheString";
			StringBuilder sb = new StringBuilder();
			StringBuilder expected = new StringBuilder();
			// Run
			StringBuilder returned = Util.appendIfNotEmpty(sb, s);
			// Check
			assertEquals(expected.toString(), returned.toString());
		}

	}

	@DisplayName("Tests of method 'appendIfNotEmptyOrElseReturn(StringBuilder, String, String)'.")
	@Nested
	class TestOfTheMethod_appendIfNotEmptyOrElseReturn_StringBuilder_String_String {

		@DisplayName("Throws an exception if a null value as string builder is passed.")
		@Test
		void nullValueAsStringBuilderPassed_ThrowsAnException() {
			assertThrows(IllegalArgumentException.class, () -> appendIfNotEmptyOrElseReturn(null, ",", null));
		}

		@DisplayName("Throws an exception if a null as string value is passed.")
		@Test
		void nullValueAsStringPassed_ThrowsAnException() {
			assertThrows(IllegalArgumentException.class,
					() -> appendIfNotEmptyOrElseReturn(new StringBuilder(), null, null));
		}

		@DisplayName("Returns the string builder plus the string if the string builder is not empty.")
		@Test
		void passedANotEmptyStringBuilder_ReturnsAWithTheStringBuilderContentPlusTheString() {
			// Prepare
			String s = "TheString";
			String orElse = "OrElse";
			StringBuilder sb = new StringBuilder("StringBuilder");
			String expected = sb.toString() + s;
			// Run
			String returned = Util.appendIfNotEmptyOrElseReturn(sb, s, orElse);
			// Check
			assertEquals(expected, returned);
		}

		@DisplayName("Returns an empty string builder if the passed string builder is empty.")
		@Test
		void passedAnEmptyStringBuilder_ReturnsAEmptyStringBuilder() {
			// Prepare
			String s = "TheString";
			String orElse = "OrElse";
			StringBuilder sb = new StringBuilder();
			// Run
			String returned = Util.appendIfNotEmptyOrElseReturn(sb, s, orElse);
			// Check
			assertEquals(orElse, returned);
		}

		@DisplayName("Returns null value if the passed string builder is empty and 'orElse' is null.")
		@Test
		void passedAnEmptyStringBuilderAndANullValueForOrElse_ReturnsNull() {
			// Prepare
			String s = "TheString";
			StringBuilder sb = new StringBuilder();
			// Run
			String returned = Util.appendIfNotEmptyOrElseReturn(sb, s, null);
			// Check
			assertNull(returned);
		}

	}

	@DisplayName("Tests of method 'isNullOrEmpty(String)'.")
	@Nested
	class TestOfTheMethod_isNullOrEmpty_String {

		@DisplayName("Returns 'true' passing a null value.")
		@Test
		void passedANullValue_ReturnsTrue() {
			assertTrue(Util.isNullOrEmpty(null));
		}

		@DisplayName("Returns 'true' passing an empty string.")
		@Test
		void passedAnEmptyString_ReturnsTrue() {
			assertTrue(Util.isNullOrEmpty(""));
		}

		@DisplayName("Returns 'false' passing a string with content.")
		@Test
		void passedANotEmptyString_ReturnsFalse() {
			assertFalse(Util.isNullOrEmpty(";op"));
		}

	}

}