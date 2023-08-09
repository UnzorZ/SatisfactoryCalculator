package dev.unzor.GUI;

import javax.swing.*;
import java.awt.*;

public class LoadingMessage {
    private JDialog loadingDialog;
    public void showLoadingDialog() {
        loadingDialog = new JDialog();
        loadingDialog.setSize(200, 100);
        loadingDialog.setLocationRelativeTo(null);

        ImageIcon loadingIcon = loadImageIconFromResource("/loading.gif");
        JLabel iconLabel = new JLabel(loadingIcon);
        JLabel loadingLabel = new JLabel("Loading, please wait...");
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentPanel.add(iconLabel);
        contentPanel.add(loadingLabel);

        loadingDialog.getContentPane().add(contentPanel);
        loadingDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        loadingDialog.setVisible(true);
    }

    public void hideLoadingDialog() {
        loadingDialog.setVisible(false);
        if (loadingDialog != null) {
            loadingDialog.dispose();
        }
    }
    public static ImageIcon loadImageIconFromResource(String resourcePath) {
        ImageIcon icon = new ImageIcon(LoadingMessage.class.getResource(resourcePath));
        return icon;
    }
}