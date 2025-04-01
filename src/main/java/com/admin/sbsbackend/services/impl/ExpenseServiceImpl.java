package com.admin.sbsbackend.services.impl;

import com.admin.sbsbackend.models.Expense;
import com.admin.sbsbackend.repos.ExpenseRepo;
import com.admin.sbsbackend.services.ExpenseService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepo expenseRepo;

    public ExpenseServiceImpl(ExpenseRepo expenseRepo) {
        this.expenseRepo = expenseRepo;
    }

    @Override
    public Expense addExpense(Expense expense) {
        expense.setCreatedAt(new Date());

        expense.setCreatedDate(LocalDate.now());
        return this.expenseRepo.save(expense);
    }

    @Override
    public List<Expense> getAllExpensesByBikeId(String bikeRepairId) {
        List<Expense> expenses = this.expenseRepo.findExpensesByBikeRepairId(UUID.fromString(bikeRepairId));
        return expenses;
    }

    @Override
    public Double getAllExpensesForToday() {
        Date date = new Date();
        List<Expense> expenses = this.expenseRepo.findAllCreatedToday(date);
        return expenses.stream().mapToDouble(Expense::getExpenseAmount).sum();
    }
}
