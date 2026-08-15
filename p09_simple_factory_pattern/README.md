# Simple Factory Pattern

## Creational Design Pattern

> **Simple Factory centralizes object creation in one place so the client doesn't need to directly create concrete objects.**

### Easy Definition

> **Instead of the client using `new` for different concrete classes, it asks a Factory to create the required object.**

---

# 1. Problem

Suppose we have different types of notifications:

```text
EmailNotification
SMSNotification
PushNotification
```

All follow the same contract:

```java
interface Notification {

    void send();

}
```

Without a Factory:

```java
Notification notification;

if (type.equals("EMAIL")) {
    notification = new EmailNotification();

} else if (type.equals("SMS")) {
    notification = new SMSNotification();

} else {
    notification = new PushNotification();
}

notification.send();
```

The client contains the object-creation logic.

As the number of implementations grows, this creation logic gets repeated in different places.

---

# 2. Solution

Move the creation logic into a dedicated Factory.

```text
Client
   │
   ▼
NotificationFactory
   │
   ├── EmailNotification
   ├── SMSNotification
   └── PushNotification
```

The client now asks:

```java
Notification notification =
    NotificationFactory.create("EMAIL");
```

instead of:

```java
new EmailNotification();
```

---

# 3. Main Components

## Product

Common interface:

```java
interface Notification {

    void send();

}
```

---

## Concrete Products

```text
EmailNotification
SMSNotification
PushNotification
```

Each implements:

```java
Notification
```

---

## Factory

Responsible for deciding which concrete object to create.

```java
class NotificationFactory {

    static Notification create(String type) {

        if (type.equals("EMAIL")) {
            return new EmailNotification();
        }

        if (type.equals("SMS")) {
            return new SMSNotification();
        }

        if (type.equals("PUSH")) {
            return new PushNotification();
        }

        throw new IllegalArgumentException(
            "Unknown notification type"
        );
    }
}
```

---

# 4. Client

```java
public class Main {

    public static void main(String[] args) {

        Notification notification =
            NotificationFactory.create("EMAIL");

        notification.send();
    }
}
```

The client only needs to know:

```text
Notification
NotificationFactory
```

It doesn't need to directly instantiate:

```text
EmailNotification
SMSNotification
PushNotification
```

---

# 5. Flow

```text
Client
  │
  │ create("EMAIL")
  ▼
NotificationFactory
  │
  │ new EmailNotification()
  ▼
EmailNotification
  │
  │ send()
  ▼
Output
```

---

# 6. What Problem Does Simple Factory Solve?

### Without Factory

The client knows:

```text
How to create Email
How to create SMS
How to create Push
```

### With Factory

The client says:

```text
"I need an Email Notification."
```

The Factory decides:

```text
"Okay, I'll create EmailNotification."
```

So:

> **Simple Factory separates object creation from object usage.**

---

# 7. Why Return the Interface?

Factory should generally return:

```java
Notification
```

instead of:

```java
EmailNotification
```

because the client should depend on the abstraction.

```java
Notification notification =
    NotificationFactory.create("EMAIL");
```

The actual object can be:

```text
EmailNotification
SMSNotification
PushNotification
```

This uses **polymorphism**.

---

# 8. UML

```text
                         +-------------------+
                         |   Notification    |
                         |    <<interface>>  |
                         +-------------------+
                         | + send()          |
                         +-------------------+
                           ▲       ▲       ▲
                           │       │       │
                  implements│       │       │
                           │       │       │
                    +------+  +----+---+  +------+
                    | Email|  |  SMS   |  | Push |
                    +------+  +--------+  +------+

                         ▲
                         │ creates
                         │
                +----------------------+
                | NotificationFactory  |
                +----------------------+
                | + create(type)       |
                +----------------------+
```

---

# 9. Simple Factory and `new`

The Factory does **not eliminate `new`**.

It moves it.

### Before

```text
Client
  │
  ├── new EmailNotification()
  ├── new SMSNotification()
  └── new PushNotification()
```

### After

```text
Client
  │
  ▼
Factory
  │
  ├── new EmailNotification()
  ├── new SMSNotification()
  └── new PushNotification()
```

### Important

> **Simple Factory hides object creation from the client; it does not remove object creation.**

---

# 10. OCP — Important Interview Point

Simple Factory can have an **OCP problem**.

Suppose we add:

```text
WhatsAppNotification
```

We must modify:

```java
NotificationFactory
```

and add:

```java
if (type.equals("WHATSAPP")) {
    return new WhatsAppNotification();
}
```

So:

```text
New Product
    ↓
Modify Factory
```

This means the Factory becomes a modification point.

### Important nuance

Don't say:

> "Simple Factory always violates OCP."

Better:

> **"A Simple Factory implemented with conditionals needs modification when new product types are added, so it does not fully satisfy OCP."**

For a small and stable set of products, Simple Factory may still be the simplest and best solution.

---

# 11. Simple Factory vs Factory Method

This distinction is important.

### Simple Factory

```text
One Factory
     │
     ├── Product A
     ├── Product B
     └── Product C
```

> **Factory itself decides what to create.**

---

### Factory Method

```text
Creator
   │
   └── createProduct()
          ▲
          │
   Concrete Creators
          │
          ▼
   Concrete Products
```

> **Concrete creators decide what to create.**

---

# 12. Simple Factory vs Abstract Factory

Don't confuse these.

### Simple Factory

Usually creates **one product type**:

```text
NotificationFactory
       │
       ▼
Notification
```

Possible implementations:

```text
Email
SMS
Push
```

### Abstract Factory

Creates a **family of related products**:

```text
WindowsFactory
    │
    ├── WindowsButton
    └── WindowsCheckbox
```

```text
MacFactory
    │
    ├── MacButton
    └── MacCheckbox
```

Memory:

```text
Simple Factory
→ One factory creates products

Factory Method
→ Subclasses decide creation

Abstract Factory
→ Factory creates a family of related products
```

---

# 13. Advantages

* Centralizes object creation.
* Keeps creation logic out of client code.
* Reduces direct coupling to concrete classes.
* Easy to understand and implement.
* Useful when object creation is slightly complex.

---

# 14. Disadvantages

* Factory can become a large `if/else` or `switch`.
* Adding new product types may require modifying the Factory.
* Can become a maintenance hotspot.
* May be unnecessary for very simple object creation.

---

# 15. Common Mistakes

### ❌ Thinking Factory removes `new`

It doesn't.

```text
Factory hides/moves new
```

It doesn't eliminate it.

---

### ❌ Returning concrete classes unnecessarily

Prefer:

```java
Notification create(...)
```

over:

```java
EmailNotification create(...)
```

when multiple implementations share the same abstraction.

---

### ❌ Using Factory for everything

If creation is already simple:

```java
new User();
```

there may be no reason to create:

```java
UserFactory
```

A Factory should solve a real creation/coupling problem.

---

### ❌ Confusing Simple Factory with Factory Method

Simple Factory:

```text
Factory decides
```

Factory Method:

```text
Subclass decides
```

---

# 16. Interview Questions

### What is Simple Factory?

> A creation technique that centralizes object creation in a dedicated Factory instead of letting clients directly instantiate concrete classes.

---

### Why use a Factory?

> To separate object creation from object usage and reduce direct coupling between clients and concrete implementations.

---

### Does Factory eliminate `new`?

> No. It moves the object creation responsibility into the Factory.

---

### Why return an interface?

> So the client depends on an abstraction rather than a concrete implementation.

---

### Does Simple Factory follow OCP?

> Not completely when it uses conditional logic, because adding a new product generally requires modifying the Factory.

---

### Is Simple Factory a GoF Design Pattern?

> **No.** Simple Factory is a commonly used design idiom. The GoF creational patterns include **Factory Method** and **Abstract Factory**.

---

### When should I use Simple Factory?

Use it when:

```text
There are multiple implementations
        +
Creation logic should be centralized
        +
The product set is relatively small/stable
```

---

# 17. Memory Trick

```text
Client
  │
  │ "I need a Notification"
  ▼
Factory
  │
  │ decides
  ▼
Concrete Product
```

### One-liner

> **Simple Factory = Centralize object creation.**

### Factory Method

> **Let subclasses decide object creation.**

### Abstract Factory

> **Create a family of related objects.**

---

# 18. Final Revision

```text
CREATIONAL PATTERN
        │
        ▼
SIMPLE FACTORY
        │
        ├── Centralizes creation
        ├── Client avoids direct new
        ├── Returns abstraction
        ├── Uses polymorphism
        └── Often uses if/switch

        ↓

FACTORY METHOD
        │
        ├── Creation delegated to subclasses
        └── Supports OCP better

        ↓

ABSTRACT FACTORY
        │
        └── Creates families of related products
```

> **Core idea: Don't let every client know how every concrete object is created. Put the creation decision in one dedicated place.**
