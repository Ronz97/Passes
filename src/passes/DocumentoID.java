/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passes;



/**
 *
 * @author Ronaldo Guilherme
 */
public class DocumentoID {
     TipoDocumento tipo;
    String cod;
    
    public DocumentoID(String tipo, String cod){
        this.tipo = gerarTipo(tipo);
    }
    
    
    private TipoDocumento gerarTipo(String doc){
        if(doc.equalsIgnoreCase("BI"))
            tipo = TipoDocumento.BI;  

            else if(doc.equalsIgnoreCase("PASSAPORTE"))
            tipo = TipoDocumento.PASSAPORTE;

            else if(doc.equalsIgnoreCase("CUC"))
            tipo = TipoDocumento.CUC; 

            else if(doc.equalsIgnoreCase("TRE"))
            tipo = TipoDocumento.TRE;  

            else {System.out.println("Documento Invalido");
                    System.exit(0);
            }

            return tipo;
    
    }

    public TipoDocumento getTipo() {
        return tipo;
    }

    public String getCod() {
        return cod;
    }
}
