package mytvplan.fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import mytvplan.model.*;

public class Controller {

    @FXML
    private ComboBox<InterfaceData> cbFilterType;

    @FXML
    private ComboBox<InterfaceData> cbFilterPlatform;

    @FXML
    private ComboBox<InterfaceData> cbFilterCategory;

    @FXML
    private ComboBox<InterfaceData> cbFilterRating;

    @FXML
    private ListView<Video> listVideos;

    @FXML
    private TextField tfTitle;

    @FXML
    private ComboBox<InterfaceData> cbType;

    @FXML
    private ComboBox<InterfaceData> cbPlatform;

    @FXML
    private ComboBox<InterfaceData> cbCategory;

    @FXML
    private ComboBox<InterfaceData> cbRating;

    @FXML
    private void initialize() {
        setCb(cbFilterType, TypeVideo.values(), true);
        setCb(cbFilterPlatform, PlatformVideo.values(), true);
        setCb(cbFilterCategory, CategoryVideo.values(), true);
        setCb(cbFilterRating, RatingVideo.values(), true);

        setCb(cbType, TypeVideo.values(), false);
        setCb(cbPlatform, PlatformVideo.values(), false);
        setCb(cbCategory, CategoryVideo.values(), false);
        setCb(cbRating, RatingVideo.values(), false);
    }

    private void setCb(ComboBox<InterfaceData> cb, InterfaceData[] data, boolean optionAll) {
        ObservableList<InterfaceData> dataCollection = FXCollections.observableArrayList(data);
        if (!optionAll) {
            dataCollection.set(0, null);
        }

        cb.setItems(dataCollection);
        if (optionAll) {
            cb.getSelectionModel().select(0);
        }

        cb.setConverter(new StringConverter<>() {
            @Override
            public String toString(InterfaceData interfaceData) {
                if (!optionAll) {
                    return interfaceData == null ? null : interfaceData.getValue();
                } else {
                    return interfaceData == null ? null : "Show " + interfaceData.getValue();
                }
            }

            @Override
            public InterfaceData fromString(String s) {
                return null;
            }
        });
    }

    @FXML
    void handleDelete() {

    }

    @FXML
    void handleSave() {

    }

    @FXML
    void handleUpdate() {

    }

}
