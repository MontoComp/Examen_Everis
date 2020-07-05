package com.bancario.montcomp.examen_everis.Util

import android.content.Context
import android.content.SharedPreferences

class pShardPreferences {
    var context : Context
    var sharedPreferences : SharedPreferences
    val editor : SharedPreferences.Editor

    constructor(context: Context) {
        this.context = context
        this.sharedPreferences = context.getSharedPreferences("TALLER_KOTLIN_FORM", Context.MODE_PRIVATE)
        this.editor = this.sharedPreferences.edit()
    }
    fun put(key:String, value : String) {
        this.editor.putString(key, value)
    }
    fun save() {
        this.editor.apply()
    }

    fun getKey(key: String) : String? {
        return this.sharedPreferences.getString(key, "none")
    }
    /* para enviar
    * val sf = mSharedPreferences(this)
        sf.put(SecondActivity.KEY_USUARIO, usuario)
        sf.put(SecondActivity.KEY_PASSWORD, password)
        sf.put(SecondActivity.KEY_NAME, name)
        sf.put(SecondActivity.KEY_LASTNAME, lastname)
        sf.put(SecondActivity.KEY_DNI, dni)
        sf.put(SecondActivity.KEY_ADDRESS, address)
        sf.save()
        *
        //object
        val json = JSONObject()
        json.put(SecondActivity.KEY_USUARIO, usuario)
        json.put(SecondActivity.KEY_PASSWORD, password)
        json.put(SecondActivity.KEY_NAME, name)
        json.put(SecondActivity.KEY_LASTNAME, lastname)
        json.put(SecondActivity.KEY_DNI, dni)
        json.put(SecondActivity.KEY_ADDRESS, address)
        val sf = mSharedPreferences(this)
        sf.put("session", json.toString())
        sf.save()
        *
        *
        *
        *
        * */

    /*para recibir
        val mShared  =mSharedPreferences(this)
        val session = mShared.getKey("session") ?: return null

        val sessionObj : JSONObject = JSONObject(session)

        usuario = sessionObj.getString(KEY_USUARIO)
        password = sessionObj.getString(KEY_PASSWORD)
        nombre = sessionObj.getString(KEY_NAME)
        lastname = sessionObj.getString(KEY_LASTNAME)
        dni = sessionObj.getString(KEY_DNI)
        address = sessionObj.getString(KEY_ADDRESS)
    *
    * */
}