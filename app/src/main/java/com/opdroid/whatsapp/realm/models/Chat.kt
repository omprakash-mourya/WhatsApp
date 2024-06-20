package com.opdroid.whatsapp.realm.models

import com.opdroid.whatsapp.data.MessageDay
import com.opdroid.whatsapp.data.MessageDeliveryStatus
import com.opdroid.whatsapp.data.MessageStatus
import com.opdroid.whatsapp.data.MessageType
import io.realm.kotlin.ext.backlinks
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class ChatRealm: RealmObject {
    @PrimaryKey var id: ObjectId = ObjectId()
    var messageId : Int = 0
    var message: String = ""
    var time: String = ""
    var date: String = "27/05/2024"
//    var messageType: MessageType = MessageType.TEXT
//    var messageDeliveryStatus: MessageDeliveryStatus = MessageDeliveryStatus.DELIVERED
//    var messageStatus: MessageStatus = MessageStatus.RECEIVED
//    var messageDay: MessageDay = MessageDay.DATE
//    val isChatOf: RealmResults<ContactRealm> by backlinks(ContactRealm::chatList)
}