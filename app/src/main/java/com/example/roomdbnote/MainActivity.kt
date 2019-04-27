package com.example.roomdbnote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roomdbnote.database.user.SettingEntity
import com.example.roomdbnote.database.user.SettingRoomDb
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    val db by lazy { SettingRoomDb.getRoomDb(this)!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Completable.fromAction {
            db.deviceDao().insert(
                SettingEntity.Device(
                    deviceName = "Switch",
                    type = 0,
                    ownDate = Calendar.getInstance().time,
                    userId = 0
                    )
            )

            db.deviceDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        txtView.text = "${it[0].ownDate} : ${it[0].deviceName}"
                    },{

                    }
                )

        }.subscribeOn(Schedulers.io())
            .subscribe()


    }

    override fun onDestroy() {
        super.onDestroy()

        SettingRoomDb.destroyDataBase()
    }
}
