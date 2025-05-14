package remer.weather;

import com.andrewoid.apikeys.ApiKey;
import remer.weather.json.WeatherResponse;

import javax.swing.*;

public class WeatherFrame extends JFrame
{
    ApiKey apiKey = new ApiKey();
    String keyString = apiKey.get();

    public WeatherFrame(String city)
    {
        setTitle("Weather App");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Loading weather...", SwingConstants.CENTER);
        add(label);

        // Fetch weather and update label
        new Thread(() -> {
            try
            {
                WeatherService service = new WeatherServiceFactory().getService();
                WeatherResponse response = service.weatherNow(city, keyString, "imperial").blockingGet();

                SwingUtilities.invokeLater(() ->
                        label.setText("Temperature in Edison, NJ: " + response.main.temp + "°F"));
            } catch (Exception e) {
                SwingUtilities.invokeLater(() ->
                        label.setText("Failed to fetch weather"));
            }
        }).start();
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            WeatherFrame frame = new WeatherFrame(
                    "Edison,New Jersey"
            );
            frame.setVisible(true);
        });
    }
}
