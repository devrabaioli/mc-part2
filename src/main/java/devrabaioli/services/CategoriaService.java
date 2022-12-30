package devrabaioli.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import devrabaioli.domain.Categoria;
import devrabaioli.domain.DTO.CategoriaDTO;
import devrabaioli.repositories.CategoriaRepository;
import devrabaioli.services.exception.DataIntegrityException;
import devrabaioli.services.exception.ObjectNotfoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotfoundException(
				"Objecto nao encontrado id: " + id + " Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);

	}

	public void delete (Integer id) {
		find(id);
		try {
		repo.deleteById(id);
	    }
		catch(DataIntegrityViolationException e){
		throw new DataIntegrityException("Nao é possível excluir Categorias com produtos associados");
		}
	}
	
	public List<Categoria> findAll() {
		return repo.findAll();
	}
	

	public Page<Categoria> listPage(Integer page, Integer linesPerPage, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		 Categoria obj = new Categoria(objDto.getId(), objDto.getName());
		 return obj;
	}

	
	
}
