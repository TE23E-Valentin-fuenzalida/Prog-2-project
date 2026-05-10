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
            get_all_ResponseMagazines = Unirest.get(Main.baseURL + "magazines").asString();
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

    public void LäggTill() {
        // frågar användaren av olika egenskaper
        IO.println("Titel: ");
        String title = IO.readln();

        int issueNumber = 0;
        while (true) {
            IO.println("Issue Number: ");
            String Number = IO.readln();
            try {
                issueNumber = Integer.parseInt(Number);
                break;
            } catch (NumberFormatException e) {
                IO.println("fel inmattning. skriv ett nummer");
            }
        }

        IO.println("Category");
        String category = IO.readln();

        int publishedYear = 0;
        while (true) {
            IO.println("Published Year: ");
            String Year = IO.readln();
            try {
                publishedYear = Integer.parseInt(Year);
                break;
            } catch (NumberFormatException e) {
                IO.println("fel inmattning. skriv ett nummer");
            }
        }

        String get_all_bodymagazines = "";
        // sätter id som en plus lenghten av hela arraylisten
        String id = Integer.toString((get_all_bodymagazines.length() + 1));

        Magazines magazine = new Magazines(id, title, true, issueNumber, category, publishedYear);

        listMagazines.add(magazine);

        // sparar informationen på servern
        HttpResponse<String> Lägg_Till_ResponseMagazines;
        try {
            Lägg_Till_ResponseMagazines = Unirest.post(Main.baseURL+"magazines")
                    .header("Content-Type", "application/json") // VIktigt
                    .body(magazine) // Skickar data
                    .asString(); // Returnerar ett HTTPResponse<String>
        } catch (UnirestException e) {
            IO.println("Undantag uppkoppling: " + e.getLocalizedMessage());
            return;
        }
    }

    public String Sök(){
         // hämtar alla users object och lägger de i en lista
        get_allMagazines();

        // frågar användaren för email
        IO.println("Ange titel för tidningen som du vill hitta: ");
        String titel = IO.readln().trim().toLowerCase();

        //loopar igenom listUsers för att hitta ett object med samma email
        for (Magazines magazine : listMagazines) {
            if (magazine.getTitle().toLowerCase().equals(titel)) {
                IO.println(magazine);
                return magazine.getId();
            }
        }
        
        IO.println("avstängda hittades inte.");
        return "";
    }

    public void TaBort(){
        String id = Sök();
        // loppar igenom listusers för att hitta ett objekt som har samma id som det id jag fick från Sök() och sen ta bort detta objekt
        for (Magazines magazine : listMagazines) {
            if (magazine.getId().equals(id)) {
                listMagazines.remove(magazine);
            }
        }

        int deleteStatus;

        // ta bort från servern
        try {
            // skicka ett DELETE-anrop och hämta bara statuskoden (vi förväntar oss ingen body)
            deleteStatus = Unirest.delete(Main.baseURL + "magazines/" + id)
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
}
