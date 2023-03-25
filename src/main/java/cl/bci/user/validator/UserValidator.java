package cl.bci.user.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cl.bci.user.dto.InfoPhone;
import cl.bci.user.dto.Phone;
import cl.bci.user.dto.ReqInfoUser;
import cl.bci.user.dto.ReqUser;
import cl.bci.user.environment.EnvironmentProject;
import cl.bci.user.exception.BadRequestException;
import cl.bci.user.util.Util;

@Component
public class UserValidator {
	
	@Autowired
    private EnvironmentProject environ;
	
	public void validateEmailUser( String email ) throws BadRequestException {
		
		if( !Util.matcherData( environ.getExprEmail(), email ) ) {
			throw new BadRequestException("Formato Email no válido");
		}
		
	}
	
    public void validatePassUser( String pass ) throws BadRequestException {
		
		if( !Util.matcherData( environ.getExprPass(), pass ) ) {
			throw new BadRequestException("Formato Password no válido");
		}
		
	}
    
    public void validateReqUser( ReqUser user ) throws BadRequestException {

		if( user.getName() == null || user.getName().isEmpty() ) {
			throw new BadRequestException("Debe completar valor Name.");
		}
		
        if( user.getEmail() == null || user.getEmail().isEmpty() ) {
        	throw new BadRequestException("Debe completar valor Email.");
		}

        if( user.getPassword() == null || user.getPassword().isEmpty() ) {
        	throw new BadRequestException("Debe completar valor Password.");
        }
        
        validatePhone( user.getPhones() );
    	
    }
    
    private void validatePhone( List<Phone> listPhones ) throws BadRequestException {
    	int sum = 0;
    	for(int i=0; i < listPhones.size(); i++ ) {
    		sum = i+1;
    		if( listPhones.get(i).getNumber() == null || listPhones.get(i).getNumber().isEmpty() ) {
    			throw new BadRequestException( "Debe completar valor Number en Phone N° : " + sum );
    		}
    		
            if( listPhones.get(i).getContrycode() == null || listPhones.get(i).getContrycode().isEmpty() ) {
            	throw new BadRequestException( "Debe completar valor CountryCode en Phone N°: " + sum );
    		}

            if( listPhones.get(i).getCitycode() == null || listPhones.get(i).getCitycode().isEmpty() ) {
            	throw new BadRequestException( "Debe completar valor CityCode en Phone N°: " + sum );
            }
    		
    	}
    	
     }
    
    public void validateReqInfoUser( ReqInfoUser user ) throws BadRequestException {
    	
    	if( user.getId() <= 0 ) {
			throw new BadRequestException("Valor Id debe ser mayor a cero.");
		}
		
		if( user.getName() == null || user.getName().isEmpty() ) {
			throw new BadRequestException("Debe completar valor Name.");
		}
		
        if( user.getEmail() == null || user.getEmail().isEmpty() ) {
        	throw new BadRequestException("Debe completar valor Email.");
		}

        if( user.getPassword() == null || user.getPassword().isEmpty() ) {
        	throw new BadRequestException("Debe completar valor Password.");
        }
        
        validateInfoPhone( user.getPhones() );
    	
    }
    
    private void validateInfoPhone( List<InfoPhone> listPhones ) throws BadRequestException {
    	
    	for(int i=0; i < listPhones.size(); i++ ) {
    		
    		if( listPhones.get(i).getNumber() == null || listPhones.get(i).getNumber().isEmpty() ) {
    			throw new BadRequestException( "Debe completar valor Number en Phone Id: " + listPhones.get(i).getId() );
    		}
    		
            if( listPhones.get(i).getContrycode() == null || listPhones.get(i).getContrycode().isEmpty() ) {
            	throw new BadRequestException( "Debe completar valor CountryCode en Phone Id: " + listPhones.get(i).getId() );
    		}

            if( listPhones.get(i).getCitycode() == null || listPhones.get(i).getCitycode().isEmpty() ) {
            	throw new BadRequestException( "Debe completar valor CityCode en Phone Id: " + listPhones.get(i).getId() );
            }
    		
    	}
    	
    }

}
