package com.epam.ta.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Collection;
import java.util.Iterator;

public class EditPage extends AbstractPage {
    public  EditPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver,this);
    }
    @FindBy(xpath = "//input[@name = 'gist[contents][][name]'][@aria-label = 'Filename including extension…']")
    private WebElement inputGistNameField;

    @FindBy(xpath = "//div[@class= 'CodeMirror-code']")
    private WebElement text;

    @FindBy(xpath = "//button[@class = 'btn btn-primary']")
    private WebElement updateButton;

    public void editGist(String name)
    {
        inputGistNameField.clear();
        inputGistNameField.sendKeys(name);
        updateButton.click();
    }

    public boolean checkEditGist(String name )//Проверяем наличие gist в форме
    {

        Collection<WebElement> list = driver.findElements(By.xpath("//form[@class = 'js-comment-update']"));
        WebElement element = null;
        Iterator<WebElement> i = list.iterator();
        if (i.hasNext())
        {
            element = i.next();
            if (element.findElement(By.tagName("strong")).getText().equals(name))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void openPage() {
        driver.navigate().to("https://gist.github.com/testautomationuser/010baa8bc263f3ad0fb514975fd00b2a/edit");
    }
}
