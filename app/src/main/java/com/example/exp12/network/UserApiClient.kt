package com.example.exp12.network

import com.example.exp12.model.Address
import com.example.exp12.model.Company
import com.example.exp12.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object UserApiClient {

    private const val USERS_URL = "https://jsonplaceholder.typicode.com/users"

    suspend fun fetchUsers(): Result<List<User>> = withContext(Dispatchers.IO) {
        var connection: HttpURLConnection? = null
        try {
            val url = URL(USERS_URL)
            connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 10000
            connection.readTimeout = 10000

            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val jsonBuilder = StringBuilder()
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    jsonBuilder.append(line)
                }
                reader.close()

                val jsonArray = JSONArray(jsonBuilder.toString())
                val users = mutableListOf<User>()

                for (i in 0 until jsonArray.length()) {
                    val userObj = jsonArray.getJSONObject(i)
                    
                    val addressObj = userObj.optJSONObject("address") ?: JSONObject()
                    val address = Address(
                        street = addressObj.optString("street", "N/A"),
                        suite = addressObj.optString("suite", "N/A"),
                        city = addressObj.optString("city", "N/A"),
                        zipcode = addressObj.optString("zipcode", "N/A")
                    )

                    val companyObj = userObj.optJSONObject("company") ?: JSONObject()
                    val company = Company(
                        name = companyObj.optString("name", "N/A"),
                        catchPhrase = companyObj.optString("catchPhrase", "N/A"),
                        bs = companyObj.optString("bs", "N/A")
                    )

                    val user = User(
                        id = userObj.optInt("id", i + 1),
                        name = userObj.optString("name", "Unknown"),
                        username = userObj.optString("username", "Unknown"),
                        email = userObj.optString("email", "N/A"),
                        phone = userObj.optString("phone", "N/A"),
                        website = userObj.optString("website", "N/A"),
                        address = address,
                        company = company
                    )
                    users.add(user)
                }

                Result.success(users)
            } else {
                Result.failure(Exception("HTTP Error: Response code $responseCode"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        } finally {
            connection?.disconnect()
        }
    }
}
