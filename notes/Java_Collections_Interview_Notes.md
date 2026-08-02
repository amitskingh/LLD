# Java Collections Framework — Interview and Revision Notes

> Comprehensive notes for Java interview preparation and quick revision.

---

# Table of Contents

1. Introduction to Collections
2. Collection Framework Hierarchy
3. Collection vs Collections vs Arrays
4. Iterable and Iterator
5. List Interface
6. ArrayList
7. LinkedList
8. Vector
9. Stack
10. Set Interface
11. HashSet
12. LinkedHashSet
13. TreeSet
14. Queue Interface
15. PriorityQueue
16. Deque and ArrayDeque
17. Map Interface
18. HashMap
19. LinkedHashMap
20. TreeMap
21. Hashtable
22. ConcurrentHashMap
23. Comparable and Comparator
24. Iterator vs ListIterator
25. Fail-Fast vs Fail-Safe
26. Immutable Collections
27. Collections Utility Class
28. Time Complexity
29. Common Interview Questions
30. Coding Examples
31. Quick Revision Cheat Sheet

---

# 1. Introduction to Collections

The Java Collections Framework provides classes and interfaces used to store and manipulate groups of objects.

Common operations include:

* Adding elements
* Removing elements
* Searching elements
* Sorting elements
* Iterating over elements
* Filtering elements
* Grouping key-value pairs

Example:

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();

        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");

        System.out.println(names);
    }
}
```

Output:

```text
[Alice, Bob, Charlie]
```

---

# 2. Collection Framework Hierarchy

```text
Iterable
   |
Collection
   |
   |-------------------------------
   |              |               |
  List           Set             Queue
   |              |               |
ArrayList       HashSet       PriorityQueue
LinkedList      LinkedHashSet  Deque
Vector          SortedSet          |
Stack              |           ArrayDeque
                 TreeSet
```

`Map` is part of the Collections Framework, but it does not extend the `Collection` interface.

```text
Map
 |
 |-----------------------------
 |             |              |
HashMap   LinkedHashMap     SortedMap
Hashtable ConcurrentHashMap    |
                              TreeMap
```

---

# 3. Collection vs Collections vs Arrays

## Collection

`Collection` is an interface representing a group of objects.

```java
Collection<String> names = new ArrayList<>();
```

## Collections

`Collections` is a utility class containing static methods such as:

* `sort()`
* `reverse()`
* `shuffle()`
* `min()`
* `max()`
* `frequency()`

```java
Collections.sort(names);
```

## Arrays

`Arrays` is a utility class for arrays.

```java
int[] numbers = {4, 2, 1, 3};
Arrays.sort(numbers);
```

| Term          | Meaning                                |
| ------------- | -------------------------------------- |
| `Collection`  | Root interface for List, Set and Queue |
| `Collections` | Utility class for collections          |
| `Arrays`      | Utility class for arrays               |

---

# 4. Iterable and Iterator

The `Iterable` interface allows an object to be used in an enhanced `for` loop.

```java
public interface Iterable<T> {
    Iterator<T> iterator();
}
```

Example:

```java
List<String> names = List.of("Alice", "Bob", "Charlie");

for (String name : names) {
    System.out.println(name);
}
```

Internally, the enhanced `for` loop uses an `Iterator`.

## Iterator Methods

```java
boolean hasNext();
E next();
void remove();
```

Example:

```java
List<String> names = new ArrayList<>();
names.add("Alice");
names.add("Bob");
names.add("Charlie");

Iterator<String> iterator = names.iterator();

while (iterator.hasNext()) {
    String name = iterator.next();

    if (name.equals("Bob")) {
        iterator.remove();
    }
}

System.out.println(names);
```

Output:

```text
[Alice, Charlie]
```

---

# 5. List Interface

A `List`:

* Maintains insertion order
* Allows duplicate elements
* Supports index-based access
* Allows multiple `null` values in most implementations

Common implementations:

* `ArrayList`
* `LinkedList`
* `Vector`
* `Stack`

Example:

```java
List<String> languages = new ArrayList<>();

languages.add("Java");
languages.add("Python");
languages.add("Java");

System.out.println(languages);
```

Output:

```text
[Java, Python, Java]
```

---

# 6. ArrayList

`ArrayList` is a resizable array implementation of the `List` interface.

## Characteristics

* Maintains insertion order
* Allows duplicates
* Allows `null`
* Fast random access
* Slower insertion and deletion in the middle
* Not synchronised

Example:

```java
import java.util.ArrayList;
import java.util.List;

public class ArrayListExample {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();

        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Banana");

        System.out.println(fruits);
        System.out.println(fruits.get(1));

        fruits.set(1, "Mango");
        fruits.remove("Orange");

        System.out.println(fruits);
    }
}
```

Output:

```text
[Apple, Banana, Orange, Banana]
Banana
[Apple, Mango, Banana]
```

## Internal Working

An `ArrayList` internally uses a dynamically resized array.

When the internal array becomes full, a larger array is created and existing elements are copied into it.

## Time Complexity

| Operation        |     Complexity |
| ---------------- | -------------: |
| Get by index     |           O(1) |
| Set by index     |           O(1) |
| Add at end       | O(1) amortised |
| Insert in middle |           O(n) |
| Remove by index  |           O(n) |
| Search           |           O(n) |

---

# 7. LinkedList

`LinkedList` is a doubly linked list implementation.

It implements:

* `List`
* `Deque`
* `Queue`

## Characteristics

* Maintains insertion order
* Allows duplicates
* Allows `null`
* Faster insertion and deletion at the ends
* Slower random access
* Not synchronised

Example:

```java
import java.util.LinkedList;

public class LinkedListExample {
    public static void main(String[] args) {
        LinkedList<String> names = new LinkedList<>();

        names.add("Bob");
        names.addFirst("Alice");
        names.addLast("Charlie");

        System.out.println(names);

        names.removeFirst();
        names.removeLast();

        System.out.println(names);
    }
}
```

Output:

```text
[Alice, Bob, Charlie]
[Bob]
```

## ArrayList vs LinkedList

| Feature            | ArrayList      | LinkedList                      |
| ------------------ | -------------- | ------------------------------- |
| Internal structure | Dynamic array  | Doubly linked list              |
| Random access      | Fast           | Slow                            |
| Add at end         | Fast           | Fast                            |
| Add in middle      | Usually slower | Efficient after node is located |
| Memory usage       | Lower          | Higher                          |
| Implements Deque   | No             | Yes                             |

Interview note:

> `LinkedList` insertion is not automatically O(1) when inserting by index. Finding the target node still takes O(n).

---

# 8. Vector

`Vector` is a resizable array similar to `ArrayList`.

## Characteristics

* Maintains insertion order
* Allows duplicates
* Synchronised
* Legacy class
* Usually slower than `ArrayList`

Example:

```java
Vector<String> vector = new Vector<>();

vector.add("Java");
vector.add("Python");
vector.add("Go");

System.out.println(vector);
```

Prefer `ArrayList` for normal single-threaded code.

For concurrent requirements, modern concurrent collections are usually preferred over `Vector`.

---

# 9. Stack

`Stack` represents a Last-In, First-Out structure.

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.pop());
System.out.println(stack.peek());
```

Output:

```text
30
20
```

Common methods:

| Method     | Purpose                       |
| ---------- | ----------------------------- |
| `push()`   | Add an element                |
| `pop()`    | Remove and return top element |
| `peek()`   | Return top element            |
| `empty()`  | Check whether stack is empty  |
| `search()` | Find position from top        |

Interview recommendation:

> Prefer `Deque` or `ArrayDeque` instead of the legacy `Stack` class.

```java
Deque<Integer> stack = new ArrayDeque<>();

stack.push(10);
stack.push(20);

System.out.println(stack.pop());
```

---

# 10. Set Interface

A `Set` stores unique elements.

Common implementations:

* `HashSet`
* `LinkedHashSet`
* `TreeSet`

Example:

```java
Set<String> names = new HashSet<>();

names.add("Alice");
names.add("Bob");
names.add("Alice");

System.out.println(names);
```

Only one `"Alice"` is stored.

---

# 11. HashSet

`HashSet` stores unique elements using hashing.

## Characteristics

* Does not guarantee insertion order
* Does not allow duplicates
* Allows one `null`
* Fast insertion, deletion and search
* Internally backed by a `HashMap`
* Not synchronised

Example:

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(30);
numbers.add(10);
numbers.add(20);
numbers.add(10);

System.out.println(numbers);
System.out.println(numbers.contains(20));
```

Possible output:

```text
[20, 10, 30]
true
```

The order is not guaranteed.

## How HashSet Detects Duplicates

`HashSet` uses:

1. `hashCode()`
2. `equals()`

When adding an object:

* Java calculates its hash code.
* It locates the corresponding bucket.
* It checks existing objects using `equals()`.
* If an equal object already exists, the new object is not added.

## Custom Object Example

```java
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}

public class Main {
    public static void main(String[] args) {
        Set<Employee> employees = new HashSet<>();

        employees.add(new Employee(1, "Alice"));
        employees.add(new Employee(1, "Alicia"));
        employees.add(new Employee(2, "Bob"));

        System.out.println(employees);
    }
}
```

The two employees with ID `1` are treated as duplicates.

---

# 12. LinkedHashSet

`LinkedHashSet` maintains insertion order while preventing duplicates.

Example:

```java
Set<String> cities = new LinkedHashSet<>();

cities.add("London");
cities.add("Manchester");
cities.add("Birmingham");
cities.add("London");

System.out.println(cities);
```

Output:

```text
[London, Manchester, Birmingham]
```

Use `LinkedHashSet` when:

* Unique elements are required
* Insertion order must be preserved

---

# 13. TreeSet

`TreeSet` stores unique elements in sorted order.

## Characteristics

* Does not allow duplicates
* Maintains natural or custom sorted order
* Usually does not permit `null`
* Implements `NavigableSet`
* Internally uses a balanced tree

Example:

```java
Set<Integer> numbers = new TreeSet<>();

numbers.add(40);
numbers.add(10);
numbers.add(30);
numbers.add(20);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30, 40]
```

## NavigableSet Methods

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);
numbers.add(40);

System.out.println(numbers.lower(30));
System.out.println(numbers.floor(30));
System.out.println(numbers.higher(30));
System.out.println(numbers.ceiling(25));
```

Output:

```text
20
30
40
30
```

| Method       | Meaning                                       |
| ------------ | --------------------------------------------- |
| `lower(x)`   | Greatest element strictly less than `x`       |
| `floor(x)`   | Greatest element less than or equal to `x`    |
| `higher(x)`  | Smallest element strictly greater than `x`    |
| `ceiling(x)` | Smallest element greater than or equal to `x` |

## HashSet vs LinkedHashSet vs TreeSet

| Feature            | HashSet      | LinkedHashSet            | TreeSet       |
| ------------------ | ------------ | ------------------------ | ------------- |
| Unique elements    | Yes          | Yes                      | Yes           |
| Ordering           | No guarantee | Insertion order          | Sorted order  |
| Typical operations | O(1)         | O(1)                     | O(log n)      |
| Allows `null`      | One          | One                      | Generally no  |
| Internal structure | Hash table   | Hash table + linked list | Balanced tree |

---

# 14. Queue Interface

A queue commonly follows First-In, First-Out order.

Example:

```java
Queue<String> queue = new LinkedList<>();

queue.offer("Alice");
queue.offer("Bob");
queue.offer("Charlie");

System.out.println(queue.poll());
System.out.println(queue.peek());
```

Output:

```text
Alice
Bob
```

## Queue Method Pairs

| Throws exception | Returns special value |
| ---------------- | --------------------- |
| `add()`          | `offer()`             |
| `remove()`       | `poll()`              |
| `element()`      | `peek()`              |

`offer()`, `poll()` and `peek()` are generally safer because they return a special value instead of throwing an exception for normal failure conditions.

---

# 15. PriorityQueue

`PriorityQueue` processes elements according to priority rather than insertion order.

By default, the smallest element has the highest priority.

Example:

```java
Queue<Integer> queue = new PriorityQueue<>();

queue.offer(30);
queue.offer(10);
queue.offer(20);

while (!queue.isEmpty()) {
    System.out.println(queue.poll());
}
```

Output:

```text
10
20
30
```

## Maximum Priority Queue

```java
Queue<Integer> maxHeap =
        new PriorityQueue<>(Comparator.reverseOrder());

maxHeap.offer(10);
maxHeap.offer(30);
maxHeap.offer(20);

System.out.println(maxHeap.poll());
```

Output:

```text
30
```

Important:

> Iterating over a `PriorityQueue` does not guarantee sorted order. Repeatedly calling `poll()` returns elements according to priority.

---

# 16. Deque and ArrayDeque

`Deque` means double-ended queue.

Elements can be added or removed from both ends.

Example:

```java
Deque<String> deque = new ArrayDeque<>();

deque.addFirst("Bob");
deque.addFirst("Alice");
deque.addLast("Charlie");

System.out.println(deque);
```

Output:

```text
[Alice, Bob, Charlie]
```

## Using ArrayDeque as a Stack

```java
Deque<Integer> stack = new ArrayDeque<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.pop());
```

Output:

```text
30
```

## Using ArrayDeque as a Queue

```java
Deque<Integer> queue = new ArrayDeque<>();

queue.offer(10);
queue.offer(20);
queue.offer(30);

System.out.println(queue.poll());
```

Output:

```text
10
```

`ArrayDeque`:

* Is generally preferred over `Stack`
* Is often faster than `LinkedList` for queue and stack operations
* Does not allow `null`

---

# 17. Map Interface

A `Map` stores key-value pairs.

## Characteristics

* Keys must be unique
* Values may be duplicated
* Each key maps to one value

Example:

```java
Map<Integer, String> employees = new HashMap<>();

employees.put(101, "Alice");
employees.put(102, "Bob");
employees.put(103, "Charlie");

System.out.println(employees.get(102));
```

Output:

```text
Bob
```

Common implementations:

* `HashMap`
* `LinkedHashMap`
* `TreeMap`
* `Hashtable`
* `ConcurrentHashMap`

---

# 18. HashMap

`HashMap` stores key-value pairs using hashing.

## Characteristics

* No guaranteed order
* Allows one `null` key
* Allows multiple `null` values
* Not synchronised
* Average O(1) lookup, insertion and deletion

Example:

```java
Map<String, Integer> scores = new HashMap<>();

scores.put("Alice", 90);
scores.put("Bob", 80);
scores.put("Charlie", 85);

System.out.println(scores.get("Alice"));

scores.put("Alice", 95);

System.out.println(scores);
```

The second `put()` replaces the previous value associated with `"Alice"`.

## Useful HashMap Methods

```java
map.put(key, value);
map.putIfAbsent(key, value);
map.get(key);
map.getOrDefault(key, defaultValue);
map.containsKey(key);
map.containsValue(value);
map.remove(key);
map.replace(key, value);
map.size();
map.isEmpty();
map.clear();
```

Example:

```java
Map<String, Integer> stock = new HashMap<>();

stock.put("Laptop", 5);
stock.putIfAbsent("Phone", 10);

System.out.println(stock.getOrDefault("Tablet", 0));
```

Output:

```text
0
```

## Iterating Over a Map

### Using `entrySet()`

```java
for (Map.Entry<String, Integer> entry : scores.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}
```

### Using `keySet()`

```java
for (String key : scores.keySet()) {
    System.out.println(key + ": " + scores.get(key));
}
```

### Using `forEach()`

```java
scores.forEach((name, score) ->
        System.out.println(name + ": " + score)
);
```

For accessing both keys and values, `entrySet()` is usually preferable to calling `get()` for every key.

## HashMap Internal Working

When `put(key, value)` is called:

1. Java calculates the key's hash code.
2. The hash is transformed to identify a bucket.
3. If the bucket is empty, the entry is inserted.
4. If entries already exist, Java compares keys using `equals()`.
5. If an equal key exists, its value is replaced.
6. Otherwise, a new entry is added to the bucket.

In modern Java versions, heavily populated buckets may be transformed from linked structures into balanced trees under specific conditions.

## Null Key

`HashMap` allows one `null` key.

```java
Map<String, Integer> map = new HashMap<>();

map.put(null, 100);
map.put(null, 200);

System.out.println(map);
```

Output:

```text
{null=200}
```

The second value replaces the first because keys are unique.

---

# 19. LinkedHashMap

`LinkedHashMap` maintains predictable iteration order.

By default, it maintains insertion order.

Example:

```java
Map<Integer, String> map = new LinkedHashMap<>();

map.put(3, "Charlie");
map.put(1, "Alice");
map.put(2, "Bob");

System.out.println(map);
```

Output:

```text
{3=Charlie, 1=Alice, 2=Bob}
```

## Access-Order LinkedHashMap

A `LinkedHashMap` can also maintain access order.

```java
Map<Integer, String> map =
        new LinkedHashMap<>(16, 0.75f, true);

map.put(1, "Alice");
map.put(2, "Bob");
map.put(3, "Charlie");

map.get(1);

System.out.println(map);
```

Output:

```text
{2=Bob, 3=Charlie, 1=Alice}
```

This behaviour can be used when implementing an LRU cache.

---

# 20. TreeMap

`TreeMap` stores entries sorted by key.

## Characteristics

* Keys are sorted
* Does not allow duplicate keys
* Normally does not allow `null` keys
* Allows multiple `null` values
* Implements `NavigableMap`
* Operations are typically O(log n)

Example:

```java
Map<Integer, String> map = new TreeMap<>();

map.put(30, "Charlie");
map.put(10, "Alice");
map.put(20, "Bob");

System.out.println(map);
```

Output:

```text
{10=Alice, 20=Bob, 30=Charlie}
```

## NavigableMap Example

```java
TreeMap<Integer, String> map = new TreeMap<>();

map.put(10, "A");
map.put(20, "B");
map.put(30, "C");

System.out.println(map.lowerKey(20));
System.out.println(map.floorKey(20));
System.out.println(map.higherKey(20));
System.out.println(map.ceilingKey(25));
```

Output:

```text
10
20
30
30
```

---

# 21. Hashtable

`Hashtable` is a legacy synchronised map.

## Characteristics

* Thread-safe through method-level synchronisation
* Does not allow `null` keys
* Does not allow `null` values
* Usually slower than `HashMap`
* Legacy class

Example:

```java
Hashtable<Integer, String> table = new Hashtable<>();

table.put(1, "Alice");
table.put(2, "Bob");

System.out.println(table);
```

## HashMap vs Hashtable

| Feature      | HashMap        | Hashtable      |
| ------------ | -------------- | -------------- |
| Synchronised | No             | Yes            |
| Null key     | One allowed    | Not allowed    |
| Null values  | Allowed        | Not allowed    |
| Performance  | Usually faster | Usually slower |
| Status       | Modern         | Legacy         |

For concurrent code, `ConcurrentHashMap` is usually preferred over `Hashtable`.

---

# 22. ConcurrentHashMap

`ConcurrentHashMap` is designed for concurrent access.

## Characteristics

* Thread-safe
* Does not lock the entire map for every operation
* Does not allow `null` keys or values
* Supports concurrent reads and updates
* Iterators are weakly consistent

Example:

```java
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map =
                new ConcurrentHashMap<>();

        map.put("Java", 1);
        map.put("Python", 2);

        map.compute("Java", (key, value) ->
                value == null ? 1 : value + 1
        );

        System.out.println(map);
    }
}
```

Output:

```text
{Java=2, Python=2}
```

## Atomic Update Example

```java
ConcurrentHashMap<String, Integer> counts =
        new ConcurrentHashMap<>();

counts.merge("Java", 1, Integer::sum);
counts.merge("Java", 1, Integer::sum);

System.out.println(counts);
```

Output:

```text
{Java=2}
```

---

# 23. Comparable and Comparator

Both are used for sorting custom objects.

## Comparable

`Comparable` defines the natural ordering of a class.

```java
public interface Comparable<T> {
    int compareTo(T object);
}
```

Example:

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

Usage:

```java
List<Student> students = new ArrayList<>();

students.add(new Student(3, "Charlie"));
students.add(new Student(1, "Alice"));
students.add(new Student(2, "Bob"));

Collections.sort(students);

System.out.println(students);
```

The students are sorted by ID.

## Comparator

`Comparator` defines external or custom ordering.

```java
Comparator<Student> comparator =
        (first, second) ->
                first.getName().compareTo(second.getName());
```

Complete example:

```java
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Employee {
    private final int id;
    private final String name;
    private final double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return id + " - " + name + " - " + salary;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(1, "Charlie", 50000));
        employees.add(new Employee(2, "Alice", 70000));
        employees.add(new Employee(3, "Bob", 60000));

        employees.sort(
                Comparator.comparing(Employee::getName)
        );

        System.out.println(employees);
    }
}
```

## Sorting by Multiple Fields

```java
employees.sort(
        Comparator.comparingDouble(Employee::getSalary)
                .reversed()
                .thenComparing(Employee::getName)
);
```

This sorts:

1. Salary in descending order
2. Name in ascending order when salaries are equal

## Comparable vs Comparator

| Comparable                | Comparator                           |
| ------------------------- | ------------------------------------ |
| Natural ordering          | Custom ordering                      |
| Implemented by the class  | Separate object or lambda            |
| Uses `compareTo()`        | Uses `compare()`                     |
| Usually one natural order | Multiple custom orders               |
| Changes class code        | Does not require changing class code |

---

# 24. Iterator vs ListIterator

## Iterator

* Works with most collections
* Moves only forward
* Can remove elements
* Cannot directly add or replace elements

## ListIterator

* Works only with lists
* Moves forward and backward
* Can add elements
* Can remove elements
* Can replace elements

Example:

```java
List<String> names =
        new ArrayList<>(List.of("Alice", "Bob", "Charlie"));

ListIterator<String> iterator = names.listIterator();

while (iterator.hasNext()) {
    String name = iterator.next();

    if (name.equals("Bob")) {
        iterator.set("Robert");
        iterator.add("Bella");
    }
}

System.out.println(names);
```

Output:

```text
[Alice, Robert, Bella, Charlie]
```

---

# 25. Fail-Fast vs Fail-Safe

## Fail-Fast Iterator

A fail-fast iterator throws `ConcurrentModificationException` when the collection is structurally modified outside the iterator during iteration.

Example:

```java
List<String> names =
        new ArrayList<>(List.of("Alice", "Bob", "Charlie"));

for (String name : names) {
    if (name.equals("Bob")) {
        names.remove(name);
    }
}
```

This may throw:

```text
ConcurrentModificationException
```

Correct approach:

```java
Iterator<String> iterator = names.iterator();

while (iterator.hasNext()) {
    if (iterator.next().equals("Bob")) {
        iterator.remove();
    }
}
```

## Weakly Consistent or Snapshot-Based Iteration

Concurrent collections often provide iterators that do not throw `ConcurrentModificationException`.

Examples:

* `ConcurrentHashMap`
* `CopyOnWriteArrayList`

`CopyOnWriteArrayList` iterates over a snapshot.

```java
CopyOnWriteArrayList<String> names =
        new CopyOnWriteArrayList<>();

names.add("Alice");
names.add("Bob");

for (String name : names) {
    names.add("Charlie");
}
```

No `ConcurrentModificationException` is thrown.

Interview note:

> “Fail-safe” is a commonly used interview term, but it is not an official iterator category in the Java API specification.

---

# 26. Immutable Collections

Modern Java provides factory methods for creating unmodifiable collections.

## Immutable List

```java
List<String> names =
        List.of("Alice", "Bob", "Charlie");
```

Attempting modification:

```java
names.add("David");
```

throws:

```text
UnsupportedOperationException
```

## Immutable Set

```java
Set<Integer> numbers = Set.of(10, 20, 30);
```

Duplicate elements are not allowed:

```java
Set.of(10, 10);
```

throws:

```text
IllegalArgumentException
```

## Immutable Map

```java
Map<Integer, String> users =
        Map.of(
                1, "Alice",
                2, "Bob"
        );
```

## `Collections.unmodifiableList()`

```java
List<String> mutable = new ArrayList<>();
mutable.add("Alice");

List<String> view =
        Collections.unmodifiableList(mutable);
```

Important distinction:

```java
mutable.add("Bob");

System.out.println(view);
```

Output:

```text
[Alice, Bob]
```

The unmodifiable collection is a read-only view of the original collection. Changes made through the original reference remain visible.

## Defensive Copy

```java
List<String> safeCopy =
        List.copyOf(mutable);
```

`List.copyOf()` creates an unmodifiable copy when necessary.

---

# 27. Collections Utility Class

The `Collections` class provides static utility methods.

## Sorting

```java
List<Integer> numbers =
        new ArrayList<>(List.of(40, 10, 30, 20));

Collections.sort(numbers);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30, 40]
```

## Reverse

```java
Collections.reverse(numbers);
```

## Shuffle

```java
Collections.shuffle(numbers);
```

## Minimum and Maximum

```java
System.out.println(Collections.min(numbers));
System.out.println(Collections.max(numbers));
```

## Frequency

```java
List<String> values =
        List.of("A", "B", "A", "C", "A");

System.out.println(Collections.frequency(values, "A"));
```

Output:

```text
3
```

## Binary Search

```java
List<Integer> numbers =
        new ArrayList<>(List.of(10, 20, 30, 40));

int index = Collections.binarySearch(numbers, 30);

System.out.println(index);
```

Output:

```text
2
```

The list must be sorted according to the same ordering used by the binary search.

## Synchronized Wrapper

```java
List<String> names =
        Collections.synchronizedList(new ArrayList<>());
```

When iterating, manual synchronisation may still be required:

```java
synchronized (names) {
    for (String name : names) {
        System.out.println(name);
    }
}
```

---

# 28. Time Complexity

## List Implementations

| Operation             |      ArrayList | LinkedList |
| --------------------- | -------------: | ---------: |
| Get by index          |           O(1) |       O(n) |
| Add at end            | O(1) amortised |       O(1) |
| Add at beginning      |           O(n) |       O(1) |
| Remove from beginning |           O(n) |       O(1) |
| Search                |           O(n) |       O(n) |

## Set Implementations

| Operation |      HashSet | LinkedHashSet |  TreeSet |
| --------- | -----------: | ------------: | -------: |
| Add       | O(1) average |  O(1) average | O(log n) |
| Remove    | O(1) average |  O(1) average | O(log n) |
| Search    | O(1) average |  O(1) average | O(log n) |
| Ordering  |         None |     Insertion |   Sorted |

## Map Implementations

| Operation |      HashMap |    LinkedHashMap |    TreeMap |
| --------- | -----------: | ---------------: | ---------: |
| Put       | O(1) average |     O(1) average |   O(log n) |
| Get       | O(1) average |     O(1) average |   O(log n) |
| Remove    | O(1) average |     O(1) average |   O(log n) |
| Ordering  |         None | Insertion/access | Key-sorted |

Complexities are typical or average-case values. Poor hash distribution can reduce hash-based collection performance.

---

# 29. Common Interview Questions

## Why does Map not extend Collection?

A `Collection` represents a group of individual elements.

A `Map` stores key-value associations, so its data model and operations are different.

---

## Why are duplicate keys not allowed in a Map?

Each key uniquely identifies one value.

Adding the same key again replaces its existing value.

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "Alice");
map.put(1, "Bob");

System.out.println(map);
```

Output:

```text
{1=Bob}
```

---

## Can a HashMap contain duplicate values?

Yes.

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "Java");
map.put(2, "Java");
```

Keys are unique, but values may be duplicated.

---

## Why should equals() and hashCode() be overridden together?

Hash-based collections first use `hashCode()` to locate a bucket and then use `equals()` to compare objects.

If equal objects produce different hash codes, retrieval and duplicate detection may fail.

Contract:

```text
If a.equals(b) is true,
a.hashCode() must equal b.hashCode().
```

The reverse is not required. Two unequal objects may have the same hash code.

---

## What happens when two keys have the same hash code?

This is called a hash collision.

Both entries may be stored in the same bucket. Java uses `equals()` to distinguish their keys.

---

## Difference between HashMap and TreeMap

| HashMap                          | TreeMap                  |
| -------------------------------- | ------------------------ |
| Hash-based                       | Tree-based               |
| No guaranteed order              | Sorted by key            |
| O(1) average operations          | O(log n) operations      |
| Allows one null key              | Normally no null key     |
| Uses `equals()` and `hashCode()` | Uses comparison ordering |

---

## Difference between HashSet and TreeSet

`HashSet` is generally faster and unordered.

`TreeSet` keeps elements sorted and supports navigation methods such as `lower()`, `floor()`, `ceiling()` and `higher()`.

---

## Why is ArrayList usually faster than LinkedList?

`ArrayList` has:

* Better cache locality
* Less per-element memory overhead
* Constant-time random access

`LinkedList` must traverse nodes and stores additional references for every element.

---

## What is the initial capacity of ArrayList?

An empty `ArrayList` does not necessarily allocate its full backing array immediately. Its internal storage grows when elements are added.

Avoid depending on undocumented implementation details in application logic.

---

## What is load factor in HashMap?

The load factor controls when the map resizes.

A common default is `0.75`.

Conceptually:

```text
resize threshold = capacity × load factor
```

A lower load factor may reduce collisions but use more memory.

---

## What is the difference between capacity and size?

* Size: Number of stored elements
* Capacity: Amount of storage available before resizing

---

## Can we use a mutable object as a HashMap key?

Technically yes, but it is dangerous.

If fields involved in `equals()` or `hashCode()` change after insertion, the key may become unreachable in the expected bucket.

Prefer immutable keys.

---

## How do you make a collection thread-safe?

Options include:

```java
Collections.synchronizedList(new ArrayList<>());
```

or concurrent collections such as:

```java
CopyOnWriteArrayList
ConcurrentHashMap
ConcurrentLinkedQueue
BlockingQueue
```

The best choice depends on read/write patterns and required guarantees.

---

## What is CopyOnWriteArrayList?

It creates a new internal array whenever a modification occurs.

Advantages:

* Safe iteration
* Efficient when reads greatly outnumber writes

Disadvantages:

* Expensive writes
* Additional memory usage
* Iterator sees a snapshot

---

## Why does ConcurrentHashMap not allow null?

In concurrent code, a returned `null` could be ambiguous:

* The key does not exist
* The key exists and maps to `null`

Disallowing `null` avoids this ambiguity.

---

## Can TreeSet store custom objects?

Yes, but objects must be comparable through either:

* `Comparable`
* A supplied `Comparator`

```java
Set<Employee> employees =
        new TreeSet<>(Comparator.comparing(Employee::getName));
```

---

## Does PriorityQueue maintain sorted iteration order?

No.

Only the head element is guaranteed to have the highest priority according to the comparator.

Use repeated `poll()` calls to retrieve elements in priority order.

---

# 30. Coding Examples

## Example 1: Remove Duplicates from a List

Preserve insertion order:

```java
List<Integer> numbers =
        List.of(10, 20, 10, 30, 20);

List<Integer> unique =
        new ArrayList<>(new LinkedHashSet<>(numbers));

System.out.println(unique);
```

Output:

```text
[10, 20, 30]
```

---

## Example 2: Count Word Frequency

```java
import java.util.HashMap;
import java.util.Map;

public class WordFrequency {
    public static void main(String[] args) {
        String text = "java is simple and java is powerful";

        Map<String, Integer> frequency = new HashMap<>();

        for (String word : text.split("\\s+")) {
            frequency.merge(word, 1, Integer::sum);
        }

        System.out.println(frequency);
    }
}
```

Possible output:

```text
{java=2, is=2, simple=1, and=1, powerful=1}
```

---

## Example 3: Find Duplicate Elements

```java
List<Integer> numbers =
        List.of(10, 20, 30, 10, 40, 20);

Set<Integer> seen = new HashSet<>();
Set<Integer> duplicates = new LinkedHashSet<>();

for (Integer number : numbers) {
    if (!seen.add(number)) {
        duplicates.add(number);
    }
}

System.out.println(duplicates);
```

Output:

```text
[10, 20]
```

---

## Example 4: First Non-Repeated Character

```java
String input = "swiss";

Map<Character, Integer> frequency =
        new LinkedHashMap<>();

for (char character : input.toCharArray()) {
    frequency.merge(character, 1, Integer::sum);
}

for (Map.Entry<Character, Integer> entry :
        frequency.entrySet()) {

    if (entry.getValue() == 1) {
        System.out.println(entry.getKey());
        break;
    }
}
```

Output:

```text
w
```

---

## Example 5: Sort a Map by Value

```java
Map<String, Integer> scores = new HashMap<>();

scores.put("Alice", 90);
scores.put("Bob", 75);
scores.put("Charlie", 85);

List<Map.Entry<String, Integer>> entries =
        new ArrayList<>(scores.entrySet());

entries.sort(Map.Entry.comparingByValue());

for (Map.Entry<String, Integer> entry : entries) {
    System.out.println(
            entry.getKey() + " = " + entry.getValue()
    );
}
```

---

## Example 6: Group Employees by Department

```java
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

record Employee(String name, String department) {
}

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("Alice", "Engineering"),
                new Employee("Bob", "Sales"),
                new Employee("Charlie", "Engineering")
        );

        Map<String, List<Employee>> grouped =
                employees.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Employee::department
                                )
                        );

        System.out.println(grouped);
    }
}
```

---

## Example 7: Find the Highest-Frequency Element

```java
List<Integer> numbers =
        List.of(10, 20, 10, 30, 10, 20);

Map<Integer, Integer> frequency = new HashMap<>();

for (Integer number : numbers) {
    frequency.merge(number, 1, Integer::sum);
}

Integer mostFrequent = null;
int highestCount = 0;

for (Map.Entry<Integer, Integer> entry :
        frequency.entrySet()) {

    if (entry.getValue() > highestCount) {
        mostFrequent = entry.getKey();
        highestCount = entry.getValue();
    }
}

System.out.println(mostFrequent);
```

Output:

```text
10
```

---

## Example 8: Implement a Basic LRU Cache

```java
import java.util.LinkedHashMap;
import java.util.Map;

class LruCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    LruCache(int capacity) {
        super(capacity, 0.75f, true);

        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "Capacity must be positive"
            );
        }

        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(
            Map.Entry<K, V> eldest
    ) {
        return size() > capacity;
    }
}

public class Main {
    public static void main(String[] args) {
        LruCache<Integer, String> cache =
                new LruCache<>(3);

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        cache.get(1);
        cache.put(4, "D");

        System.out.println(cache);
    }
}
```

Key `2` is removed because it is the least recently used entry.

---

## Example 9: Top K Largest Numbers

```java
public static List<Integer> topKLargest(
        List<Integer> numbers,
        int k
) {
    if (k < 0 || k > numbers.size()) {
        throw new IllegalArgumentException(
                "Invalid value of k"
        );
    }

    PriorityQueue<Integer> minHeap =
            new PriorityQueue<>();

    for (Integer number : numbers) {
        minHeap.offer(number);

        if (minHeap.size() > k) {
            minHeap.poll();
        }
    }

    List<Integer> result =
            new ArrayList<>(minHeap);

    result.sort(Comparator.reverseOrder());

    return result;
}
```

Usage:

```java
List<Integer> numbers =
        List.of(10, 50, 20, 80, 40, 70);

System.out.println(topKLargest(numbers, 3));
```

Output:

```text
[80, 70, 50]
```

---

## Example 10: Safely Remove Elements Using removeIf()

```java
List<Integer> numbers =
        new ArrayList<>(List.of(10, 15, 20, 25, 30));

numbers.removeIf(number -> number % 2 != 0);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30]
```

---

# 31. Quick Revision Cheat Sheet

## List

* Ordered
* Allows duplicates
* Supports index access

Implementations:

```text
ArrayList
LinkedList
Vector
Stack
```

---

## Set

* Stores unique elements
* No index-based access

Implementations:

```text
HashSet        → No guaranteed order
LinkedHashSet  → Insertion order
TreeSet        → Sorted order
```

---

## Queue

* Commonly FIFO
* Used in scheduling and processing

Implementations:

```text
LinkedList
PriorityQueue
ArrayDeque
```

---

## Deque

* Add and remove at both ends
* Can work as queue or stack

Preferred implementation:

```text
ArrayDeque
```

---

## Map

* Stores key-value pairs
* Keys are unique
* Values may be duplicated

Implementations:

```text
HashMap          → No guaranteed order
LinkedHashMap    → Insertion/access order
TreeMap          → Sorted by key
Hashtable        → Legacy synchronised map
ConcurrentHashMap → Concurrent access
```

---

## ArrayList

```text
Fast random access
Slow middle insertion and removal
Dynamic array
```

---

## LinkedList

```text
Doubly linked list
Fast end insertion and deletion
Slow random access
```

---

## HashSet

```text
Unique values
Hash-based
Uses equals() and hashCode()
```

---

## TreeSet

```text
Unique sorted values
O(log n) operations
Uses Comparable or Comparator
```

---

## HashMap

```text
Unique keys
One null key
Multiple null values
O(1) average lookup
```

---

## TreeMap

```text
Keys in sorted order
O(log n) operations
NavigableMap methods
```

---

## Comparable

```java
int compareTo(T other);
```

Used for natural ordering.

---

## Comparator

```java
int compare(T first, T second);
```

Used for custom ordering.

---

## Fail-Fast

Examples:

```text
ArrayList
HashMap
HashSet
```

May throw `ConcurrentModificationException`.

---

## Concurrent Collections

Examples:

```text
ConcurrentHashMap
CopyOnWriteArrayList
ConcurrentLinkedQueue
```

Designed for concurrent access.

---

## Important Interview Rules

1. Override `equals()` and `hashCode()` together.
2. Avoid mutable keys in a `HashMap`.
3. Prefer `ArrayDeque` over `Stack`.
4. Prefer `ConcurrentHashMap` over `Hashtable`.
5. Use `ArrayList` by default for general list requirements.
6. Use `LinkedHashSet` to remove duplicates while preserving order.
7. Use `TreeSet` or `TreeMap` when sorted results are required.
8. Use `entrySet()` when iterating over both map keys and values.
9. Do not structurally modify a collection during iteration unless using the iterator's supported methods.
10. Choose collections based on operation complexity and ordering requirements.

---

# One-Line Summary

> Use `List` for ordered duplicates, `Set` for unique values, `Queue` for processing order, `Deque` for operations at both ends, and `Map` for key-value relationships.

---

# Final Interview Selection Guide

| Requirement                      | Recommended Collection |
| -------------------------------- | ---------------------- |
| Fast index access                | `ArrayList`            |
| Frequent operations at both ends | `ArrayDeque`           |
| Unique unordered values          | `HashSet`              |
| Unique insertion-ordered values  | `LinkedHashSet`        |
| Unique sorted values             | `TreeSet`              |
| Fast key-value lookup            | `HashMap`              |
| Insertion-ordered map            | `LinkedHashMap`        |
| Sorted keys                      | `TreeMap`              |
| Priority-based processing        | `PriorityQueue`        |
| Thread-safe high-concurrency map | `ConcurrentHashMap`    |
| Read-heavy concurrent list       | `CopyOnWriteArrayList` |

---

**End of Java Collections Notes**
