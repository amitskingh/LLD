For **LLD**, you don't need to learn all of UML. You mainly need the relationships you'll use while designing classes.

## UML arrows you should know for LLD

### 1. Inheritance — `is-a`

```text
Dog ─────────▷ Animal
```

**Direction:** Child → Parent

```java
class Dog extends Animal
```

Think:

> **Dog is an Animal**

The **hollow triangle always points to the parent**.

---

### 2. Interface Implementation — `implements`

```text
CreditCard ─────────▷ Payment
```

Where `Payment` is an interface.

```java
class CreditCard implements Payment
```

**Direction:** Class → Interface

Again, the hollow triangle points toward the interface.

---

### 3. Association — `has a relationship with`

```text
Customer ───────── Order
```

There may be **no arrow**.

For example:

```java
class Customer {
    List<Order> orders;
}
```

If you want to show navigability:

```text
Customer ─────────> Order
```

Meaning:

> Customer knows/has access to Order.

---

### 4. Aggregation — weak `has-a`

```text
Team ◇──────── Player
```

The **empty diamond is on the owner/whole side**.

```java
class Team {
    List<Player> players;
}
```

But:

> Player can exist without Team.

For example, a player can leave one team and join another.

---

### 5. Composition — strong `has-a`

```text
House ◆──────── Room
```

The **filled diamond is on the owner/whole side**.

```java
class House {
    List<Room> rooms;
}
```

Conceptually:

> Room belongs strongly to House.

If the `House` is destroyed, its `Room`s are also considered destroyed in the model.

---

### 6. Dependency — `uses`

```text
OrderService - - - - > PaymentService
```

**Direction:**

```text
OrderService → PaymentService
```

Meaning:

> `OrderService` uses/depends on `PaymentService`.

For example:

```java
class OrderService {

    void placeOrder(PaymentService paymentService) {
        paymentService.pay();
    }
}
```

## 7. UML Visibility / Access Modifiers

The symbols inside a UML class represent **visibility**.

| Symbol | Meaning         | Java        |
| ------ | --------------- | ----------- |
| `+`    | Public          | `public`    |
| `-`    | Private         | `private`   |
| `#`    | Protected       | `protected` |
| `~`    | Package-private | no modifier |

Example:

```text
+--------------------------+
| PaymentService           |
+--------------------------+
| - strategy               |
+--------------------------+
| + processPayment()       |
+--------------------------+
```

Equivalent Java:

```java
class PaymentService {

    private PaymentStrategy strategy;

    public void processPayment(double amount) {
        strategy.pay(amount);
    }
}
```

### Important distinction

Symbols **inside the class** describe visibility:

```text
- strategy
+ processPayment()
```

Symbols **between classes** describe relationships:

```text
────▷     Inheritance / Implementation
────>     Association / Navigability
◆────     Composition
◇────     Aggregation
- - - ->  Dependency
```

---

## 8. How to Read UML Arrow Direction

Do **not** interpret the arrow based on where the class is physically drawn.

For example:

```text
PaymentService
      |
      |
      ▼
PaymentStrategy
```

and:

```text
PaymentService ─────────> PaymentStrategy
```

represent the same direction.

The important question is:

> **What relationship is this arrow expressing?**

### Inheritance

```text
Dog ─────────▷ Animal
```

Read as:

> Dog inherits from Animal.

**Child → Parent**

---

### Interface Implementation

```text
CreditCardPayment ─────▷ PaymentStrategy
```

Read as:

> CreditCardPayment implements PaymentStrategy.

**Class → Interface**

---

### Dependency

```text
OrderService - - - - > PaymentService
```

Read as:

> OrderService uses/depends on PaymentService.

**Dependent → Dependency**

---

### Association

```text
Customer ─────────> Order
```

Read as:

> Customer knows about / can navigate to Order.

**Source → Target**

### Mental Rule

Do not memorize:

> "Arrow points upward."

Instead memorize:

> **"Arrow points toward the class that the relationship refers to."**

The physical layout of the UML diagram doesn't matter.

---

## 9. Association vs Aggregation vs Composition

These three are often confused because all can represent some form of **has-a relationship**.

### Association

```text
Customer ───────── Order
```

Simply means:

> Customer and Order are related.

There is no strong ownership implied.

Example:

```java
class Customer {
    private List<Order> orders;
}
```

---

### Aggregation — Weak Ownership

```text
Team ◇──────── Player
```

Means:

> Team contains/groups Players, but Players have an independent lifecycle.

Example:

```text
Team A
 ├── Player 1
 └── Player 2

Team A destroyed

Player 1
Player 2
```

The Players still exist and can join another Team.

```text
Team B
 ├── Player 1
 └── Player 2
```

### Key idea

> **Aggregation = "You are with me."**

The contained object has an independent identity and lifecycle.

---

### Composition — Strong Ownership

```text
Order ◆──────── OrderItem
```

Means:

> Order strongly owns its OrderItems.

Conceptually:

```text
Order #101
 ├── Item 1
 ├── Item 2
 └── Item 3
```

If the Order is removed from the model, its OrderItems are also removed.

### Key idea

> **Composition = "You are a part of me."**

The contained object's lifecycle is tied to the owner.

---

## 10. Important Nuance: Independent Existence Is Not the Whole Rule

A common oversimplification is:

> "If B can exist without A → aggregation."

This is **not sufficient**.

The real questions are:

1. Does A contain B?
2. Does A strongly own B?
3. Is B's lifecycle controlled by A?
4. Does B have an independent identity?
5. Can the same B naturally move between different A objects?

For example:

```text
Car ───── Engine
```

You might initially think this must be composition because:

> "A Car has an Engine."

But an Engine can have its own identity and can potentially be removed from one Car and installed in another.

```text
Car A
  │
 Engine E1
  │
  ↓ removed

Car B
  │
 Engine E1
```

Therefore, **Car → Engine is domain-dependent**.

It could be modeled as association, aggregation, or composition depending on what the system is actually modeling.

### Important LLD principle

> UML relationships describe the **domain model**, not physical reality.

Do not decide a relationship only by asking:

> "Can this object physically exist without the other?"

Instead ask:

> **"Who owns this object and who controls its lifecycle in this system?"**

---

## 11. Composition vs Inheritance

This distinction is extremely important in LLD.

### Inheritance = IS-A

```text
Dog ─────▷ Animal
```

```java
class Dog extends Animal
```

Read:

> Dog IS an Animal.

---

### Composition = HAS-A

```text
Car ◆──── Engine
```

```java
class Car {
    private Engine engine;
}
```

Read:

> Car HAS an Engine.

Therefore:

```text
IS-A  → Inheritance
HAS-A → Composition / Aggregation / Association
```

### Common mistake

```text
Car ─────▷ Engine
```

❌ Wrong because:

> Car is not an Engine.

Instead:

```text
Car ◆──── Engine
```

or another appropriate has-a relationship.

---

## 12. Prefer Composition Over Inheritance

In LLD, a useful design principle is:

> **Prefer composition over inheritance when inheritance does not represent a genuine IS-A relationship.**

Example:

Instead of:

```text
Car ─────▷ Engine
```

use:

```text
Car ◆──── Engine
```

because the Car **has an Engine** rather than **being an Engine**.

Composition also makes behavior easier to replace.

```java
class Car {

    private Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
    }
}
```

Now different Engine implementations can potentially be supplied without changing the Car hierarchy.

---

## 13. Practical LLD Relationship Decision Tree

When designing two classes, ask:

### Step 1 — Is it an IS-A relationship?

```text
Dog IS-A Animal
```

→ **Inheritance**

```text
Dog ─────▷ Animal
```

---

### Step 2 — Is a class implementing an interface?

```text
CreditCardPayment implements PaymentStrategy
```

→ **Implementation**

```text
CreditCardPayment ─────▷ PaymentStrategy
```

---

### Step 3 — Does one class simply use another?

```text
OrderService uses PaymentService
```

→ **Dependency**

```text
OrderService - - - - > PaymentService
```

---

### Step 4 — Does one object know/have another?

```text
Customer has Orders
```

→ **Association**

```text
Customer ───── Order
```

---

### Step 5 — Is there a whole-part relationship?

Ask:

> Does the whole strongly own the part and control its lifecycle?

**Yes**

→ Composition

```text
Order ◆──── OrderItem
```

**No, the part has an independent lifecycle**

→ Aggregation / Association

```text
Team ◇──── Player
```

---

## 14. LLD UML Quick Reference

```text
╔══════════════════════════════════════════════════════╗
║                 UML FOR LLD                          ║
╚══════════════════════════════════════════════════════╝


1. INHERITANCE — IS-A

Dog ─────────▷ Animal

Child → Parent


2. IMPLEMENTATION — IMPLEMENTS

CreditCardPayment ─────▷ PaymentStrategy

Class → Interface


3. ASSOCIATION — KNOWS / HAS RELATIONSHIP

Customer ───────── Order

Objects are related.


4. AGGREGATION — WEAK HAS-A

Team ◇──────── Player

Player has independent lifecycle.


5. COMPOSITION — STRONG HAS-A

Order ◆──────── OrderItem

Part's lifecycle is tied to owner.


6. DEPENDENCY — USES

OrderService - - - - > PaymentService

Dependent → Dependency


7. VISIBILITY

+  public
-  private
#  protected
~  package-private
```

### ⭐ The three questions to remember

When looking at an LLD diagram:

```text
IS-A?
  ↓
Inheritance / Implementation

HAS-A?
  ↓
Association / Aggregation / Composition

USES?
  ↓
Dependency
```

And for **HAS-A**:

```text
Simple relationship
      ↓
 Association

Weak ownership
      ↓
 Aggregation

Strong ownership + lifecycle control
      ↓
 Composition
```


---

# ⭐ LLD Cheat Sheet

This is the one I'd recommend putting in your handwritten notes:

```text
                 UML for LLD

Inheritance
Dog ─────────▷ Animal
        is-a
        Child → Parent


Implementation
CreditCard ───▷ Payment
             implements
             Class → Interface


Association
Customer ─────── Order
       has relationship with


Aggregation
Team ◇──────── Player
     weak has-a
     diamond → owner


Composition
House ◆──────── Room
      strong has-a
      diamond → owner


Dependency
OrderService - - - > PaymentService
              uses
              dependent → dependency
```

## The BIG distinction

When you're doing LLD, you'll repeatedly ask:

### **"Is-a?"**

Use **Inheritance / Implementation**

```text
Dog ───▷ Animal
```

### **"Has-a?"**

Usually use **Composition/Aggregation**

```text
Car ◆── Engine
```

### **"Uses?"**

Use **Dependency**

```text
OrderService - - - > PaymentService
```

And this is especially important for LLD interviews:

> **Prefer composition over inheritance** when you don't genuinely have an `is-a` relationship.

For example:

```text
❌ Car ───▷ Engine

A Car is NOT an Engine.
```

Instead:

```text
✅ Car ◆──── Engine

Car HAS an Engine.
```