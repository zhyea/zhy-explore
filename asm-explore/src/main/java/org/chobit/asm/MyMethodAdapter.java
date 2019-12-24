package org.chobit.asm;


import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

public class MyMethodAdapter extends AdviceAdapter {

    private String methodId;

    private final int start = newLocal(Type.LONG_TYPE);

    private final int success = newLocal(Type.BOOLEAN_TYPE);

    private final int throwable = newLocal(Type.getType(Throwable.class));

    private static final String systemOwner = System.class.getName().replace('.', '/');

    private static final String watcherOwner = TimeClerk.class.getName().replace('.', '/');

    private Label tryStart = new Label(), tryEnd = new Label(), catchStart = new Label(), catchEnd = new Label();

    protected MyMethodAdapter(final MethodVisitor mv,
                              final int access,
                              final String name,
                              final String desc,
                              final String className) {
        super(ASM6, mv, access, name, desc);
        this.methodId = (className + "." + name + desc).replace('/', '.');
    }


    @Override
    protected void onMethodEnter() {
        visitMethodInsn(INVOKESTATIC, systemOwner, "currentTimeMillis", "()J", false);
        visitVarInsn(LSTORE, start);

        visitInsn(ICONST_0);
        visitVarInsn(ISTORE, success);

        mv.visitLabel(tryStart);
    }


    @Override
    protected void onMethodExit(int opcode) {
    }


    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        visitTryCatchBlock(tryStart, tryEnd, tryEnd, null);
        visitLabel(tryEnd);
        insertWatcher();
        visitInsn(ATHROW);
        super.visitMaxs(maxStack + 4, maxLocals);
    }


    private void insertWatcher() {
        visitVarInsn(ILOAD, success);

        visitLdcInsn(methodId);

        visitMethodInsn(INVOKESTATIC, systemOwner, "currentTimeMillis", "()J", false);
        visitVarInsn(LLOAD, start);
        visitInsn(LSUB);

        visitMethodInsn(INVOKESTATIC, watcherOwner, "update", "(ZLjava/lang/String;J)V", false);
    }
}
