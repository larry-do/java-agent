package javaagent;

import java.lang.instrument.Instrumentation;

public class AgentmainJavaAgent {
    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("agentmain method called with args: " + agentArgs);
    }
}
