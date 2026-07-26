package P01_single_responsibility_principle;

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
