package dmitriy.blanutsa.coursework;

import dmitriy.blanutsa.coursework.persistance.entities.Car;
import dmitriy.blanutsa.coursework.persistance.entities.Manufacturer;
import dmitriy.blanutsa.coursework.persistance.entities.Model;
import dmitriy.blanutsa.coursework.persistance.repositories.CarRepository;
import dmitriy.blanutsa.coursework.persistance.repositories.ManufacturerRepository;
import dmitriy.blanutsa.coursework.persistance.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@SpringBootApplication
public class CourseworkApplication {

	private static ManufacturerRepository manufacturerRepository;

	private static ModelRepository modelRepository;

	private static CarRepository carRepository;

	@Autowired
	public CourseworkApplication(ManufacturerRepository manufacturerRepository, ModelRepository modelRepository, CarRepository carRepository) {
		this.manufacturerRepository = manufacturerRepository;
		this.modelRepository = modelRepository;
		this.carRepository = carRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CourseworkApplication.class, args);

		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setDescription("description");
		manufacturer.setName("Nissan");

		manufacturerRepository.saveAndFlush(manufacturer);

		Model model = new Model();
		model.setDescription("description");
		model.setName("4x4");
		model.setManufacturerId(manufacturer.getId());

		modelRepository.saveAndFlush(model);

		Car car = new Car();
		car.setNumber("test-number");
		car.setModelId(model.getId());
		car.setProductYear(new Date());

		carRepository.saveAndFlush(car);
	}
}
