package com.opdroid.whatsapp.realm.models

import androidx.room.Embedded
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


// Teacher 1-to-1 Address
// Teacher 1-to-many Courses
// Student many-to-many Courses

class Address: EmbeddedRealmObject { //to embed the object in the teacher class
//    @PrimaryKey val id: ObjectId = ObjectId()
    var fullName: String = ""
    var street: String = ""
    var city: String = ""
    var houseNumber: Int = 0
    var zipCode: Int = 0
    var teacher: Teacher? = null
}