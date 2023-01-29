package com.prueba.ingeneo.security.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "products", schema = "transporte")
public class Product {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotBlank(message = "el nombre es requerido")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Size(max = 100)
    @Column(name = "description", length = 100)
    private String description;

    @PositiveOrZero(message = "el valor del precio debe de ser positivo")
    @Column(name = "price")
    private BigDecimal price;

    @NotNull
    @Column(name = "enabled", nullable = false)
    private Boolean enabled = false;

    

    public Product() {
		super();
	}

	public Product(String name, String description,	BigDecimal price, Boolean enabled) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}