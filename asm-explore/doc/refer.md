
## Class

原始类：

```java
package org.chobit.asm;

public class MyApp {

    public static void main(String[] args) {
        boolean success = true;
        long start = System.currentTimeMillis();
        try {
            System.out.println("This is a test!");
        } catch (Throwable t) {
            success = false;
            throw t;
        } finally {
            TimeClerk.update(success, "zzzzz", System.currentTimeMillis() - start);
        }
    }

}
```

## ByteCode

ByteCode：

```text
// class version 52.0 (52)
// access flags 0x21
public class org/chobit/asm/MyApp {

  // compiled from: MyApp.java

  // access flags 0x1
  public <init>()V
   L0
    LINENUMBER 3 L0
    ALOAD 0
    INVOKESPECIAL java/lang/Object.<init> ()V
    RETURN
   L1
    LOCALVARIABLE this Lorg/chobit/asm/MyApp; L0 L1 0
    MAXSTACK = 1
    MAXLOCALS = 1

  // access flags 0x9
  public static main([Ljava/lang/String;)V
    TRYCATCHBLOCK L0 L1 L2 java/lang/Throwable
    TRYCATCHBLOCK L0 L1 L3 null
    TRYCATCHBLOCK L2 L4 L3 null
   L5
    LINENUMBER 6 L5
    ICONST_1
    ISTORE 1
   L6
    LINENUMBER 7 L6
    INVOKESTATIC java/lang/System.currentTimeMillis ()J
    LSTORE 2
   L0
    LINENUMBER 9 L0
    GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
    LDC "This is a test!"
    INVOKEVIRTUAL java/io/PrintStream.println (Ljava/lang/String;)V
   L1
    LINENUMBER 14 L1
    ILOAD 1
    LDC "zzzzz"
    INVOKESTATIC java/lang/System.currentTimeMillis ()J
    LLOAD 2
    LSUB
    INVOKESTATIC org/chobit/asm/TimeClerk.update (ZLjava/lang/String;J)V
   L7
    LINENUMBER 15 L7
    GOTO L8
   L2
    LINENUMBER 10 L2
   FRAME FULL [[Ljava/lang/String; I J] [java/lang/Throwable]
    ASTORE 4
   L9
    LINENUMBER 11 L9
    ICONST_0
    ISTORE 1
   L10
    LINENUMBER 12 L10
    ALOAD 4
    ATHROW
   L3
    LINENUMBER 14 L3
   FRAME SAME1 java/lang/Throwable
    ASTORE 5
   L4
    ILOAD 1
    LDC "zzzzz"
    INVOKESTATIC java/lang/System.currentTimeMillis ()J
    LLOAD 2
    LSUB
    INVOKESTATIC org/chobit/asm/TimeClerk.update (ZLjava/lang/String;J)V
    ALOAD 5
    ATHROW
   L8
    LINENUMBER 16 L8
   FRAME SAME
    RETURN
   L11
    LOCALVARIABLE t Ljava/lang/Throwable; L9 L3 4
    LOCALVARIABLE args [Ljava/lang/String; L5 L11 0
    LOCALVARIABLE success Z L6 L11 1
    LOCALVARIABLE start J L0 L11 2
    MAXSTACK = 6
    MAXLOCALS = 6
}

```