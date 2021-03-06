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
package org.sola.services.ejb.search.businesslogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import org.sola.services.common.ejbs.AbstractEJBLocal;
import org.sola.services.ejb.search.repository.entities.*;
import org.sola.services.ejb.search.spatial.QueryForNavigation;
import org.sola.services.ejb.search.spatial.QueryForPublicDisplayMap;
import org.sola.services.ejb.search.spatial.QueryForSelect;
import org.sola.services.ejb.search.spatial.ResultForNavigationInfo;
import org.sola.services.ejb.search.spatial.ResultForSelectionInfo;

/**
 * Local interface for the {@linkplain SearchEJB}
 */
@Local
public interface SearchEJBLocal extends AbstractEJBLocal {

    /**
     * See {@linkplain SearchEJB#getPropertyVerifier(java.lang.String, java.lang.String, java.lang.String)
     * SearchEJB.getPropertyVerifier}.
     */
    PropertyVerifier getPropertyVerifier(String applicationNumber, String firstPart, String lastPart);

    /**
     * See {@linkplain SearchEJB#getGenericResultList(java.lang.String, java.util.Map)
     * SearchEJB.getGenericResultList}.
     */
    GenericResult getGenericResultList(String queryName, Map params);

    /**
     * See {@linkplain SearchEJB#searchApplications(org.sola.services.ejb.search.repository.entities.ApplicationSearchParams)
     * SearchEJB.searchApplications}.
     */
    List<ApplicationSearchResult> searchApplications(ApplicationSearchParams params);

    /**
     * See {@linkplain SearchEJB#getUnassignedApplications(java.lang.String)
     * SearchEJB.getUnassignedApplications}.
     */
    List<ApplicationSearchResult> getUnassignedApplications(String locale);

    /**
     * See {@linkplain SearchEJB#getAssignedApplications(java.lang.String)
     * SearchEJB.getAssignedApplications}.
     */
    List<ApplicationSearchResult> getAssignedApplications(String locale);

    /**
     * See {@linkplain SearchEJB#getSpatialResult(org.sola.services.ejb.search.spatial.QueryForNavigation)
     * SearchEJB.getSpatialResult}.
     */
    ResultForNavigationInfo getSpatialResult(QueryForNavigation spatialQuery);

    /**
     * See {@linkplain SearchEJB#getSpatialResultForPublicDisplay(org.sola.services.ejb.search.spatial.QueryForPublicDisplayMap)
     * SearchEJB.getSpatialResult}.
     */
    ResultForNavigationInfo getSpatialResultForPublicDisplay(QueryForPublicDisplayMap spatialQuery);

    /**
     * See {@linkplain SearchEJB#getConfigMapLayerList(java.lang.String)
     * SearchEJB.getConfigMapLayerList}.
     */
    List<ConfigMapLayer> getConfigMapLayerList(String languageCode);

    /**
     * See {@linkplain SearchEJB#getCrsList()
     * SearchEJB.getCrsList}.
     */
    List<Crs> getCrsList();
        /**
     * See {@linkplain SearchEJB#getSpatialResultFromSelection(java.util.List)
     * SearchEJB.getSpatialResultFromSelection}.
     */
    List<ResultForSelectionInfo> getSpatialResultFromSelection(
            List<QueryForSelect> queriesForSelection);

    /**
     * See {@linkplain SearchEJB#getMapSettingList()
     * SearchEJB.getMapSettingList}.
     */
    HashMap<String, String> getMapSettingList();

    /**
     * See {@linkplain SearchEJB#getResultObjectFromStatement(java.lang.String, java.util.Map)
     * SearchEJB.getResultObjectFromStatement}.
     */
    HashMap getResultObjectFromStatement(String sqlStatement, Map params);

    /**
     * See {@linkplain SearchEJB#searchParties(org.sola.services.ejb.search.repository.entities.PartySearchParams)
     * SearchEJB.searchParties}.
     */
    List<PartySearchResult> searchParties(PartySearchParams searchParams);
    
    /**
     * See {@linkplain SearchEJB#searchPartiesByRole(java.lang.String)
     * SearchEJB.searchPartiesByRole}.
     */
    List<PartySearchResult> searchPartiesByRole(String roleCode);
     
    /**
     * See {@linkplain SearchEJB#searchPartiesProperty(org.sola.services.ejb.search.repository.entities.PartySearchParams)
     * SearchEJB.searchParties}.
     */
    List<PartyPropertySearchResult> searchPartiesProperty(PartySearchParams searchParams, String party_id);
    
     /**
     * See {@linkplain SearchEJB#searchPartiesProperty(org.sola.services.ejb.search.repository.entities.PartySearchParams)
     * SearchEJB.searchParties}.
     */
    List<NotifiablePartySearchResult> searchNotifiableParties(PartySearchParams searchParams, String service);

    
    /**
     * See {@linkplain SearchEJB#searchSources(org.sola.services.ejb.search.repository.entities.SourceSearchParams)
     * SearchEJB.searchSources}.
     */
    List<SourceSearchResult> searchSources(SourceSearchParams searchParams);
    
    /**
     * See {@linkplain SearchEJB#searchPowerOfAttorney(org.sola.services.ejb.search.repository.entities.PowerOfAttorneySearchParams)
     * SearchEJB.searchPowerOfAttorney}.
     */
    List<PowerOfAttorneySearchResult> searchPowerOfAttorney(PowerOfAttorneySearchParams searchParams);

    /**
     * See {@linkplain SearchEJB#getActiveUsers()
     * SearchEJB.getActiveUsers}.
     */
    List<UserSearchResult> getActiveUsers();

    /**
     * See {@linkplain SearchEJB#searchUsers(org.sola.services.ejb.search.repository.entities.UserSearchParams)
     * SearchEJB.searchUsers}.
     */
    List<UserSearchResult> searchUsers(UserSearchParams searchParams);

    /**
     * See {@linkplain SearchEJB#getApplicationLog(java.lang.String)
     * SearchEJB.getApplicationLog}.
     */
    List<ApplicationLogResult> getApplicationLog(String applicationId);

    /**
     * See {@linkplain SearchEJB#searchBr(org.sola.services.ejb.search.repository.entities.BrSearchParams, java.lang.String)
     * SearchEJB.searchBr}.
     */
    List<BrSearchResult> searchBr(BrSearchParams searchParams, String lang);

    /**
     * See {@linkplain SearchEJB#searchBaUnits(org.sola.services.ejb.search.repository.entities.BaUnitSearchParams)
     * SearchEJB.searchBaUnits}.
     */
    List<BaUnitSearchResult> searchBaUnits(BaUnitSearchParams searchParams);

    /**
     * See {@linkplain SearchEJB#getQueryListAll()
     * SearchEJB.getQueryListAll}.
     */
    List<DynamicQuery> getQueryListAll();

    /**
     * See {@linkplain SearchEJB#getSpatialSearchOptions(java.lang.String)
     * SearchEJB.getSpatialSearchOptions}.
     */
    List<SpatialSearchOption> getSpatialSearchOptions(String languageCode);

    /**
     * See {@linkplain SearchEJB#searchSpatialObjects(java.lang.String, java.lang.String)
     * SearchEJB.searchSpatialObjects}.
     */
    List<SpatialSearchResult> searchSpatialObjects(String queryName, String searchString, int srid);
    
    /**
     * See {@linkplain SearchEJB#searchRightsForExport(org.sola.services.ejb.search.repository.entities.RightsExportParams)}.
     */
    List<RightsExportResult> searchRightsForExport(RightsExportParams searchParams);
    
    /**
     * See {@linkplain SearchEJB#getExtentOfPublicDisplayMap(String)}.
     */
    byte[] getExtentOfPublicDisplayMap(String nameLastPart);
    
    /**
     * See {@linkplain SearchEJB#getMapCenterLabel(byte[])}.
     */
    String getMapCenterLabel(byte[] mapCenterPoint);
    
    /**
     * See {@linkplain SearchEJB#getPlanCadastreObjects(String)}.
     */
    List<SpatialResult> getPlanCadastreObjects(String cadastreObjectId);

    /**
     * See {@linkplain SearchEJB#transform(byte[], int)}.
     */
    byte[] transform(byte[] geom, int targetSrid);
    
    List<ApplicationSearchResult> getMyApplications(String locale);
    
    List<ApplicationSearchResult> getPlLodgedApplications(String locale);
    
    List<ApplicationSearchResult> getPlApplicationsForPlanCapturing(String locale);
            
    List<ApplicationSearchResult> getPlApplicationsForSLClearance(String locale);
    
    List<ApplicationSearchResult> getPlApplicationsForPlanningClearance(String locale);
    
    List<ApplicationSearchResult> getPlApplicationsForEnvClearance(String locale);
    
    List<ApplicationSearchResult> getPlApplicationsForCompletion(String locale);
    
    List<ApplicationSearchResult> getPlApplicationsForApproval(String locale);
    
    List<ApplicationSearchResult> getSlLodgedApplications(String locale);
    
    List<ApplicationSearchResult> getSlApplicationsForPlanCapturing(String locale);
    
    List<ApplicationSearchResult> getSlApplicationsForCompletion(String locale);
    
    List<ApplicationSearchResult> getSlApplicationsForApproval(String locale);
    
    List<ApplicationSearchResult> getPlApprovedApplications(String locale);
    
    List<ApplicationSearchResult> getSlApprovedApplications(String locale);
    
    DashboardStatistics getDashboardStatistics();
}
