public class Customer {
    private String name, address;
    private int age, ssn;

    public Customer(String name, String address, int age, int ssn) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.ssn = ssn;
    }
    public Customer(){

    }

    // Getters and setters for customer attributes

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Overriding toString() method to provide custom string representation of Customer object
    @Override
    public String toString() {
        return "Name: " + name + ", Address: " + address + ", Age: " + age;
    }
}