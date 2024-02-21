package kr.co.lion.android01.my_project_loginservice

import android.content.Context

class LoginDAO {

    //selectOne
    fun selectLoginOne(context: Context, idx:Int) : LoginClass{
        //쿼리 생성
        var sql = """select idx, userId, userPw, userName, userNumber
            |from LoginTable
            |where idx = ?
        """.trimMargin()

        //?에 들어갈 값
        var args = arrayOf(idx.toString())

        //쿼리 실핼
        var dbHelper = DBHelper(context)
        var cursor = dbHelper.writableDatabase.rawQuery(sql, args)

        //접근
        cursor.moveToNext()

        //순서값을 가져온다
        var idx1 = cursor.getColumnIndex("idx")
        var idx2 = cursor.getColumnIndex("userId")
        var idx3 = cursor.getColumnIndex("userPw")
        var idx4 = cursor.getColumnIndex("userName")
        var idx5 = cursor.getColumnIndex("userNumber")

        //데이터를 가져온다
        var idx = cursor.getInt(idx1)
        var userId = cursor.getString(idx2)
        var userPw = cursor.getString(idx3)
        var userName = cursor.getString(idx4)
        var userNumber = cursor.getInt(idx5)

        //객체에 데이터를 담는다
        var loginInfo = LoginClass(idx, userId, userPw, userName, userNumber)
        //데이터 베이스를 닫아준다
        dbHelper.close()
        return loginInfo
    }


    //select All
    fun selectLoginAll(context: Context) : MutableList<LoginClass>{
        var sql = """select idx, userId, userPw, userName, UserNumber
            |from LoginTable
            |order by idx desc
        """.trimMargin()

        //쿼리 실행
        var dbHelper = DBHelper(context)
        var cursor = dbHelper.writableDatabase.rawQuery(sql, null)

        //담을 리스트
        var loginList = mutableListOf<LoginClass>()

        //접근
        while (cursor.moveToNext()){
            //순서값을 가져온다
            var idx1 = cursor.getColumnIndex("idx")
            var idx2 = cursor.getColumnIndex("userId")
            var idx3 = cursor.getColumnIndex("userPw")
            var idx4 = cursor.getColumnIndex("userName")
            var idx5 = cursor.getColumnIndex("userNumber")

            //데이터를 가져온다
            var idx = cursor.getInt(idx1)
            var userId = cursor.getString(idx2)
            var userPw = cursor.getString(idx3)
            var userName = cursor.getString(idx4)
            var userNumber = cursor.getInt(idx5)

            var loginModel = LoginClass(idx, userId, userPw, userName, userNumber)
            loginList.add(loginModel)
        }
        dbHelper.close()
        return loginList
    }


    //insert
    fun insertLogin(context: Context, loginClass: LoginClass){
        //쿼리 생성
        var sql = """insert into LoginTable
            |(userId, userPw, userName, userNumber)
            |values(?, ?, ?, ?)
        """.trimMargin()

        //?에 들어갈 값
        var args = arrayOf(loginClass.userId, loginClass.userPw, loginClass.userName, loginClass.userNumber)

        //쿼리 실행
        var dbHelper = DBHelper(context)
        dbHelper.writableDatabase.execSQL(sql, args)
        //닫아준다
        dbHelper.close()
    }


     //update
    fun updateLogin(context: Context, loginClass: LoginClass){
        var sql = """update LoginTable
            |set userId = ?, userPw = ?, userName = ?, userNumber = ?
            |where idx = ?
        """.trimMargin()

         //?에 들어갈 값
         var args = arrayOf(loginClass.userId, loginClass.userPw, loginClass.userName, loginClass.userNumber, loginClass.idx)

         //쿼리 실행
         var dbHelper = DBHelper(context)
         dbHelper.writableDatabase.execSQL(sql, args)
         //닫아준다
         dbHelper.close()
    }


    //delete
    fun delete(context: Context, idx: Int){
        //쿼리 생성
        var sql = """delete from LoginTable
            |where idx = ?
        """.trimMargin()
        //?에 들어갈 값
        var args = arrayOf(idx.toString())
        //쿼리 실행
        var dbHelper = DBHelper(context)
        dbHelper.writableDatabase.execSQL(sql, args)
        //닫아준다
        dbHelper.close()
    }
}