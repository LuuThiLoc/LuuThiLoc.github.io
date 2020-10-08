package excutiontest;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class RunApiTest {
    @Test()
    public void verifyGetRequest() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL("https://my-json-server.typicode.com/typicode/demo/posts/1").openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        System.out.println(String.format("Actual response code is %s", responseCode));
        Assert.assertEquals(responseCode, 200, String.format("Actual response code is %s", responseCode));

        String expectedResponseMessage = "{\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"Post 1\"\n" +
                "}\n";
        if(responseCode == 200) {
            String response = "";
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                response += scanner.nextLine();
                response += "\n";
            }
            System.out.println(response);
            Assert.assertEquals(response, expectedResponseMessage,
                    String.format("Actual response message is %s. Expected response message is %s.",
                            response, expectedResponseMessage));
        }
    }
}
