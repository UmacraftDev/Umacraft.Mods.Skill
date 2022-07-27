package cn.umacraft.mods.skill.skill

class TestSkill : FatherSkill(), ISpeedable {
    override fun getSpeed(): Int {
        return 1
    }
}