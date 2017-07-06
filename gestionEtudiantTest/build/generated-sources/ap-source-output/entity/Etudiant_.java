package entity;

import entity.Filiere;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-01-30T11:47:43")
@StaticMetamodel(Etudiant.class)
public class Etudiant_ { 

    public static volatile SingularAttribute<Etudiant, Filiere> filiere;
    public static volatile SingularAttribute<Etudiant, Long> id;
    public static volatile SingularAttribute<Etudiant, String> nom;
    public static volatile SingularAttribute<Etudiant, String> prenom;
    public static volatile SingularAttribute<Etudiant, Date> ddn;

}