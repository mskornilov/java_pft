package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {

    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstname")
    private String firstname;
    @Expose
    @Column(name = "lastname")
    private String lastname;
    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;
    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;
    @Transient
    private String allPhones;
    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String firstEmail;
    @Column(name = "email2")
    @Type(type = "text")
    private String secondEmail;
    @Column(name = "email3")
    @Type(type = "text")
    private String thirdEmail;
    @Transient
    private String allEmails;
    @Expose
    @Transient
    private String group;
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getAllPhones() {
        return allPhones;
    }


    public String getFirstEmail() {
        return firstEmail;
    }

    public String getSecondEmail() {
        return secondEmail;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getThirdEmail() {
        return thirdEmail;
    }

    public String getGroup() {
        return group;
    }

    public File getPhoto() {
        return new File(photo);
    }

    public int getId() {
        return id;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomePhone(String phoneNumber) {
        this.homePhone = phoneNumber;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }


    public ContactData withFirstEmail(String email) {
        this.firstEmail = email;
        return this;
    }

    public ContactData withSecondEmail(String secondEmail) {
        this.secondEmail = secondEmail;
        return this;
    }

    public ContactData withThirdEmail(String thirdEmail) {
        this.thirdEmail = thirdEmail;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }


    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

}
