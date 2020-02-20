package com.example.jimmy.leaguecalculator;

/**
 * Created by Jimmy on 2016-12-02. YEET
 */

public class Champions {
    int id;
    String name;
    String imageName;
    int armor;
    int armorPerLevel;
    int attackDamage;
    int attackDamagePerLevel;
    int attackRange;
    int attackSpeedOffSet;
    int attackSpeedPerLevel;
    int hp;
    int hpPerLevel;
    int hpRegen;
    int hpRegenPerLevel;
    int moveSpeed;
    int mp;
    int mpPerLevel;
    int mpRegen;
    int mpRegenPerLevel;
    int spellBlock;
    int spellBlockPerLevel;

    public Champions(int id, String name, String imageName, int armor, int armorPerLevel, int attackDamage, int attackDamagePerLevel, int attackRange, int attackSpeedOffSet, int attackSpeedPerLevel, int hp, int hpPerLevel, int hpRegen, int hpRegenPerLevel, int moveSpeed, int mp, int mpPerLevel, int mpRegen, int mpRegenPerLevel, int spellBlock, int spellBlockPerLevel) {
        this.id = id;
        this.name = name;
        this.imageName = imageName;
        this.armor = armor;
        this.armorPerLevel = armorPerLevel;
        this.attackDamage = attackDamage;
        this.attackDamagePerLevel = attackDamagePerLevel;
        this.attackRange = attackRange;
        this.attackSpeedOffSet = attackSpeedOffSet;
        this.attackSpeedPerLevel = attackSpeedPerLevel;
        this.hp = hp;
        this.hpPerLevel = hpPerLevel;
        this.hpRegen = hpRegen;
        this.hpRegenPerLevel = hpRegenPerLevel;
        this.moveSpeed = moveSpeed;
        this.mp = mp;
        this.mpPerLevel = mpPerLevel;
        this.mpRegen = mpRegen;
        this.mpRegenPerLevel = mpRegenPerLevel;
        this.spellBlock = spellBlock;
        this.spellBlockPerLevel = spellBlockPerLevel;
    }
}