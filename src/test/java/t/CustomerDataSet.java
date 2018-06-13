package t;

public class CustomerDataSet {
    private final String customer1;
    private final String customer2;
    private final String customer3;
    private final String address1;
    private final String address2;
    private final String address3;
    private final String address4;
    private final String addressNumber1;
    private final String address3Number2;
    private final String customer1Id;

    public CustomerDataSet(String customer1, String customer2, String customer3, String address1, String address2, String address3, String address4, String addressNumber1, String address3Number2, String customer1Id) {
        this.customer1 = customer1;
        this.customer2 = customer2;
        this.customer3 = customer3;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.address4 = address4;
        this.addressNumber1 = addressNumber1;
        this.address3Number2 = address3Number2;
		this.customer1Id = customer1Id;
    }

    public String getCustomer1() {
        return customer1;
    }

    public String getCustomer2() {
        return customer2;
    }

    public String getCustomer3() {
        return customer3;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getAddress3() {
        return address3;
    }

    public String getAddress4() {
        return address4;
    }

    public String getAddressNumber1() {
        return addressNumber1;
    }

    public String getAddress3Number2() {
        return address3Number2;
    }

	public String getCustomer2Id() {
		return customer1Id;
	}
}
