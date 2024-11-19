package com.developer.edra.unedrappacademy.android.service

import android.annotation.SuppressLint
import com.google.firebase.messaging.FirebaseMessagingService

//Prueba de servicio con FirebaseMessagingServiceApp- mejor hice mi propio servicio
@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class FirebaseMessagingServiceApp : FirebaseMessagingService() {
//    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//        if (remoteMessage.data.isNotEmpty()) {
//            val title = remoteMessage.data["title"] ?: "Nueva Notificación"
//            val message = remoteMessage.data["message"] ?: "Tienes una nueva actualización"
//            showNotification(title, message)
//            playSound()
//        }
//    }
//
//    override fun onNewToken(token: String) {
//        super.onNewToken(token)
//        Log.i("FCM_TOKEN", token)
//        // Aquí puedes enviar el nuevo token al servidor si es necesario
//    }
//
//    @SuppressLint("NewApi")
//    private fun showNotification(title: String, message: String) {
//        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//        val notificationChannelId = "NOTES_ALERT_CHANNEL"
//
//        val notificationChannel = NotificationChannel(
//            notificationChannelId,
//            "Canal de Notificaciones de Notas",
//            NotificationManager.IMPORTANCE_HIGH
//        )
//        notificationManager.createNotificationChannel(notificationChannel)
//
//        val notificationBuilder = NotificationCompat.Builder(this, notificationChannelId)
//            .setSmallIcon(R.drawable.info)
//            .setContentTitle(title)
//            .setContentText(message)
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//
//        notificationManager.notify(System.currentTimeMillis().toInt(), notificationBuilder.build())
//    }
//
//    private fun playSound() {
//        try {
//            val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
//            val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION)
//
//            audioManager.setStreamVolume(
//                AudioManager.STREAM_NOTIFICATION,
//                audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION),
//                AudioManager.FLAG_PLAY_SOUND
//            )
//
//            val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//            val r: Ringtone = RingtoneManager.getRingtone(applicationContext, notification)
//
//            val numRepeats = 3
//            repeat(numRepeats) {
//                r.play()
//                Thread.sleep(1000)
//            }
//
//            audioManager.setStreamVolume(
//                AudioManager.STREAM_NOTIFICATION,
//                currentVolume,
//                AudioManager.FLAG_PLAY_SOUND
//            )
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
}