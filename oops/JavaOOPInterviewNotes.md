# Object-Oriented Programming in Java — Interview and Revision Notes

> Comprehensive Java OOP notes for interview preparation, concept revision, and coding practice.

---

# Table of Contents

1. Introduction to OOP
2. Class and Object
3. Object Creation and Memory
4. Constructors
5. `this` Keyword
6. `super` Keyword
7. Encapsulation
8. Inheritance
9. Types of Inheritance
10. Method Overloading
11. Method Overriding
12. Polymorphism
13. Upcasting and Downcasting
14. Abstraction
15. Abstract Classes
16. Interfaces
17. Abstract Class vs Interface
18. Access Modifiers
19. `static` Keyword
20. `final` Keyword
21. Object Class
22. `equals()` and `hashCode()`
23. Association, Aggregation, and Composition
24. Inheritance vs Composition
25. Coupling and Cohesion
26. Immutable Classes
27. Nested Classes
28. Initialisation Blocks
29. Covariant Return Types
30. SOLID Principles
31. Common Interview Questions
32. Tricky Interview Scenarios
33. Coding Examples
34. Quick Revision Cheat Sheet

---

# 1. Introduction to OOP

Object-Oriented Programming is a programming paradigm that organises software around **objects**.

An object contains:

* State
* Behaviour
* Identity

Example:

```text
Object: Bank Account

State:
- accountNumber
- balance
- accountHolder

Behaviour:
- deposit()
- withdraw()
- checkBalance()
```

Java models objects using classes.

## Four Main Pillars

```text
Encapsulation
Inheritance
Polymorphism
Abstraction
```

A useful mnemonic is:

```text
EIPA
```

---

# 2. Class and Object

## Class

A class is a blueprint or template used to create objects.

```java
class Student {
    int id;
    String name;

    void study() {
        System.out.println(name + " is studying");
    }
}
```

The class defines:

* Fields
* Methods
* Constructors
* Initialisation blocks
* Nested types

## Object

An object is an instance of a class.

```java
Student student = new Student();

student.id = 101;
student.name = "Alice";

student.study();
```

Output:

```text
Alice is studying
```

## Class vs Object

| Class                                    | Object               |
| ---------------------------------------- | -------------------- |
| Blueprint                                | Instance of a class  |
| Logical definition                       | Runtime entity       |
| Does not represent actual data by itself | Stores actual values |
| Created using `class`                    | Created using `new`  |

---

# 3. Object Creation and Memory

Example:

```java
Student student = new Student();
```

Conceptually:

```text
Student student
```

creates a reference variable.

```text
new Student()
```

creates an object in heap memory.

The reference variable points to the object.

```text
Stack                         Heap

student  ------------------>  Student object
                              id = 0
                              name = null
```

## Multiple References to the Same Object

```java
Student first = new Student();
Student second = first;

second.name = "Bob";

System.out.println(first.name);
```

Output:

```text
Bob
```

Both references point to the same object.

## Anonymous Object

```java
new Student().study();
```

An anonymous object is created without storing its reference.

It is useful when the object is needed only once.

---

# 4. Constructors

A constructor initialises a newly created object.

## Constructor Rules

* Constructor name must match the class name.
* It has no return type.
* It is called automatically when an object is created.
* It can be overloaded.
* It cannot be inherited.
* It cannot be overridden.

Example:

```java
class Employee {
    int id;
    String name;

    Employee() {
        id = 0;
        name = "Unknown";
    }
}
```

## Default Constructor

If no constructor is declared, the compiler provides a default no-argument constructor.

```java
class Employee {
}
```

Conceptually, the compiler provides:

```java
Employee() {
    super();
}
```

Important:

> The compiler provides a default constructor only when no constructor is explicitly declared.

## No-Argument Constructor

```java
class Employee {
    Employee() {
        System.out.println("Employee created");
    }
}
```

## Parameterised Constructor

```java
class Employee {
    int id;
    String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
```

Usage:

```java
Employee employee = new Employee(101, "Alice");
```

## Constructor Overloading

```java
class Employee {
    int id;
    String name;
    double salary;

    Employee() {
        this(0, "Unknown", 0.0);
    }

    Employee(int id, String name) {
        this(id, name, 0.0);
    }

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
}
```

## Constructor vs Method

| Constructor           | Method                  |
| --------------------- | ----------------------- |
| Initialises an object | Performs an operation   |
| Same name as class    | Can have any valid name |
| No return type        | May have a return type  |
| Called automatically  | Called explicitly       |
| Cannot be overridden  | Can be overridden       |

---

# 5. `this` Keyword

`this` refers to the current object.

## Access Current Object Fields

```java
class Student {
    private int id;
    private String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
```

Here:

```java
this.id
```

refers to the instance field.

```java
id
```

refers to the constructor parameter.

## Call Another Constructor

```java
class Student {
    Student() {
        this(101);
        System.out.println("No-argument constructor");
    }

    Student(int id) {
        System.out.println("ID: " + id);
    }
}
```

`this()` must be the first statement in a constructor.

## Pass Current Object as an Argument

```java
class Printer {
    void print(Student student) {
        System.out.println(student);
    }
}

class Student {
    void display() {
        Printer printer = new Printer();
        printer.print(this);
    }
}
```

## Return Current Object

```java
class Builder {
    Builder configure() {
        return this;
    }
}
```

This pattern is commonly used in method chaining.

```java
builder.configure().configure();
```

---

# 6. `super` Keyword

`super` refers to the immediate parent class.

## Access Parent Field

```java
class Parent {
    int value = 10;
}

class Child extends Parent {
    int value = 20;

    void display() {
        System.out.println(value);
        System.out.println(super.value);
    }
}
```

Output:

```text
20
10
```

## Call Parent Method

```java
class Parent {
    void show() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    @Override
    void show() {
        super.show();
        System.out.println("Child");
    }
}
```

## Call Parent Constructor

```java
class Parent {
    Parent(int value) {
        System.out.println("Parent: " + value);
    }
}

class Child extends Parent {
    Child() {
        super(100);
        System.out.println("Child");
    }
}
```

`super()` must be the first statement in a constructor.

## Important Rule

A constructor can call either:

```java
this()
```

or:

```java
super()
```

as its first statement, but not both directly.

---

# 7. Encapsulation

Encapsulation means wrapping data and related behaviour inside a class while controlling access to the data.

It is commonly achieved using:

* Private fields
* Public methods
* Validation logic

Example:

```java
class BankAccount {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Deposit amount must be positive"
            );
        }

        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            throw new IllegalArgumentException(
                    "Invalid withdrawal amount"
            );
        }

        balance -= amount;
    }
}
```

## Benefits

* Data hiding
* Validation
* Security
* Maintainability
* Reduced coupling
* Controlled state changes

## Encapsulation Is More Than Getters and Setters

Poor encapsulation:

```java
class User {
    private int age;

    public void setAge(int age) {
        this.age = age;
    }
}
```

Better:

```java
public void setAge(int age) {
    if (age < 0) {
        throw new IllegalArgumentException(
                "Age cannot be negative"
        );
    }

    this.age = age;
}
```

---

# 8. Inheritance

Inheritance allows one class to acquire the accessible fields and methods of another class.

```java
class Animal {
    void eat() {
        System.out.println("Animal is eating");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog is barking");
    }
}
```

Usage:

```java
Dog dog = new Dog();

dog.eat();
dog.bark();
```

## Terminology

```text
Animal → Parent class / Superclass / Base class
Dog    → Child class / Subclass / Derived class
```

## IS-A Relationship

Inheritance represents an IS-A relationship.

```text
Dog IS-A Animal
Car IS-A Vehicle
Manager IS-A Employee
```

## Members Not Inherited Directly

Constructors are not inherited.

Private members are part of the parent object state, but they are not directly accessible in the child class.

---

# 9. Types of Inheritance

## Single Inheritance

```text
A
|
B
```

```java
class Animal {
}

class Dog extends Animal {
}
```

## Multilevel Inheritance

```text
A
|
B
|
C
```

```java
class Animal {
}

class Mammal extends Animal {
}

class Dog extends Mammal {
}
```

## Hierarchical Inheritance

```text
      A
     / \
    B   C
```

```java
class Animal {
}

class Dog extends Animal {
}

class Cat extends Animal {
}
```

## Multiple Inheritance

```text
A   B
 \ /
  C
```

Java does not support multiple inheritance through classes.

Invalid:

```java
class C extends A, B {
}
```

Java supports multiple inheritance of type through interfaces.

```java
interface Flyable {
}

interface Swimmable {
}

class Duck implements Flyable, Swimmable {
}
```

## Why Java Avoids Multiple Class Inheritance

It prevents ambiguity such as the diamond problem.

```text
    A
   / \
  B   C
   \ /
    D
```

If both `B` and `C` define the same inherited method, Java would need to decide which implementation `D` should use.

---

# 10. Method Overloading

Method overloading means defining multiple methods with the same name but different parameter lists.

It is compile-time polymorphism.

```java
class Calculator {
    int add(int first, int second) {
        return first + second;
    }

    double add(double first, double second) {
        return first + second;
    }

    int add(int first, int second, int third) {
        return first + second + third;
    }
}
```

## Valid Ways to Overload

Change:

* Number of parameters
* Parameter types
* Parameter order

```java
void display(int value) {
}

void display(String value) {
}

void display(int value, String text) {
}

void display(String text, int value) {
}
```

## Return Type Alone Is Not Enough

Invalid:

```java
int calculate() {
    return 1;
}

double calculate() {
    return 1.0;
}
```

The compiler cannot distinguish methods based only on return type.

## Static Methods Can Be Overloaded

```java
static void print(int value) {
}

static void print(String value) {
}
```

## Constructors Can Be Overloaded

```java
Employee() {
}

Employee(int id) {
}

Employee(int id, String name) {
}
```

---

# 11. Method Overriding

Method overriding occurs when a child class provides a new implementation of an inherited method.

It is runtime polymorphism.

```java
class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Bark");
    }
}
```

## Overriding Rules

The overriding method must have:

* Same method name
* Same parameter list
* Same or covariant return type
* Same or wider access level
* Compatible exception declaration

## Visibility Cannot Be Reduced

Valid:

```java
class Parent {
    protected void show() {
    }
}

class Child extends Parent {
    @Override
    public void show() {
    }
}
```

Invalid:

```java
class Child extends Parent {
    @Override
    private void show() {
    }
}
```

## Methods That Cannot Be Overridden

* `final` methods
* `private` methods
* Constructors

Static methods are hidden, not overridden.

## `@Override` Annotation

```java
@Override
void sound() {
}
```

Benefits:

* Compiler checks the method signature.
* Prevents accidental overloading.
* Improves readability.

---

# 12. Polymorphism

Polymorphism means one interface or reference can represent multiple forms.

## Compile-Time Polymorphism

Achieved through method overloading.

```java
calculator.add(10, 20);
calculator.add(10.5, 20.5);
```

## Runtime Polymorphism

Achieved through method overriding.

```java
Animal animal = new Dog();

animal.sound();
```

Output:

```text
Bark
```

The reference type is `Animal`, but the runtime object is `Dog`.

## Dynamic Method Dispatch

Java determines the overridden instance method at runtime based on the actual object.

```java
class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Bark");
    }
}

class Cat extends Animal {
    @Override
    void sound() {
        System.out.println("Meow");
    }
}
```

Usage:

```java
Animal first = new Dog();
Animal second = new Cat();

first.sound();
second.sound();
```

Output:

```text
Bark
Meow
```

## Fields Are Not Polymorphic

```java
class Parent {
    String name = "Parent";
}

class Child extends Parent {
    String name = "Child";
}

Parent object = new Child();

System.out.println(object.name);
```

Output:

```text
Parent
```

Field access is based on the reference type.

Method calls are based on the runtime object when overridden.

---

# 13. Upcasting and Downcasting

## Upcasting

Converting a child object reference to a parent reference.

```java
Dog dog = new Dog();
Animal animal = dog;
```

or:

```java
Animal animal = new Dog();
```

Upcasting is implicit and safe.

Through the parent reference, only members available in the parent type are directly accessible.

```java
animal.sound();
```

If `sound()` is overridden, the child implementation is called.

## Downcasting

Converting a parent reference back to a child reference.

```java
Animal animal = new Dog();
Dog dog = (Dog) animal;
```

Downcasting is explicit.

## Invalid Downcasting

```java
Animal animal = new Animal();
Dog dog = (Dog) animal;
```

This throws:

```text
ClassCastException
```

## Safe Downcasting with `instanceof`

```java
if (animal instanceof Dog) {
    Dog dog = (Dog) animal;
    dog.bark();
}
```

Modern pattern matching syntax:

```java
if (animal instanceof Dog dog) {
    dog.bark();
}
```

---

# 14. Abstraction

Abstraction means exposing essential behaviour while hiding implementation details.

Example:

```text
User presses a car's start button.

Visible:
- Start operation

Hidden:
- Fuel injection
- Electrical ignition
- Engine control
```

In Java, abstraction is achieved mainly through:

* Abstract classes
* Interfaces

Benefits:

* Reduced complexity
* Loose coupling
* Flexible implementations
* Clear contracts
* Easier testing

---

# 15. Abstract Classes

An abstract class is declared using the `abstract` keyword.

```java
abstract class Shape {
    abstract double area();

    void display() {
        System.out.println("This is a shape");
    }
}
```

A child class must implement abstract methods unless the child is also abstract.

```java
class Circle extends Shape {
    private final double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }
}
```

## Abstract Class Characteristics

An abstract class can contain:

* Abstract methods
* Concrete methods
* Constructors
* Instance variables
* Static variables
* Static methods
* Final methods
* Access modifiers

## Cannot Instantiate Directly

Invalid:

```java
Shape shape = new Shape();
```

Valid:

```java
Shape shape = new Circle(5);
```

## Abstract Class Constructor

```java
abstract class Vehicle {
    Vehicle() {
        System.out.println("Vehicle constructor");
    }
}
```

Abstract classes can have constructors because their constructor runs during child object creation.

## Abstract Method

```java
abstract void start();
```

An abstract method:

* Has no body
* Must be implemented by a concrete subclass
* Cannot be `private`
* Cannot be `final`
* Cannot be `static`

---

# 16. Interfaces

An interface defines a contract that implementing classes must follow.

```java
interface Payment {
    void pay(double amount);
}
```

Implementation:

```java
class CardPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println(
                "Paid by card: " + amount
        );
    }
}
```

Usage:

```java
Payment payment = new CardPayment();
payment.pay(100.0);
```

## Interface Fields

Fields declared in an interface are implicitly:

```text
public static final
```

Example:

```java
interface Configuration {
    int MAX_RETRIES = 3;
}
```

Equivalent to:

```java
public static final int MAX_RETRIES = 3;
```

## Interface Methods

Traditional abstract interface methods are implicitly:

```text
public abstract
```

```java
interface Vehicle {
    void start();
}
```

Equivalent to:

```java
public abstract void start();
```

## Default Methods

Interfaces can contain default methods.

```java
interface Vehicle {
    void start();

    default void stop() {
        System.out.println("Vehicle stopped");
    }
}
```

Default methods allow an interface to evolve without forcing every implementation to immediately implement the new method.

## Static Interface Methods

```java
interface Validator {
    static boolean isValid(String value) {
        return value != null && !value.isBlank();
    }
}
```

Usage:

```java
boolean valid = Validator.isValid("Java");
```

Static interface methods are called using the interface name.

## Private Interface Methods

Private interface methods can support code reuse between default or static methods.

```java
interface Logger {
    default void info(String message) {
        log("INFO", message);
    }

    default void error(String message) {
        log("ERROR", message);
    }

    private void log(String level, String message) {
        System.out.println(level + ": " + message);
    }
}
```

## Multiple Interfaces

```java
interface Printable {
    void print();
}

interface Scannable {
    void scan();
}

class Machine implements Printable, Scannable {
    @Override
    public void print() {
        System.out.println("Printing");
    }

    @Override
    public void scan() {
        System.out.println("Scanning");
    }
}
```

## Default Method Conflict

```java
interface First {
    default void show() {
        System.out.println("First");
    }
}

interface Second {
    default void show() {
        System.out.println("Second");
    }
}
```

A class implementing both must resolve the conflict.

```java
class Result implements First, Second {
    @Override
    public void show() {
        First.super.show();
    }
}
```

---

# 17. Abstract Class vs Interface

| Abstract Class                         | Interface                                               |
| -------------------------------------- | ------------------------------------------------------- |
| Declared with `abstract class`         | Declared with `interface`                               |
| Extended using `extends`               | Implemented using `implements`                          |
| Supports instance fields               | Fields are constants                                    |
| Can have constructors                  | Cannot have constructors                                |
| Can have any access modifier           | Abstract methods are public                             |
| Supports single class inheritance      | Supports multiple interface implementation              |
| Can store object state                 | Mainly defines behaviour contracts                      |
| Can have abstract and concrete methods | Can have abstract, default, static, and private methods |

## When to Use an Abstract Class

Use an abstract class when:

* Classes share common state.
* Classes share common implementation.
* Protected members are required.
* Constructors are needed.
* Subclasses are closely related.

Example:

```text
Employee
├── Developer
├── Tester
└── Manager
```

## When to Use an Interface

Use an interface when:

* A behaviour contract is needed.
* Unrelated classes can share the same capability.
* Multiple behaviours must be combined.
* Loose coupling is important.

Example:

```text
Payable
├── Employee
├── Invoice
└── Subscription
```

---

# 18. Access Modifiers

Java provides four access levels.

| Modifier    | Same Class | Same Package | Subclass Outside Package | Everywhere |
| ----------- | ---------: | -----------: | -----------------------: | ---------: |
| `private`   |        Yes |           No |                       No |         No |
| Default     |        Yes |          Yes |                       No |         No |
| `protected` |        Yes |          Yes |                      Yes |         No |
| `public`    |        Yes |          Yes |                      Yes |        Yes |

## Private

```java
private int balance;
```

Accessible only within the declaring class.

## Default or Package-Private

```java
int count;
```

No modifier is written.

Accessible within the same package.

## Protected

```java
protected void calculate() {
}
```

Accessible:

* In the same package
* In subclasses outside the package under inheritance access rules

## Public

```java
public void display() {
}
```

Accessible from everywhere, subject to class visibility.

## Top-Level Class Access

A top-level class can only be:

* `public`
* Package-private

Invalid:

```java
private class Example {
}
```

---

# 19. `static` Keyword

A static member belongs to the class rather than an individual object.

## Static Variable

```java
class Employee {
    static int employeeCount;
}
```

Only one shared copy exists for the class.

```java
class Employee {
    static int employeeCount;

    Employee() {
        employeeCount++;
    }
}
```

Usage:

```java
new Employee();
new Employee();

System.out.println(Employee.employeeCount);
```

Output:

```text
2
```

## Static Method

```java
class Mathematics {
    static int square(int value) {
        return value * value;
    }
}
```

Usage:

```java
int result = Mathematics.square(5);
```

A static method:

* Can directly access static members.
* Cannot directly access instance members.
* Cannot use `this`.
* Cannot use `super`.

## Static Initialisation Block

```java
class Configuration {
    static String environment;

    static {
        environment = "production";
        System.out.println("Static block executed");
    }
}
```

A static block executes when the class is initialised.

## Static Nested Class

```java
class Outer {
    static class Nested {
        void display() {
            System.out.println("Static nested class");
        }
    }
}
```

Usage:

```java
Outer.Nested nested = new Outer.Nested();
```

---

# 20. `final` Keyword

The meaning of `final` depends on where it is used.

## Final Variable

A final variable can be assigned only once.

```java
final int maximum = 100;
```

Invalid:

```java
maximum = 200;
```

## Blank Final Variable

```java
class User {
    private final int id;

    User(int id) {
        this.id = id;
    }
}
```

A blank final instance variable must be assigned in every constructor.

## Final Reference

```java
final List<String> names = new ArrayList<>();
```

The reference cannot point to another list.

Invalid:

```java
names = new ArrayList<>();
```

The object itself may still be modified.

Valid:

```java
names.add("Alice");
```

## Final Method

```java
class Parent {
    final void display() {
    }
}
```

A final method cannot be overridden.

## Final Class

```java
final class Utility {
}
```

A final class cannot be extended.

Example:

```text
String is final.
```

## `final`, `finally`, and `finalize()`

| Term         | Meaning                                                      |
| ------------ | ------------------------------------------------------------ |
| `final`      | Restricts variables, methods, or classes                     |
| `finally`    | Exception-handling block                                     |
| `finalize()` | Deprecated cleanup method associated with garbage collection |

---

# 21. Object Class

`java.lang.Object` is the root class of the Java class hierarchy.

Every class directly or indirectly extends `Object`.

```java
class Employee {
}
```

Conceptually:

```java
class Employee extends Object {
}
```

## Important Object Methods

```java
toString()
equals()
hashCode()
getClass()
clone()
wait()
notify()
notifyAll()
```

## `toString()`

Returns a string representation of the object.

Default output resembles:

```text
Employee@5acf9800
```

Override it for meaningful output.

```java
@Override
public String toString() {
    return "Employee{id=" + id + ", name='" + name + "'}";
}
```

## `getClass()`

Returns the runtime class.

```java
Employee employee = new Employee();

System.out.println(employee.getClass().getName());
```

## `clone()`

Creates a field-by-field copy when properly supported.

Using `clone()` is often avoided in modern design because constructors or factory methods can provide clearer copying behaviour.

## `wait()`, `notify()`, and `notifyAll()`

These methods support thread communication and must be used while owning the object's monitor.

---

# 22. `equals()` and `hashCode()`

## Reference Comparison with `==`

For objects, `==` checks whether two references point to the same object.

```java
String first = new String("Java");
String second = new String("Java");

System.out.println(first == second);
```

Output:

```text
false
```

## Logical Comparison with `equals()`

```java
System.out.println(first.equals(second));
```

Output:

```text
true
```

## Default `equals()`

The default implementation inherited from `Object` behaves like reference comparison.

Custom classes should override `equals()` when logical equality is required.

## Example

```java
import java.util.Objects;

class Employee {
    private final int id;
    private final String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Employee employee)) {
            return false;
        }

        return id == employee.id
                && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
```

## Equality Contract

A correct `equals()` implementation should be:

* Reflexive
* Symmetric
* Transitive
* Consistent
* False for `null`

## `hashCode()` Contract

If:

```java
first.equals(second)
```

is true, then:

```java
first.hashCode() == second.hashCode()
```

must also be true.

The reverse is not required.

Two unequal objects may have the same hash code.

## Why Override Both?

Hash-based collections such as:

* `HashMap`
* `HashSet`
* `Hashtable`

use both `hashCode()` and `equals()`.

Overriding only one can cause incorrect lookup or duplicate behaviour.

---

# 23. Association, Aggregation, and Composition

## Association

Association is a general relationship between two independent objects.

Example:

```text
Teacher teaches Student
Customer uses Bank
Doctor treats Patient
```

```java
class Teacher {
    void teach(Student student) {
        System.out.println("Teaching " + student.getName());
    }
}
```

## Aggregation

Aggregation is a weak HAS-A relationship.

The contained object can exist independently.

Example:

```text
Department HAS-A Teacher
```

```java
class Teacher {
    private final String name;

    Teacher(String name) {
        this.name = name;
    }
}

class Department {
    private final List<Teacher> teachers;

    Department(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
```

Teachers can exist even if the department is removed.

## Composition

Composition is a strong HAS-A relationship.

The contained object's lifecycle is strongly controlled by the owner.

Example:

```text
House HAS-A Room
Car HAS-A Engine
Order HAS-A OrderLine
```

```java
class Engine {
    void start() {
        System.out.println("Engine started");
    }
}

class Car {
    private final Engine engine;

    Car() {
        engine = new Engine();
    }

    void start() {
        engine.start();
    }
}
```

## Comparison

| Association             | Aggregation                | Composition                      |
| ----------------------- | -------------------------- | -------------------------------- |
| General relationship    | Weak ownership             | Strong ownership                 |
| Objects are independent | Child can exist separately | Child lifecycle depends on owner |
| Uses or interacts with  | HAS-A                      | Strong HAS-A                     |

---

# 24. Inheritance vs Composition

## Inheritance

Represents an IS-A relationship.

```java
class Dog extends Animal {
}
```

Advantages:

* Code reuse
* Runtime polymorphism
* Shared contracts

Disadvantages:

* Tight coupling
* Fragile hierarchies
* Parent changes may affect children

## Composition

Represents a HAS-A relationship.

```java
class Car {
    private final Engine engine;
}
```

Advantages:

* Flexible design
* Loose coupling
* Behaviour can be replaced
* Easier testing

## Prefer Composition Over Inheritance

Use inheritance only when the child is truly a specialised form of the parent.

Good:

```text
Dog IS-A Animal
SavingsAccount IS-A BankAccount
```

Questionable:

```text
Car IS-A Engine
```

Correct:

```text
Car HAS-A Engine
```

## Strategy Through Composition

```java
interface PaymentMethod {
    void pay(double amount);
}

class CardPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Card payment");
    }
}

class CheckoutService {
    private final PaymentMethod paymentMethod;

    CheckoutService(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    void checkout(double amount) {
        paymentMethod.pay(amount);
    }
}
```

This design allows payment behaviour to be replaced without modifying `CheckoutService`.

---

# 25. Coupling and Cohesion

## Coupling

Coupling describes how strongly classes depend on one another.

### Tight Coupling

```java
class OrderService {
    private final EmailService emailService =
            new EmailService();
}
```

`OrderService` directly creates its dependency.

### Loose Coupling

```java
interface NotificationService {
    void send(String message);
}

class OrderService {
    private final NotificationService notificationService;

    OrderService(
            NotificationService notificationService
    ) {
        this.notificationService = notificationService;
    }
}
```

Loose coupling improves:

* Testability
* Flexibility
* Maintainability

## Cohesion

Cohesion describes how closely related the responsibilities inside a class are.

High cohesion:

```java
class InvoiceCalculator {
    double calculateTotal(Invoice invoice) {
        return 0.0;
    }
}
```

Low cohesion:

```java
class UtilityManager {
    void calculateInvoice() {
    }

    void sendEmail() {
    }

    void saveEmployee() {
    }

    void resizeImage() {
    }
}
```

Preferred design:

```text
Low coupling
High cohesion
```

---

# 26. Immutable Classes

An immutable object cannot be changed after creation.

Example:

```java
final class User {
    private final int id;
    private final String name;

    User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
```

## Rules for Immutability

* Make the class final.
* Make fields private and final.
* Initialise all fields through constructors.
* Do not provide setters.
* Do not expose mutable internal objects directly.
* Make defensive copies where needed.

## Defensive Copy Example

```java
import java.util.ArrayList;
import java.util.List;

final class Team {
    private final List<String> members;

    Team(List<String> members) {
        this.members = new ArrayList<>(members);
    }

    public List<String> getMembers() {
        return List.copyOf(members);
    }
}
```

Without defensive copying, callers could change the internal state.

## Benefits

* Thread safety
* Easier reasoning
* Safe map keys
* Predictable behaviour
* Simple caching

Examples of immutable Java types include:

```text
String
Integer
Long
BigInteger
LocalDate
```

---

# 27. Nested Classes

Java supports several nested class types.

## Static Nested Class

```java
class Outer {
    static class Nested {
        void display() {
            System.out.println("Nested");
        }
    }
}
```

Usage:

```java
Outer.Nested nested = new Outer.Nested();
```

A static nested class does not require an outer object.

## Inner Class

```java
class Outer {
    private int value = 10;

    class Inner {
        void display() {
            System.out.println(value);
        }
    }
}
```

Usage:

```java
Outer outer = new Outer();
Outer.Inner inner = outer.new Inner();

inner.display();
```

An inner class is associated with an outer object.

## Local Class

Declared inside a method.

```java
class Example {
    void execute() {
        class Local {
            void show() {
                System.out.println("Local class");
            }
        }

        new Local().show();
    }
}
```

## Anonymous Class

An unnamed class created for one-time use.

```java
Runnable task = new Runnable() {
    @Override
    public void run() {
        System.out.println("Running");
    }
};
```

When the target type is a functional interface, a lambda is often preferred.

```java
Runnable task = () -> System.out.println("Running");
```

---

# 28. Initialisation Blocks

## Instance Initialisation Block

Runs whenever an object is created, before the constructor body.

```java
class Example {
    {
        System.out.println("Instance block");
    }

    Example() {
        System.out.println("Constructor");
    }
}
```

Output:

```text
Instance block
Constructor
```

## Static Initialisation Block

Runs when the class is initialised.

```java
class Example {
    static {
        System.out.println("Static block");
    }
}
```

## Initialisation Order

For child object creation, the broad order is:

```text
1. Parent static members and blocks
2. Child static members and blocks
3. Parent instance fields and blocks
4. Parent constructor
5. Child instance fields and blocks
6. Child constructor
```

Static initialisation occurs once per class initialisation.

Instance initialisation occurs for every object.

---

# 29. Covariant Return Types

An overriding method may return a more specific type than the parent method.

```java
class Animal {
}

class Dog extends Animal {
}

class Parent {
    Animal create() {
        return new Animal();
    }
}

class Child extends Parent {
    @Override
    Dog create() {
        return new Dog();
    }
}
```

`Dog` is a subtype of `Animal`, so this is valid.

Covariant returns apply to reference types.

---

# 30. SOLID Principles

SOLID is a set of object-oriented design principles.

## S — Single Responsibility Principle

A class should have one reason to change.

Poor design:

```java
class EmployeeService {
    void calculateSalary() {
    }

    void saveToDatabase() {
    }

    void sendEmail() {
    }
}
```

Better:

```text
SalaryCalculator
EmployeeRepository
EmailService
```

## O — Open/Closed Principle

Software entities should be:

```text
Open for extension
Closed for modification
```

Poor design:

```java
class DiscountCalculator {
    double calculate(String customerType) {
        if (customerType.equals("REGULAR")) {
            return 0.05;
        }

        if (customerType.equals("PREMIUM")) {
            return 0.10;
        }

        return 0;
    }
}
```

Better:

```java
interface DiscountStrategy {
    double calculate();
}

class RegularDiscount implements DiscountStrategy {
    @Override
    public double calculate() {
        return 0.05;
    }
}

class PremiumDiscount implements DiscountStrategy {
    @Override
    public double calculate() {
        return 0.10;
    }
}
```

New discount types can be added without changing existing implementations.

## L — Liskov Substitution Principle

A child object should be usable wherever a parent object is expected without breaking expected behaviour.

Poor hierarchy:

```text
Bird
└── Penguin
```

If `Bird` requires `fly()`, a penguin violates the expected behaviour.

Better:

```text
Bird
├── FlyingBird
└── NonFlyingBird
```

## I — Interface Segregation Principle

Clients should not depend on methods they do not use.

Poor interface:

```java
interface Worker {
    void work();
    void eat();
}
```

A robot may work but not eat.

Better:

```java
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}
```

## D — Dependency Inversion Principle

High-level modules should depend on abstractions rather than concrete implementations.

Poor:

```java
class NotificationManager {
    private final EmailService emailService =
            new EmailService();
}
```

Better:

```java
interface MessageService {
    void send(String message);
}

class NotificationManager {
    private final MessageService messageService;

    NotificationManager(MessageService messageService) {
        this.messageService = messageService;
    }
}
```

---

# 31. Common Interview Questions

## Why is Java not considered purely object-oriented?

Java supports primitive data types such as:

```text
int
char
boolean
double
```

These are not objects.

---

## Can a class be both abstract and final?

No.

`abstract` means the class must be extended.

`final` means the class cannot be extended.

The two requirements conflict.

---

## Can a constructor be final?

No.

Constructors are not inherited or overridden, so `final` is unnecessary.

---

## Can a constructor be static?

No.

A constructor initialises an object, while `static` belongs to the class.

---

## Can a constructor be private?

Yes.

```java
class Singleton {
    private Singleton() {
    }
}
```

Private constructors are used in:

* Singleton patterns
* Utility classes
* Factory methods
* Controlled object creation

---

## Can an abstract class have no abstract methods?

Yes.

```java
abstract class Base {
    void show() {
        System.out.println("Concrete method");
    }
}
```

Such a class cannot be instantiated but may provide shared behaviour.

---

## Can an abstract class have static methods?

Yes.

```java
abstract class Utility {
    static void display() {
    }
}
```

---

## Can an abstract method be private?

No.

A private method cannot be accessed or overridden by subclasses.

---

## Can an abstract method be final?

No.

An abstract method must be overridden.

A final method cannot be overridden.

---

## Can an interface extend another interface?

Yes.

```java
interface A {
}

interface B extends A {
}
```

An interface can extend multiple interfaces.

```java
interface C extends A, B {
}
```

---

## Can a class extend an interface?

No.

A class implements an interface.

```java
class Example implements Runnable {
}
```

---

## Can an interface implement another interface?

No.

An interface extends another interface.

---

## Can an interface extend a class?

No.

---

## Can static methods be overridden?

No.

Static methods are hidden.

```java
class Parent {
    static void show() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    static void show() {
        System.out.println("Child");
    }
}
```

```java
Parent reference = new Child();
reference.show();
```

Output:

```text
Parent
```

The call is resolved using the reference type.

---

## Can private methods be overridden?

No.

Private methods are not visible to child classes.

A child can declare a method with the same name, but it is a separate method.

---

## Can final methods be overloaded?

Yes.

`final` prevents overriding, not overloading.

```java
final void print(int value) {
}

final void print(String value) {
}
```

---

## Can the main method be overloaded?

Yes.

```java
public static void main(String[] args) {
}

public static void main(int value) {
}
```

The JVM starts execution only from:

```java
public static void main(String[] args)
```

---

## Can the main method be overridden?

No in the polymorphic sense, because it is static.

It can be hidden in a child class.

---

## Is Java pass-by-reference?

No.

Java is always pass-by-value.

For objects, the copied value is the object reference.

```java
class Box {
    int value;
}

static void update(Box box) {
    box.value = 100;
}
```

The copied reference still points to the same object, so object state can be modified.

However:

```java
static void replace(Box box) {
    box = new Box();
}
```

Reassigning the local parameter does not change the caller's reference.

---

## What is dynamic binding?

Dynamic binding means an overridden instance method is selected at runtime based on the actual object.

```java
Animal animal = new Dog();
animal.sound();
```

## What is static binding?

Static binding occurs at compile time.

Examples include:

* Method overloading
* Static methods
* Private methods
* Final methods

---

## What is method hiding?

When a child defines a static method with the same signature as a parent static method.

The selected method depends on the reference type, not the runtime object.

---

## What is object slicing in Java?

Traditional object slicing, as seen in C++, does not occur in Java because objects are accessed through references.

A parent reference may provide a restricted view of a child object, but the child object itself remains complete.

---

## Why prefer composition over inheritance?

Composition:

* Reduces coupling
* Improves flexibility
* Supports runtime replacement
* Avoids fragile inheritance hierarchies
* Makes testing easier

---

# 32. Tricky Interview Scenarios

## Scenario 1: Overridden Method Call

```java
class Parent {
    void show() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    @Override
    void show() {
        System.out.println("Child");
    }
}

Parent reference = new Child();
reference.show();
```

Output:

```text
Child
```

Reason:

Runtime polymorphism applies to overridden instance methods.

---

## Scenario 2: Hidden Static Method

```java
class Parent {
    static void show() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    static void show() {
        System.out.println("Child");
    }
}

Parent reference = new Child();
reference.show();
```

Output:

```text
Parent
```

Reason:

Static method calls are resolved from the reference type.

---

## Scenario 3: Hidden Field

```java
class Parent {
    int value = 10;
}

class Child extends Parent {
    int value = 20;
}

Parent reference = new Child();

System.out.println(reference.value);
```

Output:

```text
10
```

Fields are resolved using the reference type.

---

## Scenario 4: Constructor Execution Order

```java
class Parent {
    Parent() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    Child() {
        System.out.println("Child");
    }
}

new Child();
```

Output:

```text
Parent
Child
```

The parent constructor runs before the child constructor body.

---

## Scenario 5: Overridable Method Called from Constructor

```java
class Parent {
    Parent() {
        display();
    }

    void display() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    private int value = 100;

    @Override
    void display() {
        System.out.println(value);
    }
}
```

```java
new Child();
```

Possible output:

```text
0
```

Reason:

The overridden child method runs before the child fields are fully initialised.

Interview lesson:

> Avoid calling overridable methods from constructors.

---

## Scenario 6: Final Reference

```java
final StringBuilder builder =
        new StringBuilder("Java");

builder.append(" OOP");
```

Valid.

```java
builder = new StringBuilder("Python");
```

Invalid.

The reference is final, not necessarily the referenced object.

---

## Scenario 7: Overloading with `null`

```java
void print(String value) {
}

void print(Integer value) {
}
```

Call:

```java
print(null);
```

Compilation error:

```text
Reference to print is ambiguous
```

Neither `String` nor `Integer` is more specific than the other.

---

## Scenario 8: Parent Constructor Requirement

```java
class Parent {
    Parent(int value) {
    }
}

class Child extends Parent {
    Child() {
    }
}
```

Compilation error.

The compiler tries to insert:

```java
super();
```

but the parent has no no-argument constructor.

Correct:

```java
class Child extends Parent {
    Child() {
        super(10);
    }
}
```

---

## Scenario 9: Interface Default Method Conflict

```java
interface A {
    default void show() {
        System.out.println("A");
    }
}

interface B {
    default void show() {
        System.out.println("B");
    }
}

class C implements A, B {
    @Override
    public void show() {
        A.super.show();
    }
}
```

The implementing class must explicitly resolve the conflict.

---

## Scenario 10: Class Method Beats Interface Default

```java
class Parent {
    public void show() {
        System.out.println("Parent");
    }
}

interface Displayable {
    default void show() {
        System.out.println("Interface");
    }
}

class Child extends Parent implements Displayable {
}
```

```java
new Child().show();
```

Output:

```text
Parent
```

A concrete class method takes precedence over an interface default method.

---

# 33. Coding Examples

## Example 1: Encapsulated Bank Account

```java
class BankAccount {
    private final String accountNumber;
    private double balance;

    BankAccount(
            String accountNumber,
            double openingBalance
    ) {
        if (openingBalance < 0) {
            throw new IllegalArgumentException(
                    "Opening balance cannot be negative"
            );
        }

        this.accountNumber = accountNumber;
        this.balance = openingBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Deposit amount must be positive"
            );
        }

        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            throw new IllegalArgumentException(
                    "Invalid withdrawal amount"
            );
        }

        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
```

---

## Example 2: Runtime Polymorphism

```java
abstract class Shape {
    abstract double area();
}

class Circle extends Shape {
    private final double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    private final double length;
    private final double width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    double area() {
        return length * width;
    }
}
```

Usage:

```java
List<Shape> shapes = List.of(
        new Circle(5),
        new Rectangle(4, 6)
);

for (Shape shape : shapes) {
    System.out.println(shape.area());
}
```

Each object uses its own implementation of `area()`.

---

## Example 3: Interface-Based Payment System

```java
interface PaymentMethod {
    void pay(double amount);
}

class CardPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println(
                "Paid using card: " + amount
        );
    }
}

class UpiPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println(
                "Paid using UPI: " + amount
        );
    }
}

class CheckoutService {
    private final PaymentMethod paymentMethod;

    CheckoutService(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    void checkout(double amount) {
        paymentMethod.pay(amount);
    }
}
```

Usage:

```java
CheckoutService checkout =
        new CheckoutService(new CardPayment());

checkout.checkout(500);
```

This follows dependency inversion and composition.

---

## Example 4: Immutable Employee

```java
import java.util.List;

final class Employee {
    private final int id;
    private final String name;
    private final List<String> skills;

    Employee(
            int id,
            String name,
            List<String> skills
    ) {
        this.id = id;
        this.name = name;
        this.skills = List.copyOf(skills);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getSkills() {
        return skills;
    }
}
```

The internal list cannot be modified through the getter.

---

## Example 5: Method Overloading

```java
class Printer {
    void print(int value) {
        System.out.println("Integer: " + value);
    }

    void print(double value) {
        System.out.println("Double: " + value);
    }

    void print(String value) {
        System.out.println("String: " + value);
    }
}
```

Usage:

```java
Printer printer = new Printer();

printer.print(10);
printer.print(10.5);
printer.print("Java");
```

---

## Example 6: Composition

```java
class Engine {
    void start() {
        System.out.println("Engine started");
    }

    void stop() {
        System.out.println("Engine stopped");
    }
}

class Car {
    private final Engine engine;

    Car(Engine engine) {
        this.engine = engine;
    }

    void startCar() {
        engine.start();
        System.out.println("Car started");
    }

    void stopCar() {
        engine.stop();
        System.out.println("Car stopped");
    }
}
```

Usage:

```java
Engine engine = new Engine();
Car car = new Car(engine);

car.startCar();
```

---

## Example 7: Factory Method with Private Constructor

```java
class DatabaseConnection {
    private final String url;

    private DatabaseConnection(String url) {
        this.url = url;
    }

    public static DatabaseConnection connect(
            String url
    ) {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException(
                    "URL cannot be empty"
            );
        }

        return new DatabaseConnection(url);
    }

    public String getUrl() {
        return url;
    }
}
```

Usage:

```java
DatabaseConnection connection =
        DatabaseConnection.connect(
                "jdbc:mysql://localhost/app"
        );
```

---

## Example 8: Singleton

```java
final class ApplicationConfig {
    private static final ApplicationConfig INSTANCE =
            new ApplicationConfig();

    private ApplicationConfig() {
    }

    public static ApplicationConfig getInstance() {
        return INSTANCE;
    }
}
```

Usage:

```java
ApplicationConfig first =
        ApplicationConfig.getInstance();

ApplicationConfig second =
        ApplicationConfig.getInstance();

System.out.println(first == second);
```

Output:

```text
true
```

---

## Example 9: Comparable Domain Object

```java
class Student implements Comparable<Student> {
    private final int id;
    private final String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}
```

This demonstrates abstraction through a contract and polymorphic sorting behaviour.

---

## Example 10: Loose Coupling with Dependency Injection

```java
interface Repository {
    void save(String data);
}

class DatabaseRepository implements Repository {
    @Override
    public void save(String data) {
        System.out.println(
                "Saved to database: " + data
        );
    }
}

class FileRepository implements Repository {
    @Override
    public void save(String data) {
        System.out.println(
                "Saved to file: " + data
        );
    }
}

class ReportService {
    private final Repository repository;

    ReportService(Repository repository) {
        this.repository = repository;
    }

    void generate(String report) {
        repository.save(report);
    }
}
```

Usage:

```java
ReportService service =
        new ReportService(
                new DatabaseRepository()
        );

service.generate("Annual Report");
```

The service depends on an abstraction rather than a concrete repository.

---

# 34. Quick Revision Cheat Sheet

## Class

```text
Blueprint for objects
Defines fields and methods
```

## Object

```text
Instance of a class
Has state, behaviour, and identity
```

## Constructor

```text
Initialises an object
Same name as class
No return type
Can be overloaded
Cannot be overridden
```

## `this`

```text
Refers to current object
Accesses current fields and methods
Calls another constructor using this()
```

## `super`

```text
Refers to immediate parent
Accesses parent fields and methods
Calls parent constructor using super()
```

## Encapsulation

```text
Hide internal state
Use private fields
Provide controlled methods
```

## Inheritance

```text
Represents IS-A
Uses extends
Promotes reuse and polymorphism
```

## Polymorphism

```text
One reference, multiple implementations
```

Compile time:

```text
Method overloading
```

Runtime:

```text
Method overriding
```

## Abstraction

```text
Expose essential behaviour
Hide implementation details
```

Achieved using:

```text
Abstract classes
Interfaces
```

## Overloading

```text
Same method name
Different parameters
Compile-time resolution
```

## Overriding

```text
Same signature
Child provides new implementation
Runtime resolution
```

## Abstract Class

```text
Can have state
Can have constructors
Can have abstract and concrete methods
Single class inheritance
```

## Interface

```text
Defines a contract
Supports multiple implementation
Can have default, static, and private methods
```

## Static

```text
Belongs to class
Shared among objects
Cannot directly access instance members
```

## Final

```text
Final variable → Assigned once
Final method   → Cannot be overridden
Final class    → Cannot be extended
```

## Upcasting

```java
Animal animal = new Dog();
```

Safe and implicit.

## Downcasting

```java
Dog dog = (Dog) animal;
```

Explicit and potentially unsafe.

## Association

```text
General object relationship
```

## Aggregation

```text
Weak HAS-A
Independent lifecycle
```

## Composition

```text
Strong HAS-A
Owned lifecycle
```

## Object Class

Important methods:

```text
equals()
hashCode()
toString()
getClass()
wait()
notify()
notifyAll()
```

## `==` vs `equals()`

```text
==       → Reference comparison
equals() → Logical comparison
```

## Strong OOP Design

```text
High cohesion
Low coupling
Prefer composition over inheritance
Program to interfaces
Protect object invariants
```

---

# Final Interview Selection Guide

| Requirement                                          | Recommended Concept                  |
| ---------------------------------------------------- | ------------------------------------ |
| Protect internal state                               | Encapsulation                        |
| Share common implementation                          | Abstract class                       |
| Define a behaviour contract                          | Interface                            |
| Reuse through a true IS-A relationship               | Inheritance                          |
| Replace behaviour flexibly                           | Composition                          |
| Same operation with different parameters             | Overloading                          |
| Different implementation in child classes            | Overriding                           |
| Represent multiple child types through one reference | Runtime polymorphism                 |
| Prevent class inheritance                            | Final class                          |
| Create objects through controlled logic              | Private constructor and factory      |
| Design thread-safe value objects                     | Immutability                         |
| Reduce dependencies                                  | Abstraction and dependency injection |

---

# One-Line Summary

> Java OOP combines classes and objects with encapsulation, inheritance, abstraction, and polymorphism to create modular, reusable, maintainable, and flexible software.

---

**End of Java OOP Interview Notes**
