package agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Configuracion;
import modelo.Resultados;

/**
 *
 * @author Isma2
 */
public class AgenteAuxiliar extends Agent {

    ArrayList<Object[]> resultadosConfiguraciones = new ArrayList<>();

    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    class Comportamiento extends CyclicBehaviour {

        @Override
        public void action() {
            try {
                ACLMessage aclmsj = blockingReceive();
                String conversationId = aclmsj.getConversationId();
                System.out.println("\nAGENTE AUXILIAR");
                Object[] contenido = (Object[]) aclmsj.getContentObject();
                if (conversationId.equals("CD-AG-AA")) {
                    Resultados resultados = (Resultados) contenido[0];
                    Configuracion configuracion = (Configuracion) contenido[1];
                    System.out.println("Resultados recibidos: CD-AG-AA");
                    System.out.println("Resultados:");
                    System.out.println(resultados.toString());
                    System.out.println("Configuración:");
                    System.out.println(configuracion.toString());
                    System.out.println("--------------------------------------");

                    // Agregar el resultado y configuración al ArrayList
                    resultadosConfiguraciones.add(new Object[]{resultados, configuracion});

                    // Ordenar la lista en base al score de forma ascendente
                    resultadosConfiguraciones.sort(Comparator.comparingDouble(o -> ((Resultados) o[0]).getScore()));

                    System.out.println("Enviar configuracion y score al agente observador");
                    Comunicacion.msj(ACLMessage.INFORM, getAgent(), "AgObservador", null, resultadosConfiguraciones.toArray(), "CD-AA-AO");
                }
            } catch (UnreadableException ex) {
                Logger.getLogger(AgenteGenetico.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("No serializado");
            }
        }
    }
}
