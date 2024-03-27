package cz.spsmb.view;

import cz.spsmb.dao.CarRepository;
import cz.spsmb.dto.CarDTO;
import cz.spsmb.dto.PersonDTO;
import cz.spsmb.model.Car;
import cz.spsmb.model.Person;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import cz.spsmb.dao.PersonRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class TestView {

    @Inject
    PersonRepository personRepository;

    @Inject
    CarRepository carRepository;

    List<Person> personList;
    List<Car> carList;
    String name;
    int age;

    PersonDTO newPerson;
    CarDTO newCar;

    String selectedPersonCar;
    String selectedCar;


    @PostConstruct
    public void init() {

        Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        String personName = params.get("name");

        System.out.println(personName);

        personList = personRepository.listAll();
        System.out.println(personList);

        personList = personRepository.listAll();

        newPerson = new PersonDTO();
        newCar = new CarDTO();

    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public CarRepository getCarRepository() {
        return carRepository;
    }

    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public String getSelectedPersonCar() {
        return selectedPersonCar;
    }

    public void setSelectedPersonCar(String selectedPersonCar) {
        this.selectedPersonCar = selectedPersonCar;
    }

    public CarDTO getNewCar() {
        return newCar;
    }

    public void setNewCar(CarDTO newCar) {
        this.newCar = newCar;
    }

    public PersonRepository getPersonRepository() {
        return personRepository;
    }

    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonDTO getNewPerson() {
        return newPerson;
    }

    public void setNewPerson(PersonDTO newPerson) {
        this.newPerson = newPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public List<String> getPersonNames() {
        return personList.stream().map(person -> person.getName()).collect(Collectors.toList());
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Transactional
    public void savePerson(){

        System.out.println("SavePerson");
        if(validateInputPerson(newPerson)){
            Person person = new Person();
            person.setName(newPerson.getName());
            person.setAge(newPerson.getAge());
            Optional<Car> carOptinal = carRepository.listByName(newPerson.getCarName());
            if(carOptinal.isPresent()) {
                person.setCar(carOptinal.get());
            }
            else{
                Car car = new Car();
                car.setModel(newPerson.getCarName());
                car.setPerson(person);
                carRepository.persist(car);
            }

            personRepository.persist(person);
            personList = personRepository.listAll();
        }
    }

    private boolean validateInputPerson(PersonDTO personDTO){
        return !(personDTO.getName().isEmpty() || personDTO.getAge() == 0 || personDTO.getCarName().isEmpty());
    }

    @Transactional
    public void saveCar(){
        System.out.println("SaveCaR");
        if(validateInputCar(newCar)){
            Car car = new Car();
            car.setBrand(newCar.getBrand());
            car.setModel(newCar.getModel());

            carRepository.persist(car);
            carList = carRepository.listAll();
        }
    }

    private boolean validateInputCar(CarDTO carDTO){
        return !(carDTO.getBrand().isEmpty() || carDTO.getModel().isEmpty());
    }
}
