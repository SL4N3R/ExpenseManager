package com.example.expensemanager2

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import android.widget.Button
import android.content.Intent
import android.widget.ListView
import android.widget.TextView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.expensemanager2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val expenseManager = ExpenseManager.getInstance()
    private lateinit var expenseList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        expenseList = findViewById<ListView>(R.id.expense_list)
        val addButton = findViewById<Button>(R.id.add_button)

        updateListView()

        addButton.setOnClickListener {
            val intent = Intent(this, AddExpenseActivity::class.java)
            startActivity(intent)
        }

        expenseList.setOnItemClickListener { _, _, position, _ ->

            val selectedExpense = expenseManager.getExpenses()[position]
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("expense_id", selectedExpense.id)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        updateListView()
    }

    private fun updateListView() {
        val expenses = expenseManager.getExpenses().map { "${it.description} - ${it.amount}" }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, expenses)
        expenseList.adapter = adapter
    }
}




