package geometrical.anr.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public final class NetherRoofListener implements Listener {
    private static final String BYPASS_PERMISSION = "preventnetherroof.bypass";

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();

        if (to == null || player.hasPermission(BYPASS_PERMISSION)) {
            return;
        }

        if (to.getWorld() != null && to.getWorld().getEnvironment() == World.Environment.NETHER) {
            if (to.getY() > 127) {
                Location safeLocation = findSafeLocationBelow(to);

                if (safeLocation != null) {
                    if (!isSameBlock(from, safeLocation)) {
                        event.setTo(safeLocation);
                    }
                } else {
                    Location fallbackLocation = getFallbackLocation(to);
                    if (!isSameBlock(from, fallbackLocation)) {
                        event.setTo(fallbackLocation);
                    }
                }
            }
        }
    }

    private Location findSafeLocationBelow(Location startLocation) {
        World world = startLocation.getWorld();
        if (world == null) return null;

        int startY = (int) startLocation.getY();
        int x = startLocation.getBlockX();
        int z = startLocation.getBlockZ();

        for (int y = startY - 3; y >= world.getMinHeight(); y--) {
            Location checkLocation = new Location(world, x + 0.5, y, z + 0.5);
            Location belowLocation = new Location(world, x + 0.5, y - 1, z + 0.5);

            if (checkLocation.getBlock().getType() == Material.AIR &&
                    belowLocation.getBlock().getType().isSolid()) {

                checkLocation.setPitch(startLocation.getPitch());
                checkLocation.setYaw(startLocation.getYaw());
                return checkLocation;
            }
        }
        return null;
    }

    private Location getFallbackLocation(Location startLocation) {
        World world = startLocation.getWorld();
        if (world == null) return null;

        int fallbackY = 117;
        int x = startLocation.getBlockX();
        int z = startLocation.getBlockZ();

        Location fallbackLocation = new Location(world, x + 0.5, fallbackY, z + 0.5);

        fallbackLocation.setPitch(startLocation.getPitch());
        fallbackLocation.setYaw(startLocation.getYaw());

        return fallbackLocation;
    }

    private boolean isSameBlock(Location loc1, Location loc2) {
        if (loc1 == null || loc2 == null) return false;
        return loc1.getBlockX() == loc2.getBlockX() &&
                loc1.getBlockY() == loc2.getBlockY() &&
                loc1.getBlockZ() == loc2.getBlockZ();
    }
}