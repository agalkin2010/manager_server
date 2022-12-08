
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<Category> categories;
    static List<Information> clientData = new ArrayList();

    public static void main(String[] args) {

        CSVParser parser = new CSVParserBuilder()
                .withSeparator('\t')
                .build();

        try(CSVReader reader = new CSVReaderBuilder(new FileReader("categories.csv"))
                .withCSVParser(parser)
                .build();){

            ColumnPositionMappingStrategy<Category> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Category.class);
            strategy.setColumnMapping("name", "category");

            CsvToBean<Category> csv = new CsvToBeanBuilder<Category>(reader)
                    .withMappingStrategy(strategy)
                    .withSeparator('\t')
                    .build();

            categories = csv.parse();

        }
        catch (RuntimeException | IOException e){
            e.printStackTrace();
        }

        Statistic statistic = new Statistic(categories, clientData);

        try (ServerSocket serverSocket = new ServerSocket(8989);) { // стартуем сервер один(!) раз
            System.out.println("Server started...");
            while (true) { // в цикле(!) принимаем подключения
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                ) {
                    System.out.println("New connection accepted");
                    String infoClient = in.readLine();

                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();

                    Information info = gson.fromJson(infoClient, Information.class);
                    statistic.addPosition(info);

                    String jsonRes = statistic.getJsonStatistic();

                    out.println(String.format("Json result: %s", jsonRes));
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }

}
