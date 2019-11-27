package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class NewContactTests extends TestBase {

  @Test
  public void testNewContact() {
    ContactData contactData = new ContactData(
            "Sherlock",
            "Holmes",
            "Baker street, 221b",
            "+10 555 423 84 88",
            "Sherlock.Holmes@lndn.uk"
    );
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillNewContactForm(contactData);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();
  }
}
