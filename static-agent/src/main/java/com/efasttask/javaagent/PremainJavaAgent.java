package com.efasttask.javaagent;

import java.lang.instrument.Instrumentation;

public class PremainJavaAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("premain method called with args: " + agentArgs);
        InterceptingClassTransformer interceptingClassTransformer = new InterceptingClassTransformer();
        interceptingClassTransformer.init();
        inst.addTransformer(interceptingClassTransformer);
    }
}
