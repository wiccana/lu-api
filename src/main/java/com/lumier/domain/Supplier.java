package com.lumier.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ospos_suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer person_id;

    private String company_name;
    private String agency_name;

    public Integer getPerson_id() {
        return this.person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getCompany_name() {
        return this.company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getAgency_name() {
        return this.agency_name;
    }

    public void setAgency_name(String agency_name) {
        this.agency_name = agency_name;
    }

}
