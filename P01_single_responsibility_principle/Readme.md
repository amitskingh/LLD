# Single Responsibility Principle (SRP)

## Definition

> **A class should have only one reason to change.**

This means a class should have **one and only one responsibility** or job.

If a class handles multiple responsibilities, a change in one responsibility may unintentionally affect the others.

---

# Easy Definition

> **One Class = One Responsibility**

or

> **A class should do one thing and do it well.**

---

# Why Do We Need SRP?

Imagine an `Invoice` class that:

* Calculates the bill
* Prints the invoice
* Saves the invoice to the database
* Sends an email

These are **four different responsibilities**.

Now suppose the company changes the invoice print format.

Should the invoice calculation logic also change?

**No.**

This is exactly the problem SRP solves.

---

# Bad Example

## Invoice Class

```java
class Invoice {

    private double amount;

    public Invoice(double amount) {
        this.amount = amount;
    }

    public double calculateTotal() {
        return amount;
    }

    public void printInvoice() {
        System.out.println("Printing Invoice...");
    }

    public void saveToDatabase() {
        System.out.println("Saving Invoice...");
    }

    public void sendEmail() {
        System.out.println("Sending Email...");
    }
}
```

---

## Problems

This class has **four responsibilities**:

* Business Logic
* Printing
* Database
* Email Notification

If tomorrow:

* Database changes
* Email service changes
* Print format changes

You'll modify the **same class**.

This violates SRP.

---

# Good Example

Separate each responsibility into its own class.

## Invoice

```java
class Invoice {

    private double amount;

    public Invoice(double amount) {
        this.amount = amount;
    }

    public double calculateTotal() {
        return amount;
    }
}
```

---

## Invoice Printer

```java
class InvoicePrinter {

    public void print(Invoice invoice) {

        System.out.println("Printing Invoice...");

    }

}
```

---

## Invoice Repository

```java
class InvoiceRepository {

    public void save(Invoice invoice) {

        System.out.println("Saving Invoice to Database");

    }

}
```

---

## Email Service

```java
class EmailService {

    public void sendInvoice(Invoice invoice) {

        System.out.println("Sending Invoice Email");

    }

}
```

---

## Client Code

```java
public class Main {

    public static void main(String[] args) {

        Invoice invoice = new Invoice(500);

        InvoicePrinter printer = new InvoicePrinter();
        InvoiceRepository repository = new InvoiceRepository();
        EmailService emailService = new EmailService();

        System.out.println(invoice.calculateTotal());

        printer.print(invoice);

        repository.save(invoice);

        emailService.sendInvoice(invoice);
    }
}
```

---

# UML Structure

```text
                +----------------+
                |    Invoice     |
                +----------------+
                | calculateTotal |
                +----------------+
                        |
        -------------------------------------
        |                  |                |
        |                  |                |
+----------------+  +----------------+  +----------------+
|InvoicePrinter  |  |InvoiceRepository| | EmailService   |
+----------------+  +----------------+  +----------------+
| print()        |  | save()         |  | sendInvoice()  |
+----------------+  +----------------+  +----------------+
```

Each class has **exactly one responsibility**.

---

# Real-World Examples

## Example 1: Student Management System

❌ Bad

```text
Student

Add Student

Calculate Grades

Print Report Card

Save to Database

Send Email
```

Everything inside one class.

---

✅ Good

```text
Student

StudentRepository

GradeCalculator

ReportCardPrinter

EmailService
```

Each class has one job.

---

## Example 2: Banking System

Instead of

```text
BankAccount

Deposit

Withdraw

Print Statement

Generate PDF

Send SMS

Store in Database
```

Create

```text
BankAccount

TransactionService

StatementPrinter

NotificationService

BankRepository
```

---

## Example 3: E-Commerce

Instead of

```text
Order

Calculate Price

Save Order

Generate Invoice

Send Email

Update Inventory
```

Use

```text
Order

OrderRepository

InvoiceService

InventoryService

NotificationService
```

---

# Benefits

* Easier to maintain
* Easier to understand
* Easier to test
* Better code reuse
* Smaller classes
* Fewer merge conflicts
* Lower risk when making changes

---

# Drawbacks

Applying SRP strictly may result in:

* More classes
* More files
* Slightly increased complexity

However, for medium and large projects, the benefits far outweigh these costs.

---

# Common Mistakes

## Mistake 1

Creating a "Utility" class that does everything.

```java
class Utils {

    print();

    save();

    sendEmail();

    calculate();

}
```

This violates SRP because the class has multiple unrelated responsibilities.

---

## Mistake 2

Making a Service class responsible for:

* Validation
* Database
* Logging
* Notification
* Business Logic

Split these into dedicated classes instead.

---

# SRP vs High Cohesion

A class following SRP usually has **high cohesion**.

High Cohesion means:

> Everything inside the class is closely related to a single purpose.

Example:

```java
InvoiceRepository

save()

delete()

find()

update()
```

All methods are related to database operations.

---

# SRP and SOLID

SRP is the **first principle** of SOLID.

It encourages writing focused classes, making it easier to apply the remaining SOLID principles.

---

# Interview Questions

## What is Single Responsibility Principle?

A class should have only one reason to change, meaning it should have only one responsibility.

---

## What does "one reason to change" mean?

If only one aspect of the system changes, only one class should need modification.

---

## How does SRP improve maintainability?

Because changes related to one responsibility remain isolated, reducing the risk of breaking unrelated functionality.

---

## Does SRP mean one method per class?

No.

A class can contain many methods, as long as they all contribute to the same responsibility.

Example:

```java
InvoiceRepository

save()

update()

delete()

find()
```

All methods serve the same responsibility: managing invoice persistence.

---

# Key Takeaways

* A class should have only one responsibility.
* One responsibility means one reason to change.
* Split unrelated responsibilities into separate classes.
* SRP leads to smaller, focused, and maintainable classes.
* High cohesion is a good indicator of SRP.

---

# 30-Second Revision

```text
Definition:
A class should have only one reason to change.

Remember:
✔ One Class = One Responsibility.
✔ Separate Business Logic, Database, Printing, and Notifications.
✔ Easier Maintenance.
✔ Easier Testing.
✔ Higher Cohesion.
✔ First principle of SOLID.

Examples:
Invoice
Student
Bank Account
Order Management
```
