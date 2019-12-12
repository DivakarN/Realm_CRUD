package com.sysaxiom.realmcrud

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import io.realm.Realm
import io.realm.RealmQuery
import io.realm.RealmResults
import io.realm.kotlin.delete
import io.realm.kotlin.where
import java.io.ByteArrayOutputStream
import java.util.*


class RealmHandler{

    companion object {

        //region Read
        fun getallDataforOneObject(context:Context) : RealmResults<ProfileRealm> {

            val realm = Realm.getDefaultInstance()
            val profile = realm.where<ProfileRealm>()
            val profileValues = profile.findAll()
            return profileValues
        }

        fun getDatafromOneObjectbasedOnCondtion(context:Context,userid:Int) : ProfileRealm? {

            val realm = Realm.getDefaultInstance()
            val profile = realm.where<ProfileRealm>()
            profile.equalTo("user_id",userid)

            //lesser than
            //profile.lessThan("efficiency",90.0)

            //greater than
            //profile.greaterThan("efficiency",90.0)

            val profileValues = profile.findFirst()
            return profileValues

        }
        //endregion

        //region Writes
        fun insertData(context:Context, user_id: Int, full_name:String, email:String, date_of_birth:Date, profile_photo:ByteArray, basic_profile_done:Boolean, efficiency: Double, efficiency_overall:Float) {

            val realm = Realm.getDefaultInstance()

            realm.executeTransactionAsync  { realm ->
                val profile = realm.createObject(ProfileRealm::class.java,user_id)
                //profile.user_id = user_id
                profile.full_name = full_name
                profile.email = email

                profile.date_of_birth = date_of_birth
                profile.profile_photo = profile_photo

                profile.basic_profile_done = basic_profile_done

                profile.efficiency = efficiency
                profile.efficiency_overall = efficiency_overall
                realm.insert(profile)
            }

            //insertData(context,profile)

        }
        //endregion

        //region Updates
        fun updateData(context:Context,user_id:Int,efficiency:Double) : ProfileRealm? {

            val realm = Realm.getDefaultInstance()

            val profile = realm.where<ProfileRealm>().equalTo("user_id",user_id)
            val specificProfile = profile.findFirst()

            if (specificProfile != null) {
                realm.executeTransaction { _ ->
                    specificProfile.efficiency = efficiency
                }
            }

            return specificProfile

        }
        //endregion

        //region Deletes
        fun deleteAll(context: Context) {

            val realm = Realm.getDefaultInstance()

            realm.executeTransaction {realm ->
                realm.deleteAll()
            }
        }
        fun deleteSpecificObject(context:Context){

            val realm = Realm.getDefaultInstance()

            realm.executeTransaction {realm ->
                realm.delete<ProfileRealm>()
            }
        }
        fun deleteSpecificObjectonCondition(context:Context,user_id: Int){

            val realm = Realm.getDefaultInstance()

            realm.executeTransaction {realm ->
                val profile = realm.where<ProfileRealm>().equalTo("user_id",user_id).findAll()
                profile.deleteAllFromRealm()
            }
        }
        //endregion

    }


}

