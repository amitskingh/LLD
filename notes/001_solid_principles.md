# SOLID Principles

SOLID is a set of **5 Object-Oriented Design principles** that help build software that is:

- Easy to understand
- Easy to extend
- Easy to maintain
- Easy to test
- Less tightly coupled

---

# 1. S — Single Responsibility Principle (SRP)

> **A class should have only one reason to change.**

### Easy Line
One class = One responsibility.

### Bad Example

```java
class Invoice {
    void calculateTotal() {}
    void printInvoice() {}
    void saveToDatabase() {}
}
```

This class has **3 responsibilities**:
- Business Logic
- Printing
- Database

### Good Example

```java
class Invoice {
    void calculateTotal() {}
}

class InvoicePrinter {
    void print() {}
}

class InvoiceRepository {
    void save() {}
}
```

### Benefits

- Easier maintenance
- Easier testing
- Smaller classes

---

# 2. O — Open/Closed Principle (OCP)

> **Open for extension, Closed for modification.**

### Easy Line

Add new features without changing existing code.

### Bad Example

```java
class PaymentService {

    void pay(String type) {
        if(type.equals("UPI")) {}
        else if(type.equals("Card")) {}
    }
}
```

Every new payment method modifies existing code.

### Good Example

```java
interface Payment {
    void pay();
}

class UpiPayment implements Payment {
    public void pay() {}
}

class CardPayment implements Payment {
    public void pay() {}
}

class PaymentService {
    void process(Payment payment) {
        payment.pay();
    }
}
```

New payment?

Just create another implementation.

### Benefits

- No modification of existing code
- Fewer bugs
- Easy feature addition

---

# 3. L — Liskov Substitution Principle (LSP)

> **A child class should be replaceable with its parent without breaking the program.**

### Easy Line

Child should behave like the parent.

### Bad Example

```java
class Bird {
    void fly() {}
}

class Penguin extends Bird {
    void fly() {
        throw new UnsupportedOperationException();
    }
}
```

Penguins can't fly.

So Penguin is **not a proper Bird** in this design.

### Good Example

```java
class Bird {}

interface FlyingBird {
    void fly();
}

class Sparrow extends Bird implements FlyingBird {
    public void fly() {}
}

class Penguin extends Bird {}
```

### Benefits

- Correct inheritance
- No unexpected behavior
- Better polymorphism

---

# 4. I — Interface Segregation Principle (ISP)

> **Don't force a class to implement methods it doesn't need.**

### Easy Line

Many small interfaces are better than one large interface.

### Bad Example

```java
interface Worker {
    void work();
    void eat();
}

class Robot implements Worker {
    public void work() {}

    public void eat() {
        // Not needed
    }
}
```

Robot doesn't eat.

### Good Example

```java
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class Human implements Workable, Eatable {
    public void work() {}
    public void eat() {}
}

class Robot implements Workable {
    public void work() {}
}
```

### Benefits

- Smaller interfaces
- Cleaner code
- Less unnecessary implementation

---

# 5. D — Dependency Inversion Principle (DIP)

> **Depend on abstractions, not concrete classes.**

### Easy Line

Use interfaces instead of directly creating objects.

### Bad Example

```java
class Keyboard {}

class Computer {

    Keyboard keyboard = new Keyboard();
}
```

Computer is tightly coupled to Keyboard.

### Good Example

```java
interface InputDevice {}

class Keyboard implements InputDevice {}

class Mouse implements InputDevice {}

class Computer {

    InputDevice device;

    Computer(InputDevice device) {
        this.device = device;
    }
}
```

Now Computer works with any input device.

### Benefits

- Loose coupling
- Easy testing
- Easy replacement
- Dependency Injection support

---

# Quick Interview Cheat Sheet

| Principle | Remember This |
|-----------|---------------|
| **S** | One class → One responsibility |
| **O** | Extend, don't modify |
| **L** | Child should replace Parent safely |
| **I** | Small focused interfaces |
| **D** | Depend on interfaces, not implementations |

---

# Easy Way to Remember

```
S → Single job

O → Extend without changing

L → Child behaves like Parent

I → Small interfaces

D → Use interfaces instead of objects
```

---

# Real-World Examples

| Principle | Example |
|-----------|---------|
| SRP | Invoice, Printer, Database classes separated |
| OCP | Add new Payment method without changing PaymentService |
| LSP | Sparrow can replace Bird, Penguin shouldn't override fly() |
| ISP | Robot shouldn't implement eat() |
| DIP | Computer depends on InputDevice interface |

---

# Common Interview Questions

### What is SOLID?

A set of five Object-Oriented Design principles that help create maintainable, scalable, flexible, and loosely coupled software.

---

### Which principle reduces tight coupling?

**Dependency Inversion Principle (DIP)**

---

### Which principle helps when adding new features?

**Open/Closed Principle (OCP)**

---

### Which principle says one class should do one thing?

**Single Responsibility Principle (SRP)**

---

### Which principle is related to inheritance?

**Liskov Substitution Principle (LSP)**

---

### Which principle avoids fat interfaces?

**Interface Segregation Principle (ISP)**

---

# 30-Second Revision

```
SRP → One class, one job.

OCP → Extend without modifying existing code.

LSP → Child should be usable wherever Parent is expected.

ISP → Prefer multiple small interfaces over one large interface.

DIP → Depend on interfaces (abstractions), not concrete implementations.
```