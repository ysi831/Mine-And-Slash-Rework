package com.robertx22.age_of_exile.uncommon.enumclasses;

import com.robertx22.age_of_exile.uncommon.utilityclasses.ErrorUtils;

import java.util.Arrays;
import java.util.List;

public enum AttackType {

    attack("attack", "Attack") {},
    dot("dot", "DOT") {},
    reflect("reflect", "Reflect") {},
    all("all", "Any") {};

    public static List<AttackType> getAllUsed() {
        return Arrays.asList(attack, all);
    }

    public String id;

    AttackType(String id, String locname) {
        this.id = id;
        this.locname = locname;

        ErrorUtils.ifFalse(id.equals(this.name()));
    }


    public boolean isHit() {
        return this == attack;
    }

    public boolean isAttack() {
        return this == attack;
    }


    public boolean isDot() {
        return this == dot;
    }

    public boolean matches(AttackType other) {

        if (other == all || this == all) {
            return true;
        }

        return this == other;
    }

    public String locname;

}