package com.opdroid.whatsapp.realm.models

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Course: RealmObject {
    @PrimaryKey var id: ObjectId = ObjectId()
    var name: String = ""
    var description: String = ""
    var teacher: Teacher? = null
    var enrolledStudents: RealmList<Student> = realmListOf()
}