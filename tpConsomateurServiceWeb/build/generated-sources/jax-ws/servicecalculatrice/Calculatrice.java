
package servicecalculatrice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Calculatrice", targetNamespace = "http://serviceCalculatrice/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Calculatrice {


    /**
     * 
     * @param a
     * @param b
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "multiplier", targetNamespace = "http://serviceCalculatrice/", className = "servicecalculatrice.Multiplier")
    @ResponseWrapper(localName = "multiplierResponse", targetNamespace = "http://serviceCalculatrice/", className = "servicecalculatrice.MultiplierResponse")
    @Action(input = "http://serviceCalculatrice/Calculatrice/multiplierRequest", output = "http://serviceCalculatrice/Calculatrice/multiplierResponse")
    public int multiplier(
        @WebParam(name = "a", targetNamespace = "")
        int a,
        @WebParam(name = "b", targetNamespace = "")
        int b);

    /**
     * 
     * @param a
     * @param b
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "additionner", targetNamespace = "http://serviceCalculatrice/", className = "servicecalculatrice.Additionner")
    @ResponseWrapper(localName = "additionnerResponse", targetNamespace = "http://serviceCalculatrice/", className = "servicecalculatrice.AdditionnerResponse")
    @Action(input = "http://serviceCalculatrice/Calculatrice/additionnerRequest", output = "http://serviceCalculatrice/Calculatrice/additionnerResponse")
    public int additionner(
        @WebParam(name = "a", targetNamespace = "")
        int a,
        @WebParam(name = "b", targetNamespace = "")
        int b);

    /**
     * 
     * @param a
     * @param b
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "soustraire", targetNamespace = "http://serviceCalculatrice/", className = "servicecalculatrice.Soustraire")
    @ResponseWrapper(localName = "soustraireResponse", targetNamespace = "http://serviceCalculatrice/", className = "servicecalculatrice.SoustraireResponse")
    @Action(input = "http://serviceCalculatrice/Calculatrice/soustraireRequest", output = "http://serviceCalculatrice/Calculatrice/soustraireResponse")
    public int soustraire(
        @WebParam(name = "a", targetNamespace = "")
        int a,
        @WebParam(name = "b", targetNamespace = "")
        int b);

    /**
     * 
     * @param name
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "hello", targetNamespace = "http://serviceCalculatrice/", className = "servicecalculatrice.Hello")
    @ResponseWrapper(localName = "helloResponse", targetNamespace = "http://serviceCalculatrice/", className = "servicecalculatrice.HelloResponse")
    @Action(input = "http://serviceCalculatrice/Calculatrice/helloRequest", output = "http://serviceCalculatrice/Calculatrice/helloResponse")
    public String hello(
        @WebParam(name = "name", targetNamespace = "")
        String name);

}
