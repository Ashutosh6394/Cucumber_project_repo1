package StepDefinition;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.*;
import page.TutorialNinjaPage;

public class TutorialNinja {

    static WebDriver driver;
    static TutorialNinjaPage page;

    static String email = "ashutosh" + System.currentTimeMillis() + "@gmail.com";
    static String password = "Ashutosh@123";

    @Given("user opens tutorialninja website")
    public void user_opens_tutorialninja_website() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        page = new TutorialNinjaPage(driver);
        page.openWebsite();

        Assert.assertTrue(page.getPageTitle().contains("Your Store"));
        System.out.println("Website opened successfully");
    }

    @When("user registers new account")
    public void user_registers_new_account() throws Exception {
        page.clickMyAccount();
        page.clickRegister();
        page.registerUser(email, password);

        Thread.sleep(2000);
    }

    @Then("account should be created successfully")
    public void account_should_be_created_successfully() {
        Assert.assertTrue(page.accountCreatedSuccessMessage());
        System.out.println("Registration completed");
    }

    @When("user login with valid details")
    public void user_login_with_valid_details() throws Exception {
        page.logout();
        page.loginUser(email, password);

        Thread.sleep(2000);
    }

    @Then("login should be successful")
    public void login_should_be_successful() {
        Assert.assertTrue(page.getPageTitle().contains("My Account"));
        System.out.println("Login completed");
    }

    @When("user searches product {string}")
    public void user_searches_product(String product) throws Exception {
        page.searchProduct(product);

        Thread.sleep(2000);
        System.out.println("Searched product: " + product);
    }

    @Then("search result should be displayed")
    public void search_result_should_be_displayed() {
        Assert.assertTrue(page.getPageTitle().contains("Search"));
        System.out.println("Search result displayed");
    }

    @When("user adds product to cart")
    public void user_adds_product_to_cart() throws Exception {
        page.addProductToCart();

        Thread.sleep(3000);
    }

    @Then("product should be added successfully")
    public void product_should_be_added_successfully() {
        Assert.assertTrue(page.productAddedSuccessMessage());
        System.out.println("Product added to cart");
    }

    @When("user deletes product from cart")
    public void user_deletes_product_from_cart() throws Exception {
        page.deleteProductFromCart();

        Thread.sleep(2000);
    }

    @Then("cart should be empty")
    public void cart_should_be_empty() {
        Assert.assertTrue(page.getCartText().contains("0 item"));
        System.out.println("Product deleted from cart");
    }

    @When("user changes address")
    public void user_changes_address() throws Exception {
        page.changeAddress();

        Thread.sleep(2000);
    }

    @Then("address should be changed successfully")
    public void address_should_be_changed_successfully() {
        Assert.assertTrue(page.getPageTitle().contains("Address Book"));
        System.out.println("Address changed successfully");
    }

    @When("user changes name")
    public void user_changes_name() throws Exception {
        page.changeName();

        Thread.sleep(2000);
    }

    @Then("name should be changed successfully")
    public void name_should_be_changed_successfully() {
        Assert.assertTrue(page.nameSuccessMessage());
        System.out.println("Name changed successfully");
    }

    @Then("user quits browser")
    public void user_quits_browser() {
        driver.quit();
        System.out.println("Browser closed successfully");
    }
}
