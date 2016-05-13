package org.sola.services.ejb.cadastre.repository.entities;
import javax.persistence.Table;
import org.sola.services.common.repository.DefaultSorter;
import org.sola.services.common.repository.entities.AbstractCodeEntity;

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
