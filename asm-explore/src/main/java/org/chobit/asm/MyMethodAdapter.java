package org.chobit.asm;


import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class MyMethodAdapter extends AdviceAdapter {

    private String methodId;


    private Label tryBlockStart = new Label(), tryBlockEnd = new Label(), catchExceptionBlockStart = new Label(), exitBlock = new Label();

    protected MyMethodAdapter(final MethodVisitor mv,
                              final int access,
                              final String name,
                              final String desc,
                              final String className) {
        super(ASM6, mv, access, name, desc);
        this.methodId = (className + "." + name + desc).replace('/', '.');
    }


    @Override
    public void visitCode(){

        super.visitCode();
        visitTryCatchBlock(tryBlockStart, tryBlockEnd, catchExceptionBlockStart, "java/lang/Exception");
        visitLabel(tryBlockStart);
    }

    @Override
    protected void onMethodEnter() {
    }


    @Override
    protected void onMethodExit(int opcode) {
    }


    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        // visit try block end label
        this.visitLabel(tryBlockEnd);
        // visit normal execution exit block
        this.visitJumpInsn(GOTO, exitBlock);

        // visit catch exception block
        this.visitLabel(catchExceptionBlockStart);
        // store the exception
        this.visitVarInsn(ASTORE, ACONST_NULL);
        // load the exception
        this.visitVarInsn(ALOAD, ACONST_NULL);
        // call printStackTrace()
        //this.visitInsn(Opcodes.ATHROW);
        this.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Exception", "printStackTrace", "()V", false);


        // exit from this dynamic block
        this.visitLabel(exitBlock);

        super.visitMaxs(maxStack + 2, maxLocals + 2);
    }



}
