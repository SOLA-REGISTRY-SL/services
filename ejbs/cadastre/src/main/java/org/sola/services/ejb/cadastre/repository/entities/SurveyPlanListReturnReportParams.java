package org.sola.services.ejb.cadastre.repository.entities;

import java.util.Date;
import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;

/**
 *
 * @author soladev
 */
public class SurveyPlanListReturnReportParams extends AbstractReadOnlyEntity {

    private Date fromDate;
    private Date toDate;
   

    public SurveyPlanListReturnReportParams() {
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
