package com.ezops.assignments.titanic.repository;


import com.ezops.assignments.titanic.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Creating repository Layer
 * @author yao.chen
 */

@Repository
@Transactional
public interface PassengerRepo extends JpaRepository<Passenger,Long> {

    @Query("SELECT count(a.survived) FROM Passenger a WHERE a.survived = :isSurvived ")
    int getCountSurvived(@Param("isSurvived") String isSurvived);


}
