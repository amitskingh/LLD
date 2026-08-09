
> **The Subject passes itself to the observer, and the observer pulls whatever data it needs.**

---

# Weather Station Example (Pull Model - Modern)

## Flow

```text
Weather Changes
       │
       ▼
WeatherStation
       │
notifyObservers()
       │
       ▼
observer.update(this)
       │
       ▼
Observer pulls data using getters
```

---

# Step 1: Observer Interface

```java
interface Observer {

    void update(Subject subject);

}
```

Notice:

* No temperature
* No humidity
* Only the `Subject`

---

# Step 2: Subject Interface

```java
interface Subject {

    void attach(Observer observer);

    void detach(Observer observer);

    void notifyObservers();

}
```

---

# Step 3: WeatherStation

```java
import java.util.ArrayList;
import java.util.List;

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
            observer.update(this);      // <-- Pass itself
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

Notice this line:

```java
observer.update(this);
```

`this` means:

> "I am the WeatherStation that changed."

---

# Step 4: MobileDisplay

```java
class MobileDisplay implements Observer {

    @Override
    public void update(Subject subject) {

        WeatherStation station = (WeatherStation) subject;

        System.out.println(
            "Mobile : Temp = " +
            station.getTemperature()
        );

    }

}
```

The observer decides what it wants.

Here it only wants the temperature.

---

# Step 5: LaptopDisplay

```java
class LaptopDisplay implements Observer {

    @Override
    public void update(Subject subject) {

        WeatherStation station = (WeatherStation) subject;

        System.out.println(
            "Laptop : Humidity = " +
            station.getHumidity()
        );

    }

}
```

Notice something interesting.

The laptop ignores the temperature completely.

---

# Main

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

---

# Output

```text
Mobile : Temp = 35

Laptop : Humidity = 80
```

---

# Why is this better?

Compare all three versions.

## Push

```java
observer.update(temp, humidity);
```

Subject decides the data.

---

## Classic Pull

```java
observer.update();
```

Observer already stores:

```java
private WeatherStation station;
```

---

## Modern Pull

```java
observer.update(this);
```

Observer gets the subject only when notified.

No permanent reference required.

---

# Why do we cast?

Inside `update()` we receive:

```java
Subject subject
```

But `Subject` only knows:

```java
attach()

detach()

notifyObservers()
```

It doesn't know about:

```java
getTemperature()
```

because that's specific to `WeatherStation`.

So we cast:

```java
WeatherStation station = (WeatherStation) subject;
```

Now we can call:

```java
station.getTemperature();
station.getHumidity();
```

---

# One improvement (Avoid the cast)

Many developers introduce a more specific subject interface.

```java
interface WeatherSubject extends Subject {

    int getTemperature();

    int getHumidity();

}
```

Now:

```java
class WeatherStation implements WeatherSubject
```

and

```java
interface Observer {

    void update(WeatherSubject subject);

}
```

Inside the observer:

```java
public void update(WeatherSubject subject) {

    System.out.println(subject.getTemperature());

}
```

No casting required.

This is more type-safe and is often preferred in larger codebases.

---

# Interview Comparison

| Push                     | Pull (Classic)                 | Pull (Modern)                  |
| ------------------------ | ------------------------------ | ------------------------------ |
| `update(temp, humidity)` | `update()`                     | `update(subject)`              |
| Subject sends data       | Observer has subject reference | Subject passes itself          |
| No getters needed        | Observer uses stored reference | Observer uses passed reference |
| Simple notifications     | Original GoF style             | Common modern implementation   |
