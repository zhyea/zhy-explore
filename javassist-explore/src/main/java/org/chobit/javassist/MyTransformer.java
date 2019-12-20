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

        System.out.println(bytes.length);

        try {

            ClassPool cp = ClassPool.getDefault();
            byte[] b = classfileBuffer;
            String name = className.replace('/', '.');
            cp.insertClassPath(new ByteArrayClassPath(name, b));
            CtClass cc = cp.get(name);

            CtMethod[] methods = cc.getMethods();
            for (CtMethod mold : methods) {
                System.out.println(mold.getDeclaringClass().getName());
                if (!mold.getDeclaringClass().getName().equals(name)) {
                    continue;
                }


                if (mold.getMethodInfo().isConstructor()) {
                    continue;
                }

                System.out.println("--------------->>>" + mold.getName() + " - " + mold.getMethodInfo().getCodeAttribute());

                final String beforeMethod = "{long startTime = System.currentTimeMillis(); System.out.println(\"Before Foo\");";
                final String afterMethod = "finally {long diff = System.currentTimeMillis() - startTime; System.out.println(\"Foo completed in:\" + diff);}}";
                mold.instrument(
                        new ExprEditor() {
                            public void edit(MethodCall m)
                                    throws CannotCompileException {
                                m.replace(beforeMethod + " try {$_ = $proceed($$); } " + afterMethod);
                            }
                        });
            }

            bytes = cc.toBytecode();
        } catch (Throwable t) {
            t.printStackTrace();
        }

        try {
            System.out.println("---------------------------finished creating bytecode file");
            new FileOutputStream("/zhy/A.class").write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(bytes.length);
        return bytes;
    }


    public boolean check(String className) {
        return className.startsWith("org/chobit/javassist/MyTest");
    }


}
