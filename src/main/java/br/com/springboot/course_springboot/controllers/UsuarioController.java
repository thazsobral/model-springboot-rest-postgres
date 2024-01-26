package br.com.springboot.course_springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.course_springboot.model.Usuario;
import br.com.springboot.course_springboot.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController // é o que mapeia as requisições
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepository;
    
    @RequestMapping(value="/sum", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String sumValues(@RequestParam(name = "n1", value = "n1") Integer n1, @RequestParam(name="n2", value="n2") Integer n2) {
    	Integer result = n1 + n2;
    	return result.toString();
    }
    
    @GetMapping(value="/listartodos") // mapeia no método GET
    @ResponseBody // todas os dados (return) no corpo
    public ResponseEntity<List<Usuario>> listarUsuarios() {
    	List<Usuario> usuarios = usuarioRepository.findAll(); // executa a consulta total no bd
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); //retorna os dados + o status de OK (200)
    }
    
    @PostMapping(value="/salvarusuario") // mapeia o método POST
    @ResponseBody
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario) { // RequestBody recebe a entidade definida e injeta a mesma na entidade
    	Usuario novoUsuario = new Usuario();
    	novoUsuario.setNome(usuario.getNome());
    	novoUsuario.setIdade(usuario.getIdade());
    	Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);
    	return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.CREATED);
    }
    
    @DeleteMapping(value="/deletarusuario")
    @ResponseBody
    public ResponseEntity<String> deletarUsuario(@RequestParam Long idUsuario) {
    	usuarioRepository.deleteById(idUsuario);// remove um registro (mas não retorna nada quando têm sucesso)
    	return new ResponseEntity<String>("Usuário "+idUsuario+" deletado com sucesso.", HttpStatus.OK);
    }
    
    @GetMapping(value="/buscarusuario")
    @ResponseBody
    public ResponseEntity<Usuario> buscarUsuario(@RequestParam(name="idUsuario") Long idUsuario) { //método GET só funciona o form-data
    	Usuario usuario = usuarioRepository.findById(idUsuario).get();// busca um usuario na base de dados
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
    
    @PutMapping(value="/atualizarusuario")
    @ResponseBody
    public ResponseEntity<?> atualizarUsuario(@RequestBody Usuario usuario) {
    	if(usuario.getId() == null) {    		
    		return new ResponseEntity<String>("Id não informado.", HttpStatus.OK);
    	}
    	Usuario novoUsuario = usuarioRepository.saveAndFlush(usuario);
    	return new ResponseEntity<Usuario>(novoUsuario, HttpStatus.OK);
    }
    
    @GetMapping(value="/buscarpornome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name="nome") String nome) {
    	List<Usuario> usuarios = usuarioRepository.buscarPorNome(nome.trim().toUpperCase()); //remove os espaços adicionados a esquerda e a direta
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }
}
