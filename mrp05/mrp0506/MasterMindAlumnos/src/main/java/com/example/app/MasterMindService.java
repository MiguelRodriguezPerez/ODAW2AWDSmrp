package com.example.app;

import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.app.model.EstadoJuego;
import com.example.app.model.Intento;
import com.example.app.model.MasterMind;

@Service
@Scope("session")//Realiza una nueva instancia por cada sesión web
public class MasterMindService {

    public MasterMind masterMind;

    public void nuevoJuego(int tamayo,int numIntentos) {
        masterMind = new MasterMind(tamayo,numIntentos);//Instancia de la clase Mastermind, necesaria para que funcione el servicio
        do {
            masterMind.setNumeroSecreto(cadenaAlAzar(tamayo));//Genera una cadena al azar
        } while (cadenaConDuplicados(masterMind.getNumeroSecreto()));
        //Mientras el número secreto tenga carácteres duplicados, el bucle iterará
        masterMind.getListaIntentos().clear();//Borra todos los intentos de la lista
        masterMind.setEstadoJuego(EstadoJuego.JUGANDO);//Cambia el estado juego a ganando
        System.out.println("=====> Num secreto: " + masterMind.getNumeroSecreto()); // solo para testing
    }

    public void comprobarIntento(String intento) {
        if (masterMind.getEstadoJuego() != EstadoJuego.JUGANDO)
            return;//Comprueba que el intento se realiza mientras quedan intentos 
        if (cadenaConDuplicados(intento) || intento.length() != masterMind.getLongitud())
            return;/*Si el intento tiene números duplicados o si su longitud no tiene
            cuatro números acabará con el intento*/

        int bienColocados = 0, malColocados = 0;//Almacenarán el número de aciertos y fallos
        String numeroSecreto = masterMind.getNumeroSecreto();
        //numeroSecreto se compará con el intento para definir cuantos aciertos y fallos hay
        for (int cont = 0; cont < intento.length(); cont++) {
            char letra = intento.charAt(cont);
            if (letra == numeroSecreto.charAt(cont))//if que da true si ambos carácteres coinciden en la misma posición
                bienColocados++;
            else if (numeroSecreto.indexOf(letra) != -1)//if que da true si ambos carácteres no coinciden en la misma posición
                malColocados++;
        }
        masterMind.getListaIntentos().add(new Intento(intento, bienColocados, malColocados));
        /*Añade a la lista de intentos un nuevo intento que contendrá los aciertos y fallos*/
        if (bienColocados == masterMind.getLongitud())//Si tiene 4 aciertos,gana
            masterMind.setEstadoJuego(EstadoJuego.GANO);
        if (masterMind.getListaIntentos().size() >= masterMind.getIntentos())
        //Si hay 4 intentos realizados o más, perderá
            masterMind.setEstadoJuego(EstadoJuego.PERDIO);
    }

    private boolean cadenaConDuplicados(String cad) {
        //Este método comprueba si una cadena tiene carácteres repetidos
        for (int i = 0; i < cad.length(); i++) {
            char c = cad.charAt(i);
            if (cad.indexOf(c, i + 1) != -1)
                return true;
        }
        return false;
    }

    private String cadenaAlAzar(int tamanho) {//Genera una cantidad de números en String al azar
        Random random = new Random();
        String cad = "";
        int x;
        for (int i = 0; i < tamanho; i++) {
            x = random.nextInt(10);
            cad += Integer.toString(x);
        }
        return cad;
    }
    
}
