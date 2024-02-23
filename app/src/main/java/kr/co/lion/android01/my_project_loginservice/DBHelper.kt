package kr.co.lion.android01.my_project_loginservice

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Login.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var sql = """create table LoginTable
            |(idx integer primary key autoincrement,
            |userId text not null,
            |userPw text not null,
            |userName text not null,
            |userNumber integer not null)
        """.trimMargin()
        db?.execSQL(sql)

        //나머지도 여기다 만들어보자 아니 나눌 필요가 있어?
        var sql2 = """create table InfoTable
            |(idx integer primary key autoincrement,
            |userId text not null,
            |height integer not null,
            |weight integer not null,
            |age integer not null,
            |bmi integer not null,
            |bone integer not null)
        """.trimMargin()
        db?.execSQL(sql2)

        var sql3 = """create table MemoTable
            |(idx integer primary key autoincrement,
            |userId text not null,
            |dateTime text not null,
            |exerciseTime integer not null,
            |exerciseBody integer not null,
            |other text not null)
        """.trimMargin()
        db?.execSQL(sql3)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}