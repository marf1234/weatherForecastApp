package com.example.weatherforecastapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

import org.json.JSONObject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField city;

    @FXML
    private Button getData;

    @FXML
    private Text pressure;

    @FXML
    private Text temp_feel;

    @FXML
    private Text temp_info;

    @FXML
    private Text temp_max;

    @FXML
    private Text temp_min;

    @FXML
    void initialize() {
        getData.setOnAction(event -> {
            String getUserCity = city.getText().trim();
            String output = null;
            try {
                output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=1a806cb6c2c28e72648521acec80ea17");
                if (!output.isEmpty()) {
                    JSONObject obj = new JSONObject(output);
                    temp_info.setText("Temperature " + obj.getJSONObject("main").getDouble("temp"));
                    temp_feel.setText("Feeling " + obj.getJSONObject("main").getDouble("feels_like"));
                    temp_max.setText("Min temperature " + obj.getJSONObject("main").getDouble("temp_min"));
                    temp_min.setText("Max temperature " + obj.getJSONObject("main").getDouble("temp_max"));
                    pressure.setText("Pressure " + obj.getJSONObject("main").getDouble("pressure"));
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            System.out.println(output);
        });
    }

    private static String getUrlContent(String urlAdress) throws MalformedURLException {
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(urlAdress);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Такой город не найден");
        }
        return content.toString();
    }
}
