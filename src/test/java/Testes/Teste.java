/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author marit
 */
public class Teste {
    
    private static WebDriver driver;
    
    private static String url = "http://google.com.br";
   
    @BeforeClass
    public static void configura() {
        System.setProperty("webdriver.gecko.driver", "/home/joao/geckodriver");
        driver = new FirefoxDriver();
        driver.get(url);
    }
    
    @Test
    public void testCadastroValido(){
        driver.navigate().to(url);
        
        WebElement pesquisa = driver.findElement(By.name("q"));
        pesquisa.sendKeys("Selenium WebDriver e Selenium IDE");
        pesquisa.submit();
        
        boolean pesquisou = driver.getCurrentUrl().contentEquals(url);
        
        assertFalse(pesquisou);
        
    }
}
