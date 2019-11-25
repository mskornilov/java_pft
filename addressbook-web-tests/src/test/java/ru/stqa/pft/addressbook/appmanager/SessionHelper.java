package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    protected void login(String username, String password) {
        type("user", username);
        type("pass", password);
        click("//input[@value='Login']");
    }
}
