package com.efasttask;


import net.bytebuddy.agent.ByteBuddyAgent;

import java.io.File;

public class Attacher {
    public static void main(String[] args) {
        ByteBuddyAgent.attach(new File("/home/nhatdo/Projects/Java/java-agent/dynamic-agent/target/dynamic-agent-1.0-SNAPSHOT-jar-with-dependencies.jar"), "12525");
    }
}
