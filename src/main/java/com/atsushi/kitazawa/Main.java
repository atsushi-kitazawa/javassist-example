package com.atsushi.kitazawa;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewConstructor;
import javassist.CtNewMethod;
import javassist.Loader;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.ClassFile;
import javassist.bytecode.FieldInfo;

/**
 * Hello world!
 *
 */
public class Main {
    public static void main(String[] args) throws Exception {
        doMain();
    }

    private static void doMain() throws Exception {

    }

    public void addIntegerField(String name) throws Exception {
        ClassPool cp = ClassPool.getDefault();
        ClassFile cf = cp.get("com.atsushi.kitazawa.Person")
                .getClassFile();
        FieldInfo f = new FieldInfo(cf.getConstPool(), name, "I");
        f.setAccessFlags(AccessFlag.PUBLIC);
        cf.addField(f);

        Field[] fields = cp.makeClass(cf).toClass(Main.class).getDeclaredFields();
        for (Field ff : fields) {
            System.out.println(ff.getName());
        }
    }

    public Object getInstance(Class<?> T) throws Exception {
        // add default public constructor
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get(T.getName());
        CtConstructor constructor = CtNewConstructor.defaultConstructor(cc);
        cc.addConstructor(constructor);

        // reload class T and create new instance
        Loader cl = new Loader(cp);
        Object o = cl.loadClass(T.getName()).getDeclaredConstructor().newInstance();
        System.out.println(o);
        return o;
    }

    public boolean hasDefaultConstructor(Class<?> T) {
        Constructor<?>[] constructors = T.getDeclaredConstructors();
        for (Constructor<?> c : constructors) {
            if (c.getParameterTypes().length == 0) {
                return true;
            }
        }
        return false;
    }

    public Object addSetterMethod(Class<?> clazz) throws Exception {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get(clazz.getName());

        CtConstructor constructor = CtNewConstructor.defaultConstructor(cc);
        cc.addConstructor(constructor);

        for (CtField cf : cc.getDeclaredFields()) {
            CtMethod method = CtNewMethod.setter("set" + cf.getName().toUpperCase(), cf);
            cc.addMethod(method);
        }

        Loader cl = new Loader(cp);
        Object o = cl.loadClass(clazz.getName()).getDeclaredConstructor().newInstance();
        return o;
    }
}
