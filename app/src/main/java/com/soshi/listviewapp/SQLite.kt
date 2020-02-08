package com.soshi.listviewapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLite(context: Context) : SQLiteOpenHelper(context, "LIST_DB", null,
    1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE USER_TABLE (" +
                "unique_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id INTEGER, " +
                "name TEXT, " +
                "`group` INTEGER)")
        //初期データ挿入
        db.execSQL("insert into USER_TABLE(id, name, `group`) VALUES(1, 'Shiro', 1)")
        db.execSQL("insert into USER_TABLE(id, name, `group`) VALUES(2, 'Lina', 2)")
        db.execSQL("insert into USER_TABLE(id, name, `group`) VALUES(null, null, null)")
        db.execSQL("insert into USER_TABLE(id, name, `group`) VALUES(3, 'Wakaba', 1)")
        db.execSQL("insert into USER_TABLE(id, name, `group`) VALUES(4, 'Rin', 2)")
        db.execSQL("insert into USER_TABLE(id, name, `group`) VALUES(6, 'Ai', 1)")
        db.execSQL("insert into USER_TABLE(id, name, `group`) VALUES(7, 'Bee', 2)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }
}