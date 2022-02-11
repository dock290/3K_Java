import java.util.ArrayList;

public class PhoneList {
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
