/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author marit
 */
public class Cadastro {
    private WebDriver driver;
    
    @FindBy(name = "nome")
    private WebElement nome;
    
    @FindBy(name = "email")
    private WebElement email;
    
    @FindBy(name = "senha")
    private WebElement senha;
    
    public Cadastro cadastrar(String nome, String email, String senha){
        this.nome.sendKeys(nome);
        this.email.sendKeys(email);
        this.senha.sendKeys(senha);
        this.senha.submit();
        
        PageFactory.initElements(driver,this);
        return this;
    }
}
