### Bài 1
- `@Entity`: Annotation này được sử dụng để đánh dấu một class là một đối tượng **Entity**. 
Thuộc tính `name` của `@Entity` dùng để chỉ định tên của **Entity** được **Hibernate** quản lý trong ngữ cảnh của Java. 
Mặc định, nếu không chỉ định `name`, tên của **Entity** sẽ được lấy từ tên của class đó.
- `@Table`: Annotation này được sử dụng để chỉ định tên của bảng trong CSDL
cho một đối tượng Entity đã được đánh dấu bằng `@Entity`. 
Thuộc tính `name` của `@Table` dùng để chỉ định tên của bảng trong CSDL cho **Entity** đó. Nếu không khai báo sẽ sử dụng tên class làm tên bảng trong CSDL.

```java
@Entity
@Table(name = "tbl_student")
public class Student {
    // ...
}
```
- Thuộc tính name trong `@Entity` không được sử dụng nên mặc định **Entity** có tên là `Student`.
- Bảng tương ứng với **Entity** này trong CSDL có tên là `tbl_student`.

### Bài 2
Để debug câu lệnh SQL mà **Hibernate** sẽ sinh ra 
trong quá trình thực thi, sử dụng thuộc tính `hibernate.show_sql`
trong file cấu hình `application.properties`. Set giá trị cho thuộc tính là `true`
để bật tính năng này.

Ví dụ:
```properties
spring.jpa.show-sql=true

```


### Bài 3
Annotation `@Column` trong JPA được sử dụng để cấu hình các thuộc tính của cột trong một bẳng của CSDL.

- Tham số **name** trong `@Column` được sử dụng để chỉ định tên của cột nếu muốn khác vối tên thuộc tính.
Ví dụ:
```java
@Column(name = "newName")
private String name;
```

- Tham số **unique** trong `@Column` được sử dụng để chỉ định cột đó có yêu cầu dữ liệu duy nhất, không được trùng lặp hay không. 
Đặt `unique = true` thì CSDL đảm bảo rằng không có giá trùng lặp trong cột này.
Ví dụ:
```java
@Column(unique = true)
private String email;
```

- Tham số **nullable** trong `@Column` được sử dụng để buộc cột đó không được null, bằng cách đặt `nullable = false`.
Ví dụ:
```java
@Column(nullable = false)
private String name;
```

### Bài 4
2 sự kiện mà JPA có thể bắt được:

- `@PrePersist` sẽ được gọi tự động trước khi một đối tượng Entity được lưu xuống cơ sở dữ liệu (trước lệnh INSERT).

Ví dụ:
```java
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;

@Entity
public class Post {
    // ...
    private LocalDateTime created_at;

    @PrePersist
    public void prePersist() {
        created_at = LocalDateTime.now();
    }
}
```

- `@PreUpdate` sẽ được gọi tự động trước khi một đối tượng Entity được cập nhật trong CSDL (trước lệnh UPDATE.
Ví dụ:

```java
import jakarta.persistence.Entity;
import jakarta.persistence.PreUpdate;

@Entity
public class Post {
    // ...
    private LocalDateTime updated_at;

    @PreUpdate
    public void preUpdate() {
        updated_at = LocalDateTime.now();
    }
}
```

### Bài 5
Interface **JpaRepository** trong Spring Data JPA kế thừa từ hai interface chính là **PagingAndSortingRepository** và **CrudRepository**.

### Bài 6

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
```

### Bài 7

Khi sử dụng `@Id` để đánh dấu một trường là trường **Identity**, không cần phải sử dụng annotation `@Column(unique = true)` nữa.
Trường được đánh dấu là `@Id` đã được đảm bảo là duy nhất, việc sử dụng `@Column(unique = true)` được coi là dư thừa.

### Bài 8
Các method trong interface EmployeeRespository

```java
import com.example.jpatest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Tìm tất cả các Employee theo emailAddress và lastName
    List<Employee> findByEmailAddressAndLastName(String emailAddress, String lastName);

    // Tìm tất cả các Employee khác nhau theo firstName hoặc lastName
    @Query("SELECT e FROM Employee e WHERE e.firstName <> :firstName OR e.lastName <> :lastName")
    List<Employee> findDistinctByFirstNameOrLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    // Tìm tất cả các Employee theo lastName và sắp xếp thứ tự theo firstName tăng dần
    List<Employee> findByLastNameOrderByFirstNameAsc(String lastName);

    // Tìm tất cả các Employee theo firstName không phân biệt hoa thường
    List<Employee> findByFirstNameIgnoreCase(String firstName);
}
```

### Bài 9
- Sử dụng `@NamedQuery`:
    - Định nghĩa truy vấn tùy chỉnh trong entity class bằng annotation
`@NamedQuery`. Trong `@NamedQuery` cung ấp tên truy vấn `name` và truy vấn SQL `query`. Ví dụ:
      ```java
      import javax.persistence.Entity;
      import javax.persistence.Id;
      import javax.persistence.NamedQuery;
      import javax.persistence.Table;

      @Entity
      @Table(name = "employees")
      @NamedQuery(
      name = "Employee.findByLastName",
      query = "SELECT e FROM Employee e WHERE e.lastName = :lastName"
      )
      public class Employee {
          @Id
          private Long id;
          private String firstName;
          private String lastName;
      }
      ```
    - Tạo repository interface cho enity (Employee)
        ```java
        import java.util.List;
        
        public interface EmployeeRepository extends JpaRepository<Employee, Long> {
        List<Employee> findByLastName(String lastName);
        }
        ```        
    -  Sử dụng tên truy vấn để thực thi truy vấn. Ví dụ:
  
        ```java
        List<Employee> employees = employeeService.findEmployeesByLastName("Jones");
        ```   


- Sử dụng `@Query`:
    - Sử dụng annotation `@Query` trực tiếp trên repository interface để định nghĩa truy vấn tùy chỉnh (bằng Native Query hoặc JPQL). Ví dụ:
    ```java
    public interface EmployeeRepository extends JpaRepository<Employee, Long> {
        @Query("SELECT e FROM Employee e WHERE e.lastName = :lastName")
        List<Employee> findByLastName(@Param("lastName") String lastName);
    }
    ```
    - Sử dụng phương thức có tên tương ứng trong repository interface để thực thi truy vấn. Ví dụ:
    ```java
    List<Employee> employees = employeeRepository.findByLastName("Jones");
    ```
### Bài 10

Trong **EmployeeRepository** tạo method findAll có kiểu trả về là `Page<Employee>` và tham số có kiểu `Pageable`.

```java
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Sử dụng Pageable để hỗ trợ sorting và paging
    Page<Employee> findAll(Pageable pageable);
}
```

Thực thi query trên:

```java
//Tạo đối tượng Pageable sử dụng để chỉ định trang đầu tiên và số lượng trang của kết quả truy vấn.
//Sort.by("firstName").descending()) định nghĩa sắp xếp dữ liệu theo trường firstName giảm dần
Pageable pageable = PageRequest.of(0, 10, Sort.by("firstName").descending());
Page<Employee> employeePage = employeeRepository.findAll(pageable);
```

### Bài 11
Định nghĩa quan hệ **One to Many** giữa bảng **Category (One) – Product (Many)**. 
Khi một Category xoá, thì không được phép xoá Product, mà chỉ set thuộc tính Category của Product là null.  

Trong `Category.java`:
```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    private List<Product> products;


    @PreRemove
    private void preRemove() {
        products.forEach( p -> p.setCategory(null));
    }
}
```

Trong `Product.java`:
```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
```

Định nghĩa quan hệ **Many to Many** giữa bảng **Tag(Many) – Product(Many)**.

Trong `Product.java`:

```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "product_tag",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;
}
```


### Bài 12

1. Sử dụng Method Query:

   - Định nghĩa method query trong repository:
    ```java
    @Repository
    public interface UserRepository extends JpaRepository<User, Long> {
        User findUserByNameContainingIgnoreCase();
    }
    ```
   - Viết method convert User sang UserDto:
   ```java
        private UserDto convertToUserDto(User user) {
            return new UserDto(
                    user.getId(),
                    user.getName(),
                    user.getEmail()
            );
        }
    ``` 
   - Gọi method query:
   ```java
   @Test
   void testFindUser1() {
        User user = userRepository.findUserByNameContainingIgnoreCase("Jones");
        UserDto userDto = convertToUserDto(user);
        System.out.println(userDto);
    }
    ```
    


2. Sử dụng JPQL Query:
   - Định nghĩa method query trong repository:
    ```java
        @Query("SELECT NEW com.example.jpatest.dto.UserDto(u.id, u.name, u.email) FROM User u WHERE u.name = :name")
        UserDto findUserDtoByName(@Param("name") String name);
    ```
    - Gọi method:
    ```java
        @Test
        void testFindUser2() {
            UserDto userDto = userRepository.findUserDtoByName("Reba Schmeler");
            System.out.println(userDto);
        }  
    ```
3. Sử dụng Native Query:

   - Trong ``User.class``:
    ```java
    @SqlResultSetMappings(value = {
        @SqlResultSetMapping(
                name = "UserDto",
                classes = @ConstructorResult(
                        targetClass = UserDto.class,
                        columns = {
                                @ColumnResult(name = "id", type=Long.class),
                                @ColumnResult(name = "name", type=String.class),
                                @ColumnResult(name = "email", type=String.class)
                        }
                )
        )
    })
    @NamedNativeQuery(
    name = "getUserDto",
    resultSetMapping = "UserDto",
    query = """
    SELECT u.id, u.name, u.email
    FROM user u
    WHERE u.name = :name
    """)
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Entity
    @Table(name="user")
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String email;
        private String password;
    }
     ```
    - Định nghĩa method query trong repository:
   ```java
    @Query(name = "getUserDto", nativeQuery = true)
    UserDto findUserDtoNativeQuery(@Param("name") String name);
    ```
   - Gọi method: 
    ```java
    @Test
    void testFindUser3() {
        UserDto userDto = userRepository.findUserDtoNativeQuery("Reba Schmeler");
        System.out.println(userDto);
    }
    ```

4. Sử dụng Projection:

    - Tạo interface chứa các trường muốn chọn từ entity:
    ```java
    public interface UserView {
        Long getId();
        String getName();
        String getEmail();
    }
    ```
    - Tạo method trong repository:
    ```java
    UserView findUserByName(String name);
    ```
   - Gọi method:
   ```java
    @Test
    void testFindUser4() {
        UserView user = userRepository.findUserByName("Reba Schmeler");
        System.out.println(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getEmail());
    }  
   ```


### Bài 13
Sử dụng `@PrePersist` gán phương thức `generatedId` vào đối tượng `Post`.
Nếu id chưa có giá trị, nó sẽ tự động tạo một ID mới sử dụng UUID và gán cho id của đối tượng `Post`.

```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post {
    @Id
    private String id;
    private String title;

    @PrePersist
    private void generateId() {
        if (id == null) {
            // Tạo một ID ngẫu nhiên sử dụng UUID
            id = UUID.randomUUID().toString();
        }
    }
}

```