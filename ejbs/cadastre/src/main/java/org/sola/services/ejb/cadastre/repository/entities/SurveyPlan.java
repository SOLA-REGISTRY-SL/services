package org.sola.services.ejb.cadastre.repository.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.services.common.repository.entities.AbstractVersionedEntity;

/**
 * Survey plan details
 */
@Table(name = "survey_plan_details", schema = "cadastre")
/**
 *
 * @author Moses VB
 */
public class SurveyPlan extends AbstractVersionedEntity {
  public static String WHERE_CONDITION = "id = #{id}";
           
    //Constructor
    public SurveyPlan() {
        super();
    }
//Columns of Database fields
    @Id
    @Column
    private String id;

    @Column(name = "ls_nr")
    private String LSNo;

    @Column(name = "owner_name")
    private String nameofOwner;
 
    @Column(name = "name_of_street")
    private String propertyNameofStreet;
    
    @Column(name = "address_nr")
    private String propertyAddressNo;
    
     @Column(name = "land_type")
    private String landtype;
     
    @Column(name = "land_area")
    private String areaOfLand;
    
    @Column(name = "land_measurement")
    private String landMeasurement;
    
    @Column(name = "license_surveyor_name")
    private String nameofLicenseSurveyor;
    
    @Column(name = "east_neighbour")
    private String eastNeighborPlotHolder;
    
    @Column(name = "west_neighbour")
    private String westNeighborPlotHolder;
    
    @Column(name = "north_neighbour")
    private String northNeighborPlotHolder;
    
    @Column(name = "south_neighbour")
    private String southNeighborPlotHolder;
    
    @Column(name = "survey_method")
    private String surveyingMethod;
    
    @Column(name = "director_of_survey")
    private String directorofSurveys;
    
    @Column(name = "date_surveyed")
    private String DateSurveyed;
    
    @Column(name = "beacon_number")
    private String beaconNumber;
    
    @Column(name = "charting_officer_name")
    private String nameofCO;
     
    @Column(name = "state_land_clearing_officer")
    private String nameofSLCO;
      
//Getters and Setters s
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
 
    public String getLSNo() {
        return LSNo;
    }

    public void setLSNo(String LSNo) {
        this.LSNo = LSNo;
    }

    public String getNameofOwner() {
        return nameofOwner;
    }

    public void setNameofOwner(String nameofOwner) {
        this.nameofOwner = nameofOwner;
    }
 
    public String getPropertyNameofStreet() {
        return propertyNameofStreet;
    }

    public void setPropertyNameofStreet(String propertyNameofStreet) {
        this.propertyNameofStreet = propertyNameofStreet;
    }

    public String getPropertyAddressNo() {
        return propertyAddressNo;
    }

    public void setPropertyAddressNo(String propertyAddressNo) {
        this.propertyAddressNo = propertyAddressNo;
    }

    public String getAreaOfLand() {
        return areaOfLand;
    }

    public void setAreaOfLand(String areaOfLand) {
        this.areaOfLand = areaOfLand;
    }

    public String getLandMeasurement() {
        return landMeasurement;
    }

    public void setLandMeasurement(String landMeasurement) {
        this.landMeasurement = landMeasurement;
    }

    public String getNameofLicenseSurveyor() {
        return nameofLicenseSurveyor;
    }

    public void setNameofLicenseSurveyor(String nameofLicenseSurveyor) {
        this.nameofLicenseSurveyor = nameofLicenseSurveyor;
    }

    public String getEastNeighborPlotHolder() {
        return eastNeighborPlotHolder;
    }

    public void setEastNeighborPlotHolder(String eastNeighborPlotHolder) {
        this.eastNeighborPlotHolder = eastNeighborPlotHolder;
    }

    public String getWestNeighborPlotHolder() {
        return westNeighborPlotHolder;
    }

    public void setWestNeighborPlotHolder(String westNeighborPlotHolder) {
        this.westNeighborPlotHolder = westNeighborPlotHolder;
    }

    public String getNorthNeighborPlotHolder() {
        return northNeighborPlotHolder;
    }

    public void setNorthNeighborPlotHolder(String northNeighborPlotHolder) {
        this.northNeighborPlotHolder = northNeighborPlotHolder;
    }

    public String getSouthNeighborPlotHolder() {
        return southNeighborPlotHolder;
    }

    public void setSouthNeighborPlotHolder(String southNeighborPlotHolder) {
        this.southNeighborPlotHolder = southNeighborPlotHolder;
    }

    public String getSurveyingMethod() {
        return surveyingMethod;
    }

    public void setSurveyingMethod(String surveyingMethod) {
        this.surveyingMethod = surveyingMethod;
    }

    public String getDirectorofSurveys() {
        return directorofSurveys;
    }

    public void setDirectorofSurveys(String directorofSurveys) {
        this.directorofSurveys = directorofSurveys;
    }

    public String getDateSurveyed() {
        return DateSurveyed;
    }

    public void setDateSurveyed(String DateSurveyed) {
        this.DateSurveyed = DateSurveyed;
    }

    public String getBeaconNumber() {
        return beaconNumber;
    }

    public void setBeaconNumber(String beaconNumber) {
        this.beaconNumber = beaconNumber;
    }

    public String getNameofCO() {
        return nameofCO;
    }

    public void setNameofCO(String nameofCO) {
        this.nameofCO = nameofCO;
    }

    public String getNameofSLCO() {
        return nameofSLCO;
    }

    public void setNameofSLCO(String nameofSLCO) {
        this.nameofSLCO = nameofSLCO;
    }
 
}
