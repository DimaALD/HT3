package com.epam.ta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SettingsPage extends AbstractPage{
    public SettingsPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver,this);
    }
    @FindBy(xpath = "//input[@id ='user_profile_name']")
    private WebElement inputNameField;

    @FindBy(xpath = "//textarea[@id ='user_profile_bio']")
    private WebElement inputBioField;

    @FindBy(xpath = "//input[@id = 'user_profile_blog']")
    private WebElement inputURLField;

    @FindBy(xpath = "//input[@id = 'user_profile_company']")
    private WebElement inputCompanyField;

    @FindBy(xpath = "//input[@id = 'user_profile_location']")
    private WebElement inputLocationField;

    @FindBy(xpath = "//button[@class = 'btn btn-primary']")
    private WebElement clickButton;

    public void setName(String name)
    {
        inputNameField.clear();
        inputNameField.sendKeys(name);
    }

    public void setBio(String bio)
    {
        inputBioField.clear();
        inputBioField.sendKeys(bio);
    }

    public void setURL(String URL)
    {
        inputURLField.clear();
        inputURLField.sendKeys(URL);
    }

    public void setCompany(String company)
    {
        inputCompanyField.clear();
        inputCompanyField.sendKeys(company);
    }

    public void setLocation(String location)
    {
        inputLocationField.clear();
        inputLocationField.sendKeys(location);
    }

    public void clickUpdateProfile()
    {
        clickButton.click();
    }

    public String getName()
    {
        return inputNameField.getAttribute("value");
    }

    public String getURL()
    {
        return inputURLField.getAttribute("value");
    }

    public String getBio()
    {
        return inputBioField.getAttribute("value");
    }

    public String getCompany()
    {
        return inputCompanyField.getAttribute("value");
    }

    public String getLocation()
    {
        return inputLocationField.getAttribute("value");
    }

    @Override
    public void openPage() {
        driver.navigate().to("https://github.com/settings/profile");
    }
}
