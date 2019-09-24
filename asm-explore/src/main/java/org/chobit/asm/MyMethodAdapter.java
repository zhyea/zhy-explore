package org.chobit.asm;


import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

public class MyMethodAdapter extends AdviceAdapter {

    private final String className;

    private Label l0 = new Label(), l1 = new Label(), l2 = new Label();

    protected MyMethodAdapter(final MethodVisitor mv,
                              final int access,
                              final String name,
                              final String desc,
                              final String className) {
        super(ASM6, mv, access, name, desc);
        this.className = className;
    }


    @Override
    protected void onMethodEnter() {
        visitTryCatchBlock(l0, l1, l2, "java/lang/Throwable");
        visitLabel(l0);
    }


    @Override
    protected void onMethodExit(int opcode) {
    }


    @Override
    public void visitMaxs(int maxStack, int maxLocals) {

        final int throwable = newLocal(Type.getType(Throwable.class));
        // visit catch exception block
        visitLabel(l2);
        // store the exception
        visitVarInsn(ASTORE, throwable);
        Label l3 = new Label();
        mv.visitLabel(l3);
        // load the exception
        visitVarInsn(ALOAD, throwable);
        // call printStackTrace()
        visitMethodInsn(INVOKEVIRTUAL, "java/lang/Throwable", "printStackTrace", "()V", false);


        Label l4 = new Label();
        visitLabel(l4);
        visitVarInsn(ALOAD, throwable);
        visitInsn(ATHROW);

        super.visitMaxs(maxStack + 4, maxLocals);
    }
}
