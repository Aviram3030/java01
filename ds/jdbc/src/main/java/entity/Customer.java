package entity;

public class Customer implements Model{
    private final int customerId;
    private final String city;
    private final String company;
    private final String address;
    private final String state;
    private final String country;
    private final String postalCode;

    public Customer(int customerId, String city, String company,
                    String address, String state, String country, String postalCode) {
        this.customerId = customerId;
        this.city = city;
        this.company = company;
        this.address = address;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;;
    }

    public int getCustomerId() {
        return customerId;
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


    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", city='" + city + '\'' +
                ", company='" + company + '\'' +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
