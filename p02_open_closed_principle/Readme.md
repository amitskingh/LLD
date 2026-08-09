# Open/Closed Principle (OCP)

## Definition

> **Software entities (classes, modules, functions, etc.) should be open for extension but closed for modification.**

This means you should be able to **add new functionality without changing existing, tested code**.

---

# Easy Definition

> **Extend behavior without modifying existing code.**

or

> **Add new features by adding new classes, not by changing old ones.**

---

# Why Do We Need OCP?

Imagine an e-commerce application that initially supports only **Credit Card** payments.

Later, the business asks for:

* UPI
* PayPal
* Wallet
* Net Banking

If you modify the same `PaymentService` every time a new payment method is added, you risk:

* Breaking existing functionality
* Introducing bugs
* Creating merge conflicts
* Violating maintainability

Instead, the existing code should remain untouched, and new functionality should be added through extension.

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
            throw new IllegalArgumentException("Invalid payment type");
        }
    }
}
```

Client

```java
PaymentService payment = new PaymentService();

payment.pay("UPI", 500);
```

---

## Problems

Every time a new payment method is introduced:

```text
Credit Card
UPI
PayPal
Wallet
Crypto
```

You must modify `PaymentService`.

Problems:

* Large `if-else` block
* Difficult to test
* Existing code changes frequently
* High risk of introducing bugs
* Violates Open/Closed Principle

---

# Good Example

## Step 1 — Create an Abstraction

```java
interface PaymentStrategy {

    void pay(double amount);

}
```

---

## Step 2 — Implement Different Payment Methods

### Credit Card

```java
class CreditCardPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {

        System.out.println("Paid ₹" + amount + " using Credit Card");

    }

}
```

---

### UPI

```java
class UpiPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {

        System.out.println("Paid ₹" + amount + " using UPI");

    }

}
```

---

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

## Step 3 — Payment Service

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

## Client

```java
public class Main {

    public static void main(String[] args) {

        PaymentService payment =
            new PaymentService(new UpiPayment());

        payment.processPayment(500);

    }

}
```

Adding Wallet support?

Simply create:

```java
class WalletPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {

        System.out.println("Paid using Wallet");

    }

}
```

Nothing else changes.

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

# How OCP Works

```text
Client

Chooses Payment Strategy

↓

PaymentService

↓

PaymentStrategy

↓

Card / UPI / Wallet / PayPal
```

The service remains unchanged while new strategies are added.

---

# Real-World Examples

## Example 1 — Payment Gateway

```text
Payment

Credit Card

UPI

Wallet

PayPal
```

New payment methods are added without modifying existing code.

---

## Example 2 — Notification System

Instead of

```text
if(email)

if(sms)

if(push)
```

Use

```text
Notification

EmailNotification

SMSNotification

PushNotification
```

---

## Example 3 — Discount System

Instead of

```text
if(Student)

if(Premium)

if(Employee)
```

Use

```text
Discount

StudentDiscount

PremiumDiscount

EmployeeDiscount
```

---

## Example 4 — Report Generator

Instead of

```text
if(PDF)

if(EXCEL)

if(CSV)
```

Use

```text
ReportGenerator

PdfReport

ExcelReport

CsvReport
```

---

# Benefits

* Existing code remains stable
* Easier maintenance
* Easier testing
* Easier to extend
* Supports plug-and-play architecture
* Reduces regression bugs

---

# Drawbacks

* More classes
* Requires interfaces or abstract classes
* Can slightly increase design complexity

---

# Common Mistakes

## Mistake 1

Using long `if-else` or `switch` statements.

```java
if(type.equals("UPI"))

else if(type.equals("Card"))

else if(type.equals("Wallet"))
```

---

## Mistake 2

Adding new features by editing old classes.

Every modification increases the chance of breaking existing functionality.

---

## Mistake 3

Not using abstractions.

Depending directly on concrete classes makes extension difficult.

---

# OCP and Strategy Pattern

The **Strategy Pattern** is one of the best examples of OCP.

Instead of changing existing code:

```java
if(type.equals("UPI"))
```

Create

```java
class UpiPayment implements PaymentStrategy
```

The service remains unchanged.

---

# OCP vs SRP

| SRP                                      | OCP                                               |
| ---------------------------------------- | ------------------------------------------------- |
| One class should have one responsibility | Classes should be extendable without modification |
| Focuses on responsibility                | Focuses on extensibility                          |
| Reduces reasons to change                | Reduces modifications                             |

---

# Interview Questions

## What is Open/Closed Principle?

Software entities should be open for extension but closed for modification.

---

## Why is OCP important?

It allows new features to be added without changing existing, tested code, reducing the risk of introducing bugs.

---

## Which design patterns commonly implement OCP?

* Strategy Pattern
* Factory Pattern
* Decorator Pattern
* Command Pattern

---

## Does OCP mean code should never change?

No.

It means **stable business logic should not require modification whenever new behavior is added**. Existing code may still change for bug fixes, refactoring, or evolving requirements.

---

# Key Takeaways

* Extend behavior instead of modifying existing code.
* Use interfaces or abstract classes as extension points.
* Replace long `if-else` blocks with polymorphism.
* OCP promotes maintainable and scalable software.
* Strategy Pattern is a classic implementation of OCP.

---

# 30-Second Revision

```text
Definition:
Open for Extension, Closed for Modification.

Remember:
✔ Add new features by creating new classes.
✔ Avoid changing existing tested code.
✔ Use interfaces and polymorphism.
✔ Replace if-else with Strategy Pattern.

Examples:
Payment Gateway
Notification System
Discount Engine
Report Generator
```
