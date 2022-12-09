import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import stat.Information;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client1 {

    public static void main(String[] args) {

        try(Socket client = new Socket("localhost", 8989);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);){

            Information info = new Information("булка", "2022.02.08", 100l);

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();


            out.println(gson.toJson(info));
            System.out.println("FromServer> " + in.readLine());
        }catch(IOException e){
            e.printStackTrace();
        }

    }

}
