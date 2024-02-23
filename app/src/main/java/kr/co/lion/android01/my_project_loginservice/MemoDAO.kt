package kr.co.lion.android01.my_project_loginservice

import android.content.Context

class MemoDAO {
    companion object{

        //selectOne
        fun selectOneMemo(context: Context, userId:String) : MemoClass? {
            //쿼리 생성
            var sql = """select *
                |from MemoTable
                |where userId = ?
            """.trimMargin()

            //?에 들어갈 값
            var args = arrayOf(userId)

            //쿼리 실행
            var dbHelper = DBHelper(context)
            var cursor = dbHelper.writableDatabase.rawQuery(sql, args)

            if (cursor.moveToNext()){
                //순서값을 가져온다
                var idx1 = cursor.getColumnIndex("idx")
                var idx2 = cursor.getColumnIndex("userId")
                var idx3 = cursor.getColumnIndex("dateTime")
                var idx4 = cursor.getColumnIndex("exerciseTime")
                var idx5 = cursor.getColumnIndex("exerciseBody")
                var idx6 = cursor.getColumnIndex("other")


                //데이터를 담아준다
                var idx = cursor.getInt(idx1)
                var userId = cursor.getString(idx2)
                var dateTime = cursor.getString(idx3)
                var exerciseTime = cursor.getInt(idx4)
                var exerciseBody = cursor.getInt(idx5)
                var other = cursor.getString(idx6)

                var memoList = MemoClass(idx, userId, dateTime, exerciseTime, exerciseBody, other)
                dbHelper.close()
                cursor.close()
                return memoList
            }else{
                dbHelper.close()
                cursor.close()
                return null
            }
        }


        //selectAll
        fun selectAllMemo(context: Context) : MutableList<MemoClass>{
            var sql = """select *
                |from MemoTable
                |order by userId desc
            """.trimMargin()

            //쿼리 실행
            var dbHelper = DBHelper(context)
            var cursor = dbHelper.writableDatabase.rawQuery(sql, null)

            //리스트 생성
            var memoList = mutableListOf<MemoClass>()

            while (cursor.moveToNext()){
                //순서값을 가져온다
                var idx1 = cursor.getColumnIndex("idx")
                var idx2 = cursor.getColumnIndex("userId")
                var idx3 = cursor.getColumnIndex("dateTime")
                var idx4 = cursor.getColumnIndex("exerciseTime")
                var idx5 = cursor.getColumnIndex("exerciseBody")
                var idx6 = cursor.getColumnIndex("other")


                //데이터를 담아준다
                var idx = cursor.getInt(idx1)
                var userId = cursor.getString(idx2)
                var dateTime = cursor.getString(idx3)
                var exerciseTime = cursor.getInt(idx4)
                var exerciseBody = cursor.getInt(idx5)
                var other = cursor.getString(idx6)

                var memoModel = MemoClass(idx, userId, dateTime, exerciseTime, exerciseBody, other)
                memoList.add(memoModel)
            }
            dbHelper.close()
            cursor.close()
            return memoList

        }


        //insert
        fun insertMemo(context: Context, memoClass: MemoClass){
            var sql = """insert into MemoTable
                |(userId, dateTime, exerciseTime, exerciseBody, other)
                |values (?, ?, ?, ?, ?)
            """.trimMargin()

            //?에 들어갈 값
            var args = arrayOf(memoClass.userId, memoClass.dateTime, memoClass.exerciseTime, memoClass.exerciseBody , memoClass.other)

            //쿼리 실행
            var dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }


        //update
        fun updateMemo(context: Context, memoClass: MemoClass){
            var sql = """update MemoTable
                |set userId = ?, dateTime = ?, exerciseTime = ?, exerciseBody = ?, other = ?
                |where idx = ?
            """.trimMargin()

            //?에 들어갈 값
            var args = arrayOf(memoClass.userId, memoClass.dateTime, memoClass.exerciseTime, memoClass.exerciseBody , memoClass.other, memoClass.idx)

            //쿼리 실행
            var dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }


        //delete
        fun deleteMemo(context: Context, userId: String){
            var sql = """delete from MemoTable
                |where userId = ?
            """.trimMargin()

            //?에 들어갈 값
            var args = arrayOf(userId)

            //쿼리 실행
            var dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }

        //Login과 Memo를 join한다
        fun joinLoginMemo(context: Context, userId: String) : UserMemoInfoClass? {
            var sql = """select LoginTable.userId, LoginTable.userPw, LoginTable.userNumber, LoginTable.userName, MemoTable.dateTime, MemoTable.exerciseTime, MemoTable.exerciseBody, MemoTable.other
                from LoginTable
                left join MemoTable on LoginTable.userId = MemoTable.userId
                where LoginTable.userId = ?
            """.trimMargin()

            //?에 들어갈 값
            var args = arrayOf(userId)

            //쿼리 실행
            var dbHelper = DBHelper(context)
            var db = dbHelper.readableDatabase

            var cursor = db.rawQuery(sql, args)
            var userMemoInfoClass:UserMemoInfoClass? = null

            if (cursor.moveToNext()){
                var idx0 = cursor.getColumnIndex("userId")
                var idx1 = cursor.getColumnIndex("userPw")
                var idx2 = cursor.getColumnIndex("userNumber")
                var idx3 = cursor.getColumnIndex("userName")
                var idx4 = cursor.getColumnIndex("dateTime")
                var idx5 = cursor.getColumnIndex("exerciseTime")
                var idx6 = cursor.getColumnIndex("exerciseBody")
                var idx7 = cursor.getColumnIndex("other")


                var userId = cursor.getString(idx0)
                var userPw = cursor.getString(idx1)
                var userNumber = cursor.getInt(idx2)
                var userName = cursor.getString(idx3)
                var dateTime = cursor.getString(idx4)
                var exerciseTime = cursor.getInt(idx5)
                var exerciseBody = cursor.getInt(idx6)
                var other = cursor.getString(idx7)


                userMemoInfoClass = UserMemoInfoClass(userId, userPw, userNumber, userName, dateTime, exerciseTime, exerciseBody, other)
            }
            dbHelper.close()
            cursor.close()
            return userMemoInfoClass

        }
        fun joinLoginMemo12(context: Context, userId: String) : MutableList<UserMemoInfoClass> {
            var sql = """select LoginTable.userId, LoginTable.userPw, LoginTable.userNumber, LoginTable.userName, MemoTable.dateTime, MemoTable.exerciseTime, MemoTable.exerciseBody, MemoTable.other
                from LoginTable
                left join MemoTable on LoginTable.userId = MemoTable.userId
                where LoginTable.userId = ?
            """.trimMargin()

            //?에 들어갈 값
            var args = arrayOf(userId)

            //쿼리 실행
            var dbHelper = DBHelper(context)
            var db = dbHelper.readableDatabase

            var cursor = db.rawQuery(sql, args)
            var userMemoInfoClass:UserMemoInfoClass? = null

            var memoList = mutableListOf<UserMemoInfoClass>()

            if (cursor.moveToNext()){
                var idx0 = cursor.getColumnIndex("userId")
                var idx1 = cursor.getColumnIndex("userPw")
                var idx2 = cursor.getColumnIndex("userNumber")
                var idx3 = cursor.getColumnIndex("userName")
                var idx4 = cursor.getColumnIndex("dateTime")
                var idx5 = cursor.getColumnIndex("exerciseTime")
                var idx6 = cursor.getColumnIndex("exerciseBody")
                var idx7 = cursor.getColumnIndex("other")


                var userId = cursor.getString(idx0)
                var userPw = cursor.getString(idx1)
                var userNumber = cursor.getInt(idx2)
                var userName = cursor.getString(idx3)
                var dateTime = cursor.getString(idx4)
                var exerciseTime = cursor.getInt(idx5)
                var exerciseBody = cursor.getInt(idx6)
                var other = cursor.getString(idx7)


                userMemoInfoClass = UserMemoInfoClass(userId, userPw, userNumber, userName, dateTime, exerciseTime, exerciseBody, other)
                memoList.add(userMemoInfoClass)
            }
            dbHelper.close()
            cursor.close()

            return memoList

        }





    }

}











































