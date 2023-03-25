package cl.bci.user.service;

import cl.bci.user.dto.ReqInfoUser;
import cl.bci.user.dto.ReqUser;
import cl.bci.user.dto.RespInfoUser;
import cl.bci.user.dto.RespUser;
import cl.bci.user.dto.Response;
import cl.bci.user.exception.BadRequestException;
import cl.bci.user.exception.NotFoundException;

public interface ApiService {
	
	public RespUser createUser( ReqUser req, String token ) throws BadRequestException;
	
	public RespInfoUser getUser( Integer id ) throws NotFoundException;
	
	public Response changeUser( ReqInfoUser req ) throws NotFoundException, BadRequestException;
	
	public Response delUser( Integer id ) throws NotFoundException;

}
