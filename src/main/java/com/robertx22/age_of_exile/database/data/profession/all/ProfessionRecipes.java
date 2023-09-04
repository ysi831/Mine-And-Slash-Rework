package com.robertx22.age_of_exile.database.data.profession.all;

import com.robertx22.age_of_exile.database.data.profession.ProfessionRecipe;
import com.robertx22.age_of_exile.database.data.profession.buffs.StatBuffs;
import com.robertx22.temp.SkillItemTier;
import net.minecraft.world.item.Items;

public class ProfessionRecipes {

    public static void init() {

        foods();
        potions();
    }

    private static void potions() {

        int potions = 6;

        ProfessionRecipe.Builder.of(StatBuffs.INT.getHolder(), SkillItemTier.TIER0, Professions.ALCHEMY, potions)
                .coreMaterials(Professions.FARMING) // general farming produce
                .lesser(Items.NETHER_WART, 3) // Vanilla material
                .medium(ProfessionMatItems.Essence.INT.get(), 1) // common misc drop from prof
                .greater(ProfessionMatItems.Essence.Rare.DELIRIUM.get(), 1) // rare prof drop like - Essence of Delirium
                .build();

        ProfessionRecipe.Builder.of(StatBuffs.DEX.getHolder(), SkillItemTier.TIER0, Professions.ALCHEMY, potions)
                .coreMaterials(Professions.FARMING)
                .lesser(Items.CARROT, 3)
                .medium(ProfessionMatItems.Essence.DEX.get(), 1)
                .greater(ProfessionMatItems.Essence.Rare.DELIRIUM.get(), 1)
                .build();

        ProfessionRecipe.Builder.of(StatBuffs.STR.getHolder(), SkillItemTier.TIER0, Professions.ALCHEMY, potions)
                .coreMaterials(Professions.FARMING)
                .lesser(Items.POTATO, 3)
                .medium(ProfessionMatItems.Essence.STR.get(), 1)
                .greater(ProfessionMatItems.Essence.Rare.DELIRIUM.get(), 1)
                .build();

        ProfessionRecipe.Builder.of(StatBuffs.CRIT.getHolder(), SkillItemTier.TIER0, Professions.ALCHEMY, potions)
                .coreMaterials(Professions.FARMING)
                .lesser(Items.GOLDEN_CARROT, 1)
                .medium(ProfessionMatItems.Essence.CRIT.get(), 1)
                .greater(ProfessionMatItems.Essence.Rare.INSANITY.get(), 1)
                .build();

        ProfessionRecipe.Builder.of(StatBuffs.ARCANE.getHolder(), SkillItemTier.TIER0, Professions.ALCHEMY, potions)
                .coreMaterials(Professions.FARMING)
                .lesser(Items.BEETROOT, 5)
                .medium(ProfessionMatItems.Essence.MAGIC.get(), 1)
                .greater(ProfessionMatItems.Essence.Rare.INSANITY.get(), 1)
                .build();

        ProfessionRecipe.Builder.of(StatBuffs.MIGHT.getHolder(), SkillItemTier.TIER0, Professions.ALCHEMY, potions)
                .coreMaterials(Professions.FARMING)
                .lesser(Items.APPLE, 1)
                .medium(ProfessionMatItems.Essence.LIFE.get(), 1)
                .greater(ProfessionMatItems.Essence.Rare.INSANITY.get(), 1)
                .build();

    }

    private static void foods() {
        int foods = 2;

        ProfessionRecipe.Builder.of(StatBuffs.HEALTH.getHolder(), SkillItemTier.TIER0, Professions.COOKING, foods)
                .coreMaterials(Professions.HUSBANDRY)
                .lesser(Items.APPLE, 1)
                .medium(ProfessionMatItems.Essence.LIFE.get(), 1)
                .greater(ProfessionMatItems.Essence.Rare.HYSTERIA.get(), 1)
                .build();

        ProfessionRecipe.Builder.of(StatBuffs.MANA.getHolder(), SkillItemTier.TIER0, Professions.COOKING, foods)
                .coreMaterials(Professions.HUSBANDRY)
                .lesser(Items.BEETROOT, 1)
                .medium(ProfessionMatItems.Essence.MANA.get(), 1)
                .greater(ProfessionMatItems.Essence.Rare.HYSTERIA.get(), 1)
                .build();

        ProfessionRecipe.Builder.of(StatBuffs.ENERGY.getHolder(), SkillItemTier.TIER0, Professions.COOKING, foods)
                .coreMaterials(Professions.HUSBANDRY)
                .lesser(Items.CARROT, 1)
                .medium(ProfessionMatItems.Essence.ENERGY.get(), 1)
                .greater(ProfessionMatItems.Essence.Rare.HYSTERIA.get(), 1)
                .build();

        ProfessionRecipe.Builder.of(StatBuffs.MAGIC.getHolder(), SkillItemTier.TIER0, Professions.COOKING, foods)
                .coreMaterials(Professions.HUSBANDRY)
                .lesser(Items.POTATO, 1)
                .medium(ProfessionMatItems.Essence.MAGIC.get(), 1)
                .greater(ProfessionMatItems.Essence.Rare.HYSTERIA.get(), 1)
                .build();

    }
}
