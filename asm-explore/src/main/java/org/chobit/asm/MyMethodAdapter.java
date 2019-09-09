package org.chobit.asm;


import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

public class MyMethodAdapter extends AdviceAdapter {

    private String owner;

    private boolean isConstructor;

    private int start;

    private int end;

    private static final String systemOwner = System.class.getName().replace("\\.", "/");

    private static final String watcherOwner = TimeClerk.class.getName().replace("\\.", "/");

    protected MyMethodAdapter(final MethodVisitor mv,
                              final int access,
                              final String name,
                              final String desc,
                              final String owner) {
        super(ASM6, mv, access, name, desc);
        this.owner = owner + "." + name;
        this.isConstructor = name.equals("<init>");
    }


    @Override
    protected void onMethodEnter() {
        if (!isConstructor) {
            mv.visitMethodInsn(INVOKESTATIC, systemOwner, "currentTimeMillis", "()J", false);
            start = newLocal(Type.LONG_TYPE);
            mv.visitVarInsn(LSTORE, start);
        }
    }


    @Override
    protected void onMethodExit(int opcode) {
        if (!isConstructor) {
            mv.visitMethodInsn(INVOKESTATIC, systemOwner, "currentTimeMillis", "()J", false);
            end = newLocal(Type.LONG_TYPE);
            mv.visitVarInsn(LSTORE, end);

            mv.visitLdcInsn(owner);

            mv.visitVarInsn(LLOAD, end);
            mv.visitVarInsn(LLOAD, start);
            mv.visitInsn(LSUB);

            mv.visitMethodInsn(INVOKESTATIC, watcherOwner, "update", "(Ljava/lang/String;J)V", false);
        }
    }


    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        super.visitMaxs(maxStack + 4, maxLocals);
    }
}
