package org.chobit.core;

import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.*;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import java.util.HashSet;
import java.util.Set;


@SupportedAnnotationTypes({"org.chobit.core.ToJsonString"})
public class ToJsonStringProcessor extends AbstractProcessor {


    private JavacTrees trees;
    private TreeMaker treeMaker;
    private Names names;


    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        Context context = ((JavacProcessingEnvironment) processingEnv).getContext();

        this.trees = JavacTrees.instance(context);
        this.treeMaker = TreeMaker.instance(context);
        this.names = Names.instance(context);
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> result = new HashSet<>(1);
        result.add(ToJsonString.class.getCanonicalName());
        return result;
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        Set<TypeElement> typeElements = ElementFilter.typesIn(roundEnv.getElementsAnnotatedWith(ToJsonString.class));

        for (TypeElement ele : typeElements) {
            JCTree.JCClassDecl classDecl = trees.getTree(ele);
            classDecl.accept(new TreeTranslator() {
                @Override
                public void visitClassDef(JCTree.JCClassDecl tree) {
                    super.visitClassDef(tree);
                }
            });
            this.makeToStringMethod();
        }

        return true;

    }


    private void makeToStringMethod() {

        Name methodName = names.fromString("toString");
        JCTree.JCExpression methodReturnType = treeMaker.Ident(names.fromString(String.class.getName()));

        ListBuffer<JCTree.JCStatement> statements = new ListBuffer<>();

        JCTree.JCExpressionStatement statement = treeMaker.Exec(
                treeMaker.Apply(
                        List.nil(),
                        treeMaker.Select(
                                treeMaker.Ident(names.fromString(JsonStringSerializer.class.getName())),
                                names.fromString("toJson")
                        ),
                        List.of(treeMaker.Ident(names.fromString("this")))
                )
        );

        treeMaker.Select(treeMaker.Ident(names.fromString("this")), names.fromString("this"));

        statements.append(statement);

        JCTree.JCBlock body = treeMaker.Block(0, statements.toList());

        treeMaker.MethodDef(treeMaker.Modifiers(Flags.PUBLIC), methodName, methodReturnType,
                List.nil(), List.nil(), List.nil(), body, null);
    }

}
