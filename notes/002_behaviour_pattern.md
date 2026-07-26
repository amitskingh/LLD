# Behavioral Design Patterns

## What are Behavioral Design Patterns?

Behavioral Design Patterns define **how objects communicate and interact with each other**.

Instead of focusing on object creation (Creational) or object structure (Structural), they focus on **behavior**, **responsibilities**, and **communication** between objects.

---

# Easy Definition

> **Behavioral patterns deal with communication and interaction between objects.**

---

# When to Use Behavioral Patterns

Use behavioral patterns when:

* Multiple objects need to communicate.
* The behavior of an object changes over time.
* Different algorithms should be interchangeable.
* You want to reduce coupling between objects.
* You need to define workflows or request processing.

---

# Behavioral Pattern List

| Pattern                 | Purpose                                                   |
| ----------------------- | --------------------------------------------------------- |
| Strategy                | Choose an algorithm at runtime                            |
| Observer                | Notify multiple objects about changes                     |
| Command                 | Encapsulate a request as an object                        |
| State                   | Change behavior based on current state                    |
| Chain of Responsibility | Pass a request through multiple handlers                  |
| Iterator                | Traverse a collection without exposing its implementation |
| Mediator                | Centralize communication between objects                  |
| Template Method         | Define algorithm skeleton; subclasses fill in steps       |
| Visitor                 | Add new operations without modifying classes              |
| Memento                 | Save and restore object state                             |
| Interpreter             | Interpret a language or expression                        |
| Null Object             | Provide a default object instead of `null`                |

---

# 1. Strategy Pattern ⭐ (Most Asked)

## Intent

Define a family of algorithms and make them interchangeable.

## Real-Life Example

Google Maps:

* Car Route
* Bike Route
* Walking Route

Different algorithms, same interface.

```text
Navigation
      |
-------------------------
|         |            |
Car     Bike      Walking
```

### Use When

* Multiple algorithms solve the same problem.
* You want to switch behavior at runtime.

---

# 2. Observer Pattern ⭐

## Intent

One object notifies many dependent objects when its state changes.

## Real-Life Example

YouTube Subscription

```
YouTube Channel
        |
-----------------------
|         |          |
User A   User B    User C
```

Upload video → Everyone gets notified.

### Use When

* Event-driven systems
* Notifications
* Stock market updates
* Chat applications

---

# 3. Command Pattern

## Intent

Encapsulate a request as an object.

Instead of calling methods directly, create a command object.

## Real-Life Example

TV Remote

```
Button
   |
Command
   |
Television
```

Each button represents a command.

### Use When

* Undo/Redo
* Task queues
* Scheduling
* Remote controls

---

# 4. State Pattern ⭐

## Intent

Allow an object to change its behavior when its internal state changes.

## Real-Life Example

Traffic Light

```
Red
 ↓
Green
 ↓
Yellow
 ↓
Red
```

Behavior depends on the current state.

### Use When

* Workflow systems
* Order processing
* ATM Machine
* Vending Machine

---

# 5. Chain of Responsibility

## Intent

Pass a request through multiple handlers until one handles it.

## Real-Life Example

Customer Support

```
Support Agent
      ↓
Team Lead
      ↓
Manager
      ↓
Director
```

If one can't handle it, pass it to the next.

### Use When

* Authentication
* Middleware
* Validation
* Approval workflows

---

# 6. Iterator Pattern

## Intent

Access elements of a collection without exposing its internal structure.

## Real-Life Example

Java Collections

```java
Iterator<String> it = list.iterator();

while(it.hasNext()){
    System.out.println(it.next());
}
```

### Use When

* Collections
* Trees
* Graph traversal

---

# 7. Mediator Pattern

## Intent

Reduce direct communication between objects by introducing a mediator.

## Real-Life Example

Air Traffic Control

Planes don't communicate with each other.

Everything goes through Air Traffic Control.

```
Plane A
    |
ATC Tower
    |
Plane B
```

### Use When

* Chat rooms
* GUI components
* Complex communication systems

---

# 8. Template Method

## Intent

Define the overall algorithm while allowing subclasses to implement specific steps.

## Real-Life Example

Making Tea vs Coffee

```
Boil Water
      ↓
Add Ingredient
      ↓
Pour
      ↓
Serve
```

Overall process is fixed.

Specific step differs.

### Use When

* Shared workflow
* Framework design
* Code reuse

---

# 9. Visitor Pattern

## Intent

Add new operations to existing classes without modifying them.

## Real-Life Example

Tax Calculation

Different visitors perform different operations:

* Tax Visitor
* Insurance Visitor
* Discount Visitor

Same object.

Different operations.

### Use When

* Compilers
* AST Processing
* Reporting

---

# 10. Memento Pattern

## Intent

Save and restore an object's previous state.

## Real-Life Example

Undo in Microsoft Word

```
Type
 ↓
Save State
 ↓
Undo
 ↓
Restore
```

### Use When

* Undo/Redo
* Game save points
* Snapshots

---

# 11. Interpreter Pattern

## Intent

Interpret a language or grammar.

## Real-Life Example

SQL Parser

Regex Engine

Expression Calculator

### Use When

* DSL (Domain Specific Language)
* Mathematical expressions
* Query parsing

---

# 12. Null Object Pattern

## Intent

Return a default object instead of returning `null`.

Instead of:

```java
if(user != null)
```

Return:

```java
GuestUser
```

### Benefits

* Avoid NullPointerException
* Cleaner code
* No repeated null checks

---

# Difference Between Behavioral Patterns

| Pattern                 | Main Purpose                            |
| ----------------------- | --------------------------------------- |
| Strategy                | Choose algorithm                        |
| Observer                | Notify subscribers                      |
| State                   | Change behavior based on state          |
| Command                 | Wrap request into an object             |
| Iterator                | Traverse collection                     |
| Mediator                | Centralize communication                |
| Chain of Responsibility | Pass request through handlers           |
| Template Method         | Fixed algorithm with customizable steps |
| Visitor                 | Add new operations                      |
| Memento                 | Save and restore state                  |
| Interpreter             | Interpret language                      |
| Null Object             | Replace null with default behavior      |

---

# Which Patterns Are Asked Most in Interviews?

⭐ Very Common

* Strategy
* Observer
* State
* Command
* Chain of Responsibility

⭐ Medium

* Iterator
* Mediator
* Template Method

⭐ Rare

* Visitor
* Interpreter
* Memento
* Null Object

---

# Easy Way to Remember

```
Strategy  → Which algorithm?

Observer  → Notify everyone.

State     → Current state decides behavior.

Command   → Wrap a request.

Chain     → Pass request along.

Iterator  → Traverse items.

Mediator  → One object manages communication.

Template  → Same workflow, different steps.

Visitor   → Add new operation.

Memento   → Undo.

Interpreter → Read expressions.

Null Object → No null checks.
```

---

# Behavioral vs Structural vs Creational

| Category   | Focus                              |
| ---------- | ---------------------------------- |
| Creational | How objects are created            |
| Structural | How objects are connected          |
| Behavioral | How objects communicate and behave |

---

# 30-Second Revision

```
Behavioral Patterns

✔ Concerned with object communication.
✔ Reduce coupling.
✔ Improve flexibility.

Most Important:

Strategy → Algorithm

Observer → Notification

State → Behavior changes

Command → Request object

Chain → Multiple handlers

Iterator → Traversal

Mediator → Central communication
```
