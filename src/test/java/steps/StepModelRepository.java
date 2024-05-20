package steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class StepModelRepository {
    @Step("Open main page")
    public void openPage(){
        open("https://github.com/");
    }

    @Step("Find repository")
    public void findRepository(String name){
        $(".search-input-container").click();
        $("#query-builder-test").setValue(name).pressEnter();
    }

    @Step("Select repository")
    public void selectRepository(String repository){
        $(linkText(repository)).click();
    }

    @Step("Click on Issue tab")
    public void clickIssueTab(){
        $("#issues-tab").click();
    }

    @Step("Check that issue with #{0} exists")
    public void checkIssueTitle(int issueID, String title){
        $("#issue_"+ issueID+"_link")
                .should(Condition.exist)
                .shouldHave(text(title));
    }
}
