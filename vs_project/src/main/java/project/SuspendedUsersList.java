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
        // omvandla json-text till i ArrayList med SuspendedUser-objekt
        Type postListType1 = new TypeToken<ArrayList<SuspendedUsers>>() {
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

        // sparar informationen på servern
        HttpResponse<String> Lägg_Till_ResponseSuspendedUser;
        try {
            Lägg_Till_ResponseSuspendedUser = Unirest.post(Main.baseURL+"suspended")
                    .header("Content-Type", "application/json") // VIktigt
                    .body(SuspendedUser) // Skickar data
                    .asString(); // Returnerar ett HTTPResponse<String>
        } catch (UnirestException e) {
            IO.println("Undantag uppkoppling: " + e.getLocalizedMessage());
            return;
        }
    }

    public String Sök(){
        // hämtar alla suspendedUser object och lägger de i en lista
        get_allSuspendedUsers();

        // frågar användaren för userid
        IO.println("Säg kund id för den avstängda du vill hitta: ");
        String userId = IO.readln().trim().toLowerCase();

        //loopar igenom listSuspendedUsers för att hitta ett object med samma userid
        for (SuspendedUsers user : listSuspendedUsers) {
            if (user.getUserId().toLowerCase().equals(userId)) {
                IO.println(user);
                return user.getId();
            }
        }
        
        IO.println("avstängda hittades inte.");
        return "";
    }

    
    public void TaBort(){
         // hitta boken som ska readeras
         String id = Sök();

        // loppar igenom listSuspendedUsers för att hitta ett objekt som har samma id för att ta bort det objektet
        for (SuspendedUsers users : listSuspendedUsers) {
            if (users.getId().equals(id)) {
                listSuspendedUsers.remove(users);
            }
        }

        int deleteStatus;

        // ta bort från servern
        try {
            // skicka ett DELETE-anrop och hämta bara statuskoden (vi förväntar oss ingen body)
            deleteStatus = Unirest.delete(Main.baseURL + "suspended/" + id)
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

    public ArrayList<SuspendedUsers> getListSuspendedUsers(){
        return listSuspendedUsers;
    }
}
