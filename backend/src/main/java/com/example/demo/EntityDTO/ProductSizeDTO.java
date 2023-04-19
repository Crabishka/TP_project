package com.example.demo.EntityDTO;

import java.util.Map;
import java.util.Objects;

public class ProductSizeDTO {

    private Map<Double, Boolean> map;

    public ProductSizeDTO(Map<Double, Boolean> map) {
        this.map = map;
    }

    public Map<Double, Boolean> getMap() {
        return map;
    }

    public void setMap(Map<Double, Boolean> map) {
        this.map = map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSizeDTO that = (ProductSizeDTO) o;
        return Objects.equals(map, that.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
    }
}
