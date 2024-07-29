package com.efasttask;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class DynamicJavaAgent {
    public static void agentmain(String agentArgs, Instrumentation inst)  throws InstantiationException  {
        System.out.println("agentmain method called with args " + agentArgs);
        for (Class<?> loadedClass : inst.getAllLoadedClasses()) {
            if (loadedClass.getName().equals("com.efasttask.FuncClass")) {
                System.out.println("Found class " + loadedClass.getName());
                try {
                    InterceptingClassTransformer interceptingClassTransformer = new InterceptingClassTransformer();
                    interceptingClassTransformer.init();
                    inst.addTransformer(interceptingClassTransformer, true);

                    inst.retransformClasses(loadedClass);
                } catch (UnmodifiableClassException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
