package cl.bci.user.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.bci.user.dto.ReqInfoUser;
import cl.bci.user.dto.ReqUser;
import cl.bci.user.dto.RespInfoUser;
import cl.bci.user.dto.RespUser;
import cl.bci.user.dto.Response;
import cl.bci.user.exception.BadRequestException;
import cl.bci.user.exception.InternalServerErrorException;
import cl.bci.user.exception.NotFoundException;
import cl.bci.user.service.ApiService;
import cl.bci.user.validator.UserValidator;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@OpenAPIDefinition(
	    info = @Info(
	        title = "APi User",
	        description = "API desarrollada para prueba técnica BCI",
	        version = "1.0.0",
	        contact = @Contact(
					email = "salvadormorenoramos@gmail.com"
				)
  
	    )
	    
	)

@SecurityScheme(
	    name = "bearerAuth",
	    type = SecuritySchemeType.HTTP,
	    bearerFormat = "JWT",
	    scheme = "bearer"
	)
@Tag(name = "UserRequest", description = "API Users")
@RestController
@RequestMapping(path = "/api/user")
@Transactional
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
    private UserValidator userValidator;
	
	@Autowired
	private ApiService service;

	
	@Operation(summary = "Servicio para crear un nuevo usuario.",
			description = "Permite crear un nuevo usuario en la base de datos.",
			security = {@SecurityRequirement(name = "bearerAuth")})
	@PutMapping( path="/create", consumes = "application/json", produces = "application/json" )
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity< RespUser > createUser( @RequestHeader Map<String, String> headers, @RequestBody ReqUser user ) throws BadRequestException, InternalServerErrorException{
		
		String key = "Authorization";
		String token = "Sin_Token";
		if ( headers.containsKey(key) && headers.get(key) != "" && headers.get(key) != null ) {
			token = headers.get( key );
		}
		
		userValidator.validateReqUser( user );
		userValidator.validateEmailUser( user.getEmail() );
		userValidator.validatePassUser( user.getPassword() );
		
		RespUser respUser = service.createUser( user, token );
		return new ResponseEntity<>( respUser, HttpStatus.CREATED );
		
	}
	
	
	@Operation(summary = "Servicio para consultar por un usuario.",
			description = "Permite consultar por id del usuario.",
			security = {@SecurityRequirement(name = "bearerAuth")})
	@GetMapping( path="/get", produces = "application/json" )
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity< RespInfoUser > getUser(  @RequestParam(required = true, name = "idUser" ) Integer idUser ) throws  Throwable, InternalServerErrorException, NotFoundException, BadRequestException{
		
		if( idUser <= 0 ) {
			throw new BadRequestException("Valor Id debe ser mayor a cero.");
		}
		
		RespInfoUser respInfoUser = service.getUser( idUser );
		return new ResponseEntity<>( respInfoUser, HttpStatus.OK );
		
	}
	
	
	@Operation(summary = "Servicio para modificar un usuario.",
			description = "Permite modificar un usuario en la base de datos.",
			security = {@SecurityRequirement(name = "bearerAuth")})
	@PostMapping( path="/change", consumes = "application/json", produces = "application/json" )
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity< Response > changeUser( @RequestBody ReqInfoUser user ) throws BadRequestException, InternalServerErrorException, NotFoundException{
		
		userValidator.validateReqInfoUser( user );
        userValidator.validateEmailUser( user.getEmail() );
		userValidator.validatePassUser( user.getPassword() );
		
		Response resp = service.changeUser( user );
		return new ResponseEntity<>( resp, HttpStatus.OK );
		
	}

	
	@Operation(summary = "Servicio para eliminar un usuario.",
			description = "Permite eliminar un usuario de la base de datos.",
			security = {@SecurityRequirement(name = "bearerAuth")})
	@DeleteMapping( path="/delete", produces = "application/json" )
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity< Response > deleteUser( @RequestParam(required = true, name = "idUser" ) Integer idUser ) throws BadRequestException, InternalServerErrorException, NotFoundException{
		
		if( idUser <= 0 ) {
			throw new BadRequestException("Valor Id debe ser mayor a cero.");
		}
		
		Response resp = service.delUser( idUser );
		return new ResponseEntity<>( resp, HttpStatus.OK );
		
	}

}
