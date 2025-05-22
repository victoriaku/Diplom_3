package ui.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static ui.pageobjects.ConstructorPage.*;

public class AccountPage extends Page {

    public static final By EXIT_BUTTON = By.xpath(".//button[text()='Выход']");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать на кнопку \"Выход\"")
    public void clickExitButton(){
        driver.findElement(EXIT_BUTTON).click();

        waitForVisibilityOfElementLocated(LOGIN_BUTTON);
    }

    @Step("Проверить, отображается ли кнопка \"Выход\"")
    public boolean hasExitButton(){
        return !driver.findElements(EXIT_BUTTON).isEmpty();
    }
}
