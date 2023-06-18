package ru.gb;

import ru.gb.Exceptions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Непосредственно класс для промежуточной аттестации по курсу
 * "Исключения в программировании и их обработка"
 */
public class IntermediateCertification {
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Person> persons = new ArrayList<>();

    public void run() {
        enterData();
        writeData();
    }

    private void enterData() {
        while (true) {
            String line = getLine();

            if (line == null)
                break;

            Person person = new Person();

            try {
                person.fromString(line);
                persons.add(person);
            } catch (ExtraDataException e) {
                System.out.println("Слишком много данных: " + e.getMessage());
            } catch (PrematureEndOfDataException e) {
                System.out.println("Слишком мало данных: " + e.getMessage());
            } catch (BirthDateFormatException e) {
                System.out.println("Некорректный формат даты рождения: " + e.getMessage());
            } catch (PhoneNumberFormatException e) {
                System.out.println("Некорректный формат телефонного номера: " + e.getMessage());
            } catch (GenderFormatException e) {
                System.out.println("Некорректный формат информации о гендере: " + e.getMessage());
            }
        }
    }

    private void writeData() {
        if (persons.isEmpty()) {
            System.out.println("Никаких данных для записи нет!");
            return;
        }

        for (Person person : persons) {
            String fileName = person.getLastName() + ".txt";
            try (FileWriter fw = new FileWriter(fileName, true)) {
                fw.write(person + "\n");
            } catch (IOException e) {
                System.err.println("Ошибка при записи файла " + fileName);
                System.err.println(e.getStackTrace());
            }
        }
    }

    private String getLine() {
        System.out.println("Введите строку в формате:");
        System.out.println("фамилия имя отчество дата_рождения номер_телефона пол");
        System.out.println("\tгде:");
        System.out.println("\t\tдата_рождения: dd.mm.yyy");
        System.out.println("\t\tпол: m (мужчина) или f (женщина)");
        System.out.println();
        System.out.println("Пустая строка для завершения ввода");
        System.out.print("> ");

        try {
            String result = scanner.nextLine();
            return (result.isEmpty()) ? null : result;
        } catch (Exception e) {
            System.out.println("Неизвестная ошибка ввода");
            return null;
        }
    }
}
