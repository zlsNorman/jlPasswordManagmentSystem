package de.jlingott.jlpasswordmanagmentsystem.tester;

import de.jlingott.jlpasswordmanagmentsystem.helper.PasswordHelper;
import de.jlingott.jlpasswordmanagmentsystem.logic.database.DbManager;

public class TestStuff {

    public static void main(String[] args) {

        System.out.println("TEST SOME STUFF");
        PasswordHelper passwordHelper = new PasswordHelper("test");
        System.out.println(passwordHelper.getEncryptPassword());

        Boolean isVerified = DbManager.getInstance().handleUserVerification("test", "test");
        System.out.println(isVerified);
    }
}
