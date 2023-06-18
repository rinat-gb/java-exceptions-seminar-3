package ru.gb;

import ru.gb.Exceptions.*;

public class Person {
    // порядок слов во входной строке
    public static final int LAST_NAME = 0;
    public static final int FIRST_NAME = 1;
    public static final int MIDDLE_NAME = 2;
    public static final int BIRTH_DATE = 3;
    public static final int PHONE_NUMBER = 4;
    public static final int GENDER = 5;
    public static final int EXACTLY_WORDS_COUNT = 6;

    // данные конкретного человека
    private String firstName;
    private String middleName;
    private String lastName;
    private String birthDate;
    private int phoneNumber;
    private String gender;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void fromString(String str) throws
            PrematureEndOfDataException,
            ExtraDataException,
            BirthDateFormatException,
            PhoneNumberFormatException,
            GenderFormatException {
        String[] words = str.split(" ");

        if (words.length < EXACTLY_WORDS_COUNT)
            throw new PrematureEndOfDataException(str);
        if (words.length > EXACTLY_WORDS_COUNT)
            throw new ExtraDataException(str);

        firstName = words[FIRST_NAME];
        middleName = words[MIDDLE_NAME];
        lastName = words[LAST_NAME];

        if (!words[BIRTH_DATE].matches("[0-9]{1,2}\\.[0-9]{1,2}\\.[0-9]{4}"))
            throw new BirthDateFormatException(words[BIRTH_DATE]);

        birthDate = words[BIRTH_DATE];

        try {
            phoneNumber = Integer.parseUnsignedInt(words[PHONE_NUMBER]);
        } catch (NumberFormatException e) {
            throw new PhoneNumberFormatException(words[PHONE_NUMBER]);
        }

        if (words[GENDER].length() != 1 ||
                (words[GENDER].charAt(0) != 'f' && words[GENDER].charAt(0) != 'F' &&
                words[GENDER].charAt(0) != 'm' && words[GENDER].charAt(0) != 'M'))
            throw new GenderFormatException(words[GENDER]);

        gender = words[GENDER];
    }

    @Override
    public String toString() {
        return firstName + " " +
                middleName + " " +
                lastName + " " +
                birthDate + " " +
                phoneNumber + " " +
                gender;
    }
}
