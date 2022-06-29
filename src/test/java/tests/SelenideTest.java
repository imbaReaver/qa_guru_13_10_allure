package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }
    @Test
    public void testGithubIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com");
        $(".header-search-input").click();
        $(".header-search-input").sendKeys("eroshenkoam/allure-example");
        $(".header-search-input").submit();
        $(By.linkText("eroshenkoam/allure-example")).click();
        $(By.partialLinkText("Issues")).click();
        $$(".js-issue-row").filterBy(Condition.text("69 nice")).first().should(Condition.exist);
    }
}
