import java.util.ArrayList;
import java.util.List;

// Observer Pattern Components

// Subject: Define the subject (publisher) that will produce events or data.
interface WeatherDataSubject {
    void registerObserver(WeatherObserver observer);
    void removeObserver(WeatherObserver observer);
    void notifyObservers();
}

// Observer: Create observers that want to be notified when events occur.
interface WeatherObserver {
    void update(float temperature, float humidity, float pressure);
}

// Concrete Subject: Implement a specific subject that will produce events.
class WeatherStation implements WeatherDataSubject {
    private List<WeatherObserver> observers = new ArrayList<>();
    private float temperature;
    private float humidity;
    private float pressure;


    @Override
    public void registerObserver(WeatherObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(WeatherObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (WeatherObserver observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    private void measurementsChanged() {
        notifyObservers();
    }
}

// Concrete Observer: Implement observers that react to specific events.
class Display implements WeatherObserver {
    private WeatherDataSubject weatherData;


    public Display(WeatherDataSubject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        System.out.println("Temperature Sensor: Measure temperature = " + String.format("%.1f", temperature) + "°C");
        System.out.println("Humidity Sensor: Measured humidity = " + String.format("%.0f", humidity) + "%");
        System.out.println("Pressure Sensor: Measured pressure = " + String.format("%.1f", pressure) + " hPa");
        System.out.println("\nNew weather notifications: ");
    }
}

// Factory Pattern Components

// Product: Define the product interface.
interface WeatherSensor {
    void measure();
}

// Concrete Product(s): Create concrete implementations of products.
class TemperatureSensor implements WeatherSensor {
    @Override
    public void measure() {
        // Simulate temperature measurement
        float temperature = (float) (Math.random() * 30 + 10);
        System.out.println("Temperature Sensor: Measure temperature = " + String.format("%.1f", temperature) + "°C");
    }
}

class HumiditySensor implements WeatherSensor {
    @Override
    public void measure() {
        // Simulate humidity measurement
        float humidity = (float) (Math.random() * 80 + 20);
        System.out.println("Humidity Sensor: Measured humidity = " + String.format("%.0f", humidity) + "%");
    }
}

class PressureSensor implements WeatherSensor {
    @Override
    public void measure() {
        // Simulate pressure measurement
        float pressure = (float) (Math.random() * 10 + 990);
        System.out.println("Pressure Sensor: Measured pressure = " + String.format("%.1f", pressure) + " hPa");
    }
}

// Factory: Design the factory interface.
interface WeatherSensorFactory {
    WeatherSensor createSensor();
}

// Concrete Factory: Implement concrete factories that create specific products.
class TemperatureSensorFactory implements WeatherSensorFactory {
    @Override
    public WeatherSensor createSensor() {
        return new TemperatureSensor();
    }
}

class HumiditySensorFactory implements WeatherSensorFactory {
    @Override
    public WeatherSensor createSensor() {
        return new HumiditySensor();
    }
}

class PressureSensorFactory implements WeatherSensorFactory {
    @Override
    public WeatherSensor createSensor() {
        return new PressureSensor();
    }
}


