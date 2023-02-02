package manager;

import model.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {
    @DataProvider
    public Iterator<Object[]> loginDataClass(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"michael@gmail.com","Michael12345$"});
        list.add(new Object[]{"franky@gmail.com","FrankY123$"});
        list.add(new Object[]{"michael@gmail.com","Michael12345$"});

        return list.iterator();
    }


    @DataProvider
    public Iterator<Object[]> loginDataUser(){
        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{User.builder().email("michael@gmail.com").password("Michael12345$").build()});
        list.add(new Object[]{User.builder().email("franky@gmail.com").password("FrankY123$").build()});
        list.add(new Object[]{User.builder().email("michael@gmail.com").password("Michael12345$").build()});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginDataUserFromFile() throws IOException {
        List<Object[]> list = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/test/resources/data.csv")));
        String line = bufferedReader.readLine();
        while(line!=null){
            String[] split = line.split(";");
            list.add(new Object[]{User.builder().email(split[0]).password(split[1]).build()});
            line = bufferedReader.readLine();
        }

        return list.iterator();
    }
}
