package kr.co.lion.android01.my_project_loginservice

data class LoginClass(
    var idx:Int,
    var userId:String,
    var userPw:String,
    var userName:String,
    var userNumber:Int,
)

data class UserInfo(
    var idx:Int,
    var userId: String,
    var height:Int,
    var age:Int,
    var weight:Int,
    var bmi:Int,
    var bone:Int
)

data class MemoClass(
    var idx:Int,
    var login_idx2: Int,
    var dateTime:Int,
    var exerciseTime:Int,
    var exerciseBody:Int,
    var other:String
)