package np.escriturarapida.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Implements the word manager using a list of predefined random words.
 * Provides word generation, validation, and scoring logic.
 */

public class RandomWords implements IWordManager{
    private final GameModel gM;
    private final ArrayList<String> randomWords;
    private String currentWord = "";


    /**
     * Creates a new RandomWords manager linked to the given game model.
     *
     * @param gM the game model to update score and level
     */
    public RandomWords(GameModel gM){
        this.gM = gM;
        this.randomWords = new ArrayList<>();
    }

    /** Initializes the list of random words and shuffles them. */
    private void initializeWords(){
        randomWords.add("arriba");
        randomWords.add("llamo");
        randomWords.add("Prohibido");
        randomWords.add("insistencia");
        randomWords.add("decisivo");
        randomWords.add("motor");
        randomWords.add("decepcionar");
        randomWords.add("amenaza");
        randomWords.add("dividir");
        randomWords.add("desierto");
        randomWords.add("reluctancia");
        randomWords.add("terapeuta");
        randomWords.add("cueva");
        randomWords.add("diamante");
        randomWords.add("píldora");
        randomWords.add("crítica");
        randomWords.add("pollo");
        randomWords.add("colbón");
        randomWords.add("ganadores");
        randomWords.add("constante");
        randomWords.add("abadía");
        randomWords.add("micrófono");
        randomWords.add("Aleatoriamente");
        randomWords.add("Agraciada");
        randomWords.add("oportunidades");
        randomWords.add("embutido");
        randomWords.add("Investigadores");
        randomWords.add("omisión");
        randomWords.add("Fotografía");
        randomWords.add("filosofía");
        randomWords.add("diálogo");
        randomWords.add("vacío");
        randomWords.add("vaca");
        randomWords.add("perro");
        randomWords.add("champán");
        randomWords.add("Embarcarse");
        randomWords.add("atrás");
        randomWords.add("Ejecutar");
        randomWords.add("Restricción");
        randomWords.add("suministrar");
        randomWords.add("finanzas claras");
        randomWords.add("suposición");
        randomWords.add("excavar");
        randomWords.add("hemisferio");
        randomWords.add("a continuación");
        randomWords.add("disparo");
        randomWords.add("medalla de oro");
        randomWords.add("círculo");
        randomWords.add("ejecución");
        randomWords.add("león");

        Collections.shuffle(randomWords);
    }

    @Override
    /** {@inheritDoc} */
    public void generateWord(){
        if(randomWords.isEmpty())
        {
            initializeWords();
        }
        this.currentWord = randomWords.remove(0);
    }

    @Override
    /** {@inheritDoc} */
    public boolean verifyWord(String wordText){
        if(wordText.equals(currentWord)) {
            gM.setCurrentScore(currentWord.length());
            gM.setCurrentLevel();

            return true;
        }
        else
            return false;
    }

    @Override
    /** {@inheritDoc} */
    public String getCurrentWord() {
        return currentWord;
    }



}
