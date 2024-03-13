package cz.spsmb.dao;

import cz.spsmb.model.Animal;
import cz.spsmb.model.Car;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CarRepository implements PanacheRepository<Car> {

    public List<Car> listByModel(String model){
        return find("model",model).list();
    }

        public List<Car> listByBrand(String brand){
            return find("brand",brand).list();
        }
        public Car listById(Long id){
            return findById(id);
        }
    }

