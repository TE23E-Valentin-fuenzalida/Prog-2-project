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
        SuspendedUsersList listSuspendedUsers = new SuspendedUsersList();

        while (true) {

            String val = system.meny();

            switch (val) {
                case "1":
                    IO.println("Vill du hämta ut alla böcker, tidningar, kunder eller avstängda (b, t, k eller a)");
                    String svar = IO.readln();
                    if (svar == "b") {
                        listBooks.get_allBooks();
                    }
                    else if (svar == "t") {
                        listMagazines.get_allMagazines();
                    } else if (svar == "k") {
                        listUsers.get_allUsers();
                    } else if (svar == "a") {
                        listSuspendedUsers.get_allSuspendedUsers();
                    }
                    break;
                case "2":
                    IO.println("Vill du hämta ut en bok, tidning, kund eller avstängd (b, t, k eller a): ");
                    String svar1 = IO.readln().toLowerCase().trim();
                    if (svar1 == "b") {
                        listBooks.Sök();
                    }
                    else if (svar1 == "t") {
                        listMagazines.Sök();
                    } else if (svar1 == "k") {
                        listUsers.Sök();
                    } else if (svar1 == "a") {
                        listSuspendedUsers.Sök();
                    }
                    break;
                case "3":
                    IO.println("Vill du skapa en ny bok, tidning, kund eller avstängd (b, t, k eller a): ");
                    String svar2 = IO.readln().toLowerCase().trim();
                    if (svar2 == "b") {
                        listBooks.LäggTill();
                    } else if (svar2 == "t") {
                        listMagazines.LäggTill();
                    } else if (svar2 == "k") {
                        listUsers.LäggTill();
                    } else if (svar2 == "a") {
                        listSuspendedUsers.LäggTill();
                    }
                    break;
                case "4":
                    listUsers.Sök();
                    break;
                case "5":
                    IO.println("Vill du hita bok eller tidning (b eller t): ");
                    String svar3 = IO.readln().toLowerCase().trim();
                    if (svar3 == "b") {
                        listBooks.Sök();
                    }
                    else if (svar3 == "t") {
                        listMagazines.Sök();
                    }
                    break;
                case "6":
                    IO.println("vill du ta bort bok eller tidning (b eller t): ");
                    String svar4 = IO.readln().toLowerCase().trim();
                    if (svar4 == "b") {
                        listBooks.TaBort();
                    }
                    else if (svar4 == "t") {
                        listMagazines.TaBort();
                    }
                    break;
                case "7":
                    IO.println("Vill du ta bort en kund eller avstängd (k eller a): ");
                    String svar5 = IO.readln();
                    if (svar5 == "k") {
                        listUsers.TaBort();
                    }
                    else if (svar5 == "a") {
                        listSuspendedUsers.TaBort();
                    }
                    break;
                case "8":
                    break;
                default:
                    break;
            }

        }

    }
}