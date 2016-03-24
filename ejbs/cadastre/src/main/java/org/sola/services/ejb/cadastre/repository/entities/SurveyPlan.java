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

    //Constructor
    public SurveyPlan() {
        super();
    }
//Columns of Database fields
    @Id
    @Column
    private String id;

    @Column(name = "LSNo")
    String LSNo;

    @Column(name = "NameofOwner")
    String nameofOwner;

    @Column(name = "Gender")
    String gender;

    @Column(name = "DateofBirt")
    String dateOfBirth;

    @Column(name = "PlaceofBirth")
    String placeOfBirth;

    @Column(name = "PhoneNo")
    String phoneNo;
    
    @Column(name = "EmailAddress")
    String emailAddress;
    
    @Column(name = "NationalIDNo")
    String nationalIDNo;
    
    @Column(name = "PassportNo")
    String passportNo;
    
    @Column(name = "AddressofOwner")
    String addressOfOwner;
    
    @Column(name = "PropertyNameofStreet")
    String propertyNameofStreet;
    
    @Column(name = "PropertyAddressNo")
    String propertyAddressNo;
    
    @Column(name = "AreaofLand")
    String areaOfLand;
    
    @Column(name = "LandMeasurement")
    String landMeasurement;
    
    @Column(name = "NameofLicenseSurveyor")
    String nameofLicenseSurveyor;
    
    @Column(name = "EastNeighborPlotHolder")
    String eastNeighborPlotHolder;
    
    @Column(name = "WestNeighborPlotHolder")
    String westNeighborPlotHolder;
    
    @Column(name = "NorthNeighborPlotHolder")
    String northNeighborPlotHolder;
    
    @Column(name = "SouthNeighborPlotHolder")
    String southNeighborPlotHolder;
    
    @Column(name = "SurveyingMethod")
    String surveyingMethod;
    
    @Column(name = "DirectorofSurveys")
    String directorofSurveys;
    
    @Column(name = "DateSurveyed")
    String DateSurveyed;
    
    @Column(name = "BeaconNumber")
    String beaconNumber;
    
    @Column(name = "NameofCO")
    String nameofCO;
    
    @Column(name = "PhoneNoofCO")
    String phoneNoofCO;
    
    @Column(name = "DateApprovedbyCO")
    String dateApprovedbyCO;
    
    @Column(name = "DesignationofCO")
    String designationofCO;
    
    @Column(name = "NameofSLCO")
    String nameofSLCO;
    
    @Column(name = "PhoneNoOfSLCO")
    String phoneNoOfSLCO;
    
    @Column(name = "EmailAddressOfSLCO")
    String emailAddressOfSLCO;
    
    @Column(name = "DateInputtedIntoSystem")
    String dateInputtedIntoSystem;
    
    @Column(name = "InputtedBy")
    String inputtedBy;
    
//Getters and Setters
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getNationalIDNo() {
        return nationalIDNo;
    }

    public void setNationalIDNo(String nationalIDNo) {
        this.nationalIDNo = nationalIDNo;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getAddressOfOwner() {
        return addressOfOwner;
    }

    public void setAddressOfOwner(String addressOfOwner) {
        this.addressOfOwner = addressOfOwner;
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

    public String getPhoneNoofCO() {
        return phoneNoofCO;
    }

    public void setPhoneNoofCO(String phoneNoofCO) {
        this.phoneNoofCO = phoneNoofCO;
    }

    public String getDateApprovedbyCO() {
        return dateApprovedbyCO;
    }

    public void setDateApprovedbyCO(String dateApprovedbyCO) {
        this.dateApprovedbyCO = dateApprovedbyCO;
    }

    public String getDesignationofCO() {
        return designationofCO;
    }

    public void setDesignationofCO(String designationofCO) {
        this.designationofCO = designationofCO;
    }

    public String getNameofSLCO() {
        return nameofSLCO;
    }

    public void setNameofSLCO(String nameofSLCO) {
        this.nameofSLCO = nameofSLCO;
    }

    public String getPhoneNoOfSLCO() {
        return phoneNoOfSLCO;
    }

    public void setPhoneNoOfSLCO(String phoneNoOfSLCO) {
        this.phoneNoOfSLCO = phoneNoOfSLCO;
    }

    public String getEmailAddressOfSLCO() {
        return emailAddressOfSLCO;
    }

    public void setEmailAddressOfSLCO(String emailAddressOfSLCO) {
        this.emailAddressOfSLCO = emailAddressOfSLCO;
    }

    public String getDateInputtedIntoSystem() {
        return dateInputtedIntoSystem;
    }

    public void setDateInputtedIntoSystem(String dateInputtedIntoSystem) {
        this.dateInputtedIntoSystem = dateInputtedIntoSystem;
    }

    public String getInputtedBy() {
        return inputtedBy;
    }

    public void setInputtedBy(String inputtedBy) {
        this.inputtedBy = inputtedBy;
    }
   
}
