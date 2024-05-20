import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class TestBase {
        @BeforeAll
        static void beforeAll() {
            Configuration.pageLoadStrategy = "eager";
            Configuration.browserSize="3840x2160";
        }

        @AfterEach
        void afterEach() {
            Selenide.closeWebDriver();
        }

        @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
        public byte[] takeScreenshot() {
            return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
        }
}
