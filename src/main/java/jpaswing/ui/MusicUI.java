package jpaswing.ui;

import jpaswing.controller.MusicController;
import jpaswing.entity.Music;
import jpaswing.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class MusicUI extends JFrame {
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField nacionalityField;
    private JTextField genreField;
    private JButton btnFirst;
    private JButton btnPrevious;
    private JButton btnNext;
    private JButton btnLast;
    private JButton saveButton;
    private JPanel panel1;
    private Music music;
    private MusicController musicController;
    private MusicRepository musicRepository;

    public MusicUI(MusicRepository musicRepository, MusicController musicController) {
        this.musicController = musicController;
        this.musicRepository = musicRepository;
        setTitle("Singers");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 600);
        initComponents();
        music = musicRepository.findFirstByOrderByIdAsc();
        updateData();
    }
    private void updateData() {
        idField.setText(Long.toString(this.music.getId()));
        nameField.setText(this.music.getName());
        ageField.setText(Integer.toString(this.music.getAge()));
        nacionalityField.setText(this.music.getNacionality());
        genreField.setText(this.music.getGenre());
    }
    private void initComponents() {
        panel1 = new JPanel();
        idField = new JTextField(10);
        nameField = new JTextField(10);
        ageField = new JTextField(10);
        nacionalityField = new JTextField(10);
        genreField = new JTextField(10);
        JLabel l;

        this.setLayout(null);
        panel1.setLayout(null);
        panel1.setBounds(0, 0, 500, 650);

        l = new JLabel("ID:");
        l.setBounds(10,10,70,20);
        panel1.add(l);
        idField.setEnabled(false);
        idField.setBounds(10 + 80, 10, 200, 20);
        panel1.add(idField);

        l = new JLabel("Name:");
        l.setBounds(10, 40, 70, 20);
        panel1.add(l);
        idField.setEnabled(false);
        nameField.setBounds(10 + 80, 40, 200, 20 );
        panel1.add(nameField);

        l = new JLabel("Age:");
        l.setBounds(10, 70, 70, 20);
        panel1.add(l);
        ageField.setBounds(10 + 80, 70, 200, 20 );
        panel1.add(ageField);

        l = new JLabel("Nacionality:");
        l.setBounds(10, 100, 70, 20);
        panel1.add(l);
        nacionalityField.setBounds(10 + 80, 100, 200, 20 );
        panel1.add(nacionalityField);

        l = new JLabel("Genre:");
        l.setBounds(10, 130, 70, 20);
        panel1.add(l);
        genreField.setBounds(10 + 80, 130, 200, 20 );
        panel1.add(genreField);

        btnFirst = new JButton("<<");
        btnFirst.addActionListener(e -> first());
        btnPrevious = new JButton("<");
        btnPrevious.addActionListener(e -> previous());
        btnNext = new JButton(">");
        btnNext.addActionListener(e -> next());
        btnLast = new JButton(">>");
        btnLast.addActionListener(e -> last());
        btnFirst.setBounds(20, 260, 60,40);
        panel1.add(btnFirst);

        btnPrevious.setBounds(100, 260, 60,40);
        panel1.add(btnPrevious);

        btnNext.setBounds(180, 260, 60,40);
        panel1.add(btnNext);

        btnLast.setBounds(260, 260, 60,40);
        panel1.add(btnLast);

        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveEntity());
        saveButton.setBounds(120, 350, 100, 40);
        panel1.add(saveButton);

        this.add(panel1);
    }
    private void next(){
        this.music = musicController.next().orElse(null);
        updateData();
    }

    private void previous(){
        this.music = musicController.previous().orElse(null);
        updateData();
    }

    private void last(){
        this.music = musicController.last().orElse(null);
        updateData();
    }

    private void first(){
        this.music = musicController.first().orElse(null);
        updateData();
    }

    private void saveEntity() {
        music.setName(nameField.getText());
        music.setAge(Integer.parseInt(ageField.getText()));
        music.setNacionality(nacionalityField.getText());
        music.setGenre(genreField.getText());
        this.musicRepository.save(music);
        JOptionPane.showMessageDialog(this, "Entity saved successfully!");
    }


}
