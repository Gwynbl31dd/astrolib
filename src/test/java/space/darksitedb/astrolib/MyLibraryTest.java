package space.darksitedb.astrolib;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyLibraryTest {

    @Test
    void hello_shouldReturnGreeting() {
        assertEquals("Hello Anthony", MyLibrary.hello("Anthony"));
    }
}