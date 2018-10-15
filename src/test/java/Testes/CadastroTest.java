/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author marit
 
public class CadastroTest {

    private static WebDriver driver;
    
    private static String url = "http://localhost:8080/MinicursoSelenium/registro.html";
   
    @BeforeClass
    public static void configura() {
        System.setProperty("webdriver.gecko.driver", "");
        driver = new FirefoxDriver();
        driver.get(url);
    }
    
    @Test
    public void testCadastroValido(){
        driver.navigate().to(url);
        Cadastro pageCadastro = PageFactory.initElements(driver, Cadastro.class);
        pageCadastro.cadastrar("Marina", "marina@test", "123456");
        boolean achouProjeto = driver.getCurrentUrl().matches("http://localhost:8080/dcc192-2018-1-trb2-joao-pedro-rian-master");

        assertTrue(achouProjeto);
        
    }

}
*/