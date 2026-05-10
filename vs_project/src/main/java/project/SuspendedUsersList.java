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

public class SuspendedUsersList {
    private ArrayList<SuspendedUsers> listSuspendedUsers = new ArrayList<>();
    Gson gson = new Gson();

    public void get_allSuspendedUsers() {

        HttpResponse<String> get_all_ResponseSuspendedUsers;
        // hämtar URL och lagrar den i servern
        try {
            get_all_ResponseSuspendedUsers = Unirest.get(Main.baseURL + "suspended").asString();
        } catch (Exception e) {
            IO.println("Undantag uppkoppling " + e.getLocalizedMessage());
            return;
        }
        // kontrollera om servern svarade med "200 OK"
        int status = get_all_ResponseSuspendedUsers.getStatus();
        if (status != 200) {
            IO.println("Fel från servern, statuskod: " + status);
            return;
        }
        // hämtar json texten
        String get_all_bodySuspendedUsers = get_all_ResponseSuspendedUsers.getBody();
        // omvandla json-text till i ArrayList med bok-objekt
        Type postListType1 = new TypeToken<ArrayList<Users>>() {
        }.getType();
        listSuspendedUsers = gson.fromJson(get_all_bodySuspendedUsers, postListType1);
    }

    public void LäggTill(){
         // frågar användaren av olika egenskaper
        IO.println("user Id: ");
        String userId = IO.readln();

        IO.println("Reason: ");
        String reason = IO.readln();

        String get_all_bodySuspendedUsers = "";
        // sätter id som en plus lenghten av hela arraylisten
        String id = Integer.toString((get_all_bodySuspendedUsers.length() + 1));

        SuspendedUsers SuspendedUser = new SuspendedUsers(id, userId, reason);

        listSuspendedUsers.add(SuspendedUser);
    }

    public SuspendedUsers Sök(){
         IO.println("Säg id för den avstängda du vill hitta: ");
        String userid = IO.readln().trim().toLowerCase();
        try {
            // Skickar ett GET anrop till servern för att hämta en avstängd med en viss userid
            HttpResponse<SuspendedUsers> response = Unirest.get(Main.baseURL+"/suspended/userid/"+userid)
            // försöker omvandla svaret till ett Books-object
            .asObject(SuspendedUsers.class);
            
            // kollar om servern svarade "200 OK" 
            if (response.getStatus()==200) {
                // hämtar själva body från servern
                SuspendedUsers SuspendedUser = response.getBody();
                //skriver ut boken
                IO.println("Den boken du hittade är "+SuspendedUser);
                return SuspendedUser;
            }
            IO.println("Boken hittades inte.");
            return null;
        } catch (UnirestException e) {
            IO.println("Fel vid sökning: "+e.getMessage());
            return null;
        }
    }
}
