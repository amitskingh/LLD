````markdown
# Abstract Factory Pattern

## Creational Design Pattern

> **The Abstract Factory Pattern provides an interface for creating families of related objects without specifying their concrete classes.**

### Easy Definition

> **One factory represents a product family and creates multiple related products from that family.**

---

# 1. Problem

Suppose we are building a UI application supporting:

```text
Windows
Mac
````

Each platform has multiple UI components:

```text
Button
Checkbox
```

Concrete products:

```text
WindowsButton
WindowsCheckbox

MacButton
MacCheckbox
```

These form product families:

```text
Windows Family
├── WindowsButton
└── WindowsCheckbox
```

```text
Mac Family
├── MacButton
└── MacCheckbox
```

The problem is that the client should not directly create platform-specific classes.

---

# 2. Solution

Create an **Abstract Factory** representing the product family.

```java
interface GUIFactory {

    Button createButton();

    Checkbox createCheckbox();

}
```

Then create one factory for each family:

```text
WindowsFactory
    ├── WindowsButton
    └── WindowsCheckbox
```

```text
MacFactory
    ├── MacButton
    └── MacCheckbox
```

The client chooses the **family**, and the factory creates the appropriate products.

---

# 3. Main Components

## 1. Abstract Products

Common interfaces:

```java
interface Button {
    void render();
}
```

```java
interface Checkbox {
    void render();
}
```

---

## 2. Concrete Products

Platform-specific implementations:

```text
WindowsButton
WindowsCheckbox

MacButton
MacCheckbox
```

---

## 3. Abstract Factory

Defines how to create the products:

```java
interface GUIFactory {

    Button createButton();

    Checkbox createCheckbox();

}
```

---

## 4. Concrete Factories

Each factory creates products belonging to one family.

```text
WindowsFactory
        ↓
WindowsButton
WindowsCheckbox
```

```text
MacFactory
        ↓
MacButton
MacCheckbox
```

---

# 4. UML

```text
                     GUIFactory
                    <<interface>>
                 +------------------+
                 | createButton()   |
                 | createCheckbox() |
                 +------------------+
                    ▲            ▲
                    │            │
          ┌─────────┘            └─────────┐
          │                                │
 WindowsFactory                         MacFactory
          │                                │
     ┌────┴────┐                      ┌────┴────┐
     ▼         ▼                      ▼         ▼
WindowsButton WindowsCheckbox      MacButton MacCheckbox
     ▲              ▲                  ▲          ▲
     │              │                  │          │
     └──── implements Button ──────────┘
```

Conceptually:

```text
                  GUIFactory
                 /          \
                ▼            ▼
       WindowsFactory      MacFactory
            │                  │
       ┌────┴────┐        ┌────┴────┐
       ▼         ▼        ▼         ▼
    Button   Checkbox   Button   Checkbox
    Windows   Windows     Mac      Mac
```

---

# 5. Client

The client works only with abstractions:

```java
GUIFactory factory =
    new WindowsFactory();

Button button =
    factory.createButton();

Checkbox checkbox =
    factory.createCheckbox();
```

The client does not need to know:

```text
WindowsButton
WindowsCheckbox
```

If we change:

```java
GUIFactory factory =
    new MacFactory();
```

the client automatically gets:

```text
MacButton
MacCheckbox
```

---

# 6. The Key Idea — Product Family

This is the most important concept.

When we write:

```java
GUIFactory factory =
    new WindowsFactory();
```

we are choosing:

> **The Windows product family.**

Then:

```java
factory.createButton();
```

means:

> Give me the Button from the Windows family.

And:

```java
factory.createCheckbox();
```

means:

> Give me the Checkbox from the Windows family.

---

# 7. Why Use Abstract Factory?

The main goals are:

```text
✔ Hide concrete product classes

✔ Create multiple related products

✔ Keep products from the same family together

✔ Prevent accidental mixing of product families

✔ Let the client work with abstractions
```

---

# 8. Family Consistency

Without Abstract Factory, the client could accidentally create:

```text
WindowsButton
+
MacCheckbox
```

With:

```java
GUIFactory factory =
    new WindowsFactory();
```

both products come from:

```text
WindowsFactory
```

so we get:

```text
WindowsButton
WindowsCheckbox
```

The factory maintains **family consistency**.

---

# 9. Abstract Factory vs Simple Factory

## Simple Factory

Usually focuses on creating **one product type**.

```text
NotificationFactory
        │
        ▼
   Notification
    /   |   \
 Email SMS Push
```

The factory decides:

> **Which product should I create?**

---

## Abstract Factory

Creates **multiple related product types**.

```text
WindowsFactory
      │
      ├── Button
      └── Checkbox
```

The factory represents:

> **Which product family should I use?**

---

# 10. Abstract Factory vs Factory Method

This distinction is very important.

### Factory Method

```text
Creator
   │
   └── createProduct()
           │
           ▼
      One Product
```

> **Common workflow + subclass decides which product to create.**

---

### Abstract Factory

```text
Factory
   │
   ├── createProductA()
   ├── createProductB()
   └── createProductC()
```

> **One factory creates a family of related products.**

---

# 11. Three Factory Concepts

Keep these three lines in memory:

```text
Simple Factory
→ Centralize object creation
```

```text
Factory Method
→ Subclasses decide object creation
```

```text
Abstract Factory
→ Create families of related objects
```

---

# 12. Abstract Factory and OCP

Suppose we already support:

```text
Windows
Mac
```

Now we want:

```text
Linux
```

We can add:

```text
LinuxFactory
├── LinuxButton
└── LinuxCheckbox
```

The existing client can continue working with:

```java
GUIFactory
```

We don't need to modify the existing Windows or Mac factories.

The system is extended by adding a new product family.

---

# 13. Advantages

* Creates related objects consistently.
* Keeps concrete classes hidden from the client.
* Supports switching entire product families.
* Promotes programming to interfaces.
* Reduces coupling between client and concrete products.
* Makes adding a new product family easier.

---

# 14. Disadvantages

* More classes and interfaces.
* Can be overengineering for simple object creation.
* Adding a **new product type** can require changing every concrete factory.

For example, if we add:

```text
Textbox
```

then we need:

```java
createTextbox();
```

in `GUIFactory` and implement it in:

```text
WindowsFactory
MacFactory
LinuxFactory
...
```

This is an important tradeoff.

---

# 15. Common Mistakes

### ❌ Thinking Abstract Factory creates only one object

The key idea is creating a **family of related products**.

---

### ❌ Confusing Factory Method and Abstract Factory

```text
Factory Method
→ One creation method / product focus
```

```text
Abstract Factory
→ Multiple creation methods / product family
```

---

### ❌ Thinking "Abstract" means it must use an abstract class

Abstract Factory can be defined using an interface:

```java
interface GUIFactory {

    Button createButton();

    Checkbox createCheckbox();
}
```

An abstract class could also be used.

The important part is the **family of related products**, not the Java keyword `abstract`.

---

### ❌ Creating concrete products directly in the client

Avoid:

```java
new WindowsButton();
new WindowsCheckbox();
```

when the goal is to keep the client independent of platform-specific implementations.

Prefer:

```java
factory.createButton();
factory.createCheckbox();
```

---

### ❌ Mixing product families

Avoid:

```text
WindowsButton
+
MacCheckbox
```

when the system expects products from one consistent family.

---

# 16. When Should You Use It?

Use Abstract Factory when you have:

```text
Multiple product types
        +
Multiple families
        +
Products within a family must work together
```

Example:

```text
Operating Systems
    ↓
Windows / Mac / Linux

Components
    ↓
Button / Checkbox / Textbox
```

---

# 17. When Should You NOT Use It?

If you only need:

```text
One product
```

Abstract Factory is probably unnecessary.

For example:

```text
EmailNotification
SMSNotification
PushNotification
```

If all you need is one Notification object, a Simple Factory may be enough.

---

# 18. Interview Questions

### What is Abstract Factory?

> A creational design pattern that provides an interface for creating families of related objects without specifying their concrete classes.

---

### What problem does it solve?

> It allows the client to create multiple related products while remaining independent of their concrete implementations.

---

### What is the main difference from Factory Method?

> Factory Method focuses on delegating the creation of a product, while Abstract Factory creates a family of related products.

---

### Why use a factory family?

> To ensure that related products belong to the same family and work consistently together.

---

### Can Abstract Factory use an interface?

> Yes. The Abstract Factory is commonly represented by an interface, but it can also be implemented using an abstract class.

---

### What happens if we add a new product family?

Example:

```text
LinuxFactory
```

We generally add:

```text
LinuxButton
LinuxCheckbox
LinuxFactory
```

Existing families remain unchanged.

---

### What happens if we add a new product type?

Example:

```text
Textbox
```

We need to update:

```java
interface GUIFactory {

    Button createButton();

    Checkbox createCheckbox();

    Textbox createTextbox();
}
```

and every concrete factory must implement it.

This is an important tradeoff of Abstract Factory.

---

# 19. Quick Comparison

|                    | Simple Factory           | Factory Method    | Abstract Factory          |
| ------------------ | ------------------------ | ----------------- | ------------------------- |
| Main purpose       | Centralize creation      | Delegate creation | Create product families   |
| Products           | Usually one product type | One product focus | Multiple related products |
| Who decides?       | Factory                  | Concrete creator  | Concrete factory/family   |
| Common workflow    | Not required             | Often important   | Not the main focus        |
| Family consistency | No                       | No                | Yes                       |
| Complexity         | Low                      | Medium            | Higher                    |

---

# 20. Memory Trick

```text
Simple Factory
      ↓
"Which product?"
```

```text
Factory Method
      ↓
"Which creator creates the product?"
```

```text
Abstract Factory
      ↓
"Which family of products?"
```

---

# Final Takeaway

```text
                    ABSTRACT FACTORY
                           │
                    Choose a FAMILY
                           │
              ┌────────────┴────────────┐
              │                         │
              ▼                         ▼
        Product A                  Product B
              │                         │
              └──── Same Family ────────┘
```

> **Abstract Factory = One factory represents a product family and creates multiple related products from that family.**

```
```
