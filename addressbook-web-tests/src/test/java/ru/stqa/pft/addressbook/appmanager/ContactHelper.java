package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

    ContactData contactData = new ContactData()
            .withFirstname("Sherlock")
            .withLastname("Holmes")
            .withAddress("Baker street, 221b")
            .withHomePhone("+10 555 423 84 88")
            .withEmail("Sherlock.Holmes@lndn.uk")
            .withGroup("test1");

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void gotoHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void fillNewContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("email"), contactData.getEmail());
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void acceptAlert() {
        wd.switchTo().alert().accept();
    }

    public void initContactModification(int id) {
        click(By.cssSelector("a[href='edit.php?id=" + id + "']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public ContactData getContactData() {
        return contactData;
    }

    public void createContact() {
        initContactCreation();
        fillNewContactForm(contactData, true);
        submitContactCreation();
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        acceptAlert();
        gotoHomePage();
    }

    public void modify(ContactData contact) {
        initContactModification(contact.getId());
        fillNewContactForm(contact, false);
        submitContactModification();
        returnToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.cssSelector(":nth-child(1)>input")).getAttribute("value"));
            String lastname = element.findElement(By.cssSelector(":nth-child(2)")).getText();
            String firstname = element.findElement(By.cssSelector(":nth-child(3)")).getText();
            String allPhones = element.findElement(By.cssSelector(":nth-child(6)")).getText();
            contacts.add(
                    new ContactData()
                            .withId(id)
                            .withFirstname(firstname)
                            .withLastname(lastname)
                            .withAllPhones(allPhones)
            );
        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModification(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId())
                .withFirstname(firstname)
                .withLastname(lastname)
                .withHomePhone(home)
                .withMobilePhone(mobile)
                .withWorkPhone(work);
    }
}
