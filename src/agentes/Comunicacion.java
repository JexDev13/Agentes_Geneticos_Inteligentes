package agentes;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author Isma2
 */
public class Comunicacion {

    public static void msj(
            int tipoMSJ,
            Agent emisor,
            String receptor,
            String conetenidoStr,
            Serializable contenidoObj,
            String conversationID) {
        
        ACLMessage acl = new ACLMessage(tipoMSJ);

        AID receptorID = new AID();
        receptorID.setLocalName(receptor);
        acl.addReceiver(receptorID);
        acl.setSender(emisor.getAID());
        acl.setLanguage(FIPANames.ContentLanguage.FIPA_SL);

        if (conetenidoStr == null) {
            try {
                acl.setContentObject(contenidoObj);
            } catch (IOException ex) {
                System.out.println("Contenido no serializado");
                System.out.println(ex);
            }
        } else {
            acl.setContent(conetenidoStr);
        }

        acl.setConversationId(conversationID);

        emisor.send(acl);

    }

}
