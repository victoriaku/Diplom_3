package ui;

import io.qameta.allure.junit4.DisplayName;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class ConstructorSectionTest extends BaseTest{

    SoftAssertions softly = new SoftAssertions();

    @Test
    @DisplayName("Переход к разделу \"Булки\"")
    public void clickBunsLinkShouldSwitchToBunsSection(){
        constructorPage.clickFillingsSectionLink();
        constructorPage.clickBunsSectionLink();

        softly.assertThat(constructorPage.isBunsSectionSelected())
                .as("Раздел \"Булки\" должен быть выбран")
                .isTrue();

        softly.assertThat(constructorPage.isBunsTitleInViewport())
                .as("Заголовок \"Булки\" должен быть в зоне видимости")
                .isTrue();

        softly.assertAll();
    }

    @Test
    @DisplayName("Переход к разделу \"Соусы\"")
    public void clickSaucesLinkShouldSwitchToSaucesSection(){
        constructorPage.clickSaucesSectionLink();

        softly.assertThat(constructorPage.isSaucesSectionSelected())
                .as("Раздел \"Соусы\" должен быть выбран")
                .isTrue();

        softly.assertThat(constructorPage.isSaucesTitleInViewport())
                .as("Заголовок \"Соусы\" должен быть в зоне видимости")
                .isTrue();

        softly.assertAll();
    }

    @Test
    @DisplayName("Переход к разделу \"Начинки\"")
    public void clickFillingsLinkShouldSwitchToFillingsSection(){
        constructorPage.clickFillingsSectionLink();

        softly.assertThat(constructorPage.isFillingsSectionSelected())
                .as("Раздел \"Начинки\" должен быть выбран")
                .isTrue();

        softly.assertThat(constructorPage.isFillingsTitleInViewport())
                .as("Заголовок \"Начинки\" должен быть в зоне видимости")
                .isTrue();

        softly.assertAll();
    }
}