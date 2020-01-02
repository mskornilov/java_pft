package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {

    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException e) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private String[] readFromDataSource(String file) throws IOException {
    return new String(Files.readAllBytes(Paths.get("src/test/resources/data-source/" + file))).split(",");
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    try (Writer writer = new FileWriter(file)) {
      for (ContactData contact : contacts) {
        writer.write(String.format("%s;%s\n", contact.getFirstname(), contact.getLastname()));
      }
    }
  }

  private List<ContactData> generateContacts(int count) throws IOException {
    String[] firstNames = readFromDataSource("first-names.txt");
    String[] lastNames = readFromDataSource("last-names.txt");
    List<ContactData> contacts = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      int firstNameIndex = new Random().nextInt(firstNames.length);
      int lastNameIndex = new Random().nextInt(lastNames.length);
      contacts.add(
              new ContactData()
              .withFirstname(firstNames[firstNameIndex])
              .withLastname(lastNames[lastNameIndex])
              .withAddress(String.format("Baker street, %db", i + 1))
              .withFirstEmail(String.format("%s.%s@lndn.uk", firstNames[firstNameIndex].substring(0,2), lastNames[lastNameIndex]))
              .withHomePhone(String.format("+7 555 423 84 %s", i > 9 ? i : "0" + i))
              .withGroup("test1")
      );
    }
    return contacts;
  }
}
