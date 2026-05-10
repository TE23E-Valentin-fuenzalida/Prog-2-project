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
import java.util.Collections;

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

    public void LäggTill(){
         // frågar användaren av olika egenskaper
        IO.println("Name: ");
        String name = IO.readln();

        IO.println("Email (med @skola.se): ");
        String email = IO.readln();

        String get_all_bodyUsers = "";
        // sätter id som en längre än lenghten av hela arraylisten
        String id = Integer.toString((get_all_bodyUsers.length() + 1));

        Users User = new Users(id, name, email);

        listUsers.add(User);

                // sparar informationen på servern
        HttpResponse<String> Lägg_Till_ResponseUser;
        try {
            Lägg_Till_ResponseUser = Unirest.post(Main.baseURL+"users")
                    .header("Content-Type", "application/json") // VIktigt
                    .body(User) // Skickar data
                    .asString(); // Returnerar ett HTTPResponse<String>
        } catch (UnirestException e) {
            IO.println("Undantag uppkoppling: " + e.getLocalizedMessage());
            return;
        }
    }

        public String Sök(){
        // hämtar alla users object och lägger de i en lista
        get_allUsers();

        // frågar användaren för email
        IO.println("Säg email för den kund du vill hitta (med @skola.se): ");
        String email = IO.readln().trim().toLowerCase();

        //loopar igenom listUsers för att hitta ett object med samma email
        for (Users user : listUsers) {
            if (user.getEmail().toLowerCase().equals(email)) {
                IO.println(user);
                return user.getId();
            }
        }

        
        IO.println("kunden hittades inte.");
        return "";
    }

    
    public void TaBort(){
         // hitta kunden som ska ta borts
        String id = Sök();

        // loppar igenom listusers för att hitta ett objekt som har samma id som det id jag fick från Sök() och sen ta bort detta objekt
        for (Users users : listUsers) {
            if (users.getId().equals(id)) {
                listUsers.remove(users);
            }
        }

        int deleteStatus;

        // ta bort från servern
        try {
            // skicka ett DELETE-anrop och hämta bara statuskoden (vi förväntar oss ingen body)
            deleteStatus = Unirest.delete(Main.baseURL + "users/" + id)
                    .asEmpty() // Skickar INGEN body
                    .getStatus();
        } catch (UnirestException e) {
            IO.println("Undantag uppkoppling: " + e.getLocalizedMessage());
            return;
        }
        if (deleteStatus == 200) {
            IO.println("Inlägget med ID " + id + " är borttaget");
        } else if (deleteStatus == 204) {
            IO.println("Inlägget fanns inte kvar / Inget innehåll på id=" + id);
        } else {
            IO.println("Något gick fel. Statuskod: " + deleteStatus);
        }
    }

    public void Sortera(){
        // hämtar alla Users och lägger de i lista
        get_allUsers();
        // sorterar listan för bosktavsordning
        Collections.sort(listUsers);
        // skriver ut listan genom att loppa igenom varje objekt i  listan
        for (Users users : listUsers) {
            IO.println(users);
        }
    }
    public ArrayList<Users> getListUsers(){
        return listUsers;
    }
}
