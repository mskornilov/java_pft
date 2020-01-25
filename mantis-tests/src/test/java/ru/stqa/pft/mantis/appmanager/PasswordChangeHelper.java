package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class PasswordChangeHelper extends HelperBase {

    public PasswordChangeHelper(ApplicationManager app) {
        super(app);
    }

    public void resetPassByUserId(int id) {
        toggleMenu();
        openManageTab();
        openManageUsersPage();
        chooseUserById(id);
        resetPassword();
    }

    public void openManageTab() {
        click(By.cssSelector("a[href='/" + app.getProperty("mantis.folder") + "/manage_overview_page.php']"));
    }

    public void openManageUsersPage() {
        click(By.cssSelector("a[href='/" + app.getProperty("mantis.folder") + "/manage_user_page.php']"));
    }

    public void chooseUserById(int id) {
        click(By.cssSelector("a[href='manage_user_edit_page.php?user_id=" + id + "']"));
    }

    public void resetPassword() {
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void toggleMenu() {
        click(By.cssSelector("button[id='menu-toggler']"));
    }
}
