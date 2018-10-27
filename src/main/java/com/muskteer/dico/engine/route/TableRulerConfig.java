package com.muskteer.dico.engine.route;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@Setter
public class TableRulerConfig {
    public String name;
    public String column;
    public String val;
    public String partionMethod;
    public String[] params;

    public TableRulerConfig() {
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
