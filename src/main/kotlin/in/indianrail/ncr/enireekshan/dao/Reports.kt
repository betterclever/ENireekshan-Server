package `in`.indianrail.ncr.enireekshan.dao

import `in`.indianrail.ncr.enireekshan.dao.Inspection.Companion.referrersOn
import `in`.indianrail.ncr.enireekshan.model.InspectionModel
import `in`.indianrail.ncr.enireekshan.model.ReportModel
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

object Reports : IntIdTable() {
    val submittedBy = reference("submittedBy", Users)
    val timestamp = long("timestamp")
}

class Report(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Report>(Reports)

    var ReportID by Reports.id
    var submittedBy by UserEntity referencedOn Reports.submittedBy
    val inspections by Inspection referrersOn Inspections.reportID
    val timestamp by Reports.timestamp

    fun getReportModel() = ReportModel(
            id = ReportID.value,
            submittedBy = submittedBy.phone.value,
            inspections = inspections.map{ it.getInspectionModel() },
            timestamp =  timestamp
    )
}


