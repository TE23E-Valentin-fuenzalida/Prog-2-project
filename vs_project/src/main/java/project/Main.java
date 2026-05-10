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

import org.apache.http.impl.client.BasicCookieStore;

public class Main {
    // baseURL
/*     public static final String baseURL = "http://10.151.168.5:3148/"; */
    // baseUrl om jag är hemma
       public static final String baseURL = "http://localhost:3000/";
    void main() {

        // ArrayLists
        ArrayList<Magazines> magazinesLista = new ArrayList<>();
        ArrayList<Books> bokLista = new ArrayList<>();
        // skapar ett Gson object
        Gson gson = new Gson();

        IO.println("Startar JSON klient");

        // skapar alla object av klasserna som jag använder
        Biblotekssystem system = new Biblotekssystem();
        UsersList listUsers = new UsersList();
        MagazinesList listMagazines = new MagazinesList();
        BooksList listBooks = new BooksList();

        while (true) {

            String val = system.meny();

            switch (val) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    listUsers.Sök();
                    break;
                case "4":
                    IO.println("Vill du hita bok eller tidning (b eller t): ");
                    String svar = IO.readln();
                    if (svar == "b") {
                        listBooks.Sök();
                    }
                    else if (svar == "t") {
                        listMagazines.Sök();
                    }
                    break;
                case "5":
                    break;
                default:
                    break;
            }

        }

    }
}