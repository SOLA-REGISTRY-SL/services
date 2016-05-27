package org.sola.services.ejb.cadastre.repository.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;

/**
 * Survey plan view for reporting........
 */
@Table(name = "survey_plan_view", schema = "cadastre")
/**
 *
 * @author soladev
 */
public class SurveyPlanListReturnReport extends AbstractReadOnlyEntity {

    public static final String QUERY_PARAMETER_ID = "id";
    public static final String QUERY_WHERE_BYID = "id = #{" + QUERY_PARAMETER_ID + "}";
    /**
     * WHERE clause to return the survey plan by transaction id
     */
  //To be corrected for the sql querry

    public static final String PARAMETER_FROM = "fromDate";
    public static final String PARAMETER_TO = "toDate";

    public static final String QUERY_WHERE_SEARCHBYPARTS = "status_code= 'current' and "
            + "compare_strings(#{search_string}, name_firstpart || ' ' || name_lastpart)";

    public static final String QUERY_GETQUERY = "(dsl_date  between to_date(#{" + PARAMETER_FROM + "},'yyyy-mm-dd')  and to_date(#{" + PARAMETER_TO + "},'yyyy-mm-dd'))";
            

    //Constructor
    public SurveyPlanListReturnReport() {
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

    @Column(name = "address")
    private String address;

    @Column(name = "land_type")
    private String landtype;

    @Column(name = "parcel_area")
    private String areaOfLand;

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

    @Column(name = "survey_date")
    private Date DateSurveyed;
    
    @Column(name = "license_surveyor")
    private String nameofLicenseSurveyor;

    @Column(name = "survey_type_code")
    private String surveyType;
    
    @Column(name = "ref_survey")
    private String rfSurvey;
    
    @Column(name = "survey_number")
    private String surveyNumber;
    
    @Column(name = "dsl_date")
    private Date dslDate;
        
//   
    public String getId() {
        return id;
    }

    public String getLSNo() {
        return LSNo;
    }

    public String getNameofOwner() {
        return nameofOwner;
    }

    
    public String getLandtype() {
        return landtype;
    }

    public String getAreaOfLand() {
        return areaOfLand;
    }

   
    public String getNameofLicenseSurveyor() {
        return nameofLicenseSurveyor;
    }

    public String getEastNeighborPlotHolder() {
        return eastNeighborPlotHolder;
    }

    public String getWestNeighborPlotHolder() {
        return westNeighborPlotHolder;
    }

    public String getNorthNeighborPlotHolder() {
        return northNeighborPlotHolder;
    }

    public String getSouthNeighborPlotHolder() {
        return southNeighborPlotHolder;
    }

    public String getSurveyingMethod() {
        return surveyingMethod;
    }

 
    public Date getDateSurveyed() {
        return DateSurveyed;
    }
   
    public String getAddress() {
        return address;
    }

    public String getSurveyType() {
        return surveyType;
    }

    public String getRfSurvey() {
        return rfSurvey;
    }

    public String getSurveyNumber() {
        return surveyNumber;
    }

    public Date getDslDate() {
        return dslDate;
    }
    
    
}
