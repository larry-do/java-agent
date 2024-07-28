import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

public class Attacher {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java com.efasttask.Attacher <PID> <path-to-agent-jar>");
            return;
        }

        String pid = args[0]; // Process ID of the target JVM
        String agentPath = args[1]; // Path to the agent JAR file

        try {
            System.out.println("Attached VM to PID " + pid);
            VirtualMachine vm = VirtualMachine.attach(pid);
            System.out.println("Load agent " + agentPath);
            vm.loadAgent(agentPath);
            System.out.println("Detach");
            vm.detach();
            System.out.println("Agent attached successfully.");
        } catch (AttachNotSupportedException | IOException | AgentLoadException | AgentInitializationException e) {
            throw new RuntimeException(e);
        }
    }
}
