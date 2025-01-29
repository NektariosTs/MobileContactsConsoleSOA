package gr.aueb.cf.mobilecontacts.core.serializer;

import gr.aueb.cf.mobilecontacts.dto.MobileContactReadOnlyDTO;

public class Serializer {

    /**
     * No instances of thsi clas should be available
     * its helper class
     *
     */
    private Serializer() {

    }

    public static String serializeDTO(MobileContactReadOnlyDTO readOnlyDTO) {
        return "ID: " + readOnlyDTO.getId() + ", Onoma: " + readOnlyDTO.getFirstname()
                + ", Epwnumo: " + readOnlyDTO.getLastname() + ",thl.arithmos: " + readOnlyDTO.getPhoneNumber();
    }
}
