package lucaslimb.com.github.leilao;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class LoginPage extends PageObject {

    public static final String URL = "http://localhost:8080/login";
    public static final String URL_ERROR = "http://localhost:8080/login?error";

    public LoginPage(){
        super(null);
        browser.navigate().to(URL);
    }

    public void fillLoginForm(String username, String password) {
        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(password);
    }

    public LeiloesPage sendLoginForm() {
        browser.findElement(By.id("login-form")).submit();
        if(browser.getCurrentUrl().equals(URL_ERROR)) {
            return null;
        } else{
        return new LeiloesPage(browser);
        }
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
        return browser.getCurrentUrl().equals(URL_ERROR);
    }
}
