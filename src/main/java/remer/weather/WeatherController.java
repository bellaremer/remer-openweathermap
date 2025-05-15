package remer.weather;

import com.andrewoid.apikeys.ApiKey;
import io.reactivex.rxjava3.schedulers.Schedulers;
import remer.weather.json.WeatherResponse;

import javax.swing.*;

public class WeatherController
{
    JLabel label;
    WeatherService service;
    ApiKey apiKey;

    public WeatherController(JLabel label, WeatherService service, ApiKey apiKey)
    {
        this.label = label;
        this.service = service;
        this.apiKey = apiKey;
    }

    public void display(String city)
    {
        service.weatherNow(city, apiKey.get(), "imperial")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.from(SwingUtilities::invokeLater))
                .subscribe(
                        this::handleResponse,
                        e -> label.setText("Error: " + e.getMessage())
                );
    }

    private void handleResponse(WeatherResponse response)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("Temp: ").append(response.main.temp).append("°F<br>");
        if (response.weather != null && !response.weather.isEmpty())
        {
            sb.append("Current conditions: ").append(response.weather.get(0).description).append("<br>");
        }
        sb.append("Temp min: ").append(response.main.tempMin).append("°F<br>");
        sb.append("Temp max: ").append(response.main.tempMax).append("°F");
        sb.append("</html>");
        label.setText(sb.toString());
    }
}
