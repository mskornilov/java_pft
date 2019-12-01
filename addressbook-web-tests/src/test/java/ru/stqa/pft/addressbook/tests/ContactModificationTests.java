package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        ContactData contactData = new ContactData(
                "John",
                "Watson",
                "Baker street, 221b",
                "+10 555 423 84 89",
                "dr.watson@lndn.uk",
                null
        );
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact();
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillNewContactForm(contactData, false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
    }
}
