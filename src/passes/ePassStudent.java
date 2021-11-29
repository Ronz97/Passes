
package passes;

import java.time.LocalDate;

/**
 *
 * @author Ronaldo Guilherme
 */
public class ePassStudent extends ePass implements Titulotransporte{
    private String estabEnsino;
    private String anoEscolaridade;
    private int viagensFree;
    LocalDate dataCarregamento;
    LocalDate dataVal;
    Zonas zona;
    private final double valorBilhete = 20;
    
    ePassStudent(String tipo,String cod,String nome,String morada,String email,int telef,LocalDate dataNasc,String escola,String ano,String z1,String z2,String z3,String z4){
        super(tipo,cod,nome,morada,email,telef,dataNasc);
        this.anoEscolaridade = ano;
        this.estabEnsino = escola;
        this.zona = new Zonas(z1,z2,z3,z4);
        this.viagensFree = 4;
    }

    @Override
    public boolean passarSaldo(ePass pass, double saldoT) {
        boolean result = false;
        
        if( pass.getClass().getName().equalsIgnoreCase("ePassStudent") ){
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
        this.dataCarregamento = LocalDate.now();
        this.dataVal = dataCarregamento.plusDays(30);
        this.viagensFree = 4;
       
    }

  
    @Override
    public char viajarFree(){
        char r='N';
        int z;
        if( getViagensFree() > 0){
            System.out.println("Dispões de "+ getViagensFree()+ " viagens gratis .");
            System.out.println("Quer utilizar uma viagem grátis?");
            System.out.println("S - SIM \t N - NÃO");
            r = Le.umChar();
            if(r == 'S'){
                System.out.println("Para onde quer viajar? ");
                System.out.println("1=> "+ zona.getZona1() );
                System.out.println("2=> "+ zona.getZona2() );
                System.out.println("3=> "+ zona.getZona3() );
                System.out.println("4=> "+ zona.getZona4() );
                System.out.println("5=> OUTRO");
                System.out.print("ESCOLHA=> ");
                z = Le.umInt();
                if( z==1 || z==2 || z==3 || z==4){
                    setViagensFree(getPontos()-1);
                    incNumMovs();
                    incPontos();
                    System.out.println("VIAGENS FREE => "+getViagensFree());
                    System.out.println("OK PARA VIAJAR!");
                }
                else{
                    System.out.println("NÃO PODE VIAJAR GRATIS PARA ESTA ZONA");
                }
                
            }
        }
        else{
            System.out.println("NÃO POSSUI VIAGENS FREE!");
        }
        return r;
    }
    
    
    @Override
    public void pagarViagem(){
        char r;
        
        if(getSaldo()> getValorBilhete() && getPontos()>100){
            System.out.println("S - Pagar com saldo \t  P - Pagar com pontos");
            r = Le.umChar();
            switch(r){
                case 'S':
                    setSaldo( getSaldo()-getValorBilhete());
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
                s.append("\n-Tipo de ePass: ePass Student");
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
                s.append("\n-Estabelecimento de Ensino: ");s.append(getEstabEnsino());
                s.append("\n-Ano de escolaridade: ");s.append(getAnoEscolaridade());
                s.append("\n-Data de carregamento: ");s.append(getDataCarregamento());
                s.append("\n-Data de validade: ");s.append(getDataVal());
                s.append("\n-Viagens Gratis: ");s.append(getViagensFree());
		return s.toString();
        
    }
    public String getEstabEnsino() {
        return estabEnsino;
    }

    public String getAnoEscolaridade() {
        return anoEscolaridade;
    }

    public int getViagensFree() {
        return viagensFree;
    }

    public LocalDate getDataCarregamento() {
        return dataCarregamento;
    }

    public LocalDate getDataVal() {
        return dataVal;
    }

    
    public double getValorBilhete() {
        return valorBilhete;
    }

    public void setEstabEnsino(String estabEnsino) {
        this.estabEnsino = estabEnsino;
    }

    public void setAnoEscolaridade(String anoEscolaridade) {
        this.anoEscolaridade = anoEscolaridade;
    }

    public void setViagensFree(int viagensFree) {
        this.viagensFree = viagensFree;
    }

    public void setDataCarregamento(LocalDate dataCarregamento) {
        this.dataCarregamento = dataCarregamento;
    }

    public void setDataVal(LocalDate dataVal) {
        this.dataVal = dataVal;
    }

}
