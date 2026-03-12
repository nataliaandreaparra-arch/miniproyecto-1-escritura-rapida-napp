package np.escriturarapida.view;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
/**
 * Utility interface for creating JavaFX stages with FXML loading.
 */

public interface ICreateStage {
    /**
     * Loads an FXML file and returns its loader.
     *
     * @param url the path to the FXML resource
     * @return the FXMLLoader instance
     * @throws IOException if the resource cannot be loaded
     */
    default FXMLLoader loadFXMLLoader(String url) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        loader.load();
        return loader;
    }
}
