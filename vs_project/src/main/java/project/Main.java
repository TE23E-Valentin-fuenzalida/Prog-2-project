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
    // baseURL
    public static final String baseURL = "http://10.151.168.5:3148/";
    void main() {

        // ArrayLists
        ArrayList<Magazines> magazinesLista = new ArrayList<>();
        ArrayList<Books> bokLista = new ArrayList<>();

        // URL för books och magazines
        String booksURL = "http://10.151.168.5:3148/books";
        String magazineURL = "http://10.151.168.5:3148/magazines";
        Gson gson = new Gson();

        IO.println("Startar JSON klient");

        while (true) {

            // meny och användare svarar på den
            IO.println(
                    """
                            välj vad du vill göra
                                1. Hämta böcker.
                                2. Hämta tidningar.
                                3. Skriva ut hämtade böcker eller tidningar på skärmen
                                4. Lägg till bok.
                                5. Lägg till tidning.
                                6. Avsluta
                                """);
            String val = IO.readln();

            switch (val) {
                case "1":
                    break;
                case "2":
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
                    else{
                        IO.println("Du har inte hämtat något från i lager");
                    }
                    break;
                case "4":
                    break;
                case "5":
                    break;
                default:
                    break;
            }

        }

    }
}