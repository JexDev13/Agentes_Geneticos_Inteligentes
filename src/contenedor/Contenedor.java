package contenedor;

import agentes.AgenteAuxiliar;
import agentes.AgenteObservador;
import agentes.AgenteGenetico;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;

/*
 * @author Isma2
 */
public class Contenedor {

    public void crearContenedor(String host, int port, String name) {
        jade.core.Runtime runtime = jade.core.Runtime.instance();

        Profile p = new ProfileImpl(host, port, name);
        AgentContainer contenedorPrincipal = runtime.createMainContainer(p);
        crearAgentes(contenedorPrincipal);
    }

    private void crearAgentes(AgentContainer contenedorPrincipal) {
        try {
            System.out.println("Agentes agregados satisfactoriamente");
            System.out.println("------------------------------------");
            contenedorPrincipal.createNewAgent("AgAuxiliar", AgenteAuxiliar.class.getName(), null).start();
            contenedorPrincipal.createNewAgent("AgObservador", AgenteObservador.class.getName(), null).start();
            contenedorPrincipal.createNewAgent("AgGenetico", AgenteGenetico.class.getName(), null).start();
        } catch (StaleProxyException ex) {
            System.out.println("Error al agregar los agentes");
            System.out.print(ex);
        }
    }
}
