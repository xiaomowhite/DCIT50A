import java.util.List;

public class SmartHomeMain {
    public static void main(String[] args) {

        Device aircon = new AirConditioner();
        LampShade lamp = new LampShade("Lamp", "OFF", 100, "Warm White");
        LampShade lamp2 = new LampShade(lamp);
        Device tv = new Television("Samsung TV", "OFF", 1, 10);
        MicrowaveOven oven = new MicrowaveOven();

        List<Device> homeDevices = List.of(aircon, lamp, lamp2, tv, oven);

        Device.deviceController(homeDevices, true);

        Device.countONDevices(homeDevices);

        Device.deviceController(homeDevices, false);

        oven.turnOn();
        oven.setTemperature("High");

        Device.countONDevices(homeDevices);


    }
}

abstract class Device {
    private String status;
    private String deviceName;

    public Device (String deviceName, String status) {
        this.status = status;
        this.deviceName = deviceName;
    }

    public Device () {
        this.status = "OFF";
        this.deviceName = "Device";
    }

    public void turnOn() {
        this.status = "ON";
    }

    public void turnOFF() {
        this.status = "OFF";
    }

    public abstract void deviceStatus();

    public String getDeviceName() {
        return deviceName;
    }

    public String getStatus() {
        return status;
    }

    public static void deviceController (List<Device> devices, boolean turnON) {
        for (Device device : devices) {
            if (turnON) {
                device.turnOn();
            } else {
                device.turnOFF();
            }
            device.deviceStatus();
            System.out.println();
        }
    }

    public static void countONDevices (List<Device> devices) {
        int count = 0;
        for (Device device : devices) {
            if (device.getStatus() == "ON") {
                count++;
            }
        }
        System.out.println("The current number of powered ON devices is: " + count);
        System.out.println();
    }
}

class AirConditioner extends Device{
    private int fanSpeed;
    private double temperature;

    public AirConditioner () {
        super();
        this.fanSpeed = 3;
        this.temperature = 24;
    }

    public AirConditioner (String deviceName, String status, int fanSpeed, double temperature) {
        super(deviceName, status);
        this.fanSpeed = fanSpeed;
        this.temperature = temperature;
    }

    public void setFanSpeed(int fanSpeed) {
        this.fanSpeed = fanSpeed;
        System.out.println("Fan speed set to: " + fanSpeed);
        System.out.println();
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        System.out.println("Temperature set to: " + temperature + "°C");
        System.out.println();
    }

    @Override
    public void deviceStatus() {
        if(getStatus() == "ON") {
            System.out.println("The " + getDeviceName() + " is ON");
            System.out.println(" Fan Speed: " + fanSpeed);
            System.out.println(" Temperature: " + temperature + "°C");
        } else {
            System.out.println("The " + getDeviceName() + " is OFF");
        }

    }
}

class LampShade extends Device {
    private int brightness;
    private String lightColor;

    public LampShade(String deviceName, String status, int brightness, String lightColor) {
        super(deviceName, status);
        this.brightness = brightness;
        this.lightColor = lightColor;
    }

    public LampShade(LampShade other) {
        super(other.getDeviceName(), other.getStatus());
        this.brightness = other.brightness;
        this.lightColor = other.lightColor;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
        System.out.println("Brightness set to: " + brightness);
        System.out.println();
    }

    public void setLightColor(String lightColor) {
        this.lightColor = lightColor;
        System.out.println("Light Color set to: " + lightColor);
        System.out.println();
    }

    @Override
    public void deviceStatus() {
        if(getStatus() == "ON") {
            System.out.println("The " + getDeviceName() + " is ON");
            System.out.println(" Brightness: " + brightness);
            System.out.println(" Light Color: " + lightColor);
        } else {
            System.out.println("The " + getDeviceName() + " is OFF");
        }

    }

}

class Television extends Device{
    private int channel;
    private int volume;

    public Television (String deviceName, String status, int channel, int volume) {
        super(deviceName, status);
        this.channel = channel;
        this.volume = volume;
    }

    public void setChannel(int channel) {
        this.channel = channel;
        System.out.println("Channel set to: " + channel);
        System.out.println();
    }

    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("Volume set to: " + volume + "%");
        System.out.println();
    }

    @Override
    public void deviceStatus() {
        if(getStatus() == "ON") {
            System.out.println("The " + getDeviceName() + " is ON");
            System.out.println(" channel: " + channel);
            System.out.println(" Volume: " + volume + "%");
        } else {
            System.out.println("The " + getDeviceName() + " is OFF");
        }

    }
}

class MicrowaveOven extends Device{
    private int timer;
    private String temperature;

    public MicrowaveOven () {
        super();
        timer = 30;
        temperature = "low";
    }

    public void setTimer(int timer) {
        this.timer = timer;
        System.out.println("Timer set to: " + timer);
        System.out.println();
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
        System.out.println("Temperature set to: " + temperature);
        System.out.println();
    }

    @Override
    public void deviceStatus() {
        if(getStatus() == "ON") {
            System.out.println("The " + getDeviceName() + " is ON");
            System.out.println(" Timer: " + timer);
            System.out.println(" Temperature: " + temperature);
        } else {
            System.out.println("The " + getDeviceName() + " is OFF");
        }

    }
}
