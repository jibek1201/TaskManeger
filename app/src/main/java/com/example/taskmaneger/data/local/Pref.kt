package com.example.taskmaneger.data.local

import android.content.Context

class Pref(context:Context) {
    private val pref = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)

    fun isShow():Boolean{
        return pref.getBoolean(PREF_SHOW, false)
    }

    fun onShowed(){
        pref.edit().putBoolean(PREF_SHOW, true).apply()
    }

    fun showText():String?{
        return pref.getString(TEXT_SAVE, "")
    }

    fun saveText(text:String){
        pref.edit().putString(TEXT_SAVE, text).apply()
    }

    //image
    fun setImage(image: String) {
        pref.edit().putString(IMAGE_SAVE, image).apply()
    }

    fun getImage(): String {
        return pref.getString(IMAGE_SAVE, "").toString()
    }

    fun saveImage(image:String){
        pref.edit().putString(IMAGE_SAVE, image).apply()
    }

    fun showImage():String?{
        return pref.getString(IMAGE_SAVE, null)
    }
    companion object{
        const val PREF_NAME = "pref.name"
        const val PREF_SHOW = "pref.show"
        const val TEXT_SAVE = "text.saved"
        const val IMAGE_SAVE = "image.saved"
    }
}