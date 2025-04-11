package lucaslimb.com.github.leilao;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {

    public static final String URL = "http://localhost:8080/login";
    private ChromeDriver browser;

    public LoginPage(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        this.browser = new ChromeDriver();
        browser.navigate().to(URL);
    }

    public void quit() {
        this.browser.quit();
    }

    public void fillLoginForm(String username, String password) {
        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(password);
    }

    public void sendLoginForm() {
        browser.findElement(By.id("login-form")).submit();
    }

    public boolean isLoginPage() {
        return browser.getCurrentUrl().equals(URL);
    }

    public Object getLoginUsername() {
        try {
            return browser.findElement(By.id("usuario-logado")).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void goToLances() {
        this.browser.navigate().to("http://localhost:8080/leiloes/2");
    }

    public boolean containsText(String text) {
        return browser.getPageSource().contains(text);
    }

    public boolean isInvalidLoginPage() {
        return browser.getCurrentUrl().equals(URL + "?error");
    }
}
