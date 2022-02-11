import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            Database database = new Database();

            System.out.println("Для получения списка команд введите \"help\"");
            System.out.println("Введите команду:");

            String line;
            while (!(line = in.readLine()).equals("exit")) {
                try {
                    switch (line) {
                        case "login" -> {
                            String user = "";
                            String pass = "";

                            System.out.println("Введите имя пользователя " +
                                    "или оставьте пустым чтобы использовать пользователя по-умолчанию (ROOT ROOT):");

                            if (!(user = in.readLine()).equals("")) {
                                System.out.println("Введите пароль или " +
                                        "оставьте пустым чтобы использовать пользователя по-умолчанию (ROOT ROOT):");

                                pass = in.readLine();
                            }

                            if (!user.equals("") && !pass.equals("")) {
                                database.setUser(user, pass);
                            } else {
                                database.setUser(Database.DEFAULT_USER, Database.DEFAULT_PASS);
                            }
                        }
                        case "add" -> {
                            int number = -1;
                            String name;
                            String manufacturer;
                            String operatingsystem;
                            int releaseyear = -1;
                            int score = -1;

                            boolean ok = false;

                            System.out.println("Введите номер:");
                            do {
                                try {
                                    number = Integer.parseInt(in.readLine());
                                    ok = true;
                                } catch (NumberFormatException ignored) {
                                    System.out.println("Не удалось преобразовать в число. Попробуйте снова");
                                }
                            } while (!ok);

                            System.out.println("Введите название:");
                            name = in.readLine();

                            System.out.println("Введите производителя:");
                            manufacturer = in.readLine();

                            System.out.println("Введите операционную систему:");
                            operatingsystem = in.readLine();

                            ok = false;

                            System.out.println("Введите год выпуска:");
                            do {
                                try {
                                    releaseyear = Integer.parseInt(in.readLine());
                                    ok = true;
                                } catch (NumberFormatException ignored) {
                                    System.out.println("Не удалось преобразовать в число. Попробуйте снова");
                                }
                            } while (!ok);

                            ok = false;

                            System.out.println("Введите оценку:");
                            do {
                                try {
                                    score = Integer.parseInt(in.readLine());
                                    ok = true;
                                } catch (NumberFormatException ignored) {
                                    System.out.println("Не удалось преобразовать в число. Попробуйте снова");
                                }
                            } while (!ok);

                            try {
                                database.add(number, name, manufacturer, operatingsystem, releaseyear, score);
                                System.out.println("Запись успешно добавлена");
                            } catch (SQLException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        case "set" -> {
                            int number = -1;

                            boolean ok = false;

                            System.out.println("Введите номер:");
                            do {
                                try {
                                    number = Integer.parseInt(in.readLine());

                                    database.get(number);

                                    ok = true;
                                } catch (NumberFormatException e) {
                                    System.out.println("Не удалось преобразовать в число. Попробуйте снова");
                                } catch (SQLException e) {
                                    System.out.printf("Запись с номером %d не найдена\n", number);
                                }
                            } while (!ok);

                            System.out.println("Введите поле для изменения" +
                                    " (number, name, manufacturer, operatingsystem, releaseyear, score):");

                            String column = in.readLine();

                            System.out.println("Введите новое значение:");

                            String value = in.readLine();

                            try {
                                database.set(number, column, value);
                                System.out.println("Новое значение успешно задано");
                            } catch (SQLException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        case "get" -> {
                            System.out.println("Введите номер:");

                            try {
                                int number = Integer.parseInt(in.readLine());
                                String result = database.get(number);

                                if (result == null) {
                                    System.out.printf("Запись с номером %d не найдена\n", number);
                                } else {
                                    System.out.println(result);
                                }
                            } catch (SQLException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        case "remove" -> {
                            System.out.println("Введите номер:");

                            try {
                                int number = Integer.parseInt(in.readLine());
                                String result = database.get(number);

                                if (result == null) {
                                    System.out.printf("Запись с номером %d не найдена\n", number);
                                } else {
                                    database.remove(number);
                                    System.out.printf("Запись %s успешно удалена\n", result);
                                }
                            } catch (SQLException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        case "reset" -> {
                            do {
                                System.out.println("Сбросить таблицу? [Y/N]");
                                line = in.readLine();
                            } while (!line.equals("Y") && !line.equals("N"));

                            if (line.equals("Y")) {
                                database.resetDatabase();
                                System.out.println("База данных успешно сброшена");
                            }
                        }
                        case "cout" -> {
                            database.cout();
                        }
                        case "help" -> {
                            System.out.println(
                                    "login - Выбрать пользователя\n" +
                                            "add - Добавить запись\n" +
                                            "set - Изменить запись\n" +
                                            "get - Вывести запись\n" +
                                            "remove - Удалить запись\n" +
                                            "reset - Сбросить базу данных\n" +
                                            "cout - Вывести все записи\n" +
                                            "help - Вывести список команд\n" +
                                            "exit - Выход");
                        }
                        default -> {
                            System.out.println("Команда не распознана");
                        }
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("Введите команду:");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
