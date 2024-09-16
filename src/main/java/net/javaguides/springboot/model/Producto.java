package net.javaguides.springboot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("productos") // JDBC use @Table directly from Spring Data JDBC
public class Producto {

    @Id 
    private Long id;

    @Column("name") 
    private String name;

    @Column("image")
    private String image;

    @Column("descript")
    private String description;

    @Column("category")
    private String category = "product";

    @Column("price")
    private Double price;

    public Producto() {}

    public Producto(String name, String image, String description, String category, Double price) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.category = category;
        this.price = price;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

    
}
