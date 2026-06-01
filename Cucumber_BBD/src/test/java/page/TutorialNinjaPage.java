package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TutorialNinjaPage {

    WebDriver driver;

    public TutorialNinjaPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openWebsite() {
        driver.get("https://tutorialsninja.com/demo/");
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void clickMyAccount() {
        driver.findElement(By.xpath("//a[@title='My Account']")).click();
    }

    public void clickRegister() {
        driver.findElement(By.linkText("Register")).click();
    }

    public void registerUser(String email, String password) {
        driver.findElement(By.id("input-firstname")).sendKeys("Ashutosh");
        driver.findElement(By.id("input-lastname")).sendKeys("Pandey");
        driver.findElement(By.id("input-email")).sendKeys(email);
        driver.findElement(By.id("input-telephone")).sendKeys("9876543210");
        driver.findElement(By.id("input-password")).sendKeys(password);
        driver.findElement(By.id("input-confirm")).sendKeys(password);
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
    }

    public boolean accountCreatedSuccessMessage() {
        return driver.getPageSource().contains("Your Account Has Been Created!");
    }

    public void logout() {
        driver.findElement(By.linkText("Continue")).click();
        clickMyAccount();
        driver.findElement(By.linkText("Logout")).click();
        driver.findElement(By.linkText("Continue")).click();
    }

    public void loginUser(String email, String password) {
        clickMyAccount();
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("input-email")).sendKeys(email);
        driver.findElement(By.id("input-password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Login']")).click();
    }

    public void searchProduct(String product) {
        WebElement searchBox = driver.findElement(By.name("search"));
        searchBox.clear();
        searchBox.sendKeys(product);
        searchBox.sendKeys(Keys.ENTER);
    }

    public void addProductToCart() {
        searchProduct("iphone");
        driver.findElement(By.xpath("(//span[text()='Add to Cart'])[1]")).click();
    }

    public boolean productAddedSuccessMessage() {
        return driver.getPageSource().contains("Success");
    }

    public void deleteProductFromCart() {
        driver.findElement(By.id("cart-total")).click();
        driver.findElement(By.xpath("//button[@title='Remove']")).click();
    }

    public String getCartText() {
        return driver.findElement(By.id("cart-total")).getText();
    }

    public void changeAddress() {
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/address");

        driver.findElement(By.linkText("New Address")).click();

        driver.findElement(By.id("input-firstname")).sendKeys("Ashutosh");
        driver.findElement(By.id("input-lastname")).sendKeys("Pandey");
        driver.findElement(By.id("input-address-1")).sendKeys("Sector 62");
        driver.findElement(By.id("input-city")).sendKeys("Noida");
        driver.findElement(By.id("input-postcode")).sendKeys("201301");

        driver.findElement(By.id("input-country")).sendKeys("India");
        driver.findElement(By.id("input-zone")).sendKeys("Uttar Pradesh");

        driver.findElement(By.xpath("//input[@value='Continue']")).click();
    }

    public void changeName() {
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/edit");

        WebElement firstName = driver.findElement(By.id("input-firstname"));
        firstName.clear();
        firstName.sendKeys("Ashu");

        WebElement lastName = driver.findElement(By.id("input-lastname"));
        lastName.clear();
        lastName.sendKeys("Pandey");

        driver.findElement(By.xpath("//input[@value='Continue']")).click();
    }

    public boolean nameSuccessMessage() {
        return driver.getPageSource().contains("Success");
    }
}