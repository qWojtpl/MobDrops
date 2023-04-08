package pl.mobdrops.mobs;

import lombok.Getter;
import pl.mobdrops.MobDrops;
import pl.mobdrops.items.CustomItem;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MobDrop {

    private final String itemID;
    private final double percentage;
    private final int countMin;
    private final int countMax;
    private final boolean firework;
    private final List<Integer> fireworkColor;
    private final boolean lootBonus;
    private final CustomItem customItem;

    public MobDrop(String itemID, double percentage, int countMin, int countMax,
                   boolean firework, List<Integer> fireworkColor, boolean lootBonus) {
        this.itemID = itemID;
        this.percentage = percentage;
        this.countMin = countMin;
        this.countMax = countMax;
        this.firework = firework;
        this.lootBonus = lootBonus;
        if(fireworkColor.size() != 3) {
            List<Integer> color = new ArrayList<>();
            color.add(0);
            color.add(0);
            color.add(0);
            this.fireworkColor = color;
        } else {
            this.fireworkColor = fireworkColor;
        }
        this.customItem = MobDrops.getInstance().getItemManager().getItems().getOrDefault(itemID, null);
        if(this.customItem == null) {
            MobDrops.getInstance().getLogger().severe("Cannot find custom item: " + itemID + "!");
        }
    }

}
