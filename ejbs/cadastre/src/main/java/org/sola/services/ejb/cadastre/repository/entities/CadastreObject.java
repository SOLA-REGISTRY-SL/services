/**
 * ******************************************************************************************
 * Copyright (C) 2014 - Food and Agriculture Organization of the United Nations (FAO).
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice,this list
 *       of conditions and the following disclaimer.
 *    2. Redistributions in binary form must reproduce the above copyright notice,this list
 *       of conditions and the following disclaimer in the documentation and/or other
 *       materials provided with the distribution.
 *    3. Neither the name of FAO nor the names of its contributors may be used to endorse or
 *       promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT
 * SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,STRICT LIABILITY,OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * *********************************************************************************************
 */
package org.sola.services.ejb.cadastre.repository.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.services.common.LocalInfo;
import org.sola.services.common.repository.AccessFunctions;
import org.sola.services.common.repository.ChildEntity;
import org.sola.services.common.repository.ExternalEJB;
import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;
import org.sola.services.common.repository.entities.AbstractVersionedEntity;
import org.sola.services.ejb.party.businesslogic.PartyEJBLocal;
import org.sola.services.ejb.party.repository.entities.Party;

/**
 * Entity representing the cadastre.cadastre_object table.
 */
@Table(name = "cadastre_object", schema = "cadastre")
public class CadastreObject extends AbstractVersionedEntity {

    /**
     * WHERE clause to return current&pending CO's based on search string
     * compared to first part and last part
     */
    public static final String QUERY_WHERE_SEARCHBYALLPARTS = "(status_code= 'current' or status_code= 'pending') and "
            + "compare_strings(#{search_string}, name_firstpart || ' ' || name_lastpart)";
    /**
     * WHERE clause to return current CO's based on search string compared to
     * first part and last part
     */
    public static final String QUERY_WHERE_SEARCHBYPARTS = "status_code= 'current' and "
            + "compare_strings(#{search_string}, name_firstpart || ' ' || name_lastpart)";
    /**
     * WHERE clause to return current CO's intersecting the specified point
     */
    public static final String QUERY_WHERE_SEARCHBYPOINT = "type_code= #{type_code} "
            + "and status_code= 'current' and "
            + "ST_Intersects(st_transform(geom_polygon, #{srid}), ST_SetSRID(ST_Point(#{x}, #{y}), #{srid}))";
    /**
     * WHERE clause to return CO's linked to the specified ba_unit.id
     */
    public static final String QUERY_WHERE_SEARCHBYBAUNIT = "id in "
            + " (select spatial_unit_id from administrative.ba_unit_contains_spatial_unit "
            + "where ba_unit_id = #{ba_unit_id})";
    /**
     * WHERE clause to return current CO's linked to the specified service.id
     */
    public static final String QUERY_WHERE_SEARCHBYSERVICE = "status_code= 'current' "
            + "and transaction_id in "
            + " (select id from transaction.transaction where from_service_id = #{service_id}) ";
    /**
     * WHERE clause to return CO's linked to the specified transaction.id
     */
    public static final String QUERY_WHERE_SEARCHBYTRANSACTION =
            "transaction_id = #{transaction_id}";
    /**
     * WHERE clause to return current CO's matching type type_code and within
     * distance of the specified geometry
     */
    public static final String QUERY_WHERE_SEARCHBYGEOM = "type_code=#{type_code} "
            + "and status_code= 'current' and "
            + "ST_DWithin(st_transform(geom_polygon, #{srid}), st_transform(#{geom}, #{srid}), "
            + "system.get_setting('map-tolerance')::double precision)";
    /**
     * ORDER BY clause used to order search results for the Search by parts queries. 
     * Uses regex to order cadastre objects by lot number. 
     */
    public static final String QUERY_ORDER_BY_SEARCHBYPARTS =
            "lpad(regexp_replace(name_firstpart, '\\D*', '', 'g'), 5, '0') "
            + "|| name_firstpart || name_lastpart";
    
    public static final String QUERY_MAKE_STATE_LAND_CLEARANCE = 
            "update cadastre.cadastre_object set state_land_clearance = #{cleared} where id = #{id} and status_code = 'pending' and land_type = 'private_land'";
    
    public static final String QUERY_MAKE_PLANNING_CLEARANCE = 
            "update cadastre.cadastre_object set planning_clearance = #{cleared} where id = #{id} and status_code = 'pending' and land_type = 'private_land'";
    
    public static final String QUERY_MAKE_ENVIRONMENT_CLEARANCE = 
            "update cadastre.cadastre_object set environment_clearance = #{cleared} where id = #{id} and status_code = 'pending' and land_type = 'private_land'";
    
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "type_code")
    private String typeCode;
    @Column(name = "approval_datetime")
    private Date approvalDatetime;
    @Column(name = "historic_datetime")
    private Date historicDatetime;
    @Column(name = "source_reference")
    private String sourceReference;
    @Column(name = "name_firstpart")
    private String nameFirstpart;
    @Column(name = "name_lastpart")
    private String nameLastpart;
    @Column(name = "status_code", insertable = false, updatable = false)
    private String statusCode;
    @Column(name = "transaction_id", updatable = false)
    private String transactionId;
    @Column(name = AbstractReadOnlyEntity.CLASSIFICATION_CODE_COLUMN_NAME)
    private String classificationCode;
    @Column(name = AbstractReadOnlyEntity.REDACT_CODE_COLUMN_NAME)
    private String redactCode;
    @Column(name = "geom_polygon")
    @AccessFunctions(onSelect = "st_asewkb(geom_polygon)",
    onChange = "get_geometry_with_srid(#{geomPolygon})")
    private byte[] geomPolygon;
    @Column(name = "land_use_code")
    private String landUseCode;
    @Column(name = "owner_name")
    private String ownerName;
    @Column(name = "address")
    private String address;
    @Column(name = "land_type")
    private String landTypeCode;
    @Column(name = "parcel_area")
    private double parcelArea;
    @Column(name = "licensed_surveyor_id")
    private String licensedSurveyorId;
    @ExternalEJB(ejbLocalClass = PartyEJBLocal.class, loadMethod = "getParty")
    @ChildEntity(childIdField = "licensedSurveyorId")
    private Party licensedSurveyor;
    @Column(name = "east_neighbour")
    private String eastNeighbour;
    @Column(name = "west_neighbour")
    private String westNeighbour;
    @Column(name = "south_neighbour")
    private String southNeighbour;
    @Column(name = "north_neighbour")
    private String northNeighbour;
    @Column(name = "survey_method")
    private String surveyMethodCode;
    @Column(name = "survey_date")
    private Date surveyDate;
    @Column(name = "chiefdom_type")
    private String chiefdomTypeCode;
    @Column(name = "beacon_number")
    private String beaconNumber;
    @Column(name = "charting_officer_id")
    private String chartingOfficerId;
    @ExternalEJB(ejbLocalClass = PartyEJBLocal.class, loadMethod = "getParty")
    @ChildEntity(childIdField = "chartingOfficerId")
    private Party chartingOfficer;
    @Column(name = "state_land_clearing_officer_id")
    private String stateLandClearingOfficerId;
    @ExternalEJB(ejbLocalClass = PartyEJBLocal.class, loadMethod = "getParty")
    @ChildEntity(childIdField = "stateLandClearingOfficerId")
    private Party stateLandClearingOfficer;
    @Column(name="state_land_clearance", insertable = false, updatable = false)
    private boolean stateLandClearance;
    @Column(name="planning_clearance", insertable = false, updatable = false)
    private boolean planningClearance;
    @Column(name="environment_clearance", insertable = false, updatable = false)
    private boolean environmentClearance;
    
    @Column(name="survey_type_code")
    private String surveyTypeCode;
    
    @Column(name="ref_name_firstpart")
    private String refNameFirstpart;
    
    @Column(name="ref_name_lastpart")
    private String refNameLastpart;
    
    @Column(name="survey_number")
    private String surveyNumber;
    
    @Column(name="correspondence_file")
    private String correspondenceFile;
    
    @Column(name="computation_file")
    private String computationFile;
    
    @Column(name="drawn_by")
    private String drawnBy;
    
    @Column(name="checked_by")
    private String checkedBy;
    
    @Column(name="checking_date")
    private Date checkingDate;
    
    @Column(name="dwg_off_no")
    private String dwgOffNumber;
    
    public String getLandUseCode() {
        return landUseCode;
    }

    public void setLandUseCode(String landUseCode) {
        this.landUseCode = landUseCode;
    }

    /**
     * No-arg constructor
     */
    public CadastreObject() {
        super();
    }

    public String getId() {
        id = id == null ? generateId() : id;
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Date getApprovalDatetime() {
        return approvalDatetime;
    }

    public void setApprovalDatetime(Date approvalDatetime) {
        this.approvalDatetime = approvalDatetime;
    }

    public byte[] getGeomPolygon() {
        return geomPolygon;
    }

    public void setGeomPolygon(byte[] geomPolygon) { //NOSONAR
        this.geomPolygon = geomPolygon; //NOSONAR
    }

    public Date getHistoricDatetime() {
        return historicDatetime;
    }

    public void setHistoricDatetime(Date historicDatetime) {
        this.historicDatetime = historicDatetime;
    }

    public String getNameFirstpart() {
        return nameFirstpart;
    }

    public void setNameFirstpart(String nameFirstpart) {
        this.nameFirstpart = nameFirstpart;
    }

    public String getNameLastpart() {
        return nameLastpart;
    }

    public void setNameLastpart(String nameLastpart) {
        this.nameLastpart = nameLastpart;
    }

    public String getSourceReference() {
        return sourceReference;
    }

    public void setSourceReference(String sourceReference) {
        this.sourceReference = sourceReference;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandTypeCode() {
        return landTypeCode;
    }

    public void setLandTypeCode(String landTypeCode) {
        this.landTypeCode = landTypeCode;
    }

    public double getParcelArea() {
        return parcelArea;
    }

    public void setParcelArea(double parcelArea) {
        this.parcelArea = parcelArea;
    }

    public String getLicensedSurveyorId() {
        return licensedSurveyorId;
    }

    public void setLicensedSurveyorId(String licensedSurveyorId) {
        this.licensedSurveyorId = licensedSurveyorId;
    }

    public String getEastNeighbour() {
        return eastNeighbour;
    }

    public void setEastNeighbour(String eastNeighbour) {
        this.eastNeighbour = eastNeighbour;
    }

    public String getWestNeighbour() {
        return westNeighbour;
    }

    public void setWestNeighbour(String westNeighbour) {
        this.westNeighbour = westNeighbour;
    }

    public String getSouthNeighbour() {
        return southNeighbour;
    }

    public void setSouthNeighbour(String southNeighbour) {
        this.southNeighbour = southNeighbour;
    }

    public String getNorthNeighbour() {
        return northNeighbour;
    }

    public void setNorthNeighbour(String northNeighbour) {
        this.northNeighbour = northNeighbour;
    }

    public String getSurveyMethodCode() {
        return surveyMethodCode;
    }

    public void setSurveyMethodCode(String surveyMethodCode) {
        this.surveyMethodCode = surveyMethodCode;
    }

    public Date getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(Date surveyDate) {
        this.surveyDate = surveyDate;
    }

    public String getBeaconNumber() {
        return beaconNumber;
    }

    public void setBeaconNumber(String beaconNumber) {
        this.beaconNumber = beaconNumber;
    }

    public String getChartingOfficerId() {
        return chartingOfficerId;
    }

    public void setChartingOfficerId(String chartingOfficerId) {
        this.chartingOfficerId = chartingOfficerId;
    }

    public String getStateLandClearingOfficerId() {
        return stateLandClearingOfficerId;
    }

    public void setStateLandClearingOfficerId(String stateLandClearingOfficerId) {
        this.stateLandClearingOfficerId = stateLandClearingOfficerId;
    }

    @Override
    public String getClassificationCode() {
        return classificationCode;
    }

    @Override
    public String getRedactCode() {
        return redactCode;
    }

    public void setClassificationCode(String classificationCode) {
        this.classificationCode = classificationCode;
    }

    public void setRedactCode(String redactCode) {
        this.redactCode = redactCode;
    }  

    public Party getLicensedSurveyor() {
        return licensedSurveyor;
    }

    public void setLicensedSurveyor(Party licensedSurveyor) {
        this.licensedSurveyor = licensedSurveyor;
    }

    public Party getChartingOfficer() {
        return chartingOfficer;
    }

    public void setChartingOfficer(Party chartingOfficer) {
        this.chartingOfficer = chartingOfficer;
    }

    public Party getStateLandClearingOfficer() {
        return stateLandClearingOfficer;
    }

    public void setStateLandClearingOfficer(Party stateLandClearingOfficer) {
        this.stateLandClearingOfficer = stateLandClearingOfficer;
    }

    public String getChiefdomTypeCode() {
        return chiefdomTypeCode;
    }

    public void setChiefdomTypeCode(String chiefdomTypeCode) {
        this.chiefdomTypeCode = chiefdomTypeCode;
    }

    public String getSurveyTypeCode() {
        return surveyTypeCode;
    }

    public void setSurveyTypeCode(String surveyTypeCode) {
        this.surveyTypeCode = surveyTypeCode;
    }

    public String getRefNameFirstpart() {
        return refNameFirstpart;
    }

    public void setRefNameFirstpart(String refNameFirstpart) {
        this.refNameFirstpart = refNameFirstpart;
    }

    public String getRefNameLastpart() {
        return refNameLastpart;
    }

    public void setRefNameLastpart(String refNameLastpart) {
        this.refNameLastpart = refNameLastpart;
    }

    public String getSurveyNumber() {
        return surveyNumber;
    }

    public void setSurveyNumber(String surveyNumber) {
        this.surveyNumber = surveyNumber;
    }

    public String getCorrespondenceFile() {
        return correspondenceFile;
    }

    public void setCorrespondenceFile(String correspondenceFile) {
        this.correspondenceFile = correspondenceFile;
    }

    public String getComputationFile() {
        return computationFile;
    }

    public void setComputationFile(String computationFile) {
        this.computationFile = computationFile;
    }

    public boolean isStateLandClearance() {
        return stateLandClearance;
    }

    public void setStateLandClearance(boolean stateLandClearance) {
        this.stateLandClearance = stateLandClearance;
    }

    public String getDrawnBy() {
        return drawnBy;
    }

    public void setDrawnBy(String drawnBy) {
        this.drawnBy = drawnBy;
    }

    public String getCheckedBy() {
        return checkedBy;
    }

    public void setCheckedBy(String checkedBy) {
        this.checkedBy = checkedBy;
    }

    public Date getCheckingDate() {
        return checkingDate;
    }

    public void setCheckingDate(Date checkingDate) {
        this.checkingDate = checkingDate;
    }

    public String getDwgOffNumber() {
        return dwgOffNumber;
    }

    public void setDwgOffNumber(String dwgOffNumber) {
        this.dwgOffNumber = dwgOffNumber;
    }

    public boolean isPlanningClearance() {
        return planningClearance;
    }

    public void setPlanningClearance(boolean planningClearance) {
        this.planningClearance = planningClearance;
    }

    public boolean isEnvironmentClearance() {
        return environmentClearance;
    }

    public void setEnvironmentClearance(boolean environmentClearance) {
        this.environmentClearance = environmentClearance;
    }

    /**
     * Sets the transaction Id on the entity prior to save.
     */
    @Override
    public void preSave() {
        if (this.isNew() && this.getTransactionId() == null) {
            setTransactionId(LocalInfo.getTransactionId());
        }

        super.preSave();
    }
}
