import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainServer {
    public static void main(String[] args) {
        System.out.println("Server is starting..");
        try {
            RMIServer server = new RMIServer();
            Registry registry = LocateRegistry.createRegistry(1099);

            Naming.rebind("//localhost/RMIObject", server);

            System.out.println("Server started");
        } catch (Exception e) {
            System.out.println("Server couldn't start");
            e.printStackTrace();
        }
    }
}
