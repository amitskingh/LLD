# Java Rules Cheat Sheet

A quick reference for **access modifiers**, **classes**, **constructors**, **methods**, and **default behavior**.

---

# Access Modifiers

| Modifier    | Same Class | Same Package | Subclass (Different Package) | Other Package |
| ----------- | :--------: | :----------: | :--------------------------: | :-----------: |
| `public`    |      ✅     |       ✅      |               ✅              |       ✅       |
| `protected` |      ✅     |       ✅      |               ✅              |       ❌       |
| *(default)* |      ✅     |       ✅      |               ❌              |       ❌       |
| `private`   |      ✅     |       ❌      |               ❌              |       ❌       |

---

# Classes

## Top-Level Class

Allowed:

```java
public class Student {

}
```

```java
class Student {

}
```

Not Allowed

```java
private class Student { }
```

```java
protected class Student { }
```

```java
static class Student { }
```

### Rule

A top-level class can only be:

* `public`
* package-private (default)

---

# Nested Class

```java
class Outer {

    private class Inner {}

    protected class Inner2 {}

    static class Inner3 {}

}
```

Nested classes can use all access modifiers.

---

# Constructors

Example

```java
public class Student {

    public Student() {}

    private Student(int id) {}

    protected Student(String name) {}

    Student(double marks) {}

}
```

### Allowed

* public
* private
* protected
* default

### Not Allowed

```java
static Student() {}
```

```java
final Student() {}
```

```java
abstract Student() {}
```

### Rules

Constructors:

* Cannot be `static`
* Cannot be `final`
* Cannot be `abstract`
* Have no return type
* Name must match the class name

---

# Methods

Allowed

```java
public void display() {}
```

```java
private void display() {}
```

```java
protected void display() {}
```

```java
void display() {}
```

```java
public static void display() {}
```

```java
public final void display() {}
```

```java
public abstract void display();
```

### Methods can be

* public
* private
* protected
* default
* static
* final
* abstract
* synchronized
* native

---

# Rules for Methods

## Static Method

Belongs to the class.

```java
Math.max(10, 20);
```

Cannot directly access instance variables.

---

## Final Method

Cannot be overridden.

```java
class Animal {

    final void eat() {}
}
```

---

## Abstract Method

Has declaration only.

```java
abstract class Animal {

    abstract void sound();

}
```

Must be implemented by subclasses.

---

## Private Method

Only accessible inside the same class.

Cannot be overridden.

---

# Variables

Allowed Modifiers

```java
private int age;
```

```java
public static int count;
```

```java
public final int MAX = 100;
```

```java
private static final int SIZE = 10;
```

---

# Abstract Class Rules

```java
abstract class Animal {

    abstract void sound();

    void eat() {}

}
```

Can have:

* Constructors
* Variables
* Concrete methods
* Abstract methods
* Static methods
* Final methods

Cannot be instantiated.

---

# Interface Rules (Java 8+)

```java
interface Vehicle {

    void start();

    default void stop() {}

    static void display() {}

}
```

Can contain:

* Abstract methods
* Default methods
* Static methods
* Private methods (Java 9+)
* Constants (`public static final`)

Cannot contain constructors.

---

# Final Class

```java
final class Utility {

}
```

Cannot be inherited.

Example:

```java
String
Math
```

---

# Static Class

Top-level class?

```java
static class A {}
```

❌ Not Allowed

Nested class?

```java
class Outer {

    static class Inner {}

}
```

✅ Allowed

---

# Default Behavior

## Class

```java
class Student {

}
```

Means:

Package-private

---

## Method

```java
void display() {

}
```

Means:

Package-private

---

## Constructor

```java
Student() {

}
```

Means:

Package-private

---

## Variable

```java
int age;
```

Means:

Package-private

---

# Access Modifier Summary

## Public

Accessible from everywhere.

---

## Protected

Accessible:

* Same package
* Subclasses in other packages

---

## Default (Package-Private)

Accessible only inside the same package.

---

## Private

Accessible only inside the same class.

---

# Quick Rules

## Can a constructor be static?

❌ No

---

## Can a constructor be final?

❌ No

---

## Can a constructor be abstract?

❌ No

---

## Can a class be abstract?

✅ Yes

---

## Can a class be final?

✅ Yes

---

## Can a class be static?

Top-level → ❌

Nested → ✅

---

## Can a method be static?

✅ Yes

---

## Can a method be final?

✅ Yes

---

## Can a method be abstract?

✅ Yes

Only inside an abstract class or interface.

---

## Can a method be private?

✅ Yes

---

## Can a variable be final?

✅ Yes

---

## Can a variable be static?

✅ Yes

---

## Can an interface have constructors?

❌ No

---

## Can an abstract class have constructors?

✅ Yes

---

# Common Interview Questions

### Why can't constructors be static?

A constructor initializes an object. Since `static` belongs to the class rather than an instance, constructors cannot be static.

---

### Why can't constructors be inherited?

Constructors are responsible for creating objects of their own class, so they are not inherited by subclasses.

---

### Can a private constructor exist?

Yes. It is commonly used in the Singleton pattern and utility classes to control object creation.

---

### Why can't a class be both `abstract` and `final`?

* `abstract` means the class **must be extended** to provide implementations.
* `final` means the class **cannot be extended**.

These requirements contradict each other.

---

| Java Element    | Purpose           | Allowed Modifiers                                                         |
| --------------- | ----------------- | ------------------------------------------------------------------------- |
| **Class**       | Defines a type    | `public`, `default`, `abstract`, `final`                                  |
| **Constructor** | Creates an object | Access modifiers only (`public`, `protected`, `default`, `private`)       |
| **Method**      | Defines behavior  | Most modifiers (`public`, `private`, `static`, `final`, `abstract`, etc.) |
| **Variable**    | Stores data       | Access modifiers + `static`, `final`, `transient`, `volatile`             |


---

# 30-Second Revision

```text
Top-Level Class
✔ public
✔ default
❌ private
❌ protected
❌ static

Constructor
✔ public
✔ private
✔ protected
✔ default
❌ static
❌ final
❌ abstract

Method
✔ public
✔ private
✔ protected
✔ default
✔ static
✔ final
✔ abstract

Interface
✔ abstract methods
✔ default methods
✔ static methods
✔ private methods (Java 9+)
❌ constructors

Abstract Class
✔ constructors
✔ variables
✔ concrete methods
✔ abstract methods

Default (No Modifier)
→ Package-Private
```
