package java_learn.lambda;

public class Person{
    private int id;
    private String name;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public static class Builder{
        private int id;
        private String name;
        private String address;
        public Builder id(int id){
            this.id=id;
            return this;
        }
        public Builder name(String name){
            this.name=name;
            return this;
        }
        public Builder address(String address){
            this.address=address;
            return this;
        }
        public Person build(){
            return new Person(this);
        }
    }
    private Person(Builder b){
        this.id=b.id;
        this.address=b.address;
        this.name=b.name;
    }
}
