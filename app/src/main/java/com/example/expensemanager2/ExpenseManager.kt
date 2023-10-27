package com.example.expensemanager2

data class Expense(
    var id: Int,
    var description: String,
    var amount: Double,
    var date: String,
    var category: String
)
class ExpenseManager private constructor() {
    private val expenses = mutableListOf<Expense>()

    fun addExpense(expense: Expense) {
        expenses.add(expense)
    }

    fun getExpenses(): List<Expense> {
        return expenses
    }

    fun updateExpense(expense: Expense) {
        val index = expenses.indexOfFirst { it.id == expense.id }
        if (index != -1) {
            expenses[index] = expense
        }
    }

    fun deleteExpense(id: Int) {
        expenses.removeAll { it.id == id }
    }

    companion object {
        @Volatile
        private var INSTANCE: ExpenseManager? = null

        fun getInstance(): ExpenseManager {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = ExpenseManager()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}