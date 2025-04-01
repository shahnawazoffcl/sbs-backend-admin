package com.admin.sbsbackend.services;

import com.admin.sbsbackend.models.Expense;

import java.util.List;

public interface ExpenseService {
    Expense addExpense(Expense expense);

    List<Expense> getAllExpensesByBikeId(String bikeRepairId);

    Double getAllExpensesForToday();
}
