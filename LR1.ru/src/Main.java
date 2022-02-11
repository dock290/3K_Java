import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите путь к файлу xml " +
                "или оставьте строку пустой чтобы использовать значение по-умолчанию\nПуть [pr_1.xml]:");
        String xmlFilePath = scanner.nextLine();

        if (xmlFilePath.length() == 0) {
            xmlFilePath = "pr_1.xml";
        }
        
        try {
            PhoneList phones = DOMParser.parseGroupNode(xmlFilePath);

            for (Phone phone : phones.getPhones()) {
                System.out.println(String.format(
                        "Телефон: %s\nПроизводитель: %s\nОперационная система: %s", phone.getName(), phone.getManufacturer(), phone.getOperatingsystem())
                );

                if (!phone.getReviews().isEmpty()) {
                    System.out.println("Обзоры:");
                    for (Review review : phone.getReviews()) {
                        System.out.println(String.format("\tСайт: %s Оценка: %d", review.getTitle(), review.getScore()));
                    }
                }

                System.out.println(String.format("Средняя оценка: %d", phone.getAverage()));

                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

