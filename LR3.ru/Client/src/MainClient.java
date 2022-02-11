import phone.Phone;
import phone.PhoneList;
import phone.Review;

import java.util.Objects;
import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) {
        PhoneList phoneList = null;

        try {
            RMIClient rmiClient = new RMIClient();

            System.out.println("Setting connection...");
            if (!rmiClient.setConnection()) {
                System.out.println("No connection");
                return;
            } else {
                System.out.println("Connection was set");
            }

            Scanner scanner = new Scanner(System.in);

            String token;
            while (!(token = scanner.next()).equals("exit")) {
                switch (token) {
                    case "getPhoneList": {
                        phoneList = rmiClient.rmiObject.getPhoneList();
                        System.out.printf("Received %d phones\n", phoneList.getPhones().size());
                    }
                    break;

                    case "getAverageScore": {
                        if (isPhoneListNotNull(phoneList))
                        {
                            int index = scanner.nextInt();
                            if (index >= 0 && index < phoneList.getPhones().size()) {
                                System.out.printf("Average score: %d\n", rmiClient.rmiObject.getAverageScore(phoneList.getPhones().get(index)));
                            } else {
                                System.out.println("Index is out of bounds");
                            }
                        }
                    }
                    break;

                    case "cout": {
                        if (isPhoneListNotNull(phoneList)) {
                            cout(phoneList);
                        }
                    }
                    break;

                    default: {
                        System.out.println("Command was not resolved");
                    }
                    break;
                }
            }

            scanner.close();
            System.out.println("Client was stopped");
        } catch (Exception e) {
            System.out.println("Unexpected ERROR occurred:");
            System.out.println(e.getMessage());
        }
    }

    public static void cout(PhoneList phoneList) {
        for (Phone phone : phoneList.getPhones()) {
            System.out.printf(
                    "Модель: %d\nНазвание: %s\nПроизводитель: %s\nОперационная система: %s\nГод выпуска: %s\n",
                    phone.getModelIndex(), phone.getName(), phone.getManufacturer(),
                    phone.getOperatingSystem(), phone.getReleaseYear());

            if (!phone.getReviews().isEmpty()) {
                System.out.println("Обзоры:");
                for (Review review : phone.getReviews()) {
                    System.out.printf("\tСайт: %s Оценка: %d%n", review.getTitle(), review.getScore());
                }
            }

            System.out.printf("Средняя оценка: %d%n", phone.getAverage());

            System.out.println();
        }
    }

    public static boolean isPhoneListNotNull(PhoneList phoneList) {
        if (Objects.isNull(phoneList)) {
            System.out.println("Phone list is null");
            return false;
        } else {
            return true;
        }
    }
}
