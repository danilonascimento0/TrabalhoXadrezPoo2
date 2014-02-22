/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package p2o2.ifes.serra.state;

import p2o2.ifes.serra.model.Enum.EGameStatus;
import p2o2.ifes.serra.model.Enum.EJogadaMenu;
import p2o2.ifes.serra.model.Enum.EPlayerColor;
import p2o2.ifes.serra.model.cdp.Game;
import p2o2.ifes.serra.model.cdp.Jogador;
import p2o2.ifes.serra.util.LeitorUtil;
import p2o2.ifes.serra.view.cih.JogadaView;

/**
 *
 * @author Vic
 */
public class EmpateState implements StateInterface{
     private JogadaView jogadaView = new JogadaView();
     EJogadaMenu jogadaMenu;
    
     public EmpateState(Game game){
         jogadaDaVez(game);
     }
    
     public void jogadaDaVez(Game game){
          String cor;

         if(game.getJogadorDaVez() == 1){
             cor = "branco";
         }
          else{
             cor = "preto";
         }
         
         System.out.println(" ");
         System.out.println("Jogador "+cor+" é o seu turno!");
         
         jogadaView.show();	
         int opcaoMenu = LeitorUtil.lervalorInteiro();
	 verificaOpcaoJogada(opcaoMenu);
         
         
         if (jogadaMenu.equals(EJogadaMenu.Jogar)) {
              this.jogar();
              if(game.getJogadorDaVez() == 1){
                   game.setJogadorDaVez(EPlayerColor.black);
                    game.setState(new JogadorPretoState(game));
               }
                else{
                   game.setJogadorDaVez(EPlayerColor.white);
                    game.setState(new JogadorBrancoState(game));
               }             
         }
         if (jogadaMenu.equals(EJogadaMenu.Empate)){
                this.empatar(game);
                 
         }
         if(jogadaMenu.equals(EJogadaMenu.Desistir)){
              game.setState(new DesistirState(game));
         }
         if(jogadaMenu.equals(EJogadaMenu.Salvar)){
             
         }
     }

     protected void empatar(Game game){
         
         game.setGameStatus(EGameStatus.Terminado);
         game.setJogadorVencendorId(-1);
         //SALVAR GAME
         System.out.println(" ");
         System.out.println("Os jogadores concordaram em um Empate!");
         System.out.println("Jogo Finalizado!");

    }
     protected void jogar(){
         String jogada;
         
         System.out.println("Jogador branco, faça sua jogada!");
         jogada = LeitorUtil.lervalorString();
         System.out.println("Sua Jogada FOi: "+ jogada);
     }
     
     protected void verificaOpcaoJogada(int opcaoMainMenu){
            switch(opcaoMainMenu){
                    case 1: 
                            jogadaMenu = EJogadaMenu.Jogar;
                            break;
                    case 2:
                            jogadaMenu = EJogadaMenu.Empate;
                            break;
                    case 3: 
                            jogadaMenu = EJogadaMenu.Desistir;
                            break;
                    case 4:
                            jogadaMenu = EJogadaMenu.Salvar;
                            break;
                    default: 
                            jogadaView.mensagemOpcaoInvalida();
                            jogadaView.mensagemFim();
                            break;
            }
         
     }
    
}