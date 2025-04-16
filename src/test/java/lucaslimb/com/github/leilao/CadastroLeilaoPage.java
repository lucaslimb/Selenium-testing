package lucaslimb.com.github.leilao;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class CadastroLeilaoPage extends PageObject{

    public static final String CREATE_AUCTION_URL = "http://localhost:8080/leiloes/new";

    public CadastroLeilaoPage(ChromeDriver browser){
        super(browser);
    }

    public void createAuction(String nome, String valor, String data) {
        this.browser.findElement(By.id("nome")).sendKeys(nome);
        this.browser.findElement(By.id("valorInicial")).sendKeys(valor);
        this.browser.findElement(By.id("dataAbertura")).sendKeys(data);
        this.browser.findElement(By.id("button-submit")).click();
    }

    public boolean isCurrentPage() {
        return browser.getCurrentUrl().equals(CREATE_AUCTION_URL);
    }

    public boolean isValidationMessagesOn() {
        String pageSource = browser.getPageSource();
        return pageSource.contains("minimo 3 caracteres") &&
                pageSource.contains("não deve estar em branco") &&
                pageSource.contains("deve ser um valor maior de 0.1") &&
                pageSource.contains("não deve estar em branco") &&
                pageSource.contains("deve ser uma data no formato dd/MM/yyyy");
    }
}
