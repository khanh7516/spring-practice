package com.example.demo;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Product;
import com.example.demo.entity.Student;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StudentRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DemoJpaApplicationTests {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ProductRepository productRepository;

	@Test
	void save_student() {
		Student student = new Student(null, "Hiên", "hien@gmail.com", 27);
		studentRepository.save(student);
	}

	@Test
	void save_students() {
		Student student = new Student(null, "Bình", "binh@gmail.com", 31);
		Student student1 = new Student(null, "An", "an@gmail.com", 30);
		studentRepository.saveAll(List.of(student, student1));
	}

	@Test
	void save_all_student() {
		Faker faker = new Faker();
		for (int i = 0; i < 100; i++) {
			Student student = new Student(
					null,
					faker.name().fullName(),
					faker.internet().emailAddress(),
					faker.number().numberBetween(18, 50)
			);
			studentRepository.save(student);
		}
	}

	@Test
	void get_all_student() {
		List<Student> students = studentRepository.findAll();
		students.forEach(System.out::println);
	}

	@Test
	void get_by_id() {
		Student student = studentRepository.findById(1).orElse(null);
		System.out.println(student);
	}

	@Test
	void update_student() {
		Student student = studentRepository.findById(1).orElse(null); // name = Hiên
		student.setName("Bùi Hiên");
		studentRepository.save(student);
	}

	@Test
	void delete_by_id() {
		// Xóa theo đối tuượng
		Student student = studentRepository.findById(1).orElse(null); // name = Hiên
		studentRepository.delete(student);

		// Xoa theo id
		studentRepository.deleteById(2);
	}

	@Test
	void find_by_email() {
		Student student = studentRepository.findByEmail("hien@gmail.com").orElse(null);
		System.out.println(student);
	}

	@Test
	void save_all_employee() {
		Faker faker = new Faker();
		Random rd = new Random();
		List<String> departments = List.of("A", "B", "C", "D"); // Danh sách phòng ban
		Date from = new Date(1204095742779L); // Ngày bắt đầu
		Date to = new Date(1604095742779L); // Ngày kết thúc
		for (int i = 0; i < 30; i++) {
			Employee employee = new Employee(
					faker.name().fullName(),
					departments.get(rd.nextInt(departments.size())),
					(long) faker.number().numberBetween(1000, 5000),
					convertToLocalDate(faker.date().between(from, to))
			);
			employeeRepository.save(employee);
		}
	}
	private LocalDate convertToLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}



	@Test
	void saveAllProducts() {
		Faker faker = new Faker();
		Random rd = new Random();
		Date from = new Date(1204095742779L); // Ngày bắt đầu
		Date to = new Date(1604095742779L); // Ngày kết thúc
		for (int i = 0; i < 80000; i++) {
			Product product = new Product(
					faker.commerce().productName(),
					Double.parseDouble(faker.commerce().price()),
					faker.company().name(),
					convertToLocalDate(faker.date().between(from, to)),
					faker.number().numberBetween(1000, 5000)
			);
			productRepository.save(product);
		}
	}

	@Test
	void get_all_product() {
		List<Product> products = productRepository.findAll();
		products.forEach(System.out::println);
	}

	@Test
	public void testFindByNameOrderByCountDesc() {
		// Set up test data
		String name = "Mediocre Marble Clock";
		Pageable pageable = PageRequest.of(0, 7000);

		// Invoke the repository method
		Page<Product> resultPage = productRepository.findByNameOrderByCountDesc(name, pageable);
		resultPage.get().forEach(System.out::println);
		// Assert the result
		assertNotNull(resultPage);
		// Add any specific assertions for the expected behavior
	}

	@Test
	public void testFindByNameContainingIgnoreCase() {
		// Set up test data
		Pageable pageable = PageRequest.of(0, 10);

		// Invoke the repository method
		Page<Product> resultPage = productRepository.findByNameContainingIgnoreCase("M", pageable);

		resultPage.get().forEach(System.out::println);

		// Assert the result
		assertNotNull(resultPage);
		// Add any specific assertions for the expected behavior
	}

	@Test
	public void testFindAllByOrderByPriceDesc() {
		// Invoke the repository method
		List<Product> resultList = productRepository.findAllByOrderByPriceDesc();
		resultList.forEach(System.out::println);
		// Assert the result
		assertNotNull(resultList);
		// Add any specific assertions for the expected behavior
	}

	@Test
	public void testFindAllByOrderByNameAsc() {
		// Set up test data
		String name = "Ergonomic Paper Knife";
		Pageable pageable = PageRequest.of(0, 10);

		// Invoke the repository method
		Page<Product> resultPage = productRepository.findAllByOrderByNameAsc(name, pageable);
		resultPage.get().forEach(System.out::println);
		// Assert the result
		assertNotNull(resultPage);
		// Add any specific assertions for the expected behavior
	}

	@Test
	public void testFindAllByOrderByCountAsc() {
		// Set up test data
		Pageable pageable =  PageRequest.of(0, 10);

		// Invoke the repository method
		Page<Product> resultPage = productRepository.findAllByOrderByCountAsc(pageable);
		resultPage.get().forEach(System.out::println);
		// Assert the result
		assertNotNull(resultPage);
		// Add any specific assertions for the expected behavior
	}

	@Test
	public void testFindProductByBrandOrderByPriceAsc() {
		// Set up test data
		String brand = "Kuhlman-Bogan";
		Pageable pageable = PageRequest.of(0, 8000);

		// Invoke the repository method
		Page<Product> resultPage = productRepository.findProductByBrandOrderByPriceAsc(brand, pageable);
		resultPage.get().forEach(System.out::println);
		// Assert the result
		assertNotNull(resultPage);
		// Add any specific assertions for the expected behavior
	}

	@Test
	public void testCountAllByBrand() {
		// Set up test data
		String brand = "Stoltenberg Inc";

		// Invoke the repository method
		long count = productRepository.countAllByBrand(brand);
		System.out.println(count);
		// Assert the result
		assertNotNull(count);
		// Add any specific assertions for the expected behavior
	}

	@Test
	public void testGetTotalCountByBrand() {
		// Set up test data
		String brand = "Mosciski-Bailey";

		// Invoke the repository method
		long totalCount = productRepository.getTotalCountByBrand(brand);
		System.out.println(totalCount);
		// Assert the result
		assertNotNull(totalCount);
		// Add any specific assertions for the expected behavior
	}





}
