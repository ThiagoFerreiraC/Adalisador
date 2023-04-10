package br.com.adalisadorquimico.adalisadorquimico.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseDTO {
    @JsonProperty("PropertyTable")
    private PropertyTable propertyTable;
    @Getter
    @Setter
    public static class PropertyTable {
        @JsonProperty("Properties")
        private List<Properties> properties;

        @Getter
        @Setter
        public static class Properties {
            @JsonProperty("MolecularFormula")
            private String molecularFormula;
            @JsonProperty("MolecularWeight")
            private String molecularWeight;
        }
    }
}
