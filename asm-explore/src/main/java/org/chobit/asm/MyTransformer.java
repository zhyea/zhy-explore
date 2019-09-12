package org.chobit.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class MyTransformer implements ClassFileTransformer {


    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {

        System.out.println(className);

        if (!check(className)) {
            return null;
        }


        System.out.println("---------------------------0-");

        ClassReader cr = new ClassReader(classfileBuffer);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);


        System.out.println("---------------------------1-");

        try{
            MyClassAdapter mca = new MyClassAdapter(cw);
            cr.accept(mca, ClassReader.EXPAND_FRAMES);
        }catch(Throwable t){
            t.printStackTrace();
        }

        System.out.println("---------------------------2-");

        byte[] bytes = cw.toByteArray();
        try {
            System.out.println("---------------------------3-");
            new FileOutputStream("/zhy/A.class").write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }


    public boolean check(String className) {
        return className.startsWith("org/chobit/asm/MyTest");
    }


}
