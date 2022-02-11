package phone;

import java.io.Serializable;
import java.util.ArrayList;

public class PhoneList implements Serializable {
    ArrayList<Phone> phones;

    public PhoneList() {
        phones = new ArrayList<>();
    }

    public void addPhone(Phone phone) {
        phones.add(phone);
    }

    public ArrayList<Phone> getPhones() {
        return phones;
    }
}
