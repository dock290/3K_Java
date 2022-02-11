package psuti.kirgizov.servlet;

import psuti.kirgizov.database.Database;
import psuti.kirgizov.phone.Phone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PhoneService {

    public static List<Phone> service(HttpServletRequest request, HttpServletResponse response) {
        Database database = new Database();
        List<Phone> phoneList = new ArrayList<>();

        try {
            if (Objects.nonNull(request.getParameter("findByNumber"))) {
                int number = Integer.parseInt(request.getParameter(Database.NUMBER));
                phoneList = database.getByNumber(number);
            } else if (Objects.nonNull(request.getParameter("findByName"))) {
                String name = request.getParameter(Database.NAME);
                phoneList = database.getByName(name);
            } else if (Objects.nonNull(request.getParameter("saveTable"))) {
                String[] numbers = request.getParameterValues(Database.NUMBER);
                String[] names = request.getParameterValues(Database.NAME);
                String[] manufacturers = request.getParameterValues(Database.MANUFACTURER);
                String[] operatingsystems = request.getParameterValues(Database.OPERATING_SYSTEM);
                String[] releaseyears = request.getParameterValues(Database.RELEASE_YEAR);
                String[] sites = request.getParameterValues(Database.SITE);
                String[] scores = request.getParameterValues(Database.SCORE);
                String[] _checkboxes = request.getParameterValues("checkbox");
                List<String> checkboxes = new ArrayList<>();
                if (_checkboxes != null) {
                    Collections.addAll(checkboxes, _checkboxes);
                }

                phoneList = database.getAll();

                extern:
                for (int i = 0; i < numbers.length; i++) {
                    Phone phone = new Phone(Integer.parseInt(numbers[i]), names[i], manufacturers[i],
                            operatingsystems[i], Integer.parseInt(releaseyears[i]),
                            sites[i], Integer.parseInt(scores[i]));

                    try {
                        if (checkboxes.contains(Integer.toString(i))) {
                            for (Phone _phone : phoneList) {
                                if (_phone.getNumber() == phone.getNumber()) {
                                    database.remove(phone.getNumber());
                                    continue extern;
                                }
                            }
                        } else {
                            for (Phone _phone : phoneList) {
                                if (_phone.getNumber() == phone.getNumber()) {
                                    database.set(phone.getNumber(), phone.getName(),
                                            phone.getManufacturer(), phone.getOperatingSystem(),
                                            phone.getReleaseYear(), phone.getSite(), phone.getScore());
                                    continue extern;
                                }
                            }

                            database.add(phone.getNumber(), phone.getName(),
                                    phone.getManufacturer(), phone.getOperatingSystem(),
                                    phone.getReleaseYear(), phone.getSite(), phone.getScore());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                phoneList = database.getAll();
            } else if (Objects.nonNull(request.getParameter("showFullTable"))) {
                phoneList = database.getAll();
            } else if (Objects.nonNull(request.getParameter("resetTable"))) {
                database.reset();
                phoneList = database.getAll();
            } else {
                phoneList = database.getAll();
            }
        } catch (Exception e) {
            e.printStackTrace();

            try {
                phoneList = database.getAll();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

        return phoneList;
    }
}
