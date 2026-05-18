package project.Listor;

/* 
Valentin fuenzalida ribbing
SuspendedUsersList innehåller en metod för att hämta alla avstängda in i listan, 
lägg till en avstängd i servern, sök en avstängd i servern, 
ta bort en avstängd i servern och en getter för SuspendedUsersList 
 */

//GSON objekt som vi behöver
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
//importera Type för att hjälpa json att omvandla data
import java.lang.reflect.Type;
//Unirest objekt som vi behöver
import kong.unirest.Unirest;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import project.Basklasser.SuspendedUsers;
import project.system.Main;
import project.system.SaveToFile;

// kunna lagra objekt i listor och ändra i arraylistor
import java.util.ArrayList;
import java.util.Iterator;

public class SuspendedUsersList implements SaveToFile{
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
        Iterator<SuspendedUsers> it = listSuspendedUsers.iterator();
         // hitta kunden som ska ta borts
        String id = Sök();

        // loppar igenom listusers för att hitta ett objekt som har samma id som det id jag fick från Sök() och sen ta bort detta objekt
        while (it.hasNext()) {
            SuspendedUsers SuspendedUser = it.next();
            if (SuspendedUser.getId().equals(id)) {
                it.remove();
                break;
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

    public void save(){

    }
    public void read(){
        
    }
}
