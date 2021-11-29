
package passes;

/**
 *
 * @author Ronaldo Guilherme
 */
public class Zonas {
    private final String zona[]; 
    
    Zonas(String z1,String z2,String z3, String z4){
        zona = new String[4];
        zona[0] = z1;
        zona[1] = z2;
        zona[2] = z3;
        zona[3] = z4;
        
    }

    public String getZona1() {
        return zona[0];
    }
    public String getZona2() {
        return zona[1];
    }
    public String getZona3() {
        return zona[2];
    }
     public String getZona4() {
        return zona[3];
    }

    public void setZona1(String zona) {
        this.zona[0] = zona;
    }
    public void setZona2(String zona) {
        this.zona[1] = zona;
    }
    public void setZona3(String zona) {
        this.zona[2] = zona;
    }
    public void setZona4(String zona) {
        this.zona[3] = zona;
    }
     
}
