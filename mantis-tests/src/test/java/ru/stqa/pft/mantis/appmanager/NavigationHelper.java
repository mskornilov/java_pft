package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void logout() {
        click(By.cssSelector("span[class='user-info']"));
        click(By.cssSelector("a[href='/" + app.getProperty("mantis.folder") + "/logout_page.php']"));
    }

    public void login(String login, String password) {
        type(By.name("username"), login);
        click(By.cssSelector("input[value='Login']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Login']"));
    }
}
