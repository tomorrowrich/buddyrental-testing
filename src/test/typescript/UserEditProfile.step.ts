import {Given, When, Then, After, Before} from "@cucumber/cucumber";
import {Page, Browser, chromium, expect} from "@playwright/test";
import {config} from 'dotenv';

config()
let browser: Browser;
let page: Page;
const baseUrl = process.env.WEB_ENDPOINT;
console.log(baseUrl);
const timeout = 60000;

Before({tags: "@edit_profile"}, async function () {
    browser = await chromium.launch({headless: false});
    const context = await browser.newContext();
    page = await context.newPage();
});

After({tags: "@edit_profile"}, async function () {
    await browser.close();
});

//Scenario: Update personal information successfully
Given("I am logged in", { timeout: 30000 }, async function () {
    try {
        // Navigate to login page with more lenient wait strategy
        await page.goto(baseUrl + "/login", {
            timeout: 10000
        });

        // Wait for and fill email field
        await page.waitForSelector('input[id="email"]', { timeout: 5000 });
        await page.fill('input[id="email"]', "john.doe@example.com");

        // Wait for and fill password field
        await page.waitForSelector('input[id="password"]', { timeout: 5000 });
        await page.fill('input[id="password"]', "Password123!");

        await page.click('button[type="submit"]')
        // Click login button and wait for navigation
        await page.waitForURL(baseUrl + "/", { timeout: 10000 })
    } catch (error) {
        console.error('Login failed:', error);
        throw error;
    }
});


When("I navigate to the profile edit page", {timeout: 15000 }, async function () {
    await page.waitForSelector('[data-testid="user-avatar"]', {
        timeout: timeout,
    });
    await page.click('[data-testid="user-avatar"]');

    await page.waitForSelector("text=Edit Profile", {timeout: timeout});
    await page.click("text=Edit Profile");

    await page.waitForURL(baseUrl + "/profile");
});

When(
    "I update my first name to {string}",
    async function (newFirstName: string) {
        await page.waitForSelector('[data-testid="EditIcon"]', {
            timeout: timeout,
        });
        await page.click('[data-testid="EditIcon"]');

        await page.waitForSelector('input[name="firstName"]', {timeout: timeout});
        await page.fill('input[name="firstName"]', newFirstName);
    },
);

When("I update my last name to {string}", async function (newLastName: string) {
    await page.waitForSelector('input[name="lastName"]', {timeout: timeout});
    await page.fill('input[name="lastName"]', newLastName);
});

When("I update my nickname to {string}", async function (newNickname: string) {
    await page.waitForSelector('input[name="displayName"]', {timeout: timeout});
    await page.fill('input[name="displayName"]', newNickname);
});

When(
    "I update my phone number to {string}",
    async function (newPhoneNumber: string) {
        await page.waitForSelector('input[name="phoneNumber"]', {
            timeout: timeout,
        });
        await page.fill('input[name="phoneNumber"]', newPhoneNumber);
    },
);

When("I update my address to {string}", async function (newAddress: string) {
    await page.waitForSelector('input[name="address"]', {timeout: timeout});
    await page.fill('input[name="address"]', newAddress);
});

When("I update my city to {string}", async function (newCity: string) {
    await page.waitForSelector('input[name="city"]', {timeout: timeout});
    await page.fill('input[name="city"]', newCity);
});

When("I update my zip code to {string}", async function (newZipCode: string) {
    await page.waitForSelector('input[name="postalCode"]', {timeout: timeout});
    await page.fill('input[name="postalCode"]', newZipCode);

    await page.waitForSelector('button:has-text("Save Change")', {
        timeout: timeout,
    });
    await page.click('button:has-text("Save Change")');
});

Then(
    "I should see my updated name as {string}",
    async function (expectedName: string) {
        await page.waitForSelector("h5.MuiTypography-h5", {timeout: timeout});
        const displayedName = await page.textContent("h5.MuiTypography-h5");

        expect(displayedName).toBe(expectedName);

        await page.waitForTimeout(1000);

        await browser.close();
    },
);

//Scenario: Fail to update personal information with invalid input
Then(
    "I should see an {string} for the invalid input",
    { timeout: 15000 }, // Increase Cucumber step timeout
    async function (error_message: string) {
        // Set up dialog handler before clicking the button
        const dialogPromise = new Promise((resolve) => {
            page.once("dialog", async (dialog) => {
                const alertMessage = dialog.message();
                expect(alertMessage).toContain(error_message);
                await dialog.dismiss();
                resolve(true);
            });
        });

        // Click the button
        await page.waitForSelector('button:has-text("Save Change")', {
            timeout: timeout,
        });
        await page.click('button:has-text("Save Change")');

        // Wait for dialog to be handled
        await dialogPromise;
    }
);

