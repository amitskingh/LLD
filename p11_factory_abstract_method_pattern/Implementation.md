````markdown
# Abstract Factory Pattern — Implementation.md

This document walks through the implementation of the **Abstract Factory Pattern** using a cross-platform UI example.

We will support:

```text
Windows
Mac
````

Each platform has a family of UI components:

```text
Button
Checkbox
```

---

# 1. Project Structure

```text
p11_factory_abstract_method_pattern/
│
├── Button.java
├── Checkbox.java
│
├── WindowsButton.java
├── WindowsCheckbox.java
│
├── MacButton.java
├── MacCheckbox.java
│
├── GUIFactory.java
├── WindowsFactory.java
├── MacFactory.java
│
└── Main.java
```

Responsibilities:

```text
Button.java
Checkbox.java
        ↓
Abstract Products

WindowsButton.java
WindowsCheckbox.java
MacButton.java
MacCheckbox.java
        ↓
Concrete Products

GUIFactory.java
        ↓
Abstract Factory

WindowsFactory.java
MacFactory.java
        ↓
Concrete Factories

Main.java
        ↓
Client
```

---

# 2. Create the Abstract Product — Button

Create:

```text
Button.java
```

```java
package p11_factory_abstract_method_pattern;

public interface Button {

    void render();

}
```

This defines the common behavior of all Buttons.

The client will work with:

```java
Button
```

rather than:

```text
WindowsButton
MacButton
```

---

# 3. Create the Abstract Product — Checkbox

Create:

```text
Checkbox.java
```

```java
package p11_factory_abstract_method_pattern;

public interface Checkbox {

    void render();

}
```

Now we have two abstract product types:

```text
Button
Checkbox
```

---

# 4. Create Windows Products

## WindowsButton

Create:

```text
WindowsButton.java
```

```java
package p11_factory_abstract_method_pattern;

public class WindowsButton implements Button {

    @Override
    public void render() {
        System.out.println("Rendering Windows Button");
    }

}
```

---

## WindowsCheckbox

Create:

```text
WindowsCheckbox.java
```

```java
package p11_factory_abstract_method_pattern;

public class WindowsCheckbox implements Checkbox {

    @Override
    public void render() {
        System.out.println("Rendering Windows Checkbox");
    }

}
```

Now we have the Windows product family:

```text
Windows Family
│
├── WindowsButton
└── WindowsCheckbox
```

---

# 5. Create Mac Products

## MacButton

Create:

```text
MacButton.java
```

```java
package p11_factory_abstract_method_pattern;

public class MacButton implements Button {

    @Override
    public void render() {
        System.out.println("Rendering Mac Button");
    }

}
```

---

## MacCheckbox

Create:

```text
MacCheckbox.java
```

```java
package p11_factory_abstract_method_pattern;

public class MacCheckbox implements Checkbox {

    @Override
    public void render() {
        System.out.println("Rendering Mac Checkbox");
    }

}
```

Now we have the Mac product family:

```text
Mac Family
│
├── MacButton
└── MacCheckbox
```

---

# 6. Create the Abstract Factory

Create:

```text
GUIFactory.java
```

```java
package p11_factory_abstract_method_pattern;

public interface GUIFactory {

    Button createButton();

    Checkbox createCheckbox();

}
```

This is the central interface of the pattern.

Notice that it has **multiple creation methods**:

```java
createButton();

createCheckbox();
```

The factory represents an entire product family.

---

# 7. Create WindowsFactory

Create:

```text
WindowsFactory.java
```

```java
package p11_factory_abstract_method_pattern;

public class WindowsFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }

}
```

The important thing is that both products belong to the same family:

```text
WindowsFactory
      │
      ├── new WindowsButton()
      │
      └── new WindowsCheckbox()
```

---

# 8. Create MacFactory

Create:

```text
MacFactory.java
```

```java
package p11_factory_abstract_method_pattern;

public class MacFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }

}
```

Now:

```text
MacFactory
      │
      ├── new MacButton()
      │
      └── new MacCheckbox()
```

---

# 9. Create the Client

Create:

```text
Main.java
```

```java
package p11_factory_abstract_method_pattern;

public class Main {

    public static void main(String[] args) {

        GUIFactory factory =
            new WindowsFactory();

        Button button =
            factory.createButton();

        Checkbox checkbox =
            factory.createCheckbox();

        button.render();
        checkbox.render();
    }

}
```

Output:

```text
Rendering Windows Button
Rendering Windows Checkbox
```

---

# 10. What Happened?

The client creates:

```java
GUIFactory factory =
    new WindowsFactory();
```

This chooses the **Windows product family**.

Then:

```java
factory.createButton();
```

calls:

```java
WindowsFactory.createButton()
```

which creates:

```java
new WindowsButton();
```

Similarly:

```java
factory.createCheckbox();
```

calls:

```java
WindowsFactory.createCheckbox()
```

which creates:

```java
new WindowsCheckbox();
```

---

# 11. Complete Execution Flow

```text
Main
 │
 │ new WindowsFactory()
 ▼
WindowsFactory
 │
 ├── createButton()
 │       │
 │       ▼
 │   new WindowsButton()
 │
 └── createCheckbox()
         │
         ▼
     new WindowsCheckbox()
```

The client only sees:

```text
GUIFactory
Button
Checkbox
```

It doesn't directly create:

```text
WindowsButton
WindowsCheckbox
```

---

# 12. Switch the Entire Product Family

Now change:

```java
GUIFactory factory =
    new WindowsFactory();
```

to:

```java
GUIFactory factory =
    new MacFactory();
```

Nothing else needs to change.

The same code:

```java
Button button =
    factory.createButton();

Checkbox checkbox =
    factory.createCheckbox();
```

now creates:

```text
MacButton
MacCheckbox
```

Output:

```text
Rendering Mac Button
Rendering Mac Checkbox
```

---

# 13. Why This Is Useful

The client code remains:

```java
Button button =
    factory.createButton();

Checkbox checkbox =
    factory.createCheckbox();
```

The client doesn't need:

```java
new WindowsButton();
new WindowsCheckbox();
```

or:

```java
new MacButton();
new MacCheckbox();
```

The selected factory determines the entire family.

```text
WindowsFactory
      ↓
WindowsButton
WindowsCheckbox
```

or:

```text
MacFactory
      ↓
MacButton
MacCheckbox
```

---

# 14. The Important Family Guarantee

Suppose we select:

```java
GUIFactory factory =
    new WindowsFactory();
```

Then:

```java
factory.createButton();
```

returns:

```text
WindowsButton
```

and:

```java
factory.createCheckbox();
```

returns:

```text
WindowsCheckbox
```

So both products belong to:

```text
Windows Family
```

The client doesn't have to manually coordinate them.

---

# 15. Complete Code

## Button.java

```java
package p11_factory_abstract_method_pattern;

public interface Button {

    void render();

}
```

---

## Checkbox.java

```java
package p11_factory_abstract_method_pattern;

public interface Checkbox {

    void render();

}
```

---

## WindowsButton.java

```java
package p11_factory_abstract_method_pattern;

public class WindowsButton implements Button {

    @Override
    public void render() {
        System.out.println("Rendering Windows Button");
    }

}
```

---

## WindowsCheckbox.java

```java
package p11_factory_abstract_method_pattern;

public class WindowsCheckbox implements Checkbox {

    @Override
    public void render() {
        System.out.println("Rendering Windows Checkbox");
    }

}
```

---

## MacButton.java

```java
package p11_factory_abstract_method_pattern;

public class MacButton implements Button {

    @Override
    public void render() {
        System.out.println("Rendering Mac Button");
    }

}
```

---

## MacCheckbox.java

```java
package p11_factory_abstract_method_pattern;

public class MacCheckbox implements Checkbox {

    @Override
    public void render() {
        System.out.println("Rendering Mac Checkbox");
    }

}
```

---

## GUIFactory.java

```java
package p11_factory_abstract_method_pattern;

public interface GUIFactory {

    Button createButton();

    Checkbox createCheckbox();

}
```

---

## WindowsFactory.java

```java
package p11_factory_abstract_method_pattern;

public class WindowsFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }

}
```

---

## MacFactory.java

```java
package p11_factory_abstract_method_pattern;

public class MacFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }

}
```

---

## Main.java

```java
package p11_factory_abstract_method_pattern;

public class Main {

    public static void main(String[] args) {

        GUIFactory factory =
            new WindowsFactory();

        Button button =
            factory.createButton();

        Checkbox checkbox =
            factory.createCheckbox();

        button.render();
        checkbox.render();
    }

}
```

---

# 16. Compile and Run

From the parent directory:

```bash
javac p11_factory_abstract_method_pattern/*.java
```

Run:

```bash
java p11_factory_abstract_method_pattern.Main
```

Output:

```text
Rendering Windows Button
Rendering Windows Checkbox
```

---

# 17. Add a New Product Family

Suppose we now want Linux.

We create:

```text
LinuxButton.java
LinuxCheckbox.java
LinuxFactory.java
```

## LinuxButton

```java
package p11_factory_abstract_method_pattern;

public class LinuxButton implements Button {

    @Override
    public void render() {
        System.out.println("Rendering Linux Button");
    }

}
```

## LinuxCheckbox

```java
package p11_factory_abstract_method_pattern;

public class LinuxCheckbox implements Checkbox {

    @Override
    public void render() {
        System.out.println("Rendering Linux Checkbox");
    }

}
```

## LinuxFactory

```java
package p11_factory_abstract_method_pattern;

public class LinuxFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new LinuxButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new LinuxCheckbox();
    }

}
```

Now the client can simply use:

```java
GUIFactory factory =
    new LinuxFactory();
```

The rest of the client remains unchanged.

---

# 18. Important Tradeoff — Adding a New Product Type

Now suppose we add:

```text
Textbox
```

We need to modify the Abstract Factory:

```java
public interface GUIFactory {

    Button createButton();

    Checkbox createCheckbox();

    Textbox createTextbox();
}
```

But now every concrete factory must also implement:

```java
createTextbox();
```

So:

```text
New Product Type
      ↓
Modify GUIFactory
      ↓
Modify WindowsFactory
      ↓
Modify MacFactory
      ↓
Modify LinuxFactory
```

This is an important tradeoff of Abstract Factory.

---

# 19. Final Structure

```text
                    GUIFactory
                   <<interface>>
                 /              \
                /                \
               ▼                  ▼
      WindowsFactory          MacFactory
           │                       │
      ┌────┴────┐             ┌────┴────┐
      ▼         ▼             ▼         ▼
WindowsButton WindowsCheckbox MacButton MacCheckbox
      ▲             ▲            ▲          ▲
      │             │            │          │
      └───────┐     │     ┌──────┘          │
              │     │     │                 │
              ▼     ▼     ▼                 ▼
             Button      Checkbox
             <<interface>>
```

---

# Implementation Takeaway

The implementation follows this sequence:

```text
1. Define abstract product interfaces
        ↓
2. Create concrete products for each family
        ↓
3. Define Abstract Factory
        ↓
4. Add one creation method per product type
        ↓
5. Create one Concrete Factory per product family
        ↓
6. Each Concrete Factory creates products
   from its own family
        ↓
7. Client depends only on factory/product interfaces
        ↓
8. Switch the entire product family
   by changing the Concrete Factory
```

### Core implementation idea

> **The Concrete Factory is the family selector. Once the client chooses a factory, every product created through that factory belongs to the same product family.**
