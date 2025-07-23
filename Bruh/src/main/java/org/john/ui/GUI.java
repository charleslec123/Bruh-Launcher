package org.john.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.john.download.fabric.Fabric;
import org.john.download.forge.Forge;
import org.john.download.rawGame.DownloadRawGame;
import org.john.launch.rawGame.LaunchRawGame;
import org.to2mbn.jmccc.auth.OfflineAuthenticator;
import org.to2mbn.jmccc.mcdownloader.provider.fabric.FabricDownloadProvider;
import org.to2mbn.jmccc.mcdownloader.provider.forge.ForgeDownloadProvider;
import org.to2mbn.jmccc.option.LaunchOption;
import org.to2mbn.jmccc.option.MinecraftDirectory;
import org.to2mbn.jmccc.option.WindowSize;

import org.john.global.globalVariables;

import java.nio.file.*;

public class GUI extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        try {
            Button aboutButton = new Button("About");
            Button launchGame = new Button("Launch");
            Button downloadForge = new Button("Download Forge");
            TextField maxMemoryField = new TextField("");
            maxMemoryField.setPromptText("Max memory");
            TextField minMemoryTextField = new TextField("");
            minMemoryTextField.setPromptText("Min Memory: ");
            CheckBox fullScreenCheckBox = new CheckBox("Full Screen");
            TextField gameVersion = new TextField("");
            gameVersion.setPromptText("Game Version: ");
            TextField username = new TextField("");
            Button downlaodGameButton = new Button("Download Game");
            Button downloadFabric = new Button("Download Fabric");
            CheckBox normalDownload = new CheckBox("Normal download");
            CheckBox downloadIncrementally = new CheckBox("downloadIncrementally");
            username.setPromptText("Game username");
            aboutButton.setOnAction(actionEvent -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("About");
                String body = "This minecraft launcher is create by john chen. " +
                        "Using JMCCC and javafx. Version " + globalVariables.launcherVersion;
                alert.setX(500);
                alert.setY(600);
                alert.setContentText(body);
            });
            downloadForge.setOnAction(actionEvent -> { // FORGE
                ForgeDownloadProvider forgeDownloadProvider = new ForgeDownloadProvider();
                Forge forge = new Forge() {
                    @Override
                    public void downloadFabric(FabricDownloadProvider downloadProvider, String version, Boolean downloadIncrementally, Boolean download) {
                        // no need
                    }
                };
                try {
                    forge.downloadForge(forgeDownloadProvider, gameVersion.getText().trim(), downloadIncrementally.isSelected(), normalDownload.isSelected());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            downloadFabric.setOnAction(actionEvent -> { // FABRIC
                FabricDownloadProvider provider = new FabricDownloadProvider();
                Fabric fabric = new Fabric() {
                    @Override
                    public void downloadForge(ForgeDownloadProvider downloadProvider, String version, Boolean downloadIncrementally, Boolean download) {
                        // no need
                    }
                };
                fabric.downloadFabric(provider, gameVersion.getText().trim(), downloadIncrementally.isSelected(), normalDownload.isSelected());
            });
            launchGame.setOnAction(actionEvent ->{ // LAUNCH
                MinecraftDirectory dir = new MinecraftDirectory(".minecraft");

                Path dirPath = Paths.get(dir.getAbsolutePath());

                if (Files.exists(dirPath)) {
                    String version = gameVersion.getText().trim();
                    try {
                        int maxMem = Integer.parseInt(maxMemoryField.getText().trim());
                        int minMem = Integer.parseInt(minMemoryTextField.getText().trim());
                        boolean fullScreen = fullScreenCheckBox.isSelected();
                        LaunchOption option = new LaunchOption(
                                version,
                                new OfflineAuthenticator(username.getText()),
                                new MinecraftDirectory(".minecraft")
                        );
                        option.setMaxMemory(maxMem);
                        option.setMinMemory(minMem);
                        if (fullScreen) {
                            option.setWindowSize(WindowSize.fullscreen());
                        }
                        new LaunchRawGame(option);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("No game is founded");
                }
            });
            downlaodGameButton.setOnAction(actionEvent1 -> { // RAW GAME
                DownloadRawGame downloader = new DownloadRawGame();
                downloader.downloadRawGame(gameVersion.getText().trim());

                System.out.println("Downloading");
            });
            HBox box = new HBox(launchGame, maxMemoryField,
                    minMemoryTextField, fullScreenCheckBox,
                    gameVersion, username, downlaodGameButton, aboutButton, downloadForge, downloadFabric, downloadIncrementally, normalDownload);
            Scene scene = new Scene(box, 800, 800);
            stage.setScene(scene);
            stage.setTitle("BRUH LAUNCHER");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
