package com.digitopia.digitopiacasestudy.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity(name = "Industry")
data class Industry(

        @Id
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "uuid2")
        @Column(name = "industry_id")
        val industryID: UUID?,

        var industryName: String?,

        var normalizedIndustryName: String?,

//        @OneToMany
//        var subIndustry: SubIndustry?

//        var userList: ArrayList<>?
) {
    protected constructor() : this(Builder())

    private constructor(builder: Builder) : this(
            null,
            builder.industryName,
            builder.normalizedIndustryName,
//            builder.subIndustry
    )

    class Builder {
        var industryID: UUID? = null
            private set

        var industryName: String? = ""
            private set

        var normalizedIndustryName: String? = ""
            private set

        var subIndustry: SubIndustry? = null
            private set

        fun industryID(industryID: UUID?) = apply { this.industryID = industryID }
        fun industryName(industryName: String?) = apply { this.industryName = industryName }
        fun normalizedIndustryName(normalizedIndustryName: String) = apply { this.normalizedIndustryName = normalizedIndustryName }
        fun subIndustry(subIndustry: SubIndustry?) = apply { this.subIndustry = subIndustry }
        fun build() = Industry(this)
    }
}
