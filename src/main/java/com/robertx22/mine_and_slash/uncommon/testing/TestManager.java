package com.robertx22.mine_and_slash.uncommon.testing;

import com.robertx22.mine_and_slash.uncommon.testing.tests.MobTypesTest;
import net.minecraft.server.level.ServerLevel;

public class TestManager {

    public static void RunAllTests(ServerLevel world) {

        if (false) {
            MobTypesTest.run(world);
        }

    }

}