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

public class BooksList {

    private ArrayList<Books> listBooks = new ArrayList<>();
    Gson gson = new Gson();

    public void get_allBooks() {

        HttpResponse<String> get_all_ResponseBooks;
        // hämtar URL och lagrar den i servern
        try {
            get_all_ResponseBooks = Unirest.get(Main.baseURL + "books").asString();
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
        listBooks = gson.fromJson(get_all_bodybooks, postListType1);
    }

    public void LäggTill(){
        // frågar användaren av olika egenskaper
                    IO.println("titel: ");
                    String title = IO.readln();

                    IO.println("författare namn: ");
                    String författare = IO.readln();


                    IO.println("genre (Crime, Drama, Mystery, Adventure, Romance, Fantasy, Thriller eller Science Fiction): ");
                    String genre = IO.readln();

                    int pages = 0;
                    while (true) {
                        IO.println("Sidor");
                        String sidor = IO.readln();
                        try {
                            pages = Integer.parseInt(sidor);
                            break;
                        } catch (NumberFormatException e) {
                            IO.println("fel inmattning. skriv ett nummer");
                        }
                    }

                    String get_all_bodybooks = "";
                    // sätter id som en plus lenghten av hela arraylisten
                    String id = Integer.toString((get_all_bodybooks.length() + 1));

                    Books book = new Books(id, title, true, författare, genre, pages);

                    listBooks.add(book);
    }

    public void TaBort(){
        // frågar användaren om titlen för boken hen vill ta bort
        IO.println("Ange titeln för boken du vill ta bort");
    }
}
