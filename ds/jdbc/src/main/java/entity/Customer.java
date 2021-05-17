package entity;

public class Customer{
    private final int customerId;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String city;
    private final String company;
    private final String address;
    private final String state;
    private final String country;
    private final String postalCode;
    private final String phone;
    private final String fax;
    private final String supportRepId;


    public Customer(int customerId, String firstName, String lastName, String email, String city, String company,
                    String address, String state, String country, String postalCode, String phone, String fax,
                    String supportRepId) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.company = company;
        this.address = address;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.phone = phone;
        this.fax = fax;
        this.supportRepId = supportRepId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getFax() {
        return fax;
    }

    public String getSupportRepId() {
        return supportRepId;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", company='" + company + '\'' +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", supportRepId='" + supportRepId + '\'' +
                '}';
    }
}
