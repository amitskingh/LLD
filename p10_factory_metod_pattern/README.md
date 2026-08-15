# Factory Method Pattern

## Creational Design Pattern

> **Factory Method defines a method for creating an object, but lets subclasses decide which concrete object to create.**

### Easy Definition

> **Common workflow stays in the parent; object creation is delegated to the child.**

---

# 1. The Problem

Suppose we have different notification types:

```text
EmailNotification
SMSNotification
PushNotification
```

All implement:

```java
interface Notification {

    void send();

}
```

Now imagine a common notification workflow:

```text
Create Notification
       ↓
Send Notification
       ↓
Log
       ↓
Save
```

The workflow is the same, but the **Notification object changes**.

For example:

```text
EmailService → EmailNotification

SMSService → SMSNotification

PushService → PushNotification
```

We don't want the parent class to know every concrete notification.

---

# 2. Factory Method Solution

Create an abstract creator:

```java
abstract class NotificationCreator {

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

The parent controls the workflow:

```text
sendNotification()
      │
      ├── create notification
      │
      └── send notification
```

But it doesn't know **which concrete Notification** will be created.

---

# 3. Concrete Creators

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

Each subclass decides what the Factory Method creates.

```text
NotificationCreator
        │
        │ createNotification()
        │
   ┌────┴─────┐
   │          │
   ▼          ▼
EmailCreator  SMSCreator
   │          │
   ▼          ▼
Email        SMS
```

---

# 4. Important: There Is Only One Product Creation

This can initially look confusing:

```java
Notification notification =
    createNotification();
```

and:

```java
return new EmailNotification();
```

These do **not** create two objects.

The actual creation happens here:

```java
new EmailNotification();
```

The parent simply receives the object returned by the Factory Method:

```text
EmailCreator
     │
     │ new EmailNotification()
     ▼
EmailNotification
     │
     │ returned
     ▼
Notification notification
```

So:

```text
new EmailCreator()
```

creates the **Creator object**.

```text
new EmailNotification()
```

creates the **Product object**.

They are two different objects with two different responsibilities.

---

# 5. Why Does the Parent Call an Abstract Method?

This is the key concept.

```java
abstract Notification createNotification();
```

The parent doesn't know the implementation.

But at runtime:

```java
NotificationCreator creator =
    new EmailCreator();

creator.sendNotification();
```

When this executes:

```java
Notification notification =
    createNotification();
```

Java uses **runtime polymorphism** and calls:

```java
EmailCreator.createNotification()
```

which returns:

```java
new EmailNotification();
```

---

# 6. Complete Execution Flow

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
 │ notification.send()
 ▼
EmailNotification.send()
```

### Core idea

```text
Parent
  │
  │ controls WHAT happens
  ▼
Common Workflow
  │
  │ calls Factory Method
  ▼
Child
  │
  │ decides WHAT object to create
  ▼
Concrete Product
```

---

# 7. Why Use an Abstract Creator?

The abstract creator can contain **common workflow**.

```java
abstract class NotificationCreator {

    abstract Notification createNotification();

    public void sendNotification() {

        Notification notification =
            createNotification();

        notification.send();

        logNotification();
        saveNotification();
    }

    void logNotification() {
        // common logging
    }

    void saveNotification() {
        // common persistence
    }
}
```

Now subclasses only change the part that varies:

```java
createNotification()
```

Everything else is reused.

---

# 8. The Real Problem Factory Method Solves

A common mistake is thinking:

> "Factory Method is useful because it removes `new`."

❌ That's not the main purpose.

It doesn't eliminate `new`.

Instead:

> **Factory Method separates a common workflow from the object-creation step that varies between subclasses.**

Think:

```text
COMMON WORKFLOW
       +
VARIABLE CREATION
       ↓
FACTORY METHOD
```

---

# 9. Why Not Just Use Direct Creation?

For a very small program, this:

```java
Notification notification =
    new EmailNotification();

notification.send();
```

is perfectly fine.

Factory Method can actually introduce more classes:

```text
Notification
NotificationCreator
EmailCreator
EmailNotification
```

So don't use it just because it's a design pattern.

Use it when:

```text
There is a common workflow
        +
The product creation varies
        +
Different subclasses need different products
```

---

# 10. Simple Factory vs Factory Method

### Simple Factory

```text
Client
  │
  ▼
Factory
  │
  ├── Email
  ├── SMS
  └── Push
```

> **The Factory itself decides what to create.**

Usually:

```java
if (...)
    return new EmailNotification();

if (...)
    return new SMSNotification();
```

---

### Factory Method

```text
Creator
   │
   └── Common Workflow
          │
          ▼
   createNotification()
          ▲
          │
   Concrete Creator
          │
          ▼
   Concrete Product
```

> **The concrete creator decides what to create.**

### Memory

```text
Simple Factory
→ Factory decides

Factory Method
→ Subclass decides
```

---

# 11. Factory Method and OCP

Factory Method can better support the **Open/Closed Principle**.

Suppose we add:

```text
WhatsAppNotification
```

With a conditional Simple Factory, we may need to modify:

```java
NotificationFactory
```

With Factory Method, we can add:

```java
class WhatsAppCreator extends NotificationCreator {

    @Override
    Notification createNotification() {
        return new WhatsAppNotification();
    }
}
```

Existing creator classes and the common workflow remain unchanged.

```text
Existing Code
      │
      ├── EmailCreator
      ├── SMSCreator
      │
      └── NEW WhatsAppCreator
```

> **Extend the system by adding a new creator instead of modifying the existing creation logic.**

### Important nuance

Don't say:

> "Simple Factory always violates OCP."

Instead:

> **"A conditional Simple Factory requires modification when new product types are added, while Factory Method can support extension through new creator subclasses."**

---

# 12. Interface vs Abstract Class

Factory Method does **not** require an abstract class.

You can define the creator using an interface:

```java
interface NotificationCreator {

    Notification createNotification();
}
```

and:

```java
class EmailCreator implements NotificationCreator {

    @Override
    public Notification createNotification() {
        return new EmailNotification();
    }
}
```

This is also valid.

### Why use an abstract class?

When creators share:

* Common workflow
* Common methods
* Common state
* Common implementation

For example:

```java
abstract class NotificationCreator {

    abstract Notification createNotification();

    void sendNotification() {
        Notification notification =
            createNotification();

        notification.send();
    }
}
```

### Memory

```text
Interface
→ Contract

Abstract Class
→ Contract + Shared Implementation
```

---

# 13. UML

```text
                 <<abstract>>
              NotificationCreator
              +----------------------+
              | createNotification() |
              | sendNotification()   |
              +----------------------+
                       ▲
                       │ extends
              ┌────────┴────────┐
              │                 │
       EmailCreator         SMSCreator
              │                 │
              │ creates         │ creates
              ▼                 ▼
     EmailNotification     SMSNotification
              ▲                 ▲
              │                 │
              └──── implements ─┘
                       │
                 Notification
                  <<interface>>
```

A cleaner relationship view:

```text
        Notification
        <<interface>>
             ▲
             │ implements
      ┌──────┴───────────┐
      │                  │
EmailNotification   SMSNotification


     NotificationCreator
       <<abstract>>
             ▲
             │ extends
      ┌──────┴───────────┐
      │                  │
 EmailCreator        SMSCreator
      │                  │
      │ creates          │ creates
      ▼                  ▼
   Email             SMS
```

---

# 14. Advantages

* Separates object creation from the common workflow.
* Supports polymorphic object creation.
* New product types can be introduced through new creators.
* Can reduce coupling to concrete products.
* Encourages Open/Closed Principle.
* Allows common creation-related workflow to be reused.

---

# 15. Disadvantages

* More classes than direct object creation.
* Can be overengineering for simple creation.
* Creator hierarchy can become large.
* Understanding the indirection takes time.
* The pattern is useful only when there is a meaningful varying creation step.

---

# 16. Common Mistakes

### ❌ Thinking Factory Method removes all `new`

It doesn't.

```text
Factory Method
       ↓
Moves/delegates product creation
```

It does not eliminate object creation.

---

### ❌ Thinking `sendNotification()` creates two objects

```java
Notification notification =
    createNotification();
```

Only receives the object created by:

```java
return new EmailNotification();
```

---

### ❌ Using Factory Method when direct creation is enough

Don't create:

```text
Creator
Factory
AbstractCreator
ProductFactory
```

for:

```java
new User();
```

unless there is an actual design reason.

---

### ❌ Confusing Factory Method with Simple Factory

```text
Simple Factory
→ Factory decides

Factory Method
→ Concrete Creator decides
```

---

### ❌ Thinking Factory Method must use an abstract class

It can use:

```text
Interface
```

or:

```text
Abstract Class
```

The pattern is about **delegating creation to concrete creators**, not about a particular Java keyword.

---

# 17. Interview Questions

### What is Factory Method?

> A creational pattern that defines an object-creation method and lets concrete creators decide which concrete product to instantiate.

---

### What problem does it solve?

> It separates a common workflow from the object-creation step that varies between subclasses.

---

### Why not simply use `new`?

> Direct `new` is fine for simple cases. Factory Method becomes useful when a common workflow needs to work with different products and the creation decision varies.

---

### Why does the parent call an abstract method?

> Because runtime polymorphism invokes the concrete creator's implementation, allowing the subclass to decide which product is created.

---

### Does Factory Method eliminate `new`?

> No. It delegates and encapsulates the product creation decision.

---

### Why does Factory Method support OCP?

> New product types can be introduced by adding new concrete creators instead of modifying the existing creator's workflow.

---

### Why use an abstract class instead of an interface?

> An abstract class is useful when creators share common implementation or workflow. An interface is sufficient when only a creation contract is needed.

---

### Is Factory Method worth the extra classes?

> Only when there is a real varying creation step inside a common workflow. For simple object creation, direct instantiation is often better.

---

# 18. Key Takeaways

```text
✔ Creational Design Pattern

✔ Defines a Factory Method for object creation

✔ Concrete Creator decides the product

✔ Parent can own the common workflow

✔ Uses runtime polymorphism

✔ Separates common workflow from varying creation

✔ Does NOT eliminate new

✔ Can support OCP

✔ Can use abstract class OR interface

✔ Adds complexity, so use it only when justified
```

---

# Memory Trick

```text
Simple Factory

Factory decides
      ↓
"Which object should I create?"


Factory Method

Subclass decides
      ↓
"Which object should I create?"
```

### One-line revision

> **Factory Method = Common workflow in the parent + object creation delegated to concrete creators.**
