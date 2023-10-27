package com.example.expensemanager2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddExpenseActivity : AppCompatActivity() {
    val expenseManager = ExpenseManager.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        val descriptionInput = findViewById<EditText>(R.id.description_input)
        val amountInput = findViewById<EditText>(R.id.amount_input)
        val dateInput = findViewById<EditText>(R.id.date_input)
        val categoryInput = findViewById<EditText>(R.id.category_input)
        val addButton = findViewById<Button>(R.id.add_button)

        addButton.setOnClickListener {
            val id = expenseManager.getExpenses().size + 1
            val description = descriptionInput.text.toString()
            val amount = amountInput.text.toString().toDouble()
            val date = dateInput.text.toString()
            val category = categoryInput.text.toString()

            val newExpense = Expense(id, description, amount, date, category)
            expenseManager.addExpense(newExpense)

            finish()
        }
    }
}