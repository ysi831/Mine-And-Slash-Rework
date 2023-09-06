package com.robertx22.age_of_exile.database.data.profession.buffs;

import com.robertx22.age_of_exile.aoe_data.database.stats.Stats;
import com.robertx22.age_of_exile.aoe_data.database.stats.old.DatapackStats;
import com.robertx22.age_of_exile.database.data.StatMod;
import com.robertx22.age_of_exile.database.data.profession.CraftedItemHolder;
import com.robertx22.age_of_exile.database.data.profession.all.ProfessionProductItems;
import com.robertx22.age_of_exile.database.data.stats.types.resources.energy.Energy;
import com.robertx22.age_of_exile.database.data.stats.types.resources.energy.EnergyRegen;
import com.robertx22.age_of_exile.database.data.stats.types.resources.health.Health;
import com.robertx22.age_of_exile.database.data.stats.types.resources.health.HealthRegen;
import com.robertx22.age_of_exile.database.data.stats.types.resources.magic_shield.MagicShield;
import com.robertx22.age_of_exile.database.data.stats.types.resources.magic_shield.MagicShieldRegen;
import com.robertx22.age_of_exile.database.data.stats.types.resources.mana.Mana;
import com.robertx22.age_of_exile.database.data.stats.types.resources.mana.ManaRegen;
import com.robertx22.age_of_exile.uncommon.enumclasses.Elements;
import com.robertx22.age_of_exile.uncommon.enumclasses.PlayStyle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;

public class StatBuffs {

    // we create these independent of the actual serializables so we can init before vanilla items are registered
    public static HashSet<AlchemyBuff> ALCHEMY = new HashSet<>();
    public static HashSet<FoodBuff> COOKING = new HashSet<>();

    // have to manually then give recipes for each
    public static AlchemyBuff INT = new AlchemyBuff("int", "Intelligence", () -> DatapackStats.INT.mod(5, 15).percent()); // todo test if % more hp gives more hp
    public static AlchemyBuff DEX = new AlchemyBuff("dex", "Dexterity", () -> DatapackStats.DEX.mod(5, 15).percent());
    public static AlchemyBuff STR = new AlchemyBuff("str", "Strength", () -> DatapackStats.STR.mod(5, 15).percent());

    public static AlchemyBuff ARCANE = new AlchemyBuff("arcane", "Arcane", () -> Stats.STYLE_DAMAGE.get(PlayStyle.INT).mod(5, 25));
    public static AlchemyBuff MIGHT = new AlchemyBuff("might", "Strength", () -> Stats.ELEMENTAL_DAMAGE.get(Elements.Physical).mod(5, 25));
    public static AlchemyBuff CRIT = new AlchemyBuff("crit", "Criticals", () -> Stats.CRIT_DAMAGE.get().mod(10, 30));

    public static class AlchemyBuff {
        public String id;
        public String name;
        public Supplier<StatMod> mod;

        public AlchemyBuff(String id, String name, Supplier<StatMod> mod) {
            this.id = id;
            this.name = name;
            this.mod = mod;

            ALCHEMY.add(this);
        }

        public CraftedItemHolder getHolder() {
            return ProfessionProductItems.POTIONS.get(this);
        }
    }

    public static class FoodBuff {
        public String id;
        public String name;
        public Supplier<List<StatMod>> mod;

        public FoodBuff(String id, String name, Supplier<List<StatMod>> mod) {
            this.id = id;
            this.name = name;
            this.mod = mod;

            COOKING.add(this);
        }

        public CraftedItemHolder getHolder() {
            return ProfessionProductItems.FOODS.get(this);
        }
    }

    static StatMod outCombatRegen = Stats.OUT_OF_COMBAT_REGEN.get().mod(20, 50);

    public static FoodBuff HEALTH = new FoodBuff("life", "Life's Joy", () -> Arrays.asList(
            outCombatRegen,
            Health.getInstance().mod(5, 10).percent(),
            HealthRegen.getInstance().mod(5, 15).percent(),
            HealthRegen.getInstance().mod(1, 4)
    ));
    public static FoodBuff MANA = new FoodBuff("mana", "Mana's Inspiration", () -> Arrays.asList(
            outCombatRegen,
            Mana.getInstance().mod(5, 10).percent(),
            ManaRegen.getInstance().mod(5, 15).percent(),
            ManaRegen.getInstance().mod(1, 4)
    ));
    public static FoodBuff ENERGY = new FoodBuff("energy", "Boundlessness of Energy", () -> Arrays.asList(
            outCombatRegen,
            Energy.getInstance().mod(5, 10).percent(),
            EnergyRegen.getInstance().mod(5, 15).percent(),
            EnergyRegen.getInstance().mod(1, 4)
    ));
    public static FoodBuff MAGIC = new FoodBuff("magic", "Depth of Magic", () -> Arrays.asList(
            outCombatRegen,
            MagicShield.getInstance().mod(5, 10).percent(),
            MagicShieldRegen.getInstance().mod(5, 15).percent(),
            MagicShieldRegen.getInstance().mod(1, 4)
    ));

    public static void init() {

        for (AlchemyBuff al : ALCHEMY) {
            StatBuff buff = new StatBuff();
            buff.id = al.id;
            buff.mods.add(al.mod.get());
            buff.addToSerializables();
        }
        for (FoodBuff al : COOKING) {
            StatBuff buff = new StatBuff();
            buff.id = al.id;
            buff.mods.addAll(al.mod.get());
            buff.addToSerializables();
        }

    }
}