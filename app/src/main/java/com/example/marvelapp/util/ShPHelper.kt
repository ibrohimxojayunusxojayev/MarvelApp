package com.example.marvelapp.util

import android.content.Context
import com.example.marvelapp.model.Characters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ShPHelper private constructor(context: Context) {
    val shared = context.getSharedPreferences("data", 0)
    val edit = shared.edit()


    companion object {
        private var instance: ShPHelper? = null
        fun getInstance(context: Context): ShPHelper {
            if (instance == null) {
                instance = ShPHelper(context)
            }
            return instance!!
        }
    }

    fun setChars(characters: Characters) {
        val type = object : TypeToken<List<Characters>>() {}.type
        val gson = Gson()

        val bookList: MutableList<Characters>
        val str = shared.getString("Books", "")

        if (str == "") {
            bookList = mutableListOf()
        } else {
            bookList = gson.fromJson(str, type)
        }
        bookList.add(characters)

        val edited = gson.toJson(bookList)
        edit.putString("Books", edited).apply()
    }

    fun removeChar(characters: Characters) {
        val type = object : TypeToken<List<Characters>>() {}.type
        val gson = Gson()

        val bookList: MutableList<Characters>
        val str = shared.getString("Books", "")

        if (str == "") {
            bookList = mutableListOf()
        } else {
            bookList = gson.fromJson(str, type)
        }
        for (i in bookList) {
            if (i.name == characters.name && i.realname == characters.realname) {
                bookList.remove(i)
            }
        }

        val edited = gson.toJson(bookList)
        edit.putString("Books", edited).apply()
    }

    fun getChars(): MutableList<Characters> {
        val type = object : TypeToken<List<Characters>>() {}.type
        val gson = Gson()

        val bookList: MutableList<Characters>
        val str = shared.getString("Books", "")

        if (str == "") {
            bookList = mutableListOf()
        } else {
            bookList = gson.fromJson(str, type)
        }
        return bookList
    }

    fun setAllChars(list: MutableList<Characters>) {
        val type = object : TypeToken<MutableList<Characters>>() {}.type
        val gson = Gson()

        var bookList: MutableList<Characters>
        val str = shared.getString("AllBooks", "")

        if (str == "") {
            bookList = mutableListOf()
        } else {
            bookList = gson.fromJson(str, type)
        }
        bookList = list

        val edited = gson.toJson(bookList)
        edit.putString("AllBooks", edited).apply()
    }
    fun getAllChars(): MutableList<Characters> {
        val type = object : TypeToken<List<Characters>>() {}.type
        val gson = Gson()

        val bookList: MutableList<Characters>
        val str = shared.getString("AllBooks", "")

        if (str == "") {
            bookList = mutableListOf()
        } else {
            bookList = gson.fromJson(str, type)
        }
        return bookList
    }
}