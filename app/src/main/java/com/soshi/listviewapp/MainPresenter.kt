package com.soshi.listviewapp

import android.content.Context

class MainPresenter() {

    private var context: Context? = null
    private var sqlite: SQLite? = null

    constructor(context: Context): this() {
        this.context = context
        sqlite = SQLite(context)
    }

    //DBから全データを取得
    internal fun getUserArrayList(sortType: Int): ArrayList<User> {
        val userArrayList = getNotNullIdUserArrayList(sortType)
        userArrayList.addAll(getNullIdUserArrayList())
        return userArrayList
    }

    //DBからIDがNULLでないUserデータを指定したsortTypeで取得
    private fun getNotNullIdUserArrayList(sortType: Int): ArrayList<User> {
        val notNullIdUserArrayList = ArrayList<User>()
        val sqlCmd = when (sortType) {
            1 -> "select unique_id, id, name, `group` from USER_TABLE " +
                    "where id IS NOT NULL order by `group` asc, id asc"
            else -> "select unique_id, id, name, `group` from USER_TABLE " +
                    "where id IS NOT NULL order by `group` asc, name asc"
        }
        val writableDB = sqlite!!.writableDatabase
        writableDB.use { db ->
            val cursor = db.rawQuery(sqlCmd, null)
            var next = cursor.moveToFirst()
            while (next) {
                val user = User()
                val uniqueID = cursor.getInt(0)
                val id = cursor.getInt(1)
                val name = cursor.getString(2)
                val group = cursor.getInt(3)
                user.uniqueID = uniqueID
                user.id = id
                user.name = name
                user.group = group
                notNullIdUserArrayList.add(user)
                next = cursor.moveToNext()
            }
            cursor.close()
        }
        return notNullIdUserArrayList
    }

    //DBからIDがNULLのUserを取得
    private fun getNullIdUserArrayList(): ArrayList<User> {
        val nullIdUserArrayList = ArrayList<User>()
        val sqlCmd = "select unique_id from USER_TABLE where id IS NULL"
        val writableDB = sqlite!!.writableDatabase
        writableDB.use { db ->
            val cursor = db.rawQuery(sqlCmd, null)
            var next = cursor.moveToFirst()
            while (next) {
                val user = User()
                val uniqueID = cursor.getInt(0)
                user.uniqueID = uniqueID
                nullIdUserArrayList.add(user)
                next = cursor.moveToNext()
            }
            cursor.close()
        }
        return nullIdUserArrayList
    }
}