package org.sola.services.ejb.cadastre.repository.entities;

import javax.persistence.Table;
import org.sola.services.common.repository.DefaultSorter;
import org.sola.services.common.repository.entities.AbstractCodeEntity;

@Table(name = "survey_type", schema = "cadastre")
@DefaultSorter(sortString="display_value")
public class SurveyType extends AbstractCodeEntity {
    public static final String CODE_RESURVEY_AMENDMENT = "resurvey_amend";
    public static final String CODE_RESURVEY_EXTNSION = "resurvey_ext";
    public static final String CODE_NAME_CHANGE = "name_change";
    public static final String CODE_SUBDIVISION = "subdivision";
    public static final String CODE_BASED_ON = "based_on";
    
    public SurveyType(){
        super();
    }
}
