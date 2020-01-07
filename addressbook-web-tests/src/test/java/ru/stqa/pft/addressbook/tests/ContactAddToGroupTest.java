package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactAddToGroupTest extends TestBase{

    private int contactId;

    @BeforeMethod
    public void ensurePreconditions(){
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        if (groups.size() == 0) {
            app.goTo().GroupPage();
            app.group().create(new GroupData().withName("test1"));
            app.goTo().homePage();
        }
        if (contacts.size() == 0) {
            app.contact().createContact();
            contactId = app.db().selectContactWithMaxId().getId();
        } else {
            for (ContactData contact : contacts) {
                if (!contact.getGroups().equals(groups)) {
                    contactId = contact.getId();
                    break;
                }
            }
            if (contactId == 0) {
                app.contact().createContact();
                contactId = app.db().selectContactWithMaxId().getId();
            }
        }
    }

    @Test
    public void testContactAdditionToGroup() {
        ContactData contact = app.db().selectContactById(contactId);
        Groups before = contact.getGroups();
        app.contact().selectContactById(contactId);
        Groups groups = app.db().groups();
        groups.removeAll(contact.getGroups());
        GroupData group = groups.iterator().next();
        app.contact().addToGroupById(group.getId());
        contact = app.db().selectContactById(contactId);
        Groups after = contact.getGroups();

        assertThat(after, equalTo(before.withAdded(group)));
    }
}
