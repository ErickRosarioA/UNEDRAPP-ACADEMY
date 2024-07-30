package com.developer.edra.unedrappacademy.android.data.remote.repository

import com.developer.edra.unedrappacademy.android.data.remote.model.Audit
import com.developer.edra.unedrappacademy.android.data.remote.model.Career
import com.developer.edra.unedrappacademy.android.data.remote.model.NoteActive
import com.developer.edra.unedrappacademy.android.data.remote.model.Period
import com.developer.edra.unedrappacademy.android.data.remote.model.Schedule
import com.developer.edra.unedrappacademy.android.data.remote.model.Student
import com.developer.edra.unedrappacademy.android.data.remote.model.Resource
import com.developer.edra.unedrappacademy.android.data.remote.model.Subject
import kotlinx.coroutines.flow.Flow

interface DataRepository {
    fun getStudentByEmail(email: String): Flow<Resource<Student>>
    fun postStudent(student: Student): Flow<Resource<Student>>
    fun getCareerById(id: Int): Flow<Resource<Career>>
    fun getSubjectById(subjectId: Int, careerId: Int): Flow<Resource<Subject>>
    fun getSchedulesByStudentId(studentId: Int): Flow<Resource<Schedule>>
    fun getPeriodsById(id: Int):Flow<Resource<Period>>
    fun getPeriods(): Flow<Resource<List<Period>>>
    fun getAuditsById(id: Int): Flow<Resource<Audit>>
    fun getActiveNotes(): Flow<Resource<List<NoteActive>>>
    fun getActiveNotesById(studentId: Int): Flow<Resource<NoteActive>>
}