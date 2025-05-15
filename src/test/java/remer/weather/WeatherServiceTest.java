package remer.weather;

import com.andrewoid.apikeys.ApiKey;
import org.junit.jupiter.api.Test;
import remer.weather.json.WeatherResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeatherServiceTest
{
    @Test
    public void weatherNow()
    {
        // given
        WeatherService service = new WeatherServiceFactory().getService();
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();

        // when
        WeatherResponse response = service.weatherNow(
                "Edison,New Jersey",
                keyString,
                "imperial"
        ).blockingGet();

        // then
        assertTrue(response.main.temp > 0);
    }
}
