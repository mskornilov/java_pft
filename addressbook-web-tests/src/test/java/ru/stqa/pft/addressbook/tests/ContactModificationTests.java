package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        if (app.db().contacts().size() == 0) {
            app.contact().createContact();
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contactData = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstname("John")
                .withLastname("Watson")
                .withAddress("Baker street, 221b")
                .withHomePhone("+7 555 423 84 89")
                .withFirstEmail("dr.watson@lndn.uk");
        app.contact().modify(contactData);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contactData)));
    }
}
