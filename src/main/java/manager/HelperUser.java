package manager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperUser extends HelperBase{

    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm(){
        click(By.cssSelector("[href='/login?url=%2Fsearch']"));
    }

    public void fillLoginForm(String email,String password){
        //for email
        type(By.cssSelector("#email"),email);
        //for password
        type(By.cssSelector("#password"),password);

    }

    public void submitLogin(){
        click(By.cssSelector("[type='submit']"));

    }

    public boolean isLogged() {
       List <WebElement> list = wd.findElements(By.xpath("//*[href='/logout?url=%2Fsearch']"));
       return list.size()>0;
    }

    public void logout() {
        click(By.xpath("//*[href='/logout?url=%2Fsearch']"));
    }

    public boolean isErrorMessageDisplayed(String message){
      String text =  wd.findElement(By.xpath("//*[text()='\"Login or Password incorrect\"']")).getText();
        //click 'ok'
        click(By.xpath("//*[text()='Ok']"));
        return text.contains(message);
    }
}
