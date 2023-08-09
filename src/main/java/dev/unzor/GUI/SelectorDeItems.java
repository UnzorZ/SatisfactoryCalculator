package dev.unzor.GUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;

public class SelectorDeItems extends JFrame {
    LoadingMessage loadingMessage = new LoadingMessage();
    public JPanel gridPanel;
    public JTextField searchField;

    // Lista original de objetos
    private ArrayList<ObjectPlaceholder> originalObjectList;
    // Mapa para almacenar paneles de botones según el nombre
    private Map<String, JPanel> buttonPanelMap = new HashMap<>();

    public SelectorDeItems(ArrayList<ObjectPlaceholder> objectList) {
        originalObjectList = new ArrayList<>(objectList); // Guardar la lista original

        setTitle("Selecciona un item");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel searchPanel = createSearchPanel();
        add(searchPanel, BorderLayout.NORTH);

        updateProgressBarInBackground(objectList);

        gridPanel = new JPanel(new GridLayout(0, 5, 10, 10)); // 5 columns, horizontal and vertical gaps of 10
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterAndRefresh(searchField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterAndRefresh(searchField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterAndRefresh(searchField.getText());
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterAndRefresh(searchField.getText());
            }
        });

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        return searchPanel;
    }

    private void filterAndRefresh(String searchText) {
        List<ObjectPlaceholder> filteredList = originalObjectList.stream()
                .filter(object -> object.getName().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());

        List<JPanel> newButtonPanels = new ArrayList<>();

        for (ObjectPlaceholder object : filteredList) {
            JPanel buttonPanel = buttonPanelMap.get(object.getName());
            if (buttonPanel == null) {
                buttonPanel = createButtonPanel(object);
                buttonPanelMap.put(object.getName(), buttonPanel);
            }
            newButtonPanels.add(buttonPanel);
        }

        SwingUtilities.invokeLater(() -> updateGrid(newButtonPanels));
    }

    private void updateGrid(List<JPanel> newButtonPanels) {
        gridPanel.removeAll();
        for (JPanel buttonPanel : newButtonPanels) {
            gridPanel.add(buttonPanel);
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private JPanel createButtonPanel(ObjectPlaceholder object) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));

        ImageIcon icon = loadImageIconFromURL(object.getImageURL(), 100, 100); // Scale to 100x100
        if (icon != null) {
            JButton button = new JButton(icon);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle button action if needed
                }
            });
            buttonPanel.add(button);
        }

        JLabel nameLabel = new JLabel(object.getName());
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(nameLabel);

        if (!object.getName().startsWith("alt-")) {
            buttonPanelMap.put(object.getName(), buttonPanel); // Agregar al mapa si no comienza con "alt-"
        }

        return buttonPanel;
    }


    private ImageIcon loadImageIconFromURL(String imageURL, int width, int height) {
        try {
            URL url = new URL(imageURL);
            BufferedImage originalImage = ImageIO.read(url);
            if (originalImage != null) {
                Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class ObjectPlaceholder {
        private String name;
        private String imageURL;

        public ObjectPlaceholder(String name, String imageURL) {
            this.name = name;
            this.imageURL = imageURL;
        }

        public String getName() {
            return name;
        }

        public String getImageURL() {
            return imageURL;
        }
    }

    private void updateProgressBarInBackground(ArrayList<ObjectPlaceholder> objectList) {
        JProgressBar progressBar = CraftGUI.CraftingProgressBar;
        JButton startButton = CraftGUI.selectItemButton;
        startButton.setEnabled(false);
        progressBar.setMaximum(objectList.size());

        loadingMessage.showLoadingDialog();

        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i < objectList.size(); i++) {
                    JPanel buttonPanel = createButtonPanel(objectList.get(i));
                    gridPanel.add(buttonPanel);
                    publish(i + 1); // Actualiza el valor de progreso
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                for (int value : chunks) {
                    progressBar.setValue(value); // Actualiza la barra de progreso
                }
            }

            @Override
            protected void done() {
                loadingMessage.hideLoadingDialog();

                Toolkit mipantalla = Toolkit.getDefaultToolkit();
                Dimension tamanoPantalla = mipantalla.getScreenSize();

                int alturapantalla = tamanoPantalla.height;
                int anchurapantalla = tamanoPantalla.width;


                startButton.setEnabled(true); // Habilita el botón al finalizar
                setBounds(anchurapantalla / 4 + 225, alturapantalla / 4, 850, 650);

                setVisible(true);
            }
        };

        worker.execute(); // Inicia el SwingWorker
    }

}

