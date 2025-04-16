package lucaslimb.com.github.leilao;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class PageObject {

    protected ChromeDriver browser;

    public PageObject(ChromeDriver browser){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        if(browser == null){
            this.browser = new ChromeDriver(options);
        } else {
            this.browser = browser;
        }

        this.browser.manage().timeouts()
                .implicitlyWait(5, TimeUnit.SECONDS)
                .pageLoadTimeout(5, TimeUnit.SECONDS);
    }

    public void quit() {
        this.browser.quit();
    }

}
