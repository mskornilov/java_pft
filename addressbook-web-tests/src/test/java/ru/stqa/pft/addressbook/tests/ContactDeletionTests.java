package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact();
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().acceptAlert();
        app.getNavigationHelper().gotoHomePage();
    }
}
