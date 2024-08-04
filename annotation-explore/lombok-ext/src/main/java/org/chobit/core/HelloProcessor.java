package org.chobit.core;


import com.sun.source.util.Trees;
import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.*;

import javax.annotation.processing.*;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

import static com.sun.tools.javac.code.TypeTag.VOID;
import static com.sun.tools.javac.tree.JCTree.*;
import static javax.lang.model.SourceVersion.RELEASE_6;

/**
 * Created by Pietro Caselani
 * On 25/01/14
 * Hello
 */
@SupportedAnnotationTypes("org.chobit.core.ToJsonString")
@SupportedSourceVersion(RELEASE_6)
public class HelloProcessor extends AbstractProcessor {
	//region Fields
	private Trees mTrees;
	private TreeMaker treeMaker;
	private Names names;
	//endregion

	//region Processor
	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		Context context = ((JavacProcessingEnvironment) processingEnv).getContext();

		this.mTrees = JavacTrees.instance(context);
		this.treeMaker = TreeMaker.instance(context);
		this.names = Names.instance(context);

		super.init(processingEnv);
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
		Set<? extends Element> elements = env.getElementsAnnotatedWith(ToJsonString.class);

		if (elements.size() > 0) {
			for (Element element : elements) {
				JCClassDecl classDecl = (JCClassDecl) mTrees.getTree(element);

				addMainMethod(classDecl, element.toString());
				addHelloMethod(classDecl);
			}
		}

		return true;
	}
	//endregion

	//region Private
	private void addMainMethod(JCClassDecl classDecl, String className) {
		JCModifiers modifiers = treeMaker.Modifiers(Flags.PUBLIC | Flags.STATIC);
		JCExpression returnType = treeMaker.TypeIdent(VOID);
		List<JCVariableDecl> parameters = makeMainParameters();
		List<JCTypeParameter> generics = List.nil();
		List<JCExpression> throwz = List.nil();
		JCBlock methodBody = makeMainBody(className);
		Name methodName = getName("main");

		JCMethodDecl mainMethodDecl =
				treeMaker.MethodDef(modifiers, methodName, returnType, generics, parameters, throwz,
						methodBody, null);

		classDecl.defs = classDecl.defs.append(mainMethodDecl);
	}

	private List<JCVariableDecl> makeMainParameters() {
		JCIdent paramType = treeMaker.Ident(getName("String"));

		JCArrayTypeTree paramArray = treeMaker.TypeArray(paramType);

		JCVariableDecl paramDecl =
				treeMaker.VarDef(treeMaker.Modifiers(Flags.PARAMETER), getName("args"), paramArray, null);

		return List.from(new JCVariableDecl[]{paramDecl});
	}

	private JCBlock makeMainBody(String className) {
		String[] strings = className.split("\\.");

		JCExpression classNameIdent = treeMaker.Ident(getName(strings[0]));

		for (int i = 1; i < strings.length; i++) {
			classNameIdent = treeMaker.Select(classNameIdent, getName(strings[i]));
		}

		JCNewClass classObj = treeMaker.NewClass(null, List.<JCExpression>nil(), classNameIdent,
				List.<JCExpression>nil(), null);

		JCFieldAccess printHello = treeMaker.Select(classObj, getName("printHello"));

		JCMethodInvocation printHelloInv =
				treeMaker.Apply(List.<JCExpression>nil(), printHello, List.<JCExpression>nil());

		JCStatement exec = treeMaker.Exec(printHelloInv);

		List<JCStatement> statements = List.of(exec);

		return treeMaker.Block(0, statements);
	}

	private void addHelloMethod(JCClassDecl classDecl) {
		JCModifiers modifiers = treeMaker.Modifiers(Flags.PUBLIC);
		JCTree.JCExpression returnType = getClassExpression(String.class.getName());
		List<JCVariableDecl> parameters = List.nil();
		List<JCTypeParameter> generics = List.nil();
		Name methodName = getName("printHello");
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