package com.digitopia.digitopiacasestudy.model

import com.digitopia.digitopiacasestudy.enums.UserStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator
import java.util.Date
import java.util.UUID

@Entity(name = "User")
data class User(

        @Id
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "uuid2")
        @Column(name = "user_id")
        val id: UUID?,

        var userStatus: UserStatus?,

        var fullName: String?,

        var normalizedName: String?,

        @Column(unique = true)
        var email: String?,

        var createdDate: Date?,

        var updatedDate: Date?,

        var createdBy: UUID?,

        var updatedBy: UUID?


) {
    protected constructor() : this(Builder())

    private constructor(builder: Builder) : this(
            null,
            null,
            builder.fullName,
            builder.normalizedName,
            builder.email,
            builder.createdDate,
            builder.updatedDate,
            builder.createdBy,
            builder.updatedBy
    )

    class Builder {
        var id: UUID? = null
            private set

        var userStatus: UserStatus? = null
            private set

        var fullName: String? = ""
            private set

        var normalizedName:String? = ""
            private set

        var email: String? = ""
            private set

        var createdDate: Date? = null
            private set

        var updatedDate: Date? = null
            private set

        var createdBy: UUID? = null
            private set

        var updatedBy: UUID? = null
            private set

        fun id(id: UUID?) = apply { this.id = id }
        fun status(userStatus: UserStatus?) = apply { this.userStatus = userStatus }
        fun fullName(fullName: String) = apply { this.fullName = fullName }
        fun normalizedName(normalizedName: String) = apply { this.normalizedName = normalizedName }
        fun email(email: String) = apply { this.email = email }
        fun createdDate(createdDate: Date?) = apply { this.createdDate = createdDate }
        fun updatedDate(updatedDate: Date?) = apply { this.updatedDate = updatedDate }
        fun createdBy(createdBy: UUID?) = apply { this.createdBy = createdBy }
        fun updatedBy(updatedBy: UUID?) = apply { this.updatedBy = updatedBy }

        fun build() = User(this)
    }
}
