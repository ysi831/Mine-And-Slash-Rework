package com.robertx22.age_of_exile.aoe_data.database.stats;

import com.robertx22.age_of_exile.aoe_data.database.exile_effects.adders.ModEffects;
import com.robertx22.age_of_exile.aoe_data.database.stat_conditions.StatConditions;
import com.robertx22.age_of_exile.aoe_data.database.stat_effects.StatEffects;
import com.robertx22.age_of_exile.aoe_data.database.stats.base.DatapackStatBuilder;
import com.robertx22.age_of_exile.aoe_data.database.stats.base.EffectCtx;
import com.robertx22.age_of_exile.aoe_data.database.stats.base.EmptyAccessor;
import com.robertx22.age_of_exile.database.data.stats.Stat;
import com.robertx22.age_of_exile.database.data.stats.StatScaling;
import com.robertx22.age_of_exile.database.data.stats.datapacks.test.DataPackStatAccessor;
import com.robertx22.age_of_exile.database.data.stats.priority.StatPriority;
import com.robertx22.age_of_exile.tags.ModTag;
import com.robertx22.age_of_exile.tags.TagType;
import com.robertx22.age_of_exile.tags.imp.EffectTag;
import com.robertx22.age_of_exile.uncommon.effectdatas.DamageEvent;
import com.robertx22.age_of_exile.uncommon.effectdatas.ExilePotionEvent;
import com.robertx22.age_of_exile.uncommon.effectdatas.TenSecondPlayerTickEvent;
import com.robertx22.age_of_exile.uncommon.enumclasses.Elements;
import com.robertx22.age_of_exile.uncommon.interfaces.EffectSides;

import java.util.Arrays;

public class EffectStats {

    public static DataPackStatAccessor<EffectCtx> CURSE_SELF = DatapackStatBuilder
            .<EffectCtx>of(x -> "cursed_by_" + x.id, x -> x.element)
            .addAllOfType(ModEffects.getCurses())
            .worksWithEvent(TenSecondPlayerTickEvent.ID)
            .setPriority(StatPriority.Spell.FIRST)
            .setSide(EffectSides.Source)
            .addEffect(e -> StatEffects.GIVE_EFFECT_TO_TARGET.get(e))
            .setLocName(x -> Stat.format(
                    "Cursed by " + x.locname
            ))
            .setLocDesc(x -> "You can check the Stats of the Effect in the In-game Library (Accessed via Main and Slash Hub)")
            .modifyAfterDone(x -> {
                x.min = 0;
                x.max = 1;
                x.is_long = true;
            })
            .build();

    public static DataPackStatAccessor<EffectCtx> EFFECT_IMMUNITY = DatapackStatBuilder
            .<EffectCtx>of(x -> x.id + "_immunity", x -> x.element)
            .addAllOfType(ModEffects.getCurses())
            .worksWithEvent(ExilePotionEvent.ID)
            .setPriority(StatPriority.Spell.FIRST)
            .setSide(EffectSides.Target)
            .addCondition(x -> StatConditions.IS_EFFECT.get(x))
            .addEffect(e -> StatEffects.CANCEL_EVENT)
            .setLocName(x -> Stat.format(
                    "You are Immune to " + x.locname
            ))
            .setLocDesc(x -> "")
            .modifyAfterDone(x -> {
                x.min = 0;
                x.max = 1;
                x.is_long = true;
            })
            .build();

    public static DataPackStatAccessor<ModTag> EFFECT_OF_BUFFS_GIVEN_PER_EFFECT_TAG = DatapackStatBuilder
            .<ModTag>of(x -> "inc_effect_of_" + x.GUID() + "_buff_given", x -> Elements.Physical)
            .addAllOfType(ModTag.MAP.get(TagType.Effect))
            .worksWithEvent(ExilePotionEvent.ID)
            .setPriority(StatPriority.Spell.FIRST)
            .setSide(EffectSides.Source)
            .addCondition(x -> StatConditions.EFFECT_HAS_TAG.get(x))
            .addEffect(StatEffects.Layers.ADDITIVE_DAMAGE)
            .setLocName(x -> x.locNameForLangFile() + " Effect Strength")
            .setLocDesc(x -> "Increases the stat strength of effects you give")
            .modifyAfterDone(x -> {
                //  x.is_long = true;
                x.is_perc = true;
                x.scaling = StatScaling.NONE;
            })
            .build();
    public static DataPackStatAccessor<ModTag> EFFECT_OF_BUFFS_ON_YOU_PER_EFFECT_TAG = DatapackStatBuilder
            .<ModTag>of(x -> "inc_effect_of_" + x.GUID() + "_buff_on_you", x -> Elements.Physical)
            .addAllOfType(ModTag.MAP.get(TagType.Effect))
            .worksWithEvent(ExilePotionEvent.ID)
            .setPriority(StatPriority.Spell.FIRST)
            .setSide(EffectSides.Target)
            .addCondition(x -> StatConditions.EFFECT_HAS_TAG.get(x))
            .addEffect(StatEffects.Layers.ADDITIVE_DAMAGE)
            .setLocName(x -> Stat.VAL1 + "% to effectiveness of " + x.locNameForLangFile() + " buffs on you")
            .setLocDesc(x -> "Increases the stat strength of effects you receive")
            .modifyAfterDone(x -> {
                x.is_long = true;
                x.is_perc = true;
                x.scaling = StatScaling.NONE;
            })
            .build();
    public static DataPackStatAccessor<EmptyAccessor> EFFECT_DURATION_YOU_CAST = DatapackStatBuilder
            .ofSingle("eff_dur_u_cast", Elements.Physical)
            .worksWithEvent(ExilePotionEvent.ID)
            .setPriority(StatPriority.Spell.FIRST)
            .setSide(EffectSides.Source)
            .addEffect(e -> StatEffects.INCREASE_EFFECT_DURATION)
            .setLocName(x -> "Effect Duration")
            .setLocDesc(x -> "")
            .modifyAfterDone(x -> {
                x.is_perc = true;
            })
            .build();
    public static DataPackStatAccessor<EffectTag> EFFECT_DURATION_YOU_CAST_PER_TAG = DatapackStatBuilder
            .<EffectTag>of(x -> x.GUID() + "_eff_dur_u_cast", x -> Elements.Physical)
            .addAllOfType(EffectTag.getAll())
            .worksWithEvent(ExilePotionEvent.ID)
            .setPriority(StatPriority.Spell.FIRST)
            .setSide(EffectSides.Source)
            .addCondition(x -> StatConditions.EFFECT_HAS_TAG.get(x))
            .addEffect(e -> StatEffects.INCREASE_EFFECT_DURATION)
            .setLocName(x -> x.locNameForLangFile() + " Effect Duration")
            .setLocDesc(x -> "")
            .modifyAfterDone(x -> {
                x.is_perc = true;
            })
            .build();


    public static DataPackStatAccessor<EffectCtx> CHANCE_OF_APPLYING_EFFECT = DatapackStatBuilder
            .<EffectCtx>of(x -> "chance_of_" + x.id, x -> x.element)
            .addAllOfType(Arrays.asList(
                            ModEffects.BLIND,
                            ModEffects.SLOW
                    )
            )
            .worksWithEvent(DamageEvent.ID)
            .setPriority(StatPriority.Damage.FINAL_DAMAGE)
            .setSide(EffectSides.Source)
            .addCondition(StatConditions.IF_RANDOM_ROLL)
            .addCondition(StatConditions.ELEMENT_MATCH_STAT)
            .addCondition(StatConditions.IS_ATTACK_OR_SPELL_ATTACK)
            .addEffect(x -> StatEffects.GIVE_EFFECT_TO_TARGET.get(x))
            .setLocName(x -> Stat.format(
                    "Your " + x.element.getIconNameFormat() + " Attacks have " + Stat.VAL1 + "% chance of applying " + x.locname
            ))
            .setLocDesc(x -> "Chance to give effect")
            .modifyAfterDone(x -> {
                x.min = 0;
                x.max = 100;
                x.is_long = true;
                x.is_perc = true;
                x.scaling = StatScaling.NONE;
            })
            .build();

    public static void init() {

    }


}