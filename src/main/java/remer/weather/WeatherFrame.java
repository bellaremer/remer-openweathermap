package remer.weather;

import com.andrewoid.apikeys.ApiKey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WeatherFrame extends JFrame
{
    JLabel label = new JLabel("Loading weather...", SwingConstants.CENTER);
    JTextField cityField = new JTextField("Edison", 20);

    public WeatherFrame()
    {
        setTitle("Weather App");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("City:"));
        topPanel.add(cityField);

        add(topPanel, BorderLayout.NORTH);
        add(label, BorderLayout.CENTER);

        ApiKey apiKey = new ApiKey();
        WeatherService service = new WeatherServiceFactory().getService();
        WeatherController controller = new WeatherController(label, service, apiKey);

        // Fetch for default city
        controller.display(cityField.getText());

        // Listen for Enter key
        cityField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    controller.display(cityField.getText());
                }
            }
        });
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            WeatherFrame frame = new WeatherFrame();
            frame.setVisible(true);
        });
    }
}
