# Strategy Design Pattern

## Definition

> **The Strategy Pattern is a behavioral design pattern that defines a family of algorithms, encapsulates each one into a separate class, and makes them interchangeable at runtime.**

Instead of hardcoding different behaviors inside a class, we move each behavior into its own strategy class.

---

# Easy Definition

> **Replace multiple `if-else` or `switch` statements with interchangeable classes.**

or

> **Choose an algorithm at runtime.**

---

# Why Do We Need Strategy Pattern?

Imagine an e-commerce application.

Initially, it supports only **Credit Card** payment.

Later, the business asks for:

* UPI
* PayPal
* Net Banking
* Wallet
* Crypto

Without Strategy Pattern, the payment service keeps growing.

Every new payment method requires modifying existing code.

This violates the **Open/Closed Principle (OCP)**.

---

# Bad Example

## Payment Service

```java
class PaymentService {

    public void pay(String paymentType, double amount) {

        if (paymentType.equals("CARD")) {
            System.out.println("Paid ₹" + amount + " using Credit Card");
        }
        else if (paymentType.equals("UPI")) {
            System.out.println("Paid ₹" + amount + " using UPI");
        }
        else if (paymentType.equals("PAYPAL")) {
            System.out.println("Paid ₹" + amount + " using PayPal");
        }
        else {
            throw new IllegalArgumentException("Invalid payment method");
        }
    }
}
```

Usage:

```java
public class Main {

    public static void main(String[] args) {

        PaymentService payment = new PaymentService();

        payment.pay("UPI", 500);
    }
}
```

---

## Problems

❌ Large `if-else` block

❌ Difficult to maintain

❌ Difficult to test

❌ Every new payment method modifies existing code

❌ Violates Open/Closed Principle

---

# Good Example (Using Strategy Pattern)

## Step 1 — Create Strategy Interface

```java
interface PaymentStrategy {

    void pay(double amount);

}
```

---

## Step 2 — Implement Different Strategies

### Credit Card

```java
class CreditCardPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {

        System.out.println("Paid ₹" + amount + " using Credit Card");

    }

}
```

### UPI

```java
class UpiPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {

        System.out.println("Paid ₹" + amount + " using UPI");

    }

}
```

### PayPal

```java
class PaypalPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {

        System.out.println("Paid ₹" + amount + " using PayPal");

    }

}
```

---

## Step 3 — Context Class

```java
class PaymentService {

    private PaymentStrategy paymentStrategy;

    public PaymentService(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment(double amount) {
        paymentStrategy.pay(amount);
    }

}
```

---

## Step 4 — Client Code

```java
public class Main {

    public static void main(String[] args) {

        PaymentStrategy strategy = new UpiPayment();

        PaymentService payment = new PaymentService(strategy);

        payment.processPayment(500);

    }

}
```

Output

```text
Paid ₹500 using UPI
```

Switching to another payment method:

```java
PaymentStrategy strategy = new CreditCardPayment();
```

No changes to `PaymentService`.

---

# UML Structure

```text
                         <<interface>>
                    +----------------------+
                    |   PaymentStrategy    |
                    +----------------------+
                    | + pay(amount)        |
                    +----------^-----------+
                               |
              ┌────────────────┼────────────────┐
              │                │                │
     +----------------+ +----------------+ +----------------+
     |CreditCard      | |UpiPayment      | |PaypalPayment   |
     +----------------+ +----------------+ +----------------+


                    PaymentStrategy
                         ▲
                         │
                         │ has / uses
                         │
                 +----------------------+
                 |   PaymentService     |
                 +----------------------+
                 | - strategy           |
                 +----------------------+
                 | + processPayment()   |
                 +----------------------+
```

---

# How Strategy Pattern Works

```text
Client
   |
Chooses Strategy
   |
   v
PaymentService
   |
Calls
   |
PaymentStrategy.pay()
   |
----------------------------
|           |              |
Card       UPI         PayPal
```

---

# Real-World Examples

## Google Maps

Different route strategies:

```text
Navigation

Car Route

Bike Route

Walking Route

Public Transport
```

The user chooses one strategy at runtime.

---

## File Compression

```text
Compression

ZIP

RAR

7Z
```

Different algorithms.

Same interface.

---

## Sorting

```text
Sorter

Quick Sort

Merge Sort

Heap Sort
```

Choose the sorting algorithm depending on the data.

---

## Payment Gateway

```text
Payment

Credit Card

UPI

Wallet

PayPal
```

Exactly the example above.

---

# Benefits

* Follows Open/Closed Principle
* Eliminates long `if-else` or `switch`
* Easy to extend
* Easy to unit test
* Promotes composition over inheritance
* Algorithms are reusable

---

# Drawbacks

* More classes
* Slightly more complex for very small projects
* Client must choose the correct strategy

---

# When to Use Strategy Pattern

Use it when:

* Multiple algorithms solve the same problem.
* Algorithms need to change at runtime.
* Large `if-else` or `switch` blocks exist.
* New behaviors are added frequently.

---

# When NOT to Use

Avoid it when:

* There is only one algorithm.
* Behavior never changes.
* Creating multiple strategy classes adds unnecessary complexity.

---

# Strategy vs State Pattern

| Strategy                                  | State                                 |
| ----------------------------------------- | ------------------------------------- |
| Client chooses the algorithm              | Object changes behavior automatically |
| Focus is selecting an algorithm           | Focus is object state                 |
| Algorithm rarely changes during execution | State changes frequently              |
| Example: Payment Method                   | Example: Traffic Light                |

---

# Interview Questions

## What problem does Strategy Pattern solve?

It removes large conditional statements by moving each algorithm into its own class.

---

## Which SOLID principle does it support?

Primarily:

* Open/Closed Principle (OCP)
* Dependency Inversion Principle (DIP)

---

## Why is it called Strategy?

Because each implementation represents a different **strategy (algorithm)** to solve the same problem.

---

## Difference between Strategy and Factory?

| Strategy              | Factory                    |
| --------------------- | -------------------------- |
| Chooses behavior      | Creates objects            |
| Focuses on algorithms | Focuses on object creation |
| Behavioral Pattern    | Creational Pattern         |

---

# Key Takeaways

* Strategy Pattern encapsulates algorithms into separate classes.
* The client can switch algorithms without modifying existing code.
* It replaces long `if-else` or `switch` statements.
* It follows **Open/Closed Principle**.
* It promotes **composition over inheritance**.

---

# 30-Second Revision

```text
Definition:
Encapsulate algorithms into separate classes and make them interchangeable.

Use When:
✔ Multiple algorithms exist.
✔ Behavior changes at runtime.
✔ Large if-else or switch statements exist.

Advantages:
✔ Flexible
✔ Extensible
✔ Testable
✔ Follows OCP

Examples:
• Payment Methods
• Google Maps Routes
• Sorting Algorithms
• File Compression
```
