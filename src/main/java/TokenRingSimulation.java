import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TokenRingSimulation {
    private static final int NUM_COMPUTERS = 10;
    private static final int NUM_STEPS = 10;
    private static final boolean CLOCKWISE = true;
    private static final String[] MESSAGES = {
            "Mesaj de test",
    };

    public static void main(String[] args) {

        Computer[] computers = new Computer[NUM_COMPUTERS];
        Set<String> usedIPs = new HashSet<>();

        for (int i = 0; i < NUM_COMPUTERS; i++) {
            String ip;
            do {
                ip = generateRandomIP();
            } while (usedIPs.contains(ip));
            usedIPs.add(ip);
            computers[i] = new Computer(ip);
        }

        // Creare jeton
        Token token = new Token();
        //pozitie jeteon intiala
        int currentPosition = 0;

        // Rularea simulării pentru un număr specificat de pași
        for (int step = 0; step < NUM_STEPS; step++) {
            System.out.println("Pas " + (step + 1) + ":");

            // Afișează starea tuturor calculatoarelor
            for (int i = 0; i < NUM_COMPUTERS; i++) {
                System.out.println("C" + i + "(" + computers[i] + ")");
            }

            // sursa si destinatie
            if (token.isFree()) {
                int sourceIndex = new Random().nextInt(NUM_COMPUTERS);
                int destIndex;
                do {
                    destIndex = new Random().nextInt(NUM_COMPUTERS);
                } while (destIndex == sourceIndex);  // sursa != destinatie

                String sourceIP = computers[sourceIndex].getIpAddress();
                String destIP = computers[destIndex].getIpAddress();
                String message = MESSAGES[new Random().nextInt(MESSAGES.length)];

                System.out.println("Sursa: C" + sourceIndex + " Destinația: C" + destIndex);

                // mutam jetonul la sursa
                while (currentPosition != sourceIndex) {
                    System.out.println("C" + currentPosition + ": Muta jetonul");
                    currentPosition = moveToken(currentPosition);
                }

                // jetonul ajuns la sursa
                System.out.println("C" + sourceIndex + ": Am preluat jetonul");
                token.setSourceIP(sourceIP);
                token.setDestinationIP(destIP);
                token.setMessage(message);
                token.setFree(false);
                token.setHasArrived(false);
            }

            // Deplasarea jetonului prin rețea
            boolean completedCycle = false;

            while (!completedCycle) {
                // mutam jetonul la urmatorul calculator
                currentPosition = moveToken(currentPosition);

                //verificam daca am aj la destinatie
                if (!token.hasArrived() && computers[currentPosition].getIpAddress().equals(token.getDestinationIP())) {
                    System.out.println("C" + currentPosition + ": Am ajuns la destinație");
                    computers[currentPosition].setBuffer(token.getMessage());
                    token.setHasArrived(true);
                    System.out.println("C" + currentPosition + ": Muta jetonul");
                }
                //verificam daca a ajus inapoi la sursa
                else if (token.hasArrived() && computers[currentPosition].getIpAddress().equals(token.getSourceIP())) {
                    System.out.println("C" + currentPosition + ": Am ajuns înapoi");
                    token.reset();
                    completedCycle = true;
                }
                // trece mai departe
                else {
                    System.out.println("C" + currentPosition + ": Muta jetonul");
                }


            }

            System.out.println();
        }
    }

    // Funcție pentru mutarea jetonului în direcția specificată
    private static int moveToken(int currentPosition) {
        return CLOCKWISE ? (currentPosition + 1) % NUM_COMPUTERS : (currentPosition - 1 + NUM_COMPUTERS) % NUM_COMPUTERS;
    }

    // Funcție pentru generarea unei adrese IP aleatoare
    private static String generateRandomIP() {
        Random random = new Random();
        return "192.168.0." + random.nextInt(256);
    }
}
