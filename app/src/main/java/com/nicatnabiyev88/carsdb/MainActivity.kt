package com.nicatnabiyev88.carsdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import data.DatabaseHandler
import model.Car

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = DatabaseHandler(this)

        /** add Cars */
//        database.addCar(Car(0,"Toyota","35000"))
//        database.addCar(Car(0,"Mercedes","24000"))
//        database.addCar(Car(0,"BMW","42000"))
//        database.addCar(Car(0,"Hyundai","20000"))
//        database.addCar(Car(0,"Mazda","22000"))
//        database.addCar(Car(0,"Lexus","32000"))
//        database.addCar(Car(0,"Honda","15000"))
//        database.addCar(Car(0,"Ford","27000"))

        /** get ALl Cars */
//        val carList = database.getCars()
//        for (car in carList){
//            Log.i("myMessage",""+car)
//        }

        /** get Car by ID */
//        val car = database.getCar(7)
//        Log.i("myMessage",""+car)

        /** update Car by ID */
//        database.updateCar(Car(7,"Nissan","21000"))

        /** delete Car by ID */
//        database.deleteCar(7)

        /** get cars count */
//        Log.i("myMessage",""+database.getAllCarsCount())
    }
}