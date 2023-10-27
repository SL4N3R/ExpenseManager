package com.example.expensemanager2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ModifyExpenseActivity : AppCompatActivity() {
    val expenseManager = ExpenseManager.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_expense)

        val idInput = findViewById<EditText>(R.id.id_input)
        val descriptionInput = findViewById<EditText>(R.id.description_input)
        val amountInput = findViewById<EditText>(R.id.amount_input)
        val dateInput = findViewById<EditText>(R.id.date_input)
        val categoryInput = findViewById<EditText>(R.id.category_input)
        val saveButton = findViewById<Button>(R.id.save_button)

        val id = intent.getIntExtra("expense_id", -1)
        val selectedExpense = expenseManager.getExpenses().find { it.id == id }

        idInput.setText(selectedExpense?.id.toString())
        descriptionInput.setText(selectedExpense?.description)
        amountInput.setText(selectedExpense?.amount.toString())
        dateInput.setText(selectedExpense?.date)
        categoryInput.setText(selectedExpense?.category)

        saveButton.setOnClickListener {
            val newDescription = descriptionInput.text.toString()
            val newAmount = amountInput.text.toString().toDouble()
            val newDate = dateInput.text.toString()
            val newCategory = categoryInput.text.toString()

            val updatedExpense = Expense(id, newDescription, newAmount, newDate, newCategory)
            expenseManager.updateExpense(updatedExpense)

            finish()
        }
    }
}