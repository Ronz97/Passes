
package passes;

import java.time.LocalDate;

/**
 *
 * @author Ronaldo Guilherme
 */
public class ePassFesta extends ePass implements Titulotransporte,CartaoPagamento{
    private String evento;
    private final double valorBilhete = 38;
    private final LocalDate dataIni;
    private final LocalDate dataFim;
    private double saldoFesta;
    private Ticket Bilhete;
    
    ePassFesta(String tipo,String cod,String nome,String morada,String email,int telef,LocalDate dataNasc,String evento,LocalDate dataIni,LocalDate dataFim,double saldoFesta,String Bilhete){
        super(tipo,cod,nome,morada,email,telef,dataNasc);
        this.dataIni = dataIni;
        this.dataFim = dataFim;
        this.saldoFesta = saldoFesta;
        this.Bilhete = new Ticket(Bilhete);
    }

    @Override
    public boolean passarSaldo(ePass pass, double saldoT) {
        boolean result = false;
        
        if( pass.getClass().getName().equalsIgnoreCase("ePassFesta") ){
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
        setSaldo(+valorT);
    }
    
    @Override
    public void pagarViagem(){
        char r;
        if(getDataFim().isBefore(LocalDate.now())){
            System.out.println("\n Epass Invalido!!!\n");
            return;
        }
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
                case 'P':
                    setPontos(-40);
                    incNumMovs();
                    incPontos();
                    System.out.println("Pagamento com pontos efetuado com sucesso");
                    System.out.println("OK PARA VIAJAR!");
                    System.out.println("PONTOS ACTUAIS => "+getPontos());
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
            setPontos(getPontos()-40);
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
                s.append("\n-Evento: ");s.append(getEvento());
                s.append("\n-Data de Inicio: ");s.append(getDataIni());
                s.append("\n-Data de Encerramento: ");s.append(getDataFim());
                s.append("\n-Saldo Festa: ");s.append(getSaldoFesta());
                s.append("\n-Bilhete: ");s.append(getBilhete().getEstado());
		return s.toString();        
    }

    @Override
    public void validarTitulo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void pagarConsumo(double gasto) {
        if(getSaldoFesta()>gasto){
            setSaldoFesta(-gasto);
            System.out.println("CONSUMO PAGO!");
        }
        else{
            System.out.println("SALDO INSUFICIENTE!!!");
            System.out.println("CONSUMO NÃO PAGO!");
        }
    }
    

    public String getEvento() {
        return evento;
    }

    public double getValorBilhete() {
        return valorBilhete;
    }

    public LocalDate getDataIni() {
        return dataIni;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public double getSaldoFesta() {
        return saldoFesta;
    }

    public Ticket getBilhete() {
        return Bilhete;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public void setSaldoFesta(double saldoFesta) {
        this.saldoFesta = saldoFesta;
    }

    public void setBilhete(Ticket Bilhete) {
        this.Bilhete = Bilhete;
    }

    
    
}
