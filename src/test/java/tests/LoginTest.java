package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }

    }
    @Test
    public void loginSuccess(){
       app.getHelperUser().openLoginForm();
       app.getHelperUser().fillLoginForm("michael@gmail.com","Michael12345$");
       app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        app.getHelperUser().closeDialogContainer();
    }

    @Test
    public void loginWrongEmail(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("michael@gmailcom","Michael12345$");
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("\"Login or Password incorrect\""));
    }

    @Test
    public void loginWrongPassword(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("michael@gmail.com","Michael12345");
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("\"Login or Password incorrect\""));
    }

    @Test
    public void loginUnregisteredUser(){

    }
}
