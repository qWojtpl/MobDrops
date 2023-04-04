package pl.mobdrops.mobs;

import lombok.Getter;
import pl.mobdrops.MobDrops;
import pl.mobdrops.items.CustomItem;

import java.util.List;

@Getter
public class MobDrop {

    private final String itemID;
    private final int percentage;
    private final int countMin;
    private final int countMax;
    private final boolean firework;
    private final List<Integer> fireworkColor;
    private final CustomItem customItem;

    public MobDrop(String itemID, int percentage, int countMin, int countMax, boolean firework, List<Integer> fireworkColor) {
        this.itemID = itemID;
        this.percentage = percentage;
        this.countMin = countMin;
        this.countMax = countMax;
        this.firework = firework;
        this.fireworkColor = fireworkColor;
        this.customItem = MobDrops.getInstance().getItemManager().getItems().getOrDefault(itemID, null);
        if(this.customItem == null) {
            MobDrops.getInstance().getLogger().severe("Cannot find custom item: " + itemID + "!");
        }
    }

}
