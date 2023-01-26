package tests;

import model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        logger.info("Test success");
    }

    @Test
    public void loginSuccessModel(){
        User user = new User().withEmail("michael@gmail.com").withPassword("Michael12345$");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
    }

    @Test
    public void loginWrongEmail(){
        User user = new User().withEmail("michaelgmail.com").withPassword("Michael12345$");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }


    @Test
    public void loginWrongPassword(){
        User user = new User().withEmail("michael@gmail.com").withPassword("Michael12345");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"\"Login or Password incorrect\"");
    }

    @Test(enabled = false)
    public void loginUnregisteredUser(){

    }

    @AfterMethod
    public void postCondition(){

        app.getHelperUser().closeDialogContainer();
    }
}
