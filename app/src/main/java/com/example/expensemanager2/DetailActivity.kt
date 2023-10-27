package com.example.expensemanager2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity

class DetailActivity : AppCompatActivity() {
    val expenseManager = ExpenseManager.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getIntExtra("expense_id", -1)
        val selectedExpense = expenseManager.getExpenses().find { it.id == id }

        val descriptionText = findViewById<TextView>(R.id.description_text)
        val amountText = findViewById<TextView>(R.id.amount_text)
        val dateText = findViewById<TextView>(R.id.date_text)
        val categoryText = findViewById<TextView>(R.id.category_text)

        descriptionText.text = selectedExpense?.description
        amountText.text = selectedExpense?.amount.toString()
        dateText.text = selectedExpense?.date
        categoryText.text = selectedExpense?.category

        val modifyButton = findViewById<Button>(R.id.modify_button)
        modifyButton.setOnClickListener {
            val intent = Intent(this, ModifyExpenseActivity::class.java)
            intent.putExtra("expense_id", id)
            startActivity(intent)
            finish()
        }

        val deleteButton = findViewById<Button>(R.id.delete_button)
        deleteButton.setOnClickListener {
            expenseManager.deleteExpense(id)
            finish()
        }
    }
}