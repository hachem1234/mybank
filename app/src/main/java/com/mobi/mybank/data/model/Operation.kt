package com.mobi.mybank.data.model

data class Operation(
    val amount: String,
    val category: String,
    val date: String,
    val id: String,
    val title: String
)