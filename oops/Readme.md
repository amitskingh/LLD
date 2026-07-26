# Object Oriented Programming (OOP) - Complete Interview Guide (Java)

> Language: Java
>
> Purpose:
> - Learn OOP from beginner to interview level.
> - Build strong foundation for LLD and Design Patterns.
> - Understand why OOP exists instead of only memorizing definitions.

---

# Table of Contents

1. Why OOP?
2. Procedural Programming vs OOP
3. Class
4. Object
5. Four Pillars of OOP
6. Constructor
7. this Keyword
8. static Keyword
9. final Keyword
10. Access Modifiers
11. Method Overloading
12. Method Overriding
13. Polymorphism
14. Abstraction
15. Encapsulation
16. Inheritance
17. Composition vs Inheritance
18. Association
19. Aggregation
20. Composition
21. IS-A vs HAS-A
22. Abstract Class vs Interface
23. Object Class
24. equals() vs ==
25. hashCode()
26. toString()
27. super Keyword
28. Upcasting & Downcasting
29. instanceof
30. SOLID Overview
31. Common Interview Questions
32. Common Mistakes
33. Cheat Sheet

---

# 1. Why OOP?

Imagine building a hospital system.

Without OOP:

- patient_name
- patient_age
- patient_address
- patient_phone

Now imagine thousands of patients...

Everything becomes difficult to maintain.

Instead:

```java
class Patient{
    String name;
    int age;
}
```

Now every patient is simply

```java
Patient p1 = new Patient();
Patient p2 = new Patient();
```

OOP groups:

- Data
- Behaviour

into one unit.

That unit is called an Object.

---

# 2. Procedural Programming vs OOP

Procedural

```
Functions
↓

Data
```

OOP

```
Object

↓

Data
Behaviour
```

Examples

Procedural

```cpp
deposit(account,1000);
withdraw(account,500);
```

OOP

```java
account.deposit(1000);
account.withdraw(500);
```

Notice how the behaviour belongs to the object.

---

# 3. Class

A class is a blueprint.

Example

```java
class Car{

    String company;
    int speed;

}
```

Think of a class like an architectural drawing.

It is NOT a real house.

---

# 4. Object

An object is a real instance of a class.

```java
Car c = new Car();
```

Memory gets allocated only when object is created.

---

# 5. Four Pillars of OOP

```
OOP

├── Encapsulation
├── Abstraction
├── Inheritance
└── Polymorphism
```

Everything else supports these four ideas.

---

# 6. Constructor

Constructors initialize objects.

```java
class User{

    User(){

    }

}
```

Parameterized constructor

```java
User(String name){

}
```

Interview

Constructor

✔ No return type

NOT EVEN

```java
void User(){}
```

That becomes a method.

---

# 7. this Keyword

Current object reference.

```java
class User{

    String name;

    User(String name){

        this.name = name;

    }

}
```

Without this

```
name = name;
```

Both refer to local variable.

Nothing gets assigned.

---

# 8. static Keyword

Belongs to class.

Not object.

```java
class Student{

    static int count;

}
```

Shared by every object.

Interview Question

Can static methods access instance variables?

No.

Because instance variables require an object.

---

# 9. final Keyword

Variable

Cannot change.

Method

Cannot override.

Class

Cannot inherit.

---

# 10. Access Modifiers

| Modifier | Same Class | Package | Subclass | World |
|-----------|-----------|----------|-----------|--------|
| private | ✅ | ❌ | ❌ | ❌ |
| default | ✅ | ✅ | ❌ | ❌ |
| protected | ✅ | ✅ | ✅ | ❌ |
| public | ✅ | ✅ | ✅ | ✅ |

Interview favourite.

---

# 11. Method Overloading

Same method

Different parameters

Compile-time polymorphism.

```java
add(int a,int b)

add(double a,double b)
```

---

# 12. Method Overriding

Child changes parent implementation.

```java
class Animal{

    void sound(){}

}

class Dog extends Animal{

    @Override

    void sound(){

    }

}
```

Runtime polymorphism.

---

# 13. Polymorphism

One interface.

Many implementations.

```java
Animal a = new Dog();

Animal b = new Cat();
```

Same method

Different behaviour.

---

# 14. Encapsulation

Wrap

Data

+

Methods

inside one class.

Hide implementation.

Example

```java
private balance;
```

Access using

```java
deposit()
withdraw()
```

NOT directly.

---

# 15. Abstraction

Hide unnecessary details.

Show only required behaviour.

Driving

Need accelerator.

Don't need engine internals.

Achieved using

- Interface
- Abstract Class

---

# 16. Inheritance

Acquire properties.

```java
Dog

↓

Animal
```

Dog gets Animal's methods.

Java supports

Single inheritance.

Multiple inheritance via interfaces.

---

# 17. Composition vs Inheritance

Composition

```
Car

HAS-A

Engine
```

Inheritance

```
Dog

IS-A

Animal
```

Golden rule

Prefer Composition over Inheritance.

---

# 18. Association

General relationship.

Teacher

Student

They exist independently.

---

# 19. Aggregation

Weak HAS-A.

Department

Professor

Professor can exist without Department.

---

# 20. Composition

Strong HAS-A.

House

Room

Destroy House

Rooms disappear.

---

# 21. IS-A vs HAS-A

IS-A

Inheritance

```
Dog IS-A Animal
```

HAS-A

Composition

```
Car HAS-A Engine
```

Interview:

Whenever relationship changes frequently,

prefer HAS-A.

---

# 22. Abstract Class vs Interface

| Feature | Abstract Class | Interface |
|----------|---------------|-----------|
| Constructor | ✅ | ❌ |
| State | ✅ | Constants only |
| Multiple Inheritance | ❌ | ✅ |
| Methods | Abstract + Concrete | Mostly abstract |

Rule

IS-A

↓

Abstract Class

Capability

↓

Interface

---

# 23. Object Class

Every Java class extends

```
Object
```

Important methods

```
equals()

hashCode()

toString()

clone()

wait()

notify()
```

---

# 24. equals() vs ==

```
==
```

Reference comparison.

```
equals()
```

Logical comparison.

Interview favourite.

---

# 25. hashCode()

Used by

- HashMap
- HashSet

Rule

Equal objects

↓

Must have same hashCode.

---

# 26. toString()

Default

```
ClassName@6ab...
```

Override

For debugging.

---

# 27. super

Calls parent constructor.

```java
super();
```

Access parent members.

---

# 28. Upcasting

```java
Animal a = new Dog();
```

Always safe.

Downcasting

```java
Dog d = (Dog)a;
```

May throw

```
ClassCastException
```

Always check

```
instanceof
```

---

# 29. instanceof

```java
if(obj instanceof Dog)
```

Safe casting.

---

# 30. SOLID Overview

- Single Responsibility
- Open Closed
- Liskov
- Interface Segregation
- Dependency Inversion

Covered separately.

---

# Common Interview Questions

## Why does Java not support multiple inheritance?

Diamond Problem.

Interfaces solve it safely.

---

## Can constructor be overridden?

No.

Constructors aren't inherited.

---

## Can constructor be final?

No.

---

## Can static methods be overridden?

No.

They are hidden.

---

## Can abstract class have constructor?

Yes.

---

## Can interface have constructor?

No.

---

## Why use interfaces?

Loose coupling.

Dependency Injection.

Testing.

Extensibility.

---

## Difference between abstraction and encapsulation?

Encapsulation

Hide data.

Abstraction

Hide complexity.

---

## Why override hashCode() when overriding equals()?

Collections like HashMap rely on both.

Violating the contract causes lookup failures.

---

## Why is composition preferred over inheritance?

- Lower coupling
- Easier testing
- Better flexibility
- Runtime behaviour changes
- Avoids deep inheritance trees

---

# Common Mistakes

❌ Everything is inheritance

```
Car extends Engine
```

Wrong.

Should be

```
Car HAS-A Engine
```

---

❌ Public fields everywhere

Bad encapsulation.

Use private.

---

❌ Forgetting @Override

Compiler cannot catch mistakes.

---

❌ Using == for String comparison

Wrong

```java
name1 == name2
```

Correct

```java
name1.equals(name2)
```

---

❌ Deep inheritance

```
A

↓

B

↓

C

↓

D

↓

E
```

Hard to maintain.

Prefer composition.

---

❌ Breaking LSP

Child class should always behave like parent.

---

❌ Downcasting without checking

```java
Dog d = (Dog) animal;
```

May crash.

Use

```java
instanceof
```

---

# Interview Tips

Interviewers rarely ask for textbook definitions.

Instead, explain:

- What problem does this solve?
- Why is it useful?
- Where would you use it?
- Can you give a real-world example?
- Can you show a Java example?

Example:

Instead of saying:

"Encapsulation is wrapping data and methods."

Say:

"Encapsulation protects an object's state by keeping fields private and exposing controlled operations. For example, a bank account should not allow anyone to modify the balance directly; instead, it provides deposit() and withdraw() methods that enforce business rules."

---

# OOP Relationship Hierarchy

```
Object

↓

Class

↓

Object Creation

↓

Encapsulation

↓

Abstraction

↓

Inheritance

↓

Polymorphism

↓

Interfaces

↓

Composition

↓

Design Patterns

↓

SOLID

↓

Low Level Design
```

---

# Quick Cheat Sheet

| Concept | One-Line Definition |
|----------|---------------------|
| Class | Blueprint |
| Object | Instance of class |
| Encapsulation | Hide data |
| Abstraction | Hide complexity |
| Inheritance | IS-A |
| Composition | HAS-A |
| Aggregation | Weak HAS-A |
| Association | General relationship |
| Polymorphism | One interface, many implementations |
| Interface | Capability |
| Abstract Class | Partial implementation |
| Override | Runtime |
| Overload | Compile time |
| static | Belongs to class |
| final | Cannot change/extend/override |
| equals() | Logical equality |
| == | Reference equality |
| hashCode() | Hash collections |