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

public class BooksList {

    private ArrayList<Books> listBooks = new ArrayList<>();
    Gson gson = new Gson();

    public void get_allbooks() {

        HttpResponse<String> get_all_ResponseBooks;
        // hämtar URL och lagrar den i
        try {
            get_all_ResponseBooks = Unirest.get(Main.baseURL + "books").asString();
        } catch (Exception e) {
            IO.println("Undantag uppkoppling " + e.getLocalizedMessage());
            return;
        }
        // kontrollera om servern svarade med "200 OK"
        int status = get_all_ResponseBooks.getStatus();
        if (status != 200) {
            IO.println("Fel från servern, statuskod: " + status);
            return;
        }
        String get_all_bodybooks = get_all_ResponseBooks.getBody();
        // omvandla json-text till i ArrayList med bok-objekt
        Type postListType1 = new TypeToken<ArrayList<Books>>() {
        }.getType();
        listBooks = gson.fromJson(get_all_bodybooks, postListType1);
    }

    public void lägg_till(){
        
    }
}
