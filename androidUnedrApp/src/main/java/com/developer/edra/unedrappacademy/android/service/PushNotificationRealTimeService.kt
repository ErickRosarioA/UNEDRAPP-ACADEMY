package com.developer.edra.unedrappacademy.android.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.developer.edra.unedrappacademy.android.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class RealTimeDatabaseService : Service() {

    private lateinit var database: FirebaseDatabase
    private lateinit var notesRef: DatabaseReference
    private lateinit var studentsRef: DatabaseReference
    private lateinit var careersRef: DatabaseReference

    private lateinit var valueEventListener: ValueEventListener

    override fun onCreate() {
        super.onCreate()
        database = FirebaseDatabase.getInstance()
        studentsRef =
            database.getReference("students")
        careersRef =
            database.getReference("careers")
        notesRef =
            database.getReference("active_notes")

        val lastNoteValues: MutableMap<String, MutableMap<String, Int?>> = mutableMapOf()



        valueEventListener = object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                for (noteSnapshot in snapshot.children) {
                    val estudianteId = noteSnapshot.child("EstudianteId").getValue(Int::class.java)

                    studentsRef.orderByChild("Id").equalTo(estudianteId!!.toDouble())

                        .addListenerForSingleValueEvent(object : ValueEventListener {

                            override fun onDataChange(studentSnapshot: DataSnapshot) {
                                val studentOrdSnapshot = studentSnapshot.children.firstOrNull()

                                val studentEmail =
                                    studentOrdSnapshot!!.child("Correo")
                                        .getValue(String::class.java)
                                val studentCareerId = studentOrdSnapshot.child("CarreraId")
                                    .getValue(Double::class.java)

                                careersRef.orderByChild("Id").equalTo(studentCareerId!!.toDouble())
                                    .addListenerForSingleValueEvent(object : ValueEventListener {
                                        override fun onDataChange(asignaturaShot: DataSnapshot) {

                                            val currentUserEmail =
                                                FirebaseAuth.getInstance().currentUser?.email
                                            if (currentUserEmail == studentEmail) {
                                                val notasSnapshot = noteSnapshot.child("Notas")

                                                if (notasSnapshot.exists()) {
                                                    for (nota in notasSnapshot.children) {
                                                        var asignatureName: String? = null

                                                        val asignaturaId =
                                                            nota.child("AsignaturaId")
                                                                .getValue(Int::class.java)

                                                        val asignaturaList =
                                                            asignaturaShot.child("0")
                                                                .child("Asignaturas")
                                                        for (asignature in asignaturaList.children) {
                                                            val asignaturaIdCareer =
                                                                asignature.child("Id")
                                                                    .getValue(Int::class.java)
                                                            if (asignaturaIdCareer == asignaturaId) {
                                                                asignatureName =
                                                                    asignature.child("NombreAsignatura")
                                                                        .getValue(String::class.java)
                                                            }

                                                        }


                                                        val key =
                                                            "$estudianteId-$asignaturaId-${nota.key}"

                                                        val p1 = nota.child("P1")
                                                            .getValue(Int::class.java)
                                                        val p2 = nota.child("P2")
                                                            .getValue(Int::class.java)
                                                        val pa = nota.child("PA")
                                                            .getValue(Int::class.java)
                                                        val ass = nota.child("AS")
                                                            .getValue(Int::class.java)

                                                        val currentNoteValues = mutableMapOf(
                                                            "P1" to p1,
                                                            "P2" to p2,
                                                            "PA" to pa,
                                                            "AS" to ass
                                                        )


                                                        val previousNoteValues = lastNoteValues[key]


                                                        if (previousNoteValues == null) {
                                                            lastNoteValues[key] = currentNoteValues
                                                        } else {

                                                            currentNoteValues.forEach { (property, currentValue) ->
                                                                val previousValue =
                                                                    previousNoteValues[property]


                                                                if (previousValue != currentValue) {
                                                                    val notificationMessage =
                                                                        when (property) {
                                                                            "P1" -> "Se ha digitado la nota del primer parcial: $currentValue"
                                                                            "P2" -> "Se ha digitado la nota del segundo parcial: $currentValue"
                                                                            "PA" -> "Se ha digitado la nota de práctica: $currentValue"
                                                                            "AS" -> "Se ha digitado la nota de la asignación final: $currentValue"
                                                                            else -> "Cambio detectado en la propiedad $property: $currentValue"
                                                                        }


                                                                    showNotification(
                                                                        "Notas Asignatura $asignatureName",
                                                                        notificationMessage
                                                                    )
                                                                    playSound()
                                                                }
                                                            }


                                                            lastNoteValues[key] = currentNoteValues
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        override fun onCancelled(error: DatabaseError) {
                                            Log.e(
                                                "RealTimeDatabaseService",
                                                "Error al leer datos: ${error.message}"
                                            )
                                        }

                                    })


                            }

                            override fun onCancelled(error: DatabaseError) {
                                Log.e(
                                    "RealTimeDatabaseService",
                                    "Error al leer datos: ${error.message}"
                                )
                            }
                        })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("RealTimeDatabaseService", "Error al leer datos: ${error.message}")
            }
        }

        notesRef.addValueEventListener(valueEventListener)

    }

    @SuppressLint("ForegroundServiceType")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        val notification: Notification = NotificationCompat.Builder(this, "CHANNEL_ID")
            //   .setContentTitle("Servicio Activo: Notas")
            //      .setSmallIcon(R.drawable.info)
            .build()
        startForeground(1, notification)
        return START_NOT_STICKY
    }

    @SuppressLint("NewApi")
    private fun createNotificationChannel() {
        val serviceChannel = NotificationChannel(
            "CHANNEL_ID",
            "Canal de Notificaciones de Notas",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager: NotificationManager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(serviceChannel)
    }

    private fun showNotification(title: String, message: String) {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notificationBuilder = NotificationCompat.Builder(this, "CHANNEL_ID")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        notificationManager.notify(System.currentTimeMillis().toInt(), notificationBuilder.build())
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        notesRef.removeEventListener(valueEventListener)
    }

    private fun playSound() {
        try {
            val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
            val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION)

            audioManager.setStreamVolume(
                AudioManager.STREAM_NOTIFICATION,
                audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION),
                AudioManager.FLAG_PLAY_SOUND
            )

            val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val r: Ringtone = RingtoneManager.getRingtone(applicationContext, notification)

            val numRepeats = 3
            repeat(numRepeats) {
                r.play()
                Thread.sleep(1000)
            }

            audioManager.setStreamVolume(
                AudioManager.STREAM_NOTIFICATION,
                currentVolume,
                AudioManager.FLAG_PLAY_SOUND
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
