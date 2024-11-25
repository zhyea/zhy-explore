package org.chobit.apt;


import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.sun.tools.javac.tree.JCTree.JCBlock;

/**
 * Created by Pietro Caselani
 * On 25/01/14
 * Hello
 */
public class ToJsonProcessor extends AbstractTypeProcessor {


	@Override
	public Set<String> getSupportedAnnotationTypes() {
		Set<String> result = new HashSet<>(1);
		result.add(ToJson.class.getName());
		return result;
	}


	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		if (!isInitialized()) {
			getMessager().printMessage(Diagnostic.Kind.WARNING, "=====================================================");
			return true;
		}
		getMessager().printMessage(Diagnostic.Kind.WARNING, "=====================================================");

		Set<TypeElement> typeElements = new LinkedHashSet<>();
		annotations.forEach(e -> {

			Set<TypeElement> eleSet = ElementFilter.typesIn(roundEnv.getElementsAnnotatedWith(e));

			OUT:
			for (TypeElement ele : eleSet) {
				for (ExecutableElement exec : ElementFilter.methodsIn(ele.getEnclosedElements())) {
					if (exec.getSimpleName().contentEquals("toString") && exec.getParameters().isEmpty()) {
						continue OUT;
					}
				}
				typeElements.add(ele);
			}
		});

		if (typeElements.isEmpty()) {
			return true;
		}

		for (TypeElement ele : typeElements) {
			this.makeToStringMethod(ele);
		}

		return true;
	}
	//endregion

	//region Private


	private void makeToStringMethod(TypeElement typeElement) {

		makeImport(typeElement, JsonStringSerializer.class);

		JCTree.JCModifiers modifiers = getTreeMaker().Modifiers(Flags.PUBLIC, List.nil());
		JCTree.JCExpression returnType = getClassExpression(String.class.getName());

		List<JCTree.JCVariableDecl> parameters = List.nil();
		List<JCTree.JCTypeParameter> generics = List.nil();
		Name methodName = getName("toString");
		List<JCTree.JCExpression> exceptThrows = List.nil();

		JCBlock methodBody = makeToStringBody();

		JCTree.JCMethodDecl methodDecl =
				getTreeMaker().MethodDef(modifiers, methodName, returnType, generics, parameters, exceptThrows,
						methodBody, null);

		JCTree.JCClassDecl classDecl = (JCTree.JCClassDecl) getTrees().getTree(typeElement);
		classDecl.defs = classDecl.defs.append(methodDecl);
	}

	private JCBlock makeToStringBody() {
		JCTree.JCExpression serializerIdent = getMethodExpression(JsonStringSerializer.class.getName(), "toJson");
		List<JCTree.JCExpression> toJsonArgs = List.from(List.of(getTreeMaker().Ident(getName("this"))));

		JCTree.JCReturn returnStatement = getTreeMaker().Return(
				getTreeMaker().Apply(List.nil(), serializerIdent, toJsonArgs)
		);

		return getTreeMaker().Block(0, List.of(returnStatement));
	}


}