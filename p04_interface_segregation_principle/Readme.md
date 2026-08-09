# Interface Segregation Principle (ISP)

## Definition

> **Clients should not be forced to depend on interfaces they do not use.**

This means an interface should expose **only the methods required by its implementing classes**.

Instead of creating one large interface with many unrelated methods, create **multiple small, focused interfaces**.

---

# Easy Definition

> **Many small interfaces are better than one large interface.**

or

> **Don't force a class to implement methods it doesn't need.**

---

# Why Do We Need ISP?

Imagine you're designing a system for different types of workers.

Some workers:

* Work
* Eat

Others:

* Work only

If every worker is forced to implement both methods, some classes will have methods they don't actually support.

This leads to:

* Empty methods
* Exceptions
* Confusing code

ISP solves this by splitting large interfaces into smaller, more specific ones.

---

# Bad Example

## Worker Interface

```java
interface Worker {

    void work();

    void eat();

}
```

---

## Human

```java
class Human implements Worker {

    @Override
    public void work() {
        System.out.println("Working...");
    }

    @Override
    public void eat() {
        System.out.println("Eating...");
    }

}
```

---

## Robot

```java
class Robot implements Worker {

    @Override
    public void work() {
        System.out.println("Working...");
    }

    @Override
    public void eat() {
        throw new UnsupportedOperationException(
            "Robot doesn't eat"
        );
    }

}
```

---

## Problems

Robot doesn't eat.

Yet it is forced to implement:

```java
eat();
```

Possible outcomes:

* Empty method
* Runtime exception
* Fake implementation

This violates ISP.

---

# Good Example

Split the interface into smaller interfaces.

---

## Workable

```java
interface Workable {

    void work();

}
```

---

## Eatable

```java
interface Eatable {

    void eat();

}
```

---

## Human

```java
class Human implements Workable, Eatable {

    @Override
    public void work() {

        System.out.println("Working");

    }

    @Override
    public void eat() {

        System.out.println("Eating");

    }

}
```

---

## Robot

```java
class Robot implements Workable {

    @Override
    public void work() {

        System.out.println("Working");

    }

}
```

No unnecessary methods.

No exceptions.

No empty implementations.

---

# UML Structure

```text
                +-------------+
                | Workable    |
                +-------------+
                | work()      |
                +------^------+
                       |
             ------------------
             |                |
         Human            Robot


                +-------------+
                | Eatable     |
                +-------------+
                | eat()       |
                +------^------+
                       |
                    Human
```

---

# How ISP Works

```text
Large Interface

↓

Split into

↓

Small Focused Interfaces

↓

Classes implement only what they need.
```

---

# Real-World Examples

## Example 1 — Printer

Bad

```text
Printer

print()

scan()

fax()
```

A simple printer doesn't scan or fax.

---

Good

```text
Printable

Scannable

Faxable
```

Implement only what's required.

---

## Example 2 — Vehicle

Bad

```text
Vehicle

drive()

fly()

sail()
```

A car cannot fly.

A boat cannot drive.

---

Good

```text
Drivable

Flyable

Sailable
```

Each vehicle implements only the capabilities it supports.

---

## Example 3 — Payment Gateway

Bad

```text
PaymentGateway

pay()

refund()

emi()

crypto()
```

Not every payment provider supports all features.

---

Good

```text
Payment

Refundable

EmiSupported

CryptoSupported
```

---

## Example 4 — Smart Devices

Instead of

```text
SmartDevice

playMusic()

takePhoto()

call()

scanFingerprint()
```

Create

```text
MusicPlayer

Camera

Phone

FingerprintScanner
```

Each device implements only the features it provides.

---

# Benefits

* Smaller interfaces
* Easier maintenance
* Easier testing
* Better readability
* Lower coupling
* Better flexibility
* Prevents unnecessary implementations

---

# Drawbacks

* More interfaces
* Slight increase in project structure complexity

However, the design becomes much cleaner and easier to maintain.

---

# Common Mistakes

## Mistake 1

Creating a "God Interface"

```java
interface Employee {

    work();

    eat();

    sleep();

    drive();

    cook();

    code();

}
```

Every implementation is forced to implement everything.

---

## Mistake 2

Returning

```java
throw new UnsupportedOperationException();
```

or

```java
// Not Required
```

inside interface methods.

This usually indicates ISP is being violated.

---

## Mistake 3

Leaving methods empty.

```java
@Override
public void eat() {

}
```

If a method is never needed, the interface is too large.

---

# ISP vs SRP

| SRP                           | ISP                               |
| ----------------------------- | --------------------------------- |
| Focuses on classes            | Focuses on interfaces             |
| One class, one responsibility | One interface, one responsibility |
| Reduces reasons to change     | Prevents unnecessary dependencies |

---

# ISP vs LSP

| ISP                       | LSP                           |
| ------------------------- | ----------------------------- |
| Split large interfaces    | Correct inheritance hierarchy |
| Avoid unnecessary methods | Child must behave like parent |
| Uses interface design     | Uses inheritance design       |

---

# Interview Questions

## What is Interface Segregation Principle?

Clients should not be forced to depend on methods they do not use.

---

## Why is ISP important?

It prevents unnecessary implementations, making interfaces smaller, cleaner, and easier to maintain.

---

## How do you identify an ISP violation?

Signs include:

* Empty methods
* `UnsupportedOperationException`
* Large interfaces with unrelated methods

---

## Does ISP mean every interface should have one method?

No.

An interface can have multiple methods as long as they belong to the same responsibility.

Example:

```java
interface CrudRepository {

    save();

    update();

    delete();

    find();

}
```

All methods relate to persistence.

---

# Key Takeaways

* Create small, focused interfaces.
* Don't force classes to implement unused methods.
* Split large interfaces into role-based interfaces.
* Empty methods and `UnsupportedOperationException` often indicate an ISP violation.
* ISP improves flexibility and maintainability.

---

# 30-Second Revision

```text
Definition:
Clients should not be forced to depend on methods they do not use.

Remember:
✔ Many small interfaces.
✔ One interface = One responsibility.
✔ No empty methods.
✔ No UnsupportedOperationException.
✔ Classes implement only what they need.

Examples:
Printer
Vehicle
Payment Gateway
Smart Devices
```
