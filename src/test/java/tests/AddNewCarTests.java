package tests;

import manager.ListenerTNG;
import model.Car;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Random;
@Listeners(ListenerTNG.class)
public class AddNewCarTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("michael@gmail.com").withPassword("Michael12345$"));

        }
    }

    @Test
    public void addNewCarSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;

        Car car = Car.builder()
                .location("Haifa, Israel")
                .manufacture("BMW")
                .model("M5")
                .year("2020")
                .fuel("Petrol")
                .seats("4")
                .claSS("C")
                .carRegNumber("12345677890")
                .price("65")
                .about("very nice car")
                .build();


        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().attachPhoto("C:\\Users\\Michael Blitshtein\\Documents\\GitHub\\IlCarro\\Car.jfif");
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().isTitleMessageContains("Car added"));
        logger.info("Test success");
    }


}