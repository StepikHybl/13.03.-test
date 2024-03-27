package cz.spsmb.dao;

import cz.spsmb.model.Animal;
import cz.spsmb.model.Car;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class CarRepository implements PanacheRepository<Car> {

    public List<Car> listByModel(String model){
        return find("model",model).list();
    }

        public Optional<Car> listByName(String brand){
            return find("brand",brand).singleResultOptional();
        }
        public Car listById(Long id){
            return findById(id);
        }
}

