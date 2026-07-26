# Liskov Substitution Principle (LSP)

## Definition

> **Objects of a subclass should be able to replace objects of the superclass without changing the correctness or expected behavior of the program.**

**In simple words:**

> If `B` extends `A`, then you should be able to use a `B` object anywhere an `A` object is expected **without breaking the program**.

---

# Easy One-Liner

> **A child class should behave like its parent.**

or

> **Inheritance should not change the expected behavior.**

---

# Why Do We Need LSP?

Inheritance represents an **"is-a" relationship**.

If a subclass cannot fulfill the behavior promised by its parent, then inheritance is the wrong choice.

Violating LSP leads to:

* Runtime exceptions
* Unexpected behavior
* Broken polymorphism
* Difficult-to-maintain code

---

# Bad Example

## Problem Statement

Every bird can fly... right?

```java
class Bird {
    void fly() {
        System.out.println("Flying");
    }
}

class Sparrow extends Bird {

}

class Penguin extends Bird {

    @Override
    void fly() {
        throw new UnsupportedOperationException("Penguins can't fly");
    }
}
```

Now consider this code:

```java
public class Main {

    static void makeBirdFly(Bird bird) {
        bird.fly();
    }

    public static void main(String[] args) {

        makeBirdFly(new Sparrow());   // Works

        makeBirdFly(new Penguin());   // Runtime Exception
    }
}
```

### ❌ What went wrong?

`makeBirdFly()` expects **any Bird** to fly.

But `Penguin` breaks that expectation.

Although Penguin **is-a Bird** in real life, it is **not a flying bird**.

This violates the Liskov Substitution Principle because:

* Parent promises `fly()`
* Child cannot honor that promise

---

# Good Example

Separate the common behavior from the specialized behavior.

```java
class Bird {

}
```

Create an interface for birds that can fly.

```java
interface Flyable {
    void fly();
}
```

Implement only where appropriate.

```java
class Sparrow extends Bird implements Flyable {

    @Override
    public void fly() {
        System.out.println("Flying");
    }
}

class Eagle extends Bird implements Flyable {

    @Override
    public void fly() {
        System.out.println("Flying High");
    }
}

class Penguin extends Bird {

}
```

Now:

```java
public class Main {

    static void makeBirdFly(Flyable bird) {
        bird.fly();
    }

    public static void main(String[] args) {

        makeBirdFly(new Sparrow());

        makeBirdFly(new Eagle());

        // Penguin cannot be passed here.
    }
}
```

### ✅ Why is this correct?

Only birds capable of flying implement `Flyable`.

The method accepts only objects that actually support flying.

No runtime errors.

No broken expectations.

---

# Another Example

## Bad Example

```java
class Rectangle {

    protected int width;
    protected int height;

    void setWidth(int width) {
        this.width = width;
    }

    void setHeight(int height) {
        this.height = height;
    }

    int area() {
        return width * height;
    }
}

class Square extends Rectangle {

    @Override
    void setWidth(int width) {
        this.width = width;
        this.height = width;
    }

    @Override
    void setHeight(int height) {
        this.width = height;
        this.height = height;
    }
}
```

Client code:

```java
Rectangle rectangle = new Square();

rectangle.setWidth(5);
rectangle.setHeight(10);

System.out.println(rectangle.area());
```

Expected:

```
50
```

Actual:

```
100
```

### Why?

The client assumes Rectangle's behavior.

Square changes that behavior.

Hence, LSP is violated.

---

# Good Design

Instead of forcing inheritance:

```text
Shape
 ├── Rectangle
 └── Square
```

Each class implements its own behavior independently.

---

# How to Identify an LSP Violation

Ask yourself:

### Can I replace the parent object with the child object without changing the program?

If **Yes** ✅

LSP is satisfied.

If **No** ❌

LSP is violated.

---

# Signs You're Violating LSP

* Child throws `UnsupportedOperationException`
* Child leaves methods empty
* Child changes the expected behavior
* Client code needs `instanceof`
* Client code contains special handling for subclasses

Example:

```java
if (bird instanceof Penguin) {
    // Don't call fly()
}
```

This is a strong indication that the inheritance hierarchy is incorrect.

---

# Real-World Example

### Vehicle

```text
Vehicle
```

Bad Design:

```text
Vehicle
   |
Car
   |
ElectricCar
```

If `Car` has:

```java
fillFuel();
```

Then `ElectricCar` cannot support it.

Better Design:

```text
Vehicle
      |
------------------------
|                      |
FuelVehicle      ElectricVehicle
```

Each subclass supports only the operations that make sense.

---

# Interview Questions

### What is LSP?

A subclass should be completely replaceable for its superclass without affecting the correctness or behavior of the program.

---

### Why does Penguin violate LSP?

Because `Bird` promises the ability to fly, but `Penguin` cannot fulfill that promise, leading to broken behavior.

---

### What is the main goal of LSP?

To ensure that inheritance preserves behavior and supports correct polymorphism.

---

### How do you fix an LSP violation?

* Remove incorrect inheritance.
* Extract common behavior into a parent class.
* Move specialized behavior into interfaces or more specific subclasses.
* Use composition when inheritance doesn't represent a true "is-a" relationship.

---

# Key Takeaways

* Inheritance is about **behavior**, not just shared properties.
* A subclass must honor the contract of its parent.
* Never override a method just to throw an exception or disable functionality.
* Prefer composition or interfaces when inheritance doesn't fit.
* LSP ensures safe and reliable polymorphism.

---

# 30-Second Revision

```
Definition:
A subclass should be usable wherever its superclass is expected.

Remember:
✔ Child should behave like Parent.
✔ Don't throw UnsupportedOperationException.
✔ Don't change expected behavior.
✔ If client code needs instanceof, rethink the design.
✔ Correct inheritance enables safe polymorphism.
```
