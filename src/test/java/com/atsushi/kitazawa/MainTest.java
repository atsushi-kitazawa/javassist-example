package com.atsushi.kitazawa;

import java.lang.reflect.Method;

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

    @Test
    public void testAddSetterMethod() throws Exception {
        // Object instance = new Main().addMethod(Pojo.class, "set");
        Object instance = new Main().addSetterMethod(Pojo.class);
        System.out.println(instance.getClass().getName());
        System.out.println(instance);
        for(Method m : instance.getClass().getDeclaredMethods()) {
            System.out.println(m.getName());
        }
    }
}
