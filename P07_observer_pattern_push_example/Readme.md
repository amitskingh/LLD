# Scenario

A **WeatherStation** measures:

* Temperature
* Humidity

We have two displays:

* 📱 MobileDisplay
* 💻 LaptopDisplay

Whenever the weather changes, both displays should update.

---

# 1. Push Model

## Idea

> **WeatherStation pushes the updated data to all observers.**

```text
WeatherStation
      │
      ▼
update(temp, humidity)
      │
 ┌────┴────┐
 ▼         ▼
Mobile   Laptop
```

---

## Step 1: Observer

```java
interface Observer {
    void update(int temperature, int humidity);
}
```

---

## Step 2: Subject

```java
interface Subject {

    void attach(Observer observer);

    void detach(Observer observer);

    void notifyObservers();

}
```

---

## Step 3: WeatherStation

```java
import java.util.*;

class WeatherStation implements Subject {

    private List<Observer> observers = new ArrayList<>();

    private int temperature;
    private int humidity;

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {

        for (Observer observer : observers) {
            observer.update(temperature, humidity);
        }

    }

    public void setWeather(int temperature, int humidity) {

        this.temperature = temperature;
        this.humidity = humidity;

        notifyObservers();
    }

}
```

---

## Step 4: Mobile Display

```java
class MobileDisplay implements Observer {

    @Override
    public void update(int temperature, int humidity) {

        System.out.println(
            "Mobile : Temp = " + temperature +
            ", Humidity = " + humidity
        );

    }
}
```

---

## Step 5: Laptop Display

```java
class LaptopDisplay implements Observer {

    @Override
    public void update(int temperature, int humidity) {

        System.out.println(
            "Laptop : Temp = " + temperature +
            ", Humidity = " + humidity
        );

    }
}
```

---

## Main

```java
public class Main {

    public static void main(String[] args) {

        WeatherStation station = new WeatherStation();

        station.attach(new MobileDisplay());
        station.attach(new LaptopDisplay());

        station.setWeather(35, 80);

    }

}
```

### Output

```text
Mobile : Temp = 35, Humidity = 80

Laptop : Temp = 35, Humidity = 80
```

Notice:

The Subject sends:

```java
observer.update(temperature, humidity);
```

Everything is **pushed**.

---

# 2. Pull Model

## Idea

> **WeatherStation only says "I changed."**
>
> The observers ask for the data.

```text
WeatherStation
      │
      ▼
update()
      │
 ┌────┴────┐
 ▼         ▼
Mobile   Laptop
      │
      ▼
getTemperature()
getHumidity()
```

---

## Step 1: Observer

```java
interface Observer {

    void update();

}
```

Notice:

No parameters.

---

## Step 2: Subject

```java
interface Subject {

    void attach(Observer observer);

    void detach(Observer observer);

    void notifyObservers();

}
```

Same as before.

---

## Step 3: WeatherStation

```java
import java.util.*;

class WeatherStation implements Subject {

    private List<Observer> observers = new ArrayList<>();

    private int temperature;
    private int humidity;

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {

        for (Observer observer : observers) {
            observer.update();
        }

    }

    public void setWeather(int temperature, int humidity) {

        this.temperature = temperature;
        this.humidity = humidity;

        notifyObservers();

    }

    public int getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

}
```

Notice:

Instead of:

```java
observer.update(temperature, humidity);
```

We only call:

```java
observer.update();
```

---

## Step 4: Mobile Display

The observer keeps a reference to the WeatherStation.

```java
class MobileDisplay implements Observer {

    private WeatherStation station;

    public MobileDisplay(WeatherStation station) {
        this.station = station;
    }

    @Override
    public void update() {

        int temp = station.getTemperature();
        int humidity = station.getHumidity();

        System.out.println(
            "Mobile : Temp = " + temp +
            ", Humidity = " + humidity
        );

    }

}
```

---

## Step 5: Laptop Display

```java
class LaptopDisplay implements Observer {

    private WeatherStation station;

    public LaptopDisplay(WeatherStation station) {
        this.station = station;
    }

    @Override
    public void update() {

        int temp = station.getTemperature();
        int humidity = station.getHumidity();

        System.out.println(
            "Laptop : Temp = " + temp +
            ", Humidity = " + humidity
        );

    }

}
```

---

## Main

```java
public class Main {

    public static void main(String[] args) {

        WeatherStation station = new WeatherStation();

        station.attach(new MobileDisplay(station));
        station.attach(new LaptopDisplay(station));

        station.setWeather(35, 80);

    }

}
```

### Output

```text
Mobile : Temp = 35, Humidity = 80

Laptop : Temp = 35, Humidity = 80
```

---

# What's the Actual Difference?

Let's compare the two implementations side by side.

| Push Model                                   | Pull Model                          |
| -------------------------------------------- | ----------------------------------- |
| `update(temp, humidity)`                     | `update()`                          |
| Subject sends the data                       | Observer fetches the data           |
| No getters required                          | Subject exposes getters             |
| Observer doesn't need a reference to Subject | Observer stores a Subject reference |
| Subject knows what data to send              | Observer decides what data to read  |

---

# Visual Comparison

### Push Model

```text
WeatherStation
      │
      │ update(35, 80)
      ▼
MobileDisplay
```

The data travels **from the Subject to the Observer**.

---

### Pull Model

```text
WeatherStation
      │
      │ update()
      ▼
MobileDisplay
      │
      ├── getTemperature()
      └── getHumidity()
```

The notification is just a signal. The data is **pulled** by the observer.

---

# When Would You Prefer Pull?

Suppose `WeatherStation` also stores:

```text
Temperature
Humidity
Pressure
Wind Speed
UV Index
Rainfall
Air Quality
```

* 📱 MobileDisplay only shows **Temperature**.
* 🌾 FarmerDisplay only needs **Rainfall**.
* ✈️ AirlineDisplay only needs **Wind Speed** and **Pressure**.

With the **Push Model**, you'd either send **everything** to everyone or create multiple `update(...)` signatures.

With the **Pull Model**, every observer simply asks for the data it needs.

---

## Interview Rule

A good way to remember the difference is:

* **Push** → **"Here is the data."**
* **Pull** → **"Something changed. Come and get what you need."**

That's the essence of the two models. Once you understand that, any UML diagram or implementation you see will make sense.
