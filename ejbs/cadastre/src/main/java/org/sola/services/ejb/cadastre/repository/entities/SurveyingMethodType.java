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
import org.sola.services.common.repository.DefaultSorter;
import org.sola.services.common.repository.entities.AbstractCodeEntity;
import org.sola.services.common.repository.entities.AbstractVersionedEntity;

/**
 * Entity representing the system.surveying_method_type code table.
 * @author soladev
 */
 @Table(name = "surveying_method_type", schema = "cadastre")
@DefaultSorter(sortString="display_value")
public class SurveyingMethodType extends AbstractCodeEntity{

    public SurveyingMethodType() {
        super();
    }
     
}
