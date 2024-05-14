package agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Configuracion;
import modelo.Genetica;
import modelo.Resultados;

/**
 *
 * @author Isma2
 */
public class AgenteGenetico extends Agent {

    private boolean bandera = true;
    Configuracion configuracion;

    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    class Comportamiento extends CyclicBehaviour {

        @Override
        public void action() {
            try {
                if (bandera) {
                    System.out.println("Hola, soy el agente genético y uso un algoritmo genético para encontrar la mejor generación");
                    bandera = false;
                } else {
                    ACLMessage aclmsj = blockingReceive();
                    System.out.println("\nAGENTE GENÉTICO");
                    configuracion = (Configuracion) aclmsj.getContentObject();
                    String conversationId = aclmsj.getConversationId();
                    switch (conversationId) {
                        case "CD-AO-AG" -> {
                            Genetica gen = new Genetica();
                            System.out.println("Información recibida: CD-AO-AG");
                            System.out.println(configuracion.toString());
                            double[] resultados = gen.evolucionar(gen.get_Poblacion(gen.configuracionAG(
                                    configuracion.getPoblacion(),
                                    configuracion.getLongCrom())),
                                    configuracion.getEvoluciones(),
                                    configuracion.getIteraccion());
                            double score = resultados[0];
                            int X = (int) resultados[1];
                            int Y = (int) resultados[2];
                            Object[] contenido = {new Resultados(score, X, Y), configuracion};
                            System.out.println("Enviar resultados al agente auxiliar");
                            Comunicacion.msj(ACLMessage.INFORM, getAgent(), "AgAuxiliar", null, contenido, "CD-AG-AA");
                            System.out.println("------------------------------");
                        }
                    }
                }
            } catch (UnreadableException ex) {
                Logger.getLogger(AgenteGenetico.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("No serializado");
            }

        }
    }
}
