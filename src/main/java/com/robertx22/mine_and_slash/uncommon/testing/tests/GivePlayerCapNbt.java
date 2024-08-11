package com.robertx22.mine_and_slash.uncommon.testing.tests;

import com.robertx22.mine_and_slash.uncommon.testing.CommandTest;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.nbt.CompoundTag;

public class GivePlayerCapNbt extends CommandTest {

    @Override
    public void run(ServerPlayer player) {

        CompoundTag tag = new CompoundTag();
        player.saveWithoutId(tag);

        System.out.print(tag.toString());
    }

    @Override
    public String GUID() {
        return "print_player_nbt";
    }
}