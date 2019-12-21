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
            .withHomePhone("+7 555 423 84 88")
            .withFirstEmail("Sherlock.Holmes@lndn.uk")
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
        type(By.name("email"), contactData.getFirstEmail());
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

    private String getValueOfElement(By locator) {
        return wd.findElement(locator).getAttribute("value");
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
            String address = element.findElement(By.cssSelector(":nth-child(4)")).getText();
            String allEmails = element.findElement(By.cssSelector(":nth-child(5)")).getText();
            String allPhones = element.findElement(By.cssSelector(":nth-child(6)")).getText();
            contacts.add(
                    new ContactData()
                            .withId(id)
                            .withFirstname(firstname)
                            .withLastname(lastname)
                            .withAddress(address)
                            .withAllPhones(allPhones)
                            .withAllEmails(allEmails)
            );
        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModification(contact.getId());
        String firstname = getValueOfElement(By.name("firstname"));
        String lastname = getValueOfElement(By.name("lastname"));
        String home = getValueOfElement(By.name("home"));
        String mobile = getValueOfElement(By.name("mobile"));
        String work = getValueOfElement(By.name("work"));
        String address = getAddress(By.name("address"));
        String firstEmail = getValueOfElement(By.name("email"));
        String secondEmail = getValueOfElement(By.name("email2"));
        String thirdEmail = getValueOfElement(By.name("email3"));
        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId())
                .withFirstname(firstname)
                .withLastname(lastname)
                .withAddress(address)
                .withHomePhone(home)
                .withMobilePhone(mobile)
                .withWorkPhone(work)
                .withFirstEmail(firstEmail)
                .withSecondEmail(secondEmail)
                .withThirdEmail(thirdEmail);
    }

    private String getAddress(By locator) {
        String address = wd.findElement(locator).getAttribute("value");
        address = address.replaceAll("\\s+(?=\n)|\n$", "");
        return address;
    }
}
