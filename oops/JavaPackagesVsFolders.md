# Java Packages vs Folders

## Key Idea

> **Folders are physical directories. Packages are logical namespaces.**

Java identifies a class by its **Fully Qualified Name (FQN)**:

```text
packageName.ClassName
```

Example:

```java
package observer.push;

public class Main {}
```

FQN:

```text
observer.push.Main
```

---

## Without a Package

```text
project/
├── folder1/
│   └── Main.java
└── folder2/
    └── Main.java
```

```java
public class Main {}
```

Both classes belong to the **default package**.

Java sees:

```text
Main
Main
```

❌ Duplicate class names → Compiler Error

> **Folders do NOT create packages.**

---

## With Packages

```text
project/
├── folder1/
│   └── Main.java
└── folder2/
    └── Main.java
```

`folder1/Main.java`

```java
package folder1;

public class Main {}
```

`folder2/Main.java`

```java
package folder2;

public class Main {}
```

Java sees:

```text
folder1.Main
folder2.Main
```

✅ Different classes

---

## Package Declaration is the Source of Truth

Java determines the package from:

```java
package folder1;
```

**NOT** from the folder name.

The folder should match the package, but it does **not** create the package.

---

## Running Classes

Without package:

```bash
javac Main.java
java Main
```

With package:

```text
project/
└── observer/
    └── Main.java
```

```java
package observer;
```

Compile from project root:

```bash
javac observer/*.java
```

Run:

```bash
java observer.Main
```

---

## Can Two Classes Have the Same Name?

✅ Yes, if they are in different packages.

Example:

```text
java.util.Date
java.sql.Date
```

---

## Package Naming Rules

✅ Valid

```java
package observer;
package observer.push;
package p07_observer_pattern;
```

❌ Invalid

```java
package 07_observer;     // starts with digit
package observer-pattern; // '-' not allowed
package my package;       // spaces not allowed
```

Convention: **Use lowercase package names.**

---

## Memory Trick

```text
Folder
   ↓
Physical location

Package
   ↓
Logical namespace

Fully Qualified Name
   ↓
packageName.ClassName
```

> **Remember:** Java cares about the **package declaration**, not the folder. The folder is expected to mirror the package structure.