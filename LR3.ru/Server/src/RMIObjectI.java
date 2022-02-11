import phone.Phone;
import phone.PhoneList;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIObjectI extends Remote {
    int getAverageScore(Phone phone) throws RemoteException;

    PhoneList getPhoneList() throws RemoteException;
}
