package org.chobit.apt;


import com.sun.source.util.Trees;
import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.*;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

import static com.sun.tools.javac.tree.JCTree.*;

/**
 * Created by Pietro Caselani
 * On 25/01/14
 * Hello
 */
public class HelloProcessor extends AbstractProcessor {
    //region Fields
    private Trees trees;
    private TreeMaker treeMaker;
    private Names names;
    private Messager messager;
    //endregion

    //region Processor

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> result = new HashSet<>(1);
        result.add(Hello.class.getName());
        return result;
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {

        this.messager = processingEnv.getMessager();

        if (processingEnv instanceof JavacProcessingEnvironment) {

            Context context = ((JavacProcessingEnvironment) processingEnv).getContext();

            this.trees = JavacTrees.instance(context);
            this.treeMaker = TreeMaker.instance(context);
            this.names = Names.instance(context);
        } else if (Proxy.isProxyClass(processingEnv.getClass())) {
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(processingEnv);
            messager.printMessage(Diagnostic.Kind.WARNING, "11111111  ========================== >>>" + invocationHandler);
        }
        super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        if (null == trees) {
            messager.printMessage(Diagnostic.Kind.WARNING, "=====================================================");
            return true;
        }
        messager.printMessage(Diagnostic.Kind.WARNING, "=====================================================");

        Set<? extends Element> elements = env.getElementsAnnotatedWith(Hello.class);

        for (Element element : elements) {
            JCClassDecl classDecl = (JCClassDecl) trees.getTree(element);
            JCCompilationUnit compilationUnit = (JCCompilationUnit) trees.getPath(element).getCompilationUnit();

            addHelloMethod(classDecl, compilationUnit);
        }

        return true;
    }
    //endregion

    //region Private


    private void addHelloMethod(JCClassDecl classDecl, JCCompilationUnit compilationUnit) {

        JCExpression importExp = getClassExpression(JsonStringSerializer.class.getName());
        JCImport importVal = treeMaker.Import(importExp, false);
        compilationUnit.defs.append(importVal);

        JCExpression annoExp = getClassExpression(Override.class.getName());
        JCAnnotation overrideAnno = treeMaker.Annotation(annoExp, List.nil());
        JCModifiers modifiers = treeMaker.Modifiers(Flags.PUBLIC, List.of(overrideAnno));

        JCTree.JCExpression returnType = getClassExpression(String.class.getName());
        List<JCVariableDecl> parameters = List.nil();
        List<JCTypeParameter> generics = List.nil();
        Name methodName = getName("toString");
        List<JCExpression> throwz = List.nil();
        JCBlock methodBody = makeHelloBody();

        JCMethodDecl helloMethodDecl =
                treeMaker.MethodDef(modifiers, methodName, returnType, generics, parameters, throwz,
                        methodBody, null);

        classDecl.defs = classDecl.defs.append(helloMethodDecl);

    }

    private JCBlock makeHelloBody() {
        JCExpression printExpression = getMethodExpression(JsonStringSerializer.class.getName(), "toJson");

        List<JCExpression> printArgs = List.from(List.of(treeMaker.Ident(names.fromString("this"))));

        printExpression = treeMaker.Apply(List.nil(), printExpression, printArgs);

        ListBuffer<JCTree.JCStatement> testStatement3 = new ListBuffer<>();
        testStatement3.add(treeMaker.Return(printExpression));
        List<JCStatement> statements = List.from(testStatement3);

        return treeMaker.Block(0, statements);
    }

    private Name getName(String string) {
        return names.fromString(string);
    }
    //endregion


    /**
     * 获取方法表达式
     */
    private JCTree.JCExpression getMethodExpression(String className, String methodName) {
        JCTree.JCExpression ident = getClassExpression(className);
        return treeMaker.Select(ident, names.fromString(methodName));
    }


    /**
     * 获取类表达式
     */
    private JCTree.JCExpression getClassExpression(String className) {
        String[] arr = className.split("\\.");
        JCTree.JCExpression ident = treeMaker.Ident(names.fromString(arr[0]));

        for (int i = 1; i < arr.length; i++) {
            ident = treeMaker.Select(ident, names.fromString(arr[i]));
        }

        return ident;
    }



}