package com.example.contactsapp

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object UserRepository {
    private const val USERS_URL = "https://randomuser.me/api/?seed=contactsapp&results=50"
    private val USER_LIST = mutableListOf<User>()

    private fun parseJsonArray(jsonArray: JSONArray) {
        USER_LIST.clear()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val nameObject = jsonObject.getJSONObject("name")
            val fullName = String.format(
                "%s %s",
                nameObject.getString("first"),
                nameObject.getString("last")
            )
            val dobString = jsonObject.getJSONObject("dob").getString("date")
            val dob = dobString.split("-", "T").slice(0..2).map { it.toInt() }
            val address = jsonObject.getJSONObject("location").getJSONObject("street")
            val street = String.format(
                "%s %s",
                address.getString("number"),
                address.getString("name")
            )
            val user = User(
                i,
                jsonObject.getJSONObject("login").getString("username"),
                fullName,
                jsonObject.getString("email"),
                jsonObject.getString("phone"),
                street,
                GregorianCalendar(dob[0], dob[1], dob[2]),
                jsonObject.getJSONObject("picture").getString("large")
            )
            USER_LIST.add(user)
        }
    }

    suspend fun loadUsers(): List<User> {
        return suspendCoroutine { continuation ->
            val okHttpClient = OkHttpClient()
            val request: Request = Request.Builder().url(USERS_URL).build()
            val call = okHttpClient.newCall(request)
            call.enqueue(object : okhttp3.Callback {
                @Throws(IOException::class)
                override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                    if (response.isSuccessful) {
                        val jsonArray = JSONObject(response.body!!.string()).getJSONArray("results")
                        parseJsonArray(jsonArray)
                        continuation.resume(USER_LIST)
                    }
                }

                override fun onFailure(call: okhttp3.Call, e: IOException) {
                    continuation.resumeWithException(e)
                }
            })
        }
    }

    fun getUserList(): List<User> {
        return USER_LIST.toList()
    }

    fun getUserById(id: Int): User {
        return USER_LIST[id]
    }
}