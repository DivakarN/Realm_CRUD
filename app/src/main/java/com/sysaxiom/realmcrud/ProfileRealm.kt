package com.sysaxiom.realmcrud

import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import java.util.*


open class ProfileRealm(

    @field:PrimaryKey
    var user_id: Int = 0,
    var full_name : String = "",
    var email : String = "",
    var date_of_birth : Date? = null,
    var profile_photo : ByteArray? = null,
    var basic_profile_done : Boolean = false,
    var efficiency : Double = 0.0,
    var efficiency_overall : Float? = null


) : RealmObject()