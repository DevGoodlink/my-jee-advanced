package weblayer.managedBean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputText;
/**
 *
 * @author YASSALIE
 */
@ManagedBean
@SessionScoped
public class BeanNumberController implements Serializable {

    private int valeur;
    private int nombreAdeviner;
    private int tentative;
    private boolean test;
    private String resultat, progression;
    private ArrayList<Integer> historique;
    private HtmlCommandButton btnValidate;
    private HtmlCommandButton btnRecommencer;
    private HtmlInputText txtValeur;

    
    
    public BeanNumberController() {
        init();
    }

    
    
    public void init(){
        this.valeur=0;
        this.nombreAdeviner=(int)Math.round(Math.random()*10);
        this.tentative=0;
        this.progression=new String("");
        this.resultat = new String("Tentez votre chance");
        this.historique=new ArrayList<Integer>();
        //this.btnRecommencer.setDisabled(true);
    }
    public void setValeur(int valeur) {
        this.valeur = valeur;
        /*
        historique.add(valeur);
        tentative++;
        */
       
    }
    public int getValeur() {
        return valeur;
    }

    public String getProgression() {
        return progression;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public void setProgression(String progression) {
        this.progression = progression;
    }
    
    public ArrayList<Integer> getHistorique() {
        return historique;
    }

    public void setTest(boolean test) {
        this.test = test;
    }
    
       
    public int getNombreAdeviner() {
        return nombreAdeviner;
    }

  
    public void setTentative(int tentative) {
        this.tentative = tentative;
    }

    public int getTentative() {
        return tentative;
    }
    public HtmlCommandButton getBtnValidate() {
        return btnValidate;
    }

    public void setBtnValidate(HtmlCommandButton btnValidate) {
        this.btnValidate = btnValidate;
    }

    public HtmlCommandButton getBtnRecommencer() {
        return btnRecommencer;
    }

    public void setBtnRecommencer(HtmlCommandButton btnRecommencer) {
        this.btnRecommencer = btnRecommencer;
    }

    public HtmlInputText getTxtValeur() {
        return txtValeur;
    }

    public void setTxtValeur(HtmlInputText txtValeur) {
        this.txtValeur = txtValeur;
    }
    
    public String doVerification(){
        tentative++;
        historique.add(valeur);
        this.test=(nombreAdeviner==valeur);
        if (test){
            resultat="Bravo vous avez trouvé le bon nombre.";
            this.btnValidate.setDisabled(true);
            this.txtValeur.setDisabled(true);
        }else{
            resultat="Non, ce n'est pas le bon chiffre, refaites un essai.";
        }
        
        if (tentative==0 || test){
            progression="";
        }
        else
        {
            progression="Le nombre à rechercher (aléatoire) est plus "+ ((nombreAdeviner>valeur) ? "grand":"petit");
        }
         if(this.tentative >= 3 && !test)
         {
             return new String("fin");
         }else
         { 
             return new String("continuer");
         }
    }
    public String recommencer(){
        init();
        return "recommencer";
    }
    
}
