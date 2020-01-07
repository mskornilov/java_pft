package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroupTest extends TestBase{

    private int contactId;
    private int groupId;
    private boolean contactInGroupIsFound = false;

    @BeforeMethod
    public void ensurePreconditions(){
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        if (groups.size() == 0) {
            app.goTo().GroupPage();
            app.group().create(new GroupData().withName("test1"));
            app.goTo().homePage();
            groupId = app.db().selectGroupWithMaxId().getId();
            if (contacts.size() == 0) {
                app.contact().createContact();
                contactId = app.db().selectContactWithMaxId().getId();
                app.contact().selectContactById(contactId);
                app.contact().addToGroupById(groupId);
                contacts = app.db().contacts();
                contactInGroupIsFound = true;
            }
        }
        if (contacts.size() == 0 && !contactInGroupIsFound) {
            app.contact().createContact();
            contactId = app.db().selectContactWithMaxId().getId();
            groupId = app.db().selectGroupWithMaxId().getId();
            app.contact().selectContactById(contactId);
            app.contact().addToGroupById(groupId);
        } else {
            for (ContactData contact : contacts) {
                if (contact.getGroups().size() > 0) {
                    contactId = contact.getId();
                    groupId = contact.getGroups().iterator().next().getId();
                    break;
                }
            }
            if (groupId == 0) {
                contactId = app.db().selectContactWithMaxId().getId();
                groupId = app.db().selectGroupWithMaxId().getId();
                app.contact().selectContactById(contactId);
                app.contact().addToGroupById(groupId);
            }
        }
    }


    @Test
    public void testContactRemovalFromGroup() {
        Groups before = app.db().selectContactById(contactId).getGroups();
        app.contact().filterContactsByGroupId(groupId);
        app.contact().selectContactById(contactId);
        app.contact().removeContactFromGroup();
        Groups after = app.db().selectContactById(contactId).getGroups();
        GroupData group = app.db().selectGroupById(groupId);

        assertThat(after, equalTo(before.without(group)));
    }
}
