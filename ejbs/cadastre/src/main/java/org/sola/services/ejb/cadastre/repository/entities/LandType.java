/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.ejb.cadastre.repository.entities;
import javax.persistence.Table;
import org.sola.services.common.repository.DefaultSorter;
import org.sola.services.common.repository.entities.AbstractCodeEntity;

/**
 * Entity representing the cadastre.land_type code table.
 * @author soladev
 */
@Table(name = "land_type", schema = "cadastre")
@DefaultSorter(sortString="display_value")
public class LandType extends AbstractCodeEntity {
    public LandType(){
        super();
    }
}
