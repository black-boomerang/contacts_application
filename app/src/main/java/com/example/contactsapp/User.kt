package com.example.contactsapp

import java.util.*

data class User(
    val id: Int,
    val login: String,
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val address: String = "",
    val dob: Calendar = GregorianCalendar(1970, 1, 1),
    val imageUrl: String = ""
)