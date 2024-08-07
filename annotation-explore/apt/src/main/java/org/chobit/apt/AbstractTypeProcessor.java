package org.chobit.apt;

import com.sun.source.util.Trees;
import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;


/**
 * {@link AbstractProcessor} 的一个抽象子类，实现了一些通用的内容
 *
 * @author robin
 */
public abstract class AbstractTypeProcessor extends AbstractProcessor {


	private Trees trees;
	private TreeMaker treeMaker;
	private Names names;
	private Messager messager;



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

			super.init(processingEnv);
		} else {
			messager.printMessage(Diagnostic.Kind.WARNING, "is not supported.");
		}
	}


	/**
	 * 构造注解
	 *
	 * @param annotationClass 注解类
	 * @return 注解信息
	 */
	protected JCTree.JCAnnotation makeAnnotation(Class<?> annotationClass) {
		JCTree.JCExpression annoExp = getClassExpression(annotationClass.getName());
		return treeMaker.Annotation(annoExp, List.nil());
	}


	/**
	 * 填充import内容
	 *
	 * @param typeElement 类Element
	 * @param clazz       要import的类
	 */
	protected void makeImport(TypeElement typeElement, Class<?> clazz) {
		JCTree.JCCompilationUnit compilationUnit = (JCTree.JCCompilationUnit) trees.getPath(typeElement).getCompilationUnit();
		JCTree.JCExpression importExp = getClassExpression(clazz.getName());
		JCTree.JCImport importVal = getTreeMaker().Import(importExp, false);
		compilationUnit.defs.append(importVal);
	}


	/**
	 * 获取方法对应的Expression
	 *
	 * @param className  类名称
	 * @param methodName 方法名称
	 * @return 方法对应的Expression
	 */
	protected JCTree.JCExpression getMethodExpression(String className, String methodName) {
		JCTree.JCExpression ident = getClassExpression(className);
		return treeMaker.Select(ident, names.fromString(methodName));
	}


	/**
	 * 获取类表达式
	 *
	 * @param className 类名称
	 * @return 类对应的表达式
	 */
	protected JCTree.JCExpression getClassExpression(String className) {
		String[] arr = className.split("\\.");
		JCTree.JCExpression ident = treeMaker.Ident(names.fromString(arr[0]));

		for (int i = 1; i < arr.length; i++) {
			ident = treeMaker.Select(ident, names.fromString(arr[i]));
		}

		return ident;
	}


	/**
	 * 获取Name
	 *
	 * @param str 字符串
	 * @return Name
	 */
	protected Name getName(String str) {
		return names.fromString(str);
	}


	/**
	 * 获取Trees
	 *
	 * @return Trees
	 */
	public Trees getTrees() {
		return trees;
	}


	/**
	 * 获取TreeMaker
	 *
	 * @return TreeMaker
	 */
	public TreeMaker getTreeMaker() {
		return treeMaker;
	}


	/**
	 * 获取Messenger
	 *
	 * @return Messenger
	 */
	public Messager getMessager() {
		return messager;
	}
}
