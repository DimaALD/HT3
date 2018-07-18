package com.epam.ta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewGistPage extends AbstractPage {

    public CreateNewGistPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver,this);
    }
    private final  String URL  = "https://gist.github.com/";

    @FindBy(xpath = "//ul[@id ='user-link'/li/a[tetxt()='New gist']")
    private WebElement newGistReference;

    @FindBy(xpath = "//input[@name = 'gist[contents][][name]'][@aria-label = 'Filename including extension…']")
    private WebElement inputGistNameField;

    @FindBy(xpath = "//div[@class= 'CodeMirror-code']")
    private WebElement text;

    @FindBy(xpath = "//button[@class= 'btn js-gist-create']")
    private WebElement createGistButton;

    @FindBy(xpath = "//a[text()='See all of your gists']")
    private WebElement seeAllGistsReference;


    public void inputGistName(String gistName,String text)
    {
        inputGistNameField.sendKeys(gistName);
        this.text.sendKeys(text);
        createGistButton.click();
    }

    public void setSeeAllGistsReference()
    {
        seeAllGistsReference.click();
    }

    public void openPage() {
        driver.navigate().to(URL);
    }
}
