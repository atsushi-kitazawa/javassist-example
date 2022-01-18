package com.atsushi.kitazawa;

import java.lang.reflect.Field;

import javassist.ClassPool;
import javassist.NotFoundException;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.BadBytecode;
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
        ClassPool cp = ClassPool.getDefault();
        ClassFile cf = cp.get("com.atsushi.kitazawa.Person")
                .getClassFile();
        FieldInfo f = new FieldInfo(cf.getConstPool(), "hoge", "I");
        f.setAccessFlags(AccessFlag.PUBLIC);
        cf.addField(f);

        Field[] fields = cp.makeClass(cf).toClass(Main.class).getDeclaredFields();
        for(Field ff : fields) {
            System.out.println(ff.getName());
        }
    }
}
