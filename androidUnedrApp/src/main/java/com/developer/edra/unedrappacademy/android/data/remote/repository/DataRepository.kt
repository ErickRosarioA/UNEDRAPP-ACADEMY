package com.developer.edra.unedrappacademy.android.data.remote.repository

import com.developer.edra.unedrappacademy.android.data.remote.model.Audit
import com.developer.edra.unedrappacademy.android.data.remote.model.Career
import com.developer.edra.unedrappacademy.android.data.remote.model.NoteActive
import com.developer.edra.unedrappacademy.android.data.remote.model.Period
import com.developer.edra.unedrappacademy.android.data.remote.model.Schedule
import com.developer.edra.unedrappacademy.android.data.remote.model.Student
import com.developer.edra.unedrappacademy.android.data.remote.model.Resource
import kotlinx.coroutines.flow.Flow

interface DataRepository {
    fun getStudentById(id: Int): Flow<Resource<Student>>
    fun getStudentByEmail(email: String): Flow<Resource<Student>>
    fun getCareerById(id: Int): Flow<Resource<Career>>
    fun getSchedulesByStudentId(studentId: Int): Flow<Resource<List<Schedule>>>
    fun getPeriods(): Flow<Resource<List<Period>>>
    fun getAudits(): Flow<Resource<List<Audit>>>
    fun getActiveNotes(): Flow<Resource<List<NoteActive>>>
}