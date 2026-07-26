# Observer Pattern (Behavioral Design Pattern)

## Definition

> **The Observer Pattern defines a one-to-many dependency between objects so that when one object changes its state, all its dependent objects are notified automatically.**

### Simple Definition

One object (**Subject**) maintains a list of interested objects (**Observers**). Whenever its state changes, it notifies all registered observers.

---

# Why Do We Need It?

Without the Observer Pattern:

```java
class YouTubeChannel {

    void uploadVideo() {
        alice.notify();
        bob.notify();
        charlie.notify();
        david.notify();
    }

}
```

### Problems

- Tight coupling
- Difficult to add new subscribers
- Violates **Open/Closed Principle (OCP)**
- Poor maintainability

With Observer Pattern:

```text
YouTube Channel
      │
Maintains List
      │
Notify Everyone
```

The Subject doesn't care who the observers are. It only knows they implement the `Observer` interface.

---

# Real World Examples

| Subject | Observer |
|----------|----------|
| YouTube Channel | Subscribers |
| Instagram User | Followers |
| Weather Station | Mobile Apps |
| Stock Exchange | Investors |
| Chat Room | Connected Clients |
| Product | Price Alert Users |

---

# Participants

## 1. Subject (Publisher)

Responsible for:

- Register observers
- Remove observers
- Notify observers

Example:

- YouTube Channel
- Weather Station

---

## 2. Observer

Objects interested in receiving updates.

Examples:

- Subscriber
- Mobile App
- Email Service
- Dashboard

---

## 3. Concrete Subject

Actual implementation of the Subject.

Example:

```text
YouTubeChannel
WeatherStation
```

---

## 4. Concrete Observer

Actual implementation of Observer.

Example:

```text
Subscriber
MobileDisplay
EmailNotification
```

---

# UML Diagram

```text
                    +----------------------+
                    |      Subject         |
                    +----------------------+
                    | +attach()            |
                    | +detach()            |
                    | +notifyObservers()   |
                    +----------------------+
                              ▲
                              │
                    implements
                              │
                +-------------------------+
                |     WeatherStation      |
                +-------------------------+
                | -observers : List       |
                | -temperature            |
                | -humidity               |
                +-------------------------+

                              │
                         update(...)
                              ▼

                    +----------------------+
                    |      Observer        |
                    +----------------------+
                    | +update(...)         |
                    +----------------------+
                              ▲
                              │
                  ┌───────────┴───────────┐
                  │                       │
        +-------------------+   +-------------------+
        | MobileDisplay     |   | LaptopDisplay    |
        +-------------------+   +-------------------+
```

---

# Flow Diagram

```text
Client

↓

Create Subject

↓

Register Observers

↓

Subject State Changes

↓

notifyObservers()

↓

Observer.update()

↓

Observers React
```

---

# Push Model

## Idea

The **Subject pushes data** directly to every Observer.

### Flow

```text
WeatherStation

↓

update(temp, humidity)

↓

Observer
```

### Observer Interface

```java
interface Observer {
    void update(int temperature, int humidity);
}
```

### Characteristics

- Subject decides what data to send.
- Simple implementation.
- Good when all observers need the same data.

### Drawback

Sometimes unnecessary data is sent.

---

# Pull Model (Classic)

## Idea

The Subject only says:

> **"Something changed."**

Observer fetches required data.

### Flow

```text
WeatherStation

↓

update()

↓

Observer

↓

getTemperature()
```

### Observer Interface

```java
interface Observer {
    void update();
}
```

Observer already stores a reference to the Subject.

---

# Pull Model (Modern)

Instead of storing the Subject, it is passed during notification.

### Flow

```text
WeatherStation

↓

update(this)

↓

Observer

↓

subject.getTemperature()
```

### Observer Interface

```java
interface Observer {
    void update(Subject subject);
}
```

### Benefits

- No permanent Subject reference needed.
- More flexible.
- Common in modern implementations.

---

# Push vs Pull

| Push | Pull |
|------|------|
| Subject sends data | Observer fetches data |
| `update(data)` | `update()` / `update(subject)` |
| Simple | Flexible |
| Can send unnecessary data | Observer fetches only required data |

---

# Sequence Diagram

```text
Client
  │
  ▼
Create WeatherStation
  │
  ▼
Attach MobileDisplay
Attach LaptopDisplay
  │
  ▼
setWeather()
  │
  ▼
notifyObservers()
  │
  ├────────► update()
  │
  └────────► update()
```

---

# SOLID Principles Used

| Principle | Usage |
|-----------|-------|
| SRP | Subject manages observers, Observer reacts to updates |
| OCP | Add new observers without modifying Subject |
| DIP | Subject depends on `Observer` interface |
| ISP | Observer exposes only `update()` |
| LSP | Any concrete observer can replace another observer |

---

# Advantages

- Loose coupling
- Easy to add/remove observers
- Supports one-to-many communication
- Follows OCP and DIP
- Ideal for event-driven systems

---

# Disadvantages

- Too many observers may reduce performance.
- Debugging notification chains can be difficult.
- Forgetting to unsubscribe may cause memory leaks.

---

# Common Mistakes

### ❌ Depending on concrete observers

```java
List<Subscriber>
```

✔ Better

```java
List<Observer>
```

---

### ❌ Forgetting to unsubscribe

Unused observers remain registered, potentially causing memory leaks.

---

### ❌ Using Push when observers need different data

If every observer needs different information, prefer the Pull model.

---

### ❌ Using Java's built-in Observer

Avoid:

```java
java.util.Observer
java.util.Observable
```

Deprecated since **Java 9**.

Create your own interfaces instead.

---

# Interview Questions

### Why use an Observer interface?

To achieve loose coupling and follow DIP.

---

### Why maintain `List<Observer>`?

Allows polymorphism and adding new observer types without changing the Subject.

---

### Which SOLID principles are used?

- SRP
- OCP
- DIP
- ISP
- LSP

---

### Push vs Pull?

**Push**

> "Here is the updated data."

**Pull**

> "Something changed. Fetch what you need."

---

### Why is `java.util.Observer` deprecated?

- Based on inheritance (`Observable`)
- Less flexible
- Poor API design
- Doesn't align with modern design principles

---

### Is Observer synchronous?

Usually **Yes**.

Can also be asynchronous using:

- Kafka
- RabbitMQ
- Spring Events
- WebSockets

---

# Key Takeaways

```text
✔ Behavioural Design Pattern

✔ One Subject → Many Observers

✔ Subject maintains list of observers

✔ Loose Coupling

✔ Observer communicates via interfaces

✔ Push → Subject sends data

✔ Pull → Observer fetches data

✔ Modern Pull → update(subject)

✔ Follows SRP, OCP, DIP, ISP and LSP

✔ java.util.Observer is deprecated

✔ Used in event-driven architectures
```

---

# Memory Trick

```text
Subject
   │
Maintains List
   │
State Changes
   │
Notify Everyone
   │
Observers React
```

**Remember**

- **Push** → *"Here is the data."*
- **Pull** → *"Something changed. Come and get what you need."*