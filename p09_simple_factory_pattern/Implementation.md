````markdown
# Simple Factory Pattern — Implementation.md

This document walks through the implementation of the **Simple Factory Pattern** step by step using a Notification example.

---

# 1. Project Structure

We will create:

```text
p09_simple_factory_pattern/
│
├── Notification.java
├── EmailNotification.java
├── SMSNotification.java
├── PushNotification.java
├── NotificationFactory.java
└── Main.java
````

Each class has one clear responsibility.

```text
Notification.java
        ↓
Common contract

EmailNotification.java
SMSNotification.java
PushNotification.java
        ↓
Concrete products

NotificationFactory.java
        ↓
Creates the required product

Main.java
        ↓
Client
```

---

# 2. Create the Product Interface

Create:

```text
Notification.java
```

```java
package p09_simple_factory_pattern;

public interface Notification {

    void send();

}
```

This is the common contract for all notifications.

---

# 3. Create EmailNotification

Create:

```text
EmailNotification.java
```

```java
package p09_simple_factory_pattern;

public class EmailNotification implements Notification {

    @Override
    public void send() {
        System.out.println("Sending Email");
    }

}
```

---

# 4. Create SMSNotification

Create:

```text
SMSNotification.java
```

```java
package p09_simple_factory_pattern;

public class SMSNotification implements Notification {

    @Override
    public void send() {
        System.out.println("Sending SMS");
    }

}
```

---

# 5. Create PushNotification

Create:

```text
PushNotification.java
```

```java
package p09_simple_factory_pattern;

public class PushNotification implements Notification {

    @Override
    public void send() {
        System.out.println("Sending Push Notification");
    }

}
```

Now we have:

```text
             Notification
              <<interface>>
                    ▲
          ┌─────────┼─────────┐
          │         │         │
          ▼         ▼         ▼
        Email      SMS       Push
```

---

# 6. Create the Factory

Now create:

```text
NotificationFactory.java
```

```java
package p09_simple_factory_pattern;

public class NotificationFactory {

    public static Notification create(String type) {

        if (type.equals("EMAIL")) {

            return new EmailNotification();

        } else if (type.equals("SMS")) {

            return new SMSNotification();

        } else if (type.equals("PUSH")) {

            return new PushNotification();
        }

        throw new IllegalArgumentException(
            "Unknown notification type: " + type
        );
    }

}
```

This is the most important class in the implementation.

The Factory contains the concrete creation logic:

```java
new EmailNotification();
new SMSNotification();
new PushNotification();
```

---

# 7. Why Does the Factory Return `Notification`?

Notice:

```java
public static Notification create(String type)
```

The return type is:

```text
Notification
```

not:

```text
EmailNotification
```

because the Factory can return different implementations.

For example:

```text
"EMAIL"
    ↓
EmailNotification

"SMS"
    ↓
SMSNotification

"PUSH"
    ↓
PushNotification
```

All of them satisfy:

```java
Notification
```

Therefore the client can use:

```java
Notification notification;
```

regardless of the concrete implementation.

---

# 8. Create the Main Class

Create:

```text
Main.java
```

```java
package p09_simple_factory_pattern;

public class Main {

    public static void main(String[] args) {

        Notification notification =
            NotificationFactory.create("EMAIL");

        notification.send();

    }

}
```

Output:

```text
Sending Email
```

---

# 9. Try SMS

Change:

```java
Notification notification =
    NotificationFactory.create("EMAIL");
```

to:

```java
Notification notification =
    NotificationFactory.create("SMS");
```

Output:

```text
Sending SMS
```

No other code needs to change.

---

# 10. Try Push Notification

```java
Notification notification =
    NotificationFactory.create("PUSH");

notification.send();
```

Output:

```text
Sending Push Notification
```

---

# 11. What Happens During Execution?

Consider:

```java
Notification notification =
    NotificationFactory.create("EMAIL");
```

Execution starts from:

```text
Main
 │
 │ create("EMAIL")
 ▼
NotificationFactory
 │
 │ type == "EMAIL"
 ▼
new EmailNotification()
 │
 │ return
 ▼
Notification notification
```

Then:

```java
notification.send();
```

Because the actual object is:

```text
EmailNotification
```

Java calls:

```java
EmailNotification.send()
```

---

# 12. Important: There Is Only One Product Object

This:

```java
Notification notification =
    NotificationFactory.create("EMAIL");
```

doesn't create a second object.

Inside the Factory:

```java
return new EmailNotification();
```

creates the object.

Then the reference is returned to:

```java
notification
```

So:

```text
new EmailNotification()
        ↓
EmailNotification object
        ↓
returned by Factory
        ↓
stored in notification
```

---

# 13. Direct Creation vs Factory

Without Factory:

```java
Notification notification =
    new EmailNotification();
```

The client directly creates the concrete class.

With Factory:

```java
Notification notification =
    NotificationFactory.create("EMAIL");
```

The Factory performs the concrete creation.

The client only works with:

```java
Notification
```

---

# 14. Test All Products

We can test all three:

```java
package p09_simple_factory_pattern;

public class Main {

    public static void main(String[] args) {

        Notification email =
            NotificationFactory.create("EMAIL");

        email.send();


        Notification sms =
            NotificationFactory.create("SMS");

        sms.send();


        Notification push =
            NotificationFactory.create("PUSH");

        push.send();

    }

}
```

Output:

```text
Sending Email
Sending SMS
Sending Push Notification
```

---

# 15. Invalid Type

Try:

```java
Notification notification =
    NotificationFactory.create("WHATSAPP");
```

The Factory reaches:

```java
throw new IllegalArgumentException(
    "Unknown notification type: " + type
);
```

Output:

```text
Exception in thread "main"
java.lang.IllegalArgumentException:
Unknown notification type: WHATSAPP
```

We explicitly fail instead of returning `null`.

---

# 16. Add a New Notification

Suppose we want:

```text
WhatsAppNotification
```

## Step 1 — Create the class

Create:

```text
WhatsAppNotification.java
```

```java
package p09_simple_factory_pattern;

public class WhatsAppNotification implements Notification {

    @Override
    public void send() {
        System.out.println("Sending WhatsApp Notification");
    }

}
```

---

## Step 2 — Modify the Factory

Add:

```java
else if (type.equals("WHATSAPP")) {

    return new WhatsAppNotification();

}
```

So the Factory becomes:

```java
public static Notification create(String type) {

    if (type.equals("EMAIL")) {

        return new EmailNotification();

    } else if (type.equals("SMS")) {

        return new SMSNotification();

    } else if (type.equals("PUSH")) {

        return new PushNotification();

    } else if (type.equals("WHATSAPP")) {

        return new WhatsAppNotification();
    }

    throw new IllegalArgumentException(
        "Unknown notification type: " + type
    );
}
```

---

# 17. Test WhatsApp

In `Main.java`:

```java
Notification notification =
    NotificationFactory.create("WHATSAPP");

notification.send();
```

Output:

```text
Sending WhatsApp Notification
```

Notice what happened:

```text
New Product
    ↓
Create new class
    ↓
Modify Factory
    ↓
Client can use it
```

This modification will become important when we learn **Factory Method**.

---

# 18. Complete Code

## Notification.java

```java
package p09_simple_factory_pattern;

public interface Notification {

    void send();

}
```

---

## EmailNotification.java

```java
package p09_simple_factory_pattern;

public class EmailNotification implements Notification {

    @Override
    public void send() {
        System.out.println("Sending Email");
    }

}
```

---

## SMSNotification.java

```java
package p09_simple_factory_pattern;

public class SMSNotification implements Notification {

    @Override
    public void send() {
        System.out.println("Sending SMS");
    }

}
```

---

## PushNotification.java

```java
package p09_simple_factory_pattern;

public class PushNotification implements Notification {

    @Override
    public void send() {
        System.out.println("Sending Push Notification");
    }

}
```

---

## NotificationFactory.java

```java
package p09_simple_factory_pattern;

public class NotificationFactory {

    public static Notification create(String type) {

        if (type.equals("EMAIL")) {

            return new EmailNotification();

        } else if (type.equals("SMS")) {

            return new SMSNotification();

        } else if (type.equals("PUSH")) {

            return new PushNotification();
        }

        throw new IllegalArgumentException(
            "Unknown notification type: " + type
        );
    }

}
```

---

## Main.java

```java
package p09_simple_factory_pattern;

public class Main {

    public static void main(String[] args) {

        Notification notification =
            NotificationFactory.create("EMAIL");

        notification.send();

    }

}
```

---

# 19. Compile and Run

From the parent directory:

```bash
javac p09_simple_factory_pattern/*.java
```

Run using the fully qualified class name:

```bash
java p09_simple_factory_pattern.Main
```

Output:

```text
Sending Email
```

Because the classes belong to:

```java
package p09_simple_factory_pattern;
```

Java expects the class to be run using its **fully qualified name**:

```text
package + class
```

Therefore:

```bash
java p09_simple_factory_pattern.Main
```

not:

```bash
java Main
```

when running from the parent directory.

---

# 20. Final Implementation Flow

```text
                  Main
                    │
                    │
                    ▼
          NotificationFactory
                    │
             create(type)
                    │
          ┌─────────┼─────────┐
          │         │         │
          ▼         ▼         ▼
        Email      SMS       Push
          │         │         │
          └─────────┼─────────┘
                    │
                    ▼
              Notification
                    │
                    ▼
                  send()
```

---

# Implementation Takeaway

The implementation follows this sequence:

```text
1. Create Product interface
        ↓
2. Create Concrete Products
        ↓
3. Create Factory
        ↓
4. Move `new` operations into Factory
        ↓
5. Return Product interface
        ↓
6. Client asks Factory for object
        ↓
7. Client uses object through interface
```

The main implementation idea is:

> **The Factory owns the decision and the actual `new` operation; the client only asks for and uses the resulting abstraction.**

```
```
