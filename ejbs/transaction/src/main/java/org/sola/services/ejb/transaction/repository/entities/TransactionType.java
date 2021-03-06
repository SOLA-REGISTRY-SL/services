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
package org.sola.services.ejb.transaction.repository.entities;

public class TransactionType {
    public static final String CADASTRE_CHANGE = "cadastreChange"; 
    public static final String MAP_EXISTINGPARCEL = "mapExistingParcel";
    public static final String BULK_OPERATION_SPATIAL = "bulkOperationSpatial"; 
    public static final String BULK_OPERATION_SOURCE = "bulkOperationSource"; 
    public static final String REDEFINE_CADASTRE = "redefineCadastre"; 
    public static final String NEW_DIGITAL_TITLE = "newDigitalTitle"; 
    public static final String NEW_DIGITAL_PROPERTY = "newDigitalProperty"; 
    public static final String NEW_APARTMENT = "newApartment"; 
    public static final String NEW_STATE = "newState"; 
    public static final String NEW_FREEHOLD = "newFreehold"; 
    public static final String NEW_PARCEL = "newParcel"; 
    public static final String EXISTING_PARCEL = "existingParcel";
    public static final String NEW_PARCEL_SL = "newParcelSL";
    public static final String EXISTING_PARCEL_SL = "existingParcelSL";
    
}
