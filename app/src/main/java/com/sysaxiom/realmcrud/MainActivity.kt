package com.sysaxiom.realmcrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateData()
        getData()
    }

    //region Read
    fun getData() {
        val data = RealmHandler.getallDataforOneObject(this)
        Log.d("getData",data.toString())
    }
    fun getDatawithCondition() {
        val user_id = 1
        val data = RealmHandler.getDatafromOneObjectbasedOnCondtion(this,user_id)
        Log.d("getData",data.toString())
    }
    //endregion

    //region Write
    fun insertData() {
        val dataArray = byteArrayOf(0x2E, 0x38)
        RealmHandler.insertData(this,3,"Sudhakar","Sudhakar@gmail.com",Date(),dataArray,true,90.0,90.000.toFloat())
    }
    //endregion

    //region Update
    fun updateData() {
        val efficiency = 80.0
        RealmHandler.updateData(this,3,efficiency)
    }
    //endregion

    //region Delete
    fun deleteAll() {
        RealmHandler.deleteAll(this)
    }
    fun deleteSpecificObject() {
        RealmHandler.deleteSpecificObject(this)
    }
    fun deleteSpecificObjectforCondition() {
        RealmHandler.deleteSpecificObjectonCondition(this,1)
    }
    //endregion
}
