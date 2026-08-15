````markdown
# Factory Method Pattern — Implementation.md

This document walks through the implementation of the **Factory Method Pattern** step by step using a Notification example.

---

# 1. Project Structure

We will create:

```text
p10_factory_metod_pattern/
│
├── Notification.java
├── EmailNotification.java
├── SMSNotification.java
├── NotificationCreator.java
├── EmailCreator.java
├── SMSCreator.java
└── Main.java
````

Responsibilities:

```text
Notification.java
        ↓
Product interface

EmailNotification.java
SMSNotification.java
        ↓
Concrete Products

NotificationCreator.java
        ↓
Abstract Creator
+ common workflow
+ Factory Method

EmailCreator.java
SMSCreator.java
        ↓
Concrete Creators

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
package p10_factory_metod_pattern;

public interface Notification {

    void send();

}
```

This is the common contract for all notifications.

---

# 3. Create Concrete Products

## EmailNotification

Create:

```text
EmailNotification.java
```

```java
package p10_factory_metod_pattern;

public class EmailNotification implements Notification {

    @Override
    public void send() {
        System.out.println("Sending Email");
    }

}
```

---

## SMSNotification

Create:

```text
SMSNotification.java
```

```java
package p10_factory_metod_pattern;

public class SMSNotification implements Notification {

    @Override
    public void send() {
        System.out.println("Sending SMS");
    }

}
```

Now:

```text
              Notification
               <<interface>>
                     ▲
              ┌──────┴──────┐
              │             │
              ▼             ▼
     EmailNotification  SMSNotification
```

---

# 4. Create the Abstract Creator

Create:

```text
NotificationCreator.java
```

```java
package p10_factory_metod_pattern;

public abstract class NotificationCreator {

    // Factory Method
    abstract Notification createNotification();

    // Common workflow
    public void sendNotification() {

        Notification notification =
            createNotification();

        notification.send();
    }

}
```

This class has two important parts.

---

## Factory Method

```java
abstract Notification createNotification();
```

This is the **Factory Method**.

The parent says:

> "A Notification needs to be created here, but I don't know which concrete Notification."

The subclasses will decide.

---

## Common Workflow

```java
public void sendNotification() {

    Notification notification =
        createNotification();

    notification.send();
}
```

The parent knows the overall workflow:

```text
Create Notification
        ↓
Send Notification
```

But it doesn't know whether the Notification will be:

```text
Email
SMS
Push
```

---

# 5. Create EmailCreator

Create:

```text
EmailCreator.java
```

```java
package p10_factory_metod_pattern;

public class EmailCreator extends NotificationCreator {

    @Override
    Notification createNotification() {

        return new EmailNotification();
    }

}
```

The subclass decides:

```text
EmailCreator
     ↓
EmailNotification
```

The actual product creation happens here:

```java
new EmailNotification();
```

---

# 6. Create SMSCreator

Create:

```text
SMSCreator.java
```

```java
package p10_factory_metod_pattern;

public class SMSCreator extends NotificationCreator {

    @Override
    Notification createNotification() {

        return new SMSNotification();
    }

}
```

Now:

```text
NotificationCreator
        ▲
        │
   ┌────┴────┐
   │         │
   ▼         ▼
EmailCreator SMSCreator
   │         │
   ▼         ▼
Email       SMS
```

Each Concrete Creator decides which Concrete Product it creates.

---

# 7. Create Main

Create:

```text
Main.java
```

```java
package p10_factory_metod_pattern;

public class Main {

    public static void main(String[] args) {

        NotificationCreator creator =
            new EmailCreator();

        creator.sendNotification();

    }

}
```

Output:

```text
Sending Email
```

---

# 8. What Happens in Main?

Look at:

```java
NotificationCreator creator =
    new EmailCreator();
```

This creates the **Creator object**:

```text
EmailCreator object
```

It is stored in:

```text
NotificationCreator creator
```

So:

```text
creator
   │
   ▼
EmailCreator object
```

Then:

```java
creator.sendNotification();
```

calls the common method inherited from:

```text
NotificationCreator
```

---

# 9. Execution Flow

Inside:

```java
public void sendNotification() {

    Notification notification =
        createNotification();

    notification.send();
}
```

The first important call is:

```java
createNotification();
```

Although the method is declared in the parent:

```java
abstract Notification createNotification();
```

the actual object is:

```text
EmailCreator
```

Therefore runtime polymorphism calls:

```java
EmailCreator.createNotification();
```

which executes:

```java
return new EmailNotification();
```

---

# 10. Complete Execution Flow

```text
Main
 │
 │ new EmailCreator()
 ▼
EmailCreator Object
 │
 │ sendNotification()
 ▼
NotificationCreator
 │
 │ createNotification()
 ▼
EmailCreator.createNotification()
 │
 │ new EmailNotification()
 ▼
EmailNotification Object
 │
 │ returned
 ▼
Notification notification
 │
 │ notification.send()
 ▼
EmailNotification.send()
```

Output:

```text
Sending Email
```

---

# 11. Important: Are Two Objects Created?

Yes, but they are **different objects**.

### Object 1

Created in `Main`:

```java
new EmailCreator();
```

This creates:

```text
EmailCreator
```

### Object 2

Created inside the Factory Method:

```java
new EmailNotification();
```

This creates:

```text
EmailNotification
```

So:

```text
Main
 │
 └── new EmailCreator()
          ↓
     EmailCreator


Factory Method
 │
 └── new EmailNotification()
          ↓
     EmailNotification
```

There is no duplicate creation of the `EmailNotification`.

---

# 12. Why Does `createNotification()` Return `Notification`?

The Factory Method is:

```java
abstract Notification createNotification();
```

It returns the abstraction:

```text
Notification
```

because different creators can return different concrete products.

For example:

```text
EmailCreator
     ↓
EmailNotification
```

and:

```text
SMSCreator
     ↓
SMSNotification
```

Both are:

```text
Notification
```

Therefore the parent can safely work with:

```java
Notification notification;
```

without knowing the concrete class.

---

# 13. Add a Common Workflow

The real benefit becomes clearer when the workflow becomes larger.

Change:

```java
public void sendNotification() {

    Notification notification =
        createNotification();

    notification.send();
}
```

to:

```java
public void sendNotification() {

    Notification notification =
        createNotification();

    notification.send();

    logNotification();

    saveNotification();
}

private void logNotification() {
    System.out.println("Notification logged");
}

private void saveNotification() {
    System.out.println("Notification saved");
}
```

Now the workflow is:

```text
create Notification
        ↓
send Notification
        ↓
log Notification
        ↓
save Notification
```

The workflow is common for every notification type.

Only this part varies:

```text
createNotification()
```

---

# 14. The Subclass Only Changes the Creation Step

Email:

```java
class EmailCreator extends NotificationCreator {

    @Override
    Notification createNotification() {

        return new EmailNotification();
    }

}
```

SMS:

```java
class SMSCreator extends NotificationCreator {

    @Override
    Notification createNotification() {

        return new SMSNotification();
    }

}
```

The common workflow stays in:

```text
NotificationCreator
```

This gives us:

```text
                 NotificationCreator
                 ┌─────────────────────┐
                 │ common workflow     │
                 │                     │
                 │ sendNotification()  │
                 │        │            │
                 │        ▼            │
                 │ createNotification()│
                 └─────────┬───────────┘
                           ▲
                           │ overridden
                    ┌──────┴──────┐
                    │             │
                    ▼             ▼
              EmailCreator   SMSCreator
                    │             │
                    ▼             ▼
                  Email           SMS
```

---

# 15. Try SMS

Change `Main.java`:

```java
package p10_factory_metod_pattern;

public class Main {

    public static void main(String[] args) {

        NotificationCreator creator =
            new SMSCreator();

        creator.sendNotification();

    }

}
```

Output:

```text
Sending SMS
```

The same:

```java
sendNotification()
```

is used.

Only the Factory Method implementation changes.

---

# 16. Add Push Notification

Create:

```text
PushNotification.java
```

```java
package p10_factory_metod_pattern;

public class PushNotification implements Notification {

    @Override
    public void send() {
        System.out.println("Sending Push Notification");
    }

}
```

Then create:

```text
PushCreator.java
```

```java
package p10_factory_metod_pattern;

public class PushCreator extends NotificationCreator {

    @Override
    Notification createNotification() {

        return new PushNotification();
    }

}
```

Now:

```text
NotificationCreator
        ▲
   ┌────┼─────────┐
   │    │         │
   ▼    ▼         ▼
 Email  SMS      Push
Creator Creator  Creator
   │    │         │
   ▼    ▼         ▼
 Email  SMS      Push
```

The existing:

```text
NotificationCreator
EmailCreator
SMSCreator
```

do not need to change.

---

# 17. Main with Push

```java
package p10_factory_metod_pattern;

public class Main {

    public static void main(String[] args) {

        NotificationCreator creator =
            new PushCreator();

        creator.sendNotification();

    }

}
```

Output:

```text
Sending Push Notification
```

---

# 18. Complete Code

## Notification.java

```java
package p10_factory_metod_pattern;

public interface Notification {

    void send();

}
```

---

## EmailNotification.java

```java
package p10_factory_metod_pattern;

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
package p10_factory_metod_pattern;

public class SMSNotification implements Notification {

    @Override
    public void send() {
        System.out.println("Sending SMS");
    }

}
```

---

## NotificationCreator.java

```java
package p10_factory_metod_pattern;

public abstract class NotificationCreator {

    // Factory Method
    abstract Notification createNotification();

    // Common workflow
    public void sendNotification() {

        Notification notification =
            createNotification();

        notification.send();
    }

}
```

---

## EmailCreator.java

```java
package p10_factory_metod_pattern;

public class EmailCreator extends NotificationCreator {

    @Override
    Notification createNotification() {

        return new EmailNotification();
    }

}
```

---

## SMSCreator.java

```java
package p10_factory_metod_pattern;

public class SMSCreator extends NotificationCreator {

    @Override
    Notification createNotification() {

        return new SMSNotification();
    }

}
```

---

## Main.java

```java
package p10_factory_metod_pattern;

public class Main {

    public static void main(String[] args) {

        NotificationCreator creator =
            new EmailCreator();

        creator.sendNotification();

    }

}
```

---

# 19. Compile and Run

From the parent directory:

```bash
javac p10_factory_metod_pattern/*.java
```

Run:

```bash
java p10_factory_metod_pattern.Main
```

Output:

```text
Sending Email
```

Because every class declares:

```java
package p10_factory_metod_pattern;
```

Java uses the fully qualified class name:

```text
p10_factory_metod_pattern.Main
```

---

# 20. Implementation Flow

```text
1. Create Product interface
        ↓
2. Create Concrete Products
        ↓
3. Create Abstract Creator
        ↓
4. Put common workflow in Creator
        ↓
5. Define Factory Method
        ↓
6. Create Concrete Creators
        ↓
7. Override Factory Method
        ↓
8. Concrete Creator creates Concrete Product
        ↓
9. Client works with Creator abstraction
```

---

# Final Implementation Takeaway

The most important structure to remember is:

```text
                Creator
                   │
                   │ common workflow
                   ▼
          createProduct()
                   ▲
                   │ overridden by
                   │
          Concrete Creator
                   │
                   │ new
                   ▼
          Concrete Product
```

The key implementation idea is:

> **The parent owns the common workflow, while the concrete creator owns the decision of which product to instantiate.**

```
```
