package agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Configuracion;
import modelo.Resultados;

/**
 *
 * @author Isma2
 */
public class AgenteObservador extends Agent {

    private boolean bandera = true;
    double score;
    double mejorScore = Double.NEGATIVE_INFINITY;
    Configuracion mejorConfiguracion;

    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    class Comportamiento extends CyclicBehaviour {

        @Override
        public void action() {
            if (bandera) {
//                //ConfiguraciÃ³n inicial 
//                //int poblacion = (int) (Math.random() * 11) + 5;
//                int poblacion = 15;
//                int longCrom = 12;
//                int iteraccion = 15;
//                int evoluciones = 15;
//                System.out.println("Hola, soy el agente observador y escojo la mejor configuración");
//                Comunicacion.msj(ACLMessage.INFORM, getAgent(), "AgGenetico", null, new Configuracion(poblacion, longCrom, iteraccion, evoluciones), "CD-AO-AG");
//                bandera = false;

                // Configuración inicial
                System.out.println("Hola, soy el agente observador y escojo la mejor configuración");

                // Enviar 10 configuraciones al agente genético
                for (int i = 0; i < 10; i++) {
                    int poblacion = (int) (Math.random() * 11) + 5;
                    int longCrom = 12;
                    int iteraccion = (int) (Math.random() * 11) + 5;
                    int evoluciones = (int) (Math.random() * 11) + 5;
                    Configuracion config = new Configuracion(poblacion, longCrom, iteraccion, evoluciones);
                    Comunicacion.msj(ACLMessage.INFORM, getAgent(), "AgGenetico", null, config, "CD-AO-AG");
                }
                bandera = false;
            } else {
                try {
                    ACLMessage aclmsj = blockingReceive();
                    System.out.println("\nAGENTE OBSERVADOR");
                    String conversationId = aclmsj.getConversationId();
                    if (conversationId.equals("CD-AA-AO") && !bandera) {
                        Object[] contenido = (Object[]) aclmsj.getContentObject();
                        for (Object obj : contenido) {
                            Object[] resultadosConfiguracion = (Object[]) obj;
                            Resultados resultados = (Resultados) resultadosConfiguracion[0];
                            Configuracion configuracion = (Configuracion) resultadosConfiguracion[1];
                            System.out.println("Score: " + resultados.getScore());
                            System.out.println("Configuración: " + configuracion.toString());
                            System.out.println("--------------------------------------");

                            score = resultados.getScore();

                            // Verificar si el score actual es mejor que el mejor score hasta ahora
                            // o si es igual al mejor score pero con menor población, evoluciones o iteraciones
                            if (score > mejorScore
                                    || (score == mejorScore
                                    && (configuracion.getPoblacion() < mejorConfiguracion.getPoblacion()
                                    || configuracion.getEvoluciones() < mejorConfiguracion.getEvoluciones()
                                    || configuracion.getIteraccion() < mejorConfiguracion.getIteraccion()))) {
                                mejorScore = score;
                                mejorConfiguracion = configuracion;
                            }
                        }

                        // Imprimir la mejor configuración encontrada
                        System.out.println("Mejor configuración encontrada:");
                        System.out.println("Score: " + mejorScore);
                        System.out.println("Configuración: " + mejorConfiguracion.toString());
                        System.out.println("--------------------------------------");
                        //Enviar nueva configuración al agente genético
                        Comunicacion.msj(ACLMessage.INFORM, getAgent(), "AgGenetico", null, mejorConfiguracion, "CD-AO-AG");
                        blockingReceive();
                    }
                } catch (UnreadableException ex) {
                    Logger.getLogger(AgenteObservador.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("No serializado");
                }
            }
        }
    }
}
