package pl.mobdrops.permissions;

import org.bukkit.permissions.Permission;
import pl.mobdrops.MobDrops;

import java.util.HashMap;

public class PermissionManager {

    private final HashMap<String, Permission> permissions = new HashMap<>();

    public PermissionManager() {
        registerPermission("mobdrops.manage", "Manage item drops");
    }

    public void registerPermission(String permission, String description) {
        Permission perm = new Permission(permission, description);
        MobDrops.getInstance().getServer().getPluginManager().addPermission(perm);
        permissions.put(permission, perm);
    }

    public Permission getPermission(String permission) {
        return permissions.getOrDefault(permission, null);
    }

}
