package org.chobit.mvel;

import org.junit.jupiter.api.Test;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.templates.TemplateRuntime;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MvelTest {

    private static final ParserContext CONTEXT = new ParserContext();

    static {
        Method[] declaredMethods = MvelFunctions.class.getDeclaredMethods();
        for (Method method : declaredMethods) {
            CONTEXT.addImport(method.getName(), method);
        }
    }


    @Test
    public void equalAnalyze() {
        Map<String, Object> map = customObject();
        Boolean r = MVEL.evalToBoolean("item.a==1", map);
        System.out.println(r);
    }


    @Test
    public void inAnalyze() {
        Map<String, Object> map = customObject();
        Object obj = MVEL.eval("([1, 2] contains item.a)", map);
        System.out.println(obj);
    }


    @Test
    public void concatStr() {
        Map<String, Object> map = customObject();
        String str = MVEL.eval("item.a + ' is zhangsan'", map, String.class);
        System.out.println(str);
    }


    @Test
    public void func1() {
        String expression = "def calc() { return (@{currentRate}B-@{historyRate}B); } return calc();";
        Map<String, Object> paramMap = new HashMap<>(2);
        paramMap.put("currentRate", 0.01800000);
        paramMap.put("historyRate", 0.01600000);

        expression = (String) TemplateRuntime.eval(expression, paramMap);
        System.out.println(expression);
        Object object = MVEL.eval(expression, paramMap);
        System.out.println("输出结果：" + object);


        String exp = "def sub(a, idx){return a.subsString(idx)}";

        MVEL.eval("sub('abcdef', 3)");
    }


    @Test
    public void func2() {
        Map<String, Object> map = customObject();
        Object exp = MVEL.compileExpression("today", CONTEXT);
        System.out.println(exp);
        Object result = MVEL.executeExpression(exp, map);
        System.out.println(result);
    }


    @Test
    public void func3() {
        String expression = "def hello() { return \"Hello!\"; } hello();";
        Map<String, Object> paramMap = new HashMap<>();
        Object object = MVEL.eval(expression, paramMap);
        System.out.println(object); // Hello!
    }


    @Test
    public void func4() {
        Map<String, Object> map = customObject();
        String expression = "def subStr(str, idx) { return str.substring(idx); }";
        MVEL.eval(expression, map);
        Object object = MVEL.eval("subStr(item.name, 4)", map);
        System.out.println(object); // Hello!
    }


    @Test
    public void func5() {
        Map<String, Object> map = customObject();
        Object object = MVEL.eval("item.name.substring(4)", map);
        System.out.println(object); // Hello!
    }


    private Map<String, Object> customObject() {
        Map<String, Object> inner = new HashMap<>(8);
        inner.put("a", 1);
        inner.put("b", "2");
        inner.put("name", "robinZhang");

        Map<String, Object> wrapper = new HashMap<>(8);
        wrapper.put("item", inner);
        wrapper.put("name", "zhangsan");

        return wrapper;
    }


}
