package com.developer.edra.unedrappacademy.android.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.developer.edra.unedrappacademy.android.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class RealTimeDatabaseService : Service() {

    private lateinit var database: FirebaseDatabase
    private lateinit var notesRef: DatabaseReference
    private lateinit var valueEventListener: ValueEventListener

    override fun onCreate() {
        super.onCreate()
        database = FirebaseDatabase.getInstance()
        notesRef =
            database.getReference("active_notes")

        val lastNoteValues: MutableMap<String, MutableMap<String, Int?>> = mutableMapOf()

        valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (noteSnapshot in snapshot.children) {
                    val estudianteId = noteSnapshot.child("EstudianteId").getValue(Int::class.java)
                    val notasSnapshot = noteSnapshot.child("Notas")

                    if (notasSnapshot.exists()) {
                        for (nota in notasSnapshot.children) {
                            val asignaturaId = nota.child("AsignaturaId").getValue(Int::class.java)

                            val key = "$estudianteId-$asignaturaId-${nota.key}"

                            val p1 = nota.child("P1").getValue(Int::class.java)
                            val p2 = nota.child("P2").getValue(Int::class.java)
                            val pa = nota.child("PA").getValue(Int::class.java)
                            val ass = nota.child("AS").getValue(Int::class.java)

                            val currentNoteValues = mutableMapOf(
                                "P1" to p1,
                                "P2" to p2,
                                "PA" to pa,
                                "AS" to ass
                            )

                            // Compara los valores actuales con los previos almacenados en el mapa
                            val previousNoteValues = lastNoteValues[key]

                            // Si no había valores previos para esta nota, inicializamos
                            if (previousNoteValues == null) {
                                lastNoteValues[key] = currentNoteValues
                            } else {
                                // Comparar cada propiedad de la nota
                                currentNoteValues.forEach { (property, currentValue) ->
                                    val previousValue = previousNoteValues[property]

                                    // Si hay un cambio en alguna propiedad, muestra la notificación
                                    if (previousValue != currentValue) {
                                        showNotification(
                                            "Cambio detectado para Estudiante $estudianteId en Asignatura $asignaturaId",
                                            "Propiedad cambiada: $property\nNuevo valor: $currentValue"
                                        )
                                    }
                                }

                                // Actualizar los valores previos en el mapa
                                lastNoteValues[key] = currentNoteValues
                            }
                        }
                    }
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
            .setContentTitle("Servicio Activo: Notas")
            .setSmallIcon(R.drawable.info)
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
            .setSmallIcon(R.drawable.info)
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
}
