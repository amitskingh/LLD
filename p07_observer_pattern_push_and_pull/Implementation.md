# Implementation Examples

## Example 1 — Push Model (YouTube)

### Scenario

When a new video is uploaded, the channel sends the video title directly to every subscriber.

### Flow

```text
Upload Video

↓

YouTubeChannel

↓

update(videoTitle)

↓

Subscriber
```

### Observer

```java
interface Observer {
    void update(String videoTitle);
}
```

### Subject

```java
interface Subject {

    void subscribe(Observer observer);

    void unsubscribe(Observer observer);

    void notifySubscribers();

}
```

### Subscriber

```java
class Subscriber implements Observer {

    private String name;

    Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String videoTitle) {

        System.out.println(
            name + " received : " + videoTitle
        );

    }

}
```

### YouTubeChannel

```java
class YouTubeChannel implements Subject {

    private List<Observer> observers = new ArrayList<>();

    private String latestVideo;

    @Override
    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifySubscribers() {

        for (Observer observer : observers) {
            observer.update(latestVideo);
        }

    }

    public void uploadVideo(String title) {

        latestVideo = title;

        notifySubscribers();

    }

}
```

### Client

```java
public class Main {

    public static void main(String[] args) {

        YouTubeChannel channel = new YouTubeChannel();

        channel.subscribe(new Subscriber("Alice"));
        channel.subscribe(new Subscriber("Bob"));

        channel.uploadVideo("Observer Pattern");

    }

}
```

### Output

```text
Alice received : Observer Pattern
Bob received : Observer Pattern
```

---

## Example 2 — Pull Model (Modern)

### Scenario

The channel does **not** send the video title.

It only says:

> "I have changed."

The subscriber decides what information to fetch.

### Flow

```text
Upload Video

↓

YouTubeChannel

↓

update(this)

↓

Subscriber

↓

channel.getLatestVideo()
```

### Observer

```java
interface Observer {

    void update(Subject subject);

}
```

### Subject

```java
interface Subject {

    void subscribe(Observer observer);

    void unsubscribe(Observer observer);

    void notifySubscribers();

}
```

### Subscriber

```java
class Subscriber implements Observer {

    private String name;

    Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(Subject subject) {

        YouTubeChannel channel =
            (YouTubeChannel) subject;

        System.out.println(
            name + " received : "
            + channel.getLatestVideo()
        );

    }

}
```

### YouTubeChannel

```java
class YouTubeChannel implements Subject {

    private List<Observer> observers =
        new ArrayList<>();

    private String latestVideo;

    @Override
    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifySubscribers() {

        for (Observer observer : observers) {
            observer.update(this);
        }

    }

    public void uploadVideo(String title) {

        latestVideo = title;

        notifySubscribers();

    }

    public String getLatestVideo() {
        return latestVideo;
    }

}
```

### Client

```java
public class Main {

    public static void main(String[] args) {

        YouTubeChannel channel = new YouTubeChannel();

        channel.subscribe(new Subscriber("Alice"));
        channel.subscribe(new Subscriber("Bob"));

        channel.uploadVideo("Observer Pattern");

    }

}
```

### Output

```text
Alice received : Observer Pattern
Bob received : Observer Pattern
```

---

# Push vs Pull (Implementation Difference)

| Push | Pull (Modern) |
|------|---------------|
| `update(videoTitle)` | `update(subject)` |
| Subject sends data | Subject sends itself |
| Observer simply consumes data | Observer fetches required data |
| No getters required | Subject exposes getters |
| Good when every observer needs the same data | Good when observers need different data |

---

# Interview Tip

Remember the three signatures:

```java
// Push
update(data)
```

```java
// Classic Pull (GoF)
update()
```

```java
// Modern Pull
update(subject)
```

**Easy Memory Trick**

- **Push** → *"Here is the data."*
- **Classic Pull** → *"Something changed."*
- **Modern Pull** → *"Here I am. Fetch what you need."*