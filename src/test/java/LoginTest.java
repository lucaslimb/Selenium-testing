import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    public static final String URL = "http://localhost:9000/login";
    private ChromeDriver browser;

    @BeforeAll
    public static void beforeAll(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
    }

    @BeforeEach
    public void beforeEach(){
        this.browser = new ChromeDriver();
        browser.navigate().to(URL);
    }

    @AfterEach
    public void afterEach(){
        this.browser.quit();
    }
    
    @Test
    public void validLogin(){
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        Assert.assertFalse(browser.getCurrentUrl().equals(URL));
        Assert.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
    }

    @Test
    public void invalidLogin(){
        browser.findElement(By.id("username")).sendKeys("invalido");
        browser.findElement(By.id("password")).sendKeys("0000");
        browser.findElement(By.id("login-form")).submit();

        Assert.assertTrue(browser.getCurrentUrl().equals("http://localhost:9000/login?error"));
        Assert.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos"));
        Assert.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")).getText());
    }

    @Test
    public void denyRestrictedPageAccess(){
        this.browser.navigate().to("http://localhost:9000/leiloes/2");

        Assert.assertTrue(browser.getCurrentUrl().equals(URL));
        Assert.assertFalse(browser.getPageSource().contains("Dados do Leilão"));
    }

}
