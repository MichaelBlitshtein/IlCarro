package tests;

import manager.DataProviderUser;
import manager.ListenerTNG;
import model.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Listeners(ListenerTNG.class)

public class LoginTest extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
            logger.info("I need logout");
        }

    }


    @DataProvider
    public Iterator<Object[]> loginData(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"michael@gmail.com","Michael12345$"});
        list.add(new Object[]{"franky@gmail.com","FrankY123$"});
        list.add(new Object[]{"michael@gmail.com","Michael12345$"});

        return list.iterator();
    }


    @Test(dataProvider = "loginData")
    public void loginSuccess(String email,String password){
        logger.info("Login with valid data: email:"+ email+" & password:"+password);
       app.getHelperUser().openLoginForm();
       app.getHelperUser().fillLoginForm(email,password);
       app.getHelperUser().pause(3000);
       app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        app.getHelperUser().click(By.xpath("//button[text()='Ok']"));
        logger.info("Test success");
    }


    @Test(dataProvider = "loginDataClass",dataProviderClass = DataProviderUser.class)
    public void loginSuccess_2(String email,String password){
        logger.info("Login with valid data: email:"+ email+" & password:"+password);
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(email,password);
        app.getHelperUser().pause(3000);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        app.getHelperUser().click(By.xpath("//button[text()='Ok']"));
        logger.info("Test success");
    }


    @Test(dataProvider = "loginDataUser",dataProviderClass = DataProviderUser.class)
    public void loginSuccessModel2(User user){

        logger.info("Test starts with user model --->"+user.toString());
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
    }


    @Test(dataProvider = "loginDataUserFromFile",dataProviderClass = DataProviderUser.class)
    public void loginSuccessModelFromFile(User user){

        logger.info("Test starts with user model --->"+user.toString());
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
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
