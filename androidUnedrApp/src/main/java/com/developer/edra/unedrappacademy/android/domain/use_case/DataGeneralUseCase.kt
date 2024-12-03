package com.developer.edra.unedrappacademy.android.domain.use_case

import com.developer.edra.unedrappacademy.android.domain.model.Audit
import com.developer.edra.unedrappacademy.android.domain.model.Career
import com.developer.edra.unedrappacademy.android.domain.model.NoteActive
import com.developer.edra.unedrappacademy.android.domain.model.Period
import com.developer.edra.unedrappacademy.android.domain.model.Resource
import com.developer.edra.unedrappacademy.android.domain.model.Schedule
import com.developer.edra.unedrappacademy.android.domain.model.Student
import com.developer.edra.unedrappacademy.android.domain.model.Subject
import com.developer.edra.unedrappacademy.android.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow

data class DataGeneralUseCases(
    val getStudentByEmail: GetStudentByEmail,
    val postStudent: PostStudent,
    val getCareerById: GetCareerById,
    val getSubjectById: GetSubjectById,
    val getSchedulesByStudentId: GetSchedulesByStudentId,
    val getPeriodsById: GetPeriodsById,
    val getAllPeriods: GetAllPeriods,
    val getAuditById: GetAuditById,
    val getActiveNotes: GetActiveNotes,
    val getActiveNotesByStudentId: GetActiveNotesByStudentId
)

class GetStudentByEmail(
    private val repository: DataRepository
) {
    operator fun invoke(email: String): Flow<Resource<Student>> {
        return repository.getStudentByEmail(email)
    }
}

class PostStudent(
    private val repository: DataRepository
) {
    operator fun invoke(student: Student): Flow<Resource<Student>> {
        return repository.postStudent(student)
    }
}

class GetCareerById(
    private val repository: DataRepository
) {
    operator fun invoke(id: Int): Flow<Resource<Career>> {
        return repository.getCareerById(id)
    }
}

class GetSubjectById(
    private val repository: DataRepository
) {
    operator fun invoke(subjectId: Int, careerId: Int): Flow<Resource<Subject>> {
        return repository.getSubjectById(subjectId, careerId)
    }
}

class GetSchedulesByStudentId(
    private val repository: DataRepository
) {
    operator fun invoke(studentId: Int): Flow<Resource<Schedule>> {
        return repository.getSchedulesByStudentId(studentId)
    }
}

class GetPeriodsById(
    private val repository: DataRepository
) {
    operator fun invoke(id: Int): Flow<Resource<Period>> {
        return repository.getPeriodsById(id)
    }
}

class GetAllPeriods(
    private val repository: DataRepository
) {
    operator fun invoke(): Flow<Resource<List<Period>>> {
        return repository.getPeriods()
    }
}

class GetAuditById(
    private val repository: DataRepository
) {
    operator fun invoke(id: Int): Flow<Resource<Audit>> {
        return repository.getAuditsById(id)
    }
}

class GetActiveNotes(
    private val repository: DataRepository
) {
    operator fun invoke(): Flow<Resource<List<NoteActive>>> {
        return repository.getActiveNotes()
    }
}

class GetActiveNotesByStudentId(
    private val repository: DataRepository
) {
    operator fun invoke(studentId: Int): Flow<Resource<NoteActive>> {
        return repository.getActiveNotesById(studentId)
    }
}