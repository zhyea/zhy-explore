package org.chobit.asm;


import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

public class MyMethodAdapter extends AdviceAdapter {

    private String methodId;

    private int start;

    private int end;

    private static final String systemOwner = System.class.getName().replace('.', '/');

    private static final String watcherOwner = TimeClerk.class.getName().replace('.', '/');

    protected MyMethodAdapter(final MethodVisitor mv,
                              final int access,
                              final String name,
                              final String desc,
                              final String className) {
        super(ASM6, mv, access, name, desc);
        this.methodId = (className + "." + name+desc).replace('/', '.');
    }


    @Override
    protected void onMethodEnter() {
        visitMethodInsn(INVOKESTATIC, systemOwner, "currentTimeMillis", "()J", false);
        start = newLocal(Type.LONG_TYPE);
        visitVarInsn(LSTORE, start);
    }


    @Override
    protected void onMethodExit(int opcode) {
        visitMethodInsn(INVOKESTATIC, systemOwner, "currentTimeMillis", "()J", false);

        end = newLocal(Type.LONG_TYPE);
        visitVarInsn(LSTORE, end);

        visitLdcInsn(methodId);

        visitVarInsn(LLOAD, end);
        visitVarInsn(LLOAD, start);
        visitInsn(LSUB);

        visitMethodInsn(INVOKESTATIC, watcherOwner, "update", "(Ljava/lang/String;J)V", false);
    }


    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        super.visitMaxs(maxStack + 4, maxLocals);
    }
}
