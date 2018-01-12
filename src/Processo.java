
public class Processo {
	   private int entrada;
	   private int execucao;
	   private int iD;
	   private int emCPU;
	   private int tempoEspera;
	   private int tempoRetorno;
	   private int tempoResposta;
	   private int entradaCPU;
	   private int saidaCPU;
	   private int entradaPronto;
	   
	   public Processo(){
	      this(-1, -1, -1);
	   }
	   
	   public Processo(int i, int j, int k){
	      entrada = i;
	      execucao = j;
	      iD = k;
	      emCPU = 0;
	      tempoEspera = 0;
	      tempoResposta = 0;
	      tempoRetorno = 0;
	      entradaCPU = 0;
	      entradaPronto = 0;
	   }
	   
	   public int getEntrada(){
	      return entrada;
	   }
	   
	   public int getExecucao(){
	      return execucao;
	   }
	   
	   public int getID(){
	      return iD;
	   }
	   
	   public int getEmCPU(){
	      return emCPU;
	   }
	   
	   public int getRetorno(){
	      return tempoRetorno;
	   }
	   
	   public int getResposta(){
	      return tempoResposta;
	   }
	   
	   public int getEntradaCPU(){
	      return entradaCPU;
	   }
	   
	   public int getSaidaCPU(){
	      return saidaCPU;
	   }
	   
	   public int getEntradaPronto(){
	      return entradaPronto;
	   }
	   
	   public int getEspera(){
	      return tempoEspera;
	   }
	   
	   public void setEmCPU(){
	      emCPU++;
	   }
	   
	   public void setEspera(){
	      tempoEspera++;
	   }
	   
	   public void setResposta(int t){
	      tempoResposta = t;
	   }
	   
	   public void setRetorno(int t){
	      tempoRetorno = t;
	   }
	   
	   public void setEntradaCPU(int t){
	      entradaCPU = t;
	   }
	   
	   public void setSaidaCPU(int t){
	      saidaCPU = t;
	   }
	   
	   public void setEntradaPronto(int t){
	      entradaPronto = t;
	   }
	   
	   public boolean chegaFim(){
		 if(emCPU == execucao){
			return true;
		}else{
			return false;
		}
	   }//chegaFim
	   
	   public void setarValor(){
		   tempoResposta = entradaCPU - entradaPronto;
		   tempoRetorno = saidaCPU - entradaPronto;
	   }
	   
	   public boolean primeiraVez(){
		   if(emCPU == 0){
			   return true;
		   }else{
			   return false;
		   }
	   }//primeiraVez
	   
	   public boolean nTerminou(){
		   if(emCPU < execucao){
			   return true;
		   }else{
			   return false;
		   }
	   }//nTerminou
}
