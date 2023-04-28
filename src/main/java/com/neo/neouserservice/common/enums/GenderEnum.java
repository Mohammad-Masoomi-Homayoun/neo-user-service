package com.neo.neouserservice.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neo.neouserservice.common.execption.VerificationException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor
@Getter
public enum GenderEnum {

    NOT_SET("Not Set", 0),
    MALE("Male", 1),
    FEMALE("Female", 2);

    private String label;
    private int value;

    @Override
    public String toString(){
        return label;
    }

    public static void verifyGender(GenderEnum gender){
        if ( gender == null){
            throw new VerificationException("Gender cannot be null.");
        }
    }
}
