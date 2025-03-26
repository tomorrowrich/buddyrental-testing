import { Given, When, Then } from "@cucumber/cucumber";
import { Page, Browser, chromium, expect } from "@playwright/test";

let browser: Browser;
let page: Page;
const baseUrl = "http://localhost:3000";
const timeout = 30000;

Given("I am logged in", async function () {
  browser = await chromium.launch({ headless: false, slowMo: 200 }); // ตั้งค่า slowMo เป็น 100ms
  const context = await browser.newContext();
  page = await context.newPage();

  await page.goto(baseUrl + "/login", { timeout: timeout });

  await page.waitForSelector('input[id="email"]', { timeout: timeout });
  await page.fill('input[id="email"]', "john.doe@example.com");

  await page.waitForSelector('input[id="password"]', { timeout: timeout });
  await page.fill('input[id="password"]', "Password123!");

  await page.waitForSelector('button[type="submit"]', { timeout: timeout });
  await page.click('button[type="submit"]');

  await page.waitForURL(baseUrl + "/app", { timeout: timeout });
});

When("I navigate to the profile edit page", async function () {
  await page.waitForSelector('[data-testid="user-avatar"]', { timeout: timeout });
  await page.click('[data-testid="user-avatar"]');

  await page.waitForSelector('text=Edit Profile', { timeout: timeout });
  await page.click('text=Edit Profile');

  await page.waitForURL(baseUrl + "/app/profile", { timeout: timeout });
});

When("I update my first name to {string}", async function (newFirstName: string) {
  await page.waitForSelector('[data-testid="EditIcon"]', { timeout: timeout });
  await page.click('[data-testid="EditIcon"]');

  await page.waitForSelector('input[name="firstName"]', { timeout: timeout });
  await page.fill('input[name="firstName"]', newFirstName);
});

When("I update my last name to {string}", async function (newLastName: string) {
  await page.waitForSelector('input[name="lastName"]', { timeout: timeout });
  await page.fill('input[name="lastName"]', newLastName);
});

When("I update my nickname to {string}", async function (newNickname: string) {
  await page.waitForSelector('input[name="displayName"]', { timeout: timeout });
  await page.fill('input[name="displayName"]', newNickname);
});

When("I update my phone number to {string}", async function (newPhoneNumber: string) {
  await page.waitForSelector('input[name="phoneNumber"]', { timeout: timeout });
  await page.fill('input[name="phoneNumber"]', newPhoneNumber);
});

When("I update my address to {string}", async function (newAddress: string) {
  await page.waitForSelector('input[name="address"]', { timeout: timeout });
  await page.fill('input[name="address"]', newAddress);
});

When("I update my city to {string}", async function (newCity: string) {
  await page.waitForSelector('input[name="city"]', { timeout: timeout });
  await page.fill('input[name="city"]', newCity);
});

When("I update my zip code to {string}", async function (newZipCode: string) {
  await page.waitForSelector('input[name="postalCode"]', { timeout: timeout });
  await page.fill('input[name="postalCode"]', newZipCode);

  await page.waitForSelector('button:has-text("Save Change")', { timeout: timeout });
  await page.click('button:has-text("Save Change")');
});

Then("I should see my updated name as {string}", async function (expectedName: string) {
  await page.waitForSelector("h5.MuiTypography-h5", { timeout: timeout });
  const displayedName = await page.textContent("h5.MuiTypography-h5");

  expect(displayedName).toBe(expectedName);

  await page.waitForTimeout(1000);

  await browser.close();
});
