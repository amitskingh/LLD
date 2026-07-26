## Types of Classes in Java

### `public class C`

* Accessible from any package.
* Can be imported and used anywhere.
* Used for classes that need to be exposed publicly.

### `class C` (Package-Private / Default)

* Accessible only within the same package.
* Cannot be accessed from other packages.
* Used for internal/helper classes.

### `abstract class C`

* Cannot create objects directly.
* Intended to be extended by other classes.
* Can contain both abstract and concrete methods.

### `final class C`

* Cannot be inherited (extended).
* Used when the class implementation should not be modified through inheritance.
* Example: String is a final class in Java.

### Quick Memory

| Type             | Meaning                        |
| ---------------- | ------------------------------ |
| `public class`   | Accessible everywhere          |
| `class`          | Accessible within same package |
| `abstract class` | Must be inherited              |
| `final class`    | Cannot be inherited            |
