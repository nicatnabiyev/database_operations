package data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import model.Car

class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "carsDB"
        private val TABLE_NAME = "cars"

        private val KEY_ID = "id"
        private val KEY_NAME = "name"
        private val KEY_PRICE = "price"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PRICE + " TEXT" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    /** insert */
    fun addCar(car:Car):Long{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, car.name)
        contentValues.put(KEY_PRICE,car.price)

        //inserting row
        val success = db.insert(TABLE_NAME, null, contentValues)

        //closing database connection
        db.close();

        return success;
    }

    /** select by id */
    fun getCar(id: Int): Car {
        val db = this.readableDatabase

        val cursor = db.query(
            TABLE_NAME, arrayOf(KEY_ID, KEY_NAME, KEY_PRICE), "$KEY_ID=?", arrayOf(id.toString()),
            null, null, null, null
        )
        cursor?.moveToFirst()
        return Car(cursor.getInt(0), cursor.getString(1), cursor.getString(2))
    }

    /** select all */
    fun getCars():List<Car>{
        val carList:ArrayList<Car> = ArrayList()
        val selectQuery = "SELECT  * FROM $TABLE_NAME"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var carId: Int
        var carName: String
        var carPrice: String
        if (cursor.moveToFirst()) {
            do {
                carId = cursor.getInt(cursor.getColumnIndex("id"))
                carName = cursor.getString(cursor.getColumnIndex("name"))
                carPrice = cursor.getString(cursor.getColumnIndex("price"))
                val car= Car(carId, carName, carPrice)
                carList.add(car)
            } while (cursor.moveToNext())
        }
        return carList
    }

    /** update by id */
    fun updateCar(car:Car):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, car.name)
        contentValues.put(KEY_PRICE, car.price )

        val success = db.update(TABLE_NAME, contentValues,"id="+car.id,null)
        db.close()

        return success
    }

    /** get all count */
    fun deleteCar(id:Int):Int{
        val db = this.writableDatabase
        val success = db.delete(TABLE_NAME, "id=$id",null)
        db.close()
        return success
    }

    /** delete by id*/
    fun getAllCarsCount():Int{
        return getCars().size
    }
}