package com.opdroid.whatsapp.realm.models

import com.opdroid.whatsapp.data.MyContact
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class ContactRealm: RealmObject {
    @PrimaryKey var id: ObjectId = ObjectId()
//    var contact: MyContact = MyContact()
//    var chatList: RealmList<ChatRealm> = realmListOf()
}