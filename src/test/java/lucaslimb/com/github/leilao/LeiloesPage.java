package lucaslimb.com.github.leilao;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LeiloesPage extends PageObject{

    public static final String NEW_AUCTION_URL = "http://localhost:8080/leiloes/new";
    public static final String AUCTIONS_URL = "http://localhost:8080/leiloes";

    public LeiloesPage(ChromeDriver browser){
        super(browser);
        browser.navigate().to(AUCTIONS_URL);
    }

    public CadastroLeilaoPage loadForm() {
        this.browser.navigate().to(NEW_AUCTION_URL);
        return new CadastroLeilaoPage(browser);
    }

    public boolean isAuctionCreated(String nome, String valor, String data) {
        WebElement tableRow = this.browser.findElement(By.cssSelector("#tabela tbody tr:last-child"));
        WebElement rowNome = tableRow.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement rowData = tableRow.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement rowValor = tableRow.findElement(By.cssSelector("td:nth-child(3)"));
        return rowNome.getText().equals(nome)
                && rowData.getText().equals(data)
                && rowValor.getText().equals(valor);
    }

    public boolean isCurrentPage() {
        return browser.getCurrentUrl().equals(AUCTIONS_URL);
    }
}
