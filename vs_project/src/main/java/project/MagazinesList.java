package project;

/* 
Valentin fuenzalida ribbing
BooksList innehåller en metod för att hämta alla tidningar in i listan, lägg till ett tidning i servern, 
sök en tidning i servern, ta bort en tidning i servern och 
skriva ut tidningar i bokstavsordning beroende på titeln
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

// kunna lagra objekt i listor och ändra i arraylistor
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class MagazinesList implements SaveToFile {
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
        // omvandla json-text till i ArrayList med Magazines-objekt
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
            Lägg_Till_ResponseMagazines = Unirest.post(Main.baseURL + "magazines")
                    .header("Content-Type", "application/json") // VIktigt
                    .body(magazine) // Skickar data
                    .asString(); // Returnerar ett HTTPResponse<String>
        } catch (UnirestException e) {
            IO.println("Undantag uppkoppling: " + e.getLocalizedMessage());
            return;
        }
    }

    public String Sök() {
        // hämtar alla Magazines object och lägger de i en lista
        get_allMagazines();

        // frågar användaren för titeln för tidningen
        IO.println("Ange titel för tidningen som du vill hitta: ");
        String titel = IO.readln().trim().toLowerCase();

        // loopar igenom listMagazines för att hitta ett object med samma Titel
        for (Magazines magazine : listMagazines) {
            if (magazine.getTitle().toLowerCase().equals(titel)) {
                IO.println(magazine);
                return magazine.getId();
            }
        }

        IO.println("avstängda hittades inte.");
        return "";
    }

    public void TaBort() {
        Iterator<Magazines> it = listMagazines.iterator();
        // hitta kunden som ska ta borts
        String id = Sök();

        // loppar igenom listusers för att hitta ett objekt som har samma id som det id
        // jag fick från Sök() och sen ta bort detta objekt
        while (it.hasNext()) {
            Magazines magazines = it.next();
            if (magazines.getId().equals(id)) {
                it.remove();
                break;
            }
        }

        int deleteStatus;

        // ta bort från servern
        try {
            // skicka ett DELETE-anrop och hämta bara statuskoden (vi förväntar oss ingen
            // body)
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

    public void Sorterabokstavsordning() {
        // hämtar alla Books och lägger de i en lista
        get_allMagazines();
        // sorterar alla böcker i listan genom bokstavsordning
        Collections.sort(listMagazines);
        // loppa igenom hela arraylistan av böcker för att skriva ut varje objekt
        for (Magazines magazines : listMagazines) {
            IO.println(magazines);
        }
    }

    public void SorteraPublishedYear() {
        // Hämtar alla Books och lägger de i en lista
        get_allMagazines();

        // 1. Sortera listan först efter år, sedan efter Titel
        List<Magazines> sortedMagazinesYear = listMagazines.stream()
                .sorted(Comparator.comparingInt(Magazines::getPublishedYear)
                        .thenComparing(Magazines::getTitle)) // Sorterar titlar inom samma genre
                .collect(Collectors.toList());

                // 2. Skriv ut resultatet med rubriker för varje genre
                int currentYear = -1;

                for (Magazines m : sortedMagazinesYear) {

                    // Om tidningens år inte är samma som det förra, skriv en ny rubrik
                    if (m.getPublishedYear() != currentYear) {
                        currentYear = m.getPublishedYear();
                        IO.println("\n--- År: " + currentYear + " ---");
                    }

                    // Skriv ut boken
                    IO.println(m);
                }
    }

    public void SorteraCategory() {
        // Hämtar alla Böcker och lägger de i en lista
        get_allMagazines();
        // 1. Sortera listan först efter Genre, sedan efter Titel
        List<Magazines> sortedMagazinesCategory = listMagazines.stream()
                .sorted(Comparator.comparing(Magazines::getCatogory)
                        .thenComparing(Magazines::getTitle)) // Sorterar titlar inom samma genre
                .collect(Collectors.toList());

        // 2. Skriv ut resultatet med rubriker för varje genre
        String currentCategory = "";

        for (Magazines m : sortedMagazinesCategory) {
            // Om genren ändras, skriv ut en ny rubrik
            if (!m.getCatogory().equals(currentCategory)) {
                currentCategory = m.getCatogory();
                IO.println("\n--- Category: " + currentCategory.toUpperCase() + " ---");
            }

            // Skriv ut boken
            IO.println(m);
        }
    }

    public void FiltreraPublishedYear() {
        // Hämtar alla Böcker och lägger de i en lista
        get_allMagazines();
        // while loop tills du ha valt rätt
        boolean filtreraPublishedYear = true;
        while (filtreraPublishedYear) {
            // filtrerar ut en specifik författare
            IO.println("Säg året du vill filtrera ut: ");
            String Year = IO.readln();
            int publishedYear = 0;
            try {
                publishedYear = Integer.parseInt(Year);

                final int searchYear = publishedYear;

                List<Magazines> publishedYearfiltrera = listMagazines.stream()
                        .filter(p -> p.getPublishedYear() == searchYear)
                        .collect(Collectors.toList());
                

                if (publishedYearfiltrera.isEmpty()) {
                    IO.println("Ingen tidningar hittades från år"+publishedYear+", försök igen");
                    return;
                } else {
                    IO.println("Hittade böcker\n");
                    for (Magazines magazines : publishedYearfiltrera) {
                        IO.println("- " + magazines);
                    }
                    filtreraPublishedYear = false;
                }
                break;
            } catch (NumberFormatException e) {
                IO.println("fel inmattning. skriv ett år (t.ex. 2024)");
            }
        }
    }

    public void filtreraCategory() {
        // Hämtar alla Böcker och lägger de i en lista
        get_allMagazines();
        // while loop tills du ha valt rätt
        boolean filtreraCategory = true;
        while (filtreraCategory) {
            // Sortera efter en specifik genre
            IO.println(
                    "Säg Category du vill filtrera ut: ");
            String Category = IO.readln();
            List<Magazines> categoryfiltrera = listMagazines.stream()
                    .filter(c -> c.getCatogory().equalsIgnoreCase(Category))
                    .collect(Collectors.toList());
            if (categoryfiltrera.isEmpty()) {
                IO.println("Ingen tidning av den Category hittades, välj en annan");
                return;
            } else {
                IO.println("Hittade böcker\n");
                for (Magazines magazines : categoryfiltrera) {
                    IO.println("- " + magazines);
                }
                filtreraCategory = false;
            }
        }
    }

    

    public void save() {

    }

    public void read() {

    }
}
