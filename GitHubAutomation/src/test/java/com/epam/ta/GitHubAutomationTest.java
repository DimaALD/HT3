package com.epam.ta;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.ta.steps.Steps;

public class GitHubAutomationTest
{
	private Steps steps;
	private final String USERNAME = "testautomationuser";
	private final String PASSWORD = "Time4Death!";

	@BeforeMethod(description = "Init browser")
	public void setUp()
	{
		steps = new Steps();
		steps.initBrowser();
	}

	@Test
	public void oneCanCreateProject()
	{
		steps.loginGithub(USERNAME, PASSWORD);
		Assert.assertTrue(steps.createNewRepository("testRepo", "auto-generated test repo"));
		Assert.assertTrue(steps.currentRepositoryIsEmpty());
		// do not use lots of asserts
	}

	@Test(description = "Login to Github")
	public void oneCanLoginGithub()
	{
		steps.loginGithub(USERNAME, PASSWORD);
		Assert.assertTrue(steps.isLoggedIn(USERNAME));
	}

	//Проверка на создание gist.Проверяем , что gist появляется в окне AllGists
	// на странице https://gist.github.com/testautomationuser
	@Test(description = "CreateGist")
	public void oneCreateGist()
	{
		steps.loginGithub(USERNAME, PASSWORD);
		steps.createNewGist();
		Assert.assertTrue(steps.checkCreatedGist());
	}
	//После нажатия delete gist пропадает со страницы
	@Test(description = "DeleteGist")
	public void oneDeleteGist()
	{
		steps.loginGithub(USERNAME,PASSWORD);
		steps.createNewGist();
		steps.delteGist();
		Assert.assertFalse(steps.isDeleted());
	}
	//Проверка gist после изменения поля FileName including extension
	@Test(description = "EditGist")
	public void oneEditGist()
	{
		steps.loginGithub(USERNAME,PASSWORD);
		steps.createNewGist();
		Assert.assertTrue(steps.isGistEditted());
	}
	//Проверка данных после выполнения UpdateProfile
	//на странице https://github.com/settings/profile
	@Test(description = "CheckValuesAfterUpdateProfile")
	public void oneUpdateProfile()
	{
		steps.loginGithub(USERNAME,PASSWORD);
		steps.inputFieldsToUpadateProfile();
		Assert.assertTrue(steps.checkProfileAfterUpdate());
	}






}
