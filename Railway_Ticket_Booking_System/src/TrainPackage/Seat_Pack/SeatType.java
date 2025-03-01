package TrainPackage.Seat_Pack;

public enum SeatType {
        // unreserved general seating seater
        GENERAL_SEATER,
        //reserved seater
        SECOND_CLASS_SEATER,
        //AC coach seater
        AC_SEATER,

        //sleeper
        SLEEPER_LOWER,
        SLEEPER_MIDDLE,
        SLEEPER_UPPER,
        SLEEPER_SIDE_LOWER,
        SLEEPER_SIDE_UPPER,

        //AC Sleeper
        AC_LOWER,
        AC_MIDDLE,
        AC_UPPER,
        AC_SIDE_LOWER,
        AC_SIDE_UPPER,

        // for goods train
        BOX_CAR,    // Used for transporting general cargo that needs protection from weather (e.g., packaged goods, food, electronics).
        HOPPER_WAGONS,  //Used for bulk materials like coal, cement, and grain, often with self-discharging mechanisms.
        CONTAINER_FLATCAR   //Used for intermodal transport, carrying shipping containers that can be easily transferred between trains, trucks, and ships.
}
