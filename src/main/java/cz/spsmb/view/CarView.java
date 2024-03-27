package cz.spsmb.view;

import cz.spsmb.dao.CarRepository;
import cz.spsmb.dao.PersonRepository;
import cz.spsmb.model.Car;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@RequestScoped
@Named
public class CarView {
    @Inject
    CarRepository carRepository;

    List<Car> cars;

    @PostConstruct
    public void init(){
        cars = carRepository.listAll();
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

}
