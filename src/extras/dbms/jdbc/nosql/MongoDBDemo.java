package extras.dbms.jdbc.nosql;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;

import extras.dbms.prettytable.PrettyTable;

import org.bson.Document;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MongoDBDemo {
    private static final MongoClient client = MongoClients.create("mongodb://localhost:27017");
    private static final MongoDatabase database = client.getDatabase("new_mongo1");
    private static final MongoCollection<Document> games = database.getCollection("Games");

    /*
    Document toy = new Document(“name”, “yoyo”).append(“ages”, new Document(“min”, 5));
    ObjectId id = toys.insertOne(toy).getInsertedId().asObjectId().getValue();
     */
    public static void insertGame(
            String gameName, Long gameSize, String genre
    ) {
        Document game = new Document("Name",gameName).append("Size", gameSize+" GB").append("Genre", genre.toUpperCase());
        InsertOneResult res = games.insertOne(game);
        System.out.println(
                res.wasAcknowledged() ? "Game has been inserted!" : "Game couldn't be inserted!"
        );
    }

    private static void deleteGame(String gameName) {
        DeleteResult res = games.deleteOne(
                Filters.eq("Name", gameName)
        );
        System.out.println(
                res.wasAcknowledged() ? "Game has been deleted!" : "Game couldn't be deleted!"
        );
    }

    public static void showGames() {
        PrettyTable pt = new PrettyTable("Name", "Size", "Genre");
        for (Document doc : games.find()) {
            pt.addRow(
                    doc.get("Name").toString(), doc.get("Size").toString(), doc.get("Genre").toString()
            );
        }
        pt.print();
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                System.out.println("--------------------------------");
                System.out.println("1. Insert Game");
                System.out.println("2. Show Game");
                System.out.println("3. Delete Game");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                int choice = Integer.parseInt(reader.readLine());
                System.out.println("--------------------------------");

                switch (choice) {
                    case 1:
                        System.out.println("Enter game name: ");
                        String name = reader.readLine();

                        System.out.println("Enter game size: ");
                        long size = Long.parseLong(reader.readLine());

                        System.out.println("Enter game genre: ");
                        String genre = reader.readLine();

                        insertGame(name, size, genre);
                        break;
                    case 2:
                        showGames();
                        break;
                    case 3:
                        System.out.println("Enter game name to delete: ");
                        deleteGame(
                                reader.readLine()
                        );
                        break;
                    case 4:
                        reader.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Wrong Input Try Again later.");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
