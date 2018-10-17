/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import Paginas.Cadastro;
import Paginas.Login;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author semana
 */
public class LoginTeste {

   
    private static WebDriver driver;
    private static String url = "http://localhost:8080/MinicursoSelenium/index.html";


@BeforeClass
public static void setUpClass() {
System.setProperty("webdriver.gecko.driver", "/home/semana/Downloads/geckodriver");
        driver = new FirefoxDriver();
        driver.get(url);

    }

@Test
    public void testLogin(){
        
        Login pageLogin = PageFactory.initElements(driver, Login.class);
        pageLogin.logar("participante4@teste.com", "12345");
        boolean logou = driver.getPageSource().contains("Lista de Eventos que você participa");
        assertTrue(logou);
        
    }
    
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
