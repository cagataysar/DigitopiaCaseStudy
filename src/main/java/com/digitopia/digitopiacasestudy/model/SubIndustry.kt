package com.digitopia.digitopiacasestudy.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity(name = "SubIndustry")
data class SubIndustry(

        @Id
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "uuid2")
        @Column(name = "subindustry_id")
        val subIndustryID: UUID?,

        var sunIndustryName: String?,

) {
    protected constructor() : this(Builder())

    private constructor(builder: Builder) : this(
            null,
            builder.sunIndustryName
    )

    class Builder {
        var subIndustryID: UUID? = null
            private set

        var sunIndustryName: String? = ""
            private set


        fun subIndustryID(subIndustryID: UUID?) = apply { this.subIndustryID = subIndustryID }
        fun sunIndustryName(sunIndustryName: String?) = apply { this.sunIndustryName = sunIndustryName }
        fun build() = SubIndustry(this)
    }
}
