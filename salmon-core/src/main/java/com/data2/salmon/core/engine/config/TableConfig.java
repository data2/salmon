package com.data2.salmon.core.engine.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author leewow
 */
@Data
@NoArgsConstructor
public class TableConfig {
    private String name;
    private String column;
    private String val;
    private String partionMethod;
    private String[] params;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
