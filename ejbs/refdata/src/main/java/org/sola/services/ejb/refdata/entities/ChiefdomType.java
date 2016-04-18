/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.ejb.refdata.entities;
import javax.persistence.Table;
import org.sola.services.common.repository.DefaultSorter;
import org.sola.services.common.repository.entities.AbstractCodeEntity;
/**
 * Entity representing the cadastre.chiefdoms_type code table.
 * @author soladev
 */
@Table(name = "chiefdoms_type", schema = "cadastre")
@DefaultSorter(sortString="display_value")
public class ChiefdomType extends AbstractCodeEntity {
    
    public ChiefdomType(){
        super();
    }
}
