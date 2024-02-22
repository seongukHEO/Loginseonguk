package kr.co.lion.android01.my_project_loginservice

data class UserWithAdditionalInfo(
    val userId: String,
    val userPw: String,
    val userNumber: Int,
    val userName: String,
    val height: Int,
    val weight: Int,
    val age: Int,
    val bmi: Int,
    val bone: Int
)
