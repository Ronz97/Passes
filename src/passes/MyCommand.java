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
public class MyCommand {
    private String cmd;
	    
	    public MyCommand() {
	    	cmd = null;
	    }

	    /**
	     * limpar texto do ecra
	     */
	    public static void limparEcra() {
	    	
	        for (int y = 0; y < 25; y++)
	        	System.out.println("\n");
	    }
	    
	    /**
		 * Método para mostrar ajuda
	     */
	    public static void showhelp() {			
		        
	    	System.out.println("Comandos válidos são :");
	    	System.out.println("novo <Tipo de Epass> - para fazer novo ePass ");
	    	System.out.println("alterar -alterar dados de um ePass");
	    	System.out.println("carregar -realizar um carregamento num ePass");
	    	System.out.println("mostrar -para mostrar dados de um ePass");
	    	System.out.println("pagar - pagar uma viagem com ePass");
	    	System.out.println("passar saldo -transferir saldo de um ePass para outro");
	    	System.out.println("info -apresentar info: quantos ePass foram criados, total de saldo");
	    	System.out.println("terminar -para terminar programa");
	    	System.out.println("guardar - ");
	    	System.out.println("importar - ");
	    	System.out.println("\n");
	    	System.out.println("prima ENTER tecla para continuar");
			Le.umChar();

	    }
	    
	    /**
	     * Apresenta mensagem de prompt
	     */

	    public static void showPrompt() {
	              limparEcra(); 
	              System.out.print(" Epasse.Digite seu comando => ");
	    }

	    
	    /**
	     * Método para apresentar uma mensgagem no ecra
	     * @param ms String que será apresentada
	     */
	    public static void  showMsg(String ms) {
			
	    	System.out.println(ms);
	    	Le.umChar();
	    }
	         
	    /**
	     * Método para ler uma string que representa um comando para uma funcionalidade 
	     * @return String com o comando do utilizador
	     */
	    public String[] lerComando() {
	        cmd = Le.umaString(); 
	           
	        String[] aux = cmd.trim().split(" ");
	           
	        return aux;
	    }
	    
	    /**
	     * Valida se uma string digitada é um comando válido do programa      
	     * @param lcmd string dada pelo utilizador
	     * @return array com comando e parâmetros se houver
	     */
	    public String[] validarComando(String[] lcmd) {
	          
	            boolean resultado=true;
	            int tamanho = lcmd.length;
	            String cmd = lcmd[0];
	            
	            if (tamanho > 0) {
	                
	                switch (cmd) {
	                    case ("novo") :  {                    
	                        if (tamanho == 1) {
	                        	resultado=false;
	                            break;
	                        }
	                        	
	                        else if ((tamanho == 2) && 
	                              (lcmd[1].equalsIgnoreCase("student") || lcmd[1].equalsIgnoreCase("turista") 
	                                     || lcmd[1].equalsIgnoreCase("festival")) ) {
							} else
	                            resultado = false;
	                        break;
	                    }
	                    case ("alterar") : {
	                        if (tamanho == 1)
	                            break;
	                        else if (tamanho > 3) {resultado = false; break;}
	                        
	                        else if ((tamanho == 3) && 
	                              (lcmd[1].equalsIgnoreCase("nome") || lcmd[1].equalsIgnoreCase("email") 
	                                     || lcmd[1].equalsIgnoreCase("telefone") 
	                                     || lcmd[1].equalsIgnoreCase("morada")) ) {
							}
	                        
	                        break;		
	                    }
										
	                    case ("carregar") : {
	                        if (tamanho == 1)
	                            break;                                                
	                        else if (tamanho > 3) 
	                            {resultado = false; break;}
	                        
	                        else if  (tamanho == 3) {
							}                                                             
	                        break;		
	                    }
	                    
	                    case ("guardar") : {
	                        if (tamanho == 1)
	                            break;
	                    }
	                    
	                    case ("importar") : {
	                        if (tamanho == 1)
	                            break;
	                    }
	                
	                    case("mostrar") : {
	                        if (tamanho == 1)
	                            break;
	                        
	                        else if  (tamanho > 2) 
	                            {resultado = false; break;}
	                        
	                        else if  (tamanho == 2) {
							}                                                             
	                        break;
	                    }
				  
	                    case ("passar saldo") : {
	                        if (tamanho == 2)
	                            break;
	                                                
	                        else if ((tamanho == 1) || (tamanho > 5)) 
	                            {resultado = false; break;}
	                        
	                        else if  (tamanho == 5) {
							}                                                             
	                        break;
	                    }                
	                    case ("info") : {
	                        if (tamanho == 1)
	                            break;
	                        else if (tamanho > 1) { 
	                            resultado = false; break;
	                        }                                                                
	                    } case ("terminar") : {
	                        if (tamanho > 1) resultado = false; break;
	                    } default : lcmd[0] = "NOCMD"; // quer dizer que não foi inserido nenhum comando
	                } 
	                
	            }    
	            
	            if (!resultado && tamanho > 1) 
	                lcmd[1] = cmd.concat("PARINV"); // quer dizer que número parametros inválidos
	            
	            else if (!resultado && tamanho == 1) 
	                lcmd[0] = cmd.concat("NOPARAM"); // não foi passado parâmetros
	            
	            
	            return  lcmd;
	          
	    }
	    
	    /*
	     * Apresenta mensagem standard de erro
	     */
	    public void mensagemErro() {
	       limparEcra();
	       System.out.println("Comando inválido neste contexto\n");
	       mensagemPrompt();
	    }
	    
	    
	    private void mensagemPrompt() {
	        System.out.println("\nDigite tecla ENTER para continuar");
	        Le.umChar();
	    }
}
