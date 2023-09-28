package com.example.jpatest;

import com.example.jpatest.dto.UserDto;
import com.example.jpatest.entity.*;
import com.example.jpatest.projection.UserView;
import com.example.jpatest.repository.*;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class JpaTestApplicationTests {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private TagRepository tagRepository;

	@Test
	void generateEmps() {
		Faker faker = new Faker();

		for (int i = 0; i < 30; i++) {
			Employee employee = new Employee();
			employee.setEmailAddress(faker.internet().emailAddress());
			employee.setFirstName(faker.name().firstName());
			employee.setLastName(faker.name().lastName());
			employeeRepository.save(employee);
		}
	}

	@Test
	void findAllEmp() {
		List<Employee> employees = employeeRepository.findAll();
		employees.forEach(System.out::println);
	}

	@Test
	void testMethodEmp() {

		List<Employee> employees1 = employeeRepository.findByEmailAddressAndLastName("alberto.sanford@yahoo.com", "Connelly");
		employees1.forEach(System.out::println);
		List<Employee> employees2 = employeeRepository.findDistinctByFirstNameOrLastName("Roy", "Purdy");
		employees2.forEach(System.out::println);
		List<Employee> employees3 = employeeRepository.findByLastNameOrderByFirstNameAsc("Jones");
		employees3.forEach(System.out::println);
		List<Employee> employees4 = employeeRepository.findByFirstNameIgnoreCase("michEL");
		employees4.forEach(System.out::println);

	}

	@Test
	void testPagingAndSorting() {
		//Tạo đối tượng Pageable sử dụng để chỉ định trang đầu tiên và số lượng trang của kết quả truy vấn.
		//Sort.by("firstName").descending()) định nghĩa sắp xếp dữ liệu theo trường firstName giảm dần
		Pageable pageable = PageRequest.of(0, 10, Sort.by("firstName").descending());

		Page<Employee> employeePage = employeeRepository.findAll(pageable);

		// Kiểm tra kết quả
		List<Employee> employees = employeePage.getContent(); // Lấy danh sách Employee trên trang đầu tiên
		assertEquals(10, employees.size()); // Kiểm tra số lượng Employee trên trang đầu tiên

		// Kiểm tra sắp xếp giảm dần theo firstName
		for (int i = 0; i < employees.size() - 1; i++) {
			String currentFirstName = employees.get(i).getFirstName();
			String nextFirstName = employees.get(i + 1).getFirstName();
			assertTrue(currentFirstName.compareTo(nextFirstName) >= 0);
		}
	}

	@Test
	void generateUser() {
		Faker faker = new Faker();
		for (int i = 0; i < 30; i++) {
			User user = new User();
			user.setName(faker.name().fullName());
			user.setEmail(faker.internet().emailAddress());
			user.setPassword(faker.internet().password());

			userRepository.save(user);
		}
	}
	@Test
	void getAllUser() {
		userRepository.findAll().forEach(System.out::println);
	}

	@Test
	void testFindUser1() {
		User user = userRepository.findUserByNameContainingIgnoreCase("Reba Schmeler");
		UserDto userDto = convertToUserDto(user);
		System.out.println(userDto);
	}

	@Test
	void testFindUser2() {
		UserDto userDto = userRepository.findUserDtoByName("Reba Schmeler");
		System.out.println(userDto);
	}

	@Test
	void testFindUser3() {
		UserDto userDto = userRepository.findUserDtoNativeQuery("Reba Schmeler");
		System.out.println(userDto);
	}

	@Test
	void testFindUser4() {
		UserView user = userRepository.findUserByName("Reba Schmeler");
		System.out.println(user.getId());
		System.out.println(user.getName());
		System.out.println(user.getEmail());
	}

	private UserDto convertToUserDto(User user) {
		return new UserDto(
				user.getId(),
				user.getName(),
				user.getEmail()
		);
	}

	@Test
	void generateTestData() {
		Faker faker = new Faker();

		// Tạo danh sách danh mục (Category)
		List<Category> categories = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			Category category = new Category();
			category.setName(faker.food().ingredient());
			categories.add(category);
			categoryRepository.save(category);
		}

		// Tạo danh sách thẻ (Tag)
		List<Tag> tags = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			Tag tag = new Tag();
			tag.setName(faker.lorem().word());
			tags.add(tag);
			tagRepository.save(tag);
		}

		// Tạo danh sách sản phẩm (Product) và liên kết với danh mục và thẻ
		for (int i = 0; i < 30; i++) {
			Product product = new Product();
			product.setName(faker.food().ingredient());

			// Chọn một danh mục ngẫu nhiên
			Category randomCategory = categories.get(faker.random().nextInt(categories.size()));
			product.setCategory(randomCategory);

			// Chọn một số lượng thẻ ngẫu nhiên
			List<Tag> randomTags = new ArrayList<>();
			int numTags = faker.random().nextInt(1, 5); // Số lượng thẻ ngẫu nhiên từ 1 đến 5
			for (int j = 0; j < numTags; j++) {
				Tag randomTag = tags.get(faker.random().nextInt(tags.size()));
				randomTags.add(randomTag);
			}
			product.setTags(randomTags);

			productRepository.save(product);
		}
	}

	@Test
	void deleteCate() {
		categoryRepository.deleteById(1L);
	}
}
