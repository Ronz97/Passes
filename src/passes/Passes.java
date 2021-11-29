
package passes;

import static java.lang.System.out;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import passes.MyCommand.*;


/**
 *
 * @author Ronaldo Guilherme
 */
public class Passes {
    public static ArrayList< ePass > passes;
    
    Passes(){
     passes = new ArrayList(100);
    }
    
    public static void main(String[] args) {
        String cmd;
        boolean querSair=false;
        Passes apl = new Passes();
        do {
            MyCommand.limparEcra();
            out.print(" Passes  >>> ");
            cmd = Le.umaString();

            if  (cmd.equalsIgnoreCase("novo Epass")) {
		   novo_ePassStandard();

		} else if (cmd.equalsIgnoreCase("novo Student")) {
                    novo_ePassStudent();
                
                } else if (cmd.equalsIgnoreCase("novo Turista")) {
                    novo_ePassTurista();

                }else if (cmd.equalsIgnoreCase("novo Festa")) {
                    novo_ePassFesta();
                }
                else if (cmd.equalsIgnoreCase("alterar")) {
                        alterarDados_ePass();

                } else if (cmd.equalsIgnoreCase("carregar")) {
                        carregar_ePass();

                } else if (cmd.equalsIgnoreCase("mostrar")) {
                        mostrarDados_ePass();

                } else if (cmd.equalsIgnoreCase("pagar")) {
                        pagarViagem();

                } else if (cmd.equalsIgnoreCase("passar saldo")) {
                        passarSaldo_ePass();

                } else if (cmd.equalsIgnoreCase("info")) {
                        info_ePass();

                } else if (cmd.equalsIgnoreCase("terminar")) {
                        break;

                }else
                    MyCommand.showhelp();

        } while (!querSair);
    }
     /**
     * Cria novo Epass
     */
    public static void novo_ePassStandard() {
	ePass aux;

	String nome, morada, email,doc,cod;
	int telef;
	LocalDate nascimento;
	MyCommand.limparEcra();

	out.println(" Passes | novo ePass >>> ");
	out.println("Digite os dados que a seguir sao solicitados=> ");

        mostrarDocs();
        out.println("DOC => ");
	doc = Le.umaString();

        do{
            out.print("COD => ");
            cod = Le.umaString();
        }while( validaCod(cod) );
        
        do{
            out.print("NOME => ");
            nome = Le.umaString();
            if(nome.isEmpty()){
                out.println("ERRO: Nome eh obrigatorio.");
            }
        }while( nome.isEmpty() );

	do{
            out.print("MORADA => ");
            morada = Le.umaString();
            if(morada.isEmpty()){
                out.println("ERRO: Morada eh obrigatorio.");
            }
        }while( morada.isEmpty() );

	do{
            out.print("EMAIL => ");
            email = Le.umaString();
            if(email.isEmpty()){
                out.println("ERRO: Email eh obrigatorio.");
            }
        }while( email.isEmpty() );

        out.print("Data de nascimento(dd-MM-yyyy) => ");
        nascimento = valData();

	out.print("TELEFONE/TELEMOVEL => ");
	telef = Le.umInt();
	/*if(int(LocalDate.now().getYear()) - int(nascimento.getYear())){
        
        }*/
        int x = LocalDate.now().getYear();
        int idade = x - nascimento.getYear();
        if(idade < 25 ){
            //System.out.println("Criou um ePass Do tipo Jovem");
            aux = new ePassStandard(doc,cod, nome, morada, email, telef, nascimento);
            passes.add(aux);
            out.println("\nEste eh o ePass com o codigo "+aux.getNmrE() );
             MyCommand.showMsg("EPASS JOVEM ");
	     MyCommand.showMsg("CONCLUIDO !!!");
        }else if(idade > 65){
            System.out.println("Criou um ePass Da Terceira Idade");
            aux = new ePassStandard(doc,cod, nome, morada, email, telef, nascimento);
            passes.add(aux);
            out.println("\nEste eh o ePass com o codigo "+aux.getNmrE() );
            MyCommand.showMsg("EPASS TERCEIRA IDADE");
	    MyCommand.showMsg("CONCLUIDO !!!");
        }else{
            aux = new ePassStandard(doc,cod, nome, morada, email, telef, nascimento);
            passes.add(aux);
            out.println("\nEste eh o ePass com o codigo "+aux.getNmrE() );
            MyCommand.showMsg("EPASS FUNCIONARIOS");
	    MyCommand.showMsg("CONCLUIDO !!!");
        }
        /*aux = new ePassStandard(doc,cod, nome, morada, email, telef, nascimento);

	passes.add(aux);*/

	//out.println("\nEste eh o ePass com o codigo "+aux.getNmrE() );
	//MyCommand.showMsg("NOVO EPASS CRIADO OK! ");

	}
    /**
     * Cria novo EpassStudent
     */
    public static void novo_ePassStudent() {

	ePass aux;
        
	String nome, morada, email,doc,cod,estabE;
	String zona1,zona2,zona3,zona4;
	int telef,ano;
	LocalDate nascimento;

	MyCommand.limparEcra();

	MyCommand.showMsg(" Passes | novo ePass >>> ");

	MyCommand.showMsg("Digite os dados que a seguir s�o solicitados: ");

        mostrarDocs();
        out.println("DOC => ");
            doc = Le.umaString();

        do{
            out.print("COD => ");
            cod = Le.umaString();
        }while( validaCod(cod) );

        do{
            out.print("NOME => ");
            nome = Le.umaString();
            if(nome.isEmpty()){
                out.println("ERRO: Nome � obrigatorio.");
            }
        }while( nome.isEmpty() );

	do{
            out.print("MORADA => ");
            morada = Le.umaString();
            if(morada.isEmpty()){
                out.println("ERRO: Morada eh obrigatorio.");
            }
        }while( morada.isEmpty() );

	do{
            out.print("EMAIL => ");
            email = Le.umaString();
            if(email.isEmpty()){
                out.println("ERRO: Email eh obrigatorio.");
            }
        }
        while(email.isEmpty());

        out.print("Data de nascimento(dd-MM-yyyy) => ");
        nascimento = valData();

	out.print("TELEFONE/TELEMOVEL => ");
	telef = Le.umInt();

	out.println("--------------------------");
	out.println("Qual o seu estabelecimento de ensino =>  ");
	estabE = Le.umaString();

	out.println("Qual o ano de escolaridade => ");
        ano = Le.umInt();

        out.println("Digite as zonas que deseja ter disponivel para viagens gratis.(MAX = 4)\n Escreva Nao Definido caso nao queira.");
        
        out.println("ZONA 1 => ");
        zona1 = Le.umaString();
        
        out.println("ZONA 2 => ");
        zona2 = Le.umaString();     
        
        
        out.println("ZONA 3 => ");
        zona3 = Le.umaString();        
        
        
        out.println("ZONA 4 => ");
        zona4 = Le.umaString();       
              
        aux = new passes.ePassStudent(doc, cod, nome,morada,email ,telef, nascimento, doc, cod, zona1,zona2,zona3,zona4);
		
        passes.add(aux);
        passes.get(0).setPontos(120);
	out.println("\nEste eh o ePass com o codigo "+aux.getNmrE() );
	MyCommand.showMsg("NOVO EPASS CRIADO OK! ");

    }
   /**
     * Cria novo EpassTurista
     */
    public static void novo_ePassTurista() {
	ePass aux;
                
        String doc;
	String nome, morada, email,cod;
	int telef,ano,diasVal;
	LocalDate nascimento,dataCompra;

	MyCommand.limparEcra();

	MyCommand.showMsg(" Passes | novo ePass >>> ");
	MyCommand.showMsg("Digite os dados que a seguir s�o solicitados => ");

        mostrarDocs();
        out.println("DOC => ");
            doc = Le.umaString();

        do{
            out.print("COD => ");
            cod = Le.umaString();
        }while( validaCod(cod) );

        do{
            out.print("NOME => ");
            nome = Le.umaString();
            if(nome.isEmpty()){
                out.println("ERRO: Nome � obrigatorio.");
            }
        }while( nome.isEmpty() );

	do{
            out.print("MORADA => ");
            morada = Le.umaString();
            if(morada.isEmpty()){
                out.println("ERRO: Morada eh obrigatorio.");
            }
        }while( morada.isEmpty() );

	do{
            out.print("EMAIL => ");
            email = Le.umaString();
            if(email.isEmpty()){
                out.println("ERRO: Email eh obrigatorio.");
            }
        }
        while(email.isEmpty());

        out.print("Data de nascimento(dd-MM-yyyy) => ");
        nascimento = valData();

	out.print("TELEFONE/TELEMOVEL => ");
	telef = Le.umInt();
		
        do{
            out.println("--------------------------");
            out.println("Digite o numero que corresponde ao tipo de ePass Turista que pretende comprar : ");
            out.println("1: 1 DIA     \t 2: 3 DIAS  \t 3: 1 SEMANA ");
            diasVal = Le.umInt();
            MyCommand.limparEcra();
        }while(diasVal<1 || diasVal>3);

        LocalDate hoje = LocalDate.now();

        aux = new passes.ePassTurist(nome, morada, telef,nascimento,email ,doc, cod, diasVal,hoje);

	passes.add(aux);

	out.println("\nEste eh o ePass com o codigo "+aux.getNmrE() );
	MyCommand.showMsg("NOVO EPASS CRIADO OK! ");

	}
    /**
     * Cria novo EpassFesta
     */
    public static void novo_ePassFesta() {

	ePass aux;

	String nome, morada, email,doc,cod,evento,bilhete;
	int telef,saldoF;
	LocalDate nascimento,dataI,dataF;

	MyCommand.limparEcra();
	MyCommand.showMsg(" Passes | novo ePass >>> ");
	MyCommand.showMsg("Digite os dados que a seguir sao solicitados => ");
        
        mostrarDocs();
        out.println("DOC => ");
            doc = Le.umaString();

        do{
            out.print("COD => ");
            cod = Le.umaString();
        }while( validaCod(cod) );

        do{
            out.print("NOME => ");
            nome = Le.umaString();
            if(nome.isEmpty()){
                out.println("ERRO: Nome eh obrigatorio.");
            }
        }while( nome.isEmpty() );

	do{
            out.print("MORADA => ");
            morada = Le.umaString();
            if(morada.isEmpty()){
                out.println("ERRO: Morada eh obrigatorio.");
            }
        }while( morada.isEmpty() );

	do{
            out.print("EMAIL => ");
            email = Le.umaString();
            if(email.isEmpty()){
                out.println("ERRO: Email eh obrigatorio.");
            }
        }
        while(email.isEmpty());

        out.print("Data de nascimento(dd-MM-yyyy) => ");
        nascimento = valData();

	out.print("TELEFONE/TELEMOVEL => ");
	telef = Le.umInt();
		

	out.println("--------------------------");
	out.print("Qual o evento => ");
	evento = Le.umaString();

	out.print("Qual a data de inicio do evento => ");
	dataI = valData();

	out.print("Qual a data que o evento termina=> ");
        dataF = valData();
                
        out.print("Introduza o saldo que quer carregar para consumo na festa"); 
        out.print("SALDO FESTA => ");
        saldoF = Le.umInt();
        
        out.print("Quer comprar BILHETE para a festa?"); 
        out.print("S - SIM \t N - NAO ");
        bilhete = Le.umaString();
        if(bilhete.equals("S")){
            bilhete = "COMPRADO";
        }
        else{
            bilhete = "NAO COMPRADO";
        }
        aux = new ePassFesta(doc, cod, nome,morada,email,telef,nascimento,evento,dataI,dataF,saldoF,bilhete);

	passes.add(aux);
	out.println("\nEste eh o ePass com o codigo "+aux.getNmrE() );
	MyCommand.showMsg("NOVO EPASS CRIADO OK! ");

	}
    public static void mostrarDados_ePass() {
        int local = leCodigoEpass();
        if(local != -1){
            out.println(passes.get(local).paraString() );
            Le.umChar();
        }
        else{
            out.println("Nao existe Passe com o codigo digitado. ");
            Le.umChar();

        }


    }
    public static LocalDate valData(){
        String ddn;
        LocalDate datanasc;
        DateTimeFormatter var = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        while(true){
            try{
                System.out.print("Inserir data=> ");
                ddn = Le.umaString();
                datanasc = LocalDate.parse(ddn, var);

                System.out.println("Data okay: " + datanasc.format(var) );
                break;
            }
            catch(Exception e){
                System.out.println("Deve inserir data no formato dia-mes-ano");
            }
        }
        return datanasc;

    }
    public static void mostrarDocs(){
        out.println("\t\t TIPOS DE DOCS:");
        out.println("BI");
        out.println("PASSAPORTE");
        out.println("TRE");
        out.println("CUC");
        out.print("Escolha um dos tipos.");

    }
    /**
     * Verifica se existe bi com mesmo valor
     * @param cod que queremos verificar se existe
     * @return resultado da pesquisa: falso se n�o encontrar mesmo BI ou verdadeiro se encontrar BI com mesmo valor
     */
    public static boolean validaCod(String cod){
        boolean result = false;
        for(int i=0;i<ePass.getUltimoUsado()-100 ;i++){
            if(passes.get(i).getPassageiro().getDocID().getCod().equals(cod) ){
                result = true;
                out.println("DOC ja existe!!!");
            }
        }
        return result;
    }
    /**
     * Apresenta informacao geral sobre os Epasses
     */
    public static void info_ePass() {

	double totalSaldo=0;
	int numeViagens=0;

	if (ePass.getUltimoUsado() > 0) {
            for (int i=0; i < ePass.getUltimoUsado(); i++) {
		totalSaldo = totalSaldo + passes.get(i).getSaldo();
		numeViagens = numeViagens + passes.get(i).getNumMovs();
            }

	out.println("------Info de ePass----------");
	out.println("Total de ePass : " + ePass.getUltimoUsado() );
	out.println("Numero de viagens realizadas : "+ numeViagens);
	out.println("Total de saldo nos ePass neste momento : "+ totalSaldo);
	MyCommand.showMsg("---------------------------------");
	} else
	MyCommand.showMsg("Nao ha ePasses criados neste momento");

    }
     /**
     * Ler codigo dum Epass e procura-lo
     * @return posicao do Epass
     */
    public static int leCodigoEpass() {
		int cod;
		out.println("Digite c�digo de ePass: ");
		cod = Le.umInt();

		return (encontra_ePass(cod) );


    }
    /**
     * Procura por Epass atraves do codigo
     * @param codigo
     * @return posicao do Epass
     */
    public static int encontra_ePass(int codigo) {
	int posicao=-1;
	for (int i=0; i < ePass.getUltimoUsado(); i++) {            
            if ( passes.get(i).getNmrE() == codigo ) {
		posicao = i;
		break;
            }
	}
	return posicao;
    }
    
    public static void passarSaldo_ePass() {
        out.println("Digite o codigo do passe Origem");
        int local1 = leCodigoEpass();
        out.println("Digite o codigo do passe que quer transferir o Saldo");
        int local2 = leCodigoEpass();
        double valor=0;
        do{
            out.println("Digite o valor da transferencia: ");
            valor = Le.umDouble();
            if(valor<0){
                out.println("ERRO=> valor de carregamento tem que ser positivo!!!");
            }
        }while(valor<0);
        if(local1 != -1 && local2 != -1){      
            boolean result = passes.get(local1).passarSaldo(passes.get(local2),valor);
            if(result){
                out.println("TRANSFERENCIA EFETUADA!");
            }
            else{
                out.println("TRANSFERENCIA NAO EFETUADA!");
            }
        Le.umChar();
        }          
    }
     public static void pagarViagem(){
        int local1 = leCodigoEpass();
        char res='N';
        if(local1 != -1){
            if(passes.get(local1) instanceof ePassStudent){
                res = passes.get(local1).viajarFree();
            }
            if(res == 'N'){
                passes.get(local1).pagarViagem();
            }
        }
        Le.umChar();
    }
      public static void alterarDados_ePass() {
	    int local = leCodigoEpass();
	    if(local != -1){
            int telefone;
            String nome,email,morada;
            out.println("Introduza os novos dados que quer colocar no epass: ");


            out.println("Nome=> ");
            nome = Le.umaString();
            passes.get(local).getPassageiro().setNome(nome);

            out.println("Morada=> ");
            morada = Le.umaString();
            passes.get(local).getPassageiro().setMorada(morada);

            out.println("Email=> ");
            email = Le.umaString();
            passes.get(local).getPassageiro().setEmail(email);

            out.println("Telefone=> ");
            telefone = Le.umInt();
            passes.get(local).getPassageiro().setTelefone(telefone);

	    }
	    else{
            MyCommand.showMsg("Nao existe Epass com o codigo digitado.");
	    }

	}
      public static void carregar_ePass() {
        int local = leCodigoEpass();
        if(local != -1){
            out.println("Digite o valor de carregamento=> ");
            double val = Le.umDouble();

            while(val<0){
                out.println("Valor para carregar Epass tem que ser positivo.");
                out.println("Digite o valor de carregamento:  ");
                val = Le.umDouble();
            }
            passes.get(local).carregarEpass(val);
            out.println("Novo saldo do Passe eh: " + passes.get(local).getSaldo() );
            Le.umChar();
        }
        else{
            out.println("Nao existe Passe com o codigo digitado. ");
            Le.umChar();
        }
    }
      
}
