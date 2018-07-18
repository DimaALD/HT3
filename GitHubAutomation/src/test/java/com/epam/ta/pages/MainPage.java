package com.epam.ta.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage
{
	private final String BASE_URL = "https://github.com/";

	@FindBy(xpath = "//summary[contains(@aria-label, 'Create new')]")
	private WebElement buttonCreateNew;

	@FindBy(xpath = "//a[contains(text(), 'New repository')]")
	private WebElement linkNewRepository;

	@FindBy(xpath = "//a[@href='https://gist.github.com/']")
	private WebElement createNewGistRef;

	@FindBy(xpath = "//summary[@class = 'HeaderNavlink name mt-1'][@aria-label='View profile and more']")
	private WebElement settingsOfProfile;

	@FindBy(xpath = "//a[@href='/settings/profile']")
	private WebElement settingsReference;

	public MainPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}
	public void clickSettings()
	{
		settingsOfProfile.click();
		settingsReference.click();
	}
	public void cliclCreateNewGist()
	{
		buttonCreateNew.click();
		createNewGistRef.click();
	}

	public void clickOnCreateNewRepositoryButton()
	{
		buttonCreateNew.click();
		linkNewRepository.click();
	}

	@Override
	public void openPage()
	{
		driver.navigate().to(BASE_URL);
	}
}
