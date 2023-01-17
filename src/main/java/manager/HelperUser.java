package manager;

import model.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

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

    public void fillLoginForm(User user){
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void submit(){
        click(By.cssSelector("[type='submit']"));

    }

    public boolean isLogged() {
       List <WebElement> list = wd.findElements(By.xpath("//*[@href='/logout?url=%2Fsearch']"));
       return list.size()>0;
    }

    public void logout() {
        click(By.xpath("//*[@href='/logout?url=%2Fsearch']"));
    }

    public boolean isErrorMessageDisplayed(String message){
      String text =  wd.findElement(By.xpath("//*[text()='\"Login or Password incorrect\"']")).getText();
        //click 'ok'
        click(By.xpath("//*[text()='Ok']"));
        return text.contains(message);
    }

    public void closeDialogContainer(){
       if(isElementPresent(By.xpath("//button[text()='Ok']"))){
           click(By.xpath("//button[text()='Ok']"));
       }
    }

    public String getErrorText() {
        return wd.findElement(By.cssSelector("div.error")).getText();
    }

    public boolean isYallaButtonNotActive() {
       // return isElementPresent(By.cssSelector("button[disabled]"));
        return !wd.findElement(By.cssSelector("button[disabled]")).isEnabled();
    }

    public void openRegistrationForm() {
        click(By.cssSelector("[href^='/registration']"));
    }

    public void fillRegistrationForm(User user) {
        type(By.id("#name"),user.getName());
        type(By.id("#lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"),user.getPassword());

    }

    public void checkPolicy() {
        click(By.cssSelector("label[for='terms-of-use']"));
    }

    public void checkPolicyXY(){
        Dimension size = wd.manage().window().getSize();
        System.out.println("Window height" + size.getHeight());
        System.out.println("Window height" + size.getWidth());

        WebElement label = wd.findElement(By.cssSelector("label[for='terms-of-use']"));

        Rectangle rect = label.getRect();
        int xOffset = rect.getWidth()/2;

        Actions actions = new Actions(wd);
        actions.moveToElement(label,-xOffset,0).click().release().perform();
    }

    public void checkPolicyJS(){
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("");
        document.querySelector('#terms-of-use').checked=true;

    }



    public boolean isRegistrationSuccess(){
       List <WebElement> list = wd.findElements(By.cssSelector("//*[@class='message'][text()='You are logged in success']"));
       return list.size()>0;
    }
}
