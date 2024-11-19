package com.developer.edra.unedrappacademy.android.di

import com.developer.edra.unedrappacademy.android.data.repository.AuthFirebaseImpl
import com.developer.edra.unedrappacademy.android.data.repository.DataRepositoryImpl
import com.developer.edra.unedrappacademy.android.domain.repository.AuthRepository
import com.developer.edra.unedrappacademy.android.domain.repository.DataRepository
import com.developer.edra.unedrappacademy.android.domain.use_case.CreateUserWithEmailAndPasswordUseCase
import com.developer.edra.unedrappacademy.android.domain.use_case.DataGeneralUseCases
import com.developer.edra.unedrappacademy.android.domain.use_case.GetActiveNotes
import com.developer.edra.unedrappacademy.android.domain.use_case.GetActiveNotesByStudentId
import com.developer.edra.unedrappacademy.android.domain.use_case.GetAllPeriods
import com.developer.edra.unedrappacademy.android.domain.use_case.GetAuditById
import com.developer.edra.unedrappacademy.android.domain.use_case.GetCareerById
import com.developer.edra.unedrappacademy.android.domain.use_case.GetCurrentUserUseCase
import com.developer.edra.unedrappacademy.android.domain.use_case.GetPeriodsById
import com.developer.edra.unedrappacademy.android.domain.use_case.GetSchedulesByStudentId
import com.developer.edra.unedrappacademy.android.domain.use_case.GetStudentByEmail
import com.developer.edra.unedrappacademy.android.domain.use_case.GetSubjectById
import com.developer.edra.unedrappacademy.android.domain.use_case.LogoutUseCase
import com.developer.edra.unedrappacademy.android.domain.use_case.PostStudent
import com.developer.edra.unedrappacademy.android.domain.use_case.SignInWithEmailAndPasswordUseCase
import com.developer.edra.unedrappacademy.android.ui.main.MainViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Singleton
    @Provides
    fun provideAuthRepository(auth: FirebaseAuth): AuthRepository {
        return AuthFirebaseImpl(auth)
    }

    @Singleton
    @Provides
    fun provideFirebaseDatabase(): FirebaseDatabase = FirebaseDatabase.getInstance()

    @Singleton
    @Provides
    fun provideDataRepositoryImpl(db: FirebaseDatabase): DataRepository {
        return DataRepositoryImpl(db)
    }


    @Singleton
    @Provides
    fun provideGetStudentByEmailUseCase(repository: DataRepository): GetStudentByEmail {
        return GetStudentByEmail(repository)
    }

    @Singleton
    @Provides
    fun providePostStudentUseCase(repository: DataRepository): PostStudent {
        return PostStudent(repository)
    }

    @Singleton
    @Provides
    fun provideGetCareerByIdUseCase(repository: DataRepository): GetCareerById {
        return GetCareerById(repository)
    }

    @Singleton
    @Provides
    fun provideGetSubjectByIdUseCase(repository: DataRepository): GetSubjectById {
        return GetSubjectById(repository)
    }

    @Singleton
    @Provides
    fun provideGetSchedulesByStudentIdUseCase(repository: DataRepository): GetSchedulesByStudentId {
        return GetSchedulesByStudentId(repository)
    }

    @Singleton
    @Provides
    fun provideGetPeriodsByIdUseCase(repository: DataRepository): GetPeriodsById {
        return GetPeriodsById(repository)
    }

    @Singleton
    @Provides
    fun provideGetAllPeriodsUseCase(repository: DataRepository): GetAllPeriods {
        return GetAllPeriods(repository)
    }

    @Singleton
    @Provides
    fun provideGetAuditByIdUseCase(repository: DataRepository): GetAuditById {
        return GetAuditById(repository)
    }

    @Singleton
    @Provides
    fun provideGetActiveNotesUseCase(repository: DataRepository): GetActiveNotes {
        return GetActiveNotes(repository)
    }

    @Singleton
    @Provides
    fun provideGetActiveNotesByStudentIdUseCase(repository: DataRepository): GetActiveNotesByStudentId {
        return GetActiveNotesByStudentId(repository)
    }


    @Singleton
    @Provides
    fun provideDataGeneralUseCases(
        getStudentByEmail: GetStudentByEmail,
        postStudent: PostStudent,
        getCareerById: GetCareerById,
        getSubjectById: GetSubjectById,
        getSchedulesByStudentId: GetSchedulesByStudentId,
        getPeriodsById: GetPeriodsById,
        getAllPeriods: GetAllPeriods,
        getAuditById: GetAuditById,
        getActiveNotes: GetActiveNotes,
        getActiveNotesByStudentId: GetActiveNotesByStudentId
    ): DataGeneralUseCases {
        return DataGeneralUseCases(
            getStudentByEmail,
            postStudent,
            getCareerById,
            getSubjectById,
            getSchedulesByStudentId,
            getPeriodsById,
            getAllPeriods,
            getAuditById,
            getActiveNotes,
            getActiveNotesByStudentId
        )
    }

    @Singleton
    @Provides
    fun provideCreateUserWithEmailAndPasswordUseCase(authRepository: AuthRepository): CreateUserWithEmailAndPasswordUseCase {
        return CreateUserWithEmailAndPasswordUseCase(authRepository)
    }

    @Singleton
    @Provides
    fun provideGetCurrentUserUseCase(authRepository: AuthRepository): GetCurrentUserUseCase {
        return GetCurrentUserUseCase(authRepository)
    }

    @Singleton
    @Provides
    fun provideLogoutUseCase(authRepository: AuthRepository): LogoutUseCase {
        return LogoutUseCase(authRepository)
    }

    @Singleton
    @Provides
    fun provideSignInWithEmailAndPasswordUseCase(authRepository: AuthRepository): SignInWithEmailAndPasswordUseCase {
        return SignInWithEmailAndPasswordUseCase(authRepository)
    }

    @Singleton
    @Provides
    fun provideMainViewModel(
        getCurrentUserUseCase: GetCurrentUserUseCase,
        logoutUseCase: LogoutUseCase
    ): MainViewModel {
        return MainViewModel(getCurrentUserUseCase, logoutUseCase)
    }
}