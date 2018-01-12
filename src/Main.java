import java.io.*;
import javax.swing.*;
import java.text.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      LinkedList<Processo> listaRR = new LinkedList<Processo>();
      
      int nProcessos = 0;
      
      try {
         // TODO code application logic here
         BufferedReader leitura = new BufferedReader(new FileReader("C:/Programa/Fernando/processos1.txt"));
         
         String linha = leitura.readLine();
         String[] str = linha.split(" ");
         
         nProcessos++;
         listaRR.add(new Processo(Integer.parseInt(str[0]), Integer.parseInt(str[1]), nProcessos));
         
         while(leitura.ready()){
            linha = leitura.readLine();
            str = linha.split(" ");
            nProcessos++;
            listaRR.add(new Processo(Integer.parseInt(str[0]), Integer.parseInt(str[1]), nProcessos));
            
         }
         leitura.close();
         RRobin(listaRR, nProcessos);
      } catch (FileNotFoundException ex) {
         JOptionPane.showMessageDialog(null, "Erro na abertura do arquivo");
         System.exit(1);
      } catch (IOException ex) {
         JOptionPane.showMessageDialog(null, "Problema na leitura do arquivo");
         System.exit(1);
      }

	}
	
   public static void RRobin(List<Processo> listaFifo, int n){
      LinkedList<Processo> filaPronto = new LinkedList<>();
      LinkedList<Processo> filaEncerrado = new LinkedList<>();
      LinkedList<Processo> filaTemp = new LinkedList<Processo>();
      
      int tempo = 0,
          contagem = 0;
      double mediaRetorno = 0,
             mediaResposta = 0,
             mediaEspera = 0;
      Processo cpu,
      		   vazio = new Processo();
      DecimalFormat df = new DecimalFormat("0.#");
      
      cpu = vazio;
      /* inicia o processamento */
      for(tempo = 0; ;tempo++){
         if(contagem == n && cpu.equals(vazio)){
            break;
         }
         /* processos chegam na fila de pronto */
         for(Processo p : listaFifo){
            if(p.getEntrada() == tempo){
               p.setEntradaPronto(tempo);
               filaPronto.add(p);
            }else if(p.getEntrada() > tempo){
               break;
            }
         }
         /* Cada processo deve permanecer */
         /* por apenas 2 tempos na CPU.   */
         /* Ou seja, quando a variavel    */
         /* tempo for par, um processo    */
         /* entra na CPU. Se for impar    */
         /* a cpu devera ter um processo  */
         /* e esse processo devera ser    */
         /* retirado dela.                */
         if(tempo%2 == 0){
           if(!filaPronto.isEmpty()){
	        	/* O processo da vez entra na CPU */
	           cpu = filaPronto.remove();
	            if(cpu.primeiraVez()){
	        	   contagem++;
	               cpu.setEntradaCPU(tempo);
	            }
	            cpu.setEmCPU();
           }else{
        	   continue;
           }
         }else{
            cpu.setEmCPU();
            /* Nem todos os processos estao executando */
            /* executando corretamente.                */
            /* Temos 11 processos, mas apenas alguns   */
            /* estao entrando nesse if().              */
            if(cpu.nTerminou()){
                filaTemp.add(cpu);
                cpu = vazio;
            }else{
               cpu.setSaidaCPU(tempo);
               cpu.setarValor();
               filaEncerrado.add(cpu);
               System.out.println("ID: " + cpu.getID());
               cpu = vazio;
            }
         }
         
         for(Processo p : filaPronto){
            p.setEspera();
         }
         filaPronto.addAll(filaTemp);
         filaTemp.clear();
      }
      /* Parte nao essencial */
      /* calcular as medias  */
      for(Processo p : filaEncerrado){
    	 System.out.println("Fim ID: " + p.getID());
         mediaRetorno += p.getRetorno();
         mediaEspera += p.getEspera();
         mediaResposta += p.getEntradaCPU();
      }
      
      for(Processo p : filaPronto){
    	 System.out.println("Fim Pronto ID: " + p.getID());
      }
      mediaRetorno /= n;
      mediaResposta /= n;
      mediaEspera /= n;
      System.out.println("RR " + df.format(mediaRetorno) + " " +  df.format(mediaResposta) + " " 
                        + df.format(mediaEspera));
   }

}
