package com.developer.edra.unedrappacademy.android.data.remote.repository

import com.developer.edra.unedrappacademy.android.data.remote.model.Audit
import com.developer.edra.unedrappacademy.android.data.remote.model.Career
import com.developer.edra.unedrappacademy.android.data.remote.model.NoteActive
import com.developer.edra.unedrappacademy.android.data.remote.model.Period
import com.developer.edra.unedrappacademy.android.data.remote.model.Resource
import com.developer.edra.unedrappacademy.android.data.remote.model.Schedule
import com.developer.edra.unedrappacademy.android.data.remote.model.Student
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


class DataRepositoryImpl @Inject constructor(
    private val db: FirebaseDatabase
) : DataRepository {

    override fun getStudentByEmail(email: String) = callbackFlow {
        trySend(Resource.Loading())
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val student = snapshot.children.mapNotNull { it.getValue(Student::class.java) }
                    .firstOrNull { it.email == email }
                val result = student?.let { Resource.Success(it) }
                    ?: Resource.Error("No se encontró estudiante")
                trySend(result).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Resource.Error(error.message)).isSuccess
            }
        }
        db.getReference("students").addListenerForSingleValueEvent(listener)
        awaitClose { db.getReference("students").removeEventListener(listener) }
    }


    override fun getCareerById(id: Int) = callbackFlow {
        trySend(Resource.Loading())
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val career = snapshot.children.mapNotNull { it.getValue(Career::class.java) }
                    .firstOrNull { it.id == id }
                val result =
                    career?.let { Resource.Success(it) } ?: Resource.Error("Career not found")
                trySend(result).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Resource.Error(error.message)).isSuccess
            }
        }
        db.getReference("careers").addListenerForSingleValueEvent(listener)
        awaitClose { db.getReference("careers").removeEventListener(listener) }
    }

    override fun getSubjectById(subjectId: Int, careerId: Int) = callbackFlow {
        trySend(Resource.Loading())
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val career = snapshot.children.mapNotNull { it.getValue(Career::class.java) }
                    .firstOrNull { it.id == careerId }

                val subject = career?.subjects?.firstOrNull { it.id == subjectId }

                val result =
                    subject?.let { Resource.Success(it) } ?: Resource.Error("Subject not found")
                trySend(result).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Resource.Error(error.message)).isSuccess
            }
        }
        db.getReference("careers").addListenerForSingleValueEvent(listener)
        awaitClose { db.getReference("careers").removeEventListener(listener) }
    }

    override fun getSchedulesByStudentId(studentId: Int) = callbackFlow {
        trySend(Resource.Loading())
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val schedules = snapshot.children.mapNotNull { it.getValue(Schedule::class.java) }
                    .firstOrNull { it.studentId == studentId }
                val result =
                    schedules?.let { Resource.Success(it) } ?: Resource.Error("Subject not found")
                trySend(result).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Resource.Error(error.message)).isSuccess
            }
        }
        db.getReference("schedules")
            .addListenerForSingleValueEvent(listener)
        awaitClose {
            db.getReference("schedules").removeEventListener(listener)
        }
    }

    override fun getPeriodsById(id: Int) = callbackFlow {
        trySend(Resource.Loading())
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val periods = snapshot.children.mapNotNull { it.getValue(Period::class.java) }
                    .firstOrNull { it.id == id }

                val result =
                    periods?.let { Resource.Success(it) } ?: Resource.Error("Periodo not found")
                trySend(result).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Resource.Error(error.message)).isSuccess
            }
        }
        db.getReference("periods").addListenerForSingleValueEvent(listener)
        awaitClose { db.getReference("periods").removeEventListener(listener) }
    }

    override fun getPeriods() = callbackFlow {
        trySend(Resource.Loading())
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val periods = snapshot.children.mapNotNull { it.getValue(Period::class.java) }
                trySend(Resource.Success(periods)).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Resource.Error(error.message)).isSuccess
            }
        }
        db.getReference("periods").addListenerForSingleValueEvent(listener)
        awaitClose { db.getReference("periods").removeEventListener(listener) }
    }

    override fun getAuditsById(id: Int) = callbackFlow {
        trySend(Resource.Loading())
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val audits = snapshot.children.mapNotNull { it.getValue(Audit::class.java) }
                    .firstOrNull { it.id == id }
                val result =
                    audits?.let { Resource.Success(it) } ?: Resource.Error("Auditoria not found")
                trySend(result).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Resource.Error(error.message)).isSuccess
            }
        }
        db.getReference("audits").addListenerForSingleValueEvent(listener)
        awaitClose { db.getReference("audits").removeEventListener(listener) }
    }

    override fun getActiveNotes() = callbackFlow {
        trySend(Resource.Loading())
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val activeNotes =
                    snapshot.children.mapNotNull { it.getValue(NoteActive::class.java) }
                trySend(Resource.Success(activeNotes)).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Resource.Error(error.message)).isSuccess
            }
        }
        db.getReference("active_notes").addListenerForSingleValueEvent(listener)
        awaitClose { db.getReference("active_notes").removeEventListener(listener) }
    }

    override fun getActiveNotesById(studentId: Int) = callbackFlow {
        trySend(Resource.Loading())
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val activeNotes =
                    snapshot.children.mapNotNull { it.getValue(NoteActive::class.java) }
                        .firstOrNull { it.studentId == studentId }

                val result =
                    activeNotes?.let { Resource.Success(it) } ?: Resource.Error("Nota not found")
                trySend(result).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Resource.Error(error.message)).isSuccess
            }
        }
        db.getReference("active_notes").addListenerForSingleValueEvent(listener)
        awaitClose { db.getReference("active_notes").removeEventListener(listener) }
    }
}