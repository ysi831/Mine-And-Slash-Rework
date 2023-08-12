package com.robertx22.age_of_exile.aoe_data.database.unique_gears.uniques.jewelry;

import com.robertx22.age_of_exile.aoe_data.database.base_gear_types.BaseGearTypes;
import com.robertx22.age_of_exile.aoe_data.database.stats.Stats;
import com.robertx22.age_of_exile.aoe_data.database.stats.old.DatapackStats;
import com.robertx22.age_of_exile.aoe_data.database.unique_gears.UniqueGearBuilder;
import com.robertx22.age_of_exile.database.data.StatMod;
import com.robertx22.age_of_exile.database.data.stats.types.core_stats.AllAttributes;
import com.robertx22.age_of_exile.database.data.stats.types.defense.Armor;
import com.robertx22.age_of_exile.database.data.stats.types.defense.DodgeRating;
import com.robertx22.age_of_exile.database.data.stats.types.generated.ElementalResist;
import com.robertx22.age_of_exile.database.data.stats.types.loot.TreasureQuality;
import com.robertx22.age_of_exile.database.data.stats.types.loot.TreasureQuantity;
import com.robertx22.age_of_exile.database.data.stats.types.offense.SkillDamage;
import com.robertx22.age_of_exile.database.data.stats.types.resources.health.Health;
import com.robertx22.age_of_exile.database.data.stats.types.resources.health.HealthRegen;
import com.robertx22.age_of_exile.database.data.stats.types.resources.mana.Mana;
import com.robertx22.age_of_exile.database.data.stats.types.resources.mana.ManaRegen;
import com.robertx22.age_of_exile.database.data.stats.types.special.SpecialStats;
import com.robertx22.age_of_exile.saveclasses.unit.ResourceType;
import com.robertx22.age_of_exile.uncommon.enumclasses.Elements;
import com.robertx22.age_of_exile.uncommon.enumclasses.ModType;
import com.robertx22.library_of_exile.registry.ExileRegistryInit;

import java.util.Arrays;

public class UniqueRings implements ExileRegistryInit {

    @Override
    public void registerAll() {
        UniqueGearBuilder.of(
                        "spring_blossoms",
                        "Spring Blossoms",
                        BaseGearTypes.RING)
                .setReplacesName()
                .stats(Arrays.asList(
                        new StatMod(10, 10, SpecialStats.HEAL_CLEANSE, ModType.FLAT),
                        new StatMod(5, 10, HealthRegen.getInstance(), ModType.PERCENT),
                        new StatMod(10, 15, Stats.HEAL_STRENGTH.get(), ModType.FLAT),
                        new StatMod(15, 25, new ElementalResist(Elements.Chaos), ModType.FLAT)
                ))

                .devComment("")
                .build();

        UniqueGearBuilder.of(
                        "autumn_harvest",
                        "Autumn Harvest",
                        BaseGearTypes.RING)
                .setReplacesName()

                .stats(Arrays.asList(
                        new StatMod(10, 20, new ElementalResist(Elements.Chaos), ModType.FLAT),
                        new StatMod(10, 20, new ElementalResist(Elements.Fire), ModType.FLAT),
                        new StatMod(10, 20, new ElementalResist(Elements.Cold), ModType.FLAT),
                        new StatMod(25, 25, SpecialStats.BETTER_FOOD_BUFFS, ModType.FLAT),
                        new StatMod(5, 10, Armor.getInstance(), ModType.PERCENT),
                        new StatMod(5, 10, DodgeRating.getInstance(), ModType.PERCENT),
                        new StatMod(2, 3, AllAttributes.getInstance(), ModType.FLAT)
                ))


                .devComment("")
                .build();

        UniqueGearBuilder.of(
                        "winter_chill",
                        "Winter Chill",
                        BaseGearTypes.RING)
                .setReplacesName()
                .stats(Arrays.asList(new StatMod(5, 10, Health.getInstance(), ModType.FLAT),
                        new StatMod(20, 40, new ElementalResist(Elements.Cold), ModType.FLAT),
                        new StatMod(10, 25, Stats.ELEMENTAL_DAMAGE.get(Elements.Cold), ModType.FLAT),
                        new StatMod(5, 15, Mana.getInstance(), ModType.PERCENT),
                        new StatMod(5, 15, Stats.CRIT_CHANCE.get(), ModType.FLAT)
                ))
                .devComment("")
                .build();

        UniqueGearBuilder.of("summer_heat", "Summer Heat", BaseGearTypes.RING)
                .setReplacesName()
                .stats(Arrays.asList(new StatMod(5, 10, Health.getInstance(), ModType.FLAT),
                        new StatMod(20, 40, new ElementalResist(Elements.Fire), ModType.FLAT),
                        new StatMod(10, 25, Stats.ELEMENTAL_DAMAGE.get(Elements.Fire), ModType.FLAT),
                        new StatMod(5, 15, Mana.getInstance(), ModType.PERCENT),
                        new StatMod(5, 15, Stats.CRIT_DAMAGE.get(), ModType.FLAT)
                ))
                .devComment("")
                .build();
        UniqueGearBuilder.of(
                        "azuna_ring",
                        "Azuna's Eternal Decree",
                        BaseGearTypes.RING)
                .setReplacesName()
                .stats(Arrays.asList(
                        new StatMod(-50, 50, new ElementalResist(Elements.Fire), ModType.FLAT),
                        new StatMod(-50, 50, new ElementalResist(Elements.Chaos), ModType.FLAT),
                        new StatMod(-50, 15, TreasureQuality.getInstance(), ModType.FLAT),
                        new StatMod(-50, 15, TreasureQuantity.getInstance(), ModType.FLAT)
                ))
                .devComment("God's ring: item find and luck")
                .build();

        UniqueGearBuilder.of(
                        "witch_brew",
                        "Witch's Brew",
                        BaseGearTypes.RING)

                .stats(Arrays.asList(
                        new StatMod(15, 25, new ElementalResist(Elements.Chaos), ModType.PERCENT),
                        new StatMod(25, 50, SpecialStats.BETTER_FOOD_BUFFS, ModType.FLAT),
                        new StatMod(10, 15, SkillDamage.getInstance(), ModType.FLAT),
                        new StatMod(5, 10, ManaRegen.getInstance(), ModType.PERCENT)

                ))

                .devComment("Food buff mage ring")
                .build();

        UniqueGearBuilder.of(
                        "ghostly_shores",
                        "Ghostly Shores",
                        BaseGearTypes.RING)
                .setReplacesName()
                .stats(Arrays.asList(
                        new StatMod(5, 15, Stats.ELEMENTAL_DAMAGE.get(Elements.Cold), ModType.FLAT),
                        new StatMod(6, 10, DodgeRating.getInstance(), ModType.PERCENT),
                        new StatMod(6, 15, Stats.RESOURCE_ON_KILL.get(ResourceType.mana), ModType.FLAT),
                        new StatMod(10, 15, Stats.CRIT_DAMAGE.get(), ModType.FLAT),
                        new StatMod(5, 10, Stats.COOLDOWN_REDUCTION.get(), ModType.FLAT),
                        new StatMod(-3, -6, DatapackStats.STR, ModType.FLAT)
                ))

                .build();

    }
}