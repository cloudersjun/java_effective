package com.cloudersjun;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.mvel2.MVEL;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.googlecode.aviator.AviatorEvaluator;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.context.FelContext;
import com.greenpineyu.fel.context.MapContext;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

/**
 * Created by lenovo on 2017/5/25.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        final Map<String, Object> vars = new HashMap<>();
        vars.put("k_1", 100d);
        vars.put("k_2", -3.14d);
        String exp = "#k_1 * #k_2 + 4.5 ";
        spEl(exp, vars);
    }


    public static void mvel(String exp, Map<String, Object> vars) {
        Serializable serializable = MVEL.compileExpression(exp);
        System.out.println(MVEL.executeExpression(serializable, vars, Double.class));
    }

    public static void fel(String exp, Map<String, Object> vars) {
        FelEngine engine = new FelEngineImpl();
        FelContext ctx = new MapContext(vars);
        com.greenpineyu.fel.Expression expObj = engine.compile(exp, ctx);
        System.out.println(expObj.eval(ctx));
    }

    public static void spEl(String exp, Map<String, Object> vars) {
        ExpressionParser parser = new SpelExpressionParser();

        StandardEvaluationContext context = new StandardEvaluationContext();
        vars.forEach(context::setVariable);
        org.springframework.expression.Expression expression = parser.parseExpression(exp);
        System.out.println(expression.getValue(context, Double.class));
    }

    public static void qlExpress(String exp, Map<String, Object> map) throws Exception {
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<>();
        map.forEach(context::put);
        System.out.println(runner.execute(exp, context, null, true, false));
    }

    public static void aviator(String exp, Map<String, Object> map) throws IOException {
        com.googlecode.aviator.Expression expression = AviatorEvaluator.getInstance().compile(exp);
        Double result = (Double) expression.execute(map);
        System.out.println(result);
    }

    public static void groovy(String exp, Map<String, Object> map) {
        Binding binding = new Binding();
        map.forEach(binding::setVariable);
        GroovyShell groovyShell = new GroovyShell(binding);
        System.out.println(groovyShell.evaluate(exp));
    }

}
