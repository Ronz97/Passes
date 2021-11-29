
package passes;

import java.time.LocalDate;

/**
 *
 * @author Ronaldo Guilherme
 */
public abstract class ePass implements Titulotransporte,CartaoPagamento{
    private String chave;
    private int nmrE;
    private Titular passageiro;
    private double saldo;
    private int pontos;
    private int numMovs;
    LocalDate dataNasc;
    private static int ultimoUsado;
    
    public ePass(String tipo,String cod,String nome,int telef,LocalDate dataNasc){
        this.passageiro = new Titular(tipo,cod,nome,telef,dataNasc);
        this.saldo = 0;
        this.numMovs = 0;
        this.pontos = 0;
        this.nmrE = ultimoUsado +1;
        setUltimoUsado(nmrE);
        this.chave = gerarChave();
        
    }
    public ePass(String tipo,String cod,String nome,String morada,String email,int telef,LocalDate dataNasc){
        this.passageiro = new Titular (tipo,cod,nome,morada,email,telef,dataNasc);
        this.saldo = 0;
        this.numMovs = 0;
        this.pontos = 0;
        this.nmrE = ultimoUsado +1;
        setUltimoUsado(nmrE);
        this.chave = gerarChave();
        
    }
    // ABSTRACT METHODS

    /**
     *
     * @param pass
     * @param saldoT
     * @return
     */
    abstract public boolean passarSaldo(ePass pass,double saldoT);
    abstract public void carregarEpass(double valorT);
    abstract public char viajarFree();      
    abstract public String paraString();
    
    // CONCRETE METHODS
 
    public void incNumMovs(){
        setNumMovs(+1);
    }
    public void incPontos(){
        if(getNumMovs()%10 == 0){
            setPontos(getPontos()+1);
        }
    }
    public final String gerarChave(){
        char nomeC[];
        nomeC = passageiro.getNome().toCharArray();
	StringBuilder nome = new StringBuilder();
	StringBuilder apelido = new StringBuilder();
        String chaveE;

	for(int i =0;i< nomeC.length -1;i++){
            if(nomeC[i] == ' ')
                break;
            nome.append(nomeC[i]);
	}

	for(int i = nomeC.length -1;i >= 0;i--){
            if(nomeC[i] == ' ')
                break;
            apelido.append(nomeC[i]);
	}

	apelido.reverse();

        if(nome.length() > 5)
            nome.delete(5,nome.length() );

        if(apelido.length() > 5)
            apelido.delete(5,apelido.length() );

        chaveE = nome.reverse().toString().toUpperCase()+apelido.reverse().toString().toUpperCase()+passageiro.getDataNasc()+getNmrE();

        return chaveE;
    }
    
    //FROM INTERFACE
    @Override
    public void pagarViagem(){}
    @Override
    public void validarTitulo(){}
    @Override
    public void pagarConsumo(double valor){}
    
    
    

    public String getChave() {
        return chave;
    }

    public int getNmrE() {
        return nmrE;
    }

    public Titular getPassageiro() {
        return passageiro;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getPontos() {
        return pontos;
    }

    public int getNumMovs() {
        return numMovs;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public static int getUltimoUsado() {
        return ultimoUsado;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public void setNmrE(int nmrE) {
        this.nmrE = nmrE;
    }

    public void setPassageiro(Titular passageiro) {
        this.passageiro = passageiro;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void setNumMovs(int numMovs) {
        this.numMovs = numMovs;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public static void setUltimoUsado(int ultimoUsado) {
        ePass.ultimoUsado=ultimoUsado;
    }

    
    
    
}
