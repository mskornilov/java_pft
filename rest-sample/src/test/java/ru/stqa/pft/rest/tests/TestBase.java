package ru.stqa.pft.rest.tests;

import org.testng.SkipException;
import ru.stqa.pft.rest.appmanager.AppManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestBase {

    protected static final AppManager app = new AppManager();

    private final List<String> openStatuses = new ArrayList<>(Arrays.asList("Open", "In Progress", "Re-opened"));
    private final List<String> closedStatuses = new ArrayList<>(Arrays.asList("Resolved", "Closed"));

    public boolean isIssueOpen(int issueId) throws IOException {
        String status = app.rest().getIssueById(issueId).getStatus();
        return openStatuses.contains(status);
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}
