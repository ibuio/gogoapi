/**
 * @author chr0n0metriq: jk
 * 
 * ca.ibu.api.client
 * Auth0JerseyClient.java
 * Aug 17, 2016
 * 
 */
package ca.ibu.api.client;

// javax
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

// slf4j
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jk
 *
 */
public class Auth0JerseyClient {

    static final Logger LOG = LoggerFactory.getLogger(Auth0JerseyClient.class);
    
    private Client client;
    private WebTarget webTarget;
    private static String TOKEN = System.getenv("AUTH0_API_TOKEN");
    private static String HEADERKEYNAME = "Authorization";
    
    
    /**
     * @param client
     */
    public Auth0JerseyClient(Client client) {
        LOG.info("Auth0JerseyClient::Auth0JerseyClient");
        
        this.client = client;
        webTarget = client.target(System.getenv("AUTH0_API_ROOT_URL"));
    }
    
    /**
     * @param userToken
     * @return
     */
    public String getUser(String userToken) {
        LOG.info("Auth0JerseyClient::getUser");
        
        String entity = client.target(System.getenv("AUTH0_API_ROOT_URL"))
                .path("userinfo")
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + userToken)
                .get(String.class);
        
        return entity;
    }
}
