package com.familychorelocationtracker.views;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class ChoresPageView extends Form {
    private final Map<String, ArrayList<String>> choreMap = new HashMap<>();
    private final ArrayList<String> completedChores = new ArrayList<>();

    private final ComboBox<String> familyMemberComboBox = new ComboBox<>
            (new DefaultListModel<>(Arrays.asList("Evelyn", "Liam", "Jasmine", "Lucas")));

    public ChoresPageView() {
        super(new BoxLayout(BoxLayout.Y_AXIS));

        choreMap.put("Evelyn", new ArrayList<>());
        choreMap.put("Liam", new ArrayList<>());
        choreMap.put("Jasmine", new ArrayList<>());
        choreMap.put("Lucas", new ArrayList<>());

        // Add initial chores
        familyMemberComboBox.setSelectedIndex(0);
        addChore("Clean the kitchen");
        familyMemberComboBox.setSelectedIndex(1);
        addChore("Take out the trash");
        familyMemberComboBox.setSelectedIndex(2);
        addChore("Do the laundry");
        familyMemberComboBox.setSelectedIndex(3);
        addChore("Cook dinner");
        familyMemberComboBox.setSelectedIndex(1);
        addChore("Wash the car");
        familyMemberComboBox.setSelectedIndex(3);
        addChore("Mow the lawn");

        show();
    }

    private void addChore(String chore) {
        ArrayList<String> chores = choreMap.get(familyMemberComboBox.getSelectedItem());
        if (chores != null) {
            chores.add(chore);
            refreshChores();
        }
    }

    private void completeChore(String familyMember, String chore) {
        ArrayList<String> chores = choreMap.get(familyMember);
        if (chores != null) {
            chores.remove(chore);
            completedChores.add(chore + " - " + familyMember);
            refreshChores();
        }
    }

    private void refreshChores() {
        removeAll();

        TextField newChoreField = new TextField("", "Enter new chore");
        Button addButton = new Button("Add");

        addButton.addActionListener(e -> {
            String newChore = newChoreField.getText().trim();
            if (!newChore.isEmpty()) {
                addChore(newChore);
                newChoreField.clear();
            }
        });

        add(newChoreField);
        add(familyMemberComboBox);
        add(addButton);


        for (Map.Entry<String, ArrayList<String>> entry : choreMap.entrySet()) {
            String familyMember = entry.getKey();
            ArrayList<String> chores = entry.getValue();
            if (!chores.isEmpty()) {
                add(createLabel("Chores for " + familyMember + ":"));
                for (String chore : chores) {
                    Button choreButton = new Button(chore);
                    choreButton.addActionListener(e -> completeChore(familyMember, chore));
                    add(choreButton);
                }
            }
        }
        if (!completedChores.isEmpty()) {
            add(createLabel("Completed Chores:"));
            for (String chore : completedChores) {
                add(new Label(chore));
            }
        }
        revalidate();
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.getStyle().setFgColor(0x0000);
        label.getStyle().setBgColor(0x0000);
        label.getStyle().setMargin(5, 5, 5, 5);


        return label;
    }

}
