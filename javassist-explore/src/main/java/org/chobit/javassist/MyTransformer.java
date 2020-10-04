package org.chobit.javassist;

import javassist.*;
import javassist.bytecode.MethodInfo;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

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


        if (!check(className)) {
            return null;
        }

        byte[] bytes = classfileBuffer;

        try {

            ClassPool cp = ClassPool.getDefault();
            String name = className.replace('/', '.');
            cp.insertClassPath(new ByteArrayClassPath(name, bytes));
            CtClass cc = cp.get(name);

            CtMethod[] methods = cc.getMethods();
            for (CtMethod method : methods) {

                if (!method.getDeclaringClass().getName().equals(name)) {
                    continue;
                }


                method.addLocalVariable("$start", CtClass.longType);
                method.insertBefore("$start=System.currentTimeMillis();");

                method.addLocalVariable("$diff", CtClass.longType);
                method.insertAfter("$diff=System.currentTimeMillis()-$start;System.out.println($diff);", true);
            }

            bytes = cc.toBytecode();
            cc.detach();
        } catch (Throwable t) {
            t.printStackTrace();
        }

        try {
            System.out.println("---------------------------finished creating bytecode file");
            new FileOutputStream("/zhy/A.class").write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }


    public boolean check(String className) {
        return className.startsWith("org/chobit/javassist/MyTest");
    }


}
