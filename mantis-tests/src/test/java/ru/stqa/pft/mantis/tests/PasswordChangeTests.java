package ru.stqa.pft.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.User;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PasswordChangeTests extends TestBase {

    private User user;
    private final String mailPassword = "password";
    private final String newPassword = String.valueOf(System.currentTimeMillis());

    @BeforeMethod
    public void findUser() throws MessagingException {
        user  = app.db().selectUserWithMaxId();
        if (!app.james().doesUserExist(user.getUsername())) {
            app.james().createUser(user.getUsername(), mailPassword);
        } else {
            app.james().drainEmail(user.getUsername(), mailPassword);
        }

    }

    @Test
    public void passwordChangeTest() throws MessagingException, IOException {
        app.goTo().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.passwordChange().resetPassByUserId(user.getId());
        app.goTo().logout();
        List<MailMessage> mailMessages = app.james().waitForMail(user.getUsername(), mailPassword, 60000);
        String resetPassLink = findResetPassLink(mailMessages);
        app.registration().finish(resetPassLink, newPassword);
        assertTrue(app.newSession().login(user.getUsername(), newPassword));
    }


    private String findResetPassLink(List<MailMessage> mailMessages) {
        String resetPassLink;
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        for (MailMessage message : mailMessages) {
            resetPassLink = regex.getText(message.text);
            if (resetPassLink != null) {
                return resetPassLink;
            }
        }
        throw new Error("No link for password reset is found.");
    }

}
