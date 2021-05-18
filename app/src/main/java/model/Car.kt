package model

import android.view.ViewParent

class Car (val id:Int, val name:String, val price:String){
    override fun toString(): String {
        return "car: [$id, $name, $price]"
    }
}