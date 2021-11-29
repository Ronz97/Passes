
package passes;

import java.time.LocalDate;

/**
 *
 * @author Ronaldo Guilherme
 */
public class ePassStandard extends ePass implements Titulotransporte{
   private final double valorBilhete = 38;
    public ePassStandard(String tipo, String cod, String nome, String morada, String email, int telef, LocalDate dataNasc) {
        super(tipo, cod, nome, morada, email, telef, dataNasc);
    }

    @Override
    public boolean passarSaldo(ePass pass, double saldoT) {
       boolean result = false;
        
        if( pass.getClass().getName().equalsIgnoreCase("ePassStandard") ){
            if(getSaldo() > saldoT){
                setSaldo(-saldoT);
                pass.setSaldo(+saldoT);
                result = true;
            }
            else{
                System.out.println("NÃO FOI POSSÍVEL REALIZAR TRANSFERENCIA");
                System.out.println("O SEU SALDO É INFERIOR AO VALOR DE TRANSFERENCIA");
            }   
        }
        else {
            System.out.println("NÃO FOI POSSÍVEL REALIZAR TRANSFERENCIA");
            System.out.println("NÃO SE PODE TRANSFERIR SALDO PARA EPASS DE TIPO DIFERENTE");
            System.out.println("SEU EPASS É DO TIPO "+getClass().getName() + 
                    " MAS QUER TRANFERIR SALDO PARA EPASS DO TIPO "+pass.getClass().getName() );
        }
        
        
        
        return result;
    }
    

    @Override
    public void carregarEpass(double valorT) {
         setSaldo(+valorT);
    }

    @Override
    public char viajarFree() {
        return 'N';
    }

    @Override
    public String paraString() {
        StringBuilder s = new StringBuilder();
		s.append("------ePass N.ª : "); s.append(getNmrE());
                s.append("\n-Tipo de ePass: ePass Standard");
                s.append("\n-Chave : "); s.append(getChave());
		s.append("\n-Nome titular: "); s.append(getPassageiro().getNome());
		s.append("\n-Tipo DOC: "); s.append(getPassageiro().getDocID().getTipo() );
		s.append("\n-Codigo DOC: "); s.append(getPassageiro().getDocID().getCod() );
		s.append("\n-Morada : "); s.append(getPassageiro().getMorada());
		s.append("\n-Email : "); s.append(getPassageiro().getEmail() );
		s.append("\n-Telefone: "); s.append(getPassageiro().getTelefone());
		s.append("\n-Saldo actual: "); s.append(getSaldo());
		s.append("\n-Número de viagens: "); s.append(getNumMovs());           
		s.append("\n-------------------------------------");
		return s.toString();
        
    }
    @Override//funcoes da classe superior
   public void pagarViagem(){
        char r;
        if(getSaldo()> getValorBilhete() && getPontos()>100){
            System.out.println("S - Pagar com saldo \t  P - Pagar com pontos");
            r = Le.umChar();
            switch(r){
                case 'S':
                    setSaldo(getSaldo()-getValorBilhete());
                    incNumMovs();
                    incPontos();
                    System.out.println("Pagamento com saldo efetuado com sucesso");
                    System.out.println("OK PARA VIAJAR!");
                    System.out.println("SALDO ACTUAL => "+getSaldo());
                       break;
                case 'P':
                    setPontos(getPontos()-40);
                    incNumMovs();
                    incPontos();
                    System.out.println("Pagamento com pontos efetuado com sucesso");
                    System.out.println("OK PARA VIAJAR!");
                    System.out.println("PONTOS ACTUAIS => "+getPontos());
                       break;
                default:
                    System.out.println("OPÇÃO INVALIADA!!!");
                    System.out.println("Pagamento não realizado.");
                    System.out.println("NÃO OK PARA VIAJAR!");
            }
        }
        else if(getSaldo()> getValorBilhete() && getPontos()<100){
            setSaldo( getSaldo()-getValorBilhete());
            incNumMovs();
            incPontos();
            System.out.println("Pagamento com saldo efetuado com sucesso");
            System.out.println("OK PARA VIAJAR!");
            System.out.println("SALDO ACTUAL => "+getSaldo());
        }
        else if(getSaldo()<getValorBilhete() && getPontos()>100){            
            setPontos(-40);
            incNumMovs();
            incPontos();
            System.out.println("Pagamento com pontos efetuado com sucesso");
            System.out.println("OK PARA VIAJAR!");
            System.out.println("PONTOS ACTUAIS => "+getPontos());           
        }
        else{
            System.out.println("SALDO ACTUAL => "+getSaldo() +"\tPONTOS ACTUAIS => "+getPontos() );
            System.out.println("Pagamento não realizado.");
            System.out.println("NÃO OK PARA VIAJAR!");
        }
    }

    private double getValorBilhete() {
        return valorBilhete;
    }
    
    @Override
    public void validarTitulo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*@Override
    public String toString() {
        return "ePassStandard{" + "valorBilhete=" + valorBilhete + '}';
    }*/
    
}
