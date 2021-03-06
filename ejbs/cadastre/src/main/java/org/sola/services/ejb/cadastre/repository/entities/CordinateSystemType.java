/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.ejb.cadastre.repository.entities;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.services.common.repository.entities.AbstractCodeEntity;

/**
 * Entity representing the system.cordinate_system_type code table.
 * @author soladev
 */

@Table(name = "cordinate_system_type", schema = "system")
public class CordinateSystemType extends AbstractCodeEntity{
        
    @Id
    @Column(name = "code")
    private String code;
    
    @Column(name = "display_value")
    private String displayValue;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "status")
    private String status;
    
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   
}
