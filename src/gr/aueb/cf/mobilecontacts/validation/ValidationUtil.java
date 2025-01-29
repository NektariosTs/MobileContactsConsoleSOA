package gr.aueb.cf.mobilecontacts.validation;

import gr.aueb.cf.mobilecontacts.dto.MobileContactInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactUpdateDTO;

public class ValidationUtil {

    /**
     * no instances of this class should be available
     */
    private ValidationUtil() {

    }

    public static String validateDTO(MobileContactInsertDTO insertDTO) {
        String errorResponse = "";

        if (insertDTO.getPhoneNumber().length() <= 5)
            errorResponse += "o thlefwnikos arithmos prepei na exei perisotera apo 5 sumbola\n";
        if (insertDTO.getFirstname().length() < 2)
            errorResponse += "to onoma prepei na periexei duo h perisoterous xarakthres\n";
        if (insertDTO.getLastname().length() < 2)
            errorResponse += "to eponumo prepei na periexei duo h perisoterous xarakthres\n";

        return errorResponse;
    }

    public static String validateDTO(MobileContactUpdateDTO updateDTO) {
        String errorResponse = "";

        if (updateDTO.getPhoneNumber().length() <= 5)
            errorResponse += "o thlefwnikos arithmos prepei na exei perisotera apo 5 sumbola\n";
        if (updateDTO.getFirstname().length() < 2)
            errorResponse += "to onoma prepei na periexei duo h perisoterous xarakthres\n";
        if (updateDTO.getLastname().length() < 2)
            errorResponse += "to eponumo prepei na periexei duo h perisoterous xarakthres\n";

        return errorResponse;
    }
}
