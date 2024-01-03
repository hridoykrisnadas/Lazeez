package com.hridoykrisna.Lazeez.repository;

import com.hridoykrisna.Lazeez.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    List<Employee> findAllByIsActiveTrue();

    @Query(value = "from Employee where email=?1 And password=?2")
    Optional<Employee> findByEmailAndPassword(String email, String password);

    @Query(value = "from Employee where user_type='DRIVER'")
    List<Employee> findByDriver();

//    @Query(value = "select Employee.name from Employee where id=?")
//    String findNameById(int driverId);

    @Query(value = "select balance from Employee where id =?1")
    float getBalanceByEmployeeId(int id);

    //    Dashboard
    @Query(value = "SELECT COUNT(designation) FROM Employee WHERE user_type = ?1")
    int getTotalDriver(@Param("user_type") String userType);
}
