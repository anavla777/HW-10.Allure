import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.StepModelRepository;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

@DisplayName("Github tests")
public class RepositoryTest extends TestBase {
    private static final String repositoryName = "eroshenkoam/allure-example";
    private static final int issueNumber = 89;
    private static final String issueTitle = "Another test issue";

    @Test
    @DisplayName("Search issue via Selenide")
    public void issueSearchSelenideTest()   {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");
        $(".search-input").click();
        $("#query-builder-test").sendKeys(repositoryName);
        $("#query-builder-test").submit();

        $(linkText(repositoryName)).click();
        $("#issues-tab").click();
        $("#issue_"+ issueNumber+"_link")
                .should(Condition.exist)
                .shouldHave(text(issueTitle));
        takeScreenshot();
    }

    @Test
    @DisplayName("Search issue via Lambda step")
    public void issueSearchStepLambdaTest()   {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open main page", () -> {
            open("https://github.com");
        });
        step("Searching for repository: " + repositoryName, () -> {
                    $(".search-input").click();
                    $("#query-builder-test").sendKeys(repositoryName);
                    $("#query-builder-test").submit();
                });
        step("Click on link to repository " + repositoryName, () ->{
            $(linkText(repositoryName)).click();
        });
        step("Open Issues tab ", () ->{
            $("#issues-tab").click();
        });
        step("Find issue with number = " + issueNumber, () ->{
            $("#issue_"+ issueNumber+"_link")
                    .should(Condition.exist)
                    .shouldHave(text(issueTitle));
            takeScreenshot();
        });
    }

    @Test
    @DisplayName("Search issue via @Step Annotation")
    public void issueSearchViaStepAnnotationTest()   {
        StepModelRepository steps = new StepModelRepository();
        SelenideLogger.addListener("allure", new AllureSelenide());

        steps.openPage();
        steps.findRepository(repositoryName);
        steps.selectRepository(repositoryName);
        steps.clickIssueTab();
        steps.checkIssueTitle(issueNumber, issueTitle);
        steps.takeScreenshot();
    }
}
