package com.admin.sbsbackend.repos;

import com.admin.sbsbackend.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ExpenseRepo extends JpaRepository<Expense, UUID>{
    List<Expense> findExpensesByBikeRepairId(UUID bikeRepairId);

    @Query(value = "SELECT * FROM expense WHERE created_date = DATE(?1)", nativeQuery = true)
    List<Expense> findAllCreatedToday(Date date);
}
