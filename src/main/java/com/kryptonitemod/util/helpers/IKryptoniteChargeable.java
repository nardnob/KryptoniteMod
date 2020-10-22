package com.kryptonitemod.util.helpers;

public interface IKryptoniteChargeable {
    short DEFAULT_CHARGE_TIME = 20; //should be divisible by 4 for the sound effects
    short getChargeTime();
}