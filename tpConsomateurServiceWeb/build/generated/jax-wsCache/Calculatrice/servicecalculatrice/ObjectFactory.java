
package servicecalculatrice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the servicecalculatrice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Hello_QNAME = new QName("http://serviceCalculatrice/", "hello");
    private final static QName _AdditionnerResponse_QNAME = new QName("http://serviceCalculatrice/", "additionnerResponse");
    private final static QName _Additionner_QNAME = new QName("http://serviceCalculatrice/", "additionner");
    private final static QName _MultiplierResponse_QNAME = new QName("http://serviceCalculatrice/", "multiplierResponse");
    private final static QName _SoustraireResponse_QNAME = new QName("http://serviceCalculatrice/", "soustraireResponse");
    private final static QName _HelloResponse_QNAME = new QName("http://serviceCalculatrice/", "helloResponse");
    private final static QName _Multiplier_QNAME = new QName("http://serviceCalculatrice/", "multiplier");
    private final static QName _Soustraire_QNAME = new QName("http://serviceCalculatrice/", "soustraire");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: servicecalculatrice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Additionner }
     * 
     */
    public Additionner createAdditionner() {
        return new Additionner();
    }

    /**
     * Create an instance of {@link MultiplierResponse }
     * 
     */
    public MultiplierResponse createMultiplierResponse() {
        return new MultiplierResponse();
    }

    /**
     * Create an instance of {@link SoustraireResponse }
     * 
     */
    public SoustraireResponse createSoustraireResponse() {
        return new SoustraireResponse();
    }

    /**
     * Create an instance of {@link Soustraire }
     * 
     */
    public Soustraire createSoustraire() {
        return new Soustraire();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link Multiplier }
     * 
     */
    public Multiplier createMultiplier() {
        return new Multiplier();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link AdditionnerResponse }
     * 
     */
    public AdditionnerResponse createAdditionnerResponse() {
        return new AdditionnerResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serviceCalculatrice/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdditionnerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serviceCalculatrice/", name = "additionnerResponse")
    public JAXBElement<AdditionnerResponse> createAdditionnerResponse(AdditionnerResponse value) {
        return new JAXBElement<AdditionnerResponse>(_AdditionnerResponse_QNAME, AdditionnerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Additionner }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serviceCalculatrice/", name = "additionner")
    public JAXBElement<Additionner> createAdditionner(Additionner value) {
        return new JAXBElement<Additionner>(_Additionner_QNAME, Additionner.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiplierResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serviceCalculatrice/", name = "multiplierResponse")
    public JAXBElement<MultiplierResponse> createMultiplierResponse(MultiplierResponse value) {
        return new JAXBElement<MultiplierResponse>(_MultiplierResponse_QNAME, MultiplierResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SoustraireResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serviceCalculatrice/", name = "soustraireResponse")
    public JAXBElement<SoustraireResponse> createSoustraireResponse(SoustraireResponse value) {
        return new JAXBElement<SoustraireResponse>(_SoustraireResponse_QNAME, SoustraireResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serviceCalculatrice/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Multiplier }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serviceCalculatrice/", name = "multiplier")
    public JAXBElement<Multiplier> createMultiplier(Multiplier value) {
        return new JAXBElement<Multiplier>(_Multiplier_QNAME, Multiplier.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Soustraire }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serviceCalculatrice/", name = "soustraire")
    public JAXBElement<Soustraire> createSoustraire(Soustraire value) {
        return new JAXBElement<Soustraire>(_Soustraire_QNAME, Soustraire.class, null, value);
    }

}
