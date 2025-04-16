package lucaslimb.com.github.leilao;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LeiloesTest {

    private LeiloesPage leiloesPage;
    private CadastroLeilaoPage createPage;

    @BeforeEach
    public void beforeEach(){
        LoginPage loginPage = new LoginPage();
        loginPage.fillLoginForm("fulano", "pass");
        this.leiloesPage = loginPage.sendLoginForm();
        this.createPage = leiloesPage.loadForm();
    }

    @AfterEach
    public void afterEach(){
        this.leiloesPage.quit();
    }

    @Test
    public void createAuction(){
        String data = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilao dia " + data;
        String valor = "200.00";
        createPage.createAuction(nome, valor, data);

        Assert.assertTrue(leiloesPage.isAuctionCreated(nome, valor, data));
    }

    @Test
    public void formValidations(){
        createPage.createAuction("", "", "");
        Assert.assertFalse(this.createPage.isCurrentPage());
        Assert.assertTrue(this.leiloesPage.isCurrentPage());
        Assert.assertTrue(this.createPage.isValidationMessagesOn());
    }

}
