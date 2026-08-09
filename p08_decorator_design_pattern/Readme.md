# Decorator Design Pattern (Structural Design Pattern)

## Definition

> **The Decorator Pattern allows adding new functionality to an object dynamically without modifying its existing code.**

### Simple Definition

A **Decorator** wraps an existing object and adds new behavior while preserving the same interface.

---

# Problem Statement

Suppose you're building a coffee shop.

Available items:

```text
Plain Coffee = ₹100

Milk = +₹20

Sugar = +₹10

Chocolate = +₹40
```

Customers can order:

```text
Coffee

Coffee + Milk

Coffee + Sugar

Coffee + Milk + Sugar

Coffee + Milk + Chocolate

Coffee + Milk + Sugar + Chocolate
```

---

# Why Not Inheritance?

Using inheritance:

```text
Coffee
│
├── MilkCoffee
├── SugarCoffee
├── ChocolateCoffee
├── MilkSugarCoffee
├── MilkChocolateCoffee
├── SugarChocolateCoffee
├── MilkSugarChocolateCoffee
...
```

As new toppings are added, the number of subclasses grows rapidly.

This is known as the **Class Explosion Problem**.

---

# Solution

Instead of creating new subclasses, wrap the object.

```text
Plain Coffee

↓

Milk Decorator

↓

Sugar Decorator

↓

Chocolate Decorator
```

Each decorator adds one new responsibility.

---

# Real World Examples

- Coffee with toppings
- Pizza with extra cheese & toppings
- Java I/O Streams (`BufferedInputStream`, `BufferedReader`)
- Spring Security Filters
- HTTP Middleware
- Logging Wrappers

---

# Core Idea

A Decorator has two relationships simultaneously.

```text
MilkDecorator

IS-A Coffee

HAS-A Coffee
```

Why?

- **IS-A Coffee** → So it can replace the original Coffee.
- **HAS-A Coffee** → So it can wrap another Coffee and delegate work.

This combination enables unlimited decoration.

---

# Participants

## 1. Component

Common interface.

```java
interface Coffee {

    int cost();

    String description();

}
```

---

## 2. Concrete Component

Actual object.

```text
PlainCoffee
```

---

## 3. Decorator

Abstract wrapper.

```text
CoffeeDecorator
```

Responsibilities:

- Implements `Coffee`
- Stores another `Coffee`
- Delegates work to wrapped object

---

## 4. Concrete Decorators

Adds new behaviour.

Examples:

```text
MilkDecorator

SugarDecorator

ChocolateDecorator
```

---

# UML Diagram

```text
                     +----------------+
                     |    Coffee      |
                     +----------------+
                     | +cost()        |
                     | +description() |
                     +----------------+
                             ▲
                  implements │
                             │
               +-------------------------+
               |      PlainCoffee        |
               +-------------------------+

                             ▲
                  implements │
                             │
             +----------------------------+
             |     CoffeeDecorator        |
             +----------------------------+
             | - Coffee coffee            |
             +----------------------------+
             | +cost()                    |
             | +description()             |
             +----------------------------+
                             ▲
                   ┌─────────┴─────────┐
                   │                   │
         MilkDecorator         SugarDecorator
```

---

# Flow Diagram

```text
Plain Coffee

↓

Milk Decorator

↓

Sugar Decorator

↓

Chocolate Decorator

↓

Customer
```

Each decorator:

1. Calls wrapped object's method.
2. Adds its own behaviour.
3. Returns the result.

---

# Execution Flow

Suppose:

```java
Coffee coffee =
    new ChocolateDecorator(
        new SugarDecorator(
            new MilkDecorator(
                new PlainCoffee()
            )
        )
    );
```

Calling:

```java
coffee.cost();
```

Execution:

```text
ChocolateDecorator.cost()

↓

SugarDecorator.cost()

↓

MilkDecorator.cost()

↓

PlainCoffee.cost()

↓

100

↑

+20

↑

120

↑

+10

↑

130

↑

+40

↑

170
```

Final Cost:

```text
₹170
```

---

# Why Does Decorator Implement the Same Interface?

Because the client works with the interface.

Example:

```java
void printPrice(Coffee coffee) {

    System.out.println(coffee.cost());

}
```

This method accepts **any Coffee**.

Therefore all decorators must also be `Coffee`.

```text
PlainCoffee

↓

MilkDecorator

↓

SugarDecorator

↓

ChocolateDecorator
```

Every object in this chain **is a Coffee**.

This allows decorators to replace the original object without changing client code.

---

# Why Abstract Decorator?

```java
abstract class CoffeeDecorator
```

Reasons:

- Cannot create a generic decorator.
- Shares common wrapping logic.
- Avoids duplicate code across decorators.

---

# SOLID Principles Used

| Principle                    | Usage                                                 |
| ---------------------------- | ----------------------------------------------------- |
| SRP                          | Each decorator adds one responsibility                |
| OCP                          | Add new decorators without modifying existing classes |
| DIP                          | Decorators depend on `Coffee` interface               |
| LSP                          | Every decorator can replace a `Coffee`                |
| Composition over Inheritance | Core principle used by Decorator                      |

---

# Advantages

- Avoids class explosion.
- Adds behaviour dynamically.
- Follows Open/Closed Principle.
- Easy to combine multiple behaviours.
- Promotes composition over inheritance.

---

# Disadvantages

- Creates many small classes.
- Debugging wrapped chains can be harder.
- Order of decorators may affect behaviour.

---

# Common Mistakes

### ❌ Using inheritance for every combination

```text
MilkSugarCoffee

MilkChocolateCoffee

MilkSugarChocolateCoffee
```

Use decorators instead.

---

### ❌ Decorator does not implement Component

```java
class MilkDecorator
```

Should be:

```java
class MilkDecorator implements Coffee
```

Otherwise it cannot replace a Coffee.

---

### ❌ Forgetting to delegate

Wrong:

```java
return 20;
```

Correct:

```java
return coffee.cost() + 20;
```

Decorator should extend behaviour, not replace it.

---

### ❌ Tight coupling

Decorators should depend on:

```java
Coffee
```

Not

```java
PlainCoffee
```

---

# Decorator vs Inheritance

| Inheritance                     | Decorator                  |
| ------------------------------- | -------------------------- |
| Behaviour fixed at compile time | Behaviour added at runtime |
| Many subclasses                 | Few reusable decorators    |
| Class explosion                 | Flexible composition       |
| Strong coupling                 | Loose coupling             |

---

# Interview Questions

### Why not inheritance?

Because multiple combinations lead to class explosion.

---

### Why composition?

Composition allows behaviour to be added dynamically.

---

### Why implement the same interface?

So decorators can be used anywhere the original object is expected.

---

### Why store another Coffee?

To delegate existing behaviour before adding new behaviour.

---

### Why is the Decorator abstract?

It contains shared wrapping logic and should not be instantiated directly.

---

### Which relationship is used?

Both.

```text
Decorator

IS-A Coffee

HAS-A Coffee
```

---

### Which SOLID principles are followed?

- SRP
- OCP
- DIP
- LSP
- Composition over Inheritance

---

# Key Takeaways

```text
✔ Structural Design Pattern

✔ Adds behaviour dynamically

✔ Avoids Class Explosion

✔ Uses Composition instead of Inheritance

✔ Decorator IS-A Component

✔ Decorator HAS-A Component

✔ Client works with the interface

✔ Delegates existing behaviour before adding new behaviour

✔ Follows OCP and DIP

✔ One of the most frequently asked LLD interview patterns
```

---

# Memory Trick

```text
Original Object

↓

Wrap

↓

Wrap Again

↓

Wrap Again

↓

Final Object
```

Remember:

> **Decorators don't modify the original object—they wrap it and add behaviour.**
