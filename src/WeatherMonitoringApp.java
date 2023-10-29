public class WeatherMonitoringApp {
    public static void main(String[] args) {
        // Observer Pattern
        WeatherStation weatherStation = new WeatherStation();
        Display display = new Display(weatherStation);

        // Simulate weather data changes
        weatherStation.setMeasurements(25.0f, 60.0f, 1010.0f);

        // Factory Pattern
        WeatherSensorFactory temperatureSensorFactory = new TemperatureSensorFactory();
        WeatherSensorFactory humiditySensorFactory = new HumiditySensorFactory();
        WeatherSensorFactory pressureSensorFactory = new PressureSensorFactory();

        WeatherSensor temperatureSensor = temperatureSensorFactory.createSensor();
        WeatherSensor humiditySensor = humiditySensorFactory.createSensor();
        WeatherSensor pressureSensor = pressureSensorFactory.createSensor();

        // Simulate sensor measurements
        temperatureSensor.measure();
        humiditySensor.measure();
        pressureSensor.measure();
    }
}