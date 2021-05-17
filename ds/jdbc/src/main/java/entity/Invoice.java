package entity;

public class Invoice {
    private final int invoiceId;
    private final int customerId;
    private final String InvoiceDate;
    private final String billingAddress;
    private final String billingCity;
    private final String billingState;
    private final String billingCountry;
    private final long billingPostalCode;
    private final double total;

    public Invoice(int invoiceId, int customerId, String invoiceDate, String billingAddress, String billingCity,
                   String billingState, String billingCountry, long billingPostalCode, double total) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        InvoiceDate = invoiceDate;
        this.billingAddress = billingAddress;
        this.billingCity = billingCity;
        this.billingState = billingState;
        this.billingCountry = billingCountry;
        this.billingPostalCode = billingPostalCode;
        this.total = total;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getInvoiceDate() {
        return InvoiceDate;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public String getBillingState() {
        return billingState;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public long getBillingPostalCode() {
        return billingPostalCode;
    }

    public double getTotal() {
        return total;
    }

}
