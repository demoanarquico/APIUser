package cl.bci.user.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cl.bci.user.dto.InfoPhone;
import cl.bci.user.dto.Phone;
import cl.bci.user.dto.ReqInfoUser;
import cl.bci.user.dto.ReqUser;
import cl.bci.user.dto.RespInfoUser;
import cl.bci.user.dto.RespUser;
import cl.bci.user.dto.Response;
import cl.bci.user.entity.PhoneEntity;
import cl.bci.user.entity.UserEntity;
import cl.bci.user.exception.BadRequestException;
import cl.bci.user.exception.NotFoundException;
import cl.bci.user.repository.PhoneRepository;
import cl.bci.user.repository.UserRepository;
import cl.bci.user.util.Util;

import org.springframework.stereotype.Service;

@Service
public class ApiServiceImpl implements ApiService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PhoneRepository phoneRepository;

	@Override
	public RespUser createUser( ReqUser req, String token ) throws BadRequestException {

		UserEntity userEntity = existUserEmail( req.getEmail() );
		
		if( userEntity != null ) {
			throw new BadRequestException("Email ya registrado para otro usuario.");
		}
		
		RespUser respUser = new RespUser();
		userEntity = new UserEntity();
		userEntity.setName( req.getName() );
		userEntity.setEmail( req.getEmail() );
		userEntity.setPassword( req.getPassword() );
		userEntity.setToken( token );
		
		userEntity = saveUser( userEntity );
		
		List<PhoneEntity> listPhones = new ArrayList<PhoneEntity>();
		PhoneEntity phoneEntity;
		
		List<Phone> phones = req.getPhones();
		
		if( phones != null && !phones.isEmpty() ) {
			
			for( int i=0; i < phones.size(); i++ ) {
				phoneEntity = new PhoneEntity();
				phoneEntity.setIduser( userEntity.getId() );
				phoneEntity.setNumber( phones.get(i).getNumber() );
				phoneEntity.setCountrycode( phones.get(i).getContrycode() );
				phoneEntity.setCitycode( phones.get(i).getCitycode() );
				listPhones.add( phoneEntity );
			}
			
			savePhones( listPhones );
			
		}

		respUser.setId( userEntity.getId() );
		respUser.setCreated( userEntity.getCreated().toString() );
		respUser.setLast_login( userEntity.getLastlogin().toString() );
		respUser.setToken( userEntity.getToken() );
		respUser.setIsactive( true );
		
		return respUser;
		
	}
	
	@Override
	public RespInfoUser getUser( Integer id ) throws NotFoundException {
	
		UserEntity userEntity = getIdUser( id );
		
		if( userEntity != null ) {
			
			RespInfoUser respInfoUser = new RespInfoUser();
			respInfoUser.setId( userEntity.getId() );
			respInfoUser.setName( userEntity.getName() );
			respInfoUser.setEmail( userEntity.getEmail() );
			respInfoUser.setPassword( userEntity.getPassword() );
			
			if( userEntity.getIsactive().equals("t") ) {
				respInfoUser.setIsactive(true);
			}
			else {
				respInfoUser.setIsactive(false);
			}
			
			List<PhoneEntity> listPhonesEntity = getPhonesUser( id );
			
			if( listPhonesEntity != null && !listPhonesEntity.isEmpty() ) {
				List<InfoPhone> listPhones = new ArrayList<InfoPhone>();
				InfoPhone phone;
				for( int i=0; i < listPhonesEntity.size(); i++ ) {
					phone = new InfoPhone();
					phone.setId( listPhonesEntity.get(i).getId() );
					phone.setNumber( listPhonesEntity.get(i).getNumber() );
					phone.setCitycode( listPhonesEntity.get(i).getCitycode() );
					phone.setContrycode( listPhonesEntity.get(i).getCountrycode() );
					listPhones.add( phone );
				}
				
				respInfoUser.setPhones( listPhones );
			}
			
			return respInfoUser;
			
		}
		else {
			throw new NotFoundException("Usuario no encontrado.");
		}
		
		
	}
	
	@Override
	public Response changeUser( ReqInfoUser req ) throws NotFoundException, BadRequestException {
		
		UserEntity userEntity = getIdUser( req.getId() );
		
		if( userEntity != null ) {
			
			UserEntity userEmailEntity = existUserEmail( req.getEmail() );
			
			if( userEmailEntity != null && userEmailEntity.getId() != userEntity.getId() ) {
				throw new BadRequestException("Email ya registrado para otro usuario.");
			}
			
			userEntity.setName( req.getName() );
			userEntity.setEmail(req.getEmail() );
			userEntity.setPassword( req.getPassword() );
			
			if( req.isIsactive() ) {
				userEntity.setIsactive("t");
			}
			else {
				userEntity.setIsactive("f");
			}
			
			userEntity.setModified( Util.getTime() );
			updateUser( userEntity );
			
			List<PhoneEntity> listPhones = getPhonesUser( req.getId() );
			
			if( listPhones != null && req.getPhones() != null && !req.getPhones().isEmpty() ) {

				List<InfoPhone> phones = req.getPhones();
				
				for( int i=0; i < phones.size(); i++ ) {
					
					for( int j=0; j < listPhones.size(); j++ ) {
						
						if( listPhones.get(j).getId() == phones.get(i).getId() ) {
							
							listPhones.get(j).setNumber( phones.get(i).getNumber() );
							listPhones.get(j).setCountrycode( phones.get(i).getContrycode() );
							listPhones.get(j).setCitycode( phones.get(i).getCitycode() );
							j = listPhones.size();
							
						}
						
					}
					
				}
				
				savePhones( listPhones );
			}
			
			Response resp = new Response( 0, "OK" );
			
			return resp;
			
		}
		else {
			throw new NotFoundException("Usuario no existe.");
		}

	}
	
	
	@Override
	public Response delUser( Integer id ) throws NotFoundException {
		
		UserEntity userEntity = getIdUser( id );
		
		if( userEntity != null ) {
			
			List<PhoneEntity> listPhones = getPhonesUser( id );
			if( listPhones != null ) {
				deletePhones( listPhones );
			}
			deleteUser( id );
			
			Response resp = new Response( 0, "OK" );
			return resp;
			
		}
		else {
			throw new NotFoundException("Usuario no existe.");
		}
		
	}
	
	private UserEntity existUserEmail( String email ) {
		return userRepository.findByemail( email );
	}
	
	private UserEntity saveUser( UserEntity userEntity ) {
		
		Timestamp ts = Util.getTime();
		userEntity.setCreated( ts );
		userEntity.setLastlogin( ts );
		userEntity.setIsactive( "t" );
		return userRepository.save( userEntity );
		
	}
	
	private void updateUser( UserEntity userEntity ) {
		userRepository.save( userEntity );
	}
	
	private void savePhones( List<PhoneEntity> listPhones ) {
		phoneRepository.saveAll( listPhones );
	}

	private UserEntity getIdUser( Integer id ) {
		return userRepository.findByid( id );
	}
	
	private List<PhoneEntity> getPhonesUser( Integer id ){
		return phoneRepository.findByiduser( id );
	}
	
	private void deleteUser( Integer id ) {
		userRepository.deleteById( id );
	}
	
	private void deletePhones( List<PhoneEntity> listPhones ) {
		phoneRepository.deleteAll( listPhones );
	}

}
