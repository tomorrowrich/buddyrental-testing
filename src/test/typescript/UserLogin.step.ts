import { Given, When, Then, Before, After } from "@cucumber/cucumber";
import { Page, Browser, chromium, expect } from "@playwright/test";

let browser: Browser;
let page: Page;
const baseUrl = "http://localhost:3000";
const timeout = 30000;

Before(async function () {
  // เปิด browser ทีละตัวก่อนเริ่มการทดสอบ
  browser = await chromium.launch({ headless: false, slowMo: 200 });
  const context = await browser.newContext();
  page = await context.newPage();
});

After(async function () {
  // ปิด browser หลังการทดสอบเสร็จสิ้น
  await browser.close();
});

Given("I visit the login page", async function () {
  // ไปที่หน้า Login
  await page.goto(baseUrl + "/login", { timeout: timeout });
});

When("I input username {string} and password {string}", async function (email: string, password: string) {
  await page.waitForSelector('input[id="email"]', { timeout: timeout });
  await page.fill('input[id="email"]', email);

  await page.waitForSelector('input[id="password"]', { timeout: timeout });
  await page.fill('input[id="password"]', password);
});

When("I click on the login button", async function () {
  await page.waitForSelector('button[type="submit"]', { timeout: timeout });
  await page.click('button[type="submit"]');
});

Then("I should navigate to {string}", async function (path: string) {
  await expect(page).toHaveURL(baseUrl+path, { ignoreCase: true });
  await page.waitForTimeout(500);
});

Then("the login should fail with {string}", async function (errorMessage: string) {
  const errorMessageElement = await page.waitForSelector('p.MuiFormHelperText-root.Mui-error', { timeout: timeout });
  const displayedErrorMessage = await errorMessageElement.textContent();

  await page.waitForTimeout(500);
  expect(displayedErrorMessage).toContain(errorMessage);
});

