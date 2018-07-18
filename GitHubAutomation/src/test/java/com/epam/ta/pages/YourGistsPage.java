package com.epam.ta.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collection;
import java.util.Iterator;

public class YourGistsPage  extends AbstractPage {
    public YourGistsPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//a[@aria-label ='Edit this Gist']")
    private WebElement editButton;
    public boolean checkGist(String gistName)
    {
        Collection<WebElement> list = driver.findElements(By.xpath("//div[@class = 'repository-content gist-content']"));
        WebElement element = null;
        Iterator<WebElement> i = list.iterator();
        if (i.hasNext())
        {
            element = i.next();
            if (element.findElement(By.tagName("strong")).getText().equals(gistName))
            {
                return true;
            }
        }
        return false;
    }
    public void clickEditButton()
    {
        editButton.click();
    }

    @Override
    public void openPage() {
        driver.navigate().to("https://gist.github.com/testautomationuser");
    }
}
