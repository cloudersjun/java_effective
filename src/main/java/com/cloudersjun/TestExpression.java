package com.cloudersjun;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.mvel2.MVEL;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.greenpineyu.fel.Expression;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.context.FelContext;
import com.greenpineyu.fel.context.MapContext;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

/**
 * @author yujun
 * @since 2020/4/20
 */
public class TestExpression {


    public static void main(String[] args) throws Exception {
        speed();
    }

    /**
     * 测试性能
     */
    private static void speed() throws Exception {
        final Map<String, Object> vars = new HashMap<>();
        vars.put("i", 100);
        vars.put("pi", 3.14d);
        vars.put("d", -3.9);
        vars.put("b", 4.2f);

        String[] exps = new String[5];
        int index = 0;
        exps[index++] = "#i*#pi";
        exps[index++] = "#i*#pi+23";
        exps[index] = "#i*#pi + #b*#d-(#d+#b)";


        int times = 1000;
        for (String exp : exps) {
            if (exp == null) {
                break;
            }
            fel(exp, vars, times);
            mvel(exp, vars, times);
            qlExpress(exp, vars, times);
            spEl(exp, vars, times);
            System.out.println();
        }
    }


    private static void fel(String exp, Map<String, Object> vars, int times) {
        long start = System.currentTimeMillis();
        FelEngine engine = new FelEngineImpl();
        FelContext ctx = new MapContext(vars);
        Expression expObj = engine.compile(exp, ctx);
        int i = 0;
        while (i++ < times) {
            expObj.eval(ctx);
        }
        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println("fel exp[" + exp + "] times:[" + times + "] cost[" + cost + "]");
    }


    public static void mvel(String exp, Map<String, Object> map, int times) {
        long start = System.currentTimeMillis();
        Serializable serializable = MVEL.compileExpression(exp);
        for (int i = 0; i < times; i++) {
            MVEL.executeExpression(serializable, map, Double.class);
        }
        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println("mvel exp[" + exp + "] times:[" + times + "] cost[" + cost + "]");
    }

    public static void qlExpress(String exp, Map<String, Object> map, int times) throws Exception {
        long start = System.currentTimeMillis();
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<>();
        map.forEach(context::put);
        for (int i = 0; i < times; i++) {
            runner.execute(exp, context, null, true, false);
        }
        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println("qlExpress exp[" + exp + "] times:[" + times + "] cost[" + cost + "]");
    }

    public static void spEl(String exp, Map<String, Object> map, int times) {
        long start = System.currentTimeMillis();
        ExpressionParser parser = new SpelExpressionParser();

        StandardEvaluationContext context = new StandardEvaluationContext();
        map.forEach(context::setVariable);
        org.springframework.expression.Expression expression = parser.parseExpression(exp);
        for (int i = 0; i < times; i++) {
            expression.getValue(context, Double.class);
        }
        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println("spEl exp[" + exp + "] times:[" + times + "] cost[" + cost + "]");
    }

}

