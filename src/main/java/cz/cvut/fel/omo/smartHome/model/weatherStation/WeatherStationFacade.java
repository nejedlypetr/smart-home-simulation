package cz.cvut.fel.omo.smartHome.model.weatherStation;

public class WeatherStationFacade {
    private final Thermometer thermometer;
    private final Barometer barometer;
    private final Hygrometer hygrometer;

    public WeatherStationFacade() {
        thermometer = new Thermometer();
        barometer = new Barometer();
        hygrometer = new Hygrometer();
    }

    public void getWeatherReport() {
        int temperature = thermometer.measureTemperature();
        int pressure = barometer.measurePressure();
        int humidity = hygrometer.measureHumidity();
        System.out.println("The weather station reports a temperature of " + temperature +" °C, with atmospheric pressure at " + pressure + " hPa and humidity at " + humidity + " %.\n");
    }
}
