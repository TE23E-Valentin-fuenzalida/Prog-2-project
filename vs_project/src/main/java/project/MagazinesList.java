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

public class MagazinesList {
    private ArrayList<Magazines> listMagazines = new ArrayList<>();
    Gson gson = new Gson();

    public void get_allMagazines() {
    HttpResponse<String> get_all_ResponseMagazines;
                    try {
                        get_all_ResponseMagazines = Unirest.get(Main.baseURL+"magazines").asString();
                    } catch (Exception e) {
                        IO.println("Undantag uppkoppling " + e.getLocalizedMessage());
                        return;
                    }
                    // kontrollera om servern svarade med "200 OK"
                    int status = get_all_ResponseMagazines.getStatus();
                    if (status != 200) {
                        IO.println("Fel från servern, statuskod: " + status);
                        return;
                    }
                    // gör om resposnseMagazines till en String
                    String get_all_bodymagazines = get_all_ResponseMagazines.getBody();
                    // omvandla json-text till i ArrayList med bok-objekt
                    Type postListType2 = new TypeToken<ArrayList<Magazines>>() {
                    }.getType();
                listMagazines = gson.fromJson(get_all_bodymagazines, postListType2);
    }
}
