package project;

/* 
Valentin fuenzalida ribbing
BooksList innehåller en metod för att hämta alla books in i listan, 
lägg till en bok i servern, sök en bok i servern, 
ta bort en bok i servern och skriva ut böcker i bokstavsordning beroende på titel
 */

//TODO fixa så att sortera med metoder går medhjälp av streams
//TODO använda hashmap så att jag kan söka efter saker snabbt

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
import java.util.Iterator;

public class BooksList implements SaveToFile{

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

    public void LäggTill() {
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

        HttpResponse<String> Lägg_Till_ResponseBooks;

        // sparar informationen på servern
        try {
            Lägg_Till_ResponseBooks = Unirest.post(Main.baseURL+"books")
                    .header("Content-Type", "application/json") // VIktigt
                    .body(book) // Skickar data
                    .asString(); // Returnerar ett HTTPResponse<String>
        } catch (UnirestException e) {
            IO.println("Undantag uppkoppling: " + e.getLocalizedMessage());
            return;
        }

    }

    public String Sök(){
         // hämtar alla users object och lägger de i en lista
        get_allBooks();

        // välj sorterings sätt
        

        // frågar användaren för titeln för boken
        IO.println("Ange titel för boken som du vill hitta: ");
        String titel = IO.readln().trim().toLowerCase();

        //loopar igenom ListBooks för att hitta ett object med samma titel
        for (Books book : listBooks) {
            if (book.getTitle().toLowerCase().equals(titel)) {
                IO.println(book);
                return book.getId();
            }
        }
        
        IO.println("avstängda hittades inte.");
        return "";
    }

    public void TaBort() {
    Iterator<Books> it = listBooks.iterator();
         // hitta kunden som ska ta borts
        String id = Sök();

        // loppar igenom listusers för att ta bort objektet med samma id
        while (it.hasNext()) {
            Books books = it.next();
            if (books.getId().equals(id)) {
                it.remove();
                break;
            }
        }

        int deleteStatus;

        // ta bort från servern
        try {
            // skicka ett DELETE-anrop och hämta bara statuskoden (vi förväntar oss ingen body)
            deleteStatus = Unirest.delete(Main.baseURL + "books/" + id)
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

    public void Sortera(){
        // hämtar alla Books och lägger de i en lista
        get_allBooks();
        // sorterar alla böcker i listan genom bokstavsordning
        Collections.sort(listBooks);
        // loppa igenom hela arraylistan av böcker för att skriva ut varje objekt
        for (Books books : listBooks) {
            IO.println(books);
        }
    }

    public void save(){

    }
    public void read(){

    }
}
