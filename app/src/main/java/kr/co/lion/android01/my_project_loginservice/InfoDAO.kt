package kr.co.lion.android01.my_project_loginservice

import android.content.Context
import android.database.Cursor

class InfoDAO {
    companion object{

        //selectOne
        fun selectOneInfo(context: Context, userId:String) : UserInfo? {
            var sql = """select idx, userId, height, weight, age, bmi, bone
                |from InfoTable
                |where userId = ?
            """.trimMargin()

            //?에 들어갈 값
            var args = arrayOf(userId)

            //쿼리 실행
            var dbHelper = DBHelper(context)
            var cursor = dbHelper.writableDatabase.rawQuery(sql, args)

            //접근
            if (cursor.moveToNext()){
                //순서값을 가져온다
                var idx1 = cursor.getColumnIndex("idx")
                var idx2 = cursor.getColumnIndex("userId")
                var idx3 = cursor.getColumnIndex("height")
                var idx4 = cursor.getColumnIndex("weight")
                var idx5 = cursor.getColumnIndex("age")
                var idx6 = cursor.getColumnIndex("bmi")
                var idx7 = cursor.getColumnIndex("bone")

                //데이터를 담아준다
                var idx = cursor.getInt(idx1)
                var userId = cursor.getString(idx2)
                var height = cursor.getInt(idx3)
                var weight = cursor.getInt(idx4)
                var age = cursor.getInt(idx5)
                var bmi = cursor.getInt(idx6)
                var bone = cursor.getInt(idx7)

                var infoList = UserInfo(idx, userId, height, age, weight, bmi, bone)

                cursor.close()
                dbHelper.close()

                return infoList
            }else{
                cursor.close()
                dbHelper.close()

                return null
            }
        }


        //selectAll
        fun selectAllInfo(context: Context) : MutableList<UserInfo>{
            var sql = """select idx, userId, height, weight, age, bmi, bone
                |from InfoTable
                |order by idx desc
            """.trimMargin()

            //쿼리 실행
            var dbHelper = DBHelper(context)
            var cursor = dbHelper.writableDatabase.rawQuery(sql, null)

            //리스트 생성
            var infoList = mutableListOf<UserInfo>()

            //접근
            while (cursor.moveToNext()){
                var idx1 = cursor.getColumnIndex("idx")
                var idx2 = cursor.getColumnIndex("userId")
                var idx3 = cursor.getColumnIndex("height")
                var idx4 = cursor.getColumnIndex("weight")
                var idx5 = cursor.getColumnIndex("age")
                var idx6 = cursor.getColumnIndex("bmi")
                var idx7 = cursor.getColumnIndex("bone")

                //데이터를 담아준다
                var idx = cursor.getInt(idx1)
                var userId = cursor.getString(idx2)
                var height = cursor.getInt(idx3)
                var weight = cursor.getInt(idx4)
                var age = cursor.getInt(idx5)
                var bmi = cursor.getInt(idx6)
                var bone = cursor.getInt(idx7)

                var userList = UserInfo(idx, userId, height, age, weight, bmi, bone)
                infoList.add(userList)
            }
            dbHelper.close()
            return infoList
        }


        //insert
        fun insertInfo(context: Context, userInfo: UserInfo){
            var sql = """insert into InfoTable
                |(userId, height, weight, age, bmi, bone)
                |values(?, ?, ?, ?, ?, ?)
            """.trimMargin()

            //?에 들어갈 값
            var args = arrayOf(userInfo.userId, userInfo.height, userInfo.weight, userInfo.age, userInfo.bmi, userInfo.bone)

            //쿼리 실행
            var dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }


        //update
        fun updateInfo(context: Context, userInfo: UserInfo){
            var sql = """update InfoTable
                |set userId = ?, height = ?, weight = ?, age = ?, bmi = ?, bone = ?
                |where idx = ?
            """.trimMargin()

            //?에 들어갈 값
            var args = arrayOf(userInfo.userId, userInfo.height, userInfo.weight,userInfo.age, userInfo.bmi, userInfo.bone, userInfo.idx)


            //쿼리 실행
            var dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }


        //delete
        fun delete(context: Context, idx:Int){
            var sql = """delete from InfoTable
                |where idx = ?
            """.trimMargin()

            //?에 들어갈 값
            var args = arrayOf(idx.toString())

            //쿼리 실행
            var dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }


        fun getUserWithAdditionalInfo(context: Context, userId: String) : UserWithAdditionalInfo? {
            var sql = """SELECT LoginTable.userId, LoginTable.userPw, LoginTable.userNumber, LoginTable.userName,
                        InfoTable.height, InfoTable.weight, InfoTable.age, InfoTable.bmi, InfoTable.bone
                 FROM LoginTable
                 LEFT JOIN InfoTable ON LoginTable.userId = InfoTable.userId
                 WHERE LoginTable.userId = ?
              """.trimMargin()

            // ?에 들어갈 값
            var args = arrayOf(userId)

            var dbHelper = DBHelper(context)
            var db = dbHelper.readableDatabase

            var cursor = db.rawQuery(sql, args)
            var userWithAdditionalInfo: UserWithAdditionalInfo? = null

            if (cursor.moveToNext()) {
                // 데이터를 가져와서 UserWithAdditionalInfo 객체로 매핑

                var idx0 = cursor.getColumnIndex("userId")
                var idx1 = cursor.getColumnIndex("userPw")
                var idx2 = cursor.getColumnIndex("userNumber")
                var idx3 = cursor.getColumnIndex("userName")
                var idx4 = cursor.getColumnIndex("height")
                var idx8 = cursor.getColumnIndex("weight")
                var idx5 = cursor.getColumnIndex("age")
                var idx6 = cursor.getColumnIndex("bmi")
                var idx7 = cursor.getColumnIndex("bone")

                var userId = cursor.getString(idx0)
                var userPw = cursor.getString(idx1)
                var userNumber = cursor.getInt(idx2)
                var userName = cursor.getString(idx3)
                var height = cursor.getInt(idx4)
                var weight = cursor.getInt(idx8)
                var age = cursor.getInt(idx5)
                var bmi = cursor.getInt(idx6)
                var bone = cursor.getInt(idx7)

                userWithAdditionalInfo = UserWithAdditionalInfo(userId, userPw, userNumber, userName, height, weight, age, bmi, bone)
            }

            cursor.close()
            dbHelper.close()

            return userWithAdditionalInfo
        }
        fun getUserWithAdditionalInfo12(context: Context, userId: String) : MutableList<UserWithAdditionalInfo> {
            var sql = """SELECT LoginTable.userId, LoginTable.userPw, LoginTable.userNumber, LoginTable.userName,
                        InfoTable.height, InfoTable.weight, InfoTable.age, InfoTable.bmi, InfoTable.bone
                 FROM LoginTable
                 LEFT JOIN InfoTable ON LoginTable.userId = InfoTable.userId
                 WHERE LoginTable.userId = ?
              """.trimMargin()

            // ?에 들어갈 값
            var args = arrayOf(userId)

            var dbHelper = DBHelper(context)
            var db = dbHelper.readableDatabase

            var cursor = db.rawQuery(sql, args)
            var userWithAdditionalInfo: UserWithAdditionalInfo? = null

            var list = mutableListOf<UserWithAdditionalInfo>()

            if (cursor.moveToNext()) {
                // 데이터를 가져와서 UserWithAdditionalInfo 객체로 매핑

                var idx0 = cursor.getColumnIndex("userId")
                var idx1 = cursor.getColumnIndex("userPw")
                var idx2 = cursor.getColumnIndex("userNumber")
                var idx3 = cursor.getColumnIndex("userName")
                var idx4 = cursor.getColumnIndex("height")
                var idx8 = cursor.getColumnIndex("weight")
                var idx5 = cursor.getColumnIndex("age")
                var idx6 = cursor.getColumnIndex("bmi")
                var idx7 = cursor.getColumnIndex("bone")

                var userId = cursor.getString(idx0)
                var userPw = cursor.getString(idx1)
                var userNumber = cursor.getInt(idx2)
                var userName = cursor.getString(idx3)
                var height = cursor.getInt(idx4)
                var weight = cursor.getInt(idx8)
                var age = cursor.getInt(idx5)
                var bmi = cursor.getInt(idx6)
                var bone = cursor.getInt(idx7)

                userWithAdditionalInfo = UserWithAdditionalInfo(userId, userPw, userNumber, userName, height, weight, age, bmi, bone)
                list.add(userWithAdditionalInfo)
            }

            cursor.close()
            dbHelper.close()

            return list
        }


    }
}