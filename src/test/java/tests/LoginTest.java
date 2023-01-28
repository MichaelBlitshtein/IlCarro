package tests;

import manager.ListenerTNG;
import model.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(ListenerTNG.class)

public class LoginTest extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
            logger.info("I need logout");
        }

    }
    @Test
    public void loginSuccess(){
        logger.info("Login with valid data: email 'michael@gmail.com', password 'Michael12345$'");
       app.getHelperUser().openLoginForm();
       app.getHelperUser().fillLoginForm("michael@gmail.com","Michael12345$");
       app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        app.getHelperUser().click(By.xpath("//button[text()='Ok']"));
        logger.info("Test success");
    }

    @Test
    public void loginSuccessModel(){
        User user = new User().withEmail("michael@gmail.com").withPassword("Michael12345$");
        logger.info("Login with valid data: email 'michael@gmail.com', password 'Michael12345$'");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        app.getHelperUser().pause(2000);
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        logger.info("Test success");
    }

    @Test
    public void loginWrongEmail(){
        User user = new User().withEmail("michaelgmail.com").withPassword("Michael12345$");
        logger.info("Login with wrong data (email): email 'michaelmail.com', password 'Michael12345$'");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Test success");
    }


    @Test
    public void loginWrongPassword(){
        User user = new User().withEmail("michael@gmail.com").withPassword("Michael12345");
        logger.info("Login with wrong data (password): email 'michael@mail.com', password 'Michael12345'");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"\"Login or Password incorrect\"");
        logger.info("Test success");
    }

    @Test(enabled = false)
    public void loginUnregisteredUser(){

    }

    @AfterMethod
    public void postCondition(){

        app.getHelperUser().closeDialogContainer();
    }
}
