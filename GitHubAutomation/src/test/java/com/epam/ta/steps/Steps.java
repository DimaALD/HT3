package com.epam.ta.steps;

import com.epam.ta.driver.DriverSingleton;
import com.epam.ta.pages.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Steps
{
	private WebDriver driver;

	private final Logger logger = LogManager.getRootLogger();
	//Переменные для заполнения полей на форме UpdateProfile
	private final String NAME = "GoHome";
	private final String BIO = "Test";
	private final String URL = "test555";
	private final String LOCATION = "Minsk";
	private final String COMPANY = "Epam";
	//Переменные для создания gist
	private final String GISTNAME = "NewGist";
	private final String GISTTEST = "test";

	public void initBrowser()
	{
		driver = DriverSingleton.getDriver();
	}

	public void closeDriver()
	{
		DriverSingleton.closeDriver();
	}

	public void loginGithub(String username, String password)
	{
		LoginPage loginPage = new LoginPage(driver);
		loginPage.openPage();
		loginPage.login(username, password);
	}

	public void createNewGist()
	{
		MainPage mainPage = new MainPage(driver);
		mainPage.cliclCreateNewGist();
		CreateNewGistPage d = new CreateNewGistPage(driver);
		d.inputGistName(GISTNAME, GISTTEST);
	}

	public boolean isLoggedIn(String username)
	{
		LoginPage loginPage = new LoginPage(driver);
		String actualUsername = loginPage.getLoggedInUserName().trim().toLowerCase();
		logger.info("Actual username: " + actualUsername);
		return actualUsername.equals(username);
	}

	public boolean createNewRepository(String repositoryName, String repositoryDescription)
	{
		MainPage mainPage = new MainPage(driver);
		mainPage.clickOnCreateNewRepositoryButton();
		CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
		String expectedRepoName = createNewRepositoryPage.createNewRepository(repositoryName, repositoryDescription);
		return expectedRepoName.equals(createNewRepositoryPage.getCurrentRepositoryName());
	}

	public boolean currentRepositoryIsEmpty()
	{
		CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
		return createNewRepositoryPage.isCurrentRepositoryEmpty();
	}

	public void inputFieldsToUpadateProfile()
	{
		MainPage mainPage = new MainPage(driver);
		mainPage.clickSettings();
		SettingsPage settingsPage = new SettingsPage(driver);
		settingsPage.setName(NAME);
		settingsPage.setBio(BIO);
		settingsPage.setURL(URL);
		settingsPage.setCompany(COMPANY);
		settingsPage.setLocation(LOCATION);
		settingsPage.clickUpdateProfile();
	}
	public boolean checkCreatedGist()
	{
		CreateNewGistPage gistPage = new CreateNewGistPage(driver);
		gistPage.openPage();
		gistPage.setSeeAllGistsReference();
		YourGistsPage yourGistsPage = new YourGistsPage(driver);
		return yourGistsPage.checkGist(GISTNAME);
	}
	public boolean checkProfileAfterUpdate()
	{
		SettingsPage settingsPage = new SettingsPage(driver);
		if (settingsPage.getName().equals(NAME)&&settingsPage.getURL().equals(URL)
				&&settingsPage.getBio().equals(BIO)&&settingsPage.getCompany().equals(COMPANY)
				&&settingsPage.getLocation().equals(LOCATION))
		{
			return true;
		}
		else return false;
	}
	public void delteGist()//Continue
	{
		CreateNewGistPage createNewGistPage = new CreateNewGistPage(driver);
		createNewGistPage.openPage();
		createNewGistPage.setSeeAllGistsReference();
		YourGistsPage yourGistsPage = new YourGistsPage(driver);
		yourGistsPage.checkGist(GISTNAME);
		WebElement element = driver.findElement(By.xpath("//strong[text() ='"+ GISTNAME +"']"));
		element.click();
		WebElement element1 = driver.findElement(By.xpath("//button[@class ='btn btn-sm btn-danger'][@aria-label='Delete this Gist']"));
		element1.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	public boolean isDeleted()
	{
		YourGistsPage gistsPage = new YourGistsPage(driver);
		try {
			driver.findElement(By.xpath("//strong[text() ='"+ GISTNAME +"']"));
			return true;
		}catch (Exception exc)
		{
			return false;
		}
	}

	public boolean isGistEditted()
	{
		CreateNewGistPage createNewGistPage = new CreateNewGistPage(driver);
		createNewGistPage.openPage();
		createNewGistPage.setSeeAllGistsReference();
		YourGistsPage yourGistsPage = new YourGistsPage(driver);
		yourGistsPage.checkGist(GISTNAME);
		try {
			WebElement element = driver.findElement(By.xpath("//strong[text() ='"+ GISTNAME +"']"));
			element.click();
		}catch (Exception exc)
		{
			logger.info("Gist с именем "+GISTNAME+ " не найден");
		}
		yourGistsPage.clickEditButton();
		EditPage editPage = new EditPage(driver);
		editPage.editGist("NewGIST");
		return editPage.checkEditGist("NewGIST");
	}




}
