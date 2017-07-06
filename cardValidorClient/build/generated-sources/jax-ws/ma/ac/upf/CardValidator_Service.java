
package ma.ac.upf;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "CardValidator", targetNamespace = "http://upf.ac.ma/", wsdlLocation = "http://YASSALIE-PC:8080/CardValidatorws/CardValidator?wsdl")
public class CardValidator_Service
    extends Service
{

    private final static URL CARDVALIDATOR_WSDL_LOCATION;
    private final static WebServiceException CARDVALIDATOR_EXCEPTION;
    private final static QName CARDVALIDATOR_QNAME = new QName("http://upf.ac.ma/", "CardValidator");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://YASSALIE-PC:8080/CardValidatorws/CardValidator?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CARDVALIDATOR_WSDL_LOCATION = url;
        CARDVALIDATOR_EXCEPTION = e;
    }

    public CardValidator_Service() {
        super(__getWsdlLocation(), CARDVALIDATOR_QNAME);
    }

    public CardValidator_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), CARDVALIDATOR_QNAME, features);
    }

    public CardValidator_Service(URL wsdlLocation) {
        super(wsdlLocation, CARDVALIDATOR_QNAME);
    }

    public CardValidator_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CARDVALIDATOR_QNAME, features);
    }

    public CardValidator_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CardValidator_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns CardValidator
     */
    @WebEndpoint(name = "CardValidatorPort")
    public CardValidator getCardValidatorPort() {
        return super.getPort(new QName("http://upf.ac.ma/", "CardValidatorPort"), CardValidator.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CardValidator
     */
    @WebEndpoint(name = "CardValidatorPort")
    public CardValidator getCardValidatorPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://upf.ac.ma/", "CardValidatorPort"), CardValidator.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CARDVALIDATOR_EXCEPTION!= null) {
            throw CARDVALIDATOR_EXCEPTION;
        }
        return CARDVALIDATOR_WSDL_LOCATION;
    }

}
