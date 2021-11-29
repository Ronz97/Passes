
package passes;

import java.time.LocalDate;

/**
 *
 * @author Ronaldo Guilherme
 */
public class ePassTurist extends ePass implements Titulotransporte{
    private final double valorBilhete = 50;
    private  int tipoT;
    
    ePassTurist(String tipo,String cod,String nome,String morada,String email,int telef,LocalDate dataNasc,int duracao,LocalDate hoje){
        super(tipo,cod,nome,morada,email,telef,dataNasc);
        setTipoT(duracao);
        gerarSaldo(duracao);
    }

    ePassTurist(String nome, String morada, int telef, LocalDate nascimento, String email, String doc, String cod, int diasVal, LocalDate hoje) {
        super(doc,cod,nome,morada,email,telef,nascimento);
       
    }
    
    private void gerarSaldo(int length){        
        switch (length) {
            case 1:
                setSaldo(500);
                break;
            case 3:
                setSaldo(700);
                break;
            case 7:
                setSaldo(1500);
                break;
            default:
                System.out.println("!!!!!!ERRO!!!!!!!");
                System.out.println("A DURACAO ESCOLHIDA TEM DE SER 1,3 OU 7.");
                break;
        }
                      
    }
    @Override
    public boolean passarSaldo(ePass pass, double saldoT) {
       boolean result = false;
        
        if( pass.getClass().getName().equalsIgnoreCase("ePassTurist") ){
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
    public char viajarFree(){return 'N';}
    
    @Override
    public void carregarEpass(double valorT) {
       System.out.println("NÃO SE PODE CARREGAR EPASS DO TIPO TURISTA.");
    }
    
    
    @Override
    public void pagarViagem(){
        char r;
        if(getSaldo()> getValorBilhete() && getPontos()>100){
            System.out.println("S - Pagar com saldo \t  P - Pagar com pontos");
            r = Le.umChar();
            switch(r){
                case 'S':
                    setSaldo(getSaldo() -getValorBilhete());
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
            setSaldo(getSaldo()-getValorBilhete());
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

    

    @Override
    public void validarTitulo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String paraString(){
        StringBuilder s = new StringBuilder();
		s.append("------ePass N.ª : "); s.append(getNmrE());
                s.append("\n-Tipo de ePass: ePass Turist");
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
                s.append("\n---DADOS UNICOS---");
                s.append("\n-Duracao de ePassTurist: ");s.append(getTipoT());
		return s.toString();
        
    }
    public double getValorBilhete() {
        return valorBilhete;
    }

    public int getTipoT() {
        return tipoT;
    }
    public void setTipoT(int duracao){
       this.tipoT = duracao; 
    }
}
