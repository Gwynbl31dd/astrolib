package space.darksitedb.astrolib;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MyLibraryTest {

	@Test
	void hello_shouldReturnGreeting() {
		assertEquals("Hello Anthony", MyLibrary.hello("Anthony"));
	}
}
