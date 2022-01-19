package com.atsushi.kitazawa;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class MainTest
        extends TestCase {

    @Test
    public void testHasDefaultConstructor() {
        assertTrue(new Main().hasDefaultConstructor(HasDefaultConstructor.class));
        assertTrue(!new Main().hasDefaultConstructor(NoHasDefaultConstructor.class));
    }

    @Test
    public void testGetInstance() throws Exception {
        Object instance = new Main().getInstance(NoHasDefaultConstructor.class);
        assertEquals(NoHasDefaultConstructor.class.getName(), instance.getClass().getName());
    }
}
