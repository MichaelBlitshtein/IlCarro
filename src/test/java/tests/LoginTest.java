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
        }

    }
    @Test
    public void loginSuccess(){
       app.getHelperUser().openLoginForm();
       app.getHelperUser().fillLoginForm("michael@gmail.com","Michael12345$");
       app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test(enabled = false)
    public void loginSuccessModel(){
        User user = new User().withEmail("michael@gmail.com").withPassword("Michael12345$");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isLogged());
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
    public void loginWrongEmail_2() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("michael@gmailcom", "Michael12345$");
        app.getHelperUser().submit();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("\"Login or Password incorrect\""));
    }
    @Test
    public void loginWrongPassword(){
        User user = new User().withEmail("michael@gmail.com").withPassword("Michael12345");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("\"Login or Password incorrect\""));
    }

    @Test(enabled = false)
    public void loginUnregisteredUser(){

    }

    @AfterMethod
    public void postCondition(){
        app.getHelperUser().closeDialogContainer();
    }
}
