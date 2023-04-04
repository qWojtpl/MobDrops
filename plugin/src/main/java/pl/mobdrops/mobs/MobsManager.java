package pl.mobdrops.mobs;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;

@Getter
public class MobsManager {

    private final HashMap<String, List<MobDrop>> mobs = new HashMap<>();

}
