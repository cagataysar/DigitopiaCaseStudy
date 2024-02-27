package com.digitopia.digitopiacasestudy.model

import com.digitopia.digitopiacasestudy.enums.InvitationStatus
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import java.util.*

@Entity(name = "Invitation")
data class Invitation(

        @Id
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "uuid2")
        @Column(name = "invitation_id")
        val invitationID: UUID?,

        var invitationMessage: String?,

        @Column(unique = true, length = 32)
        var invitationCode: String?,

        var invitationStatus: InvitationStatus?,

        var userMail: String?,

        var userID: UUID?,

        var invitationDate: LocalDateTime?,

        var invitationExpiredDate: LocalDateTime?,

        var createdDate: LocalDateTime?,

        var updatedDate: LocalDateTime?,

        var createdBy: UUID?,

        var updatedBy: UUID?
) {
    protected constructor() : this(Builder())

    private constructor(builder: Builder) : this(
            null,
            builder.invitationMessage,
            builder.invitationCode,
            builder.invitationStatus,
            builder.userMail,
            builder.userID,
            builder.invitationDate,
            builder.invitationExpiredDate,
            builder.createdDate,
            builder.updatedDate,
            builder.createdBy,
            builder.updatedBy
    )

    class Builder {
        var invitationID: UUID? = null
            private set

        var invitationMessage: String? = ""
            private set

        var invitationCode: String? = ""
            private set

        var invitationStatus: InvitationStatus? = null
            private set

        var userMail: String? = ""
            private set

        var userID: UUID? = null
            private set

        var invitationDate: LocalDateTime? = null
            private set

        var invitationExpiredDate: LocalDateTime? = null
            private set

        var createdDate: LocalDateTime? = null
            private set

        var updatedDate: LocalDateTime? = null
            private set

        var createdBy: UUID? = null
            private set

        var updatedBy: UUID? = null
            private set

        fun invitationID(invitationID: UUID?) = apply { this.invitationID = invitationID }
        fun invitationMessage(invitationMessage: String?) = apply { this.invitationMessage = invitationMessage }
        fun invitationCode(invitationCode: String?) = apply { this.invitationCode = invitationCode }

        fun invitationStatus(invitationStatus: InvitationStatus?) = apply { this.invitationStatus = invitationStatus }
        fun userMail(userMail: String?) = apply { this.userMail = userMail }
        fun userID(userID: UUID?) = apply { this.userID = userID }
        fun invitationDate(invitationDate: LocalDateTime?) = apply { this.invitationDate = invitationDate }
        fun invitationExpiredDate(invitationExpiredDate: LocalDateTime?) = apply { this.invitationExpiredDate = invitationExpiredDate }

        fun createdDate(createdDate: LocalDateTime?) = apply { this.createdDate = createdDate }
        fun updatedDate(updatedDate: LocalDateTime?) = apply { this.updatedDate = updatedDate }
        fun createdBy(createdBy: UUID?) = apply { this.createdBy = createdBy }
        fun updatedBy(updatedBy: UUID?) = apply { this.updatedBy = updatedBy }

        fun build() = Invitation(this)
    }
}
