package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

  @Test
  public void testNewContact() {
    Contacts before = app.contact().all();
    app.contact().createContact();
    Contacts after = app.contact().all();
    ContactData contact = app.contact().getContactData();
    assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/Holmes.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
