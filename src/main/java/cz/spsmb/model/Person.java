package cz.spsmb.model;

import jakarta.inject.Inject;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Person")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long person_id;
    String name;
    int age;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id",referencedColumnName = "car_id")
    Car car;

    public Person(){
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return person_id;
    }

    public void setId(long id) {
        this.person_id = id;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + person_id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
