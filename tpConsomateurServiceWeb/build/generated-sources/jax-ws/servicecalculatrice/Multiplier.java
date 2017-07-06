
package servicecalculatrice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour multiplier complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="multiplier">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="a" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="b" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "multiplier", propOrder = {
    "a",
    "b"
})
public class Multiplier {

    protected int a;
    protected int b;

    /**
     * Obtient la valeur de la propriété a.
     * 
     */
    public int getA() {
        return a;
    }

    /**
     * Définit la valeur de la propriété a.
     * 
     */
    public void setA(int value) {
        this.a = value;
    }

    /**
     * Obtient la valeur de la propriété b.
     * 
     */
    public int getB() {
        return b;
    }

    /**
     * Définit la valeur de la propriété b.
     * 
     */
    public void setB(int value) {
        this.b = value;
    }

}
