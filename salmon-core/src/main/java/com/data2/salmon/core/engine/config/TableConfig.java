package com.data2.salmon.core.engine.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author leewow
 */
@Getter
@Setter
public class TableConfig {
    public String name;
    public String column;
    public String val;
    public String partionMethod;
    public String[] params;

    public TableConfig() {
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
