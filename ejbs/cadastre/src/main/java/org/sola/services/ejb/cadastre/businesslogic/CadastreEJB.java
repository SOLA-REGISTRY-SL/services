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
package org.sola.services.ejb.cadastre.businesslogic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.sola.common.RolesConstants;
import org.sola.common.SOLAException;
import org.sola.common.messaging.ServiceMessage;
import org.sola.services.common.br.ValidationResult;
import org.sola.services.common.ejbs.AbstractEJB;
import org.sola.services.common.faults.SOLAValidationException;
import org.sola.services.common.repository.CommonSqlProvider;
import org.sola.services.ejb.cadastre.repository.entities.*;
import org.sola.services.ejb.system.businesslogic.SystemEJBLocal;
import org.sola.services.ejb.system.repository.entities.BrValidation;

/**
 * EJB to manage data in the cadastre schema. Supports retrieving and saving of
 * cadastre objects. Also provides methods for retrieving reference codes from
 * the administrative schema.
 */
@Stateless
@EJB(name = "java:app/CadastreEJBLocal", beanInterface = CadastreEJBLocal.class)
public class CadastreEJB extends AbstractEJB implements CadastreEJBLocal {

    @EJB
    private SystemEJBLocal systemEJB;

    /**
     * Retrieves all cadastre.land_use_type code values.
     *
     * @param languageCode The language code to use for localization of display
     * values.
     */
    @Override
    public List<LandUseType> getLandUseTypes(String languageCode) {
        return getRepository().getCodeList(LandUseType.class, languageCode);
    }

    /**
     * Retrieves all cadastre.cadastre_object_type code values.
     *
     * @param languageCode The language code to use for localization of display
     * values.
     */
    @Override
    public List<CadastreObjectType> getCadastreObjectTypes(String languageCode) {
        return getRepository().getCodeList(CadastreObjectType.class, languageCode);
    }

    /**
     * Retrieves a cadastre object using the specified identifier.
     *
     * @param id The identifier of the cadastre object to retrieve.
     */
    @Override
    public CadastreObject getCadastreObject(String id) {
        return getRepository().getEntity(CadastreObject.class, id);
    }

    /**
     * Retrieves a list of cadastre object matching the list of ids provided.
     *
     * @param cadastreObjIds A list of cadaster object ids to use for retrieval.
     */
    @Override
    public List<CadastreObject> getCadastreObjects(List<String> cadastreObjIds) {
        return getRepository().getEntityListByIds(CadastreObject.class, cadastreObjIds);
    }
    
    /**
     * Returns a maximum of 30 cadastre objects that have a name first part
     * and/or name last part that matches the specified search string. This
     * method supports partial matches and is case insensitive.
     *
     * @param searchString The search string to use
     * @return The list of cadastre objects matching the search string
     */
    @Override
    public List<CadastreObject> getCadastreObjectByParts(String searchString) {
        Integer numberOfMaxRecordsReturned = 30;
        HashMap params = new HashMap();
        // Replace / and \ with space to improve the search
        searchString = searchString.replaceAll("\\\\|\\/", " ");
        params.put("search_string", searchString);
        params.put(CommonSqlProvider.PARAM_LIMIT_PART, numberOfMaxRecordsReturned);
        params.put(CommonSqlProvider.PARAM_ORDER_BY_PART, CadastreObject.QUERY_ORDER_BY_SEARCHBYPARTS);
        return getRepository().getEntityList(CadastreObject.class,
                CadastreObject.QUERY_WHERE_SEARCHBYPARTS, params);
    }

    /**
     * Returns a maximum of 30 cadastre objects with current and pending status
     * that have a name first part and/or name last part that matches the
     * specified search string. This method supports partial matches and is case
     * insensitive.
     *
     * @param searchString The search string to use
     * @return The list of cadastre objects matching the search string
     */
    @Override
    public List<CadastreObject> getCadastreObjectByAllParts(String searchString) {
        Integer numberOfMaxRecordsReturned = 30;
        HashMap params = new HashMap();
        params.put("search_string", searchString);
        params.put(CommonSqlProvider.PARAM_LIMIT_PART, numberOfMaxRecordsReturned);
        params.put(CommonSqlProvider.PARAM_ORDER_BY_PART, CadastreObject.QUERY_ORDER_BY_SEARCHBYPARTS);
        return getRepository().getEntityList(CadastreObject.class,
                CadastreObject.QUERY_WHERE_SEARCHBYALLPARTS, params);
    }

    /**
     * Returns the cadastre object that is located at the point specified or
     * null if there is no cadastre object at that location. Uses the PostGIS
     * ST_Intersects function to perform the comparison.
     *
     * @param x The x ordinate of the location
     * @param y The y ordinate of the location
     * @param srid The SRID identifying the coordinate system for the x,y
     * coordinate. Must match the SRID used by SOLA.
     */
    @Override
    public CadastreObject getCadastreObjectByPoint(
            double x, double y, int srid, String typeCode) {
        HashMap params = new HashMap();
        params.put("x", x);
        params.put("y", y);
        params.put("srid", srid);
        params.put("type_code", typeCode);
        return getRepository().getEntity(
                CadastreObject.class, CadastreObject.QUERY_WHERE_SEARCHBYPOINT, params);
    }

    /**
     * Can be used to create a new cadastre object or save any updates to the
     * details of an existing cadastre object.
     *
     * @param cadastreObject The cadastre object to create/save.
     * @return The cadastre object after the save is completed.
     */
    @Override
    public CadastreObject saveCadastreObject(CadastreObject cadastreObject) {
        return getRepository().saveEntity(cadastreObject);
    }

    /**
     * Retrieves all cadastre objects linked to the specified BA Unit.
     *
     * @param baUnitId Identifier of the BA Unit
     */
    @Override
    public List<CadastreObject> getCadastreObjectsByBaUnit(String baUnitId) {
        HashMap params = new HashMap();
        params.put("ba_unit_id", baUnitId);
        return getRepository().getEntityList(CadastreObject.class,
                CadastreObject.QUERY_WHERE_SEARCHBYBAUNIT, params);
    }

    /**
     * Retrieves all cadastre objects linked to the specified Service through
     * transaction.
     *
     * @param serviceId Identifier of the Service
     */
    @Override
    public List<CadastreObject> getCadastreObjectsByService(String serviceId) {
        HashMap params = new HashMap();
        params.put("service_id", serviceId);
        return getRepository().getEntityList(CadastreObject.class,
                CadastreObject.QUERY_WHERE_SEARCHBYSERVICE, params);
    }

    /**
     * Updates the status of cadastre objects associated with the specified
     * transaction. The filter parameter can be used to indicate the where
     * clause to apply.
     *
     * @param transactionId Identifier of the transaction associated to the
     * cadastre objects to be updated
     * @param filter The where clause to use when retrieving the cadastre
     * objects. Must be
     * {@linkplain CadastreObjectStatusChanger#QUERY_WHERE_SEARCHBYTRANSACTION_PENDING}
     * or
     * {@linkplain CadastreObjectStatusChanger#QUERY_WHERE_SEARCHBYTRANSACTION_TARGET}.
     * @param statusCode The status code to set on the selected cadastre
     * objects.
     */
    @Override
    @RolesAllowed({RolesConstants.APPLICATION_APPROVE, RolesConstants.APPLICATION_SERVICE_COMPLETE})
    public void ChangeStatusOfCadastreObjects(
            String transactionId, String filter, String statusCode) {
        if (!this.isInRole(RolesConstants.CADASTRE_PARCEL_SAVE)) {
            // Along with one of the above 2 roles, the user must also have the Save Parcel role 
            // to run this method. 
            throw new SOLAException(ServiceMessage.EXCEPTION_INSUFFICIENT_RIGHTS);
        }
        HashMap params = new HashMap();
        params.put("transaction_id", transactionId);
        List<CadastreObjectStatusChanger> involvedCoList =
                getRepository().getEntityList(CadastreObjectStatusChanger.class, filter, params);
        for (CadastreObjectStatusChanger involvedCo : involvedCoList) {
            involvedCo.setStatusCode(statusCode);
            getRepository().saveEntity(involvedCo);
        }
    }

    /**
     * Retrieves the list of Cadastre Object Targets associated with the
     * transaction. <p>Cadastre Object Targets are used to link the cadastre
     * object to new transactions that may occur on the cadastre object after it
     * has been initially created - for example the transaction to extinguish
     * the cadastre object.</p>
     *
     * @param transactionId The identifier of the transaction
     */
    @Override
    public List<CadastreObjectTarget> getCadastreObjectTargetsByTransaction(String transactionId) {
        Map params = new HashMap<String, Object>();
        params.put(
                CommonSqlProvider.PARAM_WHERE_PART,
                CadastreObjectTarget.QUERY_WHERE_SEARCHBYTRANSACTION);
        params.put("transaction_id", transactionId);
        return getRepository().getEntityList(CadastreObjectTarget.class, params);
    }

    /**
     * Retrieves all Survey Points associated with the transaction.
     *
     * @param transactionId The identifier of the transaction
     */
    @Override
    public List<SurveyPoint> getSurveyPointsByTransaction(String transactionId) {
        Map params = new HashMap<String, Object>();
        params.put(
                CommonSqlProvider.PARAM_WHERE_PART,
                SurveyPoint.QUERY_WHERE_SEARCHBYTRANSACTION);
        params.put("transaction_id", transactionId);
        return getRepository().getEntityList(SurveyPoint.class, params);
    }

    /**
     * Retrieves all Cadastre Objects created by the transaction.
     *
     * @param transactionId The identifier of the transaction
     */
    @Override
    public List<CadastreObject> getCadastreObjectsByTransaction(String transactionId) {
        Map params = new HashMap<String, Object>();
        params.put(
                CommonSqlProvider.PARAM_WHERE_PART,
                CadastreObject.QUERY_WHERE_SEARCHBYTRANSACTION);
        params.put("transaction_id", transactionId);
        return getRepository().getEntityList(CadastreObject.class, params);

    }

    /**
     * Retrieves all node points from the underlying cadastre objects that
     * intersect the specified bounding box coordinates. All of the node points
     * within the bounding box are used to create a single geometry -
     * {@linkplain CadastreObjectNode#geom}. The cadastre objects used as the
     * source of the node points are also captured in the
     * {@linkplain CadastreObjectNode#cadastreObjectList}.
     *
     * @param xMin The xMin ordinate of the bounding box
     * @param yMin The yMin ordinate of the bounding box
     * @param xMax The xMax ordinate of the bounding box
     * @param yMax The yMax ordinate of the bounding box
     * @param srid The SRID to use to create the bounding box. Must be the same
     * SRID as the one used by the cadastre_object table.
     * @return The CadastreObjectNode representing all node points within the
     * bounding box as well as the list of cadastre objects used to obtain the
     * node points.
     */
    @Override
    public CadastreObjectNode getCadastreObjectNode(
            double xMin, double yMin, double xMax, double yMax, int srid,
            String cadastreObjectType) {
        Map params = new HashMap<String, Object>();
        params.put(CommonSqlProvider.PARAM_FROM_PART,
                CadastreObjectNode.QUERY_GET_BY_RECTANGLE_FROM_PART);
        params.put(CommonSqlProvider.PARAM_WHERE_PART,
                CadastreObjectNode.QUERY_GET_BY_RECTANGLE_WHERE_PART);
        params.put(CommonSqlProvider.PARAM_LIMIT_PART, 1);
        params.put("minx", xMin);
        params.put("miny", yMin);
        params.put("maxx", xMax);
        params.put("maxy", yMax);
        params.put("srid", srid);
        params.put("cadastre_object_type", cadastreObjectType);
        CadastreObjectNode cadastreObjectNode = getRepository().getEntity(
                CadastreObjectNode.class, params);
        if (cadastreObjectNode != null) {
            params.clear();
            params.put("geom", cadastreObjectNode.getGeom());
            params.put("type_code", cadastreObjectType);
            params.put("srid", srid);
            cadastreObjectNode.setCadastreObjectList(getRepository().getEntityList(
                    CadastreObject.class, CadastreObject.QUERY_WHERE_SEARCHBYGEOM, params));
        }
        return cadastreObjectNode;

    }

    /**
     * Unknown
     *
     * @param xMin The xMin ordinate of the bounding box
     * @param yMin The yMin ordinate of the bounding box
     * @param xMax The xMax ordinate of the bounding box
     * @param yMax The yMax ordinate of the bounding box
     * @param srid The SRID to use to create the bounding box. Must be the same
     * SRID as the one used by the cadastre_object table.
     */
    @Override
    public CadastreObjectNode getCadastreObjectNodePotential(
            double xMin, double yMin, double xMax, double yMax, int srid,
            String cadastreObjectType) {
        Map params = new HashMap<String, Object>();
        params.put(CommonSqlProvider.PARAM_FROM_PART,
                CadastreObjectNode.QUERY_GET_BY_RECTANGLE_POTENTIAL_FROM_PART);
        params.put(CommonSqlProvider.PARAM_LIMIT_PART, 1);
        params.put("minx", xMin);
        params.put("miny", yMin);
        params.put("maxx", xMax);
        params.put("maxy", yMax);
        params.put("srid", srid);
        params.put("cadastre_object_type", cadastreObjectType);
        CadastreObjectNode cadastreObjectNode = getRepository().getEntity(
                CadastreObjectNode.class, params);
        if (cadastreObjectNode != null) {
            params.clear();
            params.put("geom", cadastreObjectNode.getGeom());
            params.put("type_code", cadastreObjectType);
            params.put("srid", srid);
            cadastreObjectNode.setCadastreObjectList(getRepository().getEntityList(
                    CadastreObject.class, CadastreObject.QUERY_WHERE_SEARCHBYGEOM, params));
        }
        return cadastreObjectNode;
    }

    /**
     * Retrieves all Cadastre Object Node Targets associated to the transaction.
     * <p>A Cadastre Object Node Target</p> is used to identify the nodes that
     * have been added, moved or removed as part of a redefinition transaction.
     * </p>
     *
     * @param transactionId The identifier of the transaction
     */
    @Override
    public List<CadastreObjectNodeTarget> getCadastreObjectNodeTargetsByTransaction(
            String transactionId) {
        Map params = new HashMap<String, Object>();
        params.put(
                CommonSqlProvider.PARAM_WHERE_PART,
                CadastreObjectNodeTarget.QUERY_WHERE_SEARCHBYTRANSACTION);
        params.put("transaction_id", transactionId);
        return getRepository().getEntityList(CadastreObjectNodeTarget.class, params);
    }

    /**
     * Retrieves the cadastre objects that have been associated with a cadastre
     * redefinition transaction.
     *
     * @param transactionId The identifier of the transaction
     */
    @Override
    public List<CadastreObjectTargetRedefinition> getCadastreObjectRedefinitionTargetsByTransaction(
            String transactionId) {
        Map params = new HashMap<String, Object>();
        params.put(
                CommonSqlProvider.PARAM_WHERE_PART,
                CadastreObjectTarget.QUERY_WHERE_SEARCHBYTRANSACTION);
        params.put("transaction_id", transactionId);
        return getRepository().getEntityList(CadastreObjectTargetRedefinition.class, params);
    }

    /**
     * Approves the changes to cadastre objects as a result of a cadastre
     * redefinition.
     *
     * @param transactionId The identifier of the transaction
     */
    @Override
    @RolesAllowed({RolesConstants.APPLICATION_APPROVE, RolesConstants.APPLICATION_SERVICE_COMPLETE})
    public void approveCadastreRedefinition(String transactionId) {
        List<CadastreObjectTargetRedefinition> targetObjectList =
                this.getCadastreObjectRedefinitionTargetsByTransaction(transactionId);

        if (!this.isInRole(RolesConstants.CADASTRE_PARCEL_SAVE)) {
            // Along with one of the above 2 roles, the user must also have the Save Parcel role 
            // to run this method. 
            throw new SOLAException(ServiceMessage.EXCEPTION_INSUFFICIENT_RIGHTS);
        }
        
        for (CadastreObjectTargetRedefinition targetObject : targetObjectList) {
            CadastreObjectStatusChanger cadastreObject =
                    this.getRepository().getEntity(CadastreObjectStatusChanger.class,
                    targetObject.getCadastreObjectId());
            cadastreObject.setGeomPolygon(targetObject.getGeomPolygon());
            cadastreObject.setTransactionId(transactionId);
            cadastreObject.setApprovalDatetime(null);
            this.saveEntity(cadastreObject);
        }
    }

    /**
     * Retrieves the temporary cadastre objects that have been associated with a
     * bulk operation transaction.
     *
     * @param transactionId The identifier of the transaction
     */
    @Override
    public List<SpatialUnitTemporary> getSpatialUnitTemporaryListByTransaction(String transactionId) {
        Map params = new HashMap<String, Object>();
        params.put(
                CommonSqlProvider.PARAM_WHERE_PART,
                SpatialUnitTemporary.QUERY_WHERE_SEARCHBYTRANSACTION);
        params.put("transaction_id", transactionId);
        return getRepository().getEntityList(SpatialUnitTemporary.class, params);
    }

    /**
     * Locates cadastre object's area size
     *
     *
     *
     * @param colist the list of cadastre object
     * @return The total area size
     */
    @Override
    public SpatialValueArea getSpatialValueArea(String colist) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(CommonSqlProvider.PARAM_LIMIT_PART, 1);
        params.put(SpatialValueArea.QUERY_PARAMETER_COLIST, colist);
        return getRepository().getEntity(SpatialValueArea.class, params);
    }

    @Override
    public NewCadastreObjectIdentifier getNewCadastreObjectIdentifier(
            byte[] geom, String cadastreObjectType) {
        String brToGetLastPart = "generate-cadastre-object-lastpart";
        String brToGetFirstPart = "generate-cadastre-object-firstpart";
        HashMap<String, Serializable> params = new HashMap<String, Serializable>();
        params.put("geom", geom);
        params.put("cadastre_object_type", cadastreObjectType);
        String lastPart = systemEJB.checkRuleGetResultSingle(brToGetLastPart, params).getValue().toString();
        params = new HashMap<String, Serializable>();
        params.put("last_part", lastPart);
        params.put("cadastre_object_type", cadastreObjectType);
        String firstPart = systemEJB.checkRuleGetResultSingle(brToGetFirstPart, null).getValue().toString();
        NewCadastreObjectIdentifier identifier = new NewCadastreObjectIdentifier();
        identifier.setFirstPart(firstPart);
        identifier.setLastPart(lastPart);
        return identifier;
    }

    /**
     * Saves the changes in the spatial unit group.
     *
     * @param items
     * @param languageCode
     */
    @Override
    public void saveSpatialUnitGroups(List<SpatialUnitGroup> items, String languageCode) {
        if (items.isEmpty()) {
            return;
        }
        for (SpatialUnitGroup item : items) {
            getRepository().saveEntity(item);
        }
        //Check afterwards if any condition is brokken by using the BR mechanism
        //Retrieve BRs that has to be checked
        List<BrValidation> brList = systemEJB.getBrForSpatialUnitGroupTransaction();
        List<ValidationResult> validationResults =
                systemEJB.checkRulesGetValidation(brList, languageCode, null);

        if (!systemEJB.validationSucceeded(validationResults)) {
            throw new SOLAValidationException(validationResults);
        }

    }

    /**
     * Gets the list of spatial unit groups that intersect with the
     * filteringGeometry.
     *
     * @param filteringGeometry The filtering geometry
     * @param hierarchyLevel The hierarchy level of the data
     * @param srid The srid
     * @return
     */
    @Override
    public List<SpatialUnitGroup> getSpatialUnitGroups(
            byte[] filteringGeometry, Integer hierarchyLevel, Integer srid) {
        HashMap<String, Serializable> params = new HashMap<String, Serializable>();
        params.put("filtering_geometry", filteringGeometry);
        params.put("srid", srid);
        params.put("hierarchy_level", hierarchyLevel);

        return getRepository().getEntityList(
                SpatialUnitGroup.class, SpatialUnitGroup.WHERE_CONDITION, params);
    }

    /**
     * Retrieves a list of spatial unit groups matching the list of ids
     * provided.
     *
     * @param ids A list of spatial unit group ids to use for retrieval.
     */
    @Override
    public List<SpatialUnitGroup> getSpatialUnitGroupsByIds(List<String> ids) {
        return getRepository().getEntityListByIds(SpatialUnitGroup.class, ids);
    }

    /**
     * Retrieves all cadastre.cadastre_object_type code values.
     *
     * @param languageCode The language code to use for localization of display
     * values.
     */
    @Override
    public List<HierarchyLevel> getHierarchyLevels(String languageCode) {
        return getRepository().getCodeList(HierarchyLevel.class, languageCode);
    }

    /**
     * Retrieves cadastre.level records that are editable.
     *
     * @param languageCode The language code to use for localization of display
     * values.
     */
    @Override
    public List<Level> getLevels(String languageCode) {
        Map params = new HashMap<String, Object>();
        params.put(CommonSqlProvider.PARAM_LANGUAGE_CODE, languageCode);
        return getRepository().getEntityList(Level.class, Level.WHERE_CONDITION, params);
    }
    
    /**
     * Gets the list of spatial units that intersect with the
     * filteringGeometry.
     *
     * @param filteringGeometry The filtering geometry
     * @param hierarchyLevel The level id of the data
     * @param srid The srid
     * @return
     */
    @Override
    public List<SpatialUnit> getSpatialUnits(
            byte[] filteringGeometry, String levelId, Integer srid) {
        HashMap<String, Serializable> params = new HashMap<String, Serializable>();
        params.put("filtering_geometry", filteringGeometry);
        params.put("srid", srid);
        params.put("level_id", levelId);

        return getRepository().getEntityList(
                SpatialUnit.class, SpatialUnit.WHERE_CONDITION, params);
    }

    /**
     * Saves the changes in the spatial unit.
     *
     * @param items
     * @param languageCode
     */
    @Override
    public void saveSpatialUnits(List<SpatialUnit> items, String languageCode) {
        if (items.isEmpty()) {
            return;
        }
        for (SpatialUnit item : items) {
            getRepository().saveEntity(item);
        }
    }

    /**
     * Retrieves a list of spatial units matching the list of ids
     * provided.
     *
     * @param ids A list of spatial unit ids to use for retrieval.
     */
    @Override
    public List<SpatialUnit> getSpatialUnitsByIds(List<String> ids) {
        return getRepository().getEntityListByIds(SpatialUnit.class, ids);
    }
    
    //SAVE SURVEY PLAN DETAILS
     /**
     * Saves the changes in the Survey Plan.
     *
     * @param items
     * @param languageCode
     */
    @Override
    public void saveSurveyPlan(List<SurveyPlan> items, String languageCode) {
        if (items.isEmpty()) {
            return;
        }
        for (SurveyPlan item : items) {
            getRepository().saveEntity(item);
        }
    }
    
     /**
     * Retrieves a list of Survey Plans matching the list of ids
     * provided.
     *
     * @param ids A list of survey Plan ids to use for retrieval or lsNo.
     */
    @Override
    public List<SurveyPlan> getSurveyPlanByIds(List<String> ids) {
        return getRepository().getEntityListByIds(SurveyPlan.class, ids);
    }
   
      /**
     * Retrieves a list of CordinateSystem Types matching the list of ids
     * provided.
     *
     * @param ids A list of CordinateSystem Type ids to use for retrieval.
     */
    @Override
    public List<CordinateSystemType> getCordinateTypesByIds(List<String> ids) {
        return getRepository().getEntityListByIds(CordinateSystemType.class, ids);
    }
    
     @Override
    public List<CordinateSystemType> getCordniateSystemTypes(String languageCode) {
        return getRepository().getCodeList(CordinateSystemType.class, languageCode);
    }
    
    //SurveyingMethodType
     @Override
    public List<SurveyingMethodType> getSurveyingMethodTypesByIds(List<String> ids) {
        return getRepository().getEntityListByIds(SurveyingMethodType.class, ids);
    }
    
     @Override
    public List<SurveyingMethodType> getSurveyingMethodTypes(String languageCode) {
        return getRepository().getCodeList(SurveyingMethodType.class, languageCode);
    }
    
    //CheifdomType
     @Override
    public List<ChiefdomType> getChiefdomTypesByIds(List<String> ids) {
        return getRepository().getEntityListByIds(ChiefdomType.class, ids);
    }
    
     @Override
    public List<ChiefdomType> getChiefdomTypes(String languageCode) {
        return getRepository().getCodeList(ChiefdomType.class, languageCode);
    }
     
    //LandType
     @Override
    public List<LandType> getLandTypesByIds(List<String> ids) {
        return getRepository().getEntityListByIds(LandType.class, ids);
    }
    
     @Override
    public List<LandType> getLandTypes(String languageCode) {
        return getRepository().getCodeList(LandType.class, languageCode);
    }
    
    
    //SurveyPlanView
    /*@Override
    public List<SurveyPlanListReturnReport> getSurveyPlanListReturnReport(String languageCode) {
        Map params = new HashMap<String, Object>();
        params.put(CommonSqlProvider.PARAM_LANGUAGE_CODE, languageCode);
        return getRepository().getEntityList(SurveyPlanListReturnReport.class,
                SurveyPlanListReturnReport.QUERY_WHERE_SEARCHBYPARTS, params);
    }
    */
    
    //
    @Override
    @RolesAllowed(RolesConstants.CADASTRE_SURVEY_PLAN_LIST)
    public List<SurveyPlanListReturnReport> getSurveyPlanListReturnReport(String searchString, String languageCode) {

       // this.validatePublicDisplay(searchString, languageCode); //Having issues with this method for validation
        HashMap params = new HashMap();
        params.put("search_string", searchString);
        return getRepository().getEntityList(SurveyPlanListReturnReport.class,
                SurveyPlanListReturnReport.QUERY_WHERE_SEARCHBYPARTS, params);
    }
}

