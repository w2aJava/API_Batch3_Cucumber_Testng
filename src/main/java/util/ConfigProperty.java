package util;

import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Mutable;

@Sources({ 
	"file:src\\test\\resources\\propertyFiles\\config.properties" // mention the property file name
})
public interface ConfigProperty extends Mutable{
	
	@Key("baseURI")
	public String getBaseURI();
	
	@Key("basePath")
	public String getBasePath();
	
	
	@Key("validSecretKet")
	public String getValidSecretKet();
	
	
	@Key("invalidSecret")
	public String getInValidSecretKet();
	
	
	@Key("customerAPIEndPoint")
	public String getCustomerAPIEndPoint();

}
