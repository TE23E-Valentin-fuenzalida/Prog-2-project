package project;
/* 
Valentin fuenzalida ribbing
Main innehåller import saker, URL för servern, menyn
*/

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

public class Main {
    void main() {

        // ArrayLists
        ArrayList<Magazines> magazinesLista = new ArrayList<>();
        ArrayList<Books> bokLista = new ArrayList<>();

        // baseURL för webbsidan
        String baseURL = "http://10.151.168.5:3148/";
        String booksURL = "http://10.151.168.5:3148/books";
        String magazineURL = "http://10.151.168.5:3148/magazines";
        Gson gson = new Gson();

        IO.println("Startar JSON klient");

        while (true) {

            // meny och användare svarar på den
            IO.println(
                    """
                            välj vad du vill göra
                                1. Hämta böcker (hämta böcker från Servern och lagra lokalt i ex ArrayList).
                                2. Hämta tidningar (hämta tidningar från Servern och lagra lokalt i ex ArrayList).
                                3. Skriva ut hämtade böcker eller tidningar på skärmen
                                4. Lägg till bok, skapa ett bokobjekt där användaren får mata in titel, författare, genre, pages (antal sidor) och spara den i en Samling (ex ArrayList).
                                5. Lägg till tidning, skapa ett tidningsobjekt där användaren får mata in titel, författare, genre, pages (antal sidor) etc och spara den i en Samling (exArrayList).
                                6. Avsluta
                                """);
            String val = IO.readln();

            switch (val) {
                case "1":
                    HttpResponse<String> get_all_ResponseBooks;
                    try {
                        get_all_ResponseBooks = Unirest.get(booksURL).asString();
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
                    bokLista = gson.fromJson(get_all_bodybooks, postListType1);

                    break;
                case "2":
                    HttpResponse<String> get_all_ResponseMagazines;
                    try {
                        get_all_ResponseMagazines = Unirest.get(magazineURL).asString();
                    } catch (Exception e) {
                        IO.println("Undantag uppkoppling " + e.getLocalizedMessage());
                        return;
                    }
                    // kontrollera om servern svarade med "200 OK"
                    status = get_all_ResponseMagazines.getStatus();
                    if (status != 200) {
                        IO.println("Fel från servern, statuskod: " + status);
                        return;
                    }
                    // gör om resposnseMagazines till en String
                    String get_all_bodymagazines = get_all_ResponseMagazines.getBody();
                    // omvandla json-text till i ArrayList med bok-objekt
                    Type postListType2 = new TypeToken<ArrayList<Magazines>>() {
                    }.getType();
                    magazinesLista = gson.fromJson(get_all_bodymagazines, postListType2);
                    break;
                case "3":
                    IO.println("vill du skriva ut books eller magazines (b eller m): ");
                    String svar = IO.readln();

                    if (svar.equalsIgnoreCase("b")) {
                        for (Books boks : bokLista) {
                            IO.print(boks);
                        }
                    }
                    if (svar.equalsIgnoreCase("m")) {
                        for (Magazines magazines : magazinesLista) {
                            IO.print(magazines);
                        }
                    }
                    break;

                default:
                    break;
            }

        }

    }
}