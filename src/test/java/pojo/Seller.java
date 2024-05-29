package pojo;

public class Seller extends ResponseParent{
    private String company_name;
    private String seller_name;
    private String email;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public String getSeller_surname() {
        return seller_surname;
    }

    public void setSeller_surname(String seller_surname) {
        this.seller_surname = seller_surname;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean isIncome() {
        return income;
    }

    public void setIncome(boolean income) {
        this.income = income;
    }

    public int getNumber_of_invoices() {
        return number_of_invoices;
    }

    public void setNumber_of_invoices(int number_of_invoices) {
        this.number_of_invoices = number_of_invoices;
    }

    private String phone_number;
    private String address;
    private int seller_id;
    private String seller_surname;
    private String created;
    private boolean income;
    private int number_of_invoices;

}
