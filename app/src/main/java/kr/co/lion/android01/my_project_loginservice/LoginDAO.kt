package kr.co.lion.android01.my_project_loginservice

import android.content.Context

class LoginDAO {

    companion object {
        //selectOne
        fun selectLoginOne(context: Context, userId: String): LoginClass? {
            // 쿼리 생성
            val sql = """select idx, userId, userPw, userName, userNumber
            |from LoginTable
            |where userId = ?
        """.trimMargin()

            // ?에 들어갈 값
            val args = arrayOf(userId)

            // 쿼리 실행
            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, args)

            // 접근
            if (cursor.moveToNext()) {
                // 순서값을 가져온다
                val idx1 = cursor.getColumnIndex("idx")
                val idx2 = cursor.getColumnIndex("userId")
                val idx3 = cursor.getColumnIndex("userPw")
                val idx4 = cursor.getColumnIndex("userName")
                val idx5 = cursor.getColumnIndex("userNumber")

                // 데이터를 가져온다
                val idx = cursor.getInt(idx1)
                val userId = cursor.getString(idx2)
                val userPw = cursor.getString(idx3)
                val userName = cursor.getString(idx4)
                val userNumber = cursor.getInt(idx5)

                // 객체에 데이터를 담는다
                val loginInfo = LoginClass(idx, userId, userPw, userName, userNumber)

                // 데이터베이스를 닫아준다
                cursor.close()
                dbHelper.close()

                return loginInfo
            } else {
                // 데이터가 없는 경우
                cursor.close()
                dbHelper.close()
                return null
            }
        }


        fun selectLoginOne1(context: Context, userNumber: Int): LoginClass? {
            // 쿼리 생성
            val sql = """select idx, userId, userPw, userName, userNumber
            |from LoginTable
            |where userNumber = ?
        """.trimMargin()

            // ?에 들어갈 값
            val args = arrayOf(userNumber.toString())

            // 쿼리 실행
            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, args)

            // 접근
            if (cursor.moveToNext()) {
                // 순서값을 가져온다
                val idx1 = cursor.getColumnIndex("idx")
                val idx2 = cursor.getColumnIndex("userId")
                val idx3 = cursor.getColumnIndex("userPw")
                val idx4 = cursor.getColumnIndex("userName")
                val idx5 = cursor.getColumnIndex("userNumber")

                // 데이터를 가져온다
                val idx = cursor.getInt(idx1)
                val userId = cursor.getString(idx2)
                val userPw = cursor.getString(idx3)
                val userName = cursor.getString(idx4)
                val userNumber = cursor.getInt(idx5)

                // 객체에 데이터를 담는다
                val loginInfo = LoginClass(idx, userId, userPw, userName, userNumber)

                // 데이터베이스를 닫아준다
                cursor.close()
                dbHelper.close()

                return loginInfo
            } else {
                // 데이터가 없는 경우
                cursor.close()
                dbHelper.close()
                return null
            }
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
        fun delete(context: Context, idx:Int){
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

}