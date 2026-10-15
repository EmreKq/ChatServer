
/**
 * Beschreiben Sie hier die Klasse ChatServer.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class ChatServer extends Server
{
    List<Benutzer> benutzer;

    public ChatServer(int pPort)
    {  
        super(pPort);
        benutzer.toFirst();
    }

    public void processMessage(String pClientIP, int pClientPort, String pMessage)
    {
        String[] splitMessage = pMessage.split(" ");
        String command = splitMessage[0].toUpperCase();
        switch (command) {
            case "Anmelden" ->{
                    if(!nameExistiert(splitMessage[1], benutzer)){
                        send(pClientIP, pClientPort, "+ERR"+ splitMessage[1]+ "existiert bereits");
                        return;
                    }
                    if (splitMessage[1].equals(" ")){
                        send(pClientIP, pClientPort, "+ERR"+ splitMessage[1] + "existiert bereits");
                        return;
                    }
                    benutzer.setContent(new Benutzer(splitMessage[1], pClientIP, pClientPort));
                    benutzer.next();
                    send(pClientIP, pClientPort, "+OK"+ "erfolgreich angemeldet");
                    angemeldet= true;
                }
            case "Abmelden" -> {
                    if(!angemeldet){
                        send(pClientIP, pClientPort, "+ERR"+"nicht angemeldet");
                        return;
                    }
                    closeConnection(pClientIP, pClientPort);
                } 
            case "" -> {

                }
            case "" -> {

                } 
            default -> {
                    send(pClientIP, pClientPort, "-ERR unbekannter Befehl");
                }
        }   
    }

    public String ListeToString(List<Benutzer> benutzer)
    {
        String ausgabe ="";
        benutzer.toFirst();
        while(benutzer.hasAccess()){
            ausgabe = ausgabe+ " " + benutzer.getContent().getName();
            benutzer.next();
        }
        return ausgabe;
    }

    public boolean nameExistiert(String pName, List<Benutzer> pBenutzer){
        while(benutzer.hasAccess()){
            if(benutzer.getContect().getName()){
                return true;
            }
            benutzer.next();
        }
        return false;
    }

