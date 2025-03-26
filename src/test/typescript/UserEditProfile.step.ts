import { Given, When, Then } from "@cucumber/cucumber";
import { Page, Browser, chromium, expect } from "@playwright/test";

let browser: Browser;
let page: Page;
const baseUrl = "http://localhost:3000";

Given("the user logged in", async function () {
  browser = await chromium.launch({ headless: false, slowMo: 100 }); // ตั้งค่า slowMo เป็น 100ms
  const context = await browser.newContext();
  page = await context.newPage();

  await page.goto(baseUrl + "/login", { timeout: 10000 });

  await page.waitForSelector('input[id="email"]', { timeout: 10000 });
  await page.fill('input[id="email"]', "john.doe@example.com");

  await page.waitForSelector('input[id="password"]', { timeout: 10000 });
  await page.fill('input[id="password"]', "Password123!");

  await page.waitForSelector('button[type="submit"]', { timeout: 10000 });
  await page.click('button[type="submit"]');

  await page.waitForURL(baseUrl + "/app", { timeout: 10000 });
});

When("the user navigate to the profile edit page", async function () {
  await page.waitForSelector('[data-testid="user-avatar"]', { timeout: 10000 });
  await page.click('[data-testid="user-avatar"]');

  await page.waitForSelector('text=Edit Profile', { timeout: 10000 });
  await page.click('text=Edit Profile');

  await page.waitForURL(baseUrl + "/app/profile", { timeout: 10000 });
});

When("the user update my first name to {string}", async function (newFirstName: string) {
  await page.waitForSelector('[data-testid="EditIcon"]', { timeout: 10000 });
  await page.click('[data-testid="EditIcon"]');

  await page.waitForTimeout(1000);
  
  await page.waitForSelector('input[name="firstName"]', { timeout: 10000 });
  await page.fill('input[name="firstName"]', newFirstName);

  await page.waitForSelector('button:has-text("Save Change")', { timeout: 10000 });
  await page.click('button:has-text("Save Change")');

  await page.waitForTimeout(1000);
});

Then("the user should see my updated name as {string}", async function (expectedName: string) {
  await page.waitForSelector("h5.MuiTypography-h5", { timeout: 10000 });
  const displayedName = await page.textContent("h5.MuiTypography-h5");

  expect(displayedName).toBe(expectedName);

  await page.waitForTimeout(1000);

  await browser.close();
});
