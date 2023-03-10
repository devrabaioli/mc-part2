package devrabaioli.domain.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import devrabaioli.domain.Categoria;

public class CategoriaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Nome nao pode ser vazio")
	@Length(min = 5, max = 80, message = "entre 5 3 80 caracteres")
	private String name;

	
	public CategoriaDTO() {}
	
	public CategoriaDTO (Categoria obj) {
		id = obj.getId();
		name = obj.getName();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
}
