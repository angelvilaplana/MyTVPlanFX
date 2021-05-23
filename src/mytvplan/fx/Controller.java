package mytvplan.fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import mytvplan.model.*;
import mytvplan.services.DeleteVideo;
import mytvplan.services.GetVideo;
import mytvplan.services.PostVideo;
import mytvplan.services.PutVideo;
import mytvplan.utils.MessageUtils;
import mytvplan.utils.ServiceUtils;

import java.io.IOException;

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

    private Video videoField;

    private boolean isSelectFilter;

    @FXML
    private void initialize() {
        isSelectFilter = false;
        setListVideos("");
        listenerSelectVideo();

        listenerFilter(cbFilterType, "/type");
        listenerFilter(cbFilterPlatform, "/platform");
        listenerFilter(cbFilterCategory, "/category");
        listenerFilter(cbFilterRating, "/rating");

        setCb(cbFilterType, TypeVideo.values(), true);
        setCb(cbFilterPlatform, PlatformVideo.values(), true);
        setCb(cbFilterCategory, CategoryVideo.values(), true);
        setCb(cbFilterRating, RatingVideo.values(), true);

        setCb(cbType, TypeVideo.values(), false);
        setCb(cbPlatform, PlatformVideo.values(), false);
        setCb(cbCategory, CategoryVideo.values(), false);
        setCb(cbRating, RatingVideo.values(), false);
    }

    private void setListVideos(String path) {
        deselectFilters(path);

        try {
            listVideos.getSelectionModel().clearSelection();
            listVideos.getItems().clear();
            GetVideo getVideo = ServiceUtils.getVideos(path);
            if (!getVideo.isOk()) {
                MessageUtils.showError("Error", getVideo.getErrorMessage());
                return;
            }
            listVideos.getItems().addAll(getVideo.getResponse());
        } catch (IOException e) {
            MessageUtils.showError("Error", e.getMessage());
        }
    }

    private void listenerSelectVideo() {
        listVideos.getSelectionModel().selectedItemProperty().addListener((observableValue, lastVideo, actualVideo) -> {
            if (actualVideo != null) {
                videoField = actualVideo;
                tfTitle.setText(actualVideo.getTitle());
                cbType.getSelectionModel().select(actualVideo.getType());
                cbPlatform.getSelectionModel().select(actualVideo.getPlatform());
                cbCategory.getSelectionModel().select(actualVideo.getCategory());
                cbRating.getSelectionModel().select(actualVideo.getRating());
            }
        });
    }

    private void listenerFilter(ComboBox<InterfaceData> cbFilter, String path) {
        cbFilter.getSelectionModel().selectedItemProperty().addListener((obs, lastFilter, actualFilter) -> {
            if (!isSelectFilter) {
                isSelectFilter = true;
                deselectFilters(path);
                if (actualFilter.getValue().equals("all")) {
                    updatePane("");
                    return;
                }
                updatePane(path + "/" + actualFilter.getValue());
            }
        });
    }

    private void updatePane(String pathVideos) {
        videoField = null;
        setListVideos(pathVideos);
        tfTitle.clear();
        cbType.getSelectionModel().clearSelection();
        cbPlatform.getSelectionModel().clearSelection();
        cbCategory.getSelectionModel().clearSelection();
        cbRating.getSelectionModel().clearSelection();
    }

    private void deselectFilters(String path) {
        if (path.indexOf("/type") == 0) {
            cbFilterPlatform.getSelectionModel().select(0);
            cbFilterCategory.getSelectionModel().select(0);
            cbFilterRating.getSelectionModel().select(0);
            isSelectFilter = false;
            return;
        } else if (path.indexOf("/platform") == 0) {
            cbFilterType.getSelectionModel().select(0);
            cbFilterCategory.getSelectionModel().select(0);
            cbFilterRating.getSelectionModel().select(0);
            isSelectFilter = false;
            return;
        } else if (path.indexOf("/category") == 0) {
            cbFilterType.getSelectionModel().select(0);
            cbFilterPlatform.getSelectionModel().select(0);
            cbFilterRating.getSelectionModel().select(0);
            isSelectFilter = false;
            return;
        } else if (path.indexOf("/rating") == 0) {
            cbFilterType.getSelectionModel().select(0);
            cbFilterPlatform.getSelectionModel().select(0);
            cbFilterCategory.getSelectionModel().select(0);
            isSelectFilter = false;
            return;
        }

        cbFilterType.getSelectionModel().select(0);
        cbFilterPlatform.getSelectionModel().select(0);
        cbFilterCategory.getSelectionModel().select(0);
        cbFilterRating.getSelectionModel().select(0);
        isSelectFilter = false;
    }

    private boolean updateVideoField() {
        String id = videoField == null ? null : videoField.getId();

        if (tfTitle.getText() == null || tfTitle.getText().isEmpty()) {
            MessageUtils.showError("Error", "The title field is empty");
            return false;
        }
        String title = tfTitle.getText();

        if (cbType.getValue() == null) {
            MessageUtils.showError("Error", "The type field is empty");
            return false;
        }
        TypeVideo type = (TypeVideo) cbType.getValue();

        if (cbPlatform.getValue() == null) {
            MessageUtils.showError("Error", "The platform field is empty");
            return false;
        }
        PlatformVideo platform = (PlatformVideo) cbPlatform.getValue();

        if (cbCategory.getValue() == null) {
            MessageUtils.showError("Error", "The category field is empty");
            return false;
        }
        CategoryVideo category = (CategoryVideo) cbCategory.getValue();

        if (cbRating.getValue() == null) {
            MessageUtils.showError("Error", "The rating field is empty");
            return false;
        }
        RatingVideo rating = (RatingVideo) cbRating.getValue();

        videoField = new Video(id, title, type, platform, category, rating);
        return true;
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
        if (!updateVideoField()) {
            return;
        } else if (listVideos.getSelectionModel().getSelectedItem() == null) {
            MessageUtils.showError("Error", "You must have a video selected to update");
            return;
        }

        try {
            DeleteVideo deleteVideo = ServiceUtils.deleteVideo(videoField);
            if (deleteVideo.isOk()) {
                MessageUtils.showMessage("Success", "Video deleted successfully");
                updatePane("");
                return;
            }
            MessageUtils.showMessage("Error", deleteVideo.getErrorMessage());
        } catch (IOException e) {
            MessageUtils.showError("Error", e.getMessage());
        }
    }

    @FXML
    void handleSave() {
        if (!updateVideoField()) {
            return;
        }

        try {
            PostVideo postVideo = ServiceUtils.saveVideo(videoField);
            if (postVideo.isOk()) {
                MessageUtils.showMessage("Success", "Video saved successfully");
                updatePane("");
                return;
            }
            MessageUtils.showMessage("Error", postVideo.getErrorMessage());
        } catch (IOException e) {
            MessageUtils.showError("Error", e.getMessage());
        }
    }

    @FXML
    void handleUpdate() {
        if (!updateVideoField()) {
            return;
        } else if (listVideos.getSelectionModel().getSelectedItem() == null) {
            MessageUtils.showError("Error", "You must have a video selected to update");
            return;
        }

        try {
            PutVideo putVideo = ServiceUtils.updateVideo(videoField);
            if (putVideo.isOk()) {
                MessageUtils.showMessage("Success", "Video updated successfully");
                updatePane("");
                return;
            }
            MessageUtils.showMessage("Error", putVideo.getErrorMessage());
        } catch (IOException e) {
            MessageUtils.showError("Error", e.getMessage());
        }
    }

}
