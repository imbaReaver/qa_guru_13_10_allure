package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class LambdaStepsTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }
    private final String REPOSITORY = "eroshenkoam/allure-example";
    private final String ISSUE_LABEL = "69 nice";
    @Test
    public void testLambdaSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий по имени " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("В результатах поиска переходим по ссылке репозитория" + REPOSITORY, () -> {
            $(By.linkText("eroshenkoam/allure-example")).click();
        });
        step("Открываешь таб Issues", () -> {
            $(By.partialLinkText("Issues")).click();
        });
        step("Проверяем что существует Issue с названием " + ISSUE_LABEL, () -> {
            $$(".js-issue-row").filterBy(Condition.text(ISSUE_LABEL)).first().should(Condition.exist);
        });
    }
}
