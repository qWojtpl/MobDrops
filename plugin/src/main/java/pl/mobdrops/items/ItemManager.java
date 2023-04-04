package pl.mobdrops.items;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class ItemManager {

    private final HashMap<String, CustomItem> items = new HashMap<>();

}
