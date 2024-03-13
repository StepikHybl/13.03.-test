package cz.spsmb.dto;

public class PersonDTO {
    long person_id;
    String name;
    int age;

    public long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(long person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "person_id=" + person_id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
