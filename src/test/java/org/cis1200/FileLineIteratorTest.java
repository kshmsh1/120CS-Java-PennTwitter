package org.cis1200;

import org.junit.jupiter.api.Test;
import java.io.StringReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/** Tests for FileLineIterator */
public class FileLineIteratorTest {

    @Test
    public void testHasNextAndNext() {

        // Note we don't need to create a new file here in order to test out our
        // FileLineIterator if we do not want to. We can just create a
        // StringReader to make testing easy!
        String words = "0, The end should come here.\n"
                + "1, This comes from data with no duplicate words!";
        StringReader sr = new StringReader(words);
        BufferedReader br = new BufferedReader(sr);
        FileLineIterator li = new FileLineIterator(br);
        assertTrue(li.hasNext());
        assertEquals("0, The end should come here.", li.next());
        assertTrue(li.hasNext());
        assertEquals("1, This comes from data with no duplicate words!", li.next());
        assertFalse(li.hasNext());
    }

    /* **** ****** **** WRITE YOUR TESTS BELOW THIS LINE **** ****** **** */

    @Test
    public void testEmptyFile() {
        StringReader sr = new StringReader("");
        BufferedReader br = new BufferedReader(sr);
        FileLineIterator li = new FileLineIterator(br);

        assertFalse(li.hasNext());
        assertThrows(NoSuchElementException.class, li::next);
    }

    @Test
    public void testOneLineFile() {
        StringReader sr = new StringReader("My name is Keshav!");
        BufferedReader br = new BufferedReader(sr);
        FileLineIterator li = new FileLineIterator(br);

        assertTrue(li.hasNext());
        assertEquals("My name is Keshav!", li.next());
        assertFalse(li.hasNext());
    }

    @Test
    public void testMultipleEmptyLines() {
        StringReader sr = new StringReader("\n\n\n\n\n");
        BufferedReader br = new BufferedReader(sr);
        FileLineIterator li = new FileLineIterator(br);

        assertFalse(li.hasNext());
        assertThrows(NoSuchElementException.class, li::next);
    }

    @Test
    public void testIOException() {
        StringReader sr = new StringReader("Checking for exception");
        BufferedReader br = new BufferedReader(sr) {
            @Override
            public String readLine() throws IOException {
                throw new IOException();
            }
        };
        FileLineIterator li = new FileLineIterator(br);
        assertFalse(li.hasNext());
        assertThrows(NoSuchElementException.class, li::next);
    }

}