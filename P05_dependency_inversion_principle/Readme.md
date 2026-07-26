# D — Dependency Inversion Principle (DIP)

> **High-level modules should not depend on low-level modules. Both should depend on abstractions (interfaces).**

Or, more simply:

> **Depend on interfaces, not concrete classes.**

---

# Why Do We Need DIP?

Imagine you're building an application.

```text
UserService
      │
      ▼
MySQLDatabase
```

Here, `UserService` directly depends on `MySQLDatabase`.

If tomorrow your company decides to switch to PostgreSQL or MongoDB, you'll have to modify `UserService`.

This makes the code **tightly coupled**.

---

# ❌ Without DIP (Tightly Coupled)

```java
class MySQLDatabase {
    void save() {
        System.out.println("Saving to MySQL");
    }
}

class UserService {

    private MySQLDatabase database = new MySQLDatabase();

    void registerUser() {
        database.save();
    }
}
```

### Problem

```text
UserService
      │
      ▼
MySQLDatabase
```

Issues:

* Can't switch databases easily.
* Hard to write unit tests.
* Changes in `MySQLDatabase` may require changes in `UserService`.

---

# ✔ With DIP

## Step 1: Create an Interface

```java
interface Database {
    void save();
}
```

Now `UserService` doesn't care **how** the data is saved.

---

## Step 2: Implement the Interface

```java
class MySQLDatabase implements Database {

    public void save() {
        System.out.println("Saving to MySQL");
    }
}
```

```java
class PostgreSQLDatabase implements Database {

    public void save() {
        System.out.println("Saving to PostgreSQL");
    }
}
```

```java
class MongoDatabase implements Database {

    public void save() {
        System.out.println("Saving to MongoDB");
    }
}
```

---

## Step 3: Depend on the Interface

```java
class UserService {

    private Database database;

    UserService(Database database) {
        this.database = database;
    }

    void registerUser() {
        database.save();
    }
}
```

---

## Usage

```java
Database db = new MySQLDatabase();
UserService service = new UserService(db);

service.registerUser();
```

Switching to PostgreSQL is easy:

```java
Database db = new PostgreSQLDatabase();
UserService service = new UserService(db);
```

No changes inside `UserService`.

---

# Before vs After

## ❌ Before DIP

```text
UserService
      │
      ▼
MySQLDatabase
```

`UserService` knows exactly which database it is using.

---

## ✔ After DIP

```text
             Database
             (Interface)
             /    |    \
            /     |     \
      MySQL   PostgreSQL  MongoDB
            \     |     /
             \    |    /
            UserService
```

Now `UserService` only knows about the **Database interface**.

---

# Real-Life Example

Think about charging your phone.

```text
Phone
   │
USB-C Cable
   │
Charger
```

Your phone doesn't care whether the charger is:

* Samsung
* Apple
* OnePlus
* Anker

It only expects a **USB-C interface**.

The interface remains the same, while implementations vary.

This is Dependency Inversion.

---

# Another Example: Payment Gateway

## ❌ Without DIP

```java
class PaymentService {

    private Razorpay razorpay = new Razorpay();

    void pay() {
        razorpay.makePayment();
    }
}
```

Adding Stripe requires modifying `PaymentService`.

---

## ✔ With DIP

```java
interface PaymentGateway {
    void makePayment();
}
```

```java
class Razorpay implements PaymentGateway {
    public void makePayment() {}
}
```

```java
class Stripe implements PaymentGateway {
    public void makePayment() {}
}
```

```java
class PaymentService {

    private PaymentGateway gateway;

    PaymentService(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    void pay() {
        gateway.makePayment();
    }
}
```

Usage:

```java
PaymentGateway gateway = new Stripe();
PaymentService service = new PaymentService(gateway);

service.pay();
```

No changes to `PaymentService`.

---

# Why Constructor Injection?

Notice this:

```java
UserService(Database database) {
    this.database = database;
}
```

This is called **Dependency Injection**.

Instead of creating dependencies itself:

```java
new MySQLDatabase();
```

the object **receives** its dependency from outside.

This:

* Reduces coupling.
* Makes testing easier.
* Makes replacing implementations simple.

> **Dependency Injection (DI)** is a technique used to achieve the **Dependency Inversion Principle (DIP)**.

---

# Interview Points

### High-Level Module

Contains business logic.

Example:

```text
UserService
OrderService
PaymentService
NotificationService
```

---

### Low-Level Module

Handles implementation details.

Example:

```text
MySQLDatabase
MongoDatabase
Stripe
Razorpay
EmailSender
```

---

### Abstraction

An interface or abstract class.

Example:

```java
interface Database
interface PaymentGateway
interface NotificationService
```

---

# Easy Memory Trick

```text
Without DIP

Business Logic
      │
      ▼
Concrete Class


With DIP

Business Logic
      │
      ▼
Interface
      ▲
      │
Concrete Classes
```

---

# Interview Summary

| Without DIP                                   | With DIP                             |
| --------------------------------------------- | ------------------------------------ |
| Depends on concrete class                     | Depends on interface                 |
| Tight coupling                                | Loose coupling                       |
| Difficult to replace implementations          | Easy to swap implementations         |
| Harder to test                                | Easy to mock and test                |
| Requires code changes for new implementations | Extend by adding new implementations |

---

# 10-Second Rule

```text
DIP = Depend on Interfaces,
not Implementations.
```

Whenever you see a class directly creating or depending on a concrete implementation (e.g., `new MySQLDatabase()`), ask yourself:

> **"Can this dependency be replaced with an interface?"**

If the answer is **yes**, applying DIP will usually make the design more flexible and maintainable.
