package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    ContactData contactData = new ContactData(
            "Sherlock",
            "Holmes",
            "Baker street, 221b",
            "+10 555 423 84 88",
            "Sherlock.Holmes@lndn.uk",
            "test1"
    );

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
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
        type(By.name("home"), contactData.getPhoneNumber());
        type(By.name("email"), contactData.getEmail());
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void acceptAlert() {
        wd.switchTo().alert().accept();
    }

    public void initContactModification(int index) {
        click(By.xpath(String.format("(//img[@alt='Edit'])[%s]", index)));
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

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String firstname = element.findElement(By.cssSelector(":nth-child(3)")).getText();
            String lastname = element.findElement(By.cssSelector(":nth-child(2)")).getText();
            int id = Integer.parseInt(element.findElement(By.cssSelector(":nth-child(1)>input")).getAttribute("value"));
            ContactData contact = new ContactData(id, firstname, lastname, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
