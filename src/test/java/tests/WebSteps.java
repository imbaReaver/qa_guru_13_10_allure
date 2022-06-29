package tests;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class WebSteps {
    @Step ("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com");
    }
    @Step ("Ищем репозиторий по имени {repository}")
    public void searchForRepository (String repository) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repository);
        $(".header-search-input").submit();
    }
    @Step ("В результатах поиска переходим по ссылке репозитория {repository}")
    public void openRepositoryLink (String repository) {
        $(By.linkText(repository)).click();
    }
    @Step ("Открываешь таб Issues")
    public void openIssueTab() {
        $(By.partialLinkText("Issues")).click();
    }
    @Step ("Проверяем что существует Issue с названием {issueLabel}")
    public void shouldSeeIssueWithNumber(String issueLabel) {
        $$(".js-issue-row").filterBy(Condition.text(issueLabel)).first().should(Condition.exist);
    }
}
