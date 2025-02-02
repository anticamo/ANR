# <center>Anti-Nether Roof</center>

Anti-Nether Roof (ANR) is a simple plugin that prevents players from accessing the Nether roof. It includes a permission for bypassing this restriction. If a player tries to access the Nether roof or is already there, they will be teleported to the nearest air block below. As a fail-safe, they will be teleported to Y=117 (10 blocks below) if no air block is found. These are mostly found on anarchy servers to stop people from dropping the TPS from loading to many chunks.

## <center>Setup Guide</center>

### Installation

1. Download the latest version from the [versions tab](https://modrinth.com/plugin/anti-nether-roof/versions/) and **NOT** from the download button at the top.
2. Place the file in your `/plugins` folder.
3. Restart the server.

### Permissions

The bypass permission is granted by default to players with operator status (op). To customize permissions, use a plugin like [LuckPerms](https://luckperms.net/).

<details>
<summary>Bypass Permission</summary>

**Permission**: `anr.bypass`  
**Description**: Allows a player to bypass Nether roof teleportation.  
**Default**: op

</details>

## <center>Known Issues</center>
[{player_name} moved too quickly! {coordinates}](https://github.com/anticamo/ANR/issues/1)
