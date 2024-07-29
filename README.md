javac -cp /usr/lib/jvm/java-8-openjdk-amd64/lib/tools.jar Attacher.java -d ./jar-content
jar xf /usr/lib/jvm/java-8-openjdk-amd64/lib/tools.jar -C ./jar-content
jar cfm attacher.jar ./jar-content/MANIFEST.MF -C ./jar-content .

java -javaagent:/mnt/d/Projects/idea-projects/java-agent/static-agent/target/static-agent-1.0-SNAPSHOT-jar-with-dependencies.jar -jar ../example-application/target/example-application-1.0-SNAPSHOT.jar


java -jar ../example-application/target/example-application-1.0-SNAPSHOT.jar > ../example-application/target/application.log &

java -jar ../attacher/attacher.jar 284 /mnt/d/Projects/idea-projects/java-agent/dynamic-agent/target/dynamic-agent-1.0-SNAPSHOT-jar-with-dependencies.jar



javap -c -p -classpath /mnt/d/Projects/idea-projects/java-agent/agent-application/target/agent-application-1.0-SNAPSHOT-jar-with-dependencies.jar com.efasttask.javaagent.AgentmainJavaAgent