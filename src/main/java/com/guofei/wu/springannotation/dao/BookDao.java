package com.guofei.wu.springannotation.dao;

import org.springframework.stereotype.Repository;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-10 20:26
 * @since v3.0
 */
@Repository
public class BookDao {
    private Integer label = 1;

    public Integer getLabel() {
        return label;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "BookDao{" +
                "label=" + label +
                '}';
    }
}
