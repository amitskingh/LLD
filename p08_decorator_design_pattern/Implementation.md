# Decorator Pattern - Implementation Guide

This document explains the implementation of the **Decorator Design Pattern** step by step using the Coffee example.

---

# Problem Statement

Build a coffee ordering system.

Available items:

```text
Plain Coffee = ₹100

Milk = +₹20

Sugar = +₹10

Chocolate = +₹40
```

Possible orders:

```text
Coffee

Coffee + Milk

Coffee + Sugar

Coffee + Milk + Sugar

Coffee + Milk + Chocolate

Coffee + Milk + Sugar + Chocolate
```

We don't want a separate class for every possible combination.

---

# Final Architecture

```text
                    Coffee (Interface)
                           ▲
                           │
               ┌───────────┴────────────┐
               │                        │
        PlainCoffee             CoffeeDecorator
                                         ▲
                        ┌────────────────┼────────────────┐
                        │                │                │
                MilkDecorator     SugarDecorator   ChocolateDecorator
```

---

# Step 1 — Create the Component

Every coffee should expose the same behaviour.

```java
interface Coffee {

    int cost();

    String description();

}
```

### Why?

This is the common contract.

Everything in our system should behave like a Coffee.

---

# Step 2 — Create the Concrete Component

The simplest coffee.

```java
class PlainCoffee implements Coffee {

    @Override
    public int cost() {
        return 100;
    }

    @Override
    public String description() {
        return "Plain Coffee";
    }

}
```

Output

```text
Description : Plain Coffee

Cost : 100
```

---

# Step 3 — Create the Decorator

```java
abstract class CoffeeDecorator implements Coffee {

    protected Coffee coffee;

    CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

}
```

## Why Abstract?

There is no such thing as a generic decorator.

Only

```text
MilkDecorator

SugarDecorator

ChocolateDecorator
```

should be instantiated.

---

## Why implement Coffee?

Because every decorator should behave exactly like a Coffee.

This allows:

```java
Coffee coffee =
    new MilkDecorator(
        new PlainCoffee()
    );
```

---

## Why store another Coffee?

```java
protected Coffee coffee;
```

Decorator wraps another Coffee.

```text
MilkDecorator

HAS-A Coffee
```

---

# Step 4 — MilkDecorator

```java
class MilkDecorator extends CoffeeDecorator {

    MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int cost() {
        return coffee.cost() + 20;
    }

    @Override
    public String description() {
        return coffee.description() + ", Milk";
    }

}
```

## What happens?

```text
PlainCoffee.cost()

↓

100

↓

Milk adds 20

↓

120
```

Description

```text
Plain Coffee

↓

Plain Coffee, Milk
```

---

# Step 5 — SugarDecorator

```java
class SugarDecorator extends CoffeeDecorator {

    SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int cost() {
        return coffee.cost() + 10;
    }

    @Override
    public String description() {
        return coffee.description() + ", Sugar";
    }

}
```

---

# Step 6 — ChocolateDecorator

```java
class ChocolateDecorator extends CoffeeDecorator {

    ChocolateDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int cost() {
        return coffee.cost() + 40;
    }

    @Override
    public String description() {
        return coffee.description() + ", Chocolate";
    }

}
```

---

# Step 7 — Client Code

## Plain Coffee

```java
Coffee coffee = new PlainCoffee();
```

Output

```text
Plain Coffee

100
```

---

## Coffee + Milk

```java
Coffee coffee =
    new MilkDecorator(
        new PlainCoffee()
    );
```

Output

```text
Plain Coffee, Milk

120
```

---

## Coffee + Milk + Sugar

```java
Coffee coffee =
    new SugarDecorator(
        new MilkDecorator(
            new PlainCoffee()
        )
    );
```

Output

```text
Plain Coffee, Milk, Sugar

130
```

---

## Coffee + Milk + Sugar + Chocolate

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

Output

```text
Plain Coffee, Milk, Sugar, Chocolate

170
```

---

# Execution Flow

Suppose

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

When

```java
coffee.cost();
```

is called:

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

The execution travels **inside-out**, then returns **outside-in**.

---

# Understanding Delegation

Decorator never replaces existing behaviour.

Instead:

```java
return coffee.cost() + 20;
```

Step 1

```text
Ask wrapped object
```

Step 2

```text
Add your behaviour
```

Step 3

```text
Return result
```

This is called **Delegation**.

---

# Why Decorator Implements Coffee

Suppose a method accepts

```java
void printPrice(Coffee coffee)
```

Then all of these should work.

```java
printPrice(new PlainCoffee());
```

```java
printPrice(
    new MilkDecorator(
        new PlainCoffee()
    )
);
```

```java
printPrice(
    new SugarDecorator(
        new MilkDecorator(
            new PlainCoffee()
        )
    )
);
```

Why?

Because every decorator **IS-A Coffee**.

---

# Why Decorator Stores Coffee

```java
private Coffee coffee;
```

Without this,

Decorator would have nothing to decorate.

The wrapped object performs the existing work.

Decorator simply adds more behaviour.

---

# IS-A + HAS-A

Decorator simultaneously has two relationships.

```text
MilkDecorator

IS-A Coffee

HAS-A Coffee
```

IS-A

```text
Allows replacing Coffee.
```

HAS-A

```text
Allows wrapping another Coffee.
```

This combination makes chaining possible.

---

# Object Chain

```text
Coffee coffee =
    new ChocolateDecorator(
        new SugarDecorator(
            new MilkDecorator(
                new PlainCoffee()
            )
        )
    );
```

Visualized

```text
ChocolateDecorator

↓

SugarDecorator

↓

MilkDecorator

↓

PlainCoffee
```

---

# Client View

Client always sees

```java
Coffee
```

Never

```java
MilkDecorator
```

or

```java
SugarDecorator
```

or

```java
ChocolateDecorator
```

That's why we say:

> **Decorator transparently replaces the original object.**

---

# Key Takeaways

```text
✔ Component defines common behaviour

✔ Concrete Component is the original object

✔ Decorator wraps another Component

✔ Concrete Decorators add behaviour

✔ Delegation is the core idea

✔ Decorator IS-A Component

✔ Decorator HAS-A Component

✔ Behaviour is added dynamically

✔ Unlimited decoration is possible

✔ Composition over Inheritance
```

---

# Interview Summary

```text
Problem

↓

Inheritance causes Class Explosion

↓

Introduce Component Interface

↓

Decorator implements Component

↓

Decorator wraps Component

↓

Delegate existing behaviour

↓

Add new behaviour

↓

Return result
```
