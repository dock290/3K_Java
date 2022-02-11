import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    RMIObjectI rmiObject = null;

    public RMIClient() {

    }

    public boolean setConnection() {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);

            rmiObject = (RMIObjectI) Naming.lookup("//localhost/RMIObject");

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
