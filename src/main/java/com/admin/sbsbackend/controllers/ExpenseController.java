package com.admin.sbsbackend.controllers;

import com.admin.sbsbackend.controllers.utils.AdminUserControllerUtils;
import com.admin.sbsbackend.controllers.utils.JwtUtil;
import com.admin.sbsbackend.models.Expense;
import com.admin.sbsbackend.services.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final AdminUserControllerUtils adminUserControllerUtils;

    public ExpenseController(ExpenseService expenseService, AdminUserControllerUtils adminUserControllerUtils) {
        this.expenseService = expenseService;
        this.adminUserControllerUtils = adminUserControllerUtils;
    }


    @GetMapping("/{bikeRepairId}")
    public ResponseEntity<List<Expense>> getAllExpensesForBikeRepair(@PathVariable String bikeRepairId, @RequestHeader("token") String token){
        adminUserControllerUtils.validateUser(token);
        return ResponseEntity.ok(this.expenseService.getAllExpensesByBikeId(bikeRepairId));
    }

    @PostMapping("/add")
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense){
        System.out.println(expense);
        return ResponseEntity.ok(this.expenseService.addExpense(expense));
    }

    @GetMapping("/today")
    public ResponseEntity<Double> getAllExpensesForToday(){
        return ResponseEntity.ok(this.expenseService.getAllExpensesForToday());
    }



}
