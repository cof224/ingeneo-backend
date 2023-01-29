package com.prueba.ingeneo.security.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "warehouses", schema = "transporte")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "El nombre es requerido requerido")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Min(value=0, message = " solo acepta valores positivos" )
    @Column(name = "type", nullable = false)
    private Integer type;

    @NotNull
    @Column(name = "enabled", nullable = false)
    private Boolean enabled = false;

    

    public Warehouse() {
		super();
	}

	public Warehouse(String name, Integer type,  Boolean enabled) {
		super();
		this.name = name;
		this.type = type;
		this.enabled = enabled;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


}