package org.chobit.mvel;

import org.mvel2.MVEL;

import java.util.HashMap;
import java.util.Map;

/**
 * @author robin
 */
public class MyApp {

    public static void main(String[] args) throws InterruptedException {
        Map<String, Object> inner = new HashMap<>(8);
        inner.put("a", "1");
        inner.put("b", "2");

        Map<String, Object> wrapper = new HashMap<>(8);
        wrapper.put("item", inner);
        wrapper.put("name", "zhangsan");

        Object obj = MVEL.eval("item.a + ' is zhangsan'", wrapper);
        System.out.println(obj);

        try{
            obj = MVEL.eval("item.e", wrapper);
        }catch (Exception e){
            System.out.println(e.getClass());
        }
        System.out.println(obj);

    }
}
