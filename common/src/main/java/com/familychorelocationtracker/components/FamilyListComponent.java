package com.familychorelocationtracker.components;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FamilyListComponent extends Container {
    private final Container familyListContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    private String title;
    private final HashMap<String, Character> familyMembers = new HashMap<>();
    private final Container choresToDo = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    private final List<String> chores = Arrays.asList("Wash the car", "Clean the kitchen", "Take out the trash",
            "Mow the lawn", "Do the laundry", "Cook dinner");

    public FamilyListComponent() {
        super(new BoxLayout(BoxLayout.Y_AXIS));

        familyMembers.put("Evelyn", FontImage.MATERIAL_WOMAN);
        familyMembers.put("Liam", FontImage.MATERIAL_MAN);
        familyMembers.put("Jasmine", FontImage.MATERIAL_GIRL);
        familyMembers.put("Lucas", FontImage.MATERIAL_BOY);

        getAllStyles().setPadding(5, 10, 10, 10);

        add(BoxLayout.encloseYCenter(BoxLayout.encloseXCenter(choresToDo)));
        add(BoxLayout.encloseYCenter(BoxLayout.encloseXCenter(familyListContainer)));
    }

    public void setFamilyListComponent(String title) {
        this.title = title;

        Label containerTitle = new Label(title);
        containerTitle.setMaterialIcon(FontImage.MATERIAL_FAMILY_RESTROOM);

        containerTitle.getAllStyles().setBgColor(0x0000);
        containerTitle.getAllStyles().setFgColor(0x0000);
        containerTitle.getAllStyles().setPadding(2, 2, 0, 0);

        familyListContainer.add(containerTitle);

        for (Map.Entry<String, Character> member : familyMembers.entrySet()) {
            Label label = new Label(member.getKey());
            label.setMaterialIcon(member.getValue());
            label.getAllStyles().setPadding(2, 2, 2, 3);
            familyListContainer.add(BoxLayout.encloseYCenter(BoxLayout.encloseXCenter(label)));
        }

        Label choreTitle = new Label("Incomplete chores:");

        choreTitle.getAllStyles().setBgColor(0x0000);
        choreTitle.getAllStyles().setFgColor(0x0000);
        choreTitle.getAllStyles().setPadding(0, 2, 2, 0);

        choresToDo.add(choreTitle);

        for (String chore : chores) {
            Label label = new Label(chore);
            label.setMaterialIcon(FontImage.MATERIAL_TASK);
            label.getAllStyles().setPadding(1, 2, 0, 0);
            choresToDo.add(BoxLayout.encloseYCenter(BoxLayout.encloseXCenter(label)));
        }


    }
}
