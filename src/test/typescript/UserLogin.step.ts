import { Given, When, Then, Before, After } from "@cucumber/cucumber";
import { Page, Browser, chromium, expect } from "@playwright/test";
import {config} from 'dotenv';

config()
let browser: Browser;
let page: Page;
const baseUrl = process.env.WEB_ENDPOINT;
const timeout = 60000;

Before({ tags: "@UserLogin" }, async function () {
  browser = await chromium.launch();
  const context = await browser.newContext();
  page = await context.newPage();
});

After({ tags: "@UserLogin" }, async function () {
  await browser.close();
});

Given("I visit the login page", {timeout: 10000}, async function () {
  await page.goto(baseUrl + "/login", {waitUntil: "networkidle"});
});

When(
  "I input username {string} and password {string}",
  async function (email: string, password: string) {
    await page.waitForSelector('input[id="email"]', { timeout: timeout });
    await page.fill('input[id="email"]', email);

    await page.waitForSelector('input[id="password"]', { timeout: timeout });
    await page.fill('input[id="password"]', password);
  },
);

When("I click on the login button", async function () {
  await page.waitForSelector('button[type="submit"]', { timeout: timeout });
  await page.click('button[type="submit"]');
});

Then("I should navigate to feed", { timeout: 10000 }, async function () {
    await page.waitForURL(baseUrl + "/", { timeout: 5000 });
    await expect(page).toHaveURL(baseUrl + "/");
});



Then(
  "the login should fail with {string}",
  async function (errorMessage: string) {
    const errorMessageElement = await page.waitForSelector(
      "#password-helper-text",
      { timeout: timeout },
    );
    const displayedErrorMessage = await errorMessageElement.textContent();
    expect(displayedErrorMessage).toContain(errorMessage);
  },
);
