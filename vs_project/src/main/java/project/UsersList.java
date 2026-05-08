package project;

//GSON objekt som vi behöver
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
//importera Type för att hjälpa json att omvandla data
import java.lang.reflect.Type;
//Unirest objekt som vi behöver
import kong.unirest.Unirest;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestConfigException;
import kong.unirest.UnirestException;

//importera Fill hanteringen
import java.nio.file.*;
import java.time.chrono.HijrahChronology;
import java.io.EOFException;
import java.io.IOException;
// arraylist för att lagra objekt
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.plaf.FontUIResource;

public class UsersList {
    private ArrayList<Users> listUsers = new ArrayList<>();
    Gson gson = new Gson();

    public void get_allUsers() {

        HttpResponse<String> get_all_ResponseUsers;
        // hämtar URL och lagrar den i servern
        try {
            get_all_ResponseUsers = Unirest.get(Main.baseURL + "users").asString();
        } catch (Exception e) {
            IO.println("Undantag uppkoppling " + e.getLocalizedMessage());
            return;
        }
        // kontrollera om servern svarade med "200 OK"
        int status = get_all_ResponseUsers.getStatus();
        if (status != 200) {
            IO.println("Fel från servern, statuskod: " + status);
            return;
        }
        // hämtar json texten
        String get_all_bodyUsers = get_all_ResponseUsers.getBody();
        // omvandla json-text till i ArrayList med bok-objekt
        Type postListType1 = new TypeToken<ArrayList<Users>>() {
        }.getType();
        listUsers = gson.fromJson(get_all_bodyUsers, postListType1);
    }
}
