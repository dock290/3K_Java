import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.xml.sax.SAXException;
import phone.Phone;
import phone.PhoneList;
import phone.Review;

import javax.xml.parsers.ParserConfigurationException;

public class RMIServer extends UnicastRemoteObject implements RMIObjectI {
    private PhoneList phones;

    protected RMIServer() throws RemoteException, IOException, SAXException, ParserConfigurationException {
//        phones = DOMParser.parseGroupNode("phones.xml");
        phones = new PhoneList();
    }

    @Override
    public int getAverageScore(Phone phone) {
        int scoreSum = 0;

        for (Review review : phone.getReviews()) {
            scoreSum += review.getScore();
        }

        int averageScore = scoreSum / phone.getReviews().size();

        phone.setAverage(averageScore);

        return averageScore;
    }

    @Override
    public PhoneList getPhoneList() throws RemoteException {
        return phones;
    }
}
