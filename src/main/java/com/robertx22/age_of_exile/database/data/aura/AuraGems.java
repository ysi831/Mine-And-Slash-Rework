package com.robertx22.age_of_exile.database.data.aura;

import com.robertx22.age_of_exile.aoe_data.database.ailments.Ailments;
import com.robertx22.age_of_exile.aoe_data.database.stats.Stats;
import com.robertx22.age_of_exile.database.data.StatMod;
import com.robertx22.age_of_exile.database.data.stats.types.ailment.AilmentChance;
import com.robertx22.age_of_exile.database.data.stats.types.defense.Armor;
import com.robertx22.age_of_exile.database.data.stats.types.defense.DodgeRating;
import com.robertx22.age_of_exile.database.data.stats.types.resources.energy.EnergyRegen;
import com.robertx22.age_of_exile.database.data.stats.types.resources.health.HealthRegen;
import com.robertx22.age_of_exile.database.data.stats.types.resources.magic_shield.MagicShield;
import com.robertx22.age_of_exile.database.data.stats.types.resources.magic_shield.MagicShieldRegen;
import com.robertx22.age_of_exile.database.data.stats.types.resources.mana.ManaRegen;
import com.robertx22.age_of_exile.uncommon.enumclasses.Elements;
import com.robertx22.age_of_exile.uncommon.enumclasses.ModType;
import com.robertx22.age_of_exile.uncommon.enumclasses.PlayStyle;

import java.util.Arrays;

public class AuraGems {

    public static void init() {


        // defenses
        new AuraGem("armor", "Armor", PlayStyle.STR, 0.5F, Arrays.asList(
                new StatMod(10, 30, Armor.getInstance(), ModType.MORE),
                new StatMod(5, 15, Armor.getInstance(), ModType.FLAT)
        )).registerToExileRegistry();

        new AuraGem("dodge", "Dodge", PlayStyle.DEX, 0.5F, Arrays.asList(
                new StatMod(10, 30, DodgeRating.getInstance(), ModType.MORE),
                new StatMod(5, 15, DodgeRating.getInstance(), ModType.FLAT)
        )).registerToExileRegistry();

        new AuraGem("magic_shield", "Magic Shield", PlayStyle.INT, 0.5F, Arrays.asList(
                new StatMod(12, 35, MagicShield.getInstance(), ModType.MORE),
                new StatMod(2, 5, MagicShield.getInstance(), ModType.FLAT)
        )).registerToExileRegistry();


        // regens
        new AuraGem("magic_shield_reg", "Magic Shield Regeneration", PlayStyle.INT, 0.2F, Arrays.asList(
                new StatMod(1, 5, MagicShieldRegen.getInstance(), ModType.FLAT)
        )).registerToExileRegistry();
        new AuraGem("health_reg", "Health Regeneration", PlayStyle.STR, 0.2F, Arrays.asList(
                new StatMod(1, 5, HealthRegen.getInstance(), ModType.FLAT)
        )).registerToExileRegistry();
        new AuraGem("energy_reg", "Energy Regeneration", PlayStyle.DEX, 0.2F, Arrays.asList(
                new StatMod(1, 5, EnergyRegen.getInstance(), ModType.FLAT)
        )).registerToExileRegistry();
        new AuraGem("mana_reg", "Mana Regeneration", PlayStyle.INT, 0.2F, Arrays.asList(
                new StatMod(1, 5, ManaRegen.getInstance(), ModType.FLAT)
        )).registerToExileRegistry();


        // ele dmg
        new AuraGem("cold_damage", "Cold Damage", PlayStyle.DEX, 0.3F, Arrays.asList(
                new StatMod(10, 35, Stats.ELEMENTAL_DAMAGE.get(Elements.Cold), ModType.MORE)
        )).registerToExileRegistry();
        new AuraGem("fire_damage", "Fire Damage", PlayStyle.STR, 0.3F, Arrays.asList(
                new StatMod(10, 35, Stats.ELEMENTAL_DAMAGE.get(Elements.Fire), ModType.MORE)
        )).registerToExileRegistry();
        new AuraGem("lightning_damage", "Lightning Damage", PlayStyle.INT, 0.3F, Arrays.asList(
                new StatMod(10, 35, Stats.ELEMENTAL_DAMAGE.get(Elements.Lightning), ModType.MORE)
        )).registerToExileRegistry();
        new AuraGem("physical_damage", "Physical Damage", PlayStyle.STR, 0.3F, Arrays.asList(
                new StatMod(10, 35, Stats.ELEMENTAL_DAMAGE.get(Elements.Physical), ModType.MORE)
        )).registerToExileRegistry();
        new AuraGem("chaos_damage", "Chaos Damage", PlayStyle.DEX, 0.3F, Arrays.asList(
                new StatMod(10, 35, Stats.ELEMENTAL_DAMAGE.get(Elements.Chaos), ModType.MORE)
        )).registerToExileRegistry();


        // flat ele + ailment
        new AuraGem("cold_ailment", "Decree of Frost ", PlayStyle.DEX, 0.3F, Arrays.asList(
                new StatMod(10, 50, Stats.ELEMENTAL_DAMAGE.get(Elements.Cold), ModType.FLAT),
                new StatMod(5, 25, new AilmentChance(Ailments.FREEZE), ModType.FLAT)
        )).registerToExileRegistry();
        new AuraGem("fire_ailment", "Decree of Fire", PlayStyle.STR, 0.3F, Arrays.asList(
                new StatMod(10, 50, Stats.ELEMENTAL_DAMAGE.get(Elements.Fire), ModType.FLAT),
                new StatMod(5, 25, new AilmentChance(Ailments.BURN), ModType.FLAT)

        )).registerToExileRegistry();
        new AuraGem("lightning_ailment", "Decree of Lightning", PlayStyle.INT, 0.3F, Arrays.asList(
                new StatMod(10, 50, Stats.ELEMENTAL_DAMAGE.get(Elements.Lightning), ModType.FLAT),
                new StatMod(5, 25, new AilmentChance(Ailments.ELECTRIFY), ModType.FLAT)

        )).registerToExileRegistry();
        new AuraGem("physical_ailment", "Decree of Pain", PlayStyle.STR, 0.3F, Arrays.asList(
                new StatMod(10, 50, Stats.ELEMENTAL_DAMAGE.get(Elements.Physical), ModType.FLAT),
                new StatMod(5, 25, new AilmentChance(Ailments.BLEED), ModType.FLAT)

        )).registerToExileRegistry();
        new AuraGem("chaos_ailment", "Decree of Chaos", PlayStyle.DEX, 0.3F, Arrays.asList(
                new StatMod(10, 50, Stats.ELEMENTAL_DAMAGE.get(Elements.Chaos), ModType.FLAT),
                new StatMod(5, 25, new AilmentChance(Ailments.POISON), ModType.FLAT)
        )).registerToExileRegistry();

        new AuraGem("summon_dmg", "Overlord", PlayStyle.INT, 0.3F, Arrays.asList(
                new StatMod(10, 40, Stats.SUMMON_DAMAGE.get(), ModType.MORE)
        )).registerToExileRegistry();


    }
}