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
            Lägg_Till_ResponseMagazines = Unirest.post(Main.baseURL)
                    .header("Content-Type", "application/json") // VIktigt
                    .body(magazine) // Skickar data
                    .asString(); // Returnerar ett HTTPResponse<String>
        } catch (UnirestException e) {
            IO.println("Undantag uppkoppling: " + e.getLocalizedMessage());
            return;
        }
    }

    public Magazines Sök(){
         IO.println("Säg titeln för tidningen som du vill hitta: ");
        String titel = IO.readln().trim().toLowerCase();
        try {
            // Skicakr ett GET anrop till servern för att hämta en bok med viss titel
            HttpResponse<Magazines> response = Unirest.get(Main.baseURL+"/magazines/titel/"+titel)
            // försöker omvandla svaret till ett Books-object
            .asObject(Magazines.class);
            
            // kollar om servern svarade "200 OK" 
            if (response.getStatus()==200) {
                // hämtar själva body från servern
                Magazines magazine = response.getBody();
                //skriver ut boken
                IO.println("Den boken du hittade är "+magazine);
                return magazine;
            }
            IO.println("Boken hittades inte.");
            return null;
        } catch (UnirestException e) {
            IO.println("Fel vid sökning: "+e.getMessage());
            return null;
        }
    }

    public void TaBort(){
         // hitta boken som ska readeras
        Magazines magazine = Sök();

        // om boken inte finns
        if (magazine == null) {
            return;
        }
        // hämtar id för boken
        String id = magazine.getId();

        int deleteStatus;

        // ta bort från servern
        try {
            // skicka ett DELETE-anrop och hämta bara statuskoden (vi förväntar oss ingen body)
            deleteStatus = Unirest.delete(Main.baseURL + "/Books/" + id)
                    .asEmpty() // Skickar INGEN body
                    .getStatus();
        } catch (UnirestException e) {
            IO.println("Undantag uppkoppling: " + e.getLocalizedMessage());
            return;
        }
        if (deleteStatus == 200) {
            IO.println("Inlägget med TITELN " + id + " är borttaget");
        } else if (deleteStatus == 204) {
            IO.println("Inlägget fanns inte kvar / Inget innehåll på titeln=" + id);
        } else {
            IO.println("Något gick fel. Statuskod: " + deleteStatus);
        }
    }
}
