package tests;

import manager.ListenerTNG;
import model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Random;
@Listeners(ListenerTNG.class)
public class RegistrationTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }
@Test
    public void registrationSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000);
        User user = new User().withName("Adam").withLastName("Robinson").withEmail("robinson" +i+"@mail.com").withPassword("Robinson123$");
        logger.info("Start test Registration Success");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
    Assert.assertEquals(app.getHelperUser().getMessage(),"You are logged in success");
    logger.info("Test success");
    }
@Test
    public void registrationWrongEmail(){

        User user = new User().withName("Adam").withLastName("Robinson").withEmail("robinsonmail.com").withPassword("Robinson123$");
        logger.info("Start test Registration With Wrong Email");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isErrorMessageContains("Wrong email format"));
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Test success");
    }
  @Test
    public void registrationWrongPassword(){

        User user = new User().withName("Adam").withLastName("Robinson").withEmail("robinson@mail.com").withPassword("Robinson123");
      logger.info("Start test Registration With Wrong Password");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isErrorMessageContains("Password must contain"));
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Test success");
    }

    @AfterMethod
    public void postCondition(){
        app.getHelperUser().closeDialogContainer();
    }
}
