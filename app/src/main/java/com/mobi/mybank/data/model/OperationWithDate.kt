package com.mobi.mybank.data.model

data class OperationWithDate(
    val amount: String,
    val category: String,
    val date: java.util.Date?,
    val id: String,
    val title: String,
    val newDate:String
)