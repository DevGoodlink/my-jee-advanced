package entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-04T09:06:22")
@StaticMetamodel(Demande.class)
public class Demande_ { 

    public static volatile SingularAttribute<Demande, Date> dateDebut;
    public static volatile SingularAttribute<Demande, String> sujetStage;
    public static volatile SingularAttribute<Demande, String> description;
    public static volatile SingularAttribute<Demande, String> etablissement;
    public static volatile SingularAttribute<Demande, Long> id;
    public static volatile SingularAttribute<Demande, Date> dateFin;
    public static volatile SingularAttribute<Demande, String> nom;
    public static volatile SingularAttribute<Demande, String> prenom;
    public static volatile SingularAttribute<Demande, Date> dateDemande;
    public static volatile SingularAttribute<Demande, String> email;
    public static volatile SingularAttribute<Demande, Integer> etatDemande;

}