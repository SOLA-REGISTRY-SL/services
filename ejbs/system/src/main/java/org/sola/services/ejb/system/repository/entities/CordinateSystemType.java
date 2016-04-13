/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.ejb.system.repository.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.services.common.repository.DefaultSorter;
import org.sola.services.common.repository.entities.AbstractCodeEntity;
import org.sola.services.common.repository.entities.AbstractEntity;
/**
 *
 * @author Moses VB
 */

@Table(name = "cordinate_system_type", schema = "system")
@DefaultSorter(sortString="display_value")
public class CordinateSystemType extends AbstractEntity{
    @Id
    @Column
    private String code;
    
    @Column(name = "display_value")
    private String displayValue;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "description")
    private String description;
    
    public CordinateSystemType(){
        super();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}

