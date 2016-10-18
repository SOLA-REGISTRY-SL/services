package org.sola.services.ejb.search.repository.entities;

import javax.persistence.Column;
import javax.persistence.Table;
import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;

@Table(name = "application", schema = "application")
public class DashboardStatistics extends AbstractReadOnlyEntity {

    public static final String QUERY_PARAM_USER_NAME = "userName";
    public static final String QUERY = "SELECT "
            + "COUNT(DISTINCT (CASE WHEN u.username IS NOT NULL AND a.status_code in ('lodged', 'approved', 'to-be-transferred') THEN a.id ELSE null END)) AS assigned_all, "
            + "COUNT(DISTINCT (CASE WHEN u.username IS NULL AND a.status_code in ('lodged', 'approved', 'to-be-transferred') THEN a.id ELSE null END)) AS unassigned_all, "
            + "COUNT(DISTINCT (CASE WHEN u.username = #{" + QUERY_PARAM_USER_NAME + "} AND a.status_code in ('lodged', 'approved', 'to-be-transferred') THEN a.id ELSE null END)) AS my_apps, "
            + "COUNT(DISTINCT (CASE WHEN a.status_code = 'lodged' AND s.request_type_code in ('newParcel', 'existingParcel') AND s.status_code = 'lodged' THEN a.id ELSE null END)) AS pl_lodged, "
            + "COUNT(DISTINCT (CASE WHEN a.status_code = 'lodged' AND s.request_type_code in ('newParcelSL', 'existingParcelSL') AND s.status_code = 'lodged' THEN a.id ELSE null END)) AS sl_lodged, "
            + "COUNT(DISTINCT (CASE WHEN a.status_code = 'lodged' AND s.request_type_code in ('newParcel', 'existingParcel') AND s.status_code in ('lodged','pending') AND co.id IS NULL THEN a.id ELSE null END)) AS pl_for_capture, "
            + "COUNT(DISTINCT (CASE WHEN a.status_code = 'lodged' AND s.request_type_code in ('newParcelSL', 'existingParcelSL') AND s.status_code in ('lodged','pending') AND co.id IS NULL THEN a.id ELSE null END)) AS sl_for_capture, "
            + "COUNT(DISTINCT (CASE WHEN a.status_code = 'lodged' AND s.request_type_code in ('newParcel', 'existingParcel') AND s.application_id NOT IN (SELECT application_id from application.service where status_code IN ('lodged', 'pending')) THEN a.id ELSE null END)) AS pl_for_approval, "
            + "COUNT(DISTINCT (CASE WHEN a.status_code = 'lodged' AND s.request_type_code in ('newParcelSL', 'existingParcelSL') AND s.application_id NOT IN (SELECT application_id from application.service where status_code IN ('lodged', 'pending')) THEN a.id ELSE null END)) AS sl_for_approval, "
            + "COUNT(DISTINCT (CASE WHEN a.status_code = 'lodged' AND s.request_type_code in ('newParcel', 'existingParcel') AND s.status_code in ('lodged','pending') AND co.state_land_clearance = 'f' THEN a.id ELSE null END)) AS pl_for_state_clearance, "
            + "COUNT(DISTINCT (CASE WHEN a.status_code = 'lodged' AND s.request_type_code in ('newParcel', 'existingParcel') AND s.status_code in ('lodged','pending') AND co.state_land_clearance = 't' AND co.planning_clearance = 'f' THEN a.id ELSE null END)) AS pl_for_planning_clearance, "
            + "COUNT(DISTINCT (CASE WHEN a.status_code = 'lodged' AND s.request_type_code in ('newParcel', 'existingParcel') AND s.status_code in ('lodged','pending') AND co.state_land_clearance = 't' AND co.environment_clearance = 'f' THEN a.id ELSE null END)) AS pl_for_env_clearance, "
            + "COUNT(DISTINCT (CASE WHEN a.status_code = 'lodged' AND s.request_type_code in ('newParcel', 'existingParcel') AND s.status_code in ('lodged','pending') AND co.state_land_clearance = 't' THEN a.id ELSE null END)) AS pl_for_completion, "
            + "COUNT(DISTINCT (CASE WHEN a.status_code = 'lodged' AND s.request_type_code in ('newParcelSL', 'existingParcelSL') AND s.status_code in ('lodged','pending') AND co.id IS NOT NULL THEN a.id ELSE null END)) AS sl_for_completion, "
            + "COUNT(DISTINCT (CASE WHEN a.status_code = 'approved' AND s.request_type_code in ('newParcelSL', 'existingParcelSL') THEN a.id ELSE null END)) AS sl_approved, "
            + "COUNT(DISTINCT (CASE WHEN a.status_code = 'approved' AND s.request_type_code in ('newParcel', 'existingParcel') THEN a.id ELSE null END)) AS pl_approved "
            + "FROM application.application a  "
            + "	INNER JOIN application.application_status_type ast on a.status_code = ast.code "
            + "	INNER JOIN application.service s ON a.id = s.application_id "
            + "	LEFT JOIN (transaction.transaction t INNER JOIN cadastre.cadastre_object co ON t.id = co.transaction_id) "
            + "	ON s.id = t.from_service_id "
            + "	LEFT JOIN system.appuser u ON a.assignee_id = u.id "
            + "WHERE a.status_code IN ('lodged', 'to-be-transferred', 'approved') ";
    
    @Column(name = "assigned_all")
    private long assignedAll;
    @Column(name = "unassigned_all")
    private long unassignedAll;
    @Column(name = "my_apps")
    private long myApps;
    @Column(name = "pl_lodged")
    private long plLodged;
    @Column(name = "sl_lodged")
    private long slLodged;
    @Column(name = "pl_for_capture")
    private long plForCapture;
    @Column(name = "sl_for_capture")
    private long slForCapture;
    @Column(name = "pl_for_approval")
    private long plForApproval;
    @Column(name = "sl_for_approval")
    private long slForApproval;
    @Column(name = "pl_for_state_clearance")
    private long plForStateClearance;
    @Column(name = "pl_for_planning_clearance")
    private long plForPlanningClearance;
    @Column(name = "pl_for_env_clearance")
    private long plForEnvClearance;
    @Column(name = "pl_for_completion")
    private long plForCompletion;
    @Column(name = "sl_for_completion")
    private long slForCompletion;
    @Column(name = "pl_approved")
    private long plApproved;
    @Column(name = "sl_approved")
    private long slApproved;
    
    public DashboardStatistics(){
        super();
    }

    public long getAssignedAll() {
        return assignedAll;
    }

    public void setAssignedAll(long assignedAll) {
        this.assignedAll = assignedAll;
    }

    public long getUnassignedAll() {
        return unassignedAll;
    }

    public void setUnassignedAll(long unassignedAll) {
        this.unassignedAll = unassignedAll;
    }

    public long getMyApps() {
        return myApps;
    }

    public void setMyApps(long myApps) {
        this.myApps = myApps;
    }

    public long getPlLodged() {
        return plLodged;
    }

    public void setPlLodged(long plLodged) {
        this.plLodged = plLodged;
    }

    public long getSlLodged() {
        return slLodged;
    }

    public void setSlLodged(long slLodged) {
        this.slLodged = slLodged;
    }

    public long getPlForCapture() {
        return plForCapture;
    }

    public void setPlForCapture(long plForCapture) {
        this.plForCapture = plForCapture;
    }

    public long getSlForCapture() {
        return slForCapture;
    }

    public void setSlForCapture(long slForCapture) {
        this.slForCapture = slForCapture;
    }

    public long getPlForApproval() {
        return plForApproval;
    }

    public void setPlForApproval(long plForApproval) {
        this.plForApproval = plForApproval;
    }

    public long getSlForApproval() {
        return slForApproval;
    }

    public void setSlForApproval(long slForApproval) {
        this.slForApproval = slForApproval;
    }

    public long getPlForStateClearance() {
        return plForStateClearance;
    }

    public void setPlForStateClearance(long plForStateClearance) {
        this.plForStateClearance = plForStateClearance;
    }

    public long getPlForPlanningClearance() {
        return plForPlanningClearance;
    }

    public void setPlForPlanningClearance(long plForPlanningClearance) {
        this.plForPlanningClearance = plForPlanningClearance;
    }

    public long getPlForEnvClearance() {
        return plForEnvClearance;
    }

    public void setPlForEnvClearance(long plForEnvClearance) {
        this.plForEnvClearance = plForEnvClearance;
    }

    public long getPlForCompletion() {
        return plForCompletion;
    }

    public void setPlForCompletion(long plForCompletion) {
        this.plForCompletion = plForCompletion;
    }

    public long getSlForCompletion() {
        return slForCompletion;
    }

    public void setSlForCompletion(long slForCompletion) {
        this.slForCompletion = slForCompletion;
    }

    public long getPlApproved() {
        return plApproved;
    }

    public void setPlApproved(long plApproved) {
        this.plApproved = plApproved;
    }

    public long getSlApproved() {
        return slApproved;
    }

    public void setSlApproved(long slApproved) {
        this.slApproved = slApproved;
    }
}
